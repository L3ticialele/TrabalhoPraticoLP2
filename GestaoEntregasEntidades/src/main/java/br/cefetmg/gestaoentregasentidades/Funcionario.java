
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

    public Funcionario() {
    }

    public Funcionario(String nome, String senha, String telefone, Empresa empresa, String perfil){
        super(senha, telefone, nome, empresa, "Funcionario");
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

}
