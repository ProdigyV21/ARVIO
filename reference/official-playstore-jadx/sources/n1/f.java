package n1;

import androidx.lifecycle.p;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f20457a;

    static {
        int[] iArr = new int[p.values().length];
        try {
            iArr[p.ON_RESUME.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[p.ON_PAUSE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        f20457a = iArr;
    }
}
