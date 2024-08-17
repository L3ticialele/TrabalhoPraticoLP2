
package br.cefetmg.gestaoentregasentidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="funcionarios")
public class Funcionario extends Usuario{
    @Column(name="perfil")
    private Perfil tipoPerfil;

    public Funcionario() {
    }

    public Funcionario(String nome, String senha, String telefone, Empresa empresa, String tipoPerfil){
        super(senha, telefone, nome, empresa, "Funcionario");
        for (Perfil perfil : Perfil.values()) {
            if(tipoPerfil.equals(perfil.toString()))
                this.tipoPerfil = perfil;
        }
    }

    public Perfil getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(Perfil tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

}
