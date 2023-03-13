package com.example.pidev1.Service;


import com.example.pidev1.Entity.Request;
import com.example.pidev1.Repository.IRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RequestServiceImp implements IRequestServices {
    @Autowired
    com.example.pidev1.Repository.IRequestRepository IRequestRepository;
    @Override
    public List<Request> retreiveAllRequest() {return IRequestRepository.findAll();}

     @Override
     public Request addRquest (Request r ) {return IRequestRepository.save(r);}


    @Override
    public Request updateRequest (Request r  ,Long idRequest) {return IRequestRepository.save(r);}
    @Override
    public void   removeRequest(Long idRequest) {IRequestRepository.deleteById(idRequest);}

}
