package g7;

import java.io.Serializable;
import java.util.RandomAccess;
import kotlin.collections.c;
import kotlin.collections.f;
import kotlin.collections.r;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends f implements a, RandomAccess, Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Enum[] f15494i;

    public b(Enum[] enumArr) {
        this.f15494i = enumArr;
    }

    @Override // kotlin.collections.a, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (!(obj instanceof Enum)) {
            return false;
        }
        Enum r4 = (Enum) obj;
        return ((Enum) r.c0(r4.ordinal(), this.f15494i)) == r4;
    }

    @Override // kotlin.collections.f, java.util.List
    public final Object get(int i10) {
        c cVar = f.Companion;
        Enum[] enumArr = this.f15494i;
        int length = enumArr.length;
        cVar.getClass();
        c.b(i10, length);
        return enumArr[i10];
    }

    @Override // kotlin.collections.f, kotlin.collections.a
    /* JADX INFO: renamed from: getSize */
    public final int get_size() {
        return this.f15494i.length;
    }

    @Override // kotlin.collections.f, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r4 = (Enum) obj;
        int iOrdinal = r4.ordinal();
        if (((Enum) r.c0(iOrdinal, this.f15494i)) == r4) {
            return iOrdinal;
        }
        return -1;
    }

    @Override // kotlin.collections.f, java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof Enum)) {
            return -1;
        }
        Enum r4 = (Enum) obj;
        int iOrdinal = r4.ordinal();
        if (((Enum) r.c0(iOrdinal, this.f15494i)) == r4) {
            return iOrdinal;
        }
        return -1;
    }
}
