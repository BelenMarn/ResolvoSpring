package com.grupob.resolvo.repository;

import com.grupob.resolvo.model.WorkerUser;

public interface WorkerUserRepository {
    WorkerUser findWorkerWithCredentials(String email, String password);
}
