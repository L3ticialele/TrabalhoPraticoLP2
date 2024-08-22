
package br.cefetmg.gestaoentregasdao.interfaces;

import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Item;
import java.util.List;

public interface IItemDAO {
    boolean inserir(Item item) throws PersistenciaException;

    boolean atualizar(Item item) throws PersistenciaException;

    boolean delete(int idItem) throws PersistenciaException;

    List<Item> listarTodos() throws PersistenciaException;
    
    Item procurarPorId(int idItem) throws PersistenciaException;
}
