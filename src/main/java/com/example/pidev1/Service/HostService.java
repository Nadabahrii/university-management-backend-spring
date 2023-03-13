package com.example.pidev1.Service;


import com.example.pidev1.Entity.Event;
import com.example.pidev1.Entity.Host;
import com.example.pidev1.Repository.HostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HostService implements IHost {

    @Autowired
    HostRepository hostRepository;

    @Override
    public List<Host> retrieveAllhosts() {
        return hostRepository.findAll();
    }

    @Override
    public Host addHost(Host h) {
        return hostRepository.save(h);
    }

        @Override
        public Host retrieveHost(Long idH) {
            return hostRepository.findById(idH).get();
        }

    @Override
    public void removeHost(Long idH) {
        hostRepository.deleteById(idH);
    }

    @Override
    public Host updateHost(Long idH, Host updatedHost) {
        Optional<Host> optionalHost = hostRepository.findById(idH);
        if (optionalHost.isPresent()) {
            Host host = optionalHost.get();
            host.setName(updatedHost.getName());
            host.setNBEtage(updatedHost.getNBEtage());
            host.setNBChambre(updatedHost.getNBChambre());
            return hostRepository.save(host);
        } else {
            return null;
        }
    }

    @Override
    public List<Host> searchHost(String query) {
        List<Host> hosts = hostRepository.searchHost(query);
        return hosts;
    }
}
