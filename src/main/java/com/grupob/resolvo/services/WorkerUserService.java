package com.grupob.resolvo.services;

import com.grupob.resolvo.model.WorkerUser;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.repository.WorkerUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerUserService {

    private final WorkerUserRepository workerUserRepository;
    @Autowired
    public WorkerUserService(WorkerUserRepository workerUserRepository) {
        this.workerUserRepository = workerUserRepository;
    }

    public WorkerUser findWorkerWithCredentials(String email, String password) throws NoWorkerUserFoundException {
        final WorkerUser user = workerUserRepository.findWorkerWithCredentials(email, password);

        if (user == null) {
            throw new NoWorkerUserFoundException("User not found");
        }else{
            return user;
        }
    }
    public boolean findIfFirstTime(int id) {
        final boolean

    }
    public String updatePassword(int id, String password) {

    }
    public void changeFirstTime(String username) {

    }
}
