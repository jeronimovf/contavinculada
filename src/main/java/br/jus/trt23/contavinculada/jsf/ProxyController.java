package br.jus.trt23.contavinculada.jsf;


import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class ProxyController implements Serializable{

    private AbstractController controller;

    public ProxyController() {
        this.controller = null;
    }

    public AbstractController getController() {
        return controller;
    }

    public void setController(AbstractController controller) {
        this.controller = controller;
    }
}
