package br.com.api.projeto.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "clientes")
public class Cliente {
    
    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @NotEmpty(message = "Informe um nome")
    private String nome;
    @Email(message = "Informe um email valido")
    private String email;



    //GETS AND SETS
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
