package org.sid.inventoryservice;

import org.sid.inventoryservice.entities.Product;
import org.sid.inventoryservice.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableDiscoveryClient // pour demander a ce ws d'aller s'enregistrer dans discoveryconsul
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean //inversion des controles.
	CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			Random random = new Random();
			for (int i = 0; i < 10; i++) {
				productRepository.saveAll(List.of(
						Product.builder()
								.name("Computer"+i)
								.price(1200+Math.random()*1000)
								.quantite(1+random.nextInt(200))
								.build()
				));
			}
		};
	}

}
