package com.example.personcatalog.controller;

import com.example.personcatalog.model.PersonCatalog;
import com.example.personcatalog.model.PersonSearch;
import com.example.personcatalog.model.Response;
import com.example.personcatalog.service.PersonCatalogservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/person_catalog")
public class PersonCatalogController {

    @Autowired
    PersonCatalogservice personCatalogservice;

    @PostMapping("/create_person")
    public ResponseEntity<Response> createPerson(@RequestBody PersonCatalog personCatalog) {

        return personCatalogservice.createUserCatalog(personCatalog);

    }


    @GetMapping("/create_p")
    public ResponseEntity<Response> testApi() {
        Response response=new Response();
        response.setStatus("sa");
        response.setMessage("?");
        return new ResponseEntity<Response>(response,HttpStatus.OK);

    }


    @PostMapping("/search_person")
    public ResponseEntity<Response> searchPerson(@RequestBody PersonSearch personSearch) {

        return personCatalogservice.fetchPersonByNameORID(personSearch);

    }

}
