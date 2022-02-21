package com.example.personcatalog.service;

import com.example.personcatalog.model.PersonCatalog;
import com.example.personcatalog.model.PersonSearch;
import com.example.personcatalog.model.Response;
import com.example.personcatalog.repository.PersonCatalogRepository;
import com.example.personcatalog.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonCatalogservice {

    @Autowired
    PersonCatalogRepository personCatalogRepository;
    public ResponseEntity<Response> createUserCatalog(PersonCatalog personCatalog){
      Response response=new Response();
        try {
            if (personCatalog.getName()==null||personCatalog.getName().isEmpty()){
               response.setMessage("Name is Empty");
               response.setStatus(Constant.Status.FAIL.name());

            }else if (personCatalog.getWorkedOn()==null||personCatalog.getWorkedOn().isEmpty()){
                response.setMessage("Worked on is Empty");
                response.setStatus(Constant.Status.FAIL.name());

            }else {
               PersonCatalog personCatalogSaved= personCatalogRepository.save(personCatalog);
                response.setMessage("Person Added Sucessfully");
                response.setStatus(Constant.Status.SUCCESS.name());
                response.setObject(personCatalogSaved);
            }

            return new ResponseEntity<Response>(response, HttpStatus.OK);

        }catch (Exception ex) {
            response.setMessage("Exception is : "+ex.getMessage());
            response.setStatus(Constant.Status.EXCEPTION.name());
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Response> fetchPersonByNameORID(PersonSearch personSearch) {
        Response response = new Response();
        try {
            if ((personSearch.getId() == null || personSearch.getId() == 0) && (personSearch.getName() == null || personSearch.getName().isEmpty())) {
                response.setMessage("Both Name and Id should not be  Null or Empty");
                response.setStatus(Constant.Status.FAIL.name());
                return new ResponseEntity<Response>(response, HttpStatus.OK);
            } else if (personSearch.getId() == null || personSearch.getId() == 0) {
                List<PersonCatalog> personCatalogList = personCatalogRepository.findPersonsByName(personSearch.getName());
                if (personCatalogList.isEmpty()) {
                    response.setMessage("Name Not Found");
                    response.setStatus(Constant.Status.FAIL.name());
                    return new ResponseEntity<Response>(response, HttpStatus.OK);
                } else {
                    response.setMessage("Person Found");
                    response.setStatus(Constant.Status.SUCCESS.name());
                    response.setObject(personCatalogList);
                    return new ResponseEntity<Response>(response, HttpStatus.OK);

                }
            } else {
                PersonCatalog personCatalog = personCatalogRepository.findAllById(personSearch.getId());
                if (personCatalog == null) {
                    response.setMessage("Id Not Found");
                    response.setStatus(Constant.Status.FAIL.name());
                    return new ResponseEntity<Response>(response, HttpStatus.OK);
                } else {
                    response.setMessage("Person Found");
                    response.setStatus(Constant.Status.SUCCESS.name());
                    List<PersonCatalog> personCatalogList=new ArrayList<>();
                    personCatalogList.add(personCatalog);
                    response.setObject(personCatalogList);
                    return new ResponseEntity<Response>(response, HttpStatus.OK);

                }

            }
        }catch (Exception exception){

            response.setMessage("Exception is : "+exception.getMessage());
            response.setStatus(Constant.Status.EXCEPTION.name());
            return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);


        }
    }
}
