package pe.com.pacifico.formularioevento.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pe.com.pacifico.formularioevento.util.EventPublisher;

public class Saludo {

    private String contenido;
    private String email;
    private String messageId;
    private String nombre;

    public void enviarMensaje() throws JsonProcessingException {
        EventPublisher publicador = new EventPublisher(this);
        publicador.publicar();
    }

    public String getContenido() {
        return contenido;
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