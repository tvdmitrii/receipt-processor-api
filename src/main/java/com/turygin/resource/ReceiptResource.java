package com.turygin.resource;

import com.turygin.model.*;
import com.turygin.points.PointPipeline;
import com.turygin.storage.Config;
import com.turygin.storage.ReceiptEntity;
import com.turygin.storage.ReceiptManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

/**
 * Receipt endpoint that allows for processing receipts and computing points awarded per receipt.
 */
@Path("receipts")
public class ReceiptResource {

    /** Configuration object that handles receipt storage and point pipeline. */
    private static final Config CONFIG = Config.getInstance();

    private static final Logger LOG = LogManager.getLogger(ReceiptResource.class);

    /**
     * Helper method that creates bad request response.
     * @return response with BadRequest model and 400 status
     */
    private static Response makeBadRequestResponse() {
        return Response.status(Response.Status.BAD_REQUEST).entity(new BadRequest()).build();
    }

    /**
     * Helper method that creates not found response.
     * @return response with NotFound model and 404 status
     */
    private static Response makeNotFoundResponse() {
        return Response.status(Response.Status.NOT_FOUND).entity(new NotFound()).build();
    }

    /**
     * Endpoint for accepting receipts.
     * @param receipt receipt model (request body)
     * @return 200 OK response and receipt UUID if receipt is valid, 400 response otherwise
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("process")
    public Response processReceipt(Receipt receipt) {
        try {
            // Validate the receipt and convert to entity
            ReceiptEntity receiptEntity = Mapper.toReceiptEntity(receipt);

            UUID id = UUID.randomUUID();
            ReceiptManager receiptManager = CONFIG.getReceiptManager();
            receiptManager.addReceipt(id, receiptEntity);

            return Response.ok(new ReceiptId(id)).build();
        } catch (Exception e) {
            LOG.error(e);
            return makeBadRequestResponse();
        }
    }

    /**
     * Endpoint for requesting the number of points awarded per receipt.
     * @param id receipt UUID (path parameter)
     * @return 200 OK response and the number of points awarded if receipt UUID is valid and exists,
     * 404 response otherwise
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/points")
    public Response getPoints(@PathParam("id") String id) {

        UUID uuid;

        // Validate UUID
        try {
            uuid = Validator.validateUUID(id);
        } catch (Exception e) {
            LOG.error(e);
            return makeNotFoundResponse();
        }

        ReceiptManager receiptManager = CONFIG.getReceiptManager();
        ReceiptEntity receiptEntity = receiptManager.getReceipt(uuid);

        // Validate that receipt exists
        if (receiptEntity == null) {
            return makeNotFoundResponse();
        }

        PointPipeline pointPipeline = CONFIG.getPointPipeline();
        long points = pointPipeline.compute(receiptEntity);

        return Response.ok(new Points(points)).build();
    }
}
