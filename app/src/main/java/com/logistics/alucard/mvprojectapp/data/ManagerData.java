package com.logistics.alucard.mvprojectapp.data;

import com.logistics.alucard.mvprojectapp.data.database.IDatabaseHelper;

public class ManagerData implements IManagerData {

    private IDatabaseHelper databaseHelper;

    public ManagerData(IDatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void createRow(OnResponseListener listener, String id) {

    }

    @Override
    public void readRow(OnResponseListener listener, String id) {

    }

    @Override
    public void updateRow(OnResponseListener listener, String id) {

    }

    @Override
    public Integer deleteRow(OnResponseListener listener, String id) {

        return databaseHelper.deleteRow(listener, "id");
    }
}
