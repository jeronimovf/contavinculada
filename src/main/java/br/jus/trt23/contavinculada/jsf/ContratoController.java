package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.ContaVinculada;
import br.jus.trt23.contavinculada.entities.Contrato;
import br.jus.trt23.contavinculada.entities.EncargoAliquota;
import br.jus.trt23.contavinculada.entities.Faturamento;
import br.jus.trt23.contavinculada.entities.Fiscal;
import br.jus.trt23.contavinculada.entities.PostoDeTrabalho;
import br.jus.trt23.contavinculada.session.ContratoFacade;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named
@SessionScoped
public class ContratoController extends AbstractController<ContratoFacade, Contrato> {

    @Inject
    private transient ContratoFacade facade;

    public ContratoController() {
        setMessagePrefix("Contrato");
    }

    private Fiscal fiscalNovo;
    private EncargoAliquota aliquotaNova;
    private Faturamento faturamentoNovo;
    private ContaVinculada contaNova;
    private PostoDeTrabalho postoNovo;

    public String prepareFiscalNovo(){
        setFiscalNovo(new Fiscal());
        return "fiscalNovo";
    }
    
    public String prepareAliquotaNova(){
        setAliquotaNova(new EncargoAliquota());
        return "aliquotaNova";        
    }

    public String prepareFaturamentoNovo(){
        setFaturamentoNovo(new Faturamento());
        return "faturamentoNovo";
    }

    public String prepareContaNova(){
        setContaNova(new ContaVinculada());
        return "contaNova";        
    }

    public String preparePostoNovo(){
        setPostoNovo(new PostoDeTrabalho());
        return "postoNovo";
    }
    
    public Fiscal getFiscalNovo() {
        return fiscalNovo;
    }

    public void setFiscalNovo(Fiscal fiscalNovo) {
        this.fiscalNovo = fiscalNovo;
    }

    public EncargoAliquota getAliquotaNova() {
        return aliquotaNova;
    }

    public void setAliquotaNova(EncargoAliquota aliquotaNova) {
        this.aliquotaNova = aliquotaNova;
    }

    public Faturamento getFaturamentoNovo() {
        return faturamentoNovo;
    }

    public void setFaturamentoNovo(Faturamento faturamentoNovo) {
        this.faturamentoNovo = faturamentoNovo;
    }

    public ContaVinculada getContaNova() {
        return contaNova;
    }

    public void setContaNova(ContaVinculada contaNova) {
        this.contaNova = contaNova;
    }

    public PostoDeTrabalho getPostoNovo() {
        return postoNovo;
    }

    public void setPostoNovo(PostoDeTrabalho postoNovo) {
        this.postoNovo = postoNovo;
    }

    @Override
    protected ContratoFacade getFacade() {
        return facade;
    }

    public String prepareAditivoEdit(Contrato aditivo) {
        selected = aditivo;
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    @FacesConverter(forClass = Contrato.class)
    public static class ContratoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContratoController controller = (ContratoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contratoController");
            return controller.getEntity(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Contrato) {
                Contrato o = (Contrato) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Contrato.class.getName());
            }
        }

    }

}
