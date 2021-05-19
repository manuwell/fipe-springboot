package com.example.demo.fipe;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.fipe.entities.Marca;
import com.example.demo.fipe.entities.Modelo;
import com.example.demo.fipe.entities.ModeloAno;
import com.example.demo.fipe.entities.ModeloList;

@Component
public class FipeClient {
	public static final String ENDPOINT = "https://parallelum.com.br/fipe/api/v1/carros";
	private final RestTemplate restTemplate = new RestTemplate();

	public Marca[] getMarcas() {
		String endpointUrl = ENDPOINT + "/marcas";
		return restTemplate.getForEntity(endpointUrl, Marca[].class).getBody();
	}
	public Modelo[] getModelos(Marca marca) {
		String endpointUrl = ENDPOINT + "/marcas/" + marca.getCodigo() + "/modelos";
		return restTemplate.getForEntity(endpointUrl, ModeloList.class).getBody().getModelos();
	}
	public ModeloAno[] getModeloAnos(Marca marca, Modelo modelo) {
		String endpointUrl = ENDPOINT + "/marcas/" + marca.getCodigo() + "/modelos/" + modelo.getCodigo() + "/anos";
		return restTemplate.getForEntity(endpointUrl, ModeloAno[].class).getBody();
	}
}
