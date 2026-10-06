package com.pupusapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de PupusApp.
 * @SpringBootApplication activa la autoconfiguración y el escaneo de componentes.
 */
@SpringBootApplication
public class PupusAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PupusAppApplication.class, args);
    }
}
