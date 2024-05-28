package com.grupob.resolvo.controller;

import com.grupob.resolvo.model.Incidence;
import com.grupob.resolvo.model.exception.EmptyIncidenceList;
import com.grupob.resolvo.model.exception.NoIncidenceFoundException;
import com.grupob.resolvo.services.IncidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/incidence/")
public class IncidenceRestController {

    private final IncidenceService incidenceService;
    @Autowired
    public IncidenceRestController(IncidenceService incidenceService) {
        this.incidenceService = incidenceService;
    }

    //BELEN
    @GetMapping("/technician/{id}")
    public List<Incidence> findIncidencesOfTechnician(@PathVariable("id")int id) throws EmptyIncidenceList {
        final List<Incidence> incidences = incidenceService.findIncidencesOfTechnician(id);
        return incidences;
    }

    //MARCOS
    @GetMapping("/findIncidence/{id}")
    public Incidence findIncidenceById(@PathVariable("id")int id) throws NoIncidenceFoundException {
        final Incidence incidence = incidenceService.findIncidenceById(id);
        return incidence;
    }
}
