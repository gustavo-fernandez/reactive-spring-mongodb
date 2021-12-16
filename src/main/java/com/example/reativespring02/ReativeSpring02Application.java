package com.example.reativespring02;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
@Slf4j
public class ReativeSpring02Application {

  public static void main(String[] args) {
    SpringApplication.run(ReativeSpring02Application.class, args);
  }

  // Fwk WebFlux
  @Bean
  CommandLineRunner cmd(PersonRepository personRepository) {
    return args -> {
      Mono<Person> crearAJorge = personRepository.save(new Person(null, "Jorge"));
      Mono<Person> crearAAlonso = personRepository.save(new Person(null, "Alonso"));
      Mono<Person> consultarAAlonso = personRepository.findPersonByName("Alonso");

      crearAJorge
        .then(crearAAlonso)
        .then(consultarAAlonso)
        .doOnNext(alonso -> log.info("Alonso: {}", alonso))
        .subscribe();
    };
  }

}
