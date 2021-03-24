package com.gyo.demoSpring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoSpringApplication implements CommandLineRunner {

	private static Logger Log = LoggerFactory.getLogger(DemoSpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Arranque de la aplicación desde el main!");  // Scrive nella console il messaggio
		Log.info("Log de arranque de la aplicación Spring Boot");

		/*
		Il Controller espone le API e chiama il Service per la logica di Business,
		il Service chiama il Repository per l'interazione con il DB
		 */
	}
}
