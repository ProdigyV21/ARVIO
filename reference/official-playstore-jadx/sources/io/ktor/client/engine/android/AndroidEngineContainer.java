package io.ktor.client.engine.android;

import io.ktor.client.HttpClientEngineContainer;
import io.ktor.client.engine.HttpClientEngineFactory;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0018\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lio/ktor/client/engine/android/AndroidEngineContainer;", "Lio/ktor/client/HttpClientEngineContainer;", "()V", "factory", "Lio/ktor/client/engine/HttpClientEngineFactory;", "getFactory", "()Lio/ktor/client/engine/HttpClientEngineFactory;", "toString", "", "ktor-client-android"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidEngineContainer implements HttpClientEngineContainer {
    private final HttpClientEngineFactory<?> factory = Android.INSTANCE;

    @Override // io.ktor.client.HttpClientEngineContainer
    public HttpClientEngineFactory<?> getFactory() {
        return this.factory;
    }

    public String toString() {
        return "Android";
    }
}
