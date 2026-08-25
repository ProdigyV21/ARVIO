package com.squareup.moshi;

import androidx.compose.ui.layout.LayoutKt;
import java.io.EOFException;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes4.dex */
public final class b0 extends l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f14404a;

    public /* synthetic */ b0(int i10) {
        this.f14404a = i10;
    }

    @Override // com.squareup.moshi.l
    public final Object a(p pVar) throws EOFException {
        long jLongValueExact;
        switch (this.f14404a) {
            case 0:
                return pVar.nextString();
            case 1:
                int iO = pVar.f14451q;
                if (iO == 0) {
                    iO = pVar.o();
                }
                boolean z = false;
                if (iO == 5) {
                    pVar.f14451q = 0;
                    int[] iArr = pVar.f14445n;
                    int i10 = pVar.f14442i - 1;
                    iArr[i10] = iArr[i10] + 1;
                    z = true;
                } else {
                    if (iO != 6) {
                        throw new JsonDataException("Expected a boolean but was " + com.arflix.tv.data.repository.g.x(pVar.b0()) + " at path " + pVar.i());
                    }
                    pVar.f14451q = 0;
                    int[] iArr2 = pVar.f14445n;
                    int i11 = pVar.f14442i - 1;
                    iArr2[i11] = iArr2[i11] + 1;
                }
                return Boolean.valueOf(z);
            case 2:
                return Byte.valueOf((byte) e0.g(pVar, "a byte", -128, 255));
            case 3:
                String strNextString = pVar.nextString();
                if (strNextString.length() <= 1) {
                    return Character.valueOf(strNextString.charAt(0));
                }
                throw new JsonDataException(androidx.compose.material3.d.n("Expected a char but was ", androidx.compose.material3.d.i('\"', "\"", strNextString), " at path ", pVar.i()));
            case 4:
                return Double.valueOf(pVar.nextDouble());
            case 5:
                float fNextDouble = (float) pVar.nextDouble();
                if (!Float.isInfinite(fNextDouble)) {
                    return Float.valueOf(fNextDouble);
                }
                throw new JsonDataException("JSON forbids NaN and infinities: " + fNextDouble + " at path " + pVar.i());
            case 6:
                return Integer.valueOf(pVar.nextInt());
            case 7:
                int iO2 = pVar.f14451q;
                if (iO2 == 0) {
                    iO2 = pVar.o();
                }
                if (iO2 != 16) {
                    if (iO2 == 17) {
                        xb.j jVar = pVar.f14450p;
                        long j10 = pVar.f14453s;
                        jVar.getClass();
                        pVar.f14454t = jVar.q0(j10, kotlin.text.a.f19924a);
                    } else if (iO2 == 9 || iO2 == 8) {
                        String strT = iO2 == 9 ? pVar.T(p.f14447v) : pVar.T(p.f14446u);
                        pVar.f14454t = strT;
                        try {
                            jLongValueExact = Long.parseLong(strT);
                            pVar.f14451q = 0;
                            int[] iArr3 = pVar.f14445n;
                            int i12 = pVar.f14442i - 1;
                            iArr3[i12] = iArr3[i12] + 1;
                        } catch (NumberFormatException unused) {
                            pVar.f14451q = 11;
                            jLongValueExact = new BigDecimal(pVar.f14454t).longValueExact();
                            pVar.f14454t = null;
                            pVar.f14451q = 0;
                            int[] iArr4 = pVar.f14445n;
                            int i13 = pVar.f14442i - 1;
                            iArr4[i13] = iArr4[i13] + 1;
                        }
                    } else if (iO2 != 11) {
                        throw new JsonDataException("Expected a long but was " + com.arflix.tv.data.repository.g.x(pVar.b0()) + " at path " + pVar.i());
                    }
                    pVar.f14451q = 11;
                    try {
                        jLongValueExact = new BigDecimal(pVar.f14454t).longValueExact();
                        pVar.f14454t = null;
                        pVar.f14451q = 0;
                        int[] iArr42 = pVar.f14445n;
                        int i132 = pVar.f14442i - 1;
                        iArr42[i132] = iArr42[i132] + 1;
                    } catch (ArithmeticException | NumberFormatException unused2) {
                        throw new JsonDataException("Expected a long but was " + pVar.f14454t + " at path " + pVar.i());
                    }
                    break;
                } else {
                    pVar.f14451q = 0;
                    int[] iArr5 = pVar.f14445n;
                    int i14 = pVar.f14442i - 1;
                    iArr5[i14] = iArr5[i14] + 1;
                    jLongValueExact = pVar.f14452r;
                }
                return Long.valueOf(jLongValueExact);
            default:
                return Short.valueOf((short) e0.g(pVar, "a short", -32768, LayoutKt.LargeDimension));
        }
    }

    @Override // com.squareup.moshi.l
    public final void e(q qVar, Object obj) {
        switch (this.f14404a) {
            case 0:
                qVar.V((String) obj);
                return;
            case 1:
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                if (qVar.f14462o) {
                    throw new IllegalStateException("Boolean cannot be used as a map key in JSON at path " + qVar.i());
                }
                qVar.b0();
                qVar.k();
                qVar.f14456q.S0(zBooleanValue ? "true" : "false");
                int[] iArr = qVar.f14461n;
                int i10 = qVar.f14458i - 1;
                iArr[i10] = iArr[i10] + 1;
                return;
            case 2:
                qVar.T(((Byte) obj).intValue() & 255);
                return;
            case 3:
                qVar.V(((Character) obj).toString());
                return;
            case 4:
                double dDoubleValue = ((Double) obj).doubleValue();
                if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                    throw new IllegalArgumentException("Numeric values must be finite, but was " + dDoubleValue);
                }
                if (qVar.f14462o) {
                    qVar.f14462o = false;
                    qVar.y(Double.toString(dDoubleValue));
                    return;
                }
                qVar.b0();
                qVar.k();
                qVar.f14456q.S0(Double.toString(dDoubleValue));
                int[] iArr2 = qVar.f14461n;
                int i11 = qVar.f14458i - 1;
                iArr2[i11] = iArr2[i11] + 1;
                return;
            case 5:
                Float f10 = (Float) obj;
                f10.getClass();
                String string = f10.toString();
                if (string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN")) {
                    throw new IllegalArgumentException("Numeric values must be finite, but was " + f10);
                }
                if (qVar.f14462o) {
                    qVar.f14462o = false;
                    qVar.y(string);
                    return;
                }
                qVar.b0();
                qVar.k();
                qVar.f14456q.S0(string);
                int[] iArr3 = qVar.f14461n;
                int i12 = qVar.f14458i - 1;
                iArr3[i12] = iArr3[i12] + 1;
                return;
            case 6:
                qVar.T(((Integer) obj).intValue());
                return;
            case 7:
                qVar.T(((Long) obj).longValue());
                return;
            default:
                qVar.T(((Short) obj).intValue());
                return;
        }
    }

    public final String toString() {
        switch (this.f14404a) {
            case 0:
                return "JsonAdapter(String)";
            case 1:
                return "JsonAdapter(Boolean)";
            case 2:
                return "JsonAdapter(Byte)";
            case 3:
                return "JsonAdapter(Character)";
            case 4:
                return "JsonAdapter(Double)";
            case 5:
                return "JsonAdapter(Float)";
            case 6:
                return "JsonAdapter(Integer)";
            case 7:
                return "JsonAdapter(Long)";
            default:
                return "JsonAdapter(Short)";
        }
    }
}
