package com.slashfmk.ecommerce;

import com.slashfmk.ecommerce.model.*;
import com.slashfmk.ecommerce.repository.*;
import com.slashfmk.ecommerce.service.UserService;
import lombok.Builder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            AddressRepository addressRepository,
            CartRepository cartRepository,
            DepartmentRepository departmentRepository,
            ProductRepository productRepository,
            UserService userService
    ) {
        return (args) -> {

            var user1 = new User("Yannick", "slashfmk", "xlt@hotmail.com", "12345");
            var address = new Address("234 Glassdoor", "Arizona", "54874", "Phoenix");


            var address2 = new Address("345 Stallar, apt 12", "Iowa", "25485", "US");


            user1.addAddress(address);
            user1.addAddress(address2);


            var phoneDpt = Department.builder()
                    .description("Phone")
                    .name("This department deals with all phones brands")
                    .build();

            departmentRepository.save(phoneDpt);

            var product1 = Product.builder()
                    .price(785.33)
                    .name("Iphone")
                    .description("Best apple phone ever")
                    .department(phoneDpt)
                    .build();

            var product2 = Product.builder()
                    .price(785.33)
                    .name("Samsung S23")
                    .description("Best samsung phone ever on the market place")
                    .department(phoneDpt)
                    .build();

            productRepository.save(product2);
            productRepository.save(product1);

            userRepository.save(user1);

            cartRepository.save(new Cart(user1, product1));
            cartRepository.save(new Cart(user1, product2));

            userService.getCart(user1.getUserId());

        };
    }

}
