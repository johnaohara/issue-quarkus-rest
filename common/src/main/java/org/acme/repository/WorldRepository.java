package org.acme.repository;

import org.acme.model.World;

/**
 *
 */
public interface WorldRepository {

    World findSingle(int id);

     void createData();

}
