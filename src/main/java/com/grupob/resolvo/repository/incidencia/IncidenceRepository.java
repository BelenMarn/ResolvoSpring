package com.grupob.resolvo.repository.incidencia;

import com.grupob.resolvo.model.incidencia.Incidence;
import com.grupob.resolvo.model.exception.EmptyIncidenceList;
import com.grupob.resolvo.model.exception.NoIncidenceFoundException;

import java.util.List;

public interface IncidenceRepository {
    List<Incidence> findIncidencesOfTechnician(int id) throws EmptyIncidenceList;
    Incidence findIncidenceById(int id) throws NoIncidenceFoundException;
    List<Incidence> findAllIncidences() throws EmptyIncidenceList;
    boolean updateIncidenceFromPhone(int id, Incidence incidence);
    boolean updateStatus(int id, String status);
    boolean updateWorker(int idIncidence, String idWorker);
}
