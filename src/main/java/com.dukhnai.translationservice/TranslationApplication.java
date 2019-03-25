package com.dukhnai.translationservice;

import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TranslationApplication {

    public static void main(String[] args) throws SQLException {
        org.h2.tools.Server dbServer = org.h2.tools.Server.createTcpServer().start();

        SpringApplication.run(TranslationApplication.class, args);

        java.lang.Runtime.getRuntime().addShutdownHook(new Thread(dbServer::stop));
    }
}
