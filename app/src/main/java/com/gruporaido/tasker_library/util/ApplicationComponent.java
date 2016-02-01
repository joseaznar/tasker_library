package com.gruporaido.tasker_library.util;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import com.gruporaido.tasker_library.activity.AboutActivity;
import com.gruporaido.tasker_library.activity.SupportActivity;
import com.gruporaido.tasker_library.activity.WebViewActivity;
import com.gruporaido.tasker_library.fragment.BaseFragment;
import com.gruporaido.tasker_library.fragment.JobDetailsFragment;
import com.gruporaido.tasker_library.fragment.MapsFragment;
import com.gruporaido.tasker_library.fragment.SupportFragment;
import com.gruporaido.tasker_library.fragment.UserCardFragment;
import com.gruporaido.tasker_library.worker.NotificationWorker;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(Application application);

    void inject(AboutActivity activity);

    void inject(SupportActivity activity);

    void inject(WebViewActivity activity);

    void inject(BaseFragment fragment);

    void inject(SupportFragment fragment);

    void inject(MapsFragment fragment);

    void inject(UserCardFragment fragment);

    void inject(JobDetailsFragment fragment);

    void inject(NotificationWorker worker);
}