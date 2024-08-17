
package br.cefetmg.gestaoentregasdao.dao;

import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IItemPedidoDAO;
import br.cefetmg.gestaoentregasentidades.ItemPedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class ItemPedidoDAO implements IItemPedidoDAO{
    @Override
    public boolean inserir(ItemPedido item) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(item);
            entityManager.getTransaction().commit();
            System.out.println("Item cadastrado!");
            return true;
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<ItemPedido> listarTodos() throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<ItemPedido> criteria
                = entityManager.getCriteriaBuilder().createQuery(ItemPedido.class);
        criteria.select(criteria.from(ItemPedido.class));
        List<ItemPedido> items = entityManager.createQuery(criteria).getResultList();

        if (!items.isEmpty()) {
            for (ItemPedido item : items) {
                System.out.print(
                        "Id: " + item.getId()
                        + " Valor Unitário: " + item.getValorUnitario()
                        + " Quantidade: " + item.getQuantidade()
                );
            }
        }

        entityManager.close();
        return items;
    }

    @Override
    public boolean delete(int idItem) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            ItemPedido item = entityManager.find(ItemPedido.class, idItem);

            if (item != null) {
                entityManager.remove(idItem);
                entityManager.getTransaction().commit();
                return true;
            } else {
                System.out.println("Não foi possível encontrar o item com o id: " + idItem);
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
    public boolean atualizar(ItemPedido item) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            ItemPedido itemPersistido = entityManager.find(ItemPedido.class, item.getId());

            if (itemPersistido != null) {
                itemPersistido.setId(item.getId());
                itemPersistido.setPedido(item.getPedido());
                itemPersistido.setProdutos(item.getProdutos());
                itemPersistido.setQuantidade(item.getQuantidade());
                itemPersistido.setValorUnitario(item.getValorUnitario());
                entityManager.getTransaction().commit();
                return true;
            } else {
                System.out.println("Não foi possível encontrar o item com o id: " + item.getId());
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
    public ItemPedido procurarPorId(int idItem) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM ItemPedido AS i WHERE i.id =:id ");
            query.setParameter("id", idItem);
            List<ItemPedido> itemPersistido = query.getResultList();
            if (!itemPersistido.isEmpty()) {
                return itemPersistido.get(0);
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
