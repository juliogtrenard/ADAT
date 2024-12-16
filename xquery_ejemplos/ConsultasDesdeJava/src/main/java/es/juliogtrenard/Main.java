package es.juliogtrenard;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultItem;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;

public class Main {
    public static void main(String[] args) throws XQException {
        XQDataSource server = new ExistXQDataSource();
        server.setProperty("serverName", "localhost");
        server.setProperty("port", "8080");
        server.setProperty("user", "admin");
        server.setProperty("password", "");

        XQConnection conn = server.getConnection("admin", "");

        XQPreparedExpression consulta;
        XQResultSequence resultado;
        consulta = conn.prepareExpression("/EMPLEADOS/EMP_ROW[DEPT_NO=10]");
        resultado = consulta.executeQuery();

        /*
         * while(resultado.next())
         * System.out.println("Elemento: " + resultado.getItemAsString(null));
         */

        XQResultItem r_item;
        while (resultado.next()) {
            r_item = (XQResultItem) resultado.getItem();
            System.out.println("Elemento: " + r_item.getItemAsString(null));
        }
    }
}