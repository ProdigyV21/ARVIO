package androidx.navigation.compose;

import androidx.compose.runtime.State;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class b0 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4560i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ State f4561l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b0(State state, int i10) {
        super(0);
        this.f4560i = i10;
        this.f4561l = state;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f4560i) {
            case 0:
                List list = (List) this.f4561l.getValue();
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    if (kotlin.jvm.internal.p.a(((androidx.navigation.i) obj).f4681l.f4657i, "composable")) {
                        arrayList.add(obj);
                    }
                }
                return arrayList;
            case 1:
                return new androidx.tv.foundation.lazy.grid.l((r7.l) this.f4561l.getValue());
            default:
                return new androidx.tv.foundation.lazy.list.a0((r7.l) this.f4561l.getValue());
        }
    }
}
