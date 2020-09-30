package com.launchacademy.marathon.controllers;

import com.launchacademy.marathon.models.Song;
import com.launchacademy.marathon.repositories.SongsRepository;
import lombok.NoArgsConstructor;
import org.aspectj.apache.bcel.classfile.annotation.RuntimeTypeAnnos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SongsRestController {
  //@Autowired //possibly in place of lines 15-18
  private SongsRepository songsRepository;

  @Autowired
  public SongsRestController(SongsRepository songsRepository) {
    this.songsRepository = songsRepository;
  }

  @GetMapping("/songs")
  public Iterable<Song> displaySongs() {
    return songsRepository.findAll();
  }

  @NoArgsConstructor
  private class SongNotFoundException extends RuntimeException {};

  @ControllerAdvice
  private class SongNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SongNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String songNotFoundHandler(SongNotFoundException ex) {
      return ex.getMessage();
    }
  }

  @GetMapping("/songs/{id}")
  public Song displayOneSong(@PathVariable Integer id) {
    return songsRepository.findById(id).orElseThrow( () -> new SongNotFoundException());
  }

  @PostMapping("/songs")
  public Song create(@RequestBody Song song){
    return songsRepository.save(song);
  }
//curl -X POST localhost:8080/api/v1/songs -H 'Content-type:application/json' -d '{"title": "A Whole New World", "genre": "Soundtrack", "releaseYear": "1992", "explicitContent": "false"}'


}
