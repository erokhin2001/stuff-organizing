package com.art.stuff;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.art.stuff.domain.Item;
import com.art.stuff.domain.ItemRepository;
import com.art.stuff.domain.LocationRepository;
import com.art.stuff.domain.TypeRepository;

@SpringBootApplication
public class StuffOrganizingProjectApplication {

	private static final Logger log = LoggerFactory.getLogger(StuffOrganizingProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StuffOrganizingProjectApplication.class, args);
	}

	@Bean
    public CommandLineRunner stuffApplication(ItemRepository itemRepository, TypeRepository typeRepository, LocationRepository locationRepository) {
        return (args) -> {

            log.info("fetch all items");
            for (Item item : itemRepository.findAll()) {
                log.info(item.toString());
            }

        };

    }

}
