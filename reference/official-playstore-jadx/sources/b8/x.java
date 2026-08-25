package b8;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class x implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Method f7249a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f7250b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Class f7251c;

    public x(Method method, List list) {
        this.f7249a = method;
        this.f7250b = list;
        this.f7251c = method.getReturnType();
    }

    @Override // b8.e
    public final List a() {
        return this.f7250b;
    }

    @Override // b8.e
    public final /* bridge */ /* synthetic */ Member b() {
        return null;
    }

    @Override // b8.e
    public final Type getReturnType() {
        return this.f7251c;
    }
}
