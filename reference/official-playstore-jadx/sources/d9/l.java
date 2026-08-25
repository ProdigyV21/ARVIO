package d9;

import kotlin.reflect.jvm.internal.impl.protobuf.v;

/* JADX INFO: loaded from: classes5.dex */
public enum l implements v {
    NONE(0),
    INTERNAL_TO_CLASS_ID(1),
    DESC_TO_CLASS_ID(2);


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f14841i;

    l(int i10) {
        this.f14841i = i10;
    }

    @Override // kotlin.reflect.jvm.internal.impl.protobuf.v
    public final int getNumber() {
        return this.f14841i;
    }
}
