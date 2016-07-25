/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.handlers;

import br.jus.trt23.contavinculada.session.AbstractFacade;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author generico
 */
public class CustomLazyDataModel<T> extends LazyDataModel<T>{
    private final AbstractFacade facade;
    T entity;

    public CustomLazyDataModel(AbstractFacade facade) {
        this.facade = facade;
    }

    @Override
    public Object getRowKey(T object) {
        return facade.find(object);
    }

    @Override
    public T getRowData(String rowKey) {
        return super.getRowData(rowKey); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        setRowCount(facade.count(filters));
        return facade.findRange(first, pageSize, multiSortMeta, filters);
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        setRowCount(facade.count(filters));
        return facade.findRange(first, pageSize, sortField, sortOrder, filters);
    }

    @Override
    public T getRowData() {
        return super.getRowData(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
