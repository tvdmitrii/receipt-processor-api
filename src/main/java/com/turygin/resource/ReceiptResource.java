package com.turygin.resource;

import com.turygin.model.*;
import com.turygin.storage.ReceiptManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;


@Path("receipts")
public class ReceiptResource extends DataStoreResource {

    private static final Logger LOG = LogManager.getLogger(ReceiptResource.class);

    private static Response makeBadRequestResponse() {
        return Response.status(Response.Status.BAD_REQUEST).entity(new BadRequest()).build();
    }

    private static Response makeNotFoundResponse() {
        return Response.status(Response.Status.NOT_FOUND).entity(new NotFound()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("process")
    public Response processReceipt(Receipt receipt) {
        // TODO: validate input
        UUID id = UUID.randomUUID();
        ReceiptManager receiptManager = DATA_STORE.getReceiptManager();
        receiptManager.addReceipt(id, receipt);

        return Response.ok(new ReceiptId(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/points")
    public Response getPoints(@PathParam("id") UUID id) {
        ReceiptManager receiptManager = DATA_STORE.getReceiptManager();
        Receipt receipt = receiptManager.getReceipt(id);

        if (receipt == null) {
            return makeNotFoundResponse();
        }

        // TODO: compute the number of points
        long points = 0;

        return Response.ok(new Points(points)).build();
    }
}
