package com.myaudiolibrary.apirest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.List;

@Entity
@Table (name="artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer artistId;
    private String name;

    @OneToMany (mappedBy = "artist")
    @JsonIgnoreProperties("artist")
    private List<Album> albums;

    public Artist() {}

    public Artist(String name, List<Album> albums) {
        this.name = name;
        this.albums = albums;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
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
