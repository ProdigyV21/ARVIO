package androidx.core.text;

/* JADX INFO: loaded from: classes3.dex */
public final class a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final byte[] f2171e = new byte[1792];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CharSequence f2172a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2173b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2174c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public char f2175d;

    static {
        for (int i10 = 0; i10 < 1792; i10++) {
            f2171e[i10] = Character.getDirectionality(i10);
        }
    }

    public a(CharSequence charSequence) {
        this.f2172a = charSequence;
        this.f2173b = charSequence.length();
    }

    public final byte a() {
        int i10 = this.f2174c - 1;
        CharSequence charSequence = this.f2172a;
        char cCharAt = charSequence.charAt(i10);
        this.f2175d = cCharAt;
        if (Character.isLowSurrogate(cCharAt)) {
            int iCodePointBefore = Character.codePointBefore(charSequence, this.f2174c);
            this.f2174c -= Character.charCount(iCodePointBefore);
            return Character.getDirectionality(iCodePointBefore);
        }
        this.f2174c--;
        char c10 = this.f2175d;
        return c10 < 1792 ? f2171e[c10] : Character.getDirectionality(c10);
    }
}
