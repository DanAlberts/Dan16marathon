package com.launchacademy.marathon.controllers;

import com.launchacademy.marathon.models.Song;
import com.launchacademy.marathon.repositories.SongsRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/songs")
public class SongsController {
  private SongsRepository songsRepository;

  @Autowired
  public SongsController(SongsRepository songsRepository) {
    this.songsRepository = songsRepository;
  }

  @GetMapping
  public String listSongs(Model model){
    List<Song> songs= (List<Song>) songsRepository.findAll();
    model.addAttribute("songs", songs);
    return "songs/index";
  }

  @GetMapping("/new")
  public String getNew(@ModelAttribute Song song) {
    return "songs/new";
  }

  @PostMapping
  public String addSong(@ModelAttribute @Valid Song song, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "songs/new";
    } else {
      songsRepository.save(song);
      return "redirect:/songs";
    }
  }
}
