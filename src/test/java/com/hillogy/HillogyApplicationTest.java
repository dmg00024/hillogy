package com.hillogy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = HillogyApplication.class)
public class HillogyApplicationTest {

	@Test
	public void contextLoads() {
	}

	@Test
	void mainMethodRunsSuccessfully() {
		// Este método prueba la ejecución exitosa del método main
		HillogyApplication.main(new String[] {}); // Puedes pasar los argumentos necesarios aquí
		// Si la ejecución llega a este punto sin excepciones, se considera un éxito
	}

}