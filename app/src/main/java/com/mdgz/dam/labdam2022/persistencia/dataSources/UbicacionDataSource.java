package com.mdgz.dam.labdam2022.persistencia.dataSources;

import com.mdgz.dam.labdam2022.model.Ubicacion;

import java.util.List;

public interface UbicacionDataSource {

    void guardar(Ubicacion ubicacion, OnResult<Ubicacion> callback);

    void getUbicaciones(OnResult<List<Ubicacion>> callback);
}
