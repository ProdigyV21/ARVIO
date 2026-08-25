package com.arflix.tv.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.arflix.tv.data.repository.CloudSyncRepository;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class CloudSyncWorker_Factory {
    private final Provider<CloudSyncRepository> cloudSyncRepositoryProvider;

    private CloudSyncWorker_Factory(Provider<CloudSyncRepository> provider) {
        this.cloudSyncRepositoryProvider = provider;
    }

    public static CloudSyncWorker_Factory create(Provider<CloudSyncRepository> provider) {
        return new CloudSyncWorker_Factory(provider);
    }

    public static CloudSyncWorker newInstance(Context context, WorkerParameters workerParameters, CloudSyncRepository cloudSyncRepository) {
        return new CloudSyncWorker(context, workerParameters, cloudSyncRepository);
    }

    public CloudSyncWorker get(Context context, WorkerParameters workerParameters) {
        return newInstance(context, workerParameters, this.cloudSyncRepositoryProvider.get());
    }
}
