package com.turygin.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource extends DataStoreResource {

    private static final Logger LOG = LogManager.getLogger(MyResource.class);

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        LOG.info("getIt");
        return "Got it!";
    }
}
