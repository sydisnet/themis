package eu.sydisnet.themis.sandbox.components;

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
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
public class SimpleBean4SetterInjectionEntBeanTest
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
     * Static constructor.
     *
     * <p>
     *     Called at class loading and must be before container initialization.
     * </p>
     */
    static
    {
        LOG.log(Level.INFO, "############### ClassLoader::{0} ###############", MethodHandles.lookup().lookupClass().getName());

        // Background
        // Given a pre-configured JVM
        System.getProperties().put(EJBContainer.MODULES, Paths.get("target", "classes").toFile());
    }

    /**
     * JavaEE / Jakarta EE Runtime.
     */
    private static final EJBContainer container = EJBContainer.createEJBContainer();

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
     * Components under test.
     *
     * <p>
     *     Given a simple component bean.
     * </p>
     */
    private SimpleBean component;

    /**
     * Injects the component under test.
     *
     * @param component the component under test
     */
    @EJB
    public void setComponent(final SimpleBean component)
    {
        this.component = component;
    }

    @Before
    public void resolveTestDependencies() throws NamingException
    {
        LOG.info("############### resolveTestDependencies() ###############");
        // Background
        // And all dependencies successfully resolved
        container.getContext().bind("inject", this);

    }

    @After
    public void releaseTestDependencies() throws NamingException
    {
        LOG.info("############### releaseTestDependencies() ###############");

        container.getContext().unbind("inject");
    }


    @Test
    public void should_be_able_to_test_simple_bean()
    {
        LOG.info("*************** should_be_able_to_test_simple_bean() ***************");

        // Scenario
        // When I try to invoke a method on this bean
        String result = component.invokeService();
        // I should obtain the "Hello Guest !" response
        this.softly.assertThat(result).containsIgnoringCase("Hello Guest");
    }
}
