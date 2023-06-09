package com.slashfmk.ecommerce;

import com.slashfmk.ecommerce.model.Address;
import com.slashfmk.ecommerce.model.Department;
import com.slashfmk.ecommerce.model.Product;
import com.slashfmk.ecommerce.model.User;
import com.slashfmk.ecommerce.repository.AddressRepository;
import com.slashfmk.ecommerce.repository.DepartmentRepository;
import com.slashfmk.ecommerce.repository.ProductRepository;
import com.slashfmk.ecommerce.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            AddressRepository addressRepository,
            DepartmentRepository departmentRepository,
            ProductRepository productRepository
    ) {
        return (args) -> {

            var user1 = new User("Yannick", "slash", "slashcs7@hotmail.com", "1234");
            userRepository.save(user1);

            var address = new Address("314 66th Ave #18", "IA", "52404", "US", user1);
            var address2 = new Address("Zaplin st 33", "CA", "7777", "US", user1);

            addressRepository.save(address2);
            addressRepository.save(address);

            var phoneDpt = new Department(
                    "Phone",
                    "This department deals with all phones brands");

            departmentRepository.save(phoneDpt);

            var iphone14 = new Product(
                    "Iphone",
                    "Best apple phone ever",
                    1045.35,
                    phoneDpt);

            var product2 = new Product(
                    "Samsung s23",
                    "Best samsung phone ever on the market place",
                    850.75,
                    phoneDpt);


            productRepository.save(iphone14);
            productRepository.save(product2);

            //userRepository.save(user1);
        };
    }

}
