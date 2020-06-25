package pe.com.pacifico.formularioevento.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pe.com.pacifico.formularioevento.model.Saludo;

import java.time.Duration;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;

public class MessagePublisher {

    public static Log LOG = LogFactory.getLog(EventPublisher.class);

    private Saludo saludo;

    public MessagePublisher(Saludo saludo) {
        this.saludo = saludo;
    }

    public void enviar() throws InterruptedException, ServiceBusException, JsonProcessingException {
        QueueClient sendClient = new QueueClient(new ConnectionStringBuilder(System.getenv("SB_CONNECTION_STRING"), System.getenv("SB_QUEUE_NAME")), ReceiveMode.PEEKLOCK);
        sendMessages(sendClient); //.thenRunAsync(sendClient::closeAsync);
        sendClient.close();
    }

    void sendMessages(QueueClient sendClient) throws JsonProcessingException, InterruptedException, ServiceBusException {
        final String messageId = UUID.randomUUID().toString();
        saludo.setMessageId(messageId);
        Message message = new Message(saludo.toJsonString().getBytes(UTF_8));
        message.setContentType("application/json");
        message.setLabel("Saludo");
        message.setMessageId(messageId);
        message.setTimeToLive(Duration.ofMinutes(180));
        LOG.info(String.format("Enviando mensjaje id = %s", message.getMessageId()));
        sendClient.send(message);
        LOG.info(String.format("Mensaje reconocido id = %s", message.getMessageId()));
    };
}
