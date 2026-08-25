package androidx.tv.foundation.lazy.grid;

import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.layout.RemeasurementModifier;

/* JADX INFO: loaded from: classes3.dex */
public final class y0 implements RemeasurementModifier {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5340i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ScrollableState f5341l;

    public /* synthetic */ y0(ScrollableState scrollableState, int i10) {
        this.f5340i = i10;
        this.f5341l = scrollableState;
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ boolean all(r7.l lVar) {
        int i10 = this.f5340i;
        return androidx.compose.ui.b.a(this, lVar);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ boolean any(r7.l lVar) {
        int i10 = this.f5340i;
        return androidx.compose.ui.b.b(this, lVar);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ Object foldIn(Object obj, r7.p pVar) {
        int i10 = this.f5340i;
        return androidx.compose.ui.b.c(this, obj, pVar);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ Object foldOut(Object obj, r7.p pVar) {
        int i10 = this.f5340i;
        return androidx.compose.ui.b.d(this, obj, pVar);
    }

    @Override // androidx.compose.ui.layout.RemeasurementModifier
    public final void onRemeasurementAvailable(Remeasurement remeasurement) {
        switch (this.f5340i) {
            case 0:
                ((b1) this.f5341l).f5165n = remeasurement;
                break;
            default:
                ((androidx.tv.foundation.lazy.list.m0) this.f5341l).f5439n = remeasurement;
                break;
        }
    }

    @Override // androidx.compose.ui.Modifier
    public final /* synthetic */ Modifier then(Modifier modifier) {
        int i10 = this.f5340i;
        return androidx.compose.ui.a.a(this, modifier);
    }
}
