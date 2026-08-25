package io.ktor.server.request;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/server/request/DoubleReceivePreventionToken;", "", "()V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DoubleReceivePreventionToken {
    public static final DoubleReceivePreventionToken INSTANCE = new DoubleReceivePreventionToken();

    private DoubleReceivePreventionToken() {
    }
}
