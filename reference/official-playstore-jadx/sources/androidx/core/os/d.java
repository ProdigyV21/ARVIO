package androidx.core.os;

import android.os.Bundle;
import android.util.Size;
import android.util.SizeF;

/* JADX INFO: loaded from: classes3.dex */
public abstract class d {
    @q7.n
    public static final void a(Bundle bundle, String str, Size size) {
        bundle.putSize(str, size);
    }

    @q7.n
    public static final void b(Bundle bundle, String str, SizeF sizeF) {
        bundle.putSizeF(str, sizeF);
    }
}
