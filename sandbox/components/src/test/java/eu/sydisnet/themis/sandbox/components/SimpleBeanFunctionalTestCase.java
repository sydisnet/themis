package eu.sydisnet.themis.sandbox.components;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import javax.ejb.embeddable.EJBContainer;
import java.lang.invoke.MethodHandles;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author sydisnet
 *         Created on 29/04/18.
 *         Project: themis
 * Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
@RunWith(Cucumber.class)
@CucumberOptions(tags = "@HLR-Y", plugin =
    {   "pretty"/*, "usage"*/,
        "html:target/cucumber-reports",
        "json:target/cucumber-reports/cucumber.json",
        "junit:target/cucumber-reports/cucumber.xml"
    }
)
public class SimpleBeanFunctionalTestCase
{
    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().getClass().getName());

    @BeforeClass
    public static void configure()
    {
        LOG.log(Level.INFO, "############### ClassLoader::{0} ###############", MethodHandles.lookup().lookupClass().getName());

        // Background
        // Given a pre-configured JVM
        System.getProperties().put(EJBContainer.MODULES, Paths.get("target", "classes").toFile());
    }
}
