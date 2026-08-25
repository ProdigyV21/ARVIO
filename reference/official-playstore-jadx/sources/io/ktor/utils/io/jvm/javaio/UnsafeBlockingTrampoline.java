package io.ktor.utils.io.jvm.javaio;

import d7.j;
import ka.f0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0007\u0010\bJ#\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\n\u0010\u000b\u001a\u00060\tj\u0002`\nH\u0016¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/utils/io/jvm/javaio/UnsafeBlockingTrampoline;", "Lka/f0;", "<init>", "()V", "Ld7/j;", "context", "", "isDispatchNeeded", "(Ld7/j;)Z", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "block", "Lx6/t0;", "dispatch", "(Ld7/j;Ljava/lang/Runnable;)V", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class UnsafeBlockingTrampoline extends f0 {
    public static final UnsafeBlockingTrampoline INSTANCE = new UnsafeBlockingTrampoline();

    private UnsafeBlockingTrampoline() {
    }

    @Override // ka.f0
    public void dispatch(j context, Runnable block) {
        block.run();
    }

    @Override // ka.f0
    public boolean isDispatchNeeded(j context) {
        return true;
    }
}
