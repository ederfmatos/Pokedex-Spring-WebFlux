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
                    new Pokemon(2, "Bulbassauro", 2, "OverGrow", 6.09, "http://pngimg.com/uploads/pokemon/pokemon_PNG149.png"),
                    new Pokemon(1, "Pikachu", 1, "OverGrow", 6.09, "http://pngimg.com/uploads/pokemon/pokemon_PNG148.png"),
                    new Pokemon(3, "Bulbassauro com diabetes", 3, "OverGrow", 6.09, "http://pngimg.com/uploads/pokemon/pokemon_PNG118.png"),
                    new Pokemon(4, "Perry que esqueceu a roupa no varal", 1, "OverGrow", 6.09, "http://pngimg.com/uploads/pokemon/pokemon_PNG110.png")
            )
                    .flatMap(repository::save);

            pokemonFlux
                    .thenMany(repository.findAll())
                    .subscribe(System.out::println);
        };
    }

}
