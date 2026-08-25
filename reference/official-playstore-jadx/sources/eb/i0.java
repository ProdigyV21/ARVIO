package eb;

/* JADX INFO: loaded from: classes5.dex */
public final class i0 extends a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f15151e;

    public i0(String str) {
        this.f15151e = str;
    }

    @Override // eb.a
    public final boolean A() {
        int iY = y();
        String str = this.f15151e;
        if (iY == str.length() || iY == -1 || str.charAt(iY) != ',') {
            return false;
        }
        this.f15100a++;
        return true;
    }

    @Override // eb.a
    public final boolean c() {
        int i10 = this.f15100a;
        if (i10 == -1) {
            return false;
        }
        while (true) {
            String str = this.f15151e;
            if (i10 >= str.length()) {
                this.f15100a = i10;
                return false;
            }
            char cCharAt = str.charAt(i10);
            if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                this.f15100a = i10;
                return !(cCharAt == '}' || cCharAt == ']' || cCharAt == ':' || cCharAt == ',');
            }
            i10++;
        }
    }

    @Override // eb.a
    public final String f() {
        j('\"');
        int i10 = this.f15100a;
        String str = this.f15151e;
        int iE0 = kotlin.text.o.e0(str, '\"', i10, false, 4);
        if (iE0 == -1) {
            t((byte) 1);
            throw null;
        }
        for (int i11 = i10; i11 < iE0; i11++) {
            if (str.charAt(i11) == '\\') {
                return m(str, this.f15100a, i11);
            }
        }
        this.f15100a = iE0 + 1;
        return str.substring(i10, iE0);
    }

    @Override // eb.a
    public final String g(String str, boolean z) {
        int i10 = this.f15100a;
        try {
            if (h() != 6) {
                this.f15100a = i10;
                return null;
            }
            if (!kotlin.jvm.internal.p.a(z ? f() : o(), str)) {
                this.f15100a = i10;
                return null;
            }
            if (h() != 5) {
                this.f15100a = i10;
                return null;
            }
            String strL = z ? l() : o();
            this.f15100a = i10;
            return strL;
        } catch (Throwable th) {
            this.f15100a = i10;
            throw th;
        }
    }

    @Override // eb.a
    public final byte h() {
        byte bG;
        do {
            int i10 = this.f15100a;
            if (i10 == -1) {
                return (byte) 10;
            }
            String str = this.f15151e;
            if (i10 >= str.length()) {
                return (byte) 10;
            }
            int i11 = this.f15100a;
            this.f15100a = i11 + 1;
            bG = r.g(str.charAt(i11));
        } while (bG == 3);
        return bG;
    }

    @Override // eb.a
    public final void j(char c10) {
        if (this.f15100a == -1) {
            B(c10);
            throw null;
        }
        while (true) {
            int i10 = this.f15100a;
            String str = this.f15151e;
            if (i10 >= str.length()) {
                B(c10);
                throw null;
            }
            int i11 = this.f15100a;
            this.f15100a = i11 + 1;
            char cCharAt = str.charAt(i11);
            if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                if (cCharAt == c10) {
                    return;
                }
                B(c10);
                throw null;
            }
        }
    }

    @Override // eb.a
    public final CharSequence v() {
        return this.f15151e;
    }

    @Override // eb.a
    public final int x(int i10) {
        if (i10 < this.f15151e.length()) {
            return i10;
        }
        return -1;
    }

    @Override // eb.a
    public final int y() {
        char cCharAt;
        int i10 = this.f15100a;
        if (i10 == -1) {
            return i10;
        }
        while (true) {
            String str = this.f15151e;
            if (i10 >= str.length() || !((cCharAt = str.charAt(i10)) == ' ' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == '\t')) {
                break;
            }
            i10++;
        }
        this.f15100a = i10;
        return i10;
    }
}
