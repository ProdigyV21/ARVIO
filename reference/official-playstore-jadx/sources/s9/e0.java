package s9;

import kotlin.jvm.internal.l0;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class e0 extends kotlin.jvm.internal.l implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final e0 f21689i = new e0(1);

    @Override // kotlin.jvm.internal.e, kotlin.reflect.c
    public final String getName() {
        return "getOuterClassId";
    }

    @Override // kotlin.jvm.internal.e
    public final kotlin.reflect.f getOwner() {
        return l0.f19747a.b(f9.b.class);
    }

    @Override // kotlin.jvm.internal.e
    public final String getSignature() {
        return "getOuterClassId()Lorg/jetbrains/kotlin/name/ClassId;";
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        return ((f9.b) obj).f();
    }
}
