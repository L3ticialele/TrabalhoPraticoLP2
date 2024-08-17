
package br.cefetmg.gestaoentregasdao.interfaces;

import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasentidades.Produto;
import java.util.List;

public interface IProdutoDAO {
    boolean inserir(Produto produto) throws PersistenciaException;

    boolean atualizar(Produto produto) throws PersistenciaException;

    boolean delete(int idProduto) throws PersistenciaException;

    List<Produto> listarTodos() throws PersistenciaException;
    
    Produto procurarPorId(int idProduto) throws PersistenciaException;
    
    Produto procurarPorNome(String nomeProduto) throws PersistenciaException;
}
