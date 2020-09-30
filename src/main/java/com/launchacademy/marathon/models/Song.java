package com.launchacademy.marathon.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "songs")
@Getter
@Setter
@NoArgsConstructor
public class Song {
  @Id
  @SequenceGenerator(name="song_generator", sequenceName = "songs_id_seq", allocationSize = 1)
  @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "song_generator")
  @Column(name="id", nullable = false, unique = true)
  private Integer id;

  @NotBlank
  @Size(min = 1, max = 25, message = "Titles cannot be longer than 25 characters")
  @Column(name="title", nullable = false)
  private String title;

  @NotBlank
  @Size(min = 1, max = 16, message = "Genres cannot be longer than 16 characters")
  @Pattern(regexp = "^([^0-9]*)$", message = "Genres cannot contain numbers")
  @Column(name="genre")
  private String genre;

  @NotNull
  @Min(value = 1000, message = "Release years must be after 999")
  @Max(value = 2020, message = "Release years must be before 2020")
  @Column(name="release_year", nullable = false)
  private Integer releaseYear;

  @NotNull
  @Column(name="explicit_content", nullable = false)
  private Boolean explicitContent;
}
