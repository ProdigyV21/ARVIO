package io.ktor.websocket;

import io.ktor.util.AttributeKey;
import io.ktor.websocket.WebSocketExtension;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\bf\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\u000e\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u00028\u00000\u00032\u00020\u0001J(\u0010\t\u001a\u00028\u00012\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007H&¢\u0006\u0004\b\t\u0010\nR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\u000b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0012\u001a\u00020\u000f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\u000f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0016\u001a\u00020\u000f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011¨\u0006\u0017"}, d2 = {"Lio/ktor/websocket/WebSocketExtensionFactory;", "", "ConfigType", "Lio/ktor/websocket/WebSocketExtension;", "ExtensionType", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "config", "install", "(Lr7/l;)Lio/ktor/websocket/WebSocketExtension;", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "key", "", "getRsv1", "()Z", "rsv1", "getRsv2", "rsv2", "getRsv3", "rsv3", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface WebSocketExtensionFactory<ConfigType, ExtensionType extends WebSocketExtension<ConfigType>> {
    AttributeKey<ExtensionType> getKey();

    boolean getRsv1();

    boolean getRsv2();

    boolean getRsv3();

    ExtensionType install(l<? super ConfigType, t0> config);
}
