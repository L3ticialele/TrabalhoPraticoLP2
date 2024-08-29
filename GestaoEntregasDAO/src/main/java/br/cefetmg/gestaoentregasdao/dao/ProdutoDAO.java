
package br.cefetmg.gestaoentregasdao.dao;

import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IProdutoDAO;
import br.cefetmg.gestaoentregasentidades.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class ProdutoDAO implements IProdutoDAO{
    @Override
    public boolean inserir(Produto produto) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(produto);
            entityManager.getTransaction().commit();
            System.out.println("Produto cadastrado!");
            return true;
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Produto> listarTodos() throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<Produto> criteria
                = entityManager.getCriteriaBuilder().createQuery(Produto.class);
        criteria.select(criteria.from(Produto.class));
        List<Produto> produtos = entityManager.createQuery(criteria).getResultList();

        if (!produtos.isEmpty()) {
            for (Produto produto : produtos) {
                System.out.print(
                        "Id: " + produto.getId()
                        + " Nome: " + produto.getNome()
                        + " Localização: " + produto.getLocalizacao()
                );
            }
        }

        entityManager.close();
        return produtos;
    }

    @Override
    public boolean delete(int idProduto) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Produto produto = entityManager.find(Produto.class, idProduto);

            if (produto != null) {
                entityManager.remove(idProduto);
                entityManager.getTransaction().commit();
                return true;
            } else {
                System.out.println("Não foi possível encontrar o produto com o id: " + idProduto);
                return false;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean atualizar(Produto produto) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Produto produtoPersistido = entityManager.find(Produto.class, produto.getId());

            if (produtoPersistido != null) {
                produtoPersistido.setId(produto.getId());
                produtoPersistido.setPedido(produto.getPedido());
                produtoPersistido.setLocalizacao(produto.getLocalizacao());
                produtoPersistido.setNome(produto.getNome());
                return true;
            } else {
                System.out.println("Não foi possível encontrar o produto com o id: " + produto.getId());
                return false;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Produto procurarPorId(int idProduto) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Produto AS p WHERE p.id =:id ");
            query.setParameter("id", idProduto);
            List<Produto> produtoPersistido = query.getResultList();
            if (!produtoPersistido.isEmpty()) {
                return produtoPersistido.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }
    
    @Override
    public Produto procurarPorNome(String nomeProduto) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Produto AS p WHERE p.nome =:nomeProduto");
            query.setParameter("nomeProduto", nomeProduto);
            List<Produto> produtoPersistido = query.getResultList();
            if (!produtoPersistido.isEmpty()) {
                return produtoPersistido.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }
}
