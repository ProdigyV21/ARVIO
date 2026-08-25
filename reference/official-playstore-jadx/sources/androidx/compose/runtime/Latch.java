package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0007\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0086\b¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\n\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u0003J\r\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\u0003J\u0010\u0010\f\u001a\u00020\tH\u0086@¢\u0006\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\"\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00110\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\"\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00110\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0013R\u0016\u0010\u0016\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Landroidx/compose/runtime/Latch;", "", "<init>", "()V", "R", "Lkotlin/Function0;", "block", "withClosed", "(Lr7/a;)Ljava/lang/Object;", "Lx6/t0;", "closeLatch", "openLatch", "await", "(Ld7/d;)Ljava/lang/Object;", "lock", "Ljava/lang/Object;", "", "Ld7/d;", "awaiters", "Ljava/util/List;", "spareList", "", "_isOpen", "Z", "isOpen", "()Z", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Latch {
    public static final int $stable = 8;
    private final Object lock = new Object();
    private List<d7.d<t0>> awaiters = new ArrayList();
    private List<d7.d<t0>> spareList = new ArrayList();
    private boolean _isOpen = true;

    public final Object await(d7.d<? super t0> dVar) {
        if (isOpen()) {
            return t0.f22605a;
        }
        ka.l lVar = new ka.l(1, t7.a.A(dVar));
        lVar.s();
        synchronized (this.lock) {
            this.awaiters.add(lVar);
        }
        lVar.c(new Latch$await$2$2(this, lVar));
        Object objR = lVar.r();
        return objR == e7.a.f15033i ? objR : t0.f22605a;
    }

    public final void closeLatch() {
        synchronized (this.lock) {
            this._isOpen = false;
        }
    }

    public final boolean isOpen() {
        boolean z;
        synchronized (this.lock) {
            z = this._isOpen;
        }
        return z;
    }

    public final void openLatch() {
        synchronized (this.lock) {
            try {
                if (isOpen()) {
                    return;
                }
                List<d7.d<t0>> list = this.awaiters;
                this.awaiters = this.spareList;
                this.spareList = list;
                this._isOpen = true;
                int size = list.size();
                for (int i10 = 0; i10 < size; i10++) {
                    list.get(i10).resumeWith(t0.f22605a);
                }
                list.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final <R> R withClosed(r7.a<? extends R> block) {
        closeLatch();
        try {
            return (R) block.invoke();
        } finally {
            openLatch();
        }
    }
}
