package a9;

/* JADX INFO: loaded from: classes5.dex */
public enum a2 implements kotlin.reflect.jvm.internal.impl.protobuf.v {
    WARNING(0),
    ERROR(1),
    HIDDEN(2);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f332i;

    a2(int i10) {
        this.f332i = i10;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.v
    public final int getNumber() {
        return this.f332i;
    }
}
