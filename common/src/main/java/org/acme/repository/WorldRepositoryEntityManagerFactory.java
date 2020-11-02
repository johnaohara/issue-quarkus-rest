package org.acme.repository;

import org.acme.model.World;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
public class WorldRepositoryEntityManagerFactory implements WorldRepository {

    @Inject
    EntityManagerFactory entityManagerFactory;

    @Transactional
    @Override
    public void createData() {
        try (StatelessSession statelessSession = entityManagerFactory.unwrap(SessionFactory.class).openStatelessSession()) {
            final ThreadLocalRandom random = ThreadLocalRandom.current();
            for (int i = 1; i <= 10000; i++) {
                final World world = new World();
                world.setId(i);
                world.setRandomNumber(1 + random.nextInt(10000));
                statelessSession.insert(world);
            }
        }
    }

    @Override
    public World findSingle(int id) {
        try (StatelessSession ss = entityManagerFactory.unwrap(SessionFactory.class).openStatelessSession()) {
            return (World) ss.get(World.class, id);
        }
    }


}
