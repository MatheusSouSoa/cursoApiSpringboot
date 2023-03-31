package br.com.api.projeto.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.projeto.modelo.Mensagem;
import br.com.api.projeto.modelo.Pessoa;
import br.com.api.projeto.repositorio.Repositorio;

@Service
public class Servico {
    
    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;

    //METODO PARA CADASTRAR PESSOAS
    public ResponseEntity<?> cadastrar(Pessoa obj){

        mensagem.setMensagem("O nome precisa ser preenchido");
        if(obj.getNome().equals("")){
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST );
        }
        else if(obj.getIdade() < 0){
            mensagem.setMensagem("Informe uma idade valida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }

    //METODO PARA SELECIONAR PESSOAS
    public ResponseEntity<?> selecionar(){

        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    //METODO PARA SELECIONAR PESSOAS ATRAVES DO CODIGO
    public ResponseEntity<?> selecionarPeloCodigo(int codigo){

        if(acao.countByCodigo(codigo) == 0){

            mensagem.setMensagem("Não foi encontrada nenhuma pessoa");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
        }
    }

    //METODO PAARA EDITAR DADOS
    public ResponseEntity<?> editar(Pessoa obj){

        if(acao.countByCodigo(obj.getCodigo()) == 0 ){
            mensagem.setMensagem("O codigo informado não existe. :(");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        else if(obj.getNome().equals("")){
            mensagem.setMensagem("É necessario informar um nome.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        else if(obj.getIdade() < 0){
            mensagem.setMensagem("Informe uma idade valida.");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
        }

    }

    //METODO PARA REMOVER SERVIÇOS
    public ResponseEntity<?> remover(int codigo){

        if(acao.countByCodigo(codigo) == 0){
            mensagem.setMensagem("O codigo informado não existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        else{

            Pessoa obj = acao.findByCodigo(codigo);
            acao.delete(obj);

            mensagem.setMensagem("Pessoa removida com sucesso.");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);

        }

    }
}
