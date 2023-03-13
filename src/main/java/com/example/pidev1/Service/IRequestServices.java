package com.example.pidev1.Service;

import com.example.pidev1.Entity.Request;


import java.util.List;

public interface IRequestServices {
    public List<Request> retreiveAllRequest();

    public Request addRquest (Request r );

   public Request updateRequest (Request r ,Long idRequest);
    public void  removeRequest(Long idRequest );
}
