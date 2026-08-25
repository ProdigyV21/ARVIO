package androidx.compose.foundation;

import androidx.compose.foundation.MarqueeSpacing;
import androidx.compose.ui.unit.Density;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements MarqueeSpacing {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1875a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ float f1876b;

    public /* synthetic */ a(float f10, int i10) {
        this.f1875a = i10;
        this.f1876b = f10;
    }

    @Override // androidx.compose.foundation.MarqueeSpacing
    public final int calculateSpacing(Density density, int i10, int i11) {
        switch (this.f1875a) {
            case 0:
                return BasicMarqueeKt.MarqueeSpacing_0680j_4$lambda$1(this.f1876b, density, i10, i11);
            default:
                return MarqueeSpacing.Companion.fractionOfContainer$lambda$0(this.f1876b, density, i10, i11);
        }
    }
}
