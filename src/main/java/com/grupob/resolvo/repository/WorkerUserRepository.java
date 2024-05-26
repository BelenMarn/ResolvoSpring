package com.grupob.resolvo.repository;

import com.grupob.resolvo.model.WorkerUser;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;

public interface WorkerUserRepository {
    WorkerUser findWorkerWithCredentials(String email, String password) throws NoWorkerUserFoundException;
    boolean findIfFirstTime(int id);
    boolean updatePassword(int id, String password);
    void changeFirstTime(int id);
}
