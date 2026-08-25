package androidx.lifecycle.viewmodel.compose;

import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.j1;
import androidx.lifecycle.z0;
import kotlin.Metadata;
import p1.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u00028G¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"Landroidx/lifecycle/viewmodel/compose/LocalViewModelStoreOwner;", "", "Landroidx/lifecycle/j1;", "getCurrent", "(Landroidx/compose/runtime/Composer;I)Landroidx/lifecycle/j1;", "current", "lifecycle-viewmodel-compose_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LocalViewModelStoreOwner {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LocalViewModelStoreOwner f3301a = new LocalViewModelStoreOwner();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ProvidableCompositionLocal f3302b = CompositionLocalKt.compositionLocalOf$default(null, a.f21040i, 1, null);

    public final j1 getCurrent(Composer composer, int i10) {
        composer.startReplaceableGroup(-584162872);
        j1 j1VarF = (j1) composer.consume(f3302b);
        if (j1VarF == null) {
            j1VarF = z0.f((View) composer.consume(AndroidCompositionLocals_androidKt.getLocalView()));
        }
        composer.endReplaceableGroup();
        return j1VarF;
    }
}
