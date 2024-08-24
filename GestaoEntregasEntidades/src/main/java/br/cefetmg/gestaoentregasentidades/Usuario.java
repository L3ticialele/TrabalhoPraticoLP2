
package br.cefetmg.gestaoentregasentidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
@Inheritance(strategy=InheritanceType.JOINED)
public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_usuario")
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
    @Column(name="cpf_usuario")
    private String cpf;
    private String tipo;
    
    public  Usuario(){
        
    }
    
    public Usuario(String senha, String telefone, String nome, Empresa empresa, String tipo, String cpf){
        this.senha = senha;
        this.telefone = telefone;
        this.nome = nome;
        this.empresa = empresa;
        this.tipo = tipo;
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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
