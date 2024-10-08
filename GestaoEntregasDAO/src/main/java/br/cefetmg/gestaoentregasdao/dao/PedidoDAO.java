
package br.cefetmg.gestaoentregasdao.dao;

import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IPedidoDAO;
import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Pedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class PedidoDAO implements IPedidoDAO{
    @Override
    public boolean inserir(Pedido pedido) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(pedido);
            entityManager.getTransaction().commit();
            System.out.println("Pedido cadastrado!");
            return true;
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Pedido> listarTodos() throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<Pedido> criteria
                = entityManager.getCriteriaBuilder().createQuery(Pedido.class);
        criteria.select(criteria.from(Pedido.class));
        List<Pedido> pedidos = entityManager.createQuery(criteria).getResultList();

        if (!pedidos.isEmpty()) {
            for (Pedido pedido : pedidos) {
                System.out.print(
                        "Id: " + pedido.getId()
                        + " Valor Total: " + pedido.getValorTotal()
                        + " Status: " + pedido.getStatus()
                        + " Data: " + pedido.getDataPedido()
                );
            }
        }

        entityManager.close();
        return pedidos;
    }

    @Override
    public boolean delete(int idPedido) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Pedido pedido = entityManager.find(Pedido.class, idPedido);

            if (pedido != null) {
                entityManager.remove(idPedido);
                entityManager.getTransaction().commit();
                return true;
            } else {
                System.out.println("Não foi possível encontrar o pedido com o id: " + idPedido);
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
    public boolean atualizar(Pedido pedido) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Pedido pedidoPersistido = entityManager.find(Pedido.class, pedido.getId());

            if (pedidoPersistido != null) {
                pedidoPersistido.setId(pedido.getId());
                pedidoPersistido.setCliente(pedido.getCliente());
                pedidoPersistido.setDataPedido(pedido.getDataPedido());
                pedidoPersistido.setProdutos(pedido.getProdutos());
                pedidoPersistido.setStatus(pedido.getStatus());
                pedidoPersistido.setValorTotal(pedido.getValorTotal());
                pedidoPersistido.setObservacoes(pedido.getObservacoes());
                entityManager.getTransaction().commit();
                return true;
            } else {
                System.out.println("Não foi possível encontrar o pedido com o id: " + pedido.getId());
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
    public Pedido procurarPorId(int idPedido) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Pedido AS p WHERE p.id =:id ");
            query.setParameter("id", idPedido);
            List<Pedido> pedidoPersistido = query.getResultList();
            if (!pedidoPersistido.isEmpty()) {
                return pedidoPersistido.get(0);
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
    public List<Pedido> procurarPorCliente(Cliente cliente) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Pedido AS p WHERE p.cliente =:id ");
            query.setParameter("id", cliente.getId());
            List<Pedido> pedidoPersistido = query.getResultList();
            if (!pedidoPersistido.isEmpty()) {
                return pedidoPersistido;
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
    public List<Pedido> procurarPorEntregador(Funcionario entregador) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Pedido AS p WHERE p.entregador =:id ");
            query.setParameter("id", entregador.getId());
            List<Pedido> pedidoPersistido = query.getResultList();
            if (!pedidoPersistido.isEmpty()) {
                return pedidoPersistido;
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
