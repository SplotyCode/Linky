package de.splotycode.linky;

import de.splotycode.linky.handler.ErrorHandling;
import me.david.davidlib.application.Application;
import me.david.davidlib.application.BootContext;
import me.david.webapi.WebApplicationType;
import me.david.webapi.config.WebConfig;
import me.david.webapi.response.content.CachedStaticFileContent;
import me.david.webapi.server.NettyWebServer;

public class Linky extends Application implements WebApplicationType {

    public void start(BootContext bootContext) {
        setWebServer(new NettyWebServer(this));
        installErrorFactory(new ErrorHandling());
        putConfig(WebConfig.NO_CONTENT_RESPONSE, new CachedStaticFileContent("/home/david/Desktop/Programieren/java/Linky/web/static/notfound.html"));
        listen(2002);
    }

    public String getName() {
        return "Linky";
    }
}
