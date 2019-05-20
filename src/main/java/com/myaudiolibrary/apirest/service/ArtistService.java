package com.myaudiolibrary.apirest.service;

import com.myaudiolibrary.apirest.exception.DoublonException;
import com.myaudiolibrary.apirest.model.Artist;
import com.myaudiolibrary.apirest.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistService
{

    @Autowired
    private ArtistRepository artistRepository;
//question 1 affiche artiste
    public Artist findById(Long id)throws EntityNotFoundException
    {
        Artist artist= artistRepository.findOne(id);
        if(artist == null)
        {
            throw new EntityNotFoundException("l'artiste d'identifiant : "+ id +" n'a pas été trouvé");
        }
        return artist;
    }
//question 2 recherche par nom
   // public List<Artist> findAllByName(String name) {
    // Artist artist = artistRepository.findByName(name);
     //   List<Artist> findAllByName = new ArrayList() ;
    //   if( findAllByName.contains(name)) {
    //       return findAllByName;
   //    }return null;
   // }


    public List<Artist> findByName(String name) throws Exception
    {
         return artistRepository.findByNameContaining(name);
    }


//question 3 affiche liste
    public Long countArtist ()
    {
        return artistRepository.count();
    }

    public Page<Artist> ListName (Integer page, Integer size, String sortDirection, String sortProperty)throws IllegalArgumentException
    {
        if (page == null)
        {
            page = 0;
        }else if(page<0)
        {
            throw new IllegalArgumentException("La page : "+page+" n'est pas valide.Le numero de page ne peut pas etre inferieur à 0.");
        }else if (page>countArtist()/size)
        {
            throw new IllegalArgumentException("La page ne doit pas etre superieur à : " + ((countArtist()/size)+1) );
        }
        PageRequest pageRequest = new PageRequest (page, size , Sort.Direction.fromString(sortDirection), sortProperty);
        return artistRepository.findAll(pageRequest);
    }
//question 4 creation d'artiste
    public Artist addArtist(Artist artist)throws DoublonException
    {
        if (artistRepository.findByName(artist.getName())!= null)
        {
            throw new DoublonException("le nom de l'artiste : "+artist.getName()+" existe deja");
        }
        return artistRepository.save(artist);
    }
    //question 5 modifier artiste
    public Artist modifArtist(Long id,Artist artist)
    {
        return artistRepository.save(artist);
    }

    //question 6 supprimer artiste
    public void deleteArtist(Long id)
    {
        artistRepository.delete(id);
    }

}

