/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guutong.guutongprimeface.service;


import com.guutong.guutongprimeface.connectdb.query.QueryBuilder;
import com.guutong.guutongprimeface.connectdb.util.SqlUtils;
import com.guutong.guutongprimeface.model.EmployeeModel;
import java.util.List;

/**
 *
 * @author pornmongkon
 */
public class EmployeeSearchBySalaryServiceImpl implements EmployeeSearchService {

    @Override
    public List<EmployeeModel> search(String keyword) {
        keyword = SqlUtils.wrapKeywordLike(keyword);
        
        return QueryBuilder.fromSQL("SELECT * FROM Employees WHERE LOWER(salary) LIKE ?")
                .addParam(keyword)
                .executeforList(EmployeeModel.class);
    }

}
