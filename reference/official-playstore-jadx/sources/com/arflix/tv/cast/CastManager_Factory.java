package com.arflix.tv.cast;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class CastManager_Factory implements Factory<CastManager> {
    private final Provider<Context> contextProvider;

    private CastManager_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static CastManager_Factory create(Provider<Context> provider) {
        return new CastManager_Factory(provider);
    }

    public static CastManager newInstance(Context context) {
        return new CastManager(context);
    }

    @Override // javax.inject.Provider
    public CastManager get() {
        return newInstance(this.contextProvider.get());
    }
}
