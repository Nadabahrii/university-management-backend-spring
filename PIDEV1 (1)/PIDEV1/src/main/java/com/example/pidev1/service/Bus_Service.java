package com.example.pidev1.service;

import com.example.pidev1.entity.Bus;
import com.example.pidev1.repository.Bus_Repository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Bus_Service implements IBus_Service{
    @Autowired
    private Bus_Repository bus_repository;
    @Override
    public Bus add_Bus(Bus b) {
        return bus_repository.save(b);
    }

    @Override
    public List<Bus> retrieveAllBuses() {
        return bus_repository.findAll();
    }
    @Override
    public Bus retrieveBus(Long idBus) {return bus_repository.findById(idBus).get();}

    @Override
    public void remove_Bus(Long idBus) {
        bus_repository.deleteById(idBus);
    }

    @Override
    public Bus updateBus(Long idBus, Bus updatedBus) {
        Optional<Bus> optionalBus = bus_repository.findById(idBus);
        if (optionalBus.isPresent()) {
            Bus bus = optionalBus.get();
            bus.setTraject(updatedBus.getTraject());
            bus.setDriver(updatedBus.getDriver());
            bus.setCapacity(updatedBus.getCapacity());
            bus.setStatus(updatedBus.getStatus());
            return bus_repository.save(bus);
        } else {
            return null;
        }
    }

}
