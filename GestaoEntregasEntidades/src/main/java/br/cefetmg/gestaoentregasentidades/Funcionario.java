
package br.cefetmg.gestaoentregasentidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="funcionarios")
@PrimaryKeyJoinColumn(name="id_usuario")
public class Funcionario extends Usuario{
    @Enumerated(EnumType.STRING)
    @Column(name="perfil", nullable=false)
    private Perfil tipoPerfil;
    @Column(name="comissao")
    private String comissao;

    public Funcionario() {
    }

    public Funcionario(String nome, String senha, String telefone, String perfil, String cpf, String comissao){
        super(senha, telefone, nome, "Funcionario", cpf);
        setTipoPerfil(perfil);
        this.comissao = comissao;
    }
    public Funcionario(String nome, String senha, String telefone, String perfil, String cpf){
        super(senha, telefone, nome, "Funcionario", cpf);
        setTipoPerfil(perfil);
        
    } 
    
    public Perfil getTipoPerfil() {
        return tipoPerfil;
    }

    public final void setTipoPerfil(String perfil) {
        switch(perfil.toUpperCase()){
            case "ADMINISTRADOR" -> tipoPerfil = Perfil.ADMINISTRADOR;
            case "ATENDENTE" -> tipoPerfil = Perfil.ATENDENTE;
            case "ENTREGADOR" -> tipoPerfil = Perfil.ENTREGADOR;
        }
    }
    
    public void setTipoPerfil(Perfil perfil){
        tipoPerfil = perfil;
    }
    
    public void setComissao(String comissao){
        this.comissao = comissao;
    }
    
    public String getComissao(){
        return this.comissao;
    }

}
