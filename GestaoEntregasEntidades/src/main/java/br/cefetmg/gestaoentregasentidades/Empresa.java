package br.cefetmg.gestaoentregasentidades;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "empresas")
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private int id;
    @Column(name = "nome_empresa")
    private String nome;
    @Column(name = "cnpj_empresa")
    private String cnpj;
    @Column(name = "cpf_empresa")
    private String cpf;
    @Column(name = "porcentagem_comissao_entregador")
    private double porcentagemComissaoEntregador;
    @OneToMany(fetch = FetchType.EAGER, cascade
            = CascadeType.ALL, mappedBy = "empresa")
    private ArrayList<Funcionario> funcionarios;
    @OneToMany(fetch = FetchType.EAGER, cascade
            = CascadeType.ALL, mappedBy = "empresa")
    private ArrayList<Cliente> clientes;

    public Empresa() {
    }

    public Empresa(String nome, String cnpj, String cpf, double porcentagemComissaoEntregador) {
        funcionarios = new ArrayList<>();
        clientes = new ArrayList<>();
        this.nome = nome;
        this.cnpj = cnpj;
        this.cpf = cpf;
        this.porcentagemComissaoEntregador = porcentagemComissaoEntregador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void setFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getPorcentagemComissaoEntregador() {
        return porcentagemComissaoEntregador;
    }

    public void setPorcentagemComissaoEntregador(double porcentagemComissaoEntregador) {
        this.porcentagemComissaoEntregador = porcentagemComissaoEntregador;
    }

}
