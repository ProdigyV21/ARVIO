package b8;

import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import kotlin.reflect.b0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class u implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Member f7244a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Type f7245b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Class f7246c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f7247d;

    public u(Member member, Type type, Class cls, Type[] typeArr) {
        List listO0;
        this.f7244a = member;
        this.f7245b = type;
        this.f7246c = cls;
        if (cls != null) {
            com.squareup.moshi.w wVar = new com.squareup.moshi.w(2);
            wVar.a(cls);
            wVar.b(typeArr);
            ArrayList arrayList = wVar.f14489a;
            listO0 = t7.a.E(arrayList.toArray(new Type[arrayList.size()]));
        } else {
            listO0 = kotlin.collections.r.o0(typeArr);
        }
        this.f7247d = listO0;
    }

    @Override // b8.e
    public final List a() {
        return this.f7247d;
    }

    @Override // b8.e
    public final Member b() {
        return this.f7244a;
    }

    public void c(Object[] objArr) {
        b0.f(this, objArr);
    }

    public final void d(Object obj) {
        if (obj == null || !this.f7244a.getDeclaringClass().isInstance(obj)) {
            throw new IllegalArgumentException("An object member requires the object instance passed as the first argument.");
        }
    }

    @Override // b8.e
    public final Type getReturnType() {
        return this.f7245b;
    }
}
