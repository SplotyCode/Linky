package de.splotycode.linky;

import me.david.davidlib.application.Application;
import me.david.davidlib.application.BootContext;
import me.david.webapi.WebApplicationType;
import me.david.webapi.server.NettyWebServer;

public class Linky extends Application implements WebApplicationType {

    public void start(BootContext bootContext) {
        setWebServer(new NettyWebServer(this));
        listen(2002);
    }

    public String getName() {
        return "Linky";
    }
}
