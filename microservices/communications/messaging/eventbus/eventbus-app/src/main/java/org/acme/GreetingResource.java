package org.acme;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.annotations.Pos;

@Path("/hello")
public class GreetingResource {

    @Inject
    EventBus eventBus;

    @POST
    @Path("async/fireandForget")
    public void process(String payload){
        eventBus.requestAndForget("sink",payload);
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{name}")
    public Uni<String> hello(@PathParam("name") String name) {
          return  eventBus.<String>request("greeting",name).onItem().transform(Message::body);
    }

    @POST
    @Path("publish")
    public void publish(String message) {
        eventBus.publish("notification", message);
    }


}
