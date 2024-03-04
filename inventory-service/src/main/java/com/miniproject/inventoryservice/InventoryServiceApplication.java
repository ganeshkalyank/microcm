package com.miniproject.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.miniproject.inventoryservice.model.Inventory;
import com.miniproject.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication {
	public static void main(String[] args) {SpringApplication.run(InventoryServiceApplication.class, args);}
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args ->{
			Inventory inventory = new Inventory();
			inventory.setSkuCode("Iphone13");
			inventory.setQuantity(100);
			
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("Iphone13_red");
			inventory1.setQuantity(200);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}
}
