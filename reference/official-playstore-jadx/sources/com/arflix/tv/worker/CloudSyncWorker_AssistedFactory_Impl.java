package com.arflix.tv.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class CloudSyncWorker_AssistedFactory_Impl implements CloudSyncWorker_AssistedFactory {
    private final CloudSyncWorker_Factory delegateFactory;

    public CloudSyncWorker_AssistedFactory_Impl(CloudSyncWorker_Factory cloudSyncWorker_Factory) {
        this.delegateFactory = cloudSyncWorker_Factory;
    }

    public static Provider<CloudSyncWorker_AssistedFactory> createFactoryProvider(CloudSyncWorker_Factory cloudSyncWorker_Factory) {
        return InstanceFactory.create(new CloudSyncWorker_AssistedFactory_Impl(cloudSyncWorker_Factory));
    }

    @Override // com.arflix.tv.worker.CloudSyncWorker_AssistedFactory, i1.c
    public CloudSyncWorker create(Context context, WorkerParameters workerParameters) {
        return this.delegateFactory.get(context, workerParameters);
    }

    public static javax.inject.Provider<CloudSyncWorker_AssistedFactory> create(CloudSyncWorker_Factory cloudSyncWorker_Factory) {
        return InstanceFactory.create(new CloudSyncWorker_AssistedFactory_Impl(cloudSyncWorker_Factory));
    }
}
