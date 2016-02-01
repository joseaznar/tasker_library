package com.gruporaido.tasker_library.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.gruporaido.tasker_library.TaskerLibrary;
import com.gruporaido.tasker_library.util.ApplicationComponent;


public abstract class DaggerFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationComponent component = getComponent();
        injectFragment(component);
    }

    protected ApplicationComponent getComponent() {
        return ((TaskerLibrary) getActivity().getApplicationContext()).getComponent();
    }

    public abstract void injectFragment(ApplicationComponent component);
}