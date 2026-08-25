package c9;

import kotlin.reflect.jvm.internal.impl.protobuf.v;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v[] f7429c;

    /* JADX WARN: Illegal instructions before constructor call */
    public c(int i10, v[] vVarArr) {
        if (vVarArr == null) {
            throw new IllegalArgumentException("Argument for @NotNull parameter 'enumEntries' of kotlin/reflect/jvm/internal/impl/metadata/deserialization/Flags$EnumLiteFlagField.bitWidth must not be null");
        }
        int i11 = 1;
        int length = vVarArr.length - 1;
        if (length != 0) {
            for (int i12 = 31; i12 >= 0; i12--) {
                if (((1 << i12) & length) != 0) {
                    i11 = 1 + i12;
                }
            }
            throw new IllegalStateException("Empty enum: " + vVarArr.getClass());
        }
        super(i10, i11);
        this.f7429c = vVarArr;
    }

    public final Object c(int i10) {
        int i11 = (1 << this.f7431b) - 1;
        int i12 = this.f7430a;
        int i13 = (i10 & (i11 << i12)) >> i12;
        for (v vVar : this.f7429c) {
            if (vVar.getNumber() == i13) {
                return vVar;
            }
        }
        return null;
    }
}
