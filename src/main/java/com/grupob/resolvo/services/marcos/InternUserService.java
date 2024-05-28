package com.grupob.resolvo.services.marcos;

import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.marcos.InternUser;
import com.grupob.resolvo.repository.marcos.InternUserRepository;
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

    public void changeFirstTime(String email, String password) {
        internUserRepository.changeFirstTime(email, password);
    }

    public InternUser findInternUser(String email, String password) throws NoWorkerUserFoundException {
        final InternUser user = internUserRepository.findInternUser(email, password);

        if (user == null) {
            throw new NoWorkerUserFoundException("User not found");
        }else{
            return user;
        }
    }

    public void updateMaterial(String email, String password) {
        internUserRepository.updateMaterial(email, password);
    }
}
