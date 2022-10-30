package com.mdgz.dam.labdam2022.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Favorito {

    @PrimaryKey
    private Integer id;

    UUID alojamientoID;
    UUID usuarioID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
