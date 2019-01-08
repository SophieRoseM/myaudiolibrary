package com.myaudiolibrary.apirest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name="artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ArtistId")
    private Long artistId;
    @Column(name="Name")
    private String name;

    @OneToMany (mappedBy = "artist")
    @JsonManagedReference // eviter pb de recurcivité
    private List<Album> albums;

    public Artist() {}

    public Artist(String name, List<Album> albums) {
        this.name = name;
        this.albums = albums;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
