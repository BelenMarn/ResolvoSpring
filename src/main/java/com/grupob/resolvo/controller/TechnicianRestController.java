package com.grupob.resolvo.controller;


import com.grupob.resolvo.model.Technician;
import com.grupob.resolvo.model.WorkerUser;
import com.grupob.resolvo.model.exception.NoTechnicianFoundException;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.services.TechnicianService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/technician")
public class TechnicianRestController {

    private final TechnicianService technicianService;

    public TechnicianRestController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @GetMapping("/{id}")
    public Technician getUser(@PathVariable("id") int id) throws NoTechnicianFoundException {
        final Technician technician = technicianService.findTechnician(id);

        if(technician != null){
            return technician;

        }else{
            throw new NoTechnicianFoundException("Technician not found");
        }
    }
}
