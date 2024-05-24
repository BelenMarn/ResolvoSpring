package com.grupob.resolvo.repository;

import com.grupob.resolvo.model.Technician;
import com.grupob.resolvo.model.WorkerUser;

public interface TechnicianRepository {
    boolean findUsername(String username, String password);
    boolean findIfFirstTime(String username, String password);
    int findIdWorker(String username, String password);
    Technician findTechnician(WorkerUser user);
    String updatePassword(String username, String password);
    void changeFirstTime(String username);
}
