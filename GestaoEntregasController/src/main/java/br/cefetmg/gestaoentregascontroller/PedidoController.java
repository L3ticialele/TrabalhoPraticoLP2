package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.dao.PedidoDAO;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IPedidoDAO;
import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Pedido;
import br.cefetmg.gestaoentregasentidades.Produto;
import br.cefetmg.gestaoentregasentidades.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoController {

    IPedidoDAO pedidoDAO = new PedidoDAO();
    Pedido pedido;

    public boolean cadastrarPedido(Date data, double valorTotal, String status, Cliente cliente, String marca, int quantidade, double valorUnitario, String pagamento, String endereco, Funcionario entregador, String observacoes, Produto produto) throws PersistenciaException {
        pedido = new Pedido(data, valorTotal, status, cliente, marca, quantidade, valorUnitario, pagamento, endereco, entregador, observacoes);
        pedido.setProduto(produto);
        cliente.setPedido(pedido);
        return pedidoDAO.inserir(pedido);
    }

    public List<Pedido> listarPedidos() throws PersistenciaException {
        return pedidoDAO.listarTodos();
    }

    public ArrayList<Pedido> listarPedidosPorCliente(Cliente cliente) throws PersistenciaException {
        return (ArrayList<Pedido>) pedidoDAO.procurarPorCliente(cliente);
    }

    public ArrayList<Pedido> listarPedidosPorEntregador(Funcionario funcionario) throws PersistenciaException {
        return (ArrayList<Pedido>) pedidoDAO.procurarPorEntregador(funcionario);
    }

    public ArrayList<Pedido> atualizaDadosPedido(int ultimoPedido, Usuario usuario) throws PersistenciaException {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        String status, marca, pagamento, endereco, observacoes;
        double valorTotal, valorUnitario;
        Cliente cliente;
        Funcionario entregador;
        ArrayList<Produto> produto;
        int quantidade;
        Date data;
        List<Pedido> pedidos = listarPedidos();
        if (usuario != null) {
            switch (usuario.getTipo()) {
                case "Cliente":
                    pedidos = listarPedidosPorCliente((Cliente) usuario);
                    break;
                case "Funcionario":
                    pedidos = listarPedidosPorEntregador((Funcionario) usuario);
                    break;
                default:
                    break;
            }
        }
        for (int i = ultimoPedido; i < pedidos.size(); i++) {
            listaPedidos.add(pedidos.get(i));
        }
        return listaPedidos;
    }

}
