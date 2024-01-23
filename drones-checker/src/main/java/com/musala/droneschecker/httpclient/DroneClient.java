package com.musala.droneschecker.httpclient;

import com.musala.dronesdto.dto.DroneResponseShortDto;

import java.util.List;

public interface DroneClient {
    List<DroneResponseShortDto> getDrones();
}
