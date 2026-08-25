package androidx.compose.material3;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.foundation.gestures.DragScope;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Offset;
import f7.j;
import ka.k0;
import ka.l0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0014\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B=\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0003\u0010\u0005\u001a\u00020\u0004\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\t¢\u0006\u0004\b\u000b\u0010\fJA\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\r2'\u0010\u0014\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000f¢\u0006\u0002\b\u0013H\u0096@¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010#\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u001fH\u0000ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J'\u0010'\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\u0002H\u0002¢\u0006\u0004\b'\u0010(J'\u0010*\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u0002H\u0002¢\u0006\u0004\b*\u0010(R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010+\u001a\u0004\b,\u0010-R\u001f\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010.\u001a\u0004\b/\u00100R\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u00101\u001a\u0004\b2\u00103R+\u0010:\u001a\u00020\u00022\u0006\u00104\u001a\u00020\u00028B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010\u0019R0\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0007\u0018\u00010;8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001a\u0010C\u001a\u00020B8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bC\u0010D\u001a\u0004\bE\u0010FR+\u0010L\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00048B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bG\u0010H\u001a\u0004\bI\u0010-\"\u0004\bJ\u0010KR\"\u0010N\u001a\u00020M8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bN\u0010O\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR+\u0010W\u001a\u00020\u00022\u0006\u00104\u001a\u00020\u00028B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bT\u00106\u001a\u0004\bU\u00108\"\u0004\bV\u0010\u0019R+\u0010\\\u001a\u00020M2\u0006\u00104\u001a\u00020M8@@BX\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bX\u0010Y\u001a\u0004\bZ\u0010Q\"\u0004\b[\u0010SR \u0010]\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b]\u0010.\u001a\u0004\b^\u00100R+\u0010b\u001a\u00020\u00022\u0006\u00104\u001a\u00020\u00028B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b_\u00106\u001a\u0004\b`\u00108\"\u0004\ba\u0010\u0019R+\u0010f\u001a\u00020\u00022\u0006\u00104\u001a\u00020\u00028B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bc\u00106\u001a\u0004\bd\u00108\"\u0004\be\u0010\u0019R\u0014\u0010g\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bg\u0010hR\u0014\u0010j\u001a\u00020i8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bj\u0010kR$\u0010\u0003\u001a\u00020\u00022\u0006\u0010l\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bm\u00108\"\u0004\bn\u0010\u0019R\u0014\u0010p\u001a\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bo\u00108\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006q"}, d2 = {"Landroidx/compose/material3/SliderState;", "Landroidx/compose/foundation/gestures/DraggableState;", "", "value", "", "steps", "Lkotlin/Function0;", "Lx6/t0;", "onValueChangeFinished", "Lx7/e;", "valueRange", "<init>", "(FILr7/a;Lx7/e;)V", "Landroidx/compose/foundation/MutatePriority;", "dragPriority", "Lkotlin/Function2;", "Landroidx/compose/foundation/gestures/DragScope;", "Ld7/d;", "", "Lx6/n;", "block", "drag", "(Landroidx/compose/foundation/MutatePriority;Lr7/p;Ld7/d;)Ljava/lang/Object;", "delta", "dispatchRawDelta", "(F)V", "newThumbWidth", "newTotalWidth", "updateDimensions$material3_release", "(FI)V", "updateDimensions", "Landroidx/compose/ui/geometry/Offset;", "pos", "onPress-k-4lQ0M$material3_release", "(J)V", "onPress", "minPx", "maxPx", "offset", "scaleToUserValue", "(FFF)F", "userValue", "scaleToOffset", "I", "getSteps", "()I", "Lr7/a;", "getOnValueChangeFinished", "()Lr7/a;", "Lx7/e;", "getValueRange", "()Lx7/e;", "<set-?>", "valueState$delegate", "Landroidx/compose/runtime/MutableFloatState;", "getValueState", "()F", "setValueState", "valueState", "Lkotlin/Function1;", "onValueChange", "Lr7/l;", "getOnValueChange$material3_release", "()Lr7/l;", "setOnValueChange$material3_release", "(Lr7/l;)V", "", "tickFractions", "[F", "getTickFractions$material3_release", "()[F", "totalWidth$delegate", "Landroidx/compose/runtime/MutableIntState;", "getTotalWidth", "setTotalWidth", "(I)V", "totalWidth", "", "isRtl", "Z", "isRtl$material3_release", "()Z", "setRtl$material3_release", "(Z)V", "thumbWidth$delegate", "getThumbWidth", "setThumbWidth", "thumbWidth", "isDragging$delegate", "Landroidx/compose/runtime/MutableState;", "isDragging$material3_release", "setDragging", "isDragging", "gestureEndAction", "getGestureEndAction$material3_release", "rawOffset$delegate", "getRawOffset", "setRawOffset", "rawOffset", "pressOffset$delegate", "getPressOffset", "setPressOffset", "pressOffset", "dragScope", "Landroidx/compose/foundation/gestures/DragScope;", "Landroidx/compose/foundation/MutatorMutex;", "scrollMutex", "Landroidx/compose/foundation/MutatorMutex;", "newVal", "getValue", "setValue", "getCoercedValueAsFraction$material3_release", "coercedValueAsFraction", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SliderState implements DraggableState {
    public static final int $stable = 0;
    private final DragScope dragScope;
    private final r7.a<t0> gestureEndAction;

    /* JADX INFO: renamed from: isDragging$delegate, reason: from kotlin metadata */
    private final MutableState isDragging;
    private boolean isRtl;
    private l<? super Float, t0> onValueChange;
    private final r7.a<t0> onValueChangeFinished;

    /* JADX INFO: renamed from: pressOffset$delegate, reason: from kotlin metadata */
    private final MutableFloatState pressOffset;

    /* JADX INFO: renamed from: rawOffset$delegate, reason: from kotlin metadata */
    private final MutableFloatState rawOffset;
    private final MutatorMutex scrollMutex;
    private final int steps;

    /* JADX INFO: renamed from: thumbWidth$delegate, reason: from kotlin metadata */
    private final MutableFloatState thumbWidth;
    private final float[] tickFractions;

    /* JADX INFO: renamed from: totalWidth$delegate, reason: from kotlin metadata */
    private final MutableIntState totalWidth;
    private final x7.e<Float> valueRange;

    /* JADX INFO: renamed from: valueState$delegate, reason: from kotlin metadata */
    private final MutableFloatState valueState;

    /* JADX INFO: renamed from: androidx.compose.material3.SliderState$drag$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.SliderState$drag$2", f = "Slider.kt", l = {1820}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ p<DragScope, d7.d<? super t0>, Object> $block;
        final /* synthetic */ MutatePriority $dragPriority;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(MutatePriority mutatePriority, p<? super DragScope, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$dragPriority = mutatePriority;
            this.$block = pVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return SliderState.this.new AnonymousClass2(this.$dragPriority, this.$block, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SliderState.this.setDragging(true);
                MutatorMutex mutatorMutex = SliderState.this.scrollMutex;
                DragScope dragScope = SliderState.this.dragScope;
                MutatePriority mutatePriority = this.$dragPriority;
                p<DragScope, d7.d<? super t0>, Object> pVar = this.$block;
                this.label = 1;
                Object objMutateWith = mutatorMutex.mutateWith(dragScope, mutatePriority, pVar, this);
                e7.a aVar = e7.a.f15033i;
                if (objMutateWith == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SliderState.this.setDragging(false);
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public SliderState() {
        this(0.0f, 0, null, null, 15, null);
    }

    private final float getPressOffset() {
        return this.pressOffset.getFloatValue();
    }

    private final float getRawOffset() {
        return this.rawOffset.getFloatValue();
    }

    private final float getThumbWidth() {
        return this.thumbWidth.getFloatValue();
    }

    private final int getTotalWidth() {
        return this.totalWidth.getIntValue();
    }

    private final float getValueState() {
        return this.valueState.getFloatValue();
    }

    private final float scaleToOffset(float minPx, float maxPx, float userValue) {
        return SliderKt.scale(((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.b()).floatValue(), userValue, minPx, maxPx);
    }

    private final float scaleToUserValue(float minPx, float maxPx, float offset) {
        return SliderKt.scale(minPx, maxPx, offset, ((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.b()).floatValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDragging(boolean z) {
        this.isDragging.setValue(Boolean.valueOf(z));
    }

    private final void setPressOffset(float f10) {
        this.pressOffset.setFloatValue(f10);
    }

    private final void setRawOffset(float f10) {
        this.rawOffset.setFloatValue(f10);
    }

    private final void setThumbWidth(float f10) {
        this.thumbWidth.setFloatValue(f10);
    }

    private final void setTotalWidth(int i10) {
        this.totalWidth.setIntValue(i10);
    }

    private final void setValueState(float f10) {
        this.valueState.setFloatValue(f10);
    }

    @Override // androidx.compose.foundation.gestures.DraggableState
    public void dispatchRawDelta(float delta) {
        float f10 = 2;
        float fMax = Math.max(getTotalWidth() - (getThumbWidth() / f10), 0.0f);
        float fMin = Math.min(getThumbWidth() / f10, fMax);
        setRawOffset(getRawOffset() + delta + getPressOffset());
        setPressOffset(0.0f);
        float fScaleToUserValue = scaleToUserValue(fMin, fMax, SliderKt.snapValueToTick(getRawOffset(), this.tickFractions, fMin, fMax));
        if (fScaleToUserValue == getValue()) {
            return;
        }
        l<? super Float, t0> lVar = this.onValueChange;
        if (lVar == null) {
            setValue(fScaleToUserValue);
        } else if (lVar != null) {
            lVar.invoke(Float.valueOf(fScaleToUserValue));
        }
    }

    @Override // androidx.compose.foundation.gestures.DraggableState
    public Object drag(MutatePriority mutatePriority, p<? super DragScope, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super t0> dVar) {
        Object objC = l0.c(new AnonymousClass2(mutatePriority, pVar, null), dVar);
        return objC == e7.a.f15033i ? objC : t0.f22605a;
    }

    public final float getCoercedValueAsFraction$material3_release() {
        return SliderKt.calcFraction(((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.b()).floatValue(), qb.d.m(getValue(), ((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.b()).floatValue()));
    }

    public final r7.a<t0> getGestureEndAction$material3_release() {
        return this.gestureEndAction;
    }

    public final l<Float, t0> getOnValueChange$material3_release() {
        return this.onValueChange;
    }

    public final r7.a<t0> getOnValueChangeFinished() {
        return this.onValueChangeFinished;
    }

    public final int getSteps() {
        return this.steps;
    }

    /* JADX INFO: renamed from: getTickFractions$material3_release, reason: from getter */
    public final float[] getTickFractions() {
        return this.tickFractions;
    }

    public final float getValue() {
        return getValueState();
    }

    public final x7.e<Float> getValueRange() {
        return this.valueRange;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isDragging$material3_release() {
        return ((Boolean) this.isDragging.getValue()).booleanValue();
    }

    /* JADX INFO: renamed from: isRtl$material3_release, reason: from getter */
    public final boolean getIsRtl() {
        return this.isRtl;
    }

    /* JADX INFO: renamed from: onPress-k-4lQ0M$material3_release, reason: not valid java name */
    public final void m1912onPressk4lQ0M$material3_release(long pos) {
        setPressOffset((this.isRtl ? getTotalWidth() - Offset.m3236getXimpl(pos) : Offset.m3236getXimpl(pos)) - getRawOffset());
    }

    public final void setOnValueChange$material3_release(l<? super Float, t0> lVar) {
        this.onValueChange = lVar;
    }

    public final void setRtl$material3_release(boolean z) {
        this.isRtl = z;
    }

    public final void setValue(float f10) {
        setValueState(SliderKt.snapValueToTick(qb.d.m(f10, ((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.b()).floatValue()), this.tickFractions, ((Number) this.valueRange.getStart()).floatValue(), ((Number) this.valueRange.b()).floatValue()));
    }

    public final void updateDimensions$material3_release(float newThumbWidth, int newTotalWidth) {
        setThumbWidth(newThumbWidth);
        setTotalWidth(newTotalWidth);
    }

    public SliderState(float f10, int i10, r7.a aVar, x7.e eVar, int i11, kotlin.jvm.internal.h hVar) {
        this((i11 & 1) != 0 ? 0.0f : f10, (i11 & 2) != 0 ? 0 : i10, (i11 & 4) != 0 ? null : aVar, (i11 & 8) != 0 ? new x7.d(0.0f, 1.0f) : eVar);
    }

    public SliderState(float f10, int i10, r7.a<t0> aVar, x7.e<Float> eVar) {
        this.steps = i10;
        this.onValueChangeFinished = aVar;
        this.valueRange = eVar;
        this.valueState = PrimitiveSnapshotStateKt.mutableFloatStateOf(f10);
        this.tickFractions = SliderKt.stepsToTickFractions(i10);
        this.totalWidth = SnapshotIntStateKt.mutableIntStateOf(0);
        this.thumbWidth = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.isDragging = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
        this.gestureEndAction = new SliderState$gestureEndAction$1(this);
        this.rawOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(scaleToOffset(0.0f, 0.0f, f10));
        this.pressOffset = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
        this.dragScope = new DragScope() { // from class: androidx.compose.material3.SliderState$dragScope$1
            @Override // androidx.compose.foundation.gestures.DragScope
            public void dragBy(float pixels) {
                this.this$0.dispatchRawDelta(pixels);
            }
        };
        this.scrollMutex = new MutatorMutex();
    }
}
