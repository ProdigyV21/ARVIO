package androidx.tv.foundation.lazy.grid;

import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class u extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ k0 f5305i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ x f5306l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(k0 k0Var, x xVar) {
        super(1);
        this.f5305i = k0Var;
        this.f5306l = xVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        androidx.core.provider.j jVarB = this.f5305i.b(((Number) obj).intValue());
        int i10 = jVarB.f2159b;
        List list = (List) jVarB.f2160c;
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        int i11 = 0;
        for (int i12 = 0; i12 < size; i12++) {
            int i13 = (int) ((o0) list.get(i12)).f5283a;
            arrayList.add(new x6.x(Integer.valueOf(i10), Constraints.m5634boximpl(this.f5306l.a(i11, i13))));
            i10++;
            i11 += i13;
        }
        return arrayList;
    }
}
