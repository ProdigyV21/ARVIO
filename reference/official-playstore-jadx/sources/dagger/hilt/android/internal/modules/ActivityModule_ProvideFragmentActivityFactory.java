package dagger.hilt.android.internal.modules;

import android.app.Activity;
import androidx.fragment.app.h0;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class ActivityModule_ProvideFragmentActivityFactory implements Factory<h0> {
    private final Provider<Activity> activityProvider;

    private ActivityModule_ProvideFragmentActivityFactory(Provider<Activity> provider) {
        this.activityProvider = provider;
    }

    public static ActivityModule_ProvideFragmentActivityFactory create(Provider<Activity> provider) {
        return new ActivityModule_ProvideFragmentActivityFactory(provider);
    }

    public static h0 provideFragmentActivity(Activity activity) {
        return (h0) Preconditions.checkNotNullFromProvides(ActivityModule.provideFragmentActivity(activity));
    }

    @Override // javax.inject.Provider
    public h0 get() {
        return provideFragmentActivity(this.activityProvider.get());
    }
}
