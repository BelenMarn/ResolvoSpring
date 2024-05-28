package com.grupob.resolvo.repository.marcos;

import com.grupob.resolvo.model.exception.EmptyWorkerList;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.marcos.WorkerData;

import java.util.List;

public interface WorkerDataRepository {
    WorkerData findWorkerDataByEmail(String email) throws NoWorkerUserFoundException;
    List<WorkerData> findWorkerList() throws EmptyWorkerList;
}
