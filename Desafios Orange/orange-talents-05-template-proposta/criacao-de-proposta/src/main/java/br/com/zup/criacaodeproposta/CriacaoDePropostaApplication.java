package br.com.zup.criacaodeproposta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CriacaoDePropostaApplication {

	public static void main(String[] args) {
			SpringApplication.run(CriacaoDePropostaApplication.class, args);

	}



}
