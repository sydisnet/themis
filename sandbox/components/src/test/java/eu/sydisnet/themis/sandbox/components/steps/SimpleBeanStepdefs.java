package eu.sydisnet.themis.sandbox.components.steps;

import cucumber.api.java8.Fr;
import eu.sydisnet.themis.sandbox.components.SimpleBean;
import org.assertj.core.api.Assertions;

import javax.ejb.EJB;

/**
 * Cucumber steps for {@link SimpleBean} class.
 *
 * @author sydisnet
 *         Created on 29/04/18.
 *         Project: themis
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
@SuppressWarnings("unused")
public class SimpleBeanStepdefs implements Fr
{
    /**
     * Component under test.
     */
    @EJB
    private SimpleBean component;

    private String invokeResult;

    public SimpleBeanStepdefs()
    {
        Etantdonné("^un composant simple$", () ->
            // Write code here that turns the phrase above into concrete actions
//            throw new cucumber.api.PendingException();
            Assertions.assertThat(this.component).isNotNull()
        );

        Lorsque("^j'invoque un de ses méthodes métiers$", () ->
            this.invokeResult = this.component.invokeService()
        );

        Alors("^le système ne me pète pas à la tronche$", () ->
            Assertions.assertThat(this.invokeResult).containsIgnoringCase("Hello Guest")
        );
    }
}
