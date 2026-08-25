package com.arflix.tv.data.telegram;

import dagger.internal.Factory;

/* JADX INFO: loaded from: classes3.dex */
public final class TelegramSearchMatcher_Factory implements Factory<TelegramSearchMatcher> {

    public static final class InstanceHolder {
        static final TelegramSearchMatcher_Factory INSTANCE = new TelegramSearchMatcher_Factory();

        private InstanceHolder() {
        }
    }

    public static TelegramSearchMatcher_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static TelegramSearchMatcher newInstance() {
        return new TelegramSearchMatcher();
    }

    @Override // javax.inject.Provider
    public TelegramSearchMatcher get() {
        return newInstance();
    }
}
