package com.mfigueroa.demo.services;

import com.mfigueroa.demo.model.Movie;
import com.mfigueroa.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie>obtenerPeliculasPorProveedor(String nombreProveedor) {
        return movieRepository.findByProveedor(nombreProveedor);
    }

    public Movie createMovie( Movie movie ) {
//        if (movieRepository.existsById(movie.getId())) {
//            throw new IllegalArgumentException("El ID ya existe, no se puede insertar.");
//        }
//        // Si no existe, guardamos la nueva película
//        return movieRepository.save(movie);
        return movieRepository.save(movie);
        //return "La pelicula \"" + movie.getNombre() + ", se ha creado exitosamente...";
    }

    public Movie updateMovie(Long id, Movie details){

        Movie movie = movieRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Pelicula no encontrada con el id: " + id)
        );
        movie.setNombre(details.getNombre());
        movie.setProveedor(details.getProveedor());
        return movieRepository.save(movie);
    }

    public String deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Pelicula no encontrada con el id: " + id)
        );
        movieRepository.delete(movie);
        return  "Pelicula con id: " + movie.getId() + " Eliminada";
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    public List<Movie> getByName(String movieName) {
        return movieRepository.findByName(movieName);
    }

    public Movie getById(Long id) {
        return movieRepository.getById(id);
    }

}
