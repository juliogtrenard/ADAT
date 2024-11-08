module es.juliogtrenard.app {
    requires org.mariadb.jdbc;
    requires com.opencsv;
    requires jakarta.persistence;
    requires org.slf4j;
    requires org.apache.logging.log4j;
    requires org.hibernate.orm.core;

    opens es.juliogtrenard.dao.sql to org.hibernate.orm.core;
    opens es.juliogtrenard.dao.csv;

    exports es.juliogtrenard.dao.csv to com.opencsv;
    exports es.juliogtrenard.app;
}