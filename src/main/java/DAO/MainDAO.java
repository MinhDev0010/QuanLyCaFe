/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;

/**
 *
 * @author DELL
 */
abstract public class MainDAO<EntityType, KeyType> {

    abstract public int insert(EntityType entity);

    abstract public int update(EntityType entity);

    abstract public void delete(KeyType ID);

    abstract public List<EntityType> selectAll();

    abstract public EntityType selectByID(KeyType id);

    abstract public List<EntityType> selectBySQL(String sql, Object... args);
}
