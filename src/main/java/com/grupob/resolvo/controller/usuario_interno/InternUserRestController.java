package com.grupob.resolvo.controller.usuario_interno;

import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.usuario_interno.InternUser;
import com.grupob.resolvo.services.usuario_interno.InternUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//MARCOS:
@RestController
@RequestMapping("/api/internUser")
public class InternUserRestController {

    private final InternUserService userModelService;

    @Autowired
    public InternUserRestController(InternUserService userModelService) {
        this.userModelService = userModelService;
    }

    @GetMapping("/{email}")
    public boolean findIfFirstTime(@PathVariable("email")String email) {
        return userModelService.findIfFirstTime(email);
    }

    @PutMapping("/changeFirstTime/{email}")
    public void changeFirstTime(@PathVariable("email") String email){
        userModelService.changeFirstTime(email);

    }

    @GetMapping("/find")
    public InternUser findInternUser(@RequestParam("email") String email, @RequestParam("material") String password) throws NoWorkerUserFoundException {
        final InternUser user = userModelService.findInternUser(email, password);

        if (user == null) {
            throw new NoWorkerUserFoundException("User not found");
        } else {
            return user;
        }
    }

    //SERGIO:
    @GetMapping("/findByEmail")
    public InternUser findInternUserByEmail(@RequestParam("email") String email) throws NoWorkerUserFoundException {
        final InternUser user = userModelService.findInternUserByEmail(email);

        if (user == null) {
            throw new NoWorkerUserFoundException("User not found");
        } else {
            return user;
        }
    }

    @PutMapping("/updatMaterial/{email}")
    public void updateMaterial(@PathVariable("email") String email, @RequestParam("material") String password){
        userModelService.updateMaterial(email, password);

    }
}
