package es.juliogtrenard.dao;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;
import es.juliogtrenard.model.ModeloJuegos;
import es.juliogtrenard.model.ModeloTributos;

public class DaoJuegos {
    public static void juegosParticipoTributo(ObjectContainer db, ModeloTributos tributo) {
        List<ModeloJuegos> juegos = db.query(new Predicate<ModeloJuegos>() {

            @Override
            public boolean match(ModeloJuegos juego) {
                return juego.getTributos().contains(tributo);
            }
        });

        for(ModeloJuegos juego : juegos) {
            System.out.println(juego.toString());
        }
    }

    public static void cambiarGanador(ObjectContainer db, ModeloJuegos juego, ModeloTributos nuevoGanador) {
        juego.setGanador(nuevoGanador);
        db.store(juego);
    }

    public static void eliminar(ObjectContainer db, ModeloJuegos juego) {
		db.delete(juego);
	}
}
