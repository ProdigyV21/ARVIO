package pa;

import d7.j;
import ka.q2;

/* JADX INFO: loaded from: classes5.dex */
public final class z extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final z f21301i = new z(2);

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        j.a aVar = (j.a) obj2;
        if (!(aVar instanceof q2)) {
            return obj;
        }
        Integer num = obj instanceof Integer ? (Integer) obj : null;
        int iIntValue = num != null ? num.intValue() : 1;
        return iIntValue == 0 ? aVar : Integer.valueOf(iIntValue + 1);
    }
}
