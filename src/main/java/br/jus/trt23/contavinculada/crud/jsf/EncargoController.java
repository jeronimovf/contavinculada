package br.jus.trt23.contavinculada.crud.jsf;

import br.jus.trt23.contavinculada.entities.Encargo;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "encargoController_")
@ViewScoped
public class EncargoController extends AbstractController<Encargo> {

    public EncargoController() {
        // Inform the Abstract parent controller of the concrete Encargo Entity
        super(Encargo.class);
    }

    /**
     * Sets the "items" attribute with a collection of EncargoAliquota entities
     * that are retrieved from Encargo?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for EncargoAliquota page
     */
    public String navigateAliquota() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EncargoAliquota_items", this.getSelected().getAliquota());
        }
        return "/crud/encargoAliquota/index";
    }

}
