package com.grupob.resolvo.controller.incidencia;

import com.grupob.resolvo.model.incidencia.Incidence;
import com.grupob.resolvo.model.exception.EmptyIncidenceList;
import com.grupob.resolvo.model.exception.NoIncidenceFoundException;
import com.grupob.resolvo.services.IncidenceService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidence")
public class IncidenceRestController {

    private final IncidenceService incidenceService;
    @Autowired
    public IncidenceRestController(IncidenceService incidenceService) {
        this.incidenceService = incidenceService;
    }

    // ----------------------- BELÉN ------------------------
    @GetMapping("/technician/{id}")
    public List<Incidence> findIncidencesOfTechnician(@PathVariable("id")int id) throws EmptyIncidenceList {
        final List<Incidence> incidences = incidenceService.findIncidencesOfTechnician(id);

        if (incidences.isEmpty()) {
            throw new EmptyIncidenceList("Empty incidence list");
        }else{
            return incidences;
        }
    }

    @PutMapping("/updateFromPhone/{id}")
    public boolean updateIncidenceFromPhone(@PathVariable("id")int id, @RequestBody Incidence incidence) {
        return incidenceService.updateIncidenceFromPhone(id, incidence);
    }

    //Cambiar el estado de la incidencia
    @PutMapping("/updateStatus/{id}")
    public boolean updateStatus(@PathVariable("id")int id, @PathParam("status") String status) {
        return incidenceService.updateStatus(id, status);
    }

    // ----------------------- MARCOS ------------------------
    @GetMapping("/findIncidence/{id}")
    public Incidence findIncidenceById(@PathVariable("id")int id) throws NoIncidenceFoundException {
        return incidenceService.findIncidenceById(id);
    }

    @GetMapping("/findAll")
    public List<Incidence> findAllIncidences() throws EmptyIncidenceList {
        final List<Incidence> incidences = incidenceService.findAllIncidences();

        if (incidences.isEmpty()) {
            throw new EmptyIncidenceList("Empty incidence list");
        }else{
            return incidences;
        }

    }
}
