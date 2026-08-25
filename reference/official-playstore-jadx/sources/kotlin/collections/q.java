package kotlin.collections;

import java.util.RandomAccess;

/* JADX INFO: loaded from: classes5.dex */
public final class q extends f implements RandomAccess {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int[] f19714i;

    public q(int[] iArr) {
        this.f19714i = iArr;
    }

    @Override // kotlin.collections.a, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (!(obj instanceof Integer)) {
            return false;
        }
        return r.I(this.f19714i, ((Number) obj).intValue());
    }

    @Override // kotlin.collections.f, java.util.List
    public final Object get(int i10) {
        return Integer.valueOf(this.f19714i[i10]);
    }

    @Override // kotlin.collections.f, kotlin.collections.a
    /* JADX INFO: renamed from: getSize */
    public final int get_size() {
        return this.f19714i.length;
    }

    @Override // kotlin.collections.f, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int iIntValue = ((Number) obj).intValue();
        int[] iArr = this.f19714i;
        int length = iArr.length;
        for (int i10 = 0; i10 < length; i10++) {
            if (iIntValue == iArr[i10]) {
                return i10;
            }
        }
        return -1;
    }

    @Override // kotlin.collections.a, java.util.Collection
    public final boolean isEmpty() {
        return this.f19714i.length == 0;
    }

    @Override // kotlin.collections.f, java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int iIntValue = ((Number) obj).intValue();
        int[] iArr = this.f19714i;
        int length = iArr.length - 1;
        if (length >= 0) {
            while (true) {
                int i10 = length - 1;
                if (iIntValue == iArr[length]) {
                    return length;
                }
                if (i10 < 0) {
                    break;
                }
                length = i10;
            }
        }
        return -1;
    }
}
