
package br.cefetmg.gestaoentregasdao.interfaces;

import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.ItemPedido;
import java.util.List;

public interface IItemPedidoDAO {
    boolean inserir(ItemPedido item) throws PersistenciaException;

    boolean atualizar(ItemPedido item) throws PersistenciaException;

    boolean delete(int idItem) throws PersistenciaException;

    List<ItemPedido> listarTodos() throws PersistenciaException;
    
    ItemPedido procurarPorId(int idItem) throws PersistenciaException;
}
