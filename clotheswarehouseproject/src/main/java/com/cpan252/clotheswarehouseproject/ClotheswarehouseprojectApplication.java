package com.cpan252.clotheswarehouseproject;

import com.cpan252.clotheswarehouseproject.model.DistributionCenter;
import com.cpan252.clotheswarehouseproject.model.Item;
import com.cpan252.clotheswarehouseproject.repository.DistributionCenterRepository;
import com.cpan252.clotheswarehouseproject.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

import java.math.BigDecimal;

@SpringBootApplication
public class ClotheswarehouseprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClotheswarehouseprojectApplication.class, args);
	}
	@Bean
	public CommandLineRunner dataLoader(DistributionCenterRepository repository) {
		return args -> {
			repository.save(DistributionCenter.builder()
					.name("Yellow Distribution Center")
					.itemsAvailable(List.of())
					.latitude(43.692593)
					.longitude(-79.556396).build());

			repository.save(DistributionCenter.builder()
					.name("Blue Distribution Center")
					.itemsAvailable(List.of())
					.latitude(43.692593)
					.longitude(-79.556396).build());

			repository.save(DistributionCenter.builder()
					.name("Red Distribution Center")
					.itemsAvailable(List.of())
					.latitude(43.662713)
					.longitude(-79.378462).build());

			repository.save(DistributionCenter.builder()
					.name("Gray Distribution Center")
					.itemsAvailable(List.of())
					.latitude(43.676958)
					.longitude(-79.345321).build());

		};
	}

	@Bean
	public CommandLineRunner dataLoader2(ItemRepository repository) {
		return args -> {
			repository.save(Item.builder()
					.name("Textured Jersey Dress")
					.brand("Zara")
					.year_of_creation(2022)
					.price(new BigDecimal(128.5))
					.quantity(5).build());

			repository.save(Item.builder()
					.name("Oversized Rib-knit Sweater")
					.brand("H&M")
					.year_of_creation(2022)
					.price(new BigDecimal(68.45))
					.quantity(3).build());

			repository.save(Item.builder()
					.name("Shiny Pants")
					.brand("Zara")
					.year_of_creation(2019)
					.price(new BigDecimal(80.05))
					.quantity(2).build());

			repository.save(Item.builder()
					.name("Twill Cargo Pants")
					.brand("UNIQULO")
					.year_of_creation(2013)
					.price(new BigDecimal(89.45))
					.quantity(4).build());

			repository.save(Item.builder()
					.name("Oversized Crinkled Blouse")
					.brand("UNIQULO")
					.year_of_creation(2011)
					.price(new BigDecimal(78.65))
					.quantity(3).build());

			repository.save(Item.builder()
					.name("Oversized Turtleneck Sweater")
					.brand("UNIQULO")
					.year_of_creation(2022)
					.price(new BigDecimal(57.35))
					.quantity(8).build());

			repository.save(Item.builder()
					.name("Pointed-collar Shirt")
					.brand("ZARA")
					.year_of_creation(2008)
					.price(new BigDecimal(38.45))
					.quantity(7).build());

			repository.save(Item.builder()
					.name("Rib-knit Mock Turtleneck Sweater")
					.brand("H&M")
					.year_of_creation(2022)
					.price(new BigDecimal(75.78))
					.quantity(6).build());

			repository.save(Item.builder()
					.name("Trench Coat")
					.brand("H&M")
					.year_of_creation(2020)
					.price(new BigDecimal(250.98))
					.quantity(8).build());

		};
	}
}
