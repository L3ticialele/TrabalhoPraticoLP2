
package br.cefetmg.gestaoentregasdao.interfaces;

import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Usuario;
import java.util.List;

public interface IUsuarioDAO {
    public boolean validarLogin(Usuario usuario) throws PersistenciaException;
    public Usuario procurarPorTelefone(String telefone) throws PersistenciaException;
    public Usuario procurarPorCPF(String cpf) throws PersistenciaException;
    List<Usuario> listarTodos() throws PersistenciaException;
}
