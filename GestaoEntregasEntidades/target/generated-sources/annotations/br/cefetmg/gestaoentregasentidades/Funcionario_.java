package br.cefetmg.gestaoentregasentidades;

import br.cefetmg.gestaoentregasentidades.Perfil;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-08T22:02:41", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Funcionario.class)
public class Funcionario_ extends Usuario_ {

    public static volatile SingularAttribute<Funcionario, String> comissao;
    public static volatile SingularAttribute<Funcionario, Perfil> tipoPerfil;

}