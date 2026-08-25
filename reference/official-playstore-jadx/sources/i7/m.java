package i7;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes5.dex */
public abstract class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Method f16236a;

    static {
        Method method;
        Method[] methods = Throwable.class.getMethods();
        int length = methods.length;
        int i10 = 0;
        while (true) {
            method = null;
            if (i10 >= length) {
                break;
            }
            Method method2 = methods[i10];
            if (kotlin.jvm.internal.p.a(method2.getName(), "addSuppressed")) {
                Class<?>[] parameterTypes = method2.getParameterTypes();
                if (kotlin.jvm.internal.p.a(parameterTypes.length == 1 ? parameterTypes[0] : null, Throwable.class)) {
                    method = method2;
                    break;
                }
            }
            i10++;
        }
        f16236a = method;
        int length2 = methods.length;
        for (int i11 = 0; i11 < length2 && !kotlin.jvm.internal.p.a(methods[i11].getName(), "getSuppressed"); i11++) {
        }
    }
}
