package de.splotycode.linky;

import de.splotycode.linky.handler.ErrorHandling;
import io.github.splotycode.mosaik.runtime.application.Application;
import io.github.splotycode.mosaik.runtime.startup.BootContext;
import io.github.splotycode.mosaik.webapi.WebApplicationType;
import io.github.splotycode.mosaik.webapi.config.WebConfig;
import io.github.splotycode.mosaik.webapi.response.content.file.CachedStaticFileContent;
import io.github.splotycode.mosaik.webapi.server.AbstractWebServer;
import io.github.splotycode.mosaik.webapi.server.netty.NettyWebServer;

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
