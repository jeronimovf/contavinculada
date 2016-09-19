package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.RAT;
import br.jus.trt23.contavinculada.entities.RATItem;
import br.jus.trt23.contavinculada.jsf.util.JsfUtil;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.datatable.DataTable;

@Named("ratController")
@Dependent
@Getter
@Setter
public class RATController extends AbstractController<RAT> {
    private RATItem itemNovo;
    
    public RATController() {
        super(RAT.class);
    }

    public String prepareItemNovo(){
        setItemNovo(new RATItem());
        return "ItemNovo";
    }
    
    public String prepareItemEdit() {
        Object obj = JsfUtil.findComponent("itemDT");
        if (obj instanceof DataTable) {
            DataTable dt = (DataTable) obj;
            setItemNovo((RATItem) dt.getRowData());
        }
        return "ItemEdit";
    }
    
    public String saveOrCreateItem(){
        String msg;
        try {
            selected.addItem(itemNovo);
            saveOrCreate();
            msg = getResponseCreated("Item");
            JsfUtil.addSuccessMessage(msg);
            return "Edit";
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }        
    }
    
    @Override
    protected String getMessagePrefix() {
        return "RAT";
    }
}
