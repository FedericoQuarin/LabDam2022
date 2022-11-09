package com.mdgz.dam.labdam2022.persistencia.dataSources;

import com.mdgz.dam.labdam2022.model.Ciudad;

import java.util.List;

public interface CiudadDataSource {

    void guardar(Ciudad ciudad, OnResult<Ciudad> callback);

    void getCiudades(OnResult<List<Ciudad>> callback);
}
