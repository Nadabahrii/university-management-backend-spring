package com.example.pidev1.Service;

import com.example.pidev1.Entity.Employers;
import com.example.pidev1.Entity.Rdv;


import java.util.Date;
import java.util.List;

public interface IRdvServices {
    public List<Rdv> retreiveAllRdvs();
    public void addRdvbyStoRTe (Rdv rdv,Long id );
    public Rdv addRdv (Rdv rdv );
    public Rdv update (Rdv rdv  ,Long idRdv);
    public void  removeRdv(Long idRdv );

    String getDay(String date);

    List<String> getAvailableStartTimesForClasseInDay(String day , Class classe);

    List<String> getAvailableStartTimesForEmployerInDay(String day , Employers employer);

    List<String> getNext30Days(String startDate);

    String incrementTime(String time);
}
