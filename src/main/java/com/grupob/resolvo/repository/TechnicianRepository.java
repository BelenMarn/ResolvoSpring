package com.grupob.resolvo.repository;

import com.grupob.resolvo.model.Technician;
import com.grupob.resolvo.model.WorkerUser;

public interface TechnicianRepository {
    Technician findTechnician(int id);
}
