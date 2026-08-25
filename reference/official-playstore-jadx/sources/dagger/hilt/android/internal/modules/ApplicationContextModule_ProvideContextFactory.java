package dagger.hilt.android.internal.modules;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class ApplicationContextModule_ProvideContextFactory implements Factory<Context> {
    private final ApplicationContextModule module;

    private ApplicationContextModule_ProvideContextFactory(ApplicationContextModule applicationContextModule) {
        this.module = applicationContextModule;
    }

    public static ApplicationContextModule_ProvideContextFactory create(ApplicationContextModule applicationContextModule) {
        return new ApplicationContextModule_ProvideContextFactory(applicationContextModule);
    }

    public static Context provideContext(ApplicationContextModule applicationContextModule) {
        return (Context) Preconditions.checkNotNullFromProvides(applicationContextModule.provideContext());
    }

    @Override // javax.inject.Provider
    public Context get() {
        return provideContext(this.module);
    }
}
