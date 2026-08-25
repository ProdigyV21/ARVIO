package com.arflix.tv.data.repository;

import dagger.internal.Factory;

/* JADX INFO: loaded from: classes3.dex */
public final class ContinueWatchingUpdates_Factory implements Factory<ContinueWatchingUpdates> {

    public static final class InstanceHolder {
        static final ContinueWatchingUpdates_Factory INSTANCE = new ContinueWatchingUpdates_Factory();

        private InstanceHolder() {
        }
    }

    public static ContinueWatchingUpdates_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static ContinueWatchingUpdates newInstance() {
        return new ContinueWatchingUpdates();
    }

    @Override // javax.inject.Provider
    public ContinueWatchingUpdates get() {
        return newInstance();
    }
}
