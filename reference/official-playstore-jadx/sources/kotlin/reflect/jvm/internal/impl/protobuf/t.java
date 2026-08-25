package kotlin.reflect.jvm.internal.impl.protobuf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes5.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c0 f19824a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f19825b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c0 f19826c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s f19827d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f19828e;

    public t(c0 c0Var, Object obj, c0 c0Var2, s sVar, Class cls) {
        if (c0Var == null) {
            throw new IllegalArgumentException("Null containingTypeDefaultInstance");
        }
        if (sVar.f19822l == u0.f19832p && c0Var2 == null) {
            throw new IllegalArgumentException("Null messageDefaultInstance");
        }
        this.f19824a = c0Var;
        this.f19825b = obj;
        this.f19826c = c0Var2;
        this.f19827d = sVar;
        if (!v.class.isAssignableFrom(cls)) {
            this.f19828e = null;
            return;
        }
        try {
            this.f19828e = cls.getMethod("valueOf", Integer.TYPE);
        } catch (NoSuchMethodException e5) {
            String name = cls.getName();
            throw new RuntimeException(androidx.compose.material3.d.q(new StringBuilder(name.length() + 52), "Generated message class \"", name, "\" missing method \"valueOf\"."), e5);
        }
    }

    public final Object a(Object obj) {
        if (this.f19827d.f19822l.f19835i != v0.f19844s) {
            return obj;
        }
        try {
            return this.f19828e.invoke(null, (Integer) obj);
        } catch (IllegalAccessException e5) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e5);
        } catch (InvocationTargetException e6) {
            Throwable cause = e6.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    public final Object b(Object obj) {
        return this.f19827d.f19822l.f19835i == v0.f19844s ? Integer.valueOf(((v) obj).getNumber()) : obj;
    }
}
