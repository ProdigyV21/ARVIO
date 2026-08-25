package com.arflix.tv.updater;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class UpdatePreferences_Factory implements Factory<UpdatePreferences> {
    private final Provider<Context> contextProvider;

    private UpdatePreferences_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static UpdatePreferences_Factory create(Provider<Context> provider) {
        return new UpdatePreferences_Factory(provider);
    }

    public static UpdatePreferences newInstance(Context context) {
        return new UpdatePreferences(context);
    }

    @Override // javax.inject.Provider
    public UpdatePreferences get() {
        return newInstance(this.contextProvider.get());
    }
}
