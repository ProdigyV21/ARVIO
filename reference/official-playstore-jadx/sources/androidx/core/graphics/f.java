package androidx.core.graphics;

import android.graphics.Path;
import android.util.Log;
import androidx.media3.container.MdtaMetadataEntry;
import com.arflix.tv.util.Constants;
import com.google.android.gms.cast.MediaError;
import io.ktor.util.date.GMTDateParser;

/* JADX INFO: loaded from: classes3.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public char f2101a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float[] f2102b;

    public f(char c10, float[] fArr) {
        this.f2101a = c10;
        this.f2102b = fArr;
    }

    public static void a(Path path, float f10, float f11, float f12, float f13, float f14, float f15, float f16, boolean z, boolean z5) {
        double d4;
        double d10;
        double radians = Math.toRadians(f16);
        double dCos = Math.cos(radians);
        double dSin = Math.sin(radians);
        double d11 = f10;
        double d12 = f11;
        double d13 = f14;
        double d14 = ((d12 * dSin) + (d11 * dCos)) / d13;
        double d15 = f15;
        double d16 = ((d12 * dCos) + (((double) (-f10)) * dSin)) / d15;
        double d17 = f13;
        double d18 = ((d17 * dSin) + (((double) f12) * dCos)) / d13;
        double d19 = ((d17 * dCos) + (((double) (-f12)) * dSin)) / d15;
        double d20 = d14 - d18;
        double d21 = d16 - d19;
        double d22 = (d14 + d18) / 2.0d;
        double d23 = (d16 + d19) / 2.0d;
        double d24 = (d21 * d21) + (d20 * d20);
        if (d24 == 0.0d) {
            Log.w("PathParser", " Points are coincident");
            return;
        }
        double d25 = (1.0d / d24) - 0.25d;
        if (d25 < 0.0d) {
            Log.w("PathParser", "Points are too far apart " + d24);
            float fSqrt = (float) (Math.sqrt(d24) / 1.99999d);
            a(path, f10, f11, f12, f13, f14 * fSqrt, fSqrt * f15, f16, z, z5);
            return;
        }
        double dSqrt = Math.sqrt(d25);
        double d26 = dSqrt * d20;
        double d27 = dSqrt * d21;
        if (z == z5) {
            d4 = d22 - d27;
            d10 = d23 + d26;
        } else {
            d4 = d22 + d27;
            d10 = d23 - d26;
        }
        double dAtan2 = Math.atan2(d16 - d10, d14 - d4);
        double dAtan22 = Math.atan2(d19 - d10, d18 - d4) - dAtan2;
        if (z5 != (dAtan22 >= 0.0d)) {
            dAtan22 = dAtan22 > 0.0d ? dAtan22 - 6.283185307179586d : dAtan22 + 6.283185307179586d;
        }
        double d28 = d4 * d13;
        double d29 = d10 * d15;
        double d30 = (d28 * dCos) - (d29 * dSin);
        double d31 = (d29 * dCos) + (d28 * dSin);
        int iCeil = (int) Math.ceil(Math.abs((dAtan22 * 4.0d) / 3.141592653589793d));
        double dCos2 = Math.cos(radians);
        double dSin2 = Math.sin(radians);
        double dCos3 = Math.cos(dAtan2);
        double dSin3 = Math.sin(dAtan2);
        double d32 = -d13;
        double d33 = d32 * dCos2;
        double d34 = d15 * dSin2;
        double d35 = (d33 * dSin3) - (d34 * dCos3);
        double d36 = d32 * dSin2;
        double d37 = d15 * dCos2;
        double d38 = dAtan22 / ((double) iCeil);
        double d39 = (dCos3 * d37) + (dSin3 * d36);
        double d40 = d11;
        double d41 = d12;
        int i10 = 0;
        double d42 = dAtan2;
        while (i10 < iCeil) {
            double d43 = d42 + d38;
            double dSin4 = Math.sin(d43);
            double dCos4 = Math.cos(d43);
            int i11 = iCeil;
            double d44 = (((d13 * dCos2) * dCos4) + d30) - (d34 * dSin4);
            double d45 = (d37 * dSin4) + (d13 * dSin2 * dCos4) + d31;
            double d46 = (d33 * dSin4) - (d34 * dCos4);
            double d47 = (dCos4 * d37) + (dSin4 * d36);
            double d48 = d43 - d42;
            double dTan = Math.tan(d48 / 2.0d);
            double dSqrt2 = ((Math.sqrt(((dTan * 3.0d) * dTan) + 4.0d) - 1.0d) * Math.sin(d48)) / 3.0d;
            path.rLineTo(0.0f, 0.0f);
            path.cubicTo((float) ((d35 * dSqrt2) + d40), (float) ((d39 * dSqrt2) + d41), (float) (d44 - (dSqrt2 * d46)), (float) (d45 - (dSqrt2 * d47)), (float) d44, (float) d45);
            i10++;
            d41 = d45;
            dCos2 = dCos2;
            d36 = d36;
            d42 = d43;
            d39 = d47;
            d40 = d44;
            iCeil = i11;
            d35 = d46;
            d38 = d38;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void b(f[] fVarArr, Path path) {
        int i10;
        float[] fArr;
        int i11;
        f fVar;
        int i12;
        char c10;
        float f10;
        float f11;
        f fVar2;
        boolean z;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float f18;
        float f19;
        f[] fVarArr2 = fVarArr;
        Path path2 = path;
        float[] fArr2 = new float[6];
        int length = fVarArr2.length;
        int i13 = 0;
        int i14 = 0;
        char c11 = GMTDateParser.MINUTES;
        while (i14 < length) {
            f fVar3 = fVarArr2[i14];
            char c12 = fVar3.f2101a;
            float[] fArr3 = fVar3.f2102b;
            float f20 = fArr2[i13];
            float f21 = fArr2[1];
            float f22 = fArr2[2];
            float f23 = fArr2[3];
            float f24 = fArr2[4];
            int i15 = i13;
            float f25 = fArr2[5];
            switch (c12) {
                case 'A':
                case 'a':
                    i10 = 7;
                    break;
                case MdtaMetadataEntry.TYPE_INDICATOR_INT32 /* 67 */:
                case 'c':
                    i10 = 6;
                    break;
                case 'H':
                case 'V':
                case MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED /* 104 */:
                case 'v':
                    i10 = 1;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i10 = 4;
                    break;
                case Constants.WATCHED_THRESHOLD /* 90 */:
                case 'z':
                    path2.close();
                    path2.moveTo(f24, f25);
                    f20 = f24;
                    f22 = f20;
                    f21 = f25;
                    f23 = f21;
                default:
                    i10 = 2;
                    break;
            }
            float f26 = f24;
            float f27 = f25;
            float f28 = f20;
            float f29 = f21;
            int i16 = i15;
            while (i16 < fArr3.length) {
                if (c12 == 'A') {
                    fArr = fArr3;
                    i11 = i16;
                    fVar = fVar3;
                    float f30 = f28;
                    float f31 = f29;
                    i12 = i14;
                    c10 = c12;
                    int i17 = i11 + 5;
                    int i18 = i11 + 6;
                    a(path, f30, f31, fArr[i17], fArr[i18], fArr[i11], fArr[i11 + 1], fArr[i11 + 2], fArr[i11 + 3] != 0.0f ? 1 : i15, fArr[i11 + 4] != 0.0f ? 1 : i15);
                    f22 = fArr[i17];
                    f10 = fArr[i18];
                    f23 = f10;
                    f11 = f22;
                } else if (c12 == 'C') {
                    fArr = fArr3;
                    i11 = i16;
                    i12 = i14;
                    fVar = fVar3;
                    c10 = c12;
                    int i19 = i11 + 2;
                    int i20 = i11 + 3;
                    int i21 = i11 + 4;
                    int i22 = i11 + 5;
                    path2.cubicTo(fArr[i11], fArr[i11 + 1], fArr[i19], fArr[i20], fArr[i21], fArr[i22]);
                    float f32 = fArr[i21];
                    float f33 = fArr[i22];
                    f22 = fArr[i19];
                    f23 = fArr[i20];
                    f10 = f33;
                    f11 = f32;
                } else if (c12 == 'H') {
                    fArr = fArr3;
                    i11 = i16;
                    fVar = fVar3;
                    c10 = c12;
                    f10 = f29;
                    i12 = i14;
                    path2.lineTo(fArr[i11], f10);
                    f11 = fArr[i11];
                } else if (c12 == 'Q') {
                    fArr = fArr3;
                    i11 = i16;
                    i12 = i14;
                    fVar = fVar3;
                    c10 = c12;
                    int i23 = i11 + 1;
                    int i24 = i11 + 2;
                    int i25 = i11 + 3;
                    path2.quadTo(fArr[i11], fArr[i23], fArr[i24], fArr[i25]);
                    float f34 = fArr[i11];
                    float f35 = fArr[i23];
                    float f36 = fArr[i24];
                    float f37 = fArr[i25];
                    f22 = f34;
                    f23 = f35;
                    f11 = f36;
                    f10 = f37;
                } else if (c12 == 'V') {
                    fArr = fArr3;
                    i11 = i16;
                    i12 = i14;
                    fVar = fVar3;
                    f11 = f28;
                    c10 = c12;
                    path2.lineTo(f11, fArr[i11]);
                    f10 = fArr[i11];
                } else if (c12 != 'a') {
                    if (c12 == 'c') {
                        fArr = fArr3;
                        i11 = i16;
                        int i26 = i11 + 2;
                        int i27 = i11 + 3;
                        int i28 = i11 + 4;
                        int i29 = i11 + 5;
                        path2.rCubicTo(fArr[i11], fArr[i11 + 1], fArr[i26], fArr[i27], fArr[i28], fArr[i29]);
                        float f38 = fArr[i26] + f28;
                        float f39 = fArr[i27] + f29;
                        f28 += fArr[i28];
                        f29 += fArr[i29];
                        f22 = f38;
                        f23 = f39;
                    } else if (c12 != 'h') {
                        if (c12 != 'q') {
                            if (c12 != 'v') {
                                if (c12 == 'L') {
                                    fArr = fArr3;
                                    i11 = i16;
                                    int i30 = i11 + 1;
                                    path2.lineTo(fArr[i11], fArr[i30]);
                                    f11 = fArr[i11];
                                    f10 = fArr[i30];
                                } else if (c12 == 'M') {
                                    fArr = fArr3;
                                    i11 = i16;
                                    f11 = fArr[i11];
                                    f10 = fArr[i11 + 1];
                                    if (i11 > 0) {
                                        path2.lineTo(f11, f10);
                                    } else {
                                        path2.moveTo(f11, f10);
                                        f26 = f11;
                                        f27 = f10;
                                    }
                                } else if (c12 != 'S') {
                                    if (c12 == 'T') {
                                        fArr = fArr3;
                                        i11 = i16;
                                        if (c11 == 'q' || c11 == 't' || c11 == 'Q' || c11 == 'T') {
                                            f28 = (f28 * 2.0f) - f22;
                                            f29 = (f29 * 2.0f) - f23;
                                        }
                                        int i31 = i11 + 1;
                                        path2.quadTo(f28, f29, fArr[i11], fArr[i31]);
                                        f11 = fArr[i11];
                                        f10 = fArr[i31];
                                        fVar = fVar3;
                                        f22 = f28;
                                        f23 = f29;
                                    } else if (c12 == 'l') {
                                        fArr = fArr3;
                                        i11 = i16;
                                        int i32 = i11 + 1;
                                        path2.rLineTo(fArr[i11], fArr[i32]);
                                        f28 += fArr[i11];
                                        f15 = fArr[i32];
                                    } else if (c12 == 'm') {
                                        fArr = fArr3;
                                        i11 = i16;
                                        float f40 = fArr[i11];
                                        f28 += f40;
                                        float f41 = fArr[i11 + 1];
                                        f29 += f41;
                                        if (i11 > 0) {
                                            path2.rLineTo(f40, f41);
                                        } else {
                                            path2.rMoveTo(f40, f41);
                                            fVar = fVar3;
                                            f11 = f28;
                                            f26 = f11;
                                            f10 = f29;
                                            f27 = f10;
                                        }
                                    } else if (c12 != 's') {
                                        if (c12 != 't') {
                                            fArr = fArr3;
                                            i11 = i16;
                                            fVar = fVar3;
                                            f11 = f28;
                                        } else {
                                            if (c11 == 'q' || c11 == 't' || c11 == 'Q' || c11 == 'T') {
                                                f18 = f28 - f22;
                                                f19 = f29 - f23;
                                            } else {
                                                f19 = 0.0f;
                                                f18 = 0.0f;
                                            }
                                            int i33 = i16 + 1;
                                            path2.rQuadTo(f18, f19, fArr3[i16], fArr3[i33]);
                                            float f42 = f18 + f28;
                                            float f43 = f19 + f29;
                                            float f44 = f28 + fArr3[i16];
                                            f29 += fArr3[i33];
                                            f23 = f43;
                                            fArr = fArr3;
                                            i11 = i16;
                                            fVar = fVar3;
                                            f11 = f44;
                                            f22 = f42;
                                        }
                                        f10 = f29;
                                    } else {
                                        if (c11 == 'c' || c11 == 's' || c11 == 'C' || c11 == 'S') {
                                            f16 = f29 - f23;
                                            f17 = f28 - f22;
                                        } else {
                                            f17 = 0.0f;
                                            f16 = 0.0f;
                                        }
                                        int i34 = i16;
                                        int i35 = i34 + 1;
                                        int i36 = i34 + 2;
                                        int i37 = i34 + 3;
                                        fArr = fArr3;
                                        i11 = i34;
                                        path2.rCubicTo(f17, f16, fArr3[i34], fArr3[i35], fArr3[i36], fArr3[i37]);
                                        f12 = fArr[i11] + f28;
                                        f13 = fArr[i35] + f29;
                                        f28 += fArr[i36];
                                        f14 = fArr[i37];
                                    }
                                    i12 = i14;
                                    c10 = c12;
                                } else {
                                    fArr = fArr3;
                                    i11 = i16;
                                    if (c11 == 'c' || c11 == 's' || c11 == 'C' || c11 == 'S') {
                                        f28 = (f28 * 2.0f) - f22;
                                        f29 = (f29 * 2.0f) - f23;
                                    }
                                    float f45 = f28;
                                    float f46 = f29;
                                    int i38 = i11 + 1;
                                    int i39 = i11 + 2;
                                    int i40 = i11 + 3;
                                    path2.cubicTo(f45, f46, fArr[i11], fArr[i38], fArr[i39], fArr[i40]);
                                    f22 = fArr[i11];
                                    f23 = fArr[i38];
                                    f11 = fArr[i39];
                                    f10 = fArr[i40];
                                }
                                i12 = i14;
                                fVar = fVar3;
                                c10 = c12;
                            } else {
                                fArr = fArr3;
                                i11 = i16;
                                path2.rLineTo(0.0f, fArr[i11]);
                                f15 = fArr[i11];
                            }
                            f29 += f15;
                        } else {
                            fArr = fArr3;
                            i11 = i16;
                            int i41 = i11 + 1;
                            int i42 = i11 + 2;
                            int i43 = i11 + 3;
                            path2.rQuadTo(fArr[i11], fArr[i41], fArr[i42], fArr[i43]);
                            f12 = fArr[i11] + f28;
                            f13 = fArr[i41] + f29;
                            f28 += fArr[i42];
                            f14 = fArr[i43];
                        }
                        f29 += f14;
                        f22 = f12;
                        f23 = f13;
                    } else {
                        fArr = fArr3;
                        i11 = i16;
                        path2.rLineTo(fArr[i11], 0.0f);
                        f28 += fArr[i11];
                    }
                    fVar = fVar3;
                    f11 = f28;
                    f10 = f29;
                    i12 = i14;
                    c10 = c12;
                } else {
                    fArr = fArr3;
                    i11 = i16;
                    int i44 = i11 + 5;
                    float f47 = fArr[i44] + f28;
                    int i45 = i11 + 6;
                    float f48 = fArr[i45] + f29;
                    float f49 = fArr[i11];
                    float f50 = fArr[i11 + 1];
                    float f51 = fArr[i11 + 2];
                    if (fArr[i11 + 3] != 0.0f) {
                        fVar2 = fVar3;
                        z = 1;
                    } else {
                        fVar2 = fVar3;
                        z = i15;
                    }
                    fVar = fVar2;
                    float f52 = f28;
                    c10 = c12;
                    float f53 = f29;
                    i12 = i14;
                    a(path, f52, f53, f47, f48, f49, f50, f51, z, fArr[i11 + 4] != 0.0f ? 1 : i15);
                    f11 = f52 + fArr[i44];
                    f10 = f53 + fArr[i45];
                    f22 = f11;
                    f23 = f10;
                }
                i16 = i11 + i10;
                path2 = path;
                fVar3 = fVar;
                c12 = c10;
                i14 = i12;
                f28 = f11;
                f29 = f10;
                c11 = c12;
                fArr3 = fArr;
            }
            fArr2[i15] = f28;
            fArr2[1] = f29;
            fArr2[2] = f22;
            fArr2[3] = f23;
            fArr2[4] = f26;
            fArr2[5] = f27;
            c11 = fVar3.f2101a;
            i14++;
            fVarArr2 = fVarArr;
            path2 = path;
            i13 = i15;
        }
    }

    public f(f fVar) {
        this.f2101a = fVar.f2101a;
        float[] fArr = fVar.f2102b;
        this.f2102b = o.c(fArr, fArr.length);
    }
}
