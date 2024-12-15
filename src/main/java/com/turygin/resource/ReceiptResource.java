package com.turygin.resource;

import com.turygin.model.*;
import com.turygin.points.PointPipeline;
import com.turygin.storage.ReceiptEntity;
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
        try {
            ReceiptEntity receiptEntity = Mapper.toReceiptEntity(receipt);

            UUID id = UUID.randomUUID();
            ReceiptManager receiptManager = DATA_STORE.getReceiptManager();
            receiptManager.addReceipt(id, receiptEntity);

            return Response.ok(new ReceiptId(id)).build();
        } catch (Exception e) {
            LOG.error(e);
            return makeBadRequestResponse();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/points")
    public Response getPoints(@PathParam("id") String id) {

        UUID uuid;
        try {
            uuid = Validator.validateUUID(id);
        } catch (Exception e) {
            LOG.error(e);
            return makeNotFoundResponse();
        }

        ReceiptManager receiptManager = DATA_STORE.getReceiptManager();
        ReceiptEntity receiptEntity = receiptManager.getReceipt(uuid);

        if (receiptEntity == null) {
            return makeNotFoundResponse();
        }

        PointPipeline pointPipeline = DATA_STORE.getPointPipeline();
        long points = pointPipeline.compute(receiptEntity);

        return Response.ok(new Points(points)).build();
    }
}
