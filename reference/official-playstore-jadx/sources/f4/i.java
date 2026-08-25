package f4;

import android.content.Context;
import f4.b;

/* JADX INFO: loaded from: classes4.dex */
public final class i implements b {
    @Override // f4.b
    public final c a(Context context, b.a aVar) {
        c cVar = new c();
        cVar.f15251a = 0;
        cVar.f15252b = 0;
        cVar.f15253c = 0;
        int iA = aVar.a(context, true);
        cVar.f15252b = iA;
        if (iA != 0) {
            cVar.f15253c = 1;
            return cVar;
        }
        int iB = aVar.b(context);
        cVar.f15251a = iB;
        if (iB != 0) {
            cVar.f15253c = -1;
        }
        return cVar;
    }
}
