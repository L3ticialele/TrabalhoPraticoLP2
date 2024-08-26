package br.cefetmg.gestaoentregasentidades;

import br.cefetmg.gestaoentregasentidades.Item;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-08-26T09:04:38", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Produto.class)
public class Produto_ { 

    public static volatile SingularAttribute<Produto, Item> item;
    public static volatile SingularAttribute<Produto, String> localizacao;
    public static volatile SingularAttribute<Produto, String> nome;
    public static volatile SingularAttribute<Produto, Integer> id;

}