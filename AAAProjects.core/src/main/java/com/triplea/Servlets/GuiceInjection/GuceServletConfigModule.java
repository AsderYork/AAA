package com.triplea.Servlets.GuiceInjection;

import com.google.inject.matcher.Matchers;
import com.google.inject.servlet.ServletModule;
import com.triplea.Servlets.*;
import com.triplea.Servlets.GuiceInjection.Log4JTypeListener;

/**
 * Created by Asder on 11.12.2016.
 */
public class GuceServletConfigModule  extends ServletModule {

    protected void configureServlets() {

        bindListener(Matchers.any(), new Log4JTypeListener());

        serve("/ajax/user").with(UserServlet.class);
        serve("/ajax/authority").with(AuthorityServlet.class);
        serve("/ajax/activity").with(ActivityServlet.class);

        serve("/echo/get").with(GetServlet.class);
        serve("/echo/post").with(PostServlet.class);
        serve("/echo/*").with(FilterServlet.class);

    }


}
