package org.acme.resteasy;

import io.quarkus.runtime.StartupEvent;
import org.acme.resteasy.model.World;
import org.acme.resteasy.repository.WorldRepository;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExampleResource {

    @Inject
    WorldRepository worldRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }


    @GET
    @Path("/db")
    public World db() {
        World world = randomWorldForRead();
        if (world ==null) throw new IllegalStateException( "No data found in DB. Did you seed the database? Make sure to invoke /createdata once." );
        return world;
    }

    @GET
    @Path("/queries")
    public World[] queries(@QueryParam("queries") String queries) {
        final int count = parseQueryCount(queries);
        World[] worlds = randomWorldForRead(count).toArray(new World[0]);
        return worlds;
    }

    private World randomWorldForRead() {
        return worldRepository.findSingleAndStateless(randomWorldNumber());
    }

    private Collection<World> randomWorldForRead(int count) {
        Set<Integer> ids = new HashSet<>(count);
        int counter = 0;
        while (counter < count) {
            counter += ids.add(Integer.valueOf(randomWorldNumber())) ? 1 : 0;
        }
        return worldRepository.find(ids);
    }
    private int parseQueryCount(String textValue) {
        if (textValue == null) {
            return 1;
        }
        int parsedValue;
        try {
            parsedValue = Integer.parseInt(textValue);
        } catch (NumberFormatException e) {
            return 1;
        }
        return Math.min(500, Math.max(1, parsedValue));
    }

    private int randomWorldNumber() {
        return 1 + ThreadLocalRandom.current().nextInt(10000);
    }

    private int randomWorldNumber(final int previousRead) {
        //conceptually split the random space in those before previousRead,
        //and those after: this approach makes sure to not affect the random characteristics.
        final int trueRandom = ThreadLocalRandom.current().nextInt(9999) + 2;
        if (trueRandom<=previousRead) {
            //all figures equal or before the current field read need to be shifted back by one
            //so to avoid hitting the same number while not affecting the distribution.
            return trueRandom - 1;
        }
        else {
            //Those after are generated by taking the generated value 2...10000 as is.
            return trueRandom;
        }
    }

}
