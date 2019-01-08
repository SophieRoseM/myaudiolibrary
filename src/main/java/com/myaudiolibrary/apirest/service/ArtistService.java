package com.myaudiolibrary.apirest.service;

import com.myaudiolibrary.apirest.model.Artist;
import com.myaudiolibrary.apirest.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public Artist findById(Long artistId)throws EntityNotFoundException {
        Artist artist= artistRepository.findOne(artistId);
        if(artist == null){
            throw new EntityNotFoundException("l'artiste d'identifiant : "+ artistId +" n'a pas été trouvé");
        }
        return artist;
    }

}
