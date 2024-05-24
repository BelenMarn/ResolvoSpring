package com.grupob.resolvo.controller;

import com.grupob.resolvo.model.Technician;
import com.grupob.resolvo.model.WorkerUser;
import com.grupob.resolvo.model.exception.NoTechnicianFoundException;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.services.TechnicianService;
import com.grupob.resolvo.services.WorkerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/workeruser/")
public class WorkerUserRestController {

    private final WorkerUserService workerUserService;
    private final TechnicianService technicianService;
    @Autowired
    public WorkerUserRestController(WorkerUserService workerUserService, TechnicianService technicianService) {
        this.workerUserService = workerUserService;
        this.technicianService = technicianService;
    }

    @GetMapping("/{user}/{password}")
    public Technician getUser(@PathVariable("user") String email, @PathVariable("password") String password) throws NoWorkerUserFoundException, NoTechnicianFoundException {
        final WorkerUser user = workerUserService.findWorkerWithCredentials(email, password);
        Technician technician = null;

        if(user != null){
             technician = technicianService.findTechnician(user);
        }else{
            throw new NoWorkerUserFoundException("User not found");
        }

        if(technician != null){
            return technician;
        }else{
            throw new NoTechnicianFoundException("Technician not found");
        }
    }

    @GetMapping("/{id}")
    public boolean findIfFirstTime(@PathVariable("id")int id) {
        boolean firstTime = workerUserService.findIfFirstTime(id);

        if(firstTime){
            return true;
        }else{
            return false;
        }
    }
}
