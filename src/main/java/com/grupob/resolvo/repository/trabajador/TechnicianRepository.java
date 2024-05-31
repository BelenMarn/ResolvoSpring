package com.grupob.resolvo.repository.trabajador;

import com.grupob.resolvo.model.trabajador.Technician;
import com.grupob.resolvo.model.exception.NoTechnicianFoundException;

public interface TechnicianRepository {
    Technician findTechnician(int id) throws NoTechnicianFoundException;
    boolean getIfTechnician(int id) throws NoTechnicianFoundException;
}
