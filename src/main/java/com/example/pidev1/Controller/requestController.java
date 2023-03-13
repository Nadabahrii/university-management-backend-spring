package com.example.pidev1.Controller;

import com.example.pidev1.Entity.Request;
import com.example.pidev1.Service.RequestServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/request")
public class requestController {

    @Autowired
    RequestServiceImp iRequestServices;

    @GetMapping("/getRequest")
    List<Request> getRequest() {
        return iRequestServices.retreiveAllRequest();
    }

    @PostMapping("/addRequest")
    Request addRequest(@RequestBody Request r) {return iRequestServices.addRquest(r);}


    @PutMapping("/updateRequest/{id}")
    Request updateRequest(@RequestBody Request r , @PathVariable("id") Long idRequest) {
        return iRequestServices.updateRequest(r, idRequest);
    }

    @DeleteMapping("/removeRdv/{id}")
    void removeRequest (@PathVariable("id") Long idRequest) {
        iRequestServices.removeRequest(idRequest);

    }
}
