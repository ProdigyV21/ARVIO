package com.arflix.tv.updater;

import dagger.internal.Factory;

/* JADX INFO: loaded from: classes4.dex */
public final class UpdateStatusManager_Factory implements Factory<UpdateStatusManager> {

    public static final class InstanceHolder {
        static final UpdateStatusManager_Factory INSTANCE = new UpdateStatusManager_Factory();

        private InstanceHolder() {
        }
    }

    public static UpdateStatusManager_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static UpdateStatusManager newInstance() {
        return new UpdateStatusManager();
    }

    @Override // javax.inject.Provider
    public UpdateStatusManager get() {
        return newInstance();
    }
}
