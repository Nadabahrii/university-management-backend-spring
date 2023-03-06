package com.example.pidev1.controller;

import com.example.pidev1.entity.Bus;
import com.example.pidev1.entity.Comment;
import com.example.pidev1.service.Bus_Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bus")
public class BusController {

    Bus_Service bus_service;

    //http://localhost:8089/pidev/bus/add-Bus
    @PostMapping("/add-Bus")
    public Bus add_Bus(@RequestBody Bus b) {
        Bus bus = bus_service.add_Bus(b);
        return bus;
    }
    //http://localhost:8089/pidev/bus/retrieve-all-buses
    @GetMapping("/retrieve-all-buses")
    public List<Bus> getBuses() {
        List<Bus> busList =bus_service.retrieveAllBuses();
        return busList;
    }
    //http://localhost:8089/pidev/bus/retrieve-bus/{{bus-id}}
    @GetMapping("/retrieve-bus/{bus-id}")
    public Bus retrieveBus(@PathVariable("bus-id") Long BusId) {
        return bus_service.retrieveBus(BusId);
    }
    //http://localhost:8089/pidev/bus/buses/{{idBus}}
    @PutMapping("/buses/{idBus}")
    public Bus updateBus(@PathVariable Long idBus, @RequestBody Bus updatedBus) {
        return bus_service.updateBus(idBus, updatedBus);
    }
    //http://localhost:8089/pidev/bus/remove-bus/{{bus-id}}
    @DeleteMapping("/remove-bus/{bus-id}")
    public void remove_Bus(@PathVariable("bus-id") Long BusId) {
        bus_service.remove_Bus(BusId);
    }


}
