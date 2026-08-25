package io.ktor.client.engine.android;

import io.ktor.client.engine.HttpClientEngine;
import io.ktor.client.engine.HttpClientEngineFactory;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J(\u0010\n\u001a\u00020\t2\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/client/engine/android/Android;", "Lio/ktor/client/engine/HttpClientEngineFactory;", "Lio/ktor/client/engine/android/AndroidEngineConfig;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "Lio/ktor/client/engine/HttpClientEngine;", "create", "(Lr7/l;)Lio/ktor/client/engine/HttpClientEngine;", "ktor-client-android"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Android implements HttpClientEngineFactory<AndroidEngineConfig> {
    public static final Android INSTANCE = new Android();

    private Android() {
    }

    @Override // io.ktor.client.engine.HttpClientEngineFactory
    public HttpClientEngine create(l<? super AndroidEngineConfig, t0> block) {
        AndroidEngineConfig androidEngineConfig = new AndroidEngineConfig();
        block.invoke(androidEngineConfig);
        return new AndroidClientEngine(androidEngineConfig);
    }
}
