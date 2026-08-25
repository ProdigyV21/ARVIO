package androidx.compose.ui.input.pointer.util;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.HistoricalChange;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\u001a3\u0010\t\u001a\u00020\b*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\n\u001a\u0019\u0010\u000e\u001a\u00020\b*\u00020\u000b2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u001b\u0010\u0010\u001a\u00020\b*\u00020\u000b2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0010\u0010\u000f\u001a\u001b\u0010\u0011\u001a\u00020\b*\u00020\u000b2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0011\u0010\u000f\u001a9\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00022\b\b\u0002\u0010\u0017\u001a\u00020\u0012H\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a/\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001bH\u0002¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u0018\u0010 \u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\u0082\b¢\u0006\u0004\b \u0010!\u001a\u001b\u0010#\u001a\u00020\u0006*\u00020\u00122\u0006\u0010\"\u001a\u00020\u0012H\u0002¢\u0006\u0004\b#\u0010$\u001a\u0014\u0010%\u001a\u00020\u0006*\u00020\u0012H\u0082\b¢\u0006\u0004\b%\u0010&\u001a*\u0010*\u001a\f\u0012\b\u0012\u00060\u0012j\u0002`)0\u00002\u0006\u0010'\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u0002H\u0082\b¢\u0006\u0004\b*\u0010+\u001a.\u0010/\u001a\u00020\u0006*\f\u0012\u0004\u0012\u00020\u00120\u0000j\u0002`,2\u0006\u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\u0002H\u0082\n¢\u0006\u0004\b/\u00100\u001a6\u0010\t\u001a\u00020\b*\f\u0012\u0004\u0012\u00020\u00120\u0000j\u0002`,2\u0006\u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\u0006\u00101\u001a\u00020\u0006H\u0082\n¢\u0006\u0004\b\t\u00102\"\u0014\u00103\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b3\u00104\"\u0014\u00105\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b5\u00104\"\u0014\u00106\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b6\u00104\"\u0014\u00107\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b7\u00108\"1\u0010B\u001a\u00020\u001b2\u0006\u00109\u001a\u00020\u001b8G@GX\u0087\u008e\u0002¢\u0006\u0018\n\u0004\b:\u0010;\u0012\u0004\b@\u0010A\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?*\u0018\b\u0002\u0010*\"\b\u0012\u0004\u0012\u00020\u00120\u00002\b\u0012\u0004\u0012\u00020\u00120\u0000*\f\b\u0002\u0010C\"\u00020\u00122\u00020\u0012¨\u0006D"}, d2 = {"", "Landroidx/compose/ui/input/pointer/util/DataPointAtTime;", "", "index", "", "time", "", "dataPoint", "Lx6/t0;", "set", "([Landroidx/compose/ui/input/pointer/util/DataPointAtTime;IJF)V", "Landroidx/compose/ui/input/pointer/util/VelocityTracker;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "event", "addPointerInputChange", "(Landroidx/compose/ui/input/pointer/util/VelocityTracker;Landroidx/compose/ui/input/pointer/PointerInputChange;)V", "addPointerInputChangeLegacy", "addPointerInputChangeWithFix", "", "x", "y", "sampleCount", "degree", "coefficients", "polyFitLeastSquares", "([F[FII[F)[F", "dataPoints", "", "isDataDifferential", "calculateImpulseVelocity", "([F[FIZ)F", "kineticEnergy", "kineticEnergyToVelocity", "(F)F", CmcdData.OBJECT_TYPE_AUDIO_ONLY, TtmlNode.TEXT_EMPHASIS_MARK_DOT, "([F[F)F", "norm", "([F)F", "rows", "cols", "Landroidx/compose/ui/input/pointer/util/Vector;", "Matrix", "(II)[[F", "Landroidx/compose/ui/input/pointer/util/Matrix;", "row", "col", "get", "([[FII)F", "value", "([[FIIF)V", "AssumePointerMoveStoppedMilliseconds", "I", "HistorySize", "HorizonMilliseconds", "DefaultWeight", "F", "<set-?>", "VelocityTrackerAddPointsFix$delegate", "Landroidx/compose/runtime/MutableState;", "getVelocityTrackerAddPointsFix", "()Z", "setVelocityTrackerAddPointsFix", "(Z)V", "getVelocityTrackerAddPointsFix$annotations", "()V", "VelocityTrackerAddPointsFix", "Vector", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VelocityTrackerKt {
    private static final int AssumePointerMoveStoppedMilliseconds = 40;
    private static final float DefaultWeight = 1.0f;
    private static final int HistorySize = 20;
    private static final int HorizonMilliseconds = 100;
    private static final MutableState VelocityTrackerAddPointsFix$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);

    private static final float[][] Matrix(int i10, int i11) {
        float[][] fArr = new float[i10][];
        for (int i12 = 0; i12 < i10; i12++) {
            fArr[i12] = new float[i11];
        }
        return fArr;
    }

    public static final void addPointerInputChange(VelocityTracker velocityTracker, PointerInputChange pointerInputChange) {
        if (getVelocityTrackerAddPointsFix()) {
            addPointerInputChangeWithFix(velocityTracker, pointerInputChange);
        } else {
            addPointerInputChangeLegacy(velocityTracker, pointerInputChange);
        }
    }

    private static final void addPointerInputChangeLegacy(VelocityTracker velocityTracker, PointerInputChange pointerInputChange) {
        if (PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange)) {
            velocityTracker.m4607setCurrentPointerPositionAccumulatork4lQ0M$ui_release(pointerInputChange.getPosition());
            velocityTracker.resetTracking();
        }
        long previousPosition = pointerInputChange.getPreviousPosition();
        List<HistoricalChange> historical = pointerInputChange.getHistorical();
        int size = historical.size();
        int i10 = 0;
        while (i10 < size) {
            HistoricalChange historicalChange = historical.get(i10);
            long jM3240minusMKHz9U = Offset.m3240minusMKHz9U(historicalChange.getPosition(), previousPosition);
            long position = historicalChange.getPosition();
            velocityTracker.m4607setCurrentPointerPositionAccumulatork4lQ0M$ui_release(Offset.m3241plusMKHz9U(velocityTracker.getCurrentPointerPositionAccumulator(), jM3240minusMKHz9U));
            velocityTracker.m4603addPositionUv8p0NA(historicalChange.getUptimeMillis(), velocityTracker.getCurrentPointerPositionAccumulator());
            i10++;
            previousPosition = position;
        }
        velocityTracker.m4607setCurrentPointerPositionAccumulatork4lQ0M$ui_release(Offset.m3241plusMKHz9U(velocityTracker.getCurrentPointerPositionAccumulator(), Offset.m3240minusMKHz9U(pointerInputChange.getPosition(), previousPosition)));
        velocityTracker.m4603addPositionUv8p0NA(pointerInputChange.getUptimeMillis(), velocityTracker.getCurrentPointerPositionAccumulator());
    }

    private static final void addPointerInputChangeWithFix(VelocityTracker velocityTracker, PointerInputChange pointerInputChange) {
        if (PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange)) {
            velocityTracker.resetTracking();
        }
        if (!PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
            List<HistoricalChange> historical = pointerInputChange.getHistorical();
            int size = historical.size();
            for (int i10 = 0; i10 < size; i10++) {
                HistoricalChange historicalChange = historical.get(i10);
                velocityTracker.m4603addPositionUv8p0NA(historicalChange.getUptimeMillis(), historicalChange.getOriginalEventPosition());
            }
            velocityTracker.m4603addPositionUv8p0NA(pointerInputChange.getUptimeMillis(), pointerInputChange.getOriginalEventPosition());
        }
        if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange) && pointerInputChange.getUptimeMillis() - velocityTracker.getLastMoveEventTimeStamp() > 40) {
            velocityTracker.resetTracking();
        }
        velocityTracker.setLastMoveEventTimeStamp$ui_release(pointerInputChange.getUptimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculateImpulseVelocity(float[] fArr, float[] fArr2, int i10, boolean z) {
        float f10 = 0.0f;
        if (i10 < 2) {
            return 0.0f;
        }
        if (i10 == 2) {
            float f11 = fArr2[0];
            float f12 = fArr2[1];
            if (f11 == f12) {
                return 0.0f;
            }
            return (z ? fArr[0] : fArr[0] - fArr[1]) / (f11 - f12);
        }
        int i11 = i10 - 1;
        for (int i12 = i11; i12 > 0; i12--) {
            int i13 = i12 - 1;
            if (fArr2[i12] != fArr2[i13]) {
                float fSignum = Math.signum(f10) * ((float) Math.sqrt(Math.abs(f10) * 2));
                float f13 = (z ? -fArr[i13] : fArr[i12] - fArr[i13]) / (fArr2[i12] - fArr2[i13]);
                float fAbs = (Math.abs(f13) * (f13 - fSignum)) + f10;
                if (i12 == i11) {
                    fAbs *= 0.5f;
                }
                f10 = fAbs;
            }
        }
        return Math.signum(f10) * ((float) Math.sqrt(Math.abs(f10) * 2));
    }

    private static final float dot(float[] fArr, float[] fArr2) {
        int length = fArr.length;
        float f10 = 0.0f;
        for (int i10 = 0; i10 < length; i10++) {
            f10 += fArr[i10] * fArr2[i10];
        }
        return f10;
    }

    private static final float get(float[][] fArr, int i10, int i11) {
        return fArr[i10][i11];
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean getVelocityTrackerAddPointsFix() {
        return ((Boolean) VelocityTrackerAddPointsFix$delegate.getValue()).booleanValue();
    }

    public static /* synthetic */ void getVelocityTrackerAddPointsFix$annotations() {
    }

    private static final float kineticEnergyToVelocity(float f10) {
        return Math.signum(f10) * ((float) Math.sqrt(Math.abs(f10) * 2));
    }

    private static final float norm(float[] fArr) {
        return (float) Math.sqrt(dot(fArr, fArr));
    }

    public static final float[] polyFitLeastSquares(float[] fArr, float[] fArr2, int i10, int i11, float[] fArr3) {
        int i12 = i11;
        if (i12 < 1) {
            throw new IllegalArgumentException("The degree must be at positive integer");
        }
        if (i10 == 0) {
            throw new IllegalArgumentException("At least one point must be provided");
        }
        if (i12 >= i10) {
            i12 = i10 - 1;
        }
        int i13 = i12 + 1;
        float[][] fArr4 = new float[i13][];
        for (int i14 = 0; i14 < i13; i14++) {
            fArr4[i14] = new float[i10];
        }
        for (int i15 = 0; i15 < i10; i15++) {
            fArr4[0][i15] = 1.0f;
            for (int i16 = 1; i16 < i13; i16++) {
                fArr4[i16][i15] = fArr4[i16 - 1][i15] * fArr[i15];
            }
        }
        float[][] fArr5 = new float[i13][];
        for (int i17 = 0; i17 < i13; i17++) {
            fArr5[i17] = new float[i10];
        }
        float[][] fArr6 = new float[i13][];
        for (int i18 = 0; i18 < i13; i18++) {
            fArr6[i18] = new float[i13];
        }
        int i19 = 0;
        while (i19 < i13) {
            float[] fArr7 = fArr5[i19];
            float[] fArr8 = fArr4[i19];
            for (int i20 = 0; i20 < i10; i20++) {
                fArr7[i20] = fArr8[i20];
            }
            for (int i21 = 0; i21 < i19; i21++) {
                float[] fArr9 = fArr5[i21];
                float fDot = dot(fArr7, fArr9);
                for (int i22 = 0; i22 < i10; i22++) {
                    fArr7[i22] = fArr7[i22] - (fArr9[i22] * fDot);
                }
            }
            float fSqrt = (float) Math.sqrt(dot(fArr7, fArr7));
            if (fSqrt < 1.0E-6f) {
                throw new IllegalArgumentException("Vectors are linearly dependent or zero so no solution. TODO(shepshapard), actually determine what this means");
            }
            float f10 = 1.0f / fSqrt;
            for (int i23 = 0; i23 < i10; i23++) {
                fArr7[i23] = fArr7[i23] * f10;
            }
            float[] fArr10 = fArr6[i19];
            int i24 = 0;
            while (i24 < i13) {
                fArr10[i24] = i24 < i19 ? 0.0f : dot(fArr7, fArr4[i24]);
                i24++;
            }
            i19++;
        }
        for (int i25 = i12; -1 < i25; i25--) {
            fArr3[i25] = dot(fArr5[i25], fArr2);
            int i26 = i25 + 1;
            if (i26 <= i12) {
                int i27 = i12;
                while (true) {
                    fArr3[i25] = fArr3[i25] - (fArr6[i25][i27] * fArr3[i27]);
                    if (i27 != i26) {
                        i27--;
                    }
                }
            }
            fArr3[i25] = fArr3[i25] / fArr6[i25][i25];
        }
        return fArr3;
    }

    public static float[] polyFitLeastSquares$default(float[] fArr, float[] fArr2, int i10, int i11, float[] fArr3, int i12, Object obj) {
        if ((i12 & 16) != 0) {
            int i13 = i11 + 1;
            if (i13 < 0) {
                i13 = 0;
            }
            fArr3 = new float[i13];
        }
        return polyFitLeastSquares(fArr, fArr2, i10, i11, fArr3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void set(DataPointAtTime[] dataPointAtTimeArr, int i10, long j10, float f10) {
        DataPointAtTime dataPointAtTime = dataPointAtTimeArr[i10];
        if (dataPointAtTime == null) {
            dataPointAtTimeArr[i10] = new DataPointAtTime(j10, f10);
        } else {
            dataPointAtTime.setTime(j10);
            dataPointAtTime.setDataPoint(f10);
        }
    }

    public static final void setVelocityTrackerAddPointsFix(boolean z) {
        VelocityTrackerAddPointsFix$delegate.setValue(Boolean.valueOf(z));
    }

    private static final void set(float[][] fArr, int i10, int i11, float f10) {
        fArr[i10][i11] = f10;
    }
}
