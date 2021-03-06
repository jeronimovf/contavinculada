package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.EntidadeGenerica;
import br.jus.trt23.contavinculada.enums.EActiveAction;
import br.jus.trt23.contavinculada.handlers.CustomLazyDataModel;
import br.jus.trt23.contavinculada.jsf.util.JsfUtil;
import br.jus.trt23.contavinculada.qualifiers.MessageBundle;
import br.jus.trt23.contavinculada.session.AbstractFacade;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;

@Getter
@Setter
public abstract class AbstractController<T extends EntidadeGenerica> implements Serializable {
    @Inject
    private AbstractFacade<T> facade;
    private Class<T> itemClass;
    //o escopo original de selected no CRUD PF é private
    protected T selected;
    private Collection<T> items;
    private CustomLazyDataModel<T> lazyItems = null;    
    
    //essas listas são utilizadas nas DataTable
    private List<T> selectedItems;
    private List<T> filteredItems;
    
    //o messagePrefix é utilizado para a obtenção de strings de bundle
    protected abstract String getMessagePrefix();

    //a enumeração é utilizada para determinar qual o estado de persistência
    //vigente
    private EActiveAction activeAction;

    @Inject
    @MessageBundle
    //o messages é utilizado para obtenção de propriedades do bundle
    //que são gerais, não dependendo de um controle específico e do
    //seu messagePrefix
    protected transient ResourceBundle messages;

    public AbstractController() {
    }

    public AbstractController(Class<T> itemClass){
        this.itemClass = itemClass;
        this.activeAction = EActiveAction.VIEW;
        prepareDlg();
    }
    
    public T getEntity(java.lang.Long id) {
        return (T) getFacade().find(id);
    }

    public Collection<T> getItems(){
        if (items == null) {
            items = this.facade.findAll();
        }
        return items;        
    }
    
    public CustomLazyDataModel<T> getLazyItems(){
        if (lazyItems == null) {
            lazyItems = new CustomLazyDataModel<>(this.facade);
        }
        return lazyItems;        
    }
    
    public void setLazyItems(CustomLazyDataModel<T> lazyItems) {
        this.lazyItems = lazyItems;
    }

    public void setLazyItems(Collection<T> items) {
        if (items instanceof List) {
            lazyItems = new CustomLazyDataModel<>((List<T>) items);
        } else {
            lazyItems = new CustomLazyDataModel<>(new ArrayList<>(items));
        }
    }
    
    public String prepareList() {
        activeAction = EActiveAction.VIEW;
        return "List?faces-redirect=true";
    }

    public String prepareCreate() {
        selected = (T) getFacade().newInstance();
        activeAction = EActiveAction.NEW;
        return "Create";
    }

    public String create() throws Exception {
        String msg;
        try {    
            getFacade().create(selected);
            msg = messages.getString(getMessagePrefix().concat("_Created"));
            JsfUtil.addSuccessMessage(msg);
            return prepareList();
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }

    public String saveOrCreate() throws Exception {
        switch (activeAction) {
            case EDIT:
                return update();
            case NEW:
                return create();
            default:
                return "";
        }
    }

    public String prepareEdit() {
        selected = (T) getLazyItems().getRowData();
        activeAction = EActiveAction.EDIT;
        return "Edit";
    }

    public String update() {
        String msg;
        try {
            getFacade().edit(selected);
            msg = messages.getString(getMessagePrefix().concat("_Updated"));
            JsfUtil.addSuccessMessage(msg);
            return prepareList();
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
            return null;
        }
    }

    public String destroy() {
        String msg;
        for (T obj : getSelectedItems()) {
            performDestroy(obj);
        }
        msg = MessageFormat.format(messages.getString(getMessagePrefix().concat("_Deleted")), getSelectedItems().size());
        JsfUtil.addSuccessMessage(msg);
        refreshList();
        return prepareList();
    }

    private void performDestroy(T item) {
        String msg;
        try {
            getFacade().remove(item);
        } catch (Exception e) {
            msg = messages.getString("PersistenceErrorOccured");
            JsfUtil.addErrorMessage(e, msg);
        }
    }
    
    public List<T> complete(String criteria){
        return getFacade().complete(criteria);        
    }

    public String getMsgPageTitle() {
        return messages.getString(getMessagePrefix().concat("_Title_").concat(activeAction.toString()));
    }

    public String getMsgEmptyList() {
        return messages.getString(getMessagePrefix().concat("_Empty"));
    }
    
    public String getMsgNotFound(){
        return messages.getString(getMessagePrefix().concat("_NotFound"));        
    }

    public String getMsgFieldLabel(String field) {
        return messages.getString(getMessagePrefix().concat("_Field_").concat(field));
    }

    public String getMsgAction(String action) {
        return messages.getString(getMessagePrefix().concat("_Action_").concat(action));
    }

    public String getDlgCreateHeader(String header) {
        String header_ = messages.getString(getMessagePrefix().concat("_TabHeader_").concat(header));
        header_.concat(" (").concat(messages.getString("CreateLink")).concat(")");
        return header_;
    }    
    
    public String getDlgEditHeader(String header) {
        String header_ = messages.getString(getMessagePrefix().concat("_TabHeader_").concat(header));
        header_.concat(" (").concat(messages.getString("EditLink")).concat(")");
        return header_;
    }    
    public String getResponseCreated(String child) {
        return messages.getString(getMessagePrefix().concat("_Response_").concat(child).concat("_Created"));
    }
    
    public String getTabHeader(String header) {
        return messages.getString(getMessagePrefix().concat("_TabHeader_").concat(header));
    } 

    public List<SelectItem> getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getFacade().findAll(), false);
    }

    public List<SelectItem> getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getFacade().findAll(), true);
    }

    private void refreshList() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formRoot:listItems");
    }
    
   
    protected void prepareDlg(){
        
    }
}
