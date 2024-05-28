package com.grupob.resolvo.repository.belen;

import com.grupob.resolvo.model.belen.Technician;
import com.grupob.resolvo.model.exception.NoTechnicianFoundException;

public interface TechnicianRepository {
    Technician findTechnician(int id) throws NoTechnicianFoundException;
}
