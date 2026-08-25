package androidx.compose.ui.platform;

import android.view.Choreographer;
import androidx.compose.runtime.MonotonicFrameClock;
import d7.j;
import kotlin.Metadata;
import x6.c0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\bJ*\u0010\r\u001a\u00028\u0000\"\u0004\b\u0000\u0010\t2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u00000\nH\u0096@¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/compose/ui/platform/AndroidUiFrameClock;", "Landroidx/compose/runtime/MonotonicFrameClock;", "Landroid/view/Choreographer;", "choreographer", "Landroidx/compose/ui/platform/AndroidUiDispatcher;", "dispatcher", "<init>", "(Landroid/view/Choreographer;Landroidx/compose/ui/platform/AndroidUiDispatcher;)V", "(Landroid/view/Choreographer;)V", "R", "Lkotlin/Function1;", "", "onFrame", "withFrameNanos", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "Landroid/view/Choreographer;", "getChoreographer", "()Landroid/view/Choreographer;", "Landroidx/compose/ui/platform/AndroidUiDispatcher;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidUiFrameClock implements MonotonicFrameClock {
    public static final int $stable = 8;
    private final Choreographer choreographer;
    private final AndroidUiDispatcher dispatcher;

    public AndroidUiFrameClock(Choreographer choreographer, AndroidUiDispatcher androidUiDispatcher) {
        this.choreographer = choreographer;
        this.dispatcher = androidUiDispatcher;
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j
    public <R> R fold(R r4, r7.p<? super R, ? super j.a, ? extends R> pVar) {
        return (R) MonotonicFrameClock.DefaultImpls.fold(this, r4, pVar);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j
    public <E extends j.a> E get(j.b<E> bVar) {
        return (E) MonotonicFrameClock.DefaultImpls.get(this, bVar);
    }

    public final Choreographer getChoreographer() {
        return this.choreographer;
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, d7.j.a
    public final /* synthetic */ j.b getKey() {
        return androidx.compose.runtime.h.a(this);
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
        AndroidUiDispatcher androidUiDispatcher = this.dispatcher;
        if (androidUiDispatcher == null) {
            j.a aVar = dVar.getContext().get(d7.f.f14687i);
            androidUiDispatcher = aVar instanceof AndroidUiDispatcher ? (AndroidUiDispatcher) aVar : null;
        }
        final ka.l lVar2 = new ka.l(1, t7.a.A(dVar));
        lVar2.s();
        Choreographer.FrameCallback frameCallback = new Choreographer.FrameCallback() { // from class: androidx.compose.ui.platform.AndroidUiFrameClock$withFrameNanos$2$callback$1
            @Override // android.view.Choreographer.FrameCallback
            public final void doFrame(long j10) {
                Object c0Var;
                d7.d dVar2 = lVar2;
                try {
                    c0Var = lVar.invoke(Long.valueOf(j10));
                } catch (Throwable th) {
                    c0Var = new c0(th);
                }
                dVar2.resumeWith(c0Var);
            }
        };
        if (androidUiDispatcher == null || !kotlin.jvm.internal.p.a(androidUiDispatcher.getChoreographer(), getChoreographer())) {
            getChoreographer().postFrameCallback(frameCallback);
            lVar2.c(new AndroidUiFrameClock$withFrameNanos$2$2(this, frameCallback));
        } else {
            androidUiDispatcher.postFrameCallback$ui_release(frameCallback);
            lVar2.c(new AndroidUiFrameClock$withFrameNanos$2$1(androidUiDispatcher, frameCallback));
        }
        return lVar2.r();
    }

    public AndroidUiFrameClock(Choreographer choreographer) {
        this(choreographer, null);
    }
}
