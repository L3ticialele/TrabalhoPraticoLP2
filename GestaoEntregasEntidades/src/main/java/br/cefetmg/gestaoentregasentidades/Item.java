
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "items")
public class Item implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_item")
    private int id;
    @Column(name="valorUnitario")
    private double valorUnitario;
    @Column(name="quantidade")
    private int quantidade;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_pedido", nullable=false)
    private Pedido pedido;
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL,
               mappedBy = "item")
    private ArrayList<Produto> produtos;

    public Item() {
    }

    public Item(double valorUnitario, int quantidade, Pedido pedido) {
        produtos = new ArrayList<>();
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.pedido = pedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public void setProduto(Produto produto){
        produtos.add(produto);
    }
    
    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    
    
}
