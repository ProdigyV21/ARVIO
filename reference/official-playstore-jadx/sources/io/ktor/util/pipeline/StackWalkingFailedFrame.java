package io.ktor.util.pipeline;

import d7.j;
import d7.k;
import f7.d;
import io.ktor.util.StackFramesJvmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\bH\u0016¢\u0006\u0004\b\t\u0010\nJ \u0010\u000e\u001a\u00020\r2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0012\u001a\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0016\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lio/ktor/util/pipeline/StackWalkingFailedFrame;", "Lf7/d;", "Lio/ktor/util/CoroutineStackFrame;", "Ld7/d;", "", "<init>", "()V", "Ljava/lang/StackTraceElement;", "Lio/ktor/util/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "Lx6/d0;", "result", "Lx6/t0;", "resumeWith", "(Ljava/lang/Object;)V", "getCallerFrame", "()Lf7/d;", "callerFrame", "Ld7/j;", "getContext", "()Ld7/j;", "context", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class StackWalkingFailedFrame implements d, d7.d<?> {
    public static final StackWalkingFailedFrame INSTANCE = new StackWalkingFailedFrame();

    private StackWalkingFailedFrame() {
    }

    @Override // f7.d
    public d getCallerFrame() {
        return null;
    }

    @Override // d7.d
    public j getContext() {
        return k.f14688i;
    }

    public StackTraceElement getStackTraceElement() {
        return StackFramesJvmKt.createStackTraceElement(l0.f19747a.b(StackWalkingFailed.class), "failedToCaptureStackFrame", "StackWalkingFailed.kt", 8);
    }

    @Override // d7.d
    public void resumeWith(Object result) {
        StackWalkingFailed.INSTANCE.failedToCaptureStackFrame();
    }
}
