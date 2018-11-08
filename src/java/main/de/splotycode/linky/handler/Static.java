package de.splotycode.linky.handler;

import me.david.webapi.handler.anotation.check.Handler;
import me.david.webapi.handler.anotation.check.Mapping;
import me.david.webapi.response.content.CachedStaticFileContent;
import me.david.webapi.response.content.ResponseContent;
import me.david.webapi.server.Request;

@Handler
public class Static {

    @Mapping("static/**")
    public ResponseContent staticSite(Request request) {
        String path = request.getPath().substring(1);
        return new CachedStaticFileContent("/home/david/Desktop/Programieren/java/Linky/web/static" + path.substring(path.indexOf('/')));
    }

    @Mapping("/")
    public ResponseContent homePage(Request request) {
        return new CachedStaticFileContent("/home/david/Desktop/Programieren/java/Linky/web/static/homepage.html");
    }

}
