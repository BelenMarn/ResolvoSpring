package com.grupob.resolvo.repository.usuario_interno;

import com.grupob.resolvo.model.exception.NoWorkerUserFoundException;
import com.grupob.resolvo.model.usuario_interno.InternUser;

public interface InternUserRepository {
    boolean findIfFirstTime(String email);
    void changeFirstTime(String email);
    InternUser findInternUser(String email, String password) throws NoWorkerUserFoundException;
    void updateMaterial(String email, String password);
    InternUser findInternUserByEmail(String email) throws NoWorkerUserFoundException;
}
