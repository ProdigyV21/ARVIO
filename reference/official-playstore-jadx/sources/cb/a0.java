package cb;

import java.util.Arrays;
import kotlinx.serialization.SerializationException;

/* JADX INFO: loaded from: classes5.dex */
public final class a0 implements ya.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Enum[] f7457a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final x6.i0 f7458b;

    public a0(String str, Enum[] enumArr) {
        this.f7457a = enumArr;
        this.f7458b = new x6.i0(new a8.l0(this, str, 8));
    }

    @Override // ya.c
    public final Object deserialize(bb.d dVar) {
        int iZ = dVar.z(getDescriptor());
        Enum[] enumArr = this.f7457a;
        if (iZ >= 0 && iZ < enumArr.length) {
            return enumArr[iZ];
        }
        throw new SerializationException(iZ + " is not among valid " + getDescriptor().h() + " enum values, values size is " + enumArr.length);
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        return (ab.g) this.f7458b.getValue();
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        Enum r52 = (Enum) obj;
        Enum[] enumArr = this.f7457a;
        int iD0 = kotlin.collections.r.d0(enumArr, r52);
        if (iD0 != -1) {
            eVar.i(getDescriptor(), iD0);
            return;
        }
        throw new SerializationException(r52 + " is not a valid enum " + getDescriptor().h() + ", must be one of " + Arrays.toString(enumArr));
    }

    public final String toString() {
        return "kotlinx.serialization.internal.EnumSerializer<" + getDescriptor().h() + '>';
    }
}
