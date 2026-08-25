package a9;

/* JADX INFO: loaded from: classes5.dex */
public enum m implements kotlin.reflect.jvm.internal.impl.protobuf.v {
    CLASS(0),
    INTERFACE(1),
    ENUM_CLASS(2),
    /* JADX INFO: Fake field, exist only in values array */
    ENUM_ENTRY(3),
    ANNOTATION_CLASS(4),
    /* JADX INFO: Fake field, exist only in values array */
    OBJECT(5),
    COMPANION_OBJECT(6);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f561i;

    m(int i10) {
        this.f561i = i10;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.v
    public final int getNumber() {
        return this.f561i;
    }
}
