package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import com.example.demo.fipe.FipeClient;
import com.example.demo.fipe.entities.Marca;
import com.example.demo.fipe.entities.Modelo;
import com.example.demo.fipe.entities.ModeloAno;

@SpringBootApplication
public class DemoApplication {
	
	@Autowired 
	public FipeClient fipeClient;

	public static void main(String[] args) {
		
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	public void testFipeClient() {
		// pegando todas as marcas
		Marca[] marcas = fipeClient.getMarcas();;
		for (Marca marca : marcas) {
			System.out.println("Marca cod: " + marca.getCodigo() + " - " + marca.getNome());
		}
		
		
		// pegando os modelos e anos da primeira marca
		Marca marca = marcas[0];

		Modelo[] modelos = fipeClient.getModelos(marca);
		for (Modelo modelo : modelos) {
			System.out.println("  Modelo: " + modelo.getCodigo() + " - " + modelo.getNome());

			ModeloAno[] anos = fipeClient.getModeloAnos(marca, modelo);
			for (ModeloAno ano : anos) {
				System.out.println("    Ano: " + ano.getCodigo() + " - " + ano.getNome());
			}
		}
		
	}

}
