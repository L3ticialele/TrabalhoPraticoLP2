
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.dao.ProdutoDAO;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IProdutoDAO;
import br.cefetmg.gestaoentregasentidades.Item;
import br.cefetmg.gestaoentregasentidades.Produto;

public class ProdutoController {
    public boolean cadastrarProduto(String nome, String localizacao, Item item) throws PersistenciaException{
        IProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = new Produto(nome, localizacao, item);
        return produtoDAO.inserir(produto);
    }
}
