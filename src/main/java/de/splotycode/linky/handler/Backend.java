package de.splotycode.linky.handler;

import io.github.splotycode.mosaik.webapi.handler.anotation.check.Handler;
import io.github.splotycode.mosaik.webapi.handler.anotation.check.Mapping;
import io.github.splotycode.mosaik.webapi.handler.anotation.handle.RequiredGet;
import io.github.splotycode.mosaik.webapi.handler.anotation.handle.UrlParam;
import io.github.splotycode.mosaik.webapi.response.Response;
import io.github.splotycode.mosaik.webapi.response.content.ResponseContent;
import io.github.splotycode.mosaik.webapi.response.content.file.CachedStaticFileContent;
import io.github.splotycode.mosaik.webapi.response.content.file.FileResponseContent;

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
