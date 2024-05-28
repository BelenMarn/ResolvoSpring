package com.grupob.resolvo.services;

import com.grupob.resolvo.model.Incidence;
import com.grupob.resolvo.model.exception.EmptyIncidenceList;
import com.grupob.resolvo.model.exception.NoIncidenceFoundException;
import com.grupob.resolvo.repository.IncidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new EmptyIncidenceList("No incidences found");
        }
    }
}
