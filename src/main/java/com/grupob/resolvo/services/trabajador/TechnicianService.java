package com.grupob.resolvo.services.trabajador;

import com.grupob.resolvo.model.trabajador.Technician;
import com.grupob.resolvo.model.exception.NoTechnicianFoundException;
import com.grupob.resolvo.repository.trabajador.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnicianService {

    private final TechnicianRepository technicianRepository;
    @Autowired
    public TechnicianService(TechnicianRepository technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    public Technician findTechnician(int id) throws NoTechnicianFoundException {
        final Technician technician = technicianRepository.findTechnician(id);

        if(technician != null){
            return technician;
        }else{
            throw new NoTechnicianFoundException("Technician not found");
        }
    }

    public boolean getIfTechnician(int id) throws NoTechnicianFoundException {
        return technicianRepository.getIfTechnician(id);
    }
}
