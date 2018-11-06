package de.splotycode.linky.handler;

import me.david.webapi.handler.anotation.check.Handler;
import me.david.webapi.handler.anotation.check.Mapping;
import me.david.webapi.response.content.CachedStatisFileContent;
import me.david.webapi.response.content.ResponseContent;
import me.david.webapi.server.Request;

@Handler
public class Static {

    @Mapping("static/**")
    public ResponseContent staticSite(Request request) {
        return new CachedStatisFileContent("/home/david/Desktop/Programieren/java/Linky/web/static/" + request.getPath().substring(request.getPath().indexOf('/')));
    }

    @Mapping("/")
    public ResponseContent homePage(Request request) {
        return new CachedStatisFileContent("/home/david/Desktop/Programieren/java/Linky/web/static/homepage.html");
    }

}
