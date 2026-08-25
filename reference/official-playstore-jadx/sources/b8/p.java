package b8;

import java.lang.reflect.Method;
import kotlin.reflect.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class p extends o implements d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f7240g;

    public p(Method method, Object obj) {
        super(method, false, 4);
        this.f7240g = obj;
    }

    @Override // b8.o, b8.e
    public final Object call(Object[] objArr) {
        b0.f(this, objArr);
        return e(objArr, this.f7240g);
    }
}
