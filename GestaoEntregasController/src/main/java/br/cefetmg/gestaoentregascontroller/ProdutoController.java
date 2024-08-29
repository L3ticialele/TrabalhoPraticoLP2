
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.dao.ProdutoDAO;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IProdutoDAO;
import br.cefetmg.gestaoentregasentidades.Pedido;
import br.cefetmg.gestaoentregasentidades.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
    IProdutoDAO produtoDAO = new ProdutoDAO();
    Produto produto;
    
    public boolean cadastrarProduto(String nome, String localizacao, Pedido pedido) throws PersistenciaException{
        produto = new Produto(nome, localizacao, pedido);
        return produtoDAO.inserir(produto);
    }
    
    public List<Produto> listarProdutos() throws PersistenciaException{
       return produtoDAO.listarTodos();
    }
    
    public ArrayList<String> nomeProdutos(List<Produto> produtos) throws PersistenciaException{
        ArrayList<String> nomeProdutos = new ArrayList<>();
        for(int i=0; i<produtos.size(); i++){
            nomeProdutos.add(produtos.get(i).getNome());
        }
        return nomeProdutos;
    }
    
    public ArrayList<Produto> atualizaDadosProduto(int ultimoProduto) throws PersistenciaException{
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        String nome, localizacao;
        List<Produto> produtos = listarProdutos();
        for (int i=ultimoProduto; i<produtos.size(); i++){
            nome = produtos.get(i).getNome();
            localizacao = produtos.get(i).getLocalizacao();
            produto = new Produto(nome, localizacao, null);
            listaProdutos.add(produto);
        }
        return listaProdutos;
    }
}
