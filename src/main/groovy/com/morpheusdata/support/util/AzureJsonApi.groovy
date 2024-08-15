package com.morpheusdata.support.util
import groovy.json.JsonBuilder
import groovy.util.logging.Slf4j

/**
A class to represent a JSON REST API. This acts like an REST API purely for convenience purposes and to ensure the example can be widely used.
**/
@Slf4j
public class AzureJsonApi {
  
  static final MATRIX_FILMS = [
    [id: 1, title: "The Matrix"],
    [id: 2, title: "The Matrix Reloaded"],
    [id: 3, title: "The Matrix Revolutions"]
  ]
  static final CHARACTERS = [
    [id: 1, name: "Neo", films: [1,2,3]],
    [id: 2, name: "Trinity", films: [1,2,3]],
    [id: 3, name: "Morpheus", films: [1,2,3]],
    [id: 4, name: "Agent Smith", films: [1,2,3]],
    [id: 5, name: "Tank", films: [1]],
    [id: 6, name: "Keymaker", films: [2]],
  ]
  static final SPECIAL_MOVES = [
    [id: 1, character: 1, name: "Whoah!", films: [1]],
    [id: 2, character: 1, name: "Backflip", films: [1,2,3]],
    [id: 3, character: 1, name: "Flying blind", films: [3]],
    [id: 4, character: 2, name: "Rooftop Jump", films: [1]],
    [id: 5, character: 2, name: "Be cool on a motorbike", films: [2]],
    [id: 6, character: 3, name: "Headbut!", films: [1,2,3]],
    [id: 7, character: 3, name: "Sweating", films: [1]],
    [id: 8, character: 4, name: "Sunglasses", films: [1,2,3]],
    [id: 9, character: 5, name: "Electrify!", films: [1]],
    [id: 10, character: 6, name: "Making keys..", films: [2]],
  ]
  
  public static getMatrixFilms(String name) {
    log.debug("AzureJsonApi getMatrixFilm: ${name}")
    def rtn = MATRIX_FILMS
    rtn = rtn.findResults { film ->
      if (film?.title?.toLowerCase()?.contains(name?.toLowerCase())) {
        return film
      }
    }
    return toJSON(rtn)
  }
  
  public static getMatrixFilms(query = [:]) {
    log.debug("AzureJsonApi getMatrixFilms: ${query}")
    return toJSON(MATRIX_FILMS)
  }
  
  public static getMatrixCharacters(query = [:]) {
    log.debug("AzureJsonApi getMatrixCharacters: ${query}")
    def rtn = CHARACTERS
    if (query.filmId) {
      rtn = rtn?.findResults { character ->
        if(character.films?.find { film ->
          if(film?.toString()?.equals(query.filmId?.toString())) {
            return true
          }
        }) {
          return character
        }
      }
    }
    return toJSON(rtn)
  }
  
  public static getMatrixSpecialMoves(query = [:]) {
    log.debug("AzureJsonApi getMatrixSpecialMoves: ${query}")
    def rtn = SPECIAL_MOVES
    if (query.filmId) {
      rtn = rtn?.findResults { move ->
        if(move.films?.find { film ->
          if(film?.toString()?.equals(query.filmId?.toString())) {
            return true
          }
        }) {
          return move
        }
      }
    }
    if (query.characterId) {
      rtn = rtn?.findResults { move ->
        if(move?.character?.toString()?.equals(query.characterId?.toString())) {
          return move
        }
      }
    }
    return toJSON(rtn)
  }
  
  private static toJSON(obj) {
    return new JsonBuilder(obj)?.toString()
  } 
}
