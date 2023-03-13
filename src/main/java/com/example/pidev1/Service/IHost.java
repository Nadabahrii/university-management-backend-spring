package com.example.pidev1.Service;

import com.example.pidev1.Entity.Event;
import com.example.pidev1.Entity.Host;

import java.util.List;

public interface IHost {

    List<Host> retrieveAllhosts();

    Host addHost (Host h);

    Host retrieveHost(Long idH);

    void removeHost(Long idH);

    Host updateHost(Long idH, Host updatedHost);

    List<Host> searchHost(String query);


}
