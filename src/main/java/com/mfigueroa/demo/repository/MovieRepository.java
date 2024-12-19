package com.mfigueroa.demo.repository;

import com.mfigueroa.demo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {


    @Query("SELECT m FROM Movie m WHERE UPPER(m.proveedor) LIKE UPPER(CONCAT('%', :proveedor, '%'))")
    List<Movie> findByProveedor(String proveedor);

    @Query("SELECT m FROM Movie m WHERE UPPER(m.nombre) LIKE UPPER(CONCAT('%', :nombre, '%'))")
    List<Movie> findByName(String movieName);

    //Optional<Movie>findById(Long id);

    Movie save(Movie movie);

}

