package io.ktor.client.engine;

import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0011\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/client/engine/ClientEngineClosedException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "cause", "", "(Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ClientEngineClosedException extends IllegalStateException {
    private final Throwable cause;

    /* JADX WARN: Multi-variable type inference failed */
    public ClientEngineClosedException() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public /* synthetic */ ClientEngineClosedException(Throwable th, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : th);
    }

    public ClientEngineClosedException(Throwable th) {
        super("Client already closed");
        this.cause = th;
    }
}
