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
            ProductRepository productRepository
    ) {
        return (args) -> {

            var user1 = new User("Yannick", "slash", "slashcs7@hotmail.com", "1234");
            user1.setAccountEnabled(false);
            user1.setAccountNonLocked(false);

            var address = new Address("314 66th Ave #18", "IA", "52404", "US");
            var address2 = new Address("Zaplin st 33", "CA", "7777", "US");


            user1.addAddress(address);
            user1.addAddress(address2);


            var phoneDpt = new Department(
                    "Phone",
                    "This department deals with all phones brands");
            new Department(
                    "Phone",
                    "This department deals with all phones brands");

            departmentRepository.save(phoneDpt);

            var product1 = new Product(
                    "Iphone",
                    "Best apple phone ever",
                    1045.35,
                    phoneDpt);

            var product2 = new Product(
                    "Samsung s23",
                    "Best samsung phone ever on the market place",
                    850.75,
                    phoneDpt);

            productRepository.save(product2);
            productRepository.save(product1);

            userRepository.save(user1);

            var cart = new Cart(user1, product2);

            cartRepository.save(new Cart(user1, product1));
            cartRepository.save(new Cart(user1, product1));
            cartRepository.save(cart);

        };
    }

}
