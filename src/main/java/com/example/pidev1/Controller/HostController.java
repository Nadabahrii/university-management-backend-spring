package com.example.pidev1.Controller;


import com.example.pidev1.Entity.Host;
import com.example.pidev1.Service.HostService;
import com.example.pidev1.Service.IHost;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class HostController {

   @Autowired
    HostService hostService;
    IHost iHost;
    @PostMapping("/add-Host")
    public Host addHost(@RequestBody Host h) {
        Host host = hostService.addHost(h);
        return host;
    }

    @GetMapping("/retrieve-all-hosts")
    public List<Host> gethost () {
        List<Host> listHost = hostService.retrieveAllhosts();
        return listHost;
    }

    @GetMapping("/retrieve-host/{host-id}")
    public Host retrieveHost(@PathVariable("host-id") Long hostId) {
        return hostService.retrieveHost(hostId);
    }

    @DeleteMapping("/remove-host/{host-id}")
    public void removehost(@PathVariable("host-id") Long hostId) {
        hostService.removeHost(hostId);
    }

    @PutMapping("/hosts/{idhost}")
    public Host updateHost(@PathVariable Long idhost, @RequestBody Host updatedHost) {
        return hostService.updateHost(idhost, updatedHost);
    }

    @GetMapping("/search-host")
    public ResponseEntity<List<Host>> searchHost(@RequestParam("query") String query){
        return ResponseEntity.ok(iHost.searchHost(query));
    }
}
