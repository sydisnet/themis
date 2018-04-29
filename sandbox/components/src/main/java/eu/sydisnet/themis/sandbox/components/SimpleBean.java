package eu.sydisnet.themis.sandbox.components;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;

/**
 * Simple component.
 *
 * @author sydisnet
 *         Created on 29/04/18.
 *         Project: themis
 *         Copyright (C) 2018 SYDISNET
 * @version 1.0.0.0-SNAPSHOT
 * @since 1.0.0.0-SNAPSHOT
 */
@Stateless
public class SimpleBean
{

    public String invokeService()
    {
        return "Hello Guest !";
    }
}
