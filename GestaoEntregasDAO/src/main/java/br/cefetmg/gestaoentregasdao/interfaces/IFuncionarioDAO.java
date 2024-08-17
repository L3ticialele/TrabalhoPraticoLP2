
package br.cefetmg.gestaoentregasdao.interfaces;

import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import java.util.List;

public interface IFuncionarioDAO {
    boolean inserir(Funcionario funcionario) throws PersistenciaException;

    boolean atualizar(Funcionario funcionario) throws PersistenciaException;

    boolean delete(int idFuncionario) throws PersistenciaException;

    List<Funcionario> listarTodos() throws PersistenciaException;
    
    Funcionario procurarPorId(int idFuncionario) throws PersistenciaException;
    
    Funcionario procurarPorNome(String nomeFuncionario) throws PersistenciaException;
}
