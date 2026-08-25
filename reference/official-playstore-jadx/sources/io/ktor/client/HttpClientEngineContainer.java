package io.ktor.client;

import io.ktor.client.engine.HttpClientEngineFactory;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lio/ktor/client/HttpClientEngineContainer;", "", "factory", "Lio/ktor/client/engine/HttpClientEngineFactory;", "getFactory", "()Lio/ktor/client/engine/HttpClientEngineFactory;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface HttpClientEngineContainer {
    HttpClientEngineFactory<?> getFactory();
}
