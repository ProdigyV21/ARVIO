package b8;

import java.util.Arrays;
import kotlin.reflect.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class w extends x {
    @Override // b8.e
    public final Object call(Object[] objArr) {
        b0.f(this, objArr);
        Object obj = objArr[0];
        Object[] objArrS = objArr.length <= 1 ? new Object[0] : kotlin.collections.r.S(objArr, 1, objArr.length);
        return this.f7249a.invoke(obj, Arrays.copyOf(objArrS, objArrS.length));
    }
}
