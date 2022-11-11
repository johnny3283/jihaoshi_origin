package com.nutrientFeature.model;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class NutrientFeatureDAOImpl implements NutrientFeatureDAO{
    public static DataSource ds = null;

    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/jihaoshi");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert() {

    }

    @Override
    public void delete() {

    }

    @Override
    public NutrientFeatureVO findByPk() {
        return null;
    }

    @Override
    public List<NutrientFeatureVO> getAll() {
        return null;
    }
}
