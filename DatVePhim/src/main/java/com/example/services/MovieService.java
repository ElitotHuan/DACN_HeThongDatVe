package com.example.services;

import com.example.dto.MovieDTO;
import com.example.models.Movie;
import com.example.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Boolean addMovie(MovieDTO movie) {
        Movie newMovie = new Movie();
        return setData(movie, newMovie);
    }

    public boolean updateMovie(MovieDTO movie, int id) {
        Movie m = movieRepository.getReferenceById(id);
        return setData(movie, m);
    }


    public boolean deleteMovie(int id) {
        movieRepository.deleteById(id);
        return true;
    }

    private boolean setData(MovieDTO movie, Movie m) {
        m.setName(movie.getName());
        m.setReleaseDate(LocalDate.parse(movie.getReleaseDate()));
        m.setLargeImageURL(movie.getLargeImageURL());
        m.setSmallImageURl(movie.getSmallImageURl());
        m.setCategories(movie.getCategories());
        m.setDescription(movie.getDescription());
        m.setDuration(movie.getDuration());
        m.setActors(movie.getActors());
        m.setDirector(movie.getDirector());
        m.setLanguage(movie.getLanguage());
        m.setIsShowing(1);
        movieRepository.save(m);
        return true;
    }


}
