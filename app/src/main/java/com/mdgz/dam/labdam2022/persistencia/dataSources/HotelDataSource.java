package com.mdgz.dam.labdam2022.persistencia.dataSources;

import com.mdgz.dam.labdam2022.model.Hotel;

import java.util.List;

public interface HotelDataSource {

    void guardar(Hotel hotel, OnResult<Hotel> callback);

    void getHoteles(OnResult<List<Hotel>> callback);
}
