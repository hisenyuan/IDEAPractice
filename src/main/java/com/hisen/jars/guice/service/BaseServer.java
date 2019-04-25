package com.hisen.jars.guice.service;

import com.google.inject.Guice;

public abstract class BaseServer {
    public BaseServer() {
        Guice.createInjector().injectMembers(this);
    }
}

