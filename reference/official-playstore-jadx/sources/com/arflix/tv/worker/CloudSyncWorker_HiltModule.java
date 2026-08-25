package com.arflix.tv.worker;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;
import i1.c;
import m2.s;

/* JADX INFO: loaded from: classes4.dex */
@Module
public interface CloudSyncWorker_HiltModule {
    @Binds
    @IntoMap
    @StringKey("com.arflix.tv.worker.CloudSyncWorker")
    c<? extends s> bind(CloudSyncWorker_AssistedFactory cloudSyncWorker_AssistedFactory);
}
