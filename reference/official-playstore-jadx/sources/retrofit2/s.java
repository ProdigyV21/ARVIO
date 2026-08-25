package retrofit2;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Method f21580a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f21581b;

    public s(Method method, ArrayList arrayList) {
        this.f21580a = method;
        this.f21581b = Collections.unmodifiableList(arrayList);
    }

    public final String toString() {
        Method method = this.f21580a;
        return String.format("%s.%s() %s", method.getDeclaringClass().getName(), method.getName(), this.f21581b);
    }
}
