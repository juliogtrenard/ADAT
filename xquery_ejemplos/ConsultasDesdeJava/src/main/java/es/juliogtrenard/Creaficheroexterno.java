package es.juliogtrenard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.xml.xquery.*;
import net.xqj.exist.ExistXQDataSource;

public class Creaficheroexterno {
    public static void main(String[] args) throws XQException {
        String nom_archivo = "NUEVO_EMPLE10.xml";
        File fichero = new File(nom_archivo);
        XQDataSource server = new ExistXQDataSource();
        server.setProperty("serverName", "localhost");
        server.setProperty("port", "8080");
        server.setProperty("user", "admin");
        server.setProperty("password", "");
        XQConnection conn = server.getConnection();
        XQPreparedExpression consulta = conn.prepareExpression(
                "let $titulo:= /EMPLEADOS/TITULO return <EMPLEADOS>{$titulo}" +
                        "{for $em in /EMPLEADOS/EMP_ROW[DEPT_NO=10]" +
                        " return $em}</EMPLEADOS>");
        XQResultSequence result = consulta.executeQuery();
        if (fichero.exists()) { // borramos el archivo si existe y se crea de nuevo
            if (fichero.delete())
                System.out.println("Archivo borrado. Creo de nuevo.");
            else
                System.out.println("Error al borrar el archivo");
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(nom_archivo));
            bw.write("<?xml version='1.0' encoding='ISO-8859-1'?>" + "\n");
            while (result.next()) {
                String cad = result.getItemAsString(null);
                System.out.println(" output " + cad); // visualizamos
                bw.write(cad + "\n"); // grabamos en el fichero
            }
            bw.close(); // Cerramos el fichero el fichero
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        conn.close();
    }
}