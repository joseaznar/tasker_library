package com.gruporaido.tasker_library.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import com.gruporaido.tasker_library.TaskerLibrary;
import com.gruporaido.tasker_library.util.ApplicationComponent;

public abstract class DaggerListFragment extends ListFragment {

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