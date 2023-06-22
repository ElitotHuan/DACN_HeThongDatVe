package com.example.services;

import com.example.dto.MovieDTO;
import com.example.models.Movie;
import com.example.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void testGetAll() {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Movie movie = new Movie();
            movie.setId(i + 1);
            movie.setName("Movie" + (i + 1));
            movie.setReleaseDate(LocalDate.now());
            movie.setLargeImageURL("LargeImageURL" + (i + 1));
            movie.setSmallImageURl("SmallImageURL" + (i + 1));
            movie.setCategories("Categories" + (i + 1));
            movie.setDescription("Description" + (i + 1));
            movie.setDuration(120);
            movie.setActors("Actors" + (i + 1));
            movie.setDirector("Director" + (i + 1));
            movie.setLanguage("Language" + (i + 1));
            movies.add(movie);
        }

        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> result = movieService.getAll();
        assertEquals(movies.size(), result.size());
    }

    @Test
    void testAddMovie() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setName("New Movie");
        movieDTO.setReleaseDate(LocalDate.now().toString());
        movieDTO.setLargeImageURL("New LargeImageURL");
        movieDTO.setSmallImageURl("New SmallImageURL");
        movieDTO.setCategories("New Categories");
        movieDTO.setDescription("New Description");
        movieDTO.setDuration(120);
        movieDTO.setActors("New Actors");
        movieDTO.setDirector("New Director");
        movieDTO.setLanguage("New Language");

        boolean result = movieService.addMovie(movieDTO);
        assertTrue(result);

        verify(movieRepository).save(any(Movie.class));
    }

    @Test
    void testUpdateMovie() {
        Movie movie = new Movie();
        movie.setId(1);
        movie.setName("Old Movie");
        movie.setReleaseDate(LocalDate.now());
        movie.setLargeImageURL("Old LargeImageURL");
        movie.setSmallImageURl("Old SmallImageURL");
        movie.setCategories("Old Categories");
        movie.setDescription("Old Description");
        movie.setDuration(120);
        movie.setActors("Old Actors");
        movie.setDirector("Old Director");
        movie.setLanguage("Old Language");

        when(movieRepository.getReferenceById(1)).thenReturn(movie);

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setName("Updated Movie");
        movieDTO.setReleaseDate(LocalDate.now().toString());
        movieDTO.setLargeImageURL("Updated LargeImageURL");
        movieDTO.setSmallImageURl("Updated SmallImageURL");
        movieDTO.setCategories("Updated Categories");
        movieDTO.setDescription("Updated Description");
        movieDTO.setDuration(130);
        movieDTO.setActors("Updated Actors");
        movieDTO.setDirector("Updated Director");
        movieDTO.setLanguage("Updated Language");

        boolean result = movieService.updateMovie(movieDTO, 1);
        assertTrue(result);

        verify(movieRepository).save(any(Movie.class));
    }
}