package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.PostoDeTrabalho;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class PostoDeTrabalhoController extends AbstractController<PostoDeTrabalho> {

    public PostoDeTrabalhoController() {
        super(PostoDeTrabalho.class);
    }

    @Override
    protected String getMessagePrefix() {
        return "PostoDeTrabalho";
    }

    @Override
    public void setSelected(PostoDeTrabalho selected) {
        super.setSelected(selected); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
