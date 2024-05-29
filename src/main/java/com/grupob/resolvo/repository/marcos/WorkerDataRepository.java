package com.grupob.resolvo.repository.marcos;

import com.grupob.resolvo.model.exception.EmptyWorkerList;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.marcos.WorkerData;

import java.util.List;

public interface WorkerDataRepository {
    WorkerData findWorkerDataByEmail(String email) throws NoWorkerUserFoundException;
    List<WorkerData> findWorkerList() throws EmptyWorkerList;
    List<WorkerData> findWorkersDataByPostalCode(String code) throws EmptyWorkerList;
    List<WorkerData> findWorkersDataByPosition(String position) throws EmptyWorkerList;
    List<WorkerData> findWorkersDataBySpecialization(String spe) throws EmptyWorkerList;
    void addWorkerData(WorkerData workerData);
}
