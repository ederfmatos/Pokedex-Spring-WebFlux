package com.ederfmatos.pokedex.controller;

import com.ederfmatos.pokedex.model.Pokemon;
import com.ederfmatos.pokedex.model.PokemonEvent;
import com.ederfmatos.pokedex.repository.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController("pokemon")
@RequestMapping("/pokemons")
public class PokemonController {

    private final PokemonRepository pokemonRepository;

    public PokemonController(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Pokemon>> findById(@PathVariable("id") String id) {
        return pokemonRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PokemonEvent> getEvents() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(value -> new PokemonEvent(value, "Product event"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pokemon> save(@RequestBody Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<Pokemon>> update(@PathVariable String id, @RequestBody Pokemon pokemon) {
        return pokemonRepository
                .findById(id)
                .flatMap(existingPokemon -> pokemonRepository.save(existingPokemon
                        .withNome(pokemon.getNome())
                        .withTipo(pokemon.getTipo())
                        .withHabilidades(pokemon.getHabilidades())
                        .withPeso(pokemon.getPeso())))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAll() {
        return pokemonRepository.deleteAll();
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return pokemonRepository
                .findById(id)
                .flatMap(existingPokemon -> pokemonRepository.delete(existingPokemon)
                        .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
