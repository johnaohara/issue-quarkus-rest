package org.acme.repository;

import org.acme.model.World;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.concurrent.ThreadLocalRandom;

//@Singleton
public class WorldRepositoryEntityManager implements WorldRepository {

    @Inject
    EntityManager entityManager;

    public WorldRepositoryEntityManager() {
        System.out.println("Creating new WorldRepositoryEntityManager");
    }

    @Transactional
    @Override
    public void createData() {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 1; i <= 10000; i++) {
            final World world = new World();
            world.setId(i);
            world.setRandomNumber(1 + random.nextInt(10000));
            entityManager.persist(world);
        }
    }

    @Override
    public World findSingle(int id) {
        return entityManager.find(World.class, id);
    }


}
