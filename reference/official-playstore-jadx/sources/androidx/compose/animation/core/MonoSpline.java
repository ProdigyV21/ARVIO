package androidx.compose.animation.core;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J/\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u000bj\b\u0012\u0004\u0012\u00020\u0002`\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ-\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u000bj\b\u0012\u0004\u0012\u00020\u0002`\f2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J?\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ?\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0011H\u0002¢\u0006\u0004\b\u001d\u0010\u001cJ\u001d\u0010!\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0002¢\u0006\u0004\b!\u0010\"J\u001d\u0010!\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\b¢\u0006\u0004\b!\u0010\u0014J\u001d\u0010!\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020#¢\u0006\u0004\b!\u0010$J\u001d\u0010\u0013\u001a\u00020 2\u0006\u0010\u0003\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\"J\u001d\u0010\u0013\u001a\u00020 2\u0006\u0010\u0003\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020#¢\u0006\u0004\b\u0013\u0010$R\u0014\u0010%\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R$\u0010'\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u000bj\b\u0012\u0004\u0012\u00020\u0002`\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R$\u0010)\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u000bj\b\u0012\u0004\u0012\u00020\u0002`\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010(R\u0014\u0010+\u001a\u00020*8\u0002X\u0082D¢\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010&¨\u0006."}, d2 = {"Landroidx/compose/animation/core/MonoSpline;", "", "", "time", "", "y", "<init>", "([FLjava/util/List;)V", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "makeFloatArray", "(II)Ljava/util/ArrayList;", "copyData", "(Ljava/util/List;)Ljava/util/ArrayList;", "", "j", "getSlope", "(FI)F", CmcdData.STREAMING_FORMAT_HLS, "x", "y1", "y2", "t1", "t2", "interpolate", "(FFFFFF)F", "diff", "t", "v", "Lx6/t0;", "getPos", "(F[F)V", "Landroidx/compose/animation/core/AnimationVector;", "(FLandroidx/compose/animation/core/AnimationVector;)V", "timePoints", "[F", "values", "Ljava/util/ArrayList;", "tangents", "", "isExtrapolate", "Z", "slopeTemp", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MonoSpline {
    public static final int $stable = 8;
    private final boolean isExtrapolate = true;
    private final float[] slopeTemp;
    private final ArrayList<float[]> tangents;
    private final float[] timePoints;
    private final ArrayList<float[]> values;

    public MonoSpline(float[] fArr, List<float[]> list) {
        int i10;
        int length = fArr.length;
        int i11 = 0;
        int length2 = list.get(0).length;
        this.slopeTemp = new float[length2];
        int i12 = length - 1;
        ArrayList<float[]> arrayListMakeFloatArray = makeFloatArray(i12, length2);
        ArrayList<float[]> arrayListMakeFloatArray2 = makeFloatArray(length, length2);
        for (int i13 = 0; i13 < length2; i13++) {
            int i14 = 0;
            while (i14 < i12) {
                int i15 = i14 + 1;
                arrayListMakeFloatArray.get(i14)[i13] = (list.get(i15)[i13] - list.get(i14)[i13]) / (fArr[i15] - fArr[i14]);
                if (i14 == 0) {
                    arrayListMakeFloatArray2.get(i14)[i13] = arrayListMakeFloatArray.get(i14)[i13];
                } else {
                    arrayListMakeFloatArray2.get(i14)[i13] = (arrayListMakeFloatArray.get(i14 - 1)[i13] + arrayListMakeFloatArray.get(i14)[i13]) * 0.5f;
                }
                i14 = i15;
            }
            arrayListMakeFloatArray2.get(i12)[i13] = arrayListMakeFloatArray.get(length - 2)[i13];
        }
        int i16 = 0;
        while (i16 < i12) {
            int i17 = i11;
            while (i17 < length2) {
                if (arrayListMakeFloatArray.get(i16)[i17] == 0.0f) {
                    arrayListMakeFloatArray2.get(i16)[i17] = 0.0f;
                    arrayListMakeFloatArray2.get(i16 + 1)[i17] = 0.0f;
                    i10 = length2;
                } else {
                    float f10 = arrayListMakeFloatArray2.get(i16)[i17] / arrayListMakeFloatArray.get(i16)[i17];
                    int i18 = i16 + 1;
                    float f11 = arrayListMakeFloatArray2.get(i18)[i17] / arrayListMakeFloatArray.get(i16)[i17];
                    i10 = length2;
                    float fHypot = (float) Math.hypot(f10, f11);
                    if (fHypot > 9.0d) {
                        float f12 = 3.0f / fHypot;
                        arrayListMakeFloatArray2.get(i16)[i17] = f10 * f12 * arrayListMakeFloatArray.get(i16)[i17];
                        arrayListMakeFloatArray2.get(i18)[i17] = f12 * f11 * arrayListMakeFloatArray.get(i16)[i17];
                    }
                }
                i17++;
                length2 = i10;
            }
            i16++;
            i11 = 0;
        }
        this.timePoints = fArr;
        this.values = copyData(list);
        this.tangents = arrayListMakeFloatArray2;
    }

    private final ArrayList<float[]> copyData(List<float[]> y10) {
        ArrayList<float[]> arrayList = new ArrayList<>();
        arrayList.addAll(y10);
        return arrayList;
    }

    private final float diff(float h3, float x, float y12, float y22, float t12, float t2) {
        float f10 = x * x;
        float f11 = 6;
        float f12 = f11 * x;
        float f13 = (((f11 * f10) * y12) + ((y22 * f12) + (((-6) * f10) * y22))) - (f12 * y12);
        float f14 = 3 * h3;
        return (h3 * t12) + (((((f14 * t12) * f10) + (((f14 * t2) * f10) + f13)) - (((2 * h3) * t2) * x)) - (((4 * h3) * t12) * x));
    }

    private final float interpolate(float h3, float x, float y12, float y22, float t12, float t2) {
        float f10 = x * x;
        float f11 = f10 * x;
        float f12 = 3 * f10;
        float f13 = 2;
        float f14 = t2 * h3;
        float f15 = (f14 * f11) + ((((f13 * f11) * y12) + ((y22 * f12) + (((-2) * f11) * y22))) - (f12 * y12)) + y12;
        float f16 = h3 * t12;
        return (f16 * x) + ((((f11 * f16) + f15) - (f14 * f10)) - (((f13 * h3) * t12) * f10));
    }

    private final ArrayList<float[]> makeFloatArray(int a10, int b10) {
        ArrayList<float[]> arrayList = new ArrayList<>();
        for (int i10 = 0; i10 < a10; i10++) {
            arrayList.add(new float[b10]);
        }
        return arrayList;
    }

    public final void getPos(float t2, float[] v2) {
        int length = this.timePoints.length;
        int i10 = 0;
        int length2 = this.values.get(0).length;
        if (this.isExtrapolate) {
            float[] fArr = this.timePoints;
            float f10 = fArr[0];
            if (t2 <= f10) {
                getSlope(f10, this.slopeTemp);
                for (int i11 = 0; i11 < length2; i11++) {
                    v2[i11] = ((t2 - this.timePoints[0]) * this.slopeTemp[i11]) + this.values.get(0)[i11];
                }
                return;
            }
            int i12 = length - 1;
            float f11 = fArr[i12];
            if (t2 >= f11) {
                getSlope(f11, this.slopeTemp);
                while (i10 < length2) {
                    v2[i10] = ((t2 - this.timePoints[i12]) * this.slopeTemp[i10]) + this.values.get(i12)[i10];
                    i10++;
                }
                return;
            }
        } else {
            float[] fArr2 = this.timePoints;
            if (t2 <= fArr2[0]) {
                for (int i13 = 0; i13 < length2; i13++) {
                    v2[i13] = this.values.get(0)[i13];
                }
                return;
            }
            int i14 = length - 1;
            if (t2 >= fArr2[i14]) {
                while (i10 < length2) {
                    v2[i10] = this.values.get(i14)[i10];
                    i10++;
                }
                return;
            }
        }
        int i15 = length - 1;
        int i16 = 0;
        while (i16 < i15) {
            if (t2 == this.timePoints[i16]) {
                for (int i17 = 0; i17 < length2; i17++) {
                    v2[i17] = this.values.get(i16)[i17];
                }
            }
            float[] fArr3 = this.timePoints;
            int i18 = i16 + 1;
            float f12 = fArr3[i18];
            if (t2 < f12) {
                float f13 = fArr3[i16];
                float f14 = f12 - f13;
                float f15 = (t2 - f13) / f14;
                int i19 = 0;
                while (i19 < length2) {
                    float f16 = f14;
                    v2[i19] = interpolate(f16, f15, this.values.get(i16)[i19], this.values.get(i18)[i19], this.tangents.get(i16)[i19], this.tangents.get(i18)[i19]);
                    i19++;
                    f14 = f16;
                }
                return;
            }
            i16 = i18;
        }
    }

    public final void getSlope(float time, float[] v2) {
        float f10;
        int length = this.timePoints.length;
        int length2 = this.values.get(0).length;
        float[] fArr = this.timePoints;
        float f11 = fArr[0];
        if (time <= f11) {
            f10 = f11;
        } else {
            f10 = fArr[length - 1];
            if (time < f10) {
                f10 = time;
            }
        }
        int i10 = length - 1;
        int i11 = 0;
        while (i11 < i10) {
            float[] fArr2 = this.timePoints;
            int i12 = i11 + 1;
            float f12 = fArr2[i12];
            if (f10 <= f12) {
                float f13 = fArr2[i11];
                float f14 = f12 - f13;
                float f15 = (f10 - f13) / f14;
                int i13 = 0;
                while (i13 < length2) {
                    float f16 = f14;
                    v2[i13] = diff(f16, f15, this.values.get(i11)[i13], this.values.get(i12)[i13], this.tangents.get(i11)[i13], this.tangents.get(i12)[i13]) / f16;
                    i13++;
                    f14 = f16;
                }
                return;
            }
            i11 = i12;
        }
    }

    public final void getSlope(float time, AnimationVector v2) {
        float f10;
        int length = this.timePoints.length;
        int length2 = this.values.get(0).length;
        float[] fArr = this.timePoints;
        float f11 = fArr[0];
        if (time <= f11) {
            f10 = f11;
        } else {
            f10 = fArr[length - 1];
            if (time < f10) {
                f10 = time;
            }
        }
        int i10 = length - 1;
        int i11 = 0;
        while (i11 < i10) {
            float[] fArr2 = this.timePoints;
            int i12 = i11 + 1;
            float f12 = fArr2[i12];
            if (f10 <= f12) {
                float f13 = fArr2[i11];
                float f14 = f12 - f13;
                float f15 = (f10 - f13) / f14;
                int i13 = 0;
                while (i13 < length2) {
                    float f16 = f14;
                    v2.set$animation_core_release(i13, diff(f16, f15, this.values.get(i11)[i13], this.values.get(i12)[i13], this.tangents.get(i11)[i13], this.tangents.get(i12)[i13]) / f16);
                    i13++;
                    f14 = f16;
                }
                return;
            }
            i11 = i12;
        }
    }

    private final float getSlope(float time, int j10) {
        float[] fArr = this.timePoints;
        int length = fArr.length;
        int i10 = 0;
        float f10 = fArr[0];
        if (time < f10) {
            time = f10;
        } else {
            float f11 = fArr[length - 1];
            if (time >= f11) {
                time = f11;
            }
        }
        int i11 = length - 1;
        while (i10 < i11) {
            float[] fArr2 = this.timePoints;
            int i12 = i10 + 1;
            float f12 = fArr2[i12];
            if (time <= f12) {
                float f13 = fArr2[i10];
                float f14 = f12 - f13;
                return diff(f14, (time - f13) / f14, this.values.get(i10)[j10], this.values.get(i12)[j10], this.tangents.get(i10)[j10], this.tangents.get(i12)[j10]) / f14;
            }
            i10 = i12;
        }
        return 0.0f;
    }

    public final float getPos(float t2, int j10) {
        float[] fArr = this.timePoints;
        int length = fArr.length;
        int i10 = 0;
        if (this.isExtrapolate) {
            if (t2 <= fArr[0]) {
                float f10 = this.values.get(0)[j10];
                float f11 = this.timePoints[0];
                return ((t2 - f11) * getSlope(f11, j10)) + f10;
            }
            int i11 = length - 1;
            if (t2 >= fArr[i11]) {
                float f12 = this.values.get(i11)[j10];
                float f13 = this.timePoints[i11];
                return ((t2 - f13) * getSlope(f13, j10)) + f12;
            }
        } else {
            if (t2 <= fArr[0]) {
                return this.values.get(0)[j10];
            }
            int i12 = length - 1;
            if (t2 >= fArr[i12]) {
                return this.values.get(i12)[j10];
            }
        }
        int i13 = length - 1;
        while (i10 < i13) {
            float[] fArr2 = this.timePoints;
            float f14 = fArr2[i10];
            if (t2 == f14) {
                return this.values.get(i10)[j10];
            }
            int i14 = i10 + 1;
            float f15 = fArr2[i14];
            if (t2 < f15) {
                float f16 = f15 - f14;
                return interpolate(f16, (t2 - f14) / f16, this.values.get(i10)[j10], this.values.get(i14)[j10], this.tangents.get(i10)[j10], this.tangents.get(i14)[j10]);
            }
            i10 = i14;
        }
        return 0.0f;
    }

    public final void getPos(float t2, AnimationVector v2) {
        int length = this.timePoints.length;
        int i10 = 0;
        int length2 = this.values.get(0).length;
        if (this.isExtrapolate) {
            float[] fArr = this.timePoints;
            float f10 = fArr[0];
            if (t2 <= f10) {
                getSlope(f10, this.slopeTemp);
                for (int i11 = 0; i11 < length2; i11++) {
                    v2.set$animation_core_release(i11, ((t2 - this.timePoints[0]) * this.slopeTemp[i11]) + this.values.get(0)[i11]);
                }
                return;
            }
            int i12 = length - 1;
            float f11 = fArr[i12];
            if (t2 >= f11) {
                getSlope(f11, this.slopeTemp);
                while (i10 < length2) {
                    v2.set$animation_core_release(i10, ((t2 - this.timePoints[i12]) * this.slopeTemp[i10]) + this.values.get(i12)[i10]);
                    i10++;
                }
                return;
            }
        } else {
            float[] fArr2 = this.timePoints;
            if (t2 <= fArr2[0]) {
                for (int i13 = 0; i13 < length2; i13++) {
                    v2.set$animation_core_release(i13, this.values.get(0)[i13]);
                }
                return;
            }
            int i14 = length - 1;
            if (t2 >= fArr2[i14]) {
                while (i10 < length2) {
                    v2.set$animation_core_release(i10, this.values.get(i14)[i10]);
                    i10++;
                }
                return;
            }
        }
        int i15 = length - 1;
        int i16 = 0;
        while (i16 < i15) {
            if (t2 == this.timePoints[i16]) {
                for (int i17 = 0; i17 < length2; i17++) {
                    v2.set$animation_core_release(i17, this.values.get(i16)[i17]);
                }
            }
            float[] fArr3 = this.timePoints;
            int i18 = i16 + 1;
            float f12 = fArr3[i18];
            if (t2 < f12) {
                float f13 = fArr3[i16];
                float f14 = f12 - f13;
                float f15 = (t2 - f13) / f14;
                int i19 = 0;
                while (i19 < length2) {
                    float f16 = f14;
                    v2.set$animation_core_release(i19, interpolate(f16, f15, this.values.get(i16)[i19], this.values.get(i18)[i19], this.tangents.get(i16)[i19], this.tangents.get(i18)[i19]));
                    i19++;
                    f14 = f16;
                }
                return;
            }
            i16 = i18;
        }
    }
}
