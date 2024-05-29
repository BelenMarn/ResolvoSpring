package com.grupob.resolvo.repository.marcos;

import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.marcos.InternUser;

public interface InternUserRepository {
    boolean findIfFirstTime(String email);
    void changeFirstTime(String email);
    InternUser findInternUser(String email, String password) throws NoWorkerUserFoundException;
    void updateMaterial(String email, String password);
}
