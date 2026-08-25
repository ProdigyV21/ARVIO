package com.arflix.tv.di;

import com.squareup.moshi.z;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class AppModule_ProvideMoshiFactory implements Factory<z> {

    public static final class InstanceHolder {
        static final AppModule_ProvideMoshiFactory INSTANCE = new AppModule_ProvideMoshiFactory();

        private InstanceHolder() {
        }
    }

    public static AppModule_ProvideMoshiFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static z provideMoshi() {
        return (z) Preconditions.checkNotNullFromProvides(AppModule.provideMoshi());
    }

    @Override // javax.inject.Provider
    public z get() {
        return provideMoshi();
    }
}
