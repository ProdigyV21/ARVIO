package eb;

import androidx.fragment.app.a2;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f15100a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final androidx.appcompat.widget.f0 f15101b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f15102c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final StringBuilder f15103d;

    public a() {
        androidx.appcompat.widget.f0 f0Var = new androidx.appcompat.widget.f0(4);
        f0Var.f1567m = new Object[8];
        int[] iArr = new int[8];
        for (int i10 = 0; i10 < 8; i10++) {
            iArr[i10] = -1;
        }
        f0Var.f1568n = iArr;
        f0Var.f1566l = -1;
        this.f15101b = f0Var;
        this.f15103d = new StringBuilder();
    }

    public static /* synthetic */ void s(a aVar, String str, int i10, int i11) {
        if ((i11 & 2) != 0) {
            i10 = aVar.f15100a;
        }
        aVar.r(i10, str, (i11 & 4) != 0 ? "" : "It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'");
        throw null;
    }

    public abstract boolean A();

    public final void B(char c10) {
        int i10 = this.f15100a - 1;
        this.f15100a = i10;
        if (i10 >= 0 && c10 == '\"' && kotlin.jvm.internal.p.a(n(), "null")) {
            r(this.f15100a - 4, "Expected string literal but 'null' literal was found", "Use 'coerceInputValues = true' in 'Json {}` builder to coerce nulls to default values.");
            throw null;
        }
        t(r.g(c10));
        throw null;
    }

    public final int a(CharSequence charSequence, int i10) {
        int i11 = i10 + 4;
        if (i11 < charSequence.length()) {
            this.f15103d.append((char) (u(charSequence, i10 + 3) + (u(charSequence, i10) << 12) + (u(charSequence, i10 + 1) << 8) + (u(charSequence, i10 + 2) << 4)));
            return i11;
        }
        this.f15100a = i10;
        p();
        if (this.f15100a + 4 < charSequence.length()) {
            return a(charSequence, this.f15100a);
        }
        s(this, "Unexpected EOF during unicode escape", 0, 6);
        throw null;
    }

    public void b(int i10, int i11) {
        this.f15103d.append(v(), i10, i11);
    }

    public abstract boolean c();

    public final boolean d(int i10) {
        int iX = x(i10);
        if (iX >= v().length() || iX == -1) {
            s(this, "EOF", 0, 6);
            throw null;
        }
        int i11 = iX + 1;
        int iCharAt = v().charAt(iX) | ' ';
        if (iCharAt == 102) {
            e(i11, "alse");
            return false;
        }
        if (iCharAt == 116) {
            e(i11, "rue");
            return true;
        }
        s(this, "Expected valid boolean literal prefix, but had '" + n() + '\'', 0, 6);
        throw null;
    }

    public final void e(int i10, String str) {
        if (v().length() - i10 < str.length()) {
            s(this, "Unexpected end of boolean literal", 0, 6);
            throw null;
        }
        int length = str.length();
        for (int i11 = 0; i11 < length; i11++) {
            if (str.charAt(i11) != (v().charAt(i10 + i11) | ' ')) {
                s(this, "Expected valid boolean literal prefix, but had '" + n() + '\'', 0, 6);
                throw null;
            }
        }
        this.f15100a = str.length() + i10;
    }

    public abstract String f();

    public abstract String g(String str, boolean z);

    public abstract byte h();

    public final byte i(byte b10) {
        byte bH = h();
        if (bH == b10) {
            return bH;
        }
        t(b10);
        throw null;
    }

    public void j(char c10) {
        p();
        CharSequence charSequenceV = v();
        int i10 = this.f15100a;
        while (true) {
            int iX = x(i10);
            if (iX == -1) {
                this.f15100a = iX;
                B(c10);
                throw null;
            }
            int i11 = iX + 1;
            char cCharAt = charSequenceV.charAt(iX);
            if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                this.f15100a = i11;
                if (cCharAt == c10) {
                    return;
                }
                B(c10);
                throw null;
            }
            i10 = i11;
        }
    }

    public final long k() {
        boolean z;
        int iX = x(y());
        if (iX >= v().length() || iX == -1) {
            s(this, "EOF", 0, 6);
            throw null;
        }
        if (v().charAt(iX) == '\"') {
            iX++;
            if (iX == v().length()) {
                s(this, "EOF", 0, 6);
                throw null;
            }
            z = true;
        } else {
            z = false;
        }
        int i10 = iX;
        boolean z5 = false;
        boolean z10 = true;
        long j10 = 0;
        while (z10) {
            char cCharAt = v().charAt(i10);
            if (cCharAt != '-') {
                if (r.g(cCharAt) != 0) {
                    break;
                }
                i10++;
                z10 = i10 != v().length();
                int i11 = cCharAt - '0';
                if (i11 < 0 || i11 >= 10) {
                    s(this, "Unexpected symbol '" + cCharAt + "' in numeric literal", 0, 6);
                    throw null;
                }
                j10 = (j10 * ((long) 10)) - ((long) i11);
                if (j10 > 0) {
                    s(this, "Numeric value overflow", 0, 6);
                    throw null;
                }
            } else {
                if (i10 != iX) {
                    s(this, "Unexpected symbol '-' in numeric literal", 0, 6);
                    throw null;
                }
                i10++;
                z5 = true;
            }
        }
        if (iX == i10 || (z5 && iX == i10 - 1)) {
            s(this, "Expected numeric literal", 0, 6);
            throw null;
        }
        if (z) {
            if (!z10) {
                s(this, "EOF", 0, 6);
                throw null;
            }
            if (v().charAt(i10) != '\"') {
                s(this, "Expected closing quotation mark", 0, 6);
                throw null;
            }
            i10++;
        }
        this.f15100a = i10;
        if (z5) {
            return j10;
        }
        if (j10 != Long.MIN_VALUE) {
            return -j10;
        }
        s(this, "Numeric value overflow", 0, 6);
        throw null;
    }

    public final String l() {
        String str = this.f15102c;
        if (str == null) {
            return f();
        }
        this.f15102c = null;
        return str;
    }

    public final String m(CharSequence charSequence, int i10, int i11) {
        String string;
        char cCharAt = charSequence.charAt(i11);
        boolean z = false;
        while (true) {
            StringBuilder sb2 = this.f15103d;
            if (cCharAt == '\"') {
                if (z) {
                    b(i10, i11);
                    string = sb2.toString();
                    sb2.setLength(0);
                } else {
                    string = z(i10, i11);
                }
                this.f15100a = i11 + 1;
                return string;
            }
            if (cCharAt == '\\') {
                b(i10, i11);
                int iX = x(i11 + 1);
                if (iX == -1) {
                    s(this, "Expected escape sequence to continue, got EOF", 0, 6);
                    throw null;
                }
                int iA = iX + 1;
                char cCharAt2 = v().charAt(iX);
                if (cCharAt2 == 'u') {
                    iA = a(v(), iA);
                } else {
                    char c10 = cCharAt2 < 'u' ? i.f15149a[cCharAt2] : (char) 0;
                    if (c10 == 0) {
                        s(this, "Invalid escaped char '" + cCharAt2 + '\'', 0, 6);
                        throw null;
                    }
                    sb2.append(c10);
                }
                i10 = x(iA);
                if (i10 == -1) {
                    s(this, "EOF", i10, 4);
                    throw null;
                }
            } else {
                i11++;
                if (i11 >= charSequence.length()) {
                    b(i10, i11);
                    i10 = x(i11);
                    if (i10 == -1) {
                        s(this, "EOF", i10, 4);
                        throw null;
                    }
                } else {
                    continue;
                    cCharAt = charSequence.charAt(i11);
                }
            }
            i11 = i10;
            z = true;
            cCharAt = charSequence.charAt(i11);
        }
    }

    public final String n() {
        String string;
        String str = this.f15102c;
        if (str != null) {
            this.f15102c = null;
            return str;
        }
        int iY = y();
        if (iY >= v().length() || iY == -1) {
            s(this, "EOF", iY, 4);
            throw null;
        }
        byte bG = r.g(v().charAt(iY));
        if (bG == 1) {
            return l();
        }
        if (bG != 0) {
            s(this, "Expected beginning of the string, but got " + v().charAt(iY), 0, 6);
            throw null;
        }
        boolean z = false;
        while (true) {
            byte bG2 = r.g(v().charAt(iY));
            StringBuilder sb2 = this.f15103d;
            if (bG2 != 0) {
                if (z) {
                    b(this.f15100a, iY);
                    string = sb2.toString();
                    sb2.setLength(0);
                } else {
                    string = z(this.f15100a, iY);
                }
                this.f15100a = iY;
                return string;
            }
            iY++;
            if (iY >= v().length()) {
                b(this.f15100a, iY);
                int iX = x(iY);
                if (iX == -1) {
                    this.f15100a = iY;
                    b(0, 0);
                    String string2 = sb2.toString();
                    sb2.setLength(0);
                    return string2;
                }
                iY = iX;
                z = true;
            }
        }
    }

    public final String o() {
        String strN = n();
        if (!kotlin.jvm.internal.p.a(strN, "null") || v().charAt(this.f15100a - 1) == '\"') {
            return strN;
        }
        s(this, "Unexpected 'null' value instead of string literal", 0, 6);
        throw null;
    }

    public final void q() {
        if (h() == 10) {
            return;
        }
        s(this, "Expected EOF after parsing, but had " + v().charAt(this.f15100a - 1) + " instead", 0, 6);
        throw null;
    }

    public final void r(int i10, String str, String str2) {
        String strConcat = str2.length() == 0 ? "" : "\n".concat(str2);
        StringBuilder sbP = a2.p(str, " at path: ");
        sbP.append(this.f15101b.d());
        sbP.append(strConcat);
        throw r.d(sbP.toString(), v(), i10);
    }

    public final void t(byte b10) {
        s(this, a2.m("Expected ", b10 == 1 ? "quotation mark '\"'" : b10 == 4 ? "comma ','" : b10 == 5 ? "colon ':'" : b10 == 6 ? "start of the object '{'" : b10 == 7 ? "end of the object '}'" : b10 == 8 ? "start of the array '['" : b10 == 9 ? "end of the array ']'" : "valid token", ", but had '", (this.f15100a == v().length() || this.f15100a <= 0) ? "EOF" : String.valueOf(v().charAt(this.f15100a - 1)), "' instead"), this.f15100a - 1, 4);
        throw null;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("JsonReader(source='");
        sb2.append((Object) v());
        sb2.append("', currentPosition=");
        return a0.c.o(sb2, this.f15100a, ')');
    }

    public final int u(CharSequence charSequence, int i10) {
        char cCharAt = charSequence.charAt(i10);
        if ('0' <= cCharAt && cCharAt < ':') {
            return cCharAt - '0';
        }
        if ('a' <= cCharAt && cCharAt < 'g') {
            return cCharAt - 'W';
        }
        if ('A' <= cCharAt && cCharAt < 'G') {
            return cCharAt - '7';
        }
        s(this, "Invalid toHexChar char '" + cCharAt + "' in unicode escape", 0, 6);
        throw null;
    }

    public abstract CharSequence v();

    public final byte w() {
        CharSequence charSequenceV = v();
        int i10 = this.f15100a;
        while (true) {
            int iX = x(i10);
            if (iX == -1) {
                this.f15100a = iX;
                return (byte) 10;
            }
            char cCharAt = charSequenceV.charAt(iX);
            if (cCharAt != ' ' && cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\t') {
                this.f15100a = iX;
                return r.g(cCharAt);
            }
            i10 = iX + 1;
        }
    }

    public abstract int x(int i10);

    public int y() {
        int iX;
        char cCharAt;
        int i10 = this.f15100a;
        while (true) {
            iX = x(i10);
            if (iX == -1 || !((cCharAt = v().charAt(iX)) == ' ' || cCharAt == '\n' || cCharAt == '\r' || cCharAt == '\t')) {
                break;
            }
            i10 = iX + 1;
        }
        this.f15100a = iX;
        return iX;
    }

    public String z(int i10, int i11) {
        return v().subSequence(i10, i11).toString();
    }

    public void p() {
    }
}
