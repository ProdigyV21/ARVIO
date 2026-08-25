package j$.time.format;

/* JADX INFO: loaded from: classes2.dex */
public final class c implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final char f18233a;

    public c(char c10) {
        this.f18233a = c10;
    }

    @Override // j$.time.format.e
    public final boolean l(w wVar, StringBuilder sb2) {
        sb2.append(this.f18233a);
        return true;
    }

    @Override // j$.time.format.e
    public final int m(u uVar, CharSequence charSequence, int i10) {
        if (i10 == charSequence.length()) {
            return ~i10;
        }
        char cCharAt = charSequence.charAt(i10);
        char c10 = this.f18233a;
        return (cCharAt == c10 || (!uVar.f18291b && (Character.toUpperCase(cCharAt) == Character.toUpperCase(c10) || Character.toLowerCase(cCharAt) == Character.toLowerCase(c10)))) ? i10 + 1 : ~i10;
    }

    public final String toString() {
        char c10 = this.f18233a;
        if (c10 == '\'') {
            return "''";
        }
        return "'" + c10 + "'";
    }
}
