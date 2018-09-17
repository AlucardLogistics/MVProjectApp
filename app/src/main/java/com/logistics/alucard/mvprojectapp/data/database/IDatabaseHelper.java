package com.logistics.alucard.mvprojectapp.data.database;

import com.logistics.alucard.mvprojectapp.extra.IManagerData;

public interface IDatabaseHelper {

    void createRow(IManagerData.OnResponseListener listener, String id);
    void readRow(IManagerData.OnResponseListener listener, String id);
    void updateRow(IManagerData.OnResponseListener listener, String id);
    Integer deleteRow(IManagerData.OnResponseListener listener, String id);
}
