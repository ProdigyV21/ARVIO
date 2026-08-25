package androidx.navigation.compose;

import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.State;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class k implements DisposableEffectResult {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4579a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f4580b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f4581c;

    public /* synthetic */ k(Object obj, Object obj2, int i10) {
        this.f4579a = i10;
        this.f4580b = obj;
        this.f4581c = obj2;
    }

    @Override // androidx.compose.runtime.DisposableEffectResult
    public final void dispose() {
        switch (this.f4579a) {
            case 0:
                ((androidx.navigation.i) this.f4580b).f4687r.removeObserver((l) this.f4581c);
                break;
            case 1:
                Iterator it = ((List) ((State) this.f4580b).getValue()).iterator();
                while (it.hasNext()) {
                    ((f) this.f4581c).b().b((androidx.navigation.i) it.next());
                }
                break;
            default:
                ((androidx.lifecycle.y) this.f4580b).getLifecycle().removeObserver((androidx.activity.i) this.f4581c);
                break;
        }
    }
}
