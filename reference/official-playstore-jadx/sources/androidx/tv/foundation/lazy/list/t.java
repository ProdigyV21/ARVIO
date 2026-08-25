package androidx.tv.foundation.lazy.list;

import androidx.compose.ui.layout.Placeable;
import java.util.ArrayList;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class t extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ ArrayList f5491i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ x f5492l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(ArrayList arrayList, x xVar, boolean z) {
        super(1);
        this.f5491i = arrayList;
        this.f5492l = xVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        x xVar;
        Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
        ArrayList arrayList = this.f5491i;
        int size = arrayList.size();
        int i10 = 0;
        while (true) {
            xVar = this.f5492l;
            if (i10 >= size) {
                break;
            }
            x xVar2 = (x) arrayList.get(i10);
            if (xVar2 != xVar) {
                xVar2.b(placementScope);
            }
            i10++;
        }
        if (xVar != null) {
            xVar.b(placementScope);
        }
        return t0.f22605a;
    }
}
