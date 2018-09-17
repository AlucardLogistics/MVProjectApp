package com.logistics.alucard.mvprojectapp.bases;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract int getLayoutId();
    public void setView(){}

    public void showMessageToast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
