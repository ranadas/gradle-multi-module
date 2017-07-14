package com.rdas.component;

import com.github.tomaslanger.chalk.Chalk;
import com.jcabi.http.Request;
import com.jcabi.http.request.JdkRequest;
import com.jcabi.http.response.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

//https://dzone.com/articles/jcabi-http-fluent-java-http
//https://github.com/jcabi/jcabi-http
//https://github.com/bigbass1997/EVEGrid/blob/c4a72be7af3e4b1592cdd9b20641c3e82eb3fc7a/core/src/com/bigbass1997/evegrid/market/Market.java
//https://github.com/search?l=Java&p=2&q=JdkRequest&type=Code&utf8=%E2%9C%93
//https://github.com/codingricky/pact-spring-boot/blob/28eae04f0e156cbec18d79ac7ea9d2a0a42b8e82/client/src/main/java/client/Client.java
//https://github.com/ericdahl/wink-content-type-bug/blob/bde338c9635770b36f5ab15b005c8e7832c2c2a8/src/test/java/ContentTypeIT.java
//https://github.com/markoivkovic/public/blob/4f6222376bc62867023ff0b0c77b12e240e778cf/Helper.java

@Component
public class JcabiHttpClient {
    private final Logger logger = LoggerFactory.getLogger(JcabiHttpClient.class);
    public String fetchStringByGetRequest(String url, String path, String queryParam, String value) throws IOException {
        logger.info("{}", Chalk.on("\tfetchStringByGetRequest ").green());
        String html = new JdkRequest("https://www.google.com/test")
                .uri().path("/users").queryParam("id", 333).back()
                .method(Request.GET)
                .header(HttpHeaders.ACCEPT, MediaType.TEXT_HTML)
                .fetch()
                .as(RestResponse.class)
                //.assertStatus(HttpURLConnection.HTTP_OK)
                .body();
        logger.info("{}", Chalk.on("\n\t--> " + html).blue());
        return html;
    }
}
