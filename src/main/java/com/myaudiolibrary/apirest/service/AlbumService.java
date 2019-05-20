package com.myaudiolibrary.apirest.service;

import com.myaudiolibrary.apirest.exception.DoublonException;
import com.myaudiolibrary.apirest.model.Album;
import com.myaudiolibrary.apirest.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService
{

    @Autowired
    private AlbumRepository albumRepository;


//question 7 ajouter album
    public Album addAlbum(Album album)throws DoublonException
    {
        if (albumRepository.findByTitle(album.getTitle())!= null)
        {
            throw new DoublonException("le nom de l'artiste : "+album.getTitle()+" existe deja");
        }
        return albumRepository.save(album);
    }

    //question 8 supprimer album
    public void deleteAlbum(Long id){
        albumRepository.delete(id);
    }
}
