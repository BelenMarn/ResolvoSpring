package com.grupob.resolvo.controller.belen;

import com.grupob.resolvo.model.belen.WorkerUser;
import com.grupob.resolvo.model.exception.NoTechnicianFoundException;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.services.belen.WorkerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//BELEN
@RestController
@RequestMapping("/api/workeruser")
public class WorkerUserRestController {

    private final WorkerUserService workerUserService;

    @Autowired
    public WorkerUserRestController(WorkerUserService workerUserService) {
        this.workerUserService = workerUserService;
    }

    @GetMapping
    public WorkerUser getUser(@RequestParam("user") String email, @RequestParam("material") String password) throws NoWorkerUserFoundException {
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

    @GetMapping("/getUserId/{email}")
    public int findIdByEmail(@PathVariable("email")String email) throws NoWorkerUserFoundException {
        final int id = workerUserService.findIdByEmail(email);

        return id;
    }

    @PutMapping("/updatePassword/{id}")
    public boolean updatePassword(@PathVariable("id") int id, @RequestParam("material") String password) {
        return workerUserService.updatePassword(id, password);
    }

    @PutMapping("/firstTime/{id}")
    public void changeFirstTime(@PathVariable("id")int id) {
        workerUserService.changeFirstTime(id);
    }
}
