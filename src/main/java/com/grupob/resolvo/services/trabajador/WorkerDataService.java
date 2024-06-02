package com.grupob.resolvo.services.trabajador;

import com.grupob.resolvo.model.exception.EmptyWorkerList;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.trabajador.WorkerData;
import com.grupob.resolvo.repository.trabajador.WorkerDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class WorkerDataService {

    private final WorkerDataRepository workerDataRepository;
    @Autowired
    public WorkerDataService(WorkerDataRepository workerDataRepository) {
        this.workerDataRepository = workerDataRepository;
    }

    public WorkerData findWorkerDataByEmail(String email) throws NoWorkerUserFoundException {
        final WorkerData worker = workerDataRepository.findWorkerDataByEmail(email);

        if (worker == null) {
            throw new NoWorkerUserFoundException("User not found");
        }else{
            return worker;
        }
    }

    public List<WorkerData> findWorkerList() throws EmptyWorkerList {
        final List<WorkerData> workers = new ArrayList<WorkerData>();
        final Iterator<WorkerData> workerIterator = workerDataRepository.findWorkerList().iterator();
        while (workerIterator.hasNext()) {
            workers.add(workerIterator.next());
        }

        if(!workers.isEmpty()){
            return workers;
        }else{
            throw new EmptyWorkerList("No workers found");
        }
    }

    public List<WorkerData> findWorkersDataByPostalCode(String code) throws EmptyWorkerList {
        final List<WorkerData> workers = new ArrayList<WorkerData>();
        final Iterator<WorkerData> workerIterator = workerDataRepository.findWorkersDataByPostalCode(code).iterator();
        while (workerIterator.hasNext()) {
            workers.add(workerIterator.next());
        }

        if(!workers.isEmpty()){
            return workers;
        }else{
            throw new EmptyWorkerList("No workers found");
        }
    }

    public List<WorkerData> findWorkersDataByPosition(String position) throws EmptyWorkerList {
        final List<WorkerData> workers = new ArrayList<WorkerData>();
        final Iterator<WorkerData> workerIterator = workerDataRepository.findWorkersDataByPosition(position).iterator();
        while (workerIterator.hasNext()) {
            workers.add(workerIterator.next());
        }

        if(!workers.isEmpty()){
            return workers;
        }else{
            throw new EmptyWorkerList("No workers found");
        }
    }

    public List<WorkerData> findWorkersDataBySpecialization(String spe) throws EmptyWorkerList {
        final List<WorkerData> workers = new ArrayList<WorkerData>();
        final Iterator<WorkerData> workerIterator = workerDataRepository.findWorkersDataBySpecialization(spe).iterator();
        while (workerIterator.hasNext()) {
            workers.add(workerIterator.next());
        }

        if(!workers.isEmpty()){
            return workers;
        }else{
            throw new EmptyWorkerList("No workers found");
        }
    }

    public void addWorkerData(WorkerData workerData) {
        workerDataRepository.addWorkerData(workerData);
    }
}
