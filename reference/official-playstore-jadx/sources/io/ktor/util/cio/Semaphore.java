package io.ktor.util.cio;

import d7.d;
import e7.a;
import kotlin.Metadata;
import ua.f;
import ua.j;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\u0007\u001a\u00020\u0006H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\t\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\bJ\u000f\u0010\n\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0006¢\u0006\u0004\b\f\u0010\u000bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lio/ktor/util/cio/Semaphore;", "", "", "limit", "<init>", "(I)V", "Lx6/t0;", "enter", "(Ld7/d;)Ljava/lang/Object;", "acquire", "leave", "()V", "release", "I", "getLimit", "()I", "Lua/f;", "delegate", "Lua/f;", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
@e
public final class Semaphore {
    private final f delegate;
    private final int limit;

    public Semaphore(int i10) {
        this.limit = i10;
        this.delegate = j.a(i10);
    }

    public final Object acquire(d<? super t0> dVar) {
        Object objA = this.delegate.a(dVar);
        return objA == a.f15033i ? objA : t0.f22605a;
    }

    @e
    public final Object enter(d<? super t0> dVar) {
        Object objA = this.delegate.a(dVar);
        return objA == a.f15033i ? objA : t0.f22605a;
    }

    public final int getLimit() {
        return this.limit;
    }

    @e
    public final void leave() {
        this.delegate.release();
    }

    public final void release() {
        this.delegate.release();
    }
}
