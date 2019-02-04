package de.splotycode.linky.handler;

import io.github.splotycode.mosaik.webapi.handler.anotation.check.Handler;
import io.github.splotycode.mosaik.webapi.handler.anotation.check.Mapping;
import io.github.splotycode.mosaik.webapi.request.Request;
import io.github.splotycode.mosaik.webapi.response.content.ResponseContent;
import io.github.splotycode.mosaik.webapi.response.content.file.CachedStaticFileContent;

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
