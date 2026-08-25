package androidx.tv.material3;

import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;

/* JADX INFO: loaded from: classes3.dex */
public final class g4 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ float f6017i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ State f6018l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g4(float f10, State state) {
        super(1);
        this.f6017i = f10;
        this.f6018l = state;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        Density density = (Density) obj;
        TabRowDefaults tabRowDefaults = TabRowDefaults.f5765a;
        return IntOffset.m5793boximpl(IntOffsetKt.IntOffset(density.mo279roundToPx0680j_4(((Dp) this.f6018l.getValue()).m5692unboximpl()), density.mo279roundToPx0680j_4(this.f6017i)));
    }
}
