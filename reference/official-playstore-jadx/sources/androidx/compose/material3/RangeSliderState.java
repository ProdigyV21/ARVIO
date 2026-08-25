package androidx.compose.material3;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b7\b\u0007\u0018\u00002\u00020\u0001BG\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0013\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0016\u001a\u00020\bH\u0000¢\u0006\u0004\b\u0014\u0010\u0015J*\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0019H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ'\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b!\u0010\"R\u001f\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010#\u001a\u0004\b$\u0010%R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010&\u001a\u0004\b'\u0010(R+\u00100\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u00028B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R+\u00104\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u00028B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b1\u0010+\u001a\u0004\b2\u0010-\"\u0004\b3\u0010/R0\u00106\u001a\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\b\u0018\u0001058\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001a\u0010=\u001a\u00020<8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R+\u0010D\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u00028@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bA\u0010+\u001a\u0004\bB\u0010-\"\u0004\bC\u0010/R+\u0010H\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u00028@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bE\u0010+\u001a\u0004\bF\u0010-\"\u0004\bG\u0010/R+\u0010N\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u00058@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bI\u0010J\u001a\u0004\bK\u0010\"\"\u0004\bL\u0010MR+\u0010R\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u00028@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bO\u0010+\u001a\u0004\bP\u0010-\"\u0004\bQ\u0010/R+\u0010V\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u00028@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bS\u0010+\u001a\u0004\bT\u0010-\"\u0004\bU\u0010/R+\u0010]\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u000e8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bW\u0010X\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R&\u0010^\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\b058\u0000X\u0080\u0004¢\u0006\f\n\u0004\b^\u00107\u001a\u0004\b_\u00109R+\u0010\u0018\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u00028B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b`\u0010+\u001a\u0004\ba\u0010-\"\u0004\bb\u0010/R+\u0010\u0017\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u00028B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bc\u0010+\u001a\u0004\bd\u0010-\"\u0004\be\u0010/R$\u0010\u0003\u001a\u00020\u00022\u0006\u0010f\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bg\u0010-\"\u0004\bh\u0010/R$\u0010\u0004\u001a\u00020\u00022\u0006\u0010f\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bi\u0010-\"\u0004\bj\u0010/R\u0014\u0010l\u001a\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bk\u0010-R\u0014\u0010n\u001a\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bm\u0010-R\u0014\u0010p\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bo\u0010\"R\u0014\u0010r\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bq\u0010\"\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006s"}, d2 = {"Landroidx/compose/material3/RangeSliderState;", "", "", "activeRangeStart", "activeRangeEnd", "", "steps", "Lkotlin/Function0;", "Lx6/t0;", "onValueChangeFinished", "Lx7/e;", "valueRange", "<init>", "(FFILr7/a;Lx7/e;)V", "", "isStart", "offset", "onDrag$material3_release", "(ZF)V", "onDrag", "updateMinMaxPx$material3_release", "()V", "updateMinMaxPx", "minPx", "maxPx", "Landroidx/compose/material3/SliderRange;", "scaleToUserValue-owVgs5E", "(FFJ)J", "scaleToUserValue", "userValue", "scaleToOffset", "(FFF)F", "I", "getSteps", "()I", "Lr7/a;", "getOnValueChangeFinished", "()Lr7/a;", "Lx7/e;", "getValueRange", "()Lx7/e;", "<set-?>", "activeRangeStartState$delegate", "Landroidx/compose/runtime/MutableFloatState;", "getActiveRangeStartState", "()F", "setActiveRangeStartState", "(F)V", "activeRangeStartState", "activeRangeEndState$delegate", "getActiveRangeEndState", "setActiveRangeEndState", "activeRangeEndState", "Lkotlin/Function1;", "onValueChange", "Lr7/l;", "getOnValueChange$material3_release", "()Lr7/l;", "setOnValueChange$material3_release", "(Lr7/l;)V", "", "tickFractions", "[F", "getTickFractions$material3_release", "()[F", "startThumbWidth$delegate", "getStartThumbWidth$material3_release", "setStartThumbWidth$material3_release", "startThumbWidth", "endThumbWidth$delegate", "getEndThumbWidth$material3_release", "setEndThumbWidth$material3_release", "endThumbWidth", "totalWidth$delegate", "Landroidx/compose/runtime/MutableIntState;", "getTotalWidth$material3_release", "setTotalWidth$material3_release", "(I)V", "totalWidth", "rawOffsetStart$delegate", "getRawOffsetStart$material3_release", "setRawOffsetStart$material3_release", "rawOffsetStart", "rawOffsetEnd$delegate", "getRawOffsetEnd$material3_release", "setRawOffsetEnd$material3_release", "rawOffsetEnd", "isRtl$delegate", "Landroidx/compose/runtime/MutableState;", "isRtl$material3_release", "()Z", "setRtl$material3_release", "(Z)V", "isRtl", "gestureEndAction", "getGestureEndAction$material3_release", "maxPx$delegate", "getMaxPx", "setMaxPx", "minPx$delegate", "getMinPx", "setMinPx", "newVal", "getActiveRangeStart", "setActiveRangeStart", "getActiveRangeEnd", "setActiveRangeEnd", "getCoercedActiveRangeStartAsFraction$material3_release", "coercedActiveRangeStartAsFraction", "getCoercedActiveRangeEndAsFraction$material3_release", "coercedActiveRangeEndAsFraction", "getStartSteps$material3_release", "startSteps", "getEndSteps$material3_release", "endSteps", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RangeSliderState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: activeRangeEndState$delegate, reason: from kotlin metadata */
    private final MutableFloatState activeRangeEndState;

    /* JADX INFO: renamed from: activeRangeStartState$delegate, reason: from kotlin metadata */
    private final MutableFloatState activeRangeStartState;

    /* JADX INFO: renamed from: endThumbWidth$delegate, reason: from kotlin metadata */
    private final MutableFloatState endThumbWidth;
    private final l<Boolean, t0> gestureEndAction;

    /* JADX INFO: renamed from: isRtl$delegate, reason: from kotlin metadata */
    private final MutableState isRtl;

    /* JADX INFO: renamed from: maxPx$delegate, reason: from kotlin metadata */
    private final MutableFloatState maxPx;

    /* JADX INFO: renamed from: minPx$delegate, reason: from kotlin metadata */
    private final MutableFloatState minPx;
    private l<? super SliderRange, t0> onValueChange;
    private final r7.a<t0> onValueChangeFinished;

    /* JADX INFO: renamed from: rawOffsetEnd$delegate, reason: from kotlin metadata */
    private final MutableFloatState rawOffsetEnd;

    /* JADX INFO: renamed from: rawOffsetStart$delegate, reason: from kotlin metadata */
    private final MutableFloatState rawOffsetStart;

    /* JADX INFO: renamed from: startThumbWidth$delegate, reason: from kotlin metadata */
    private final MutableFloatState startThumbWidth;
    private final int steps;
    private final float[] tickFractions;

    /* JADX INFO: renamed from: totalWidth$delegate, reason: from kotlin metadata */
    private final MutableIntState totalWidth;
    private final x7.e<Float> valueRange;

    public RangeSliderState() {
        this(0.0f, 0.0f, 0, null, null, 31, null);
    }

    private final float getActiveRangeEndState() {
        return this.activeRangeEndState.getFloatValue();
    }

    private final float getActiveRangeStartState() {
        return this.activeRangeStartState.getFloatValue();
    }

    private final float getMaxPx() {
        return this.maxPx.getFloatValue();
    }

    private final float getMinPx() {
        return this.minPx.getFloatValue();
    }

    private final float scaleToOffset(float minPx, float maxPx, float userValue) {
        return SliderKt.scale(((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.b()).floatValue(), userValue, minPx, maxPx);
    }

    /* JADX INFO: renamed from: scaleToUserValue-owVgs5E, reason: not valid java name */
    private final long m1790scaleToUserValueowVgs5E(float minPx, float maxPx, long offset) {
        return SliderKt.m1895scaleziovWd0(minPx, maxPx, offset, ((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.b()).floatValue());
    }

    private final void setActiveRangeEndState(float f10) {
        this.activeRangeEndState.setFloatValue(f10);
    }

    private final void setActiveRangeStartState(float f10) {
        this.activeRangeStartState.setFloatValue(f10);
    }

    private final void setMaxPx(float f10) {
        this.maxPx.setFloatValue(f10);
    }

    private final void setMinPx(float f10) {
        this.minPx.setFloatValue(f10);
    }

    public final float getActiveRangeEnd() {
        return getActiveRangeEndState();
    }

    public final float getActiveRangeStart() {
        return getActiveRangeStartState();
    }

    public final float getCoercedActiveRangeEndAsFraction$material3_release() {
        return SliderKt.calcFraction(((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.b()).floatValue(), getActiveRangeEnd());
    }

    public final float getCoercedActiveRangeStartAsFraction$material3_release() {
        return SliderKt.calcFraction(((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.b()).floatValue(), getActiveRangeStart());
    }

    public final int getEndSteps$material3_release() {
        return (int) Math.floor((1.0f - getCoercedActiveRangeStartAsFraction$material3_release()) * this.steps);
    }

    public final float getEndThumbWidth$material3_release() {
        return this.endThumbWidth.getFloatValue();
    }

    public final l<Boolean, t0> getGestureEndAction$material3_release() {
        return this.gestureEndAction;
    }

    public final l<SliderRange, t0> getOnValueChange$material3_release() {
        return this.onValueChange;
    }

    public final r7.a<t0> getOnValueChangeFinished() {
        return this.onValueChangeFinished;
    }

    public final float getRawOffsetEnd$material3_release() {
        return this.rawOffsetEnd.getFloatValue();
    }

    public final float getRawOffsetStart$material3_release() {
        return this.rawOffsetStart.getFloatValue();
    }

    public final int getStartSteps$material3_release() {
        return (int) Math.floor(getCoercedActiveRangeEndAsFraction$material3_release() * this.steps);
    }

    public final float getStartThumbWidth$material3_release() {
        return this.startThumbWidth.getFloatValue();
    }

    public final int getSteps() {
        return this.steps;
    }

    /* JADX INFO: renamed from: getTickFractions$material3_release, reason: from getter */
    public final float[] getTickFractions() {
        return this.tickFractions;
    }

    public final int getTotalWidth$material3_release() {
        return this.totalWidth.getIntValue();
    }

    public final x7.e<Float> getValueRange() {
        return this.valueRange;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isRtl$material3_release() {
        return ((Boolean) this.isRtl.getValue()).booleanValue();
    }

    public final void onDrag$material3_release(boolean isStart, float offset) {
        long jSliderRange;
        if (isStart) {
            setRawOffsetStart$material3_release(getRawOffsetStart$material3_release() + offset);
            setRawOffsetEnd$material3_release(scaleToOffset(getMinPx(), getMaxPx(), getActiveRangeEnd()));
            float rawOffsetEnd$material3_release = getRawOffsetEnd$material3_release();
            jSliderRange = SliderKt.SliderRange(SliderKt.snapValueToTick(qb.d.m(getRawOffsetStart$material3_release(), getMinPx(), rawOffsetEnd$material3_release), this.tickFractions, getMinPx(), getMaxPx()), rawOffsetEnd$material3_release);
        } else {
            setRawOffsetEnd$material3_release(getRawOffsetEnd$material3_release() + offset);
            setRawOffsetStart$material3_release(scaleToOffset(getMinPx(), getMaxPx(), getActiveRangeStart()));
            float rawOffsetStart$material3_release = getRawOffsetStart$material3_release();
            jSliderRange = SliderKt.SliderRange(rawOffsetStart$material3_release, SliderKt.snapValueToTick(qb.d.m(getRawOffsetEnd$material3_release(), rawOffsetStart$material3_release, getMaxPx()), this.tickFractions, getMinPx(), getMaxPx()));
        }
        long jM1790scaleToUserValueowVgs5E = m1790scaleToUserValueowVgs5E(getMinPx(), getMaxPx(), jSliderRange);
        if (SliderRange.m1904equalsimpl0(jM1790scaleToUserValueowVgs5E, SliderKt.SliderRange(getActiveRangeStart(), getActiveRangeEnd()))) {
            return;
        }
        l<? super SliderRange, t0> lVar = this.onValueChange;
        if (lVar == null) {
            setActiveRangeStart(SliderRange.m1906getStartimpl(jM1790scaleToUserValueowVgs5E));
            setActiveRangeEnd(SliderRange.m1905getEndInclusiveimpl(jM1790scaleToUserValueowVgs5E));
        } else if (lVar != null) {
            lVar.invoke(SliderRange.m1901boximpl(jM1790scaleToUserValueowVgs5E));
        }
    }

    public final void setActiveRangeEnd(float f10) {
        setActiveRangeEndState(SliderKt.snapValueToTick(qb.d.m(f10, getActiveRangeStart(), ((Number) this.valueRange.b()).floatValue()), this.tickFractions, ((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.b()).floatValue()));
    }

    public final void setActiveRangeStart(float f10) {
        setActiveRangeStartState(SliderKt.snapValueToTick(qb.d.m(f10, ((Number) this.valueRange.getStart()).floatValue(), getActiveRangeEnd()), this.tickFractions, ((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.b()).floatValue()));
    }

    public final void setEndThumbWidth$material3_release(float f10) {
        this.endThumbWidth.setFloatValue(f10);
    }

    public final void setOnValueChange$material3_release(l<? super SliderRange, t0> lVar) {
        this.onValueChange = lVar;
    }

    public final void setRawOffsetEnd$material3_release(float f10) {
        this.rawOffsetEnd.setFloatValue(f10);
    }

    public final void setRawOffsetStart$material3_release(float f10) {
        this.rawOffsetStart.setFloatValue(f10);
    }

    public final void setRtl$material3_release(boolean z) {
        this.isRtl.setValue(Boolean.valueOf(z));
    }

    public final void setStartThumbWidth$material3_release(float f10) {
        this.startThumbWidth.setFloatValue(f10);
    }

    public final void setTotalWidth$material3_release(int i10) {
        this.totalWidth.setIntValue(i10);
    }

    public final void updateMinMaxPx$material3_release() {
        float f10 = 2;
        float fMax = Math.max(getTotalWidth$material3_release() - (getEndThumbWidth$material3_release() / f10), 0.0f);
        float fMin = Math.min(getStartThumbWidth$material3_release() / f10, fMax);
        if (getMinPx() == fMin && getMaxPx() == fMax) {
            return;
        }
        setMinPx(fMin);
        setMaxPx(fMax);
        setRawOffsetStart$material3_release(scaleToOffset(getMinPx(), getMaxPx(), getActiveRangeStart()));
        setRawOffsetEnd$material3_release(scaleToOffset(getMinPx(), getMaxPx(), getActiveRangeEnd()));
    }

    public RangeSliderState(float f10, float f11, int i10, r7.a aVar, x7.e eVar, int i11, kotlin.jvm.internal.h hVar) {
        this((i11 & 1) != 0 ? 0.0f : f10, (i11 & 2) != 0 ? 1.0f : f11, (i11 & 4) != 0 ? 0 : i10, (i11 & 8) != 0 ? null : aVar, (i11 & 16) != 0 ? new x7.d(0.0f, 1.0f) : eVar);
    }

    public RangeSliderState(float f10, float f11, int i10, r7.a<t0> aVar, x7.e<Float> eVar) {
        this.steps = i10;
        this.onValueChangeFinished = aVar;
        this.valueRange = eVar;
        this.activeRangeStartState = PrimitiveSnapshotStateKt.mutableFloatStateOf(f10);
        this.activeRangeEndState = PrimitiveSnapshotStateKt.mutableFloatStateOf(f11);
        this.tickFractions = SliderKt.stepsToTickFractions(i10);
        this.startThumbWidth = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.endThumbWidth = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.totalWidth = SnapshotIntStateKt.mutableIntStateOf(0);
        this.rawOffsetStart = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.rawOffsetEnd = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.isRtl = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
        this.gestureEndAction = new RangeSliderState$gestureEndAction$1(this);
        this.maxPx = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.minPx = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
    }
}
