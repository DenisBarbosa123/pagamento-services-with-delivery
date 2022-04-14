package br.inatel.dm112.client;

import br.inatel.dm112.model.MailRequestData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class EmailClient {
    @Value("${utility.rest.url}")
    private String restURL;

    @Value("${email.sendFromAddress}")
    private String sendFromAddress;

    @Value("${email.password}")
    private String sendPassAddress;

    public void callSendMailService(String sendToAddress)
    {
        String url = restURL + "/mail";
        System.out.println("URL: " + url);
        MailRequestData mrd = new MailRequestData(sendFromAddress, sendPassAddress, sendToAddress, null);

        WebClient
                .create(url)
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(mrd), MailRequestData.class)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class).defaultIfEmpty("")
                .log()
                .block();
    }
}
