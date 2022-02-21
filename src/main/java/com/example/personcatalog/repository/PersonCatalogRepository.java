package com.example.personcatalog.repository;

import com.example.personcatalog.model.PersonCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonCatalogRepository extends JpaRepository<PersonCatalog,Integer> {

    @Query("Select p from PersonCatalog p where p.name like %:name%")
    List<PersonCatalog> findPersonsByName(@Param("name") String name);
    PersonCatalog findAllById(Long id);

}

