package a9;

/* JADX INFO: loaded from: classes5.dex */
public enum g1 implements kotlin.reflect.jvm.internal.impl.protobuf.v {
    IN(0),
    OUT(1),
    INV(2),
    STAR(3);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f459i;

    g1(int i10) {
        this.f459i = i10;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.v
    public final int getNumber() {
        return this.f459i;
    }
}
