package retrofit2;

import android.os.Build;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes5.dex */
public class n0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final n0 f21524c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f21525a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Constructor f21526b;

    static {
        n0 n0Var;
        if ("Dalvik".equals(System.getProperty("java.vm.name"))) {
            n0Var = new m0(Build.VERSION.SDK_INT >= 24);
        } else {
            n0Var = new n0(true);
        }
        f21524c = n0Var;
    }

    public n0(boolean z) {
        this.f21525a = z;
        Constructor declaredConstructor = null;
        if (z) {
            try {
                declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
                declaredConstructor.setAccessible(true);
            } catch (NoClassDefFoundError | NoSuchMethodException unused) {
            }
        }
        this.f21526b = declaredConstructor;
    }

    public Executor a() {
        return null;
    }

    public Object b(Method method, Class cls, Object obj, Object... objArr) {
        Constructor constructor = this.f21526b;
        return (constructor != null ? (MethodHandles.Lookup) constructor.newInstance(cls, -1) : MethodHandles.lookup()).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
    }

    public final boolean c(Method method) {
        return this.f21525a && method.isDefault();
    }
}
