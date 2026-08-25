package a9;

/* JADX INFO: loaded from: classes5.dex */
public enum x implements kotlin.reflect.jvm.internal.impl.protobuf.v {
    AT_MOST_ONCE(0),
    EXACTLY_ONCE(1),
    AT_LEAST_ONCE(2);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f727i;

    x(int i10) {
        this.f727i = i10;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.v
    public final int getNumber() {
        return this.f727i;
    }
}
