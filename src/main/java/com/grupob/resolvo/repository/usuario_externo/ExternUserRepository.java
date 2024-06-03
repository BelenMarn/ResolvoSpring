package com.grupob.resolvo.repository.usuario_externo;

import com.grupob.resolvo.model.exception.NoClientUserFoundException;
import com.grupob.resolvo.model.usuario_externo.ExternUser;

public interface ExternUserRepository {
    ExternUser findExternUserByEmail(String email) throws NoClientUserFoundException;
    void newUser(ExternUser externUser);
}
