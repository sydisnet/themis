package eu.sydisnet.themis.sandbox.components;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.lang.invoke.MethodHandles;
import java.util.logging.Logger;

/**
 * Unit testing server component.
 *
 * @author sydisnet
 *         Created on 29/04/18.
 *         Project: themis
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
public class SimpleBean1SimplestButNoCustomizableMethodTest
{
    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().getClass().getName());

    /**
     * Enables AssertJ assertions support.
     */
    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test
    public void should_be_able_to_test_simple_bean() throws NamingException
    {
        LOG.info("*************** should_be_able_to_test_simple_bean() ***************");

        // Background
        // Given a started JavaEE / Jakarta EE Runtime
        try (EJBContainer container = EJBContainer.createEJBContainer())
        {
            // And a Ready to serve JNDI Naming Service
            Context beanLocator = container.getContext();

            // Scenario
            // Given a simple component bean
            SimpleBean simpleBean = SimpleBean.class.cast(beanLocator.lookup(String.format("java:global/components/%s", SimpleBean.class.getSimpleName())));
            // When I try to invoke a method on this bean
            String result = simpleBean.invokeService();
            // I should obtain the "Hello Guest !" response
            this.softly.assertThat(result).containsIgnoringCase("Hello Guest");
        }
    }
}
