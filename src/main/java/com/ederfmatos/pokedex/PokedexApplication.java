package com.ederfmatos.pokedex;

import com.ederfmatos.pokedex.model.Pokemon;
import com.ederfmatos.pokedex.repository.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokedexApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ReactiveMongoOperations operations, PokemonRepository repository) {
        return args -> {
            Flux<Pokemon> pokemonFlux = Flux.just(
                    new Pokemon(1, "Bulbassauro", 1, "OverGrow", 6.09, null),
                    new Pokemon(2, "Charizard", 2, "Blaze", 90.05, null),
                    new Pokemon(3, "Caterpie", 1, "Poeira do Escudo", 2.09, null),
                    new Pokemon(4, "Blastoise", 3, "Torrente", 6.09, null))
                    .flatMap(repository::save);

            pokemonFlux
                    .thenMany(repository.findAll())
                    .subscribe(System.out::println);
        };
    }

}
