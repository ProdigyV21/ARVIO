package j$.time.format;

import j$.util.Objects;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/* JADX INFO: loaded from: classes2.dex */
public final class f extends i {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f18244g;

    @Override // j$.time.format.i
    public final boolean b(u uVar) {
        return uVar.f18292c && this.f18249b == this.f18250c && !this.f18244g;
    }

    @Override // j$.time.format.i, j$.time.format.e
    public final int m(u uVar, CharSequence charSequence, int i10) {
        boolean z = uVar.f18292c;
        DateTimeFormatter dateTimeFormatter = uVar.f18290a;
        int i11 = (z || b(uVar)) ? this.f18249b : 0;
        int i12 = (uVar.f18292c || b(uVar)) ? this.f18250c : 9;
        int length = charSequence.length();
        if (i10 != length) {
            if (this.f18244g) {
                if (charSequence.charAt(i10) == dateTimeFormatter.f18201c.f18218c) {
                    i10++;
                } else if (i11 > 0) {
                    return ~i10;
                }
            }
            int i13 = i10;
            int i14 = i11 + i13;
            if (i14 > length) {
                return ~i13;
            }
            int iMin = Math.min(i12 + i13, length);
            int i15 = 0;
            int i16 = i13;
            while (true) {
                if (i16 >= iMin) {
                    break;
                }
                int i17 = i16 + 1;
                int iCharAt = charSequence.charAt(i16) - dateTimeFormatter.f18201c.f18216a;
                if (iCharAt < 0 || iCharAt > 9) {
                    iCharAt = -1;
                }
                if (iCharAt >= 0) {
                    i15 = (i15 * 10) + iCharAt;
                    i16 = i17;
                } else if (i17 < i14) {
                    return ~i13;
                }
            }
            BigDecimal bigDecimalMovePointLeft = new BigDecimal(i15).movePointLeft(i16 - i13);
            j$.time.temporal.s sVarR = this.f18248a.r();
            BigDecimal bigDecimalValueOf = BigDecimal.valueOf(sVarR.f18373a);
            return uVar.f(this.f18248a, bigDecimalMovePointLeft.multiply(BigDecimal.valueOf(sVarR.f18376d).subtract(bigDecimalValueOf).add(BigDecimal.ONE)).setScale(0, RoundingMode.FLOOR).add(bigDecimalValueOf).longValueExact(), i13, i16);
        }
        if (i11 > 0) {
            return ~i10;
        }
        return i10;
    }

    public f(j$.time.temporal.o oVar, int i10, int i11, boolean z) {
        this(oVar, i10, i11, z, 0);
        Objects.requireNonNull(oVar, "field");
        j$.time.temporal.s sVarR = oVar.r();
        if (sVarR.f18373a != sVarR.f18374b || sVarR.f18375c != sVarR.f18376d) {
            throw new IllegalArgumentException(j$.time.b.a("Field must have a fixed set of values: ", oVar));
        }
        if (i10 < 0 || i10 > 9) {
            throw new IllegalArgumentException("Minimum width must be from 0 to 9 inclusive but was " + i10);
        }
        if (i11 < 1 || i11 > 9) {
            throw new IllegalArgumentException("Maximum width must be from 1 to 9 inclusive but was " + i11);
        }
        if (i11 >= i10) {
            return;
        }
        throw new IllegalArgumentException("Maximum width must exceed or equal the minimum width but " + i11 + " < " + i10);
    }

    public f(j$.time.temporal.o oVar, int i10, int i11, boolean z, int i12) {
        super(oVar, i10, i11, c0.NOT_NEGATIVE, i12);
        this.f18244g = z;
    }

    @Override // j$.time.format.i
    public final i d() {
        if (this.f18252e == -1) {
            return this;
        }
        return new f(this.f18248a, this.f18249b, this.f18250c, this.f18244g, -1);
    }

    @Override // j$.time.format.i
    public final i e(int i10) {
        return new f(this.f18248a, this.f18249b, this.f18250c, this.f18244g, this.f18252e + i10);
    }

    @Override // j$.time.format.i, j$.time.format.e
    public final boolean l(w wVar, StringBuilder sb2) {
        j$.time.temporal.o oVar = this.f18248a;
        Long lA = wVar.a(oVar);
        if (lA == null) {
            return false;
        }
        DecimalStyle decimalStyle = wVar.f18300b.f18201c;
        long jLongValue = lA.longValue();
        j$.time.temporal.s sVarR = oVar.r();
        sVarR.b(jLongValue, oVar);
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(sVarR.f18373a);
        BigDecimal bigDecimalAdd = BigDecimal.valueOf(sVarR.f18376d).subtract(bigDecimalValueOf).add(BigDecimal.ONE);
        BigDecimal bigDecimalSubtract = BigDecimal.valueOf(jLongValue).subtract(bigDecimalValueOf);
        RoundingMode roundingMode = RoundingMode.FLOOR;
        BigDecimal bigDecimalDivide = bigDecimalSubtract.divide(bigDecimalAdd, 9, roundingMode);
        BigDecimal bigDecimal = BigDecimal.ZERO;
        if (bigDecimalDivide.compareTo(bigDecimal) != 0) {
            bigDecimal = bigDecimalDivide.signum() == 0 ? new BigDecimal(BigInteger.ZERO, 0) : bigDecimalDivide.stripTrailingZeros();
        }
        int iScale = bigDecimal.scale();
        boolean z = this.f18244g;
        int i10 = this.f18249b;
        if (iScale != 0) {
            String strA = decimalStyle.a(bigDecimal.setScale(Math.min(Math.max(bigDecimal.scale(), i10), this.f18250c), roundingMode).toPlainString().substring(2));
            if (z) {
                sb2.append(decimalStyle.f18218c);
            }
            sb2.append(strA);
            return true;
        }
        if (i10 > 0) {
            if (z) {
                sb2.append(decimalStyle.f18218c);
            }
            for (int i11 = 0; i11 < i10; i11++) {
                sb2.append(decimalStyle.f18216a);
            }
        }
        return true;
    }

    @Override // j$.time.format.i
    public final String toString() {
        return "Fraction(" + this.f18248a + "," + this.f18249b + "," + this.f18250c + (this.f18244g ? ",DecimalPoint" : "") + ")";
    }
}
