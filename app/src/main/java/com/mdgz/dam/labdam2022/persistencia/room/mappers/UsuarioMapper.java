package com.mdgz.dam.labdam2022.persistencia.room.mappers;

import com.mdgz.dam.labdam2022.model.Usuario;
import com.mdgz.dam.labdam2022.persistencia.room.entities.UsuarioEntity;

public class UsuarioMapper {
    private UsuarioMapper() {}

    public static UsuarioEntity toEntity (Usuario usuario) {
        return new UsuarioEntity (
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
    }

    // TODO: ver como recuperar listas
    public static Usuario fromEntity (UsuarioEntity usuario) {
        return new Usuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                null,
                null
        );
    }
}
