package es.juliogtrenard.dao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import es.juliogtrenard.model.ModeloTributos;

public class DaoTributos {
    public static void insertar(ModeloTributos tributo, ObjectContainer db) {
		db.store(tributo);
	}

    public static void mayores16(ObjectContainer db) {
        List<ModeloTributos> tributos = db.query(new Predicate<ModeloTributos>() {

            @Override
            public boolean match(ModeloTributos tributo) {
                return tributo.getHabitante().getEdad() > 16;
            }
        });

        for(ModeloTributos tributo : tributos) {
            System.out.println(tributo.toString());
        }
    }
}
