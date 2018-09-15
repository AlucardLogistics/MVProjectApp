package com.logistics.alucard.mvprojectapp.data;

import com.logistics.alucard.mvprojectapp.data.database.IDatabaseHelper;

public interface IManagerData extends IDatabaseHelper {

    interface OnResponseListener {
        void createRow(String id);
        void deleteRow(String id);
    }
}
