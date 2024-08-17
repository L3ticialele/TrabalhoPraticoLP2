
package br.cefetmg.gestaoentregasdao.interfaces;

import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Pedido;
import java.util.List;

public interface IPedidoDAO {
    boolean inserir(Pedido pedido) throws PersistenciaException;

    boolean atualizar(Pedido pedido) throws PersistenciaException;

    boolean delete(int idPedido) throws PersistenciaException;

    List<Pedido> listarTodos() throws PersistenciaException;
    
    Pedido procurarPorId(int idPedido) throws PersistenciaException;
}
