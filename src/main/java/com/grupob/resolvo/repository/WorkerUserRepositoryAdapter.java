package com.grupob.resolvo.repository;

import com.grupob.resolvo.model.WorkerUser;
import org.springframework.stereotype.Repository;

@Repository
public class WorkerUserRepositoryAdapter implements WorkerUserRepository {
    @Override
    public WorkerUser findWorkerWithCredentials(String email, String password) {
        return null;
    }
}
