package pe.com.pacifico.formularioevento.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import pe.com.pacifico.formularioevento.util.EventPublisher;
import pe.com.pacifico.formularioevento.util.MessagePublisher;

public class Saludo {

    private String contenido;
    private String dni;
    private String email;
    private String messageId;
    private String nombre;

    public void enviarEvento() throws JsonProcessingException {
        EventPublisher publicador = new EventPublisher(this);
        publicador.publicar();
    }

    public void enviarMensaje() throws InterruptedException, ServiceBusException,JsonProcessingException {
        MessagePublisher publicador = new MessagePublisher(this);
        publicador.enviar();
    }

    public String getContenido() {
        return contenido;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

}
