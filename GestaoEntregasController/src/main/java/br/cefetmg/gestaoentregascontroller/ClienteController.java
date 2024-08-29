package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.dao.ClienteDAO;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IClienteDAO;
import br.cefetmg.gestaoentregasentidades.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    private final IClienteDAO clienteDAO = new ClienteDAO();
    private Cliente cliente;
    
    public boolean cadastrarCliente(String nome, String logradouro, String bairro, String cnpj, String cpf, String senha, String telefone) throws PersistenciaException {
        cliente = new Cliente(nome, logradouro, bairro, cnpj, cpf, senha, telefone);
        return clienteDAO.inserir(cliente);
    }
    
    public List<Cliente> listarClientes() throws PersistenciaException{
        return clienteDAO.listarTodos();
    }
    
    public ArrayList<Cliente> atualizaDadosCliente(int ultimoCliente) throws PersistenciaException{
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String nome, telefone, bairro, logradouro;
        List<Cliente> clientes = listarClientes();
        for (int i=ultimoCliente; i<clientes.size(); i++){
            nome = clientes.get(i).getNome();
            telefone = clientes.get(i).getTelefone();
            bairro = clientes.get(i).getBairro();
            logradouro = clientes.get(i).getLogradouro(); 
            cliente = new Cliente(nome,  logradouro,  bairro,  null, null,  null, telefone);
            listaClientes.add(cliente);
        }
        return listaClientes;
    }
    
    public Cliente buscarClientePorCpf(String cpf) throws PersistenciaException{
        return clienteDAO.procurarPorCpf(cpf);
    }
}
