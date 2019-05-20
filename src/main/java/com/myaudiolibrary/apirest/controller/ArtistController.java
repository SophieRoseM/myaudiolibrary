package com.myaudiolibrary.apirest.controller;

import com.myaudiolibrary.apirest.exception.DoublonException;
import com.myaudiolibrary.apirest.model.Artist;
import com.myaudiolibrary.apirest.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/artists")

public class ArtistController {

    @Autowired
    private ArtistService artistService;

    //question 1 affiche artiste
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,produces = "application/json")
    public Artist trouverArtist(@PathVariable(value ="id")Long id) throws EntityNotFoundException {

        return artistService.findById(id);
    }
    //question 2 recherche par nom
   // @RequestMapping(params = "name")
   // public List<Artist> findAllByName(@RequestParam("name")String name)throws EntityNotFoundException{
   //     return artistService.findAllByName(name);
   // }

    @RequestMapping(params = "name")
        public List<Artist> artists(@RequestParam(value="name") String name)throws Exception {
            return artistService.findByName(name);
        }



    //question 3 affiche liste
    @RequestMapping()
    public Page<Artist> ListName (@RequestParam(value = "page")Integer page,
                                              @RequestParam(value = "size")Integer size,
                                              @RequestParam(value = "sortDirection")String sortDirection,
                                              @RequestParam (value = "sortProperty")String sortProperty )throws IllegalArgumentException{
        return artistService.ListName(page,size,sortDirection,sortProperty);
    }
    //question 4 creation d'artiste
    @RequestMapping(value = "",method = RequestMethod.POST,consumes="application/json",produces = "application/json")
    public Artist addArtist(@RequestBody Artist artist) throws DoublonException {
        return artistService.addArtist(artist);
    }

    //question 5 modifier artiste
    @RequestMapping(value="/{id}",method = RequestMethod.PUT,consumes="application/json",produces = "application/json")
    public Artist modifArtist(@PathVariable(value = "id")Long id,@RequestBody Artist artist){
            return artistService.modifArtist(id, artist);
    }

    //question 6 supprimer artiste
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable(value = "id")Long id){
        artistService.deleteArtist(id);
    }


}

