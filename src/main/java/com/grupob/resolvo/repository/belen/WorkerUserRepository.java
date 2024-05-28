package com.grupob.resolvo.repository.belen;

import com.grupob.resolvo.model.belen.WorkerUser;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;

public interface WorkerUserRepository {
    WorkerUser findWorkerWithCredentials(String email, String password) throws NoWorkerUserFoundException;
    boolean findIfFirstTime(int id);
    boolean updatePassword(int id, String password);
    void changeFirstTime(int id);
    int findIdByEmail(String email) throws NoWorkerUserFoundException;
}