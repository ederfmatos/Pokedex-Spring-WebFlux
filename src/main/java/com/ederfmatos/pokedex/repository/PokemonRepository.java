package com.ederfmatos.pokedex.repository;

import com.ederfmatos.pokedex.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokemonRepository extends ReactiveMongoRepository<Pokemon, String> {
}
