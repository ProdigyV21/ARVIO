package io.ktor.client.utils;

import io.ktor.util.InternalAPI;
import ka.f0;
import ka.x0;
import kotlin.Metadata;
import ra.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lka/x0;", "", "threadCount", "", "dispatcherName", "Lka/f0;", "clientDispatcher", "(Lka/x0;ILjava/lang/String;)Lka/f0;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CoroutineDispatcherUtilsKt {
    @InternalAPI
    public static final f0 clientDispatcher(x0 x0Var, int i10, String str) {
        x0.f19655d.getClass();
        return l.f21440i.limitedParallelism(i10);
    }

    public static /* synthetic */ f0 clientDispatcher$default(x0 x0Var, int i10, String str, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            str = "ktor-client-dispatcher";
        }
        return clientDispatcher(x0Var, i10, str);
    }
}
