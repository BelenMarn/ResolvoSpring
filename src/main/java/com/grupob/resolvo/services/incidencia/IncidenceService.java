package com.grupob.resolvo.services.incidencia;

import com.grupob.resolvo.model.incidencia.Incidence;
import com.grupob.resolvo.model.exception.EmptyIncidenceList;
import com.grupob.resolvo.model.exception.NoIncidenceFoundException;
import com.grupob.resolvo.repository.incidencia.IncidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class IncidenceService {

    private final IncidenceRepository incidenceRepository;
    @Autowired
    public IncidenceService(IncidenceRepository incidenceRepository) {
        this.incidenceRepository = incidenceRepository;
    }

    //BELEN
    public List<Incidence> findIncidencesOfTechnician(int id) throws EmptyIncidenceList {
        final List<Incidence> incidences = new ArrayList<Incidence>();
        final Iterator<Incidence> incidenceIterator = incidenceRepository.findIncidencesOfTechnician(id).iterator();
        while (incidenceIterator.hasNext()) {
            incidences.add(incidenceIterator.next());
        }

        if(!incidences.isEmpty()){
            return incidences;
        }else{
            throw new EmptyIncidenceList("No incidences found");
        }
    }

    //MARCOS:
    public Incidence findIncidenceById(int id) throws NoIncidenceFoundException {
        final Incidence incidence = incidenceRepository.findIncidenceById(id);

        if(incidence != null){
            return incidence;
        }else{
            throw new NoIncidenceFoundException("No incidence found");
        }
    }

    //MARCOS:
    public List<Incidence> findAllIncidences() throws EmptyIncidenceList {
        final List<Incidence> incidences = new ArrayList<Incidence>();
        final Iterator<Incidence> incidenceIterator = incidenceRepository.findAllIncidences().iterator();
        while (incidenceIterator.hasNext()) {
            incidences.add(incidenceIterator.next());
        }

        if(!incidences.isEmpty()){
            return incidences;
        }else{
            throw new EmptyIncidenceList("Empty incidence list");
        }
    }

    public boolean updateIncidenceFromPhone(int id, Incidence incidence) {
        incidence.setClose_date(generateCloseDate());
        return incidenceRepository.updateIncidenceFromPhone(id, incidence);
    }

    public boolean updateStatus(int id, String status) {
        return incidenceRepository.updateStatus(id, status);
    }

    public boolean updateSign(int id, String sign) {return incidenceRepository.updateSign(id, sign);}

    public boolean updateMedia(int id, String media) {return incidenceRepository.updateMedia(id, media);}

    public boolean updateWorker(int idIncidence, String idWorker) {
        return incidenceRepository.updateWorker(idIncidence, idWorker);
    }

    private LocalDateTime generateCloseDate() {
        long currentDate = System.currentTimeMillis();
        Instant instant = Instant.ofEpochMilli(currentDate);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

}
