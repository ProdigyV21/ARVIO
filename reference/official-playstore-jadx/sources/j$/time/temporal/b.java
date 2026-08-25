package j$.time.temporal;

/* JADX INFO: loaded from: classes2.dex */
public abstract /* synthetic */ class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f18350a;

    static {
        int[] iArr = new int[h.values().length];
        f18350a = iArr;
        try {
            iArr[h.WEEK_BASED_YEARS.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f18350a[h.QUARTER_YEARS.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
    }
}
