package de.splotycode.linky.handler;

import me.david.webapi.handler.anotation.check.Handler;
import me.david.webapi.handler.anotation.check.Mapping;
import me.david.webapi.handler.anotation.handle.RequiredGet;
import me.david.webapi.handler.anotation.handle.UrlParam;
import me.david.webapi.response.Response;
import me.david.webapi.response.content.ResponseContent;
import me.david.webapi.response.content.file.CachedStaticFileContent;
import me.david.webapi.response.content.file.FileResponseContent;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Handler
public class Backend {

    private HashMap<Long, String> links = new HashMap<>();
    private AtomicLong linkCounter = new AtomicLong();

    @Mapping("l/?id?")
    public ResponseContent redirect(@UrlParam("id") long linkId, Response response) {
        String link = links.get(linkId);
        if (link == null) {
            return new CachedStaticFileContent("/home/david/Desktop/Programieren/java/Linky/web/app/NoLink.html");
        }
        response.redirect(link, false);
        return null;
    }

    @Mapping("create/")
    public ResponseContent create(@RequiredGet("url") String url) {
        long id = linkCounter.incrementAndGet();
        links.put(id, url);
        FileResponseContent content = new FileResponseContent("/home/david/Desktop/Programieren/java/Linky/web/app/LinkCreated.html");
        content.manipulate().variable("id", id);
        return content;
    }

}
