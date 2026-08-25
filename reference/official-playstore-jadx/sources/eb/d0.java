package eb;

import java.nio.charset.CharacterCodingException;

/* JADX INFO: loaded from: classes5.dex */
public final class d0 extends a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a8.e f15124e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f15125f = 128;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final d f15126g;

    public d0(a8.e eVar, char[] cArr) throws CharacterCodingException {
        this.f15124e = eVar;
        this.f15126g = new d(cArr);
        C(0);
    }

    @Override // eb.a
    public final boolean A() {
        int iY = y();
        d dVar = this.f15126g;
        if (iY >= dVar.f15123l || iY == -1 || dVar.f15122i[iY] != ',') {
            return false;
        }
        this.f15100a++;
        return true;
    }

    public final void C(int i10) throws CharacterCodingException {
        d dVar = this.f15126g;
        char[] cArr = dVar.f15122i;
        if (i10 != 0) {
            int i11 = this.f15100a;
            kotlin.collections.r.M(cArr, cArr, 0, i11, i11 + i10);
        }
        int i12 = dVar.f15123l;
        while (true) {
            if (i10 == i12) {
                break;
            }
            int iA = ((j) this.f15124e.f183l).a(cArr, i10, i12 - i10);
            if (iA == -1) {
                dVar.f15123l = Math.min(dVar.f15122i.length, i10);
                this.f15125f = -1;
                break;
            }
            i10 += iA;
        }
        this.f15100a = 0;
    }

    @Override // eb.a
    public final void b(int i10, int i11) {
        this.f15103d.append(this.f15126g.f15122i, i10, i11 - i10);
    }

    @Override // eb.a
    public final boolean c() throws CharacterCodingException {
        p();
        int i10 = this.f15100a;
        while (true) {
            int iX = x(i10);
            if (iX == -1) {
                this.f15100a = iX;
                return false;
            }
            char c10 = this.f15126g.f15122i[iX];
            if (c10 != ' ' && c10 != '\n' && c10 != '\r' && c10 != '\t') {
                this.f15100a = iX;
                return !(c10 == '}' || c10 == ']' || c10 == ':' || c10 == ',');
            }
            i10 = iX + 1;
        }
    }

    @Override // eb.a
    public final String f() throws CharacterCodingException {
        j('\"');
        int i10 = this.f15100a;
        d dVar = this.f15126g;
        int i11 = dVar.f15123l;
        char[] cArr = dVar.f15122i;
        int i12 = i10;
        while (true) {
            if (i12 >= i11) {
                i12 = -1;
                break;
            }
            if (cArr[i12] == '\"') {
                break;
            }
            i12++;
        }
        if (i12 == -1) {
            int iX = x(i10);
            if (iX != -1) {
                return m(dVar, this.f15100a, iX);
            }
            t((byte) 1);
            throw null;
        }
        for (int i13 = i10; i13 < i12; i13++) {
            if (cArr[i13] == '\\') {
                return m(dVar, this.f15100a, i13);
            }
        }
        this.f15100a = i12 + 1;
        return kotlin.text.u.I(cArr, i10, Math.min(i12, dVar.f15123l));
    }

    @Override // eb.a
    public final String g(String str, boolean z) {
        return null;
    }

    @Override // eb.a
    public final byte h() throws CharacterCodingException {
        p();
        int i10 = this.f15100a;
        while (true) {
            int iX = x(i10);
            if (iX == -1) {
                this.f15100a = iX;
                return (byte) 10;
            }
            int i11 = iX + 1;
            byte bG = r.g(this.f15126g.f15122i[iX]);
            if (bG != 3) {
                this.f15100a = i11;
                return bG;
            }
            i10 = i11;
        }
    }

    @Override // eb.a
    public final void p() throws CharacterCodingException {
        int i10 = this.f15126g.f15123l - this.f15100a;
        if (i10 > this.f15125f) {
            return;
        }
        C(i10);
    }

    @Override // eb.a
    public final CharSequence v() {
        return this.f15126g;
    }

    @Override // eb.a
    public final int x(int i10) throws CharacterCodingException {
        d dVar = this.f15126g;
        if (i10 < dVar.f15123l) {
            return i10;
        }
        this.f15100a = i10;
        p();
        return (this.f15100a != 0 || dVar.length() == 0) ? -1 : 0;
    }

    @Override // eb.a
    public final String z(int i10, int i11) {
        d dVar = this.f15126g;
        return kotlin.text.u.I(dVar.f15122i, i10, Math.min(i11, dVar.f15123l));
    }
}
