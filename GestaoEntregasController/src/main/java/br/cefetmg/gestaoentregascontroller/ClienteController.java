package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.dao.ClienteDAO;
import br.cefetmg.gestaoentregasdao.exception.PersistenciaException;
import br.cefetmg.gestaoentregasdao.interfaces.IClienteDAO;
import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Empresa;

public class ClienteController {

    private final IClienteDAO clienteDAO = new ClienteDAO();
    private Cliente cliente;
    
    public boolean cadastrarCliente(String nome, String logradouro, String bairro, String cnpj, String cpf, Empresa empresa, String senha, String telefone) throws PersistenciaException {
        cliente = new Cliente(nome, logradouro, bairro, cnpj, cpf, empresa, senha, telefone);
        return clienteDAO.inserir(cliente);
    }
}
