package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.dao.PedidoDAO;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IPedidoDAO;
import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Pedido;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoController {
    IPedidoDAO pedidoDAO = new PedidoDAO();
    Pedido pedido;

    public boolean cadastrarPedido(Date data, double valorTotal, String status, Cliente cliente, String marca, int quantidade, double valorUnitario, String pagamento, String endereco, Funcionario entregador, String observacoes) throws PersistenciaException {
        pedido = new Pedido(data, valorTotal, status, cliente, marca, quantidade, valorUnitario, pagamento, endereco, entregador, observacoes);
        return pedidoDAO.inserir(pedido);
    }

    public ArrayList<Pedido> listarPedidos(Cliente cliente) throws PersistenciaException {
        return (ArrayList<Pedido>)pedidoDAO.listarPorCliente(cliente);
    }
    
    public List<Pedido> listarPedidos() throws PersistenciaException{
        return pedidoDAO.listarTodos();
    }
    
    public ArrayList<Pedido> atualizaDadosCliente(int ultimoPedido) throws PersistenciaException{
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        String status, marca, pagamento, endereco, observacoes;
        double valorTotal, valorUnitario;
        Cliente cliente; 
        Funcionario entregador;
        int quantidade;
        List<Pedido> pedidos = listarPedidos();
        for (int i=ultimoPedido; i<pedidos.size(); i++){
            status = pedidos.get(i).getStatus().toString();
            valorTotal = pedidos.get(i).getValorTotal();
            marca = pedidos.get(i).getMarca();
            quantidade = pedidos.get(i).getQuantidade();
            valorUnitario = pedidos.get(i).getValorUnitario();
            pagamento = pedidos.get(i).getPagamento();
            endereco = pedidos.get(i).getEndereco();
            observacoes = pedidos.get(i).getObservacoes();
            cliente = pedidos.get(i).getCliente();
            entregador = pedidos.get(i).getEntregador();
            pedido = new Pedido(null, valorTotal, status, cliente,  marca,  quantidade,  valorUnitario,  pagamento,  endereco,  entregador, observacoes);
            listaPedidos.add(pedido);
        }
        return listaPedidos;
    }
}
