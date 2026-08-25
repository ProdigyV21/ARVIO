package com.arflix.tv.data.repository;

import dagger.internal.Factory;

/* JADX INFO: loaded from: classes3.dex */
public final class CloudSyncInvalidationBus_Factory implements Factory<CloudSyncInvalidationBus> {

    public static final class InstanceHolder {
        static final CloudSyncInvalidationBus_Factory INSTANCE = new CloudSyncInvalidationBus_Factory();

        private InstanceHolder() {
        }
    }

    public static CloudSyncInvalidationBus_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static CloudSyncInvalidationBus newInstance() {
        return new CloudSyncInvalidationBus();
    }

    @Override // javax.inject.Provider
    public CloudSyncInvalidationBus get() {
        return newInstance();
    }
}
