package com.grupob.resolvo.services.usuario_externo;

import com.grupob.resolvo.model.exception.NoClientUserFoundException;
import com.grupob.resolvo.model.usuario_externo.ExternUser;
import com.grupob.resolvo.repository.usuario_externo.ExternUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExternUserService {

    private final ExternUserRepository externUserRepository;
    @Autowired
    public ExternUserService(ExternUserRepository externUserRepository) {
        this.externUserRepository = externUserRepository;
    }


    public ExternUser findExternUserByEmail(String email) throws NoClientUserFoundException {
        final ExternUser user = externUserRepository.findExternUserByEmail(email);

        if (user == null) {
            throw new NoClientUserFoundException("Client not found");
        } else {
            return user;
        }
    }

    public void newUser(ExternUser externUser) {
        externUserRepository.newUser(externUser);
    }
}
