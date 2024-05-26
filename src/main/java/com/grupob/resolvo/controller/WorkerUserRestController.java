package com.grupob.resolvo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupob.resolvo.model.Technician;
import com.grupob.resolvo.model.WorkerUser;
import com.grupob.resolvo.model.exception.NoTechnicianFoundException;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.services.TechnicianService;
import com.grupob.resolvo.services.WorkerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/workeruser/")
public class WorkerUserRestController {

    private final WorkerUserService workerUserService;

    @Autowired
    public WorkerUserRestController(WorkerUserService workerUserService) {
        this.workerUserService = workerUserService;
    }

    @GetMapping
    public WorkerUser getUser(@RequestParam("user") String email, @RequestParam("material") String password) throws NoWorkerUserFoundException, NoTechnicianFoundException {
        final WorkerUser user = workerUserService.findWorkerWithCredentials(email, password);

        if(user != null){
            return user;

        }else{
            throw new NoWorkerUserFoundException("User not found");
        }
    }

    @GetMapping("/{id}")
    public boolean findIfFirstTime(@PathVariable("id")int id) {
        return workerUserService.findIfFirstTime(id);
    }

    @PutMapping("/updatePassword/{id}")
    public boolean updatePassword(@PathVariable("id") int id, @RequestBody String password) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> passwordMap = mapper.readValue(password, Map.class);
        String actualPassword = passwordMap.get("material");

        return workerUserService.updatePassword(id, actualPassword);
    }

    @PutMapping("/firstTime/{id}")
    public void changeFirstTime(@PathVariable("id")int id) {
        workerUserService.changeFirstTime(id);
    }
}
