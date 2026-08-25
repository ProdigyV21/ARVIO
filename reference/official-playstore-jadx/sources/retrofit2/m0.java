package retrofit2;

import android.os.Build;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes5.dex */
public final class m0 extends n0 {
    @Override // retrofit2.n0
    public final Executor a() {
        return new com.google.android.gms.common.api.internal.h0();
    }

    @Override // retrofit2.n0
    public final Object b(Method method, Class cls, Object obj, Object... objArr) {
        if (Build.VERSION.SDK_INT >= 26) {
            return super.b(method, cls, obj, objArr);
        }
        throw new UnsupportedOperationException("Calling default methods on API 24 and 25 is not supported");
    }
}
