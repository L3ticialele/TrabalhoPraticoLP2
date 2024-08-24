package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.dao.PedidoDAO;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IPedidoDAO;
import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Pedido;
import java.util.ArrayList;
import java.util.Date;

public class PedidoController {

    public boolean cadastrarPedido(Date data, double valorTotal, String status, Cliente cliente, String marca, int quantidade, double valorUnitario, String pagamento, String endereco, Funcionario entregador) throws PersistenciaException {
        Pedido pedido = new Pedido(data, valorTotal, status, cliente, marca, quantidade, valorUnitario, pagamento, endereco, entregador);
        IPedidoDAO pedidoDAO = new PedidoDAO();
        return pedidoDAO.inserir(pedido);
    }

    public ArrayList<Pedido> listarPedidos(Cliente cliente) throws PersistenciaException {
        IPedidoDAO pedidoDAO = new PedidoDAO();
        
        return (ArrayList<Pedido>)pedidoDAO.listarPorCliente(cliente);
    }
}
