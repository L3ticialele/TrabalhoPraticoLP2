
package br.cefetmg.gestaoentregasentidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="telefone_usuario")
    private String telefone;
    @Column(name="senha_usuario")
    private String senha;
    @Column(name="nome_usuario")
    private String nome;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_empresa", nullable = true)
    private Empresa empresa;
    @Column(name="tipo_usuario")
    private String tipo;
    
    public  Usuario(){
        
    }
    
    public Usuario(String senha, String telefone, String nome, Empresa empresa, String tipo){
        this.senha = senha;
        this.telefone = telefone;
        this.nome = nome;
        this.empresa = empresa;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
    
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
    public String getTelefone(){
        return telefone;
    }
}
