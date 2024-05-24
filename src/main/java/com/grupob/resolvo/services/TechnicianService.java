package com.grupob.resolvo.services;

import com.grupob.resolvo.model.Technician;
import com.grupob.resolvo.model.WorkerUser;
import com.grupob.resolvo.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnicianService {

    private final TechnicianRepository technicianRepository;
    @Autowired
    public TechnicianService(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    public Technician findTechnician(WorkerUser user) {
        final Technician technician = technicianRepository.findTechnician(user);

        return technician;
    }

}
