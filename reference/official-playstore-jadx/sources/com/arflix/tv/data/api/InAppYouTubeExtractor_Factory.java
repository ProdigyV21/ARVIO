package com.arflix.tv.data.api;

import dagger.internal.Factory;

/* JADX INFO: loaded from: classes3.dex */
public final class InAppYouTubeExtractor_Factory implements Factory<InAppYouTubeExtractor> {

    public static final class InstanceHolder {
        static final InAppYouTubeExtractor_Factory INSTANCE = new InAppYouTubeExtractor_Factory();

        private InstanceHolder() {
        }
    }

    public static InAppYouTubeExtractor_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static InAppYouTubeExtractor newInstance() {
        return new InAppYouTubeExtractor();
    }

    @Override // javax.inject.Provider
    public InAppYouTubeExtractor get() {
        return newInstance();
    }
}
