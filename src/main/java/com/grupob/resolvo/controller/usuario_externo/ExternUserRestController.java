package com.grupob.resolvo.controller.usuario_externo;

import com.grupob.resolvo.model.exception.NoClientUserFoundException;
import com.grupob.resolvo.model.usuario_externo.ExternUser;
import com.grupob.resolvo.services.usuario_externo.ExternUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/externUser")
public class ExternUserRestController {

    private final ExternUserService externUserService;
    @Autowired
    public ExternUserRestController(ExternUserService externUserService) {
        this.externUserService = externUserService;
    }

    //SERGIO:
    @GetMapping("/findByEmail")
    public ExternUser findExternUserByEmail(@RequestParam("email") String email) throws NoClientUserFoundException {
        final ExternUser user = externUserService.findExternUserByEmail(email);

        if (user == null) {
            throw new NoClientUserFoundException("Client not found");
        } else {
            return user;
        }
    }

    @PostMapping("/add")
    public void newUser(@RequestBody ExternUser externUser){
        externUserService.newUser(externUser);
    }
}
