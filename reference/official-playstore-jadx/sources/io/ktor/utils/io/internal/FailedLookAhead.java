package io.ktor.utils.io.internal;

import d7.d;
import io.ktor.utils.io.LookAheadSuspendSession;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u0006H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lio/ktor/utils/io/internal/FailedLookAhead;", "Lio/ktor/utils/io/LookAheadSuspendSession;", "", "cause", "<init>", "(Ljava/lang/Throwable;)V", "", "n", "", "consumed", "(I)Ljava/lang/Void;", "skip", "atLeast", "Ljava/nio/ByteBuffer;", "request", "(II)Ljava/nio/ByteBuffer;", "", "awaitAtLeast", "(ILd7/d;)Ljava/lang/Object;", "Ljava/lang/Throwable;", "getCause", "()Ljava/lang/Throwable;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FailedLookAhead implements LookAheadSuspendSession {
    private final Throwable cause;

    public FailedLookAhead(Throwable th) {
        this.cause = th;
    }

    @Override // io.ktor.utils.io.LookAheadSuspendSession
    public Object awaitAtLeast(int i10, d<? super Boolean> dVar) throws Throwable {
        throw this.cause;
    }

    public final Throwable getCause() {
        return this.cause;
    }

    @Override // io.ktor.utils.io.LookAheadSession
    public ByteBuffer request(int skip, int atLeast) throws Throwable {
        throw this.cause;
    }

    @Override // io.ktor.utils.io.LookAheadSession
    /* JADX INFO: renamed from: consumed, reason: merged with bridge method [inline-methods] */
    public Void mo7011consumed(int n6) throws Throwable {
        throw this.cause;
    }
}
