package com.example.reativespring02;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveCrudRepository<Person, String> {

  Mono<Person> findPersonByName(String name);

}
