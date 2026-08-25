package dagger.hilt.android.internal.modules;

import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.h0;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

/* JADX INFO: loaded from: classes4.dex */
@Module
abstract class ActivityModule {
    private ActivityModule() {
    }

    @Provides
    @Reusable
    public static h0 provideFragmentActivity(Activity activity) {
        try {
            return (h0) activity;
        } catch (ClassCastException e5) {
            throw new IllegalStateException("Expected activity to be a FragmentActivity: " + activity, e5);
        }
    }

    @Binds
    public abstract Context provideContext(Activity activity);
}
