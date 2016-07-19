package br.jus.trt23.contavinculada.jsf;

import br.jus.trt23.contavinculada.entities.EntidadeGenerica;
import br.jus.trt23.contavinculada.enums.EActiveAction;
import br.jus.trt23.contavinculada.jsf.util.JsfUtil;
import br.jus.trt23.contavinculada.jsf.util.PaginationHelper;
import br.jus.trt23.contavinculada.session.AbstractFacade;
import br.jus.trt23.contavinculada.qualifiers.MessageBundle;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;
import javax.enterprise.context.Dependent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

@Dependent
public abstract class AbstractController<F extends AbstractFacade, T extends EntidadeGenerica> implements Serializable {
    protected void prepareDlg(){
        
    }
    private List<T> selectedItems;

    private List<T> filteredItems;

    public List<T> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<T> filteredItems) {
        this.filteredItems = filteredItems;
    }

    public List<T> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<T> selectedItems) {
        this.selectedItems = selectedItems;
    }
    private DataModel items = null;
    protected PaginationHelper pagination;
    protected int selectedItemIndex;
    protected T selected;
    private String messagePrefix = "default";
    private EActiveAction activeAction;

    @Inject
    @MessageBundle
    protected transient ResourceBundle messages;

    public String getMessagePrefix() {
        return messagePrefix;
    }

    public void setMessagePrefix(String messagePrefix) {
        this.messagePrefix = messagePrefix;
    }

    protected abstract AbstractFacade getFacade();

    public AbstractController() {
        this.activeAction = EActiveAction.VIEW;
        prepareDlg();
    }

    public T getSelected() {
        if (selected == null) {
            selected = (T) getFacade().newInstance();
            selectedItemIndex = -1;
        }
        return (T) selected;
    }

    public T getEntity(java.lang.Long id) {
        return (T) getFacade().find(id);
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        activeAction = EActiveAction.VIEW;
        return "List?faces-redirect=true";
    }

    public String prepareView() {
        selected = (T) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        activeAction = EActiveAction.VIEW;
        return "View";
    }

    public String prepareCreate() {
        selected = (T) getFacade().newInstance();
        selectedItemIndex = -1;
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
                return "Erro";
        }
    }

    public String prepareEdit() {
        selected = (T) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        activeAction = EActiveAction.EDIT;
        return "Edit";
    }

    public String update() {
        String msg;
        try {
            getFacade().edit(selected);
            msg = messages.getString(getMessagePrefix().concat("_Updated"));
            JsfUtil.addSuccessMessage(msg);
            return "View";
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
        recreatePagination();
        recreateModel();
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
    

    
    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            selected = (T) getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
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

}
