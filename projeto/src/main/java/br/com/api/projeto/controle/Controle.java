package br.com.api.projeto.controle;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.projeto.modelo.Cliente;
import br.com.api.projeto.modelo.Pessoa;
import br.com.api.projeto.repositorio.Repositorio;
import br.com.api.projeto.servico.Servico;


@RestController
public class Controle {
    
    @Autowired
    private Repositorio acao;
    
    @Autowired
    private Servico servico;

    @PostMapping("/api")
    public ResponseEntity<?> cadastrar(@RequestBody Pessoa obj){
        return servico.cadastrar(obj);
    }

    @GetMapping("/api")
    public ResponseEntity<?> selecionar(){
        return servico.selecionar();
    }

    @GetMapping("/api/{codigo}")
    public ResponseEntity<?> selecionarPeloCodigo(@PathVariable int codigo){
        return servico.selecionarPeloCodigo(codigo);
    }

    @PutMapping("/api")
    public ResponseEntity<?> editar(@RequestBody Pessoa obj){
        return servico.editar(obj);
    }

    @DeleteMapping("/api/{codigo}")
    public ResponseEntity<?> remover(@PathVariable int codigo){
        return servico.remover(codigo);
    }

    @GetMapping("/api/contador")
    public long contador(){
        return acao.count();
    }

    @GetMapping("/api/ordenarNomes")
    public List<Pessoa> ordenarNomes(){
        return acao.findByOrderByNome(); //findByOrderByNome+Asc ou Desc  
    }

    @GetMapping("/api/ordenarNomes2")
    public List<Pessoa> ordenarNomes2(){
        return acao.findByNomeOrderByIdadeDesc("Matheus");
    }

    @GetMapping("/api/ordenarIdade")
    public List<Pessoa> ordenarIdade(){
        return acao.findByOrderByIdade();
    }

    @GetMapping("/api/nomeContem")// == like no banco de dados
    public List<Pessoa> nomeContem(){
        return acao.findByNomeContaining("m");
    }

    @GetMapping("/api/iniciaCom")
    public List<Pessoa> iniciaCom(){
        return acao.findByNomeStartsWith("m");
    }

    @GetMapping("/api/terminaCom")
    public List<Pessoa> terminaCom(){
        return acao.findByNomeEndsWith("on");
    }

    @GetMapping("/api/somaIdades")
    public int somaIdades(){
        return acao.somaIdades();
    }

    @GetMapping("/api/idadeMaiorIgual")
    public List<Pessoa> idadeMaiorIgual(){
        return acao.idadeMaiorIgual(23);
    }

    @GetMapping("")
    public String mensagem(){
        return "Hello World!";
    }

    @PostMapping("/pessoa")
    public Pessoa pessoa(@RequestBody Pessoa p){
        
        return p;
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cliente")
    public void cliente(@Valid @RequestBody Cliente obj){

    }

}
