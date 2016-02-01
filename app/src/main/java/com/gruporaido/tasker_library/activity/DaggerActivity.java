package com.gruporaido.tasker_library.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gruporaido.tasker_library.TaskerLibrary;
import com.gruporaido.tasker_library.util.ApplicationComponent;


public abstract class DaggerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationComponent component = getComponent();
        injectActivity(component);
    }

    protected ApplicationComponent getComponent() {
        return ((TaskerLibrary) getApplication()).getComponent();
    }

    public abstract void injectActivity(ApplicationComponent component);
}