package de.splotycode.linky;

import de.splotycode.linky.handler.ErrorHandling;
import me.david.davidlib.application.Application;
import me.david.davidlib.startup.BootContext;
import me.david.webapi.WebApplicationType;
import me.david.webapi.config.WebConfig;
import me.david.webapi.response.content.file.CachedStaticFileContent;
import me.david.webapi.server.AbstractWebServer;
import me.david.webapi.server.netty.NettyWebServer;

public class Linky extends Application implements WebApplicationType {

    public void start(BootContext bootContext) {
        setWebServer(new NettyWebServer(this));
        getErrorHandler().installErrorFactory(new ErrorHandling());
        putConfig(WebConfig.NO_CONTENT_RESPONSE, new CachedStaticFileContent("/home/david/Desktop/Programieren/java/Linky/web/static/notfound.html"));
        getGlobalShutdownManager().addShutdownTask(() -> {
            double total = ((AbstractWebServer) getWebServer()).getTotalTime();
            double counter = ((AbstractWebServer) getWebServer()).getRequests();
            System.out.println(total + " " + counter + " " + (total / counter));
        });
        listen(2002);
    }

    public String getName() {
        return "Linky";
    }
}