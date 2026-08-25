package j$.time.format;

import j$.time.DateTimeException;

/* JADX INFO: loaded from: classes2.dex */
public final class k implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f18259a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18260b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final char f18261c;

    @Override // j$.time.format.e
    public final int m(u uVar, CharSequence charSequence, int i10) {
        boolean z = uVar.f18292c;
        if (i10 > charSequence.length()) {
            throw new IndexOutOfBoundsException();
        }
        if (i10 == charSequence.length()) {
            return ~i10;
        }
        int length = this.f18260b + i10;
        if (length > charSequence.length()) {
            if (z) {
                return ~i10;
            }
            length = charSequence.length();
        }
        int i11 = i10;
        while (i11 < length && uVar.a(charSequence.charAt(i11), this.f18261c)) {
            i11++;
        }
        int iM = this.f18259a.m(uVar, charSequence.subSequence(0, length), i11);
        return (iM == length || !z) ? iM : ~(i10 + i11);
    }

    public k(e eVar, int i10, char c10) {
        this.f18259a = eVar;
        this.f18260b = i10;
        this.f18261c = c10;
    }

    @Override // j$.time.format.e
    public final boolean l(w wVar, StringBuilder sb2) {
        int length = sb2.length();
        if (!this.f18259a.l(wVar, sb2)) {
            return false;
        }
        int length2 = sb2.length() - length;
        int i10 = this.f18260b;
        if (length2 <= i10) {
            for (int i11 = 0; i11 < i10 - length2; i11++) {
                sb2.insert(length, this.f18261c);
            }
            return true;
        }
        throw new DateTimeException("Cannot print as output of " + length2 + " characters exceeds pad width of " + i10);
    }

    public final String toString() {
        String str;
        char c10 = this.f18261c;
        if (c10 == ' ') {
            str = ")";
        } else {
            str = ",'" + c10 + "')";
        }
        return "Pad(" + this.f18259a + "," + this.f18260b + str;
    }
}
