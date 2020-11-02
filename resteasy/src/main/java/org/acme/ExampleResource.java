package org.acme;

import org.acme.model.World;
import org.acme.repository.WorldRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
public class ExampleResource {

    @Inject
    WorldRepository worldRepository;

    @GET
    @Path("/db")
    @Produces(MediaType.APPLICATION_JSON)
    public World db() {
        World world = worldRepository.findSingle(randomWorldNumber());
        if (world ==null) throw new IllegalStateException( "No data found in DB. Did you seed the database? Make sure to invoke /createdata once." );
        return world;
    }

    @GET
    @Path("/createData")
    @Produces(MediaType.TEXT_PLAIN)
    public String createData() {
        worldRepository.createData();
        return "ok";
    }

    private int randomWorldNumber() {
        return 1 + ThreadLocalRandom.current().nextInt(10000);
    }


}

