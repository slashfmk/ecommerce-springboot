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

            var user1 = User.builder()
                    .email("slashcs7@hotmail.com")
                    .name("Yannick Fumukani")
                    .username("slash")
                    .password("1234")
                    .build();

            var address = Address.builder()
                    .address("Zariah 89")
                    .state("IA")
                    .country("US")
                    .zip("52404")
                    .build();

            var address2 = Address.builder()
                    .address("Zaplin st 33")
                    .state("CA")
                    .country("US")
                    .zip("58741")
                    .build();




            user1.addAddress(addressRepository.save(new Address("548 kalil", "IA", "52404", "USA")));
          //  user1.addAddress(new Address("548 Jorgon", "NY", "55555", "USA"));


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
