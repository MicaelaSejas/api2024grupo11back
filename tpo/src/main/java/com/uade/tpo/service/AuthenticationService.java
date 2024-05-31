package com.uade.tpo.service;

import com.uade.tpo.controller.auth.AuthenticationRequest;
import com.uade.tpo.controller.auth.AuthenticationResponse;
import com.uade.tpo.controller.auth.RegisterRequest;

public interface AuthenticationService {

    public AuthenticationResponse register(RegisterRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
