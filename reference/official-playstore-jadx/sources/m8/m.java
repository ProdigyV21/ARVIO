package m8;

import java.lang.reflect.Field;
import kotlin.jvm.internal.l0;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class m extends kotlin.jvm.internal.l implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final m f20286i = new m(1);

    @Override // kotlin.jvm.internal.e, kotlin.reflect.c
    public final String getName() {
        return "<init>";
    }

    @Override // kotlin.jvm.internal.e
    public final kotlin.reflect.f getOwner() {
        return l0.f19747a.b(u.class);
    }

    @Override // kotlin.jvm.internal.e
    public final String getSignature() {
        return "<init>(Ljava/lang/reflect/Field;)V";
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        return new u((Field) obj);
    }
}
