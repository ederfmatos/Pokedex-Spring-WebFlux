package com.ederfmatos.pokedex.service;

import com.ederfmatos.pokedex.model.Pokemon;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PokemonService {

    Flux<Pokemon> findAll();

    Mono<Pokemon> findById(String id);

    Mono<Pokemon> save(Pokemon pokemon);

    Mono<Pokemon> update(String id, Pokemon pokemon);

    Mono<Void> deleteAll();

    Mono<Void> delete(String id);

}
