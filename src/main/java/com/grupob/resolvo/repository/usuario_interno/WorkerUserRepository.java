package com.grupob.resolvo.repository.usuario_interno;

import com.grupob.resolvo.model.usuario_interno.WorkerUser;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;

public interface WorkerUserRepository {
    WorkerUser findWorkerWithCredentials(String email, String password) throws NoWorkerUserFoundException;
    boolean findIfFirstTime(int id);
    boolean updatePassword(int id, String password);
    void changeFirstTime(int id);
    int findIdByEmail(String email) throws NoWorkerUserFoundException;
}
