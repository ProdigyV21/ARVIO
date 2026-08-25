package a8;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class d0 extends kotlin.jvm.internal.l implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final d0 f178i = new d0(2);

    @Override // kotlin.jvm.internal.e, kotlin.reflect.c
    public final String getName() {
        return "loadProperty";
    }

    @Override // kotlin.jvm.internal.e
    public final kotlin.reflect.f getOwner() {
        return kotlin.jvm.internal.l0.f19747a.b(s9.x.class);
    }

    @Override // kotlin.jvm.internal.e
    public final String getSignature() {
        return "loadProperty(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;)Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;";
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((s9.x) obj).f((a9.t0) obj2);
    }
}
