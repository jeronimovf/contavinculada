package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.Encargo;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

@Named
@FlowScoped("encargoflow")
public class EncargoController extends AbstractController<Encargo> {

    public EncargoController() {
        super(Encargo.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "Encargo";
    }

    @Override
    public void setSelected(Encargo selected) {
        super.setSelected(selected); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
