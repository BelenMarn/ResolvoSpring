package com.grupob.resolvo.repository;

import com.grupob.resolvo.model.Incidence;
import com.grupob.resolvo.model.exception.EmptyIncidenceList;

import java.util.List;

public interface IncidenceRepository {
    List<Incidence> findIncidencesOfTechnician(int id) throws EmptyIncidenceList;
    Incidence findIncidenceById(int id);
}
