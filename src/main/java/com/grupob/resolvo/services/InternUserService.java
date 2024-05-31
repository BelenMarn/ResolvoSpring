package com.grupob.resolvo.services;

import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.usuario_interno.InternUser;
import com.grupob.resolvo.repository.usuario_interno.InternUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InternUserService {

    private final InternUserRepository internUserRepository;
    @Autowired
    public InternUserService(InternUserRepository userModelRepository) {
        this.internUserRepository = userModelRepository;
    }

    public boolean findIfFirstTime(String email) {
        return internUserRepository.findIfFirstTime(email);
    }

    public void changeFirstTime(String email) {
        internUserRepository.changeFirstTime(email);
    }

    public InternUser findInternUser(String email, String password) throws NoWorkerUserFoundException {
        final InternUser user = internUserRepository.findInternUser(email, password);

        if (user == null) {
            throw new NoWorkerUserFoundException("User not found");
        } else {
            return user;
        }
    }

    public void updateMaterial(String email, String password) {
        internUserRepository.updateMaterial(email, password);
    }
}
