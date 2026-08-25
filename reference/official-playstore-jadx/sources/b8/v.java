package b8;

import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.reflect.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class v extends x implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f7248d;

    public v(Method method, Object obj) {
        super(method, kotlin.collections.z.f19728i);
        this.f7248d = obj;
    }

    @Override // b8.e
    public final Object call(Object[] objArr) {
        b0.f(this, objArr);
        return this.f7249a.invoke(this.f7248d, Arrays.copyOf(objArr, objArr.length));
    }
}
