package com.br.quixada.lista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.quixada.lista.dao.Operacoes;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		Operacoes op = new Operacoes();
		op.questao2B().forEach(v -> System.out.println(v.toString()));
		
		//op.questao2A().forEach(v -> System.out.println(v.toString()));
	}
}
