package androidx.tv.material3;

import androidx.compose.runtime.State;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;

/* JADX INFO: loaded from: classes3.dex */
public final class j2 extends kotlin.jvm.internal.r implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ float f6134i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ State f6135l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j2(float f10, State state) {
        super(3);
        this.f6134i = f10;
        this.f6135l = state;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        MeasureScope measureScope = (MeasureScope) obj;
        long value = ((Constraints) obj3).getValue();
        int iMo279roundToPx0680j_4 = measureScope.mo279roundToPx0680j_4(((Dp) this.f6135l.getValue()).m5692unboximpl());
        int iMo279roundToPx0680j_42 = measureScope.mo279roundToPx0680j_4(this.f6134i);
        Placeable placeableMo4631measureBRTryo0 = ((Measurable) obj2).mo4631measureBRTryo0(Constraints.m5636copyZbe2FdA(value, iMo279roundToPx0680j_4, iMo279roundToPx0680j_4, iMo279roundToPx0680j_42, iMo279roundToPx0680j_42));
        return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new i2(placeableMo4631measureBRTryo0), 4, null);
    }
}
