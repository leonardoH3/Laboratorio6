/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence;

import edu.eci.pdsw.samples.persistence.jbdcimpl.JDBCDaoFactory;
import java.util.Properties;

/**
 *
 * @author 2107262
 */
public abstract class DaoFactory {
    protected DaoFactory() {
    }

    private static volatile DaoFactory instance = null;

    public static DaoFactory getInstance(Properties appProperties) {
        if (instance == null) {
            synchronized (DaoFactory.class) {
                if (instance == null) {
                    if (appProperties.get("dao").equals("jdbc")) {
                        instance = new JDBCDaoFactory(appProperties);
                    } else {
                        throw new RuntimeException("Wrong configuration: Unsupported DAO:" + appProperties.get("dao"));
                    }
                }
            }
        }
        return instance;
    }

    public abstract void beginSession() throws PersistenceException;

    public abstract DaoPaciente getDaoPaciente();

    public abstract void commitTransaction() throws PersistenceException;

    public abstract void rollbackTransaction() throws PersistenceException;

    public abstract void endSession() throws PersistenceException;
}
