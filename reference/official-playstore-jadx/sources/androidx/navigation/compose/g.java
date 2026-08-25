package androidx.navigation.compose;

import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.core.view.d0;
import kotlin.jvm.internal.k0;

/* JADX INFO: loaded from: classes3.dex */
public final class g implements DisposableEffectResult {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f4565b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f4566c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f4567d;

    public /* synthetic */ g(Object obj, Object obj2, Object obj3, int i10) {
        this.f4564a = i10;
        this.f4565b = obj;
        this.f4566c = obj2;
        this.f4567d = obj3;
    }

    @Override // androidx.compose.runtime.DisposableEffectResult
    public final void dispose() {
        switch (this.f4564a) {
            case 0:
                p pVar = (p) this.f4565b;
                androidx.navigation.i iVar = (androidx.navigation.i) this.f4566c;
                pVar.b().b(iVar);
                ((SnapshotStateList) this.f4567d).remove(iVar);
                break;
            case 1:
                ((androidx.lifecycle.y) this.f4565b).getLifecycle().removeObserver((d0) this.f4566c);
                n1.h hVar = (n1.h) ((k0) this.f4567d).f19746i;
                if (hVar != null) {
                    hVar.a();
                }
                break;
            default:
                ((androidx.lifecycle.y) this.f4565b).getLifecycle().removeObserver((d0) this.f4566c);
                n1.k kVar = (n1.k) ((k0) this.f4567d).f19746i;
                if (kVar != null) {
                    kVar.a();
                }
                break;
        }
    }
}
