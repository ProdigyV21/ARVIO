package a9;

/* JADX INFO: loaded from: classes5.dex */
public enum h2 implements kotlin.reflect.jvm.internal.impl.protobuf.v {
    /* JADX INFO: Fake field, exist only in values array */
    INTERNAL(0),
    /* JADX INFO: Fake field, exist only in values array */
    PRIVATE(1),
    /* JADX INFO: Fake field, exist only in values array */
    PROTECTED(2),
    /* JADX INFO: Fake field, exist only in values array */
    PUBLIC(3),
    /* JADX INFO: Fake field, exist only in values array */
    PRIVATE_TO_THIS(4),
    /* JADX INFO: Fake field, exist only in values array */
    LOCAL(5);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f481i;

    h2(int i10) {
        this.f481i = i10;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.v
    public final int getNumber() {
        return this.f481i;
    }
}
