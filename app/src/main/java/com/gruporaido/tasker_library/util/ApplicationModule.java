package com.gruporaido.tasker_library.util;

import android.app.Application;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.gruporaido.tasker_library.http.APIFetch;

@Module
public class ApplicationModule {

    protected Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    APIFetch provideAPIFetch(Application application) {
        return new APIFetch(application);
    }

    @Provides
    @Singleton
    Helper provideHelper(Application application) {
        return new Helper(application);
    }

    @Provides
    @Singleton
    Bus provideBus() {
        return new Bus();
    }
}