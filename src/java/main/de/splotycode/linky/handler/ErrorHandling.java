package de.splotycode.linky.handler;

import me.david.davidlib.utils.ExceptionUtil;
import me.david.webapi.response.Response;
import me.david.webapi.response.content.FileResponseContent;
import me.david.webapi.response.error.ErrorFactory;

import java.io.FileNotFoundException;

public class ErrorHandling implements ErrorFactory {

    @Override
    public Response generatePage(Throwable throwable) {
        boolean notFound = ExceptionUtil.instanceOfCause(throwable, FileNotFoundException.class);
        FileResponseContent content = new FileResponseContent(
                notFound ?
                        "/home/david/Desktop/Programieren/java/Linky/web/static/notfound.html" :
                        "/home/david/Desktop/Programieren/java/Linky/web/static/internalerror.html"
        );
        if (!notFound) content.manipulate().variable("exception", ExceptionUtil.toString(throwable));

        Response response = new Response(content);
        response.setResponseCode(notFound ? 404 : 500);
        return response;
    }

    @Override
    public boolean valid(Throwable throwable) {
        return true;
    }


}
