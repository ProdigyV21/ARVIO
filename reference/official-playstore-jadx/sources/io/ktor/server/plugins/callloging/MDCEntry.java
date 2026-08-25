package io.ktor.server.plugins.callloging;

import io.ktor.http.ContentDisposition;
import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000bR%\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/server/plugins/callloging/MDCEntry;", "", "", ContentDisposition.Parameters.Name, "Lkotlin/Function1;", "Lio/ktor/server/application/ApplicationCall;", "provider", "<init>", "(Ljava/lang/String;Lr7/l;)V", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "Lr7/l;", "getProvider", "()Lr7/l;", "ktor-server-call-logging"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MDCEntry {
    private final String name;
    private final l<ApplicationCall, String> provider;

    /* JADX WARN: Multi-variable type inference failed */
    public MDCEntry(String str, l<? super ApplicationCall, String> lVar) {
        this.name = str;
        this.provider = lVar;
    }

    public final String getName() {
        return this.name;
    }

    public final l<ApplicationCall, String> getProvider() {
        return this.provider;
    }
}
