package br.cefetmg.gestaoentregasentidades;

import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Produto;
import br.cefetmg.gestaoentregasentidades.Status;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-08T22:02:41", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, String> endereco;
    public static volatile SingularAttribute<Pedido, Date> dataPedido;
    public static volatile SingularAttribute<Pedido, Funcionario> entregador;
    public static volatile SingularAttribute<Pedido, Double> valorUnitario;
    public static volatile SingularAttribute<Pedido, Cliente> cliente;
    public static volatile SingularAttribute<Pedido, String> marca;
    public static volatile SingularAttribute<Pedido, String> observacoes;
    public static volatile ListAttribute<Pedido, Produto> produtos;
    public static volatile SingularAttribute<Pedido, Double> valorTotal;
    public static volatile SingularAttribute<Pedido, Integer> id;
    public static volatile SingularAttribute<Pedido, String> pagamento;
    public static volatile SingularAttribute<Pedido, Integer> quantidade;
    public static volatile SingularAttribute<Pedido, Status> status;

}