package com.example.pidev1.service;

import com.example.pidev1.entity.Bus;

import java.util.List;

public interface IBus_Service {
    Bus add_Bus(Bus b);

    List<Bus> retrieveAllBuses();

    Bus retrieveBus(Long idBus);

    void remove_Bus(Long idBus);

    Bus updateBus(Long idBus, Bus updatedBus);
}
