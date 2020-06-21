package pe.com.pacifico.formularioevento.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.com.pacifico.formularioevento.model.Saludo;

@Controller
public class SaludoController {

    public static Log LOG = LogFactory.getLog(SaludoController.class);

    @GetMapping("/saludo")
    public String saludoForm(Model model) {
        LOG.info("creando formulario saludo");
        model.addAttribute("saludo", new Saludo());
        return "saludo";
    }

    @PostMapping("/saludo")
    public String saludoSubmit(@ModelAttribute Saludo saludo) throws JsonProcessingException {
        LOG.info("procesando saludo");
        saludo.enviarMensaje();
        return "respuesta";
    }

}
