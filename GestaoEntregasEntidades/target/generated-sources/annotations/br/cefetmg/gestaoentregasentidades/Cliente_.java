package br.cefetmg.gestaoentregasentidades;

import br.cefetmg.gestaoentregasentidades.Pedido;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-21T13:39:48", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Cliente.class)
public class Cliente_ extends Usuario_ {

    public static volatile SingularAttribute<Cliente, String> logradouro;
    public static volatile SingularAttribute<Cliente, String> bairro;
    public static volatile SingularAttribute<Cliente, String> cpf;
    public static volatile SingularAttribute<Cliente, String> cnpj;
    public static volatile ListAttribute<Cliente, Pedido> pedidos;

}