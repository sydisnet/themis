package eu.sydisnet.themis.sandbox.components;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Paths;
import java.util.logging.Level;
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
@SuppressWarnings("DanglingJavadoc")
public class SimpleBean3SystemPropertiesWithStaticConstructorV1MethodTest
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

    /**
     * JavaEE / Jakarta EE Runtime.
     */
    private static EJBContainer container;

    /**
     * Static constructor.
     *
     * <p>
     *     Called at class loading.
     * </p>
     */
    static
    {
        LOG.log(Level.INFO, "############### ClassLoader::{0} ###############", MethodHandles.lookup().lookupClass().getName());

        // Background
        // Given a pre-configured JVM
        System.getProperties().put(EJBContainer.MODULES, Paths.get("target", "classes").toFile());
    }

    @BeforeClass
    public static void startRuntime()
    {
        LOG.info("############### startRuntime() ###############");

        // Background
        // And a started JavaEE / Jakarta EE Runtime;
        container = EJBContainer.createEJBContainer();
    }

    @AfterClass
    public static void stopRuntime()
    {
        LOG.info("############### stopRuntime() ###############");

        if (container != null)
        {
            container.close();
        }
    }

    /**
     * JNDI Naming Service.
     */
    private Context beanLocator;

    @Before
    public void openConnection()
    {
        LOG.info("############### openConnection() ###############");
        // Background
        // And a Ready to serve JNDI Naming Service
        this.beanLocator = container.getContext();
    }

    @After
    public void closeConnection() throws NamingException
    {
        LOG.info("############### closeConnection() ###############");

        if (this.beanLocator != null)
        {
            this.beanLocator.close();
        }
    }


    @Test
    public void should_be_able_to_test_simple_bean() throws NamingException
    {
        LOG.info("*************** should_be_able_to_test_simple_bean() ***************");

        // Scenario
        // Given a simple component bean
        SimpleBean simpleBean = SimpleBean.class.cast(beanLocator.lookup(String.format("java:global/components/%s", SimpleBean.class.getSimpleName())));
        // When I try to invoke a method on this bean
        String result = simpleBean.invokeService();
        // I should obtain the "Hello Guest !" response
        this.softly.assertThat(result).containsIgnoringCase("Hello Guest");
    }
}
