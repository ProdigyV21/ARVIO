package kotlin.reflect.jvm.internal.impl.protobuf;

import kotlin.reflect.jvm.internal.impl.protobuf.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class s implements m {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f19821i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final u0 f19822l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f19823m;

    public s(int i10, u0 u0Var, boolean z) {
        this.f19821i = i10;
        this.f19822l = u0Var;
        this.f19823m = z;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.m
    public final boolean b() {
        return this.f19823m;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.m
    public final u0 c() {
        return this.f19822l;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f19821i - ((s) obj).f19821i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.m
    public final o e(c0.a aVar, c0 c0Var) {
        return ((o) aVar).f((u) c0Var);
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.m
    public final v0 g() {
        return this.f19822l.f19835i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.m
    public final int getNumber() {
        return this.f19821i;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.m
    public final boolean h() {
        return false;
    }
}
