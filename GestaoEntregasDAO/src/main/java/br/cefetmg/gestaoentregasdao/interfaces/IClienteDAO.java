
package br.cefetmg.gestaoentregasdao.interfaces;

import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Cliente;
import java.util.List;

public interface IClienteDAO {
    boolean inserir(Cliente cliente) throws PersistenciaException;

    boolean atualizar(Cliente cliente) throws PersistenciaException;

    boolean delete(int idCliente) throws PersistenciaException;

    List<Cliente> listarTodos() throws PersistenciaException;
    
    Cliente procurarPorId(int idCliente) throws PersistenciaException;
    
    Cliente procurarPorNome(String nomeCliente) throws PersistenciaException;
}
