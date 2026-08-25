package eb;

/* JADX INFO: loaded from: classes5.dex */
public final class d implements CharSequence {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final char[] f15122i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f15123l;

    public d(char[] cArr) {
        this.f15122i = cArr;
        this.f15123l = cArr.length;
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i10) {
        return this.f15122i[i10];
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.f15123l;
    }

    @Override // java.lang.CharSequence
    public final CharSequence subSequence(int i10, int i11) {
        return kotlin.text.u.I(this.f15122i, i10, Math.min(i11, this.f15123l));
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        int i10 = this.f15123l;
        return kotlin.text.u.I(this.f15122i, 0, Math.min(i10, i10));
    }
}
