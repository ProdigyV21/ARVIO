package com.arflix.tv.util;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class SoundManager_Factory implements Factory<SoundManager> {
    private final Provider<Context> contextProvider;

    private SoundManager_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static SoundManager_Factory create(Provider<Context> provider) {
        return new SoundManager_Factory(provider);
    }

    public static SoundManager newInstance(Context context) {
        return new SoundManager(context);
    }

    @Override // javax.inject.Provider
    public SoundManager get() {
        return newInstance(this.contextProvider.get());
    }
}
