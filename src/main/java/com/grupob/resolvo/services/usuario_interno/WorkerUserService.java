package com.grupob.resolvo.services.usuario_interno;

import com.grupob.resolvo.model.usuario_interno.WorkerUser;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.repository.usuario_interno.WorkerUserRepository;
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
        return workerUserRepository.findIfFirstTime(id);

    }

    public int findIdByEmail(String email) throws NoWorkerUserFoundException {
        final int id = workerUserRepository.findIdByEmail(email);
        return id;
    }

    public boolean updatePassword(int id, String password) {
        return workerUserRepository.updatePassword(id, password);
    }

    public void changeFirstTime(int id) {
        workerUserRepository.changeFirstTime(id);
    }


}
