package com.arflix.tv.data.local;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ProfileDataStoreFactory_Factory implements Factory<ProfileDataStoreFactory> {
    private final Provider<Context> contextProvider;

    private ProfileDataStoreFactory_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static ProfileDataStoreFactory_Factory create(Provider<Context> provider) {
        return new ProfileDataStoreFactory_Factory(provider);
    }

    public static ProfileDataStoreFactory newInstance(Context context) {
        return new ProfileDataStoreFactory(context);
    }

    @Override // javax.inject.Provider
    public ProfileDataStoreFactory get() {
        return newInstance(this.contextProvider.get());
    }
}
