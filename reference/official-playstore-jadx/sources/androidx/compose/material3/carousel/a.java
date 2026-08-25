package androidx.compose.material3.carousel;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a {
    public static /* synthetic */ void a(KeylineListScope keylineListScope, float f10, boolean z, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: add");
        }
        if ((i10 & 2) != 0) {
            z = false;
        }
        keylineListScope.add(f10, z);
    }
}
