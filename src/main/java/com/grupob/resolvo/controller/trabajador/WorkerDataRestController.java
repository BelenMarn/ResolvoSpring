package com.grupob.resolvo.controller.trabajador;

import com.grupob.resolvo.model.exception.EmptyWorkerList;
import com.grupob.resolvo.model.exception.NoTechnicianFoundException;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.incidencia.Incidence;
import com.grupob.resolvo.model.trabajador.WorkerData;
import com.grupob.resolvo.services.trabajador.WorkerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//MARCOS y SERGIO
@RestController
@RequestMapping("/api/workerData")
public class WorkerDataRestController {

    private WorkerDataService workerDataService;

    @Autowired
    public WorkerDataRestController(WorkerDataService workerDataService) {
        this.workerDataService = workerDataService;
    }

    //MARCOS:
    @GetMapping("/find")
    public WorkerData findWorkerDataByEmail(@RequestParam("email") String email) throws NoWorkerUserFoundException {
        final WorkerData worker = workerDataService.findWorkerDataByEmail(email);

        if(worker != null){
            return worker;

        }else{
            throw new NoWorkerUserFoundException("User not found");
        }
    }

    //MARCOS Y SERGIO:
    @GetMapping("/findAll")
    public List<WorkerData> findWorkerList() throws EmptyWorkerList {
        final List<WorkerData> workers = workerDataService.findWorkerList();

        if (workers.isEmpty()) {
            throw new EmptyWorkerList("Empty worker list");
        }else{
            return workers;
        }
    }

    //SERGIO:
    @GetMapping("/findByCod")
    public List<WorkerData> findWorkersDataByPostalCode(@RequestParam("code") String code) throws EmptyWorkerList {
        final List<WorkerData> workers = workerDataService.findWorkersDataByPostalCode(code);

        if (workers.isEmpty()) {
            throw new EmptyWorkerList("Empty worker list");
        }else{
            return workers;
        }
    }

    //SERGIO:
    @GetMapping("/findByPosition")
    public List<WorkerData> findWorkersDataByPosition(@RequestParam("position") String position) throws EmptyWorkerList {
        final List<WorkerData> workers = workerDataService.findWorkersDataByPosition(position);

        if (workers.isEmpty()) {
            throw new EmptyWorkerList("Empty worker list");
        }else{
            return workers;
        }
    }

    //SERGIO:
    @GetMapping("/findBySpecialization")
    public List<WorkerData> findWorkersDataBySpecialization(@RequestParam("spe") String spe) throws EmptyWorkerList {
        final List<WorkerData> workers = workerDataService.findWorkersDataBySpecialization(spe);

        if (workers.isEmpty()) {
            throw new EmptyWorkerList("Empty worker list");
        }else{
            return workers;
        }
    }

    //SERGIO:
    @PostMapping("/add")
    public void addWorkerData(@RequestBody WorkerData workerData) {
        System.out.println(workerData.toString());
        workerDataService.addWorkerData(workerData);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteWorkerData(@PathVariable("id") String id) {
        return workerDataService.deleteWorkerData(id);
    }

    @PutMapping("/update/{id}")
    public boolean updateWorkerData(@PathVariable("id") String id, @RequestBody WorkerData workerData) {
        return workerDataService.updateWorkerData(id, workerData);
    }

}


