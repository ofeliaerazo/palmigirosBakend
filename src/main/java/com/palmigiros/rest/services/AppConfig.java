
package com.palmigiros.rest.services;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 *
 * @author ruberr
 */
@ApplicationPath("api")
public class AppConfig extends ResourceConfig{

    public AppConfig() {
         packages("com.palmigiros.rest.services;com.palmigiros.rest.auth");
        register(RolesAllowedDynamicFeature.class);
    }
}
