package androidx.tv.foundation.lazy.grid;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class a0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ ArrayList f5143i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a0(ArrayList arrayList) {
        super(1);
        this.f5143i = arrayList;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        int i10;
        int i11;
        Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
        ArrayList arrayList = this.f5143i;
        int size = arrayList.size();
        for (int i12 = 0; i12 < size; i12++) {
            c0 c0Var = (c0) arrayList.get(i12);
            List list = c0Var.f5181g;
            boolean z = c0Var.f5177c;
            if (c0Var.f5185l == Integer.MIN_VALUE) {
                throw new IllegalArgumentException("position() should be called first");
            }
            int size2 = list.size();
            int i13 = 0;
            while (i13 < size2) {
                Placeable placeable = (Placeable) list.get(i13);
                if (z) {
                    placeable.getHeight();
                } else {
                    placeable.getWidth();
                }
                long jIntOffset = c0Var.f5186m;
                c0Var.a(i13);
                if (c0Var.f5179e) {
                    int iM5802getXimpl = IntOffset.m5802getXimpl(jIntOffset);
                    if (!z) {
                        iM5802getXimpl = (c0Var.f5185l - iM5802getXimpl) - (z ? placeable.getHeight() : placeable.getWidth());
                    }
                    jIntOffset = IntOffsetKt.IntOffset(iM5802getXimpl, z ? (c0Var.f5185l - IntOffset.m5803getYimpl(jIntOffset)) - (z ? placeable.getHeight() : placeable.getWidth()) : IntOffset.m5803getYimpl(jIntOffset));
                }
                long j10 = c0Var.f5182h;
                long jC = a0.c.c(IntOffset.m5803getYimpl(jIntOffset), IntOffset.m5802getXimpl(j10) + IntOffset.m5802getXimpl(jIntOffset), j10);
                if (z) {
                    i11 = i13;
                    i10 = size2;
                    Placeable.PlacementScope.m4672placeWithLayeraW9wM$default(placementScope, placeable, jC, 0.0f, null, 6, null);
                } else {
                    i10 = size2;
                    i11 = i13;
                    Placeable.PlacementScope.m4671placeRelativeWithLayeraW9wM$default(placementScope, placeable, jC, 0.0f, null, 6, null);
                }
                i13 = i11 + 1;
                size2 = i10;
            }
        }
        return x6.t0.f22605a;
    }
}
