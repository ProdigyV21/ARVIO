package v9;

import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b0 extends d1 implements y9.i, y9.k {
    @Override // v9.d1
    /* JADX INFO: renamed from: P0, reason: merged with bridge method [inline-methods] */
    public abstract b0 M0(boolean z);

    @Override // v9.d1
    /* JADX INFO: renamed from: Q0, reason: merged with bridge method [inline-methods] */
    public abstract b0 O0(k0 k0Var);

    public String toString() throws IOException {
        StringBuilder sb2 = new StringBuilder();
        Iterator<h8.b> it = getAnnotations().iterator();
        while (it.hasNext()) {
            String[] strArr = {"[", h9.h.f15909e.w(it.next(), null), "] "};
            for (int i10 = 0; i10 < 3; i10++) {
                sb2.append(strArr[i10]);
            }
        }
        sb2.append(I0());
        if (!G0().isEmpty()) {
            kotlin.collections.x.t0(G0(), sb2, ", ", "<", ">", null, 112);
        }
        if (J0()) {
            sb2.append("?");
        }
        return sb2.toString();
    }
}
