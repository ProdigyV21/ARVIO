package m9;

import g8.e1;
import kotlin.jvm.internal.l;
import kotlin.jvm.internal.l0;
import kotlin.reflect.f;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class b extends l implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final b f20300i = new b(1);

    @Override // kotlin.jvm.internal.e, kotlin.reflect.c
    public final String getName() {
        return "declaresDefaultValue";
    }

    @Override // kotlin.jvm.internal.e
    public final f getOwner() {
        return l0.f19747a.b(e1.class);
    }

    @Override // kotlin.jvm.internal.e
    public final String getSignature() {
        return "declaresDefaultValue()Z";
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        return Boolean.valueOf(((e1) obj).w0());
    }
}
