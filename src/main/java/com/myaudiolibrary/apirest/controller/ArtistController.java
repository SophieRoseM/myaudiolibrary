package com.myaudiolibrary.apirest.controller;

import com.myaudiolibrary.apirest.model.Artist;
import com.myaudiolibrary.apirest.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/artists")

public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @RequestMapping(value = "/{artistId}", method = RequestMethod.GET,produces = "application/json")
    public Artist trouverArtist(@PathVariable(value ="artistId")Long id) throws EntityNotFoundException {

        return artistService.findById(id);
    }

}
