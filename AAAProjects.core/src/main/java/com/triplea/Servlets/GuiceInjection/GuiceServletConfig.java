package com.triplea.Servlets.GuiceInjection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.triplea.Servlets.GuiceInjection.GuceServletConfigModule;

/**
 * Created by Asder on 11.12.2016.
 */
public class GuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {

        return Guice.createInjector(new GuceServletConfigModule());
    }
}