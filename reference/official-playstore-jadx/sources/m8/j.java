package m8;

import java.lang.reflect.Member;
import kotlin.jvm.internal.l0;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class j extends kotlin.jvm.internal.l implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final j f20283i = new j(1);

    @Override // kotlin.jvm.internal.e, kotlin.reflect.c
    public final String getName() {
        return "isSynthetic";
    }

    @Override // kotlin.jvm.internal.e
    public final kotlin.reflect.f getOwner() {
        return l0.f19747a.b(Member.class);
    }

    @Override // kotlin.jvm.internal.e
    public final String getSignature() {
        return "isSynthetic()Z";
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        return Boolean.valueOf(((Member) obj).isSynthetic());
    }
}
