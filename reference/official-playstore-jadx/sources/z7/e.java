package z7;

import a9.i0;
import kotlin.jvm.internal.l;
import kotlin.jvm.internal.l0;
import kotlin.reflect.f;
import r7.p;
import s9.x;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class e extends l implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final e f23243i = new e(2);

    @Override // kotlin.jvm.internal.e, kotlin.reflect.c
    public final String getName() {
        return "loadFunction";
    }

    @Override // kotlin.jvm.internal.e
    public final f getOwner() {
        return l0.f19747a.b(x.class);
    }

    @Override // kotlin.jvm.internal.e
    public final String getSignature() {
        return "loadFunction(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;)Lorg/jetbrains/kotlin/descriptors/SimpleFunctionDescriptor;";
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((x) obj).e((i0) obj2);
    }
}
