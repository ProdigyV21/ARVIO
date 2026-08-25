package androidx.compose.runtime;

import android.view.Choreographer;
import androidx.compose.runtime.MonotonicFrameClock;
import d7.j;
import io.ktor.http.ContentDisposition;
import ka.m0;
import ka.x0;
import kotlin.Metadata;
import pa.q;
import r7.p;
import x6.c0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\u000b\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00042!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00028\u00000\u0005H\u0096@¢\u0006\u0004\b\u000b\u0010\fR\u001c\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\r0\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/compose/runtime/DefaultChoreographerFrameClock;", "Landroidx/compose/runtime/MonotonicFrameClock;", "<init>", "()V", "R", "Lkotlin/Function1;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "frameTimeNanos", "onFrame", "withFrameNanos", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "Landroid/view/Choreographer;", "kotlin.jvm.PlatformType", "choreographer", "Landroid/view/Choreographer;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class DefaultChoreographerFrameClock implements MonotonicFrameClock {
    public static final DefaultChoreographerFrameClock INSTANCE = new DefaultChoreographerFrameClock();
    private static final Choreographer choreographer;

    static {
        x0 x0Var = x0.f19652a;
        choreographer = (Choreographer) m0.s(((la.d) q.f21294a).f20077n, new DefaultChoreographerFrameClock$choreographer$1(null));
    }

    private DefaultChoreographerFrameClock() {
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j
    public <R> R fold(R r4, p<? super R, ? super j.a, ? extends R> pVar) {
        return (R) MonotonicFrameClock.DefaultImpls.fold(this, r4, pVar);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j
    public <E extends j.a> E get(j.b<E> bVar) {
        return (E) MonotonicFrameClock.DefaultImpls.get(this, bVar);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j.a
    public final /* synthetic */ j.b getKey() {
        return h.a(this);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j
    public d7.j minusKey(j.b<?> bVar) {
        return MonotonicFrameClock.DefaultImpls.minusKey(this, bVar);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j
    public d7.j plus(d7.j jVar) {
        return MonotonicFrameClock.DefaultImpls.plus(this, jVar);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock
    public <R> Object withFrameNanos(final r7.l<? super Long, ? extends R> lVar, d7.d<? super R> dVar) {
        final ka.l lVar2 = new ka.l(1, t7.a.A(dVar));
        lVar2.s();
        Choreographer.FrameCallback frameCallback = new Choreographer.FrameCallback() { // from class: androidx.compose.runtime.DefaultChoreographerFrameClock$withFrameNanos$2$callback$1
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j10) {
                Object c0Var;
                d7.d dVar2 = lVar2;
                DefaultChoreographerFrameClock defaultChoreographerFrameClock = DefaultChoreographerFrameClock.INSTANCE;
                try {
                    c0Var = lVar.invoke(Long.valueOf(j10));
                } catch (Throwable th) {
                    c0Var = new c0(th);
                }
                dVar2.resumeWith(c0Var);
            }
        };
        choreographer.postFrameCallback(frameCallback);
        lVar2.c(new DefaultChoreographerFrameClock$withFrameNanos$2$1(frameCallback));
        return lVar2.r();
    }
}
