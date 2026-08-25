package androidx.compose.foundation;

import android.view.Surface;
import d7.j;
import io.ktor.http.ContentDisposition;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000A\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003JW\u0010\r\u001a\u00020\u000b*\u00020\u00042A\u0010\r\u001a=\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\b\fH\u0096\u0001¢\u0006\u0004\b\r\u0010\u000eJ-\u0010\u0010\u001a\u00020\u000b*\u00020\u00042\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b0\u000f¢\u0006\u0002\b\fH\u0096\u0001¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0015\u001a\u00020\u00128\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"androidx/compose/foundation/BaseAndroidExternalSurfaceState$dispatchSurfaceCreated$1$receiver$1", "Landroidx/compose/foundation/SurfaceCoroutineScope;", "Landroidx/compose/foundation/SurfaceScope;", "Lka/k0;", "Landroid/view/Surface;", "Lkotlin/Function3;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "width", "height", "Lx6/t0;", "Lx6/n;", "onChanged", "(Landroid/view/Surface;Lr7/q;)V", "Lkotlin/Function1;", "onDestroyed", "(Landroid/view/Surface;Lr7/l;)V", "Ld7/j;", "getCoroutineContext", "()Ld7/j;", "coroutineContext", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BaseAndroidExternalSurfaceState$dispatchSurfaceCreated$1$receiver$1 implements SurfaceCoroutineScope, SurfaceScope, k0 {
    private final /* synthetic */ BaseAndroidExternalSurfaceState $$delegate_0;
    private final /* synthetic */ k0 $$delegate_1;

    public BaseAndroidExternalSurfaceState$dispatchSurfaceCreated$1$receiver$1(BaseAndroidExternalSurfaceState baseAndroidExternalSurfaceState, k0 k0Var) {
        this.$$delegate_0 = baseAndroidExternalSurfaceState;
        this.$$delegate_1 = k0Var;
    }

    @Override // androidx.compose.foundation.SurfaceCoroutineScope, ka.k0
    public j getCoroutineContext() {
        return this.$$delegate_1.getCoroutineContext();
    }

    @Override // androidx.compose.foundation.SurfaceScope
    public void onChanged(Surface surface, q<? super Surface, ? super Integer, ? super Integer, t0> qVar) {
        this.$$delegate_0.onChanged(surface, qVar);
    }

    @Override // androidx.compose.foundation.SurfaceScope
    public void onDestroyed(Surface surface, l<? super Surface, t0> lVar) {
        this.$$delegate_0.onDestroyed(surface, lVar);
    }
}
