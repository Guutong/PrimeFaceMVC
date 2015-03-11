/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guutong.guutongprimeface.controller;

import com.guutong.guutongprimeface.model.EmployeeModel;
import com.guutong.guutongprimeface.service.DeleteService;
import com.guutong.guutongprimeface.service.EmployeeSearchService;
import com.guutong.guutongprimeface.service.InsertService;
import com.guutong.guutongprimeface.service.SearchServiceUtils;
import com.guutong.guutongprimeface.service.UpdateService;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author pornmongkon
 */
@ManagedBean
@ViewScoped
public class EmployeeControl implements Serializable {

    private List<EmployeeModel> employees;
    private String query;
    private String searchBy;
    private EmployeeModel employee;

    @PostConstruct
    public void postConstruct() {
        onSearch();
    }

    public void onSearch() {
        EmployeeSearchService service = SearchServiceUtils.findServiceByName(searchBy);
        employees = service.search(query);
    }

    public void onClear() {
        query = null;
        searchBy = null;
        onSearch();
    }

    public List<EmployeeModel> getEmployees() {
        if (employees == null) {
            employees = new LinkedList<>();
        }

        return employees;
    }

    public void onInsert() throws ClassNotFoundException, SQLException {

        employee.setId(employees.get(employees.size() - 1).getId() + 1);
        
        IntoRow(employee);
        notifyMessageInsert(employee);
        onClear();
        onSearch();
        employee = null;

    }

    public void IntoRow(EmployeeModel e) throws ClassNotFoundException, SQLException {
        InsertService service = new InsertService();
        service.insertRow(e);
    }

    public void onDelete() throws ClassNotFoundException, SQLException {
        notifyMessageDelete();
        DeleteRow(employee);
        onClear();
        onSearch();

    }

    public void DeleteRow(EmployeeModel e) throws ClassNotFoundException, SQLException {
        DeleteService service = new DeleteService();
        service.deleteRow(e);
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public void notifyMessageDelete() {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(
                                FacesMessage.SEVERITY_INFO,
                                "Delete Employee",
                                "success (id " + employee.getId() + ")"
                        ));
    }

    public void notifyMessageInsert(EmployeeModel e) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(
                                FacesMessage.SEVERITY_INFO,
                                "Insert Employee",
                                "success (id " + e.getId() + ")"
                        ));
    }

    private Object request(String param) {
        return FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get(param);
    }

    public void onSelect() {
        Integer id = Integer.valueOf((String) request("employeeId"));
        EmployeeModel emp = new EmployeeModel();
        emp.setId(id);
        int index = getEmployees().indexOf(emp);
        employee = getEmployees().get(index);
    }

    public EmployeeModel getEmployee() {
        if (employee == null) {
            employee = new EmployeeModel();
        }

        return employee;
    }

    public void onUpdate() throws SQLException {
        updateRow(employee);
    }

    public void updateRow(EmployeeModel e) throws SQLException {
        UpdateService service = new UpdateService();
        service.updateRow(e);
    }
}
