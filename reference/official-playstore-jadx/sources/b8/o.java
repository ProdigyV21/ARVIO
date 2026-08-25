package b8;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import kotlin.reflect.b0;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class o extends u {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f7238e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f7239f;

    public /* synthetic */ o(Method method, boolean z, int i10) {
        this(method, (i10 & 2) != 0 ? !Modifier.isStatic(method.getModifiers()) : z, method.getGenericParameterTypes());
    }

    @Override // b8.u
    public void c(Object[] objArr) {
        switch (this.f7238e) {
            case 0:
                b0.f(this, objArr);
                if (this.f7239f && kotlin.collections.r.g0(objArr) == null) {
                    throw new IllegalArgumentException("null is not allowed as a value for this property.");
                }
                return;
            default:
                super.c(objArr);
                return;
        }
    }

    @Override // b8.e
    public Object call(Object[] objArr) throws IllegalAccessException {
        c(objArr);
        ((Field) this.f7244a).set(this.f7246c != null ? kotlin.collections.r.Y(objArr) : null, kotlin.collections.r.g0(objArr));
        return t0.f22605a;
    }

    public Object e(Object[] objArr, Object obj) {
        return this.f7239f ? t0.f22605a : ((Method) this.f7244a).invoke(obj, Arrays.copyOf(objArr, objArr.length));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public o(Method method, boolean z, Type[] typeArr) {
        Type genericReturnType = method.getGenericReturnType();
        super(method, genericReturnType, z ? method.getDeclaringClass() : null, typeArr);
        this.f7239f = kotlin.jvm.internal.p.a(genericReturnType, Void.TYPE);
    }

    public o(Field field, boolean z, boolean z5) {
        super(field, Void.TYPE, z5 ? field.getDeclaringClass() : null, new Type[]{field.getGenericType()});
        this.f7239f = z;
    }
}
