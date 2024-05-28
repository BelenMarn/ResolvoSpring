package com.grupob.resolvo.controller.marcos;

import com.grupob.resolvo.model.exception.EmptyWorkerList;
import com.grupob.resolvo.model.exception.NoTechnicianFoundException;
import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.marcos.WorkerData;
import com.grupob.resolvo.services.marcos.WorkerDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workerData")
public class WorkerDataRestController {

    private WorkerDataService workerDataService;

    @GetMapping
    public WorkerData findWorkerData(@RequestParam("email") String email) throws NoWorkerUserFoundException, NoTechnicianFoundException {
        final WorkerData worker = workerDataService.findWorkerDataByEmail(email);

        if(worker != null){
            return worker;

        }else{
            throw new NoWorkerUserFoundException("User not found");
        }
    }

    @GetMapping()
    public List<WorkerData> findWorkerList() throws EmptyWorkerList {
        final List<WorkerData> workers = workerDataService.findWorkerList();

        if (workers.isEmpty()) {
            throw new EmptyWorkerList("Empty worker list");
        }else{
            return workers;
        }
    }

}


