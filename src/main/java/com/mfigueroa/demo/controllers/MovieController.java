package com.mfigueroa.demo.controllers;

import com.mfigueroa.demo.model.Movie;
import com.mfigueroa.demo.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/create")
    public ResponseEntity<?> createMovie( @RequestBody Movie movie ){
        Movie movie1 = movieService.createMovie(movie);
        System.out.println("Pelicula guardada");
        return ResponseEntity.ok("Registro guardado\n" + movie1);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        // El ID se genera automáticamente al llamar a save()
        Movie savedMovie = movieService.createMovie(movie);
        return ResponseEntity.ok("Registro creado exitosamente\n" + savedMovie);  // Devolvemos la película guardada con el ID generado
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMovie(@RequestParam Long id, @RequestBody Movie movieDetails){
        try {
            Movie updatedMovie = movieService.updateMovie(id, movieDetails);
            //return ResponseEntity.ok(updatedMovie);
            return ResponseEntity.ok("Registro con id: " + id + ", actualizado...\n" + updatedMovie);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMovie(@RequestParam Long id) {
        try {
            movieService.deleteMovie(id);
            //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204 No Content
            return ResponseEntity.ok("EL regsitro con id: " + id + ", se borro exitosamente...");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);  // Retorna la lista de películas con un status 200 OK
    }

    @GetMapping("/movieByName")
    public ResponseEntity<List<Movie>> getByName(@RequestParam String movieName) {
        List<Movie> movies = movieService.getByName(movieName);
        return ResponseEntity.ok(movies);  // Retorna la lista de películas con un status 200 OK
    }

    @GetMapping("/movieById")
    public ResponseEntity<?> getById(@RequestParam Long id) {
        try {
            Movie movie = movieService.getById(id);
            return ResponseEntity.ok("Registro encontrado: \n" + movie);  // Retorna la lista de películas con un status 200 OK
        }catch (IllegalArgumentException e) {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            return ResponseEntity.ok("Registro no encontrado");
        }
    }
}
