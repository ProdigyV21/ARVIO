package a9;

/* JADX INFO: loaded from: classes5.dex */
public enum w implements kotlin.reflect.jvm.internal.impl.protobuf.v {
    RETURNS_CONSTANT(0),
    CALLS(1),
    RETURNS_NOT_NULL(2);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f711i;

    w(int i10) {
        this.f711i = i10;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.v
    public final int getNumber() {
        return this.f711i;
    }
}
