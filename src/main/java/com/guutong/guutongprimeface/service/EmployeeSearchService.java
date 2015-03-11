/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guutong.guutongprimeface.service;


import com.guutong.guutongprimeface.model.EmployeeModel;
import java.util.List;

/**
 *
 * @author pornmongkon
 */
public interface EmployeeSearchService {

    List<EmployeeModel> search(String keyword);
}
