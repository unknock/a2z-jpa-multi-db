package com.a2z.demo;

import com.a2z.demo.blog.entity.Tutorial;
import com.a2z.demo.blog.repo.TutorialRepository;
import com.a2z.demo.customer.entity.CustomerEntity;
import com.a2z.demo.customer.repo.CustomerRepository;
import com.a2z.demo.product.entity.ProductEntity;
import com.a2z.demo.product.repo.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing
public class A2zApplication implements CommandLineRunner {
    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(A2zApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        initData_4_Product();
        initData_4_Customer();
        initData_4_Tutorial();
    }

    private void initData_4_Product() {
        try {
            ProductEntity newProduct = new ProductEntity("P-1001", "Iphone 12", 1500);

            productRepository.save(newProduct);
        } catch (Exception e) {
            log.error("Init Tutorial error", e);
        }
    }

    private void initData_4_Customer() {
        try {
            CustomerEntity newCustomer = new CustomerEntity("brianvu@gmail.com", "Brian", "Vu");

            customerRepository.save(newCustomer);
        } catch (Exception e) {
            log.error("Init Tutorial error", e);
        }
    }

    private void initData_4_Tutorial() {
        try {
            Tutorial tutorial01 = new Tutorial("Tutorial 01", "About Java", true);
            Tutorial tutorial02 = new Tutorial("Tutorial 02", "About PHP", false);

            tutorialRepository.save(tutorial01);
            tutorialRepository.save(tutorial02);
        } catch (Exception e) {
            log.error("Init Tutorial error", e);
        }
    }
}
