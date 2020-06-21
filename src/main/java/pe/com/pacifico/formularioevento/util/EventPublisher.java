package pe.com.pacifico.formularioevento.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.azure.eventgrid.EventGridClient;
import com.microsoft.azure.eventgrid.TopicCredentials;
import com.microsoft.azure.eventgrid.implementation.EventGridClientImpl;
import com.microsoft.azure.eventgrid.models.EventGridEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import pe.com.pacifico.formularioevento.model.Saludo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventPublisher {

    public static Log LOG = LogFactory.getLog(EventPublisher.class);

    private final Saludo saludo;

    public EventPublisher(Saludo saludo) {
        this.saludo = saludo;
    }

    public void publicar() throws JsonProcessingException {
        try {
            LOG.info("crear cliente de EventGrid");
            TopicCredentials topicCredentials = new TopicCredentials(System.getenv("EVENTGRID_TOPIC_KEY"));
            EventGridClient client = new EventGridClientImpl(topicCredentials);
            LOG.info("publicar evento");
            saludo.setMessageId(UUID.randomUUID().toString());
            EventGridEvent evento = new EventGridEvent(
                    saludo.getMessageId(),
                    String.format("Saludo %s", saludo.getNombre()),
                    saludo.toJsonString(),
                    "Pacifico.Prueba.Saludo",
                    DateTime.now(),
                    "2.0");
            List<EventGridEvent> eventsList = new ArrayList<>();
            eventsList.add(evento);
            String eventGridEndpoint = null;
            eventGridEndpoint = String.format("https://%s/", new URI(System.getenv("EVENTGRID_TOPIC_ENDPOINT")).getHost());
            client.publishEvents(eventGridEndpoint, eventsList);
            LOG.info("evento publicado");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
