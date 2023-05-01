package com.example.services;

import com.example.dto.MovieDTO;
import com.example.models.Movie;
import com.example.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Boolean addMovie(MovieDTO movie) {
        Movie newMovie = new Movie();
        setData(movie, newMovie);
        newMovie.setIsShowing(1);
        movieRepository.save(newMovie);
        return true;
    }


    public boolean updateMovie(MovieDTO movie, int id) {
        Movie m = movieRepository.getReferenceById(id);
        setData(movie, m);
        m.setIsShowing(movie.getIsShowing());
        movieRepository.save(m);
        return true;
    }

    public List<String> doAutoComplete(final String input) {
        return getStrings(input);
    }

    public Movie getMovieById(int id) {
        return movieRepository.getById(id);
    }

    public Movie getMovieByName(String name) {
        return movieRepository.findByNameContainsIgnoreCase(name);
    }

    public boolean deleteMovie(int id) {
        movieRepository.deleteById(id);
        return true;
    }

    private void setData(MovieDTO movie, Movie newMovie) {
        newMovie.setName(movie.getName());
        newMovie.setReleaseDate(LocalDate.parse(movie.getReleaseDate()));
        newMovie.setLargeImageURL(movie.getLargeImageURL());
        newMovie.setSmallImageURl(movie.getSmallImageURl());
        newMovie.setCategories(movie.getCategories());
        newMovie.setDescription(movie.getDescription());
        newMovie.setDuration(movie.getDuration());
        newMovie.setActors(movie.getActors());
        newMovie.setDirector(movie.getDirector());
        newMovie.setLanguage(movie.getLanguage());
    }

    private List<String> getStrings(final String input) {
        return movieRepository.getNames().stream().filter(s -> s.toLowerCase().contains(input.toLowerCase())).collect(Collectors.toList());
    }

}

