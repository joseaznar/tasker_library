package com.gruporaido.tasker_library;

import android.app.Application;
import android.content.Context;

import com.gruporaido.tasker_library.util.DaggerApplicationComponent;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import com.gruporaido.tasker_library.report.Debug;
import com.gruporaido.tasker_library.report.SentryReporter;
import com.gruporaido.tasker_library.util.ApplicationComponent;
import com.gruporaido.tasker_library.util.ApplicationModule;

@ReportsCrashes(
        formKey = "",
        mode = ReportingInteractionMode.TOAST,
        logcatArguments = {"-t", "500", "-v", "time"}

)
public abstract class TaskerLibrary extends Application {

    static public Debug debug = new Debug(
            new SentryReporter("https://bf6fb70bb23d4c168c2cd31986c64e81:dff32a2aa77a43b592ed8ca8b5cf53e8@app.getsentry.com/46864")
    );

    protected RefWatcher mRefWatcher;
    protected ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        createComponent();
        createWatcher();
    }

    protected void createComponent() {
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mComponent.inject(this);
    }

    protected void createWatcher() {
        mRefWatcher = LeakCanary.install(this);
    }

    public ApplicationComponent getComponent() {
        return mComponent;
    }

    public RefWatcher getRefWatcher(Context context) {
        return mRefWatcher;
    }
}
