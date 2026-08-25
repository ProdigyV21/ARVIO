package kotlin.collections;

import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public abstract class i0 extends a.a {
    public static z6.f o0(Map map) {
        return ((z6.f) map).c();
    }

    public static z6.f p0() {
        return new z6.f();
    }

    public static int q0(int i10) {
        if (i10 < 0) {
            return i10;
        }
        if (i10 < 3) {
            return i10 + 1;
        }
        if (i10 < 1073741824) {
            return (int) ((i10 / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static Map r0(x6.x xVar) {
        return Collections.singletonMap(xVar.f22608i, xVar.f22609l);
    }
}
