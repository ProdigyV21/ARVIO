package androidx.compose.foundation.lazy.layout;

import r7.l;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a {
    public static /* synthetic */ void a(IntervalList intervalList, int i10, int i11, l lVar, int i12, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: forEach");
        }
        if ((i12 & 1) != 0) {
            i10 = 0;
        }
        if ((i12 & 2) != 0) {
            i11 = intervalList.getSize() - 1;
        }
        intervalList.forEach(i10, i11, lVar);
    }
}
