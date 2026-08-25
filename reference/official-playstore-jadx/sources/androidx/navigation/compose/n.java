package androidx.navigation.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.ui.text.TextStyle;
import androidx.tv.foundation.lazy.grid.LazyGridKt;
import androidx.tv.foundation.lazy.grid.b1;
import androidx.tv.foundation.lazy.list.LazyListKt;
import androidx.tv.foundation.lazy.list.m0;
import androidx.tv.material3.TextKt;
import java.util.Collection;
import java.util.List;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class n extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4589i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f4590l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4591m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4592n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(int i10, int i11, Object obj, Object obj2) {
        super(2);
        this.f4589i = i11;
        this.f4591m = obj;
        this.f4592n = obj2;
        this.f4590l = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f4589i;
        Composer composer = (Composer) obj;
        ((Number) obj2).intValue();
        switch (i10) {
            case 0:
                DialogHostKt.PopulateVisibleList((List) this.f4591m, (Collection) this.f4592n, composer, RecomposeScopeImplKt.updateChangedFlags(this.f4590l | 1));
                break;
            case 1:
                NavBackStackEntryProviderKt.SaveableStateProvider((SaveableStateHolder) this.f4591m, (r7.p) this.f4592n, composer, RecomposeScopeImplKt.updateChangedFlags(this.f4590l | 1));
                break;
            case 2:
                LazyGridKt.ScrollPositionUpdater((r7.a) this.f4591m, (b1) this.f4592n, composer, RecomposeScopeImplKt.updateChangedFlags(this.f4590l | 1));
                break;
            case 3:
                LazyListKt.ScrollPositionUpdater((r7.a) this.f4591m, (m0) this.f4592n, composer, RecomposeScopeImplKt.updateChangedFlags(this.f4590l | 1));
                break;
            default:
                TextKt.ProvideTextStyle((TextStyle) this.f4591m, (r7.p) this.f4592n, composer, RecomposeScopeImplKt.updateChangedFlags(this.f4590l | 1));
                break;
        }
        return t0.f22605a;
    }
}
