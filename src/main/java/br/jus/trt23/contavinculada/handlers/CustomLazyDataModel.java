/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.handlers;

import br.jus.trt23.contavinculada.entities.EntidadeGenerica;
import br.jus.trt23.contavinculada.session.AbstractFacade;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 *
 * @author generico
 * @param <T>
 */
public class CustomLazyDataModel<T extends EntidadeGenerica> extends LazyDataModel<T> {

    private final AbstractFacade<T> facade;
    private List<T> itemList;
    private Boolean isCountValid = Boolean.FALSE;

    public CustomLazyDataModel(AbstractFacade<T> facade) {
        super();
        this.facade = facade;
        this.itemList = null;
    }

    public CustomLazyDataModel(List<T> itemList) {
        super();
        this.facade = null;
        this.itemList = itemList;
    }

    @Override
    public Object getRowKey(T object) {
        return Integer.toString(object.hashCode());
    }

    @Override
    public T getRowData(String rowKey) {
        if (itemList != null) {
            for (T entity : itemList) {
                if (getRowKey(entity).equals(rowKey)) {
                    return entity;
                }
            }
        }
        return null;
    }

    @Override
    public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        // Turn sort info into a linked hash map for the facade
        HashMap<String, String> sortFields = new LinkedHashMap<>();
        if (multiSortMeta != null) {
            for (SortMeta s : multiSortMeta) {
                sortFields.put(s.getSortField(), s.getSortOrder().toString());
            }
        }
        itemList = this.facade.findRange(first, pageSize, sortFields, filters);
        this.setRowCount(this.facade.count(filters)); // Count ALL records for the applied filter
        this.isCountValid = Boolean.TRUE;
        return itemList;
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        if (this.facade != null) { // Handle data that needs to be retrieved from the data back-end of the application

            String sortOrderName = sortOrder.toString();
            this.itemList = this.facade.findRange(first, pageSize, sortField, sortOrderName, filters);
            this.setRowCount(this.facade.count(filters)); // Count ALL records for the applied filter
            this.isCountValid = Boolean.TRUE;
            return this.itemList;

        } else if (this.itemList != null) { // Handle data that was passed in by application

            // filter
            List<T> filteredItemList = filter(this.itemList, filters);

            // sort
            if (sortField != null) {
                Collections.sort(filteredItemList, new CustomLazyEntitySorter<>(sortField, sortOrder));
            }

            // rowCount
            int itemCount = filteredItemList.size();
            this.setRowCount(itemCount);

            // paginate
            if (itemCount > pageSize) {
                try {
                    return filteredItemList.subList(first, first + pageSize);
                } catch (IndexOutOfBoundsException e) {
                    return filteredItemList.subList(first, first + (itemCount % pageSize));
                }
            } else {
                return filteredItemList;
            }

        } else { // Nothing passed in by application
            return null;
        }
    }

    private List<T> filter(List<T> itemList, Map<String, Object> filters) {

        List<T> filteredItemList = new ArrayList<>();

        // apply filters
        for (T entity : itemList) {
            boolean match = true;
            for (String filterField : filters.keySet()) {
                String filterValue = String.valueOf(filters.get(filterField)).toLowerCase();
                String fieldValue = String.valueOf(EntidadeGenerica.getFieldValue(entity, filterField)).toLowerCase();
                if (filterValue != null && fieldValue != null && !fieldValue.startsWith(filterValue)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                filteredItemList.add(entity);
            }
        }
        return filteredItemList;
    }

    @Override
    public int getRowCount() {
        int rowCountTmp = super.getRowCount();
        if(this.isCountValid){
            return rowCountTmp;
        }
        else{
            rowCountTmp = this.facade.count();
            setRowCount(rowCountTmp);  
            this.isCountValid = Boolean.TRUE;            
            return rowCountTmp;
        }
    }
    
    public void refreshQuery(){
        this.isCountValid = Boolean.FALSE;
    }
}
