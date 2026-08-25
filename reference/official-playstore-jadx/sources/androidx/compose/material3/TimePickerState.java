package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0005\b\u0007\u0018\u0000 m2\u00020\u0001:\u0001mB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\r\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u000f\u0010\fJ'\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011H\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\"\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00112\b\b\u0002\u0010\u001c\u001a\u00020\u0005H\u0080@¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\"\u001a\u00020\nH\u0080@¢\u0006\u0004\b \u0010!J\u0010\u0010#\u001a\u00020\nH\u0086@¢\u0006\u0004\b#\u0010!J0\u0010'\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u0005H\u0080@¢\u0006\u0004\b%\u0010&J\u0017\u0010(\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0002H\u0002¢\u0006\u0004\b(\u0010)J\u0017\u0010+\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u0011H\u0002¢\u0006\u0004\b+\u0010,J\u0013\u0010-\u001a\u00020\u0002*\u00020\u0011H\u0002¢\u0006\u0004\b-\u0010.J\u0013\u0010/\u001a\u00020\u0002*\u00020\u0011H\u0002¢\u0006\u0004\b/\u0010.R\u0017\u00100\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b0\u00102R!\u00108\u001a\u0002038@X\u0080\u0084\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107R1\u0010@\u001a\u0002092\u0006\u0010:\u001a\u0002098@@@X\u0080\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b;\u0010<\u001a\u0004\b=\u00107\"\u0004\b>\u0010?R1\u0010F\u001a\u00020A2\u0006\u0010:\u001a\u00020A8@@@X\u0080\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bB\u0010<\u001a\u0004\bC\u0010D\"\u0004\bE\u0010\fR+\u0010K\u001a\u00020\u00052\u0006\u0010:\u001a\u00020\u00058@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bG\u0010<\u001a\u0004\bH\u00102\"\u0004\bI\u0010JR+\u0010O\u001a\u00020\u00052\u0006\u0010:\u001a\u00020\u00058@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bL\u0010<\u001a\u0004\bM\u00102\"\u0004\bN\u0010JR+\u0010V\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\u00118@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bP\u0010Q\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR+\u0010Z\u001a\u00020\u00112\u0006\u0010:\u001a\u00020\u00118@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bW\u0010Q\u001a\u0004\bX\u0010S\"\u0004\bY\u0010UR\u0014\u0010\\\u001a\u00020[8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\\\u0010]R\u001b\u0010_\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b^\u00105\u001a\u0004\b_\u00102R&\u0010b\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020a0`8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bb\u0010c\u001a\u0004\bd\u0010eR\u0011\u0010\t\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\bf\u0010DR\u0011\u0010\u000e\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\bg\u0010DR\u0014\u0010(\u001a\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bh\u0010DR\u001a\u0010l\u001a\b\u0012\u0004\u0012\u00020\u00020i8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bj\u0010k\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006n"}, d2 = {"Landroidx/compose/material3/TimePickerState;", "", "", "initialHour", "initialMinute", "", "is24Hour", "<init>", "(IIZ)V", "minute", "Lx6/t0;", "setMinute$material3_release", "(I)V", "setMinute", "hour", "setHour$material3_release", "setHour", "", "x", "y", "maxDist", "moveSelector$material3_release", "(FFF)V", "moveSelector", "value", "isSelected$material3_release", "(I)Z", "isSelected", "fromTap", "update$material3_release", "(FZLd7/d;)Ljava/lang/Object;", "update", "animateToCurrent$material3_release", "(Ld7/d;)Ljava/lang/Object;", "animateToCurrent", "settle", "autoSwitchToMinute", "onTap$material3_release", "(FFFZLd7/d;)Ljava/lang/Object;", "onTap", "hourForDisplay", "(I)I", "angle", "offsetHour", "(F)F", "toHour", "(F)I", "toMinute", "is24hour", "Z", "()Z", "Landroidx/compose/ui/unit/DpOffset;", "selectorPos$delegate", "Landroidx/compose/runtime/State;", "getSelectorPos-RKDOV3M$material3_release", "()J", "selectorPos", "Landroidx/compose/ui/unit/IntOffset;", "<set-?>", "center$delegate", "Landroidx/compose/runtime/MutableState;", "getCenter-nOcc-ac$material3_release", "setCenter--gyyYBs$material3_release", "(J)V", TtmlNode.CENTER, "Landroidx/compose/material3/Selection;", "selection$delegate", "getSelection-JiIwxys$material3_release", "()I", "setSelection-iHAOin8$material3_release", "selection", "isAfternoonToggle$delegate", "isAfternoonToggle$material3_release", "setAfternoonToggle$material3_release", "(Z)V", "isAfternoonToggle", "isInnerCircle$delegate", "isInnerCircle$material3_release", "setInnerCircle$material3_release", "isInnerCircle", "hourAngle$delegate", "Landroidx/compose/runtime/MutableFloatState;", "getHourAngle$material3_release", "()F", "setHourAngle$material3_release", "(F)V", "hourAngle", "minuteAngle$delegate", "getMinuteAngle$material3_release", "setMinuteAngle$material3_release", "minuteAngle", "Landroidx/compose/foundation/MutatorMutex;", "mutex", "Landroidx/compose/foundation/MutatorMutex;", "isAfternoon$delegate", "isAfternoon", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "currentAngle", "Landroidx/compose/animation/core/Animatable;", "getCurrentAngle$material3_release", "()Landroidx/compose/animation/core/Animatable;", "getMinute", "getHour", "getHourForDisplay$material3_release", "", "getValues$material3_release", "()Ljava/util/List;", "values", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TimePickerState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: center$delegate, reason: from kotlin metadata */
    private final MutableState center;
    private final Animatable<Float, AnimationVector1D> currentAngle;

    /* JADX INFO: renamed from: hourAngle$delegate, reason: from kotlin metadata */
    private final MutableFloatState hourAngle;
    private final boolean is24hour;

    /* JADX INFO: renamed from: isAfternoon$delegate, reason: from kotlin metadata */
    private final State isAfternoon;

    /* JADX INFO: renamed from: isAfternoonToggle$delegate, reason: from kotlin metadata */
    private final MutableState isAfternoonToggle;

    /* JADX INFO: renamed from: isInnerCircle$delegate, reason: from kotlin metadata */
    private final MutableState isInnerCircle;

    /* JADX INFO: renamed from: minuteAngle$delegate, reason: from kotlin metadata */
    private final MutableFloatState minuteAngle;
    private final MutatorMutex mutex;

    /* JADX INFO: renamed from: selection$delegate, reason: from kotlin metadata */
    private final MutableState selection;

    /* JADX INFO: renamed from: selectorPos$delegate, reason: from kotlin metadata */
    private final State selectorPos;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\f\u0012\u0004\u0012\u00020\u0005\u0012\u0002\b\u00030\u0004¨\u0006\u0006"}, d2 = {"Landroidx/compose/material3/TimePickerState$Companion;", "", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/TimePickerState;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final Saver<TimePickerState, ?> Saver() {
            return SaverKt.Saver(TimePickerState$Companion$Saver$1.INSTANCE, TimePickerState$Companion$Saver$2.INSTANCE);
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerState$isAfternoon$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements r7.a<Boolean> {
        public AnonymousClass2() {
            super(0);
        }

        @Override // r7.a
        public final Boolean invoke() {
            return Boolean.valueOf((TimePickerState.this.getIs24hour() && TimePickerState.this.isInnerCircle$material3_release()) || TimePickerState.this.isAfternoonToggle$material3_release());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerState$settle$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.material3.TimePickerState", f = "TimePicker.kt", l = {733, 734}, m = "settle")
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TimePickerState.this.settle(this);
        }
    }

    public TimePickerState(int i10, int i11, boolean z) {
        if (i10 < 0 || i10 >= 24) {
            throw new IllegalArgumentException("initialHour should in [0..23] range");
        }
        if (i11 < 0 || i11 >= 60) {
            throw new IllegalArgumentException("initialMinute should be in [0..59] range");
        }
        this.is24hour = z;
        this.selectorPos = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new TimePickerState$selectorPos$2(this, z));
        this.center = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntOffset.m5793boximpl(IntOffset.INSTANCE.m5812getZeronOccac()), null, 2, null);
        this.selection = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Selection.m1862boximpl(Selection.INSTANCE.m1869getHourJiIwxys()), null, 2, null);
        this.isAfternoonToggle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(i10 >= 12 && !z), null, 2, null);
        this.isInnerCircle = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(i10 >= 12), null, 2, null);
        this.hourAngle = PrimitiveSnapshotStateKt.mutableFloatStateOf(((i10 % 12) * 0.5235988f) - 1.5707964f);
        this.minuteAngle = PrimitiveSnapshotStateKt.mutableFloatStateOf((i11 * 0.10471976f) - 1.5707964f);
        this.mutex = new MutatorMutex();
        this.isAfternoon = SnapshotStateKt.derivedStateOf(new AnonymousClass2());
        this.currentAngle = AnimatableKt.Animatable$default(getHourAngle$material3_release(), 0.0f, 2, null);
    }

    private final int hourForDisplay(int hour) {
        if (this.is24hour) {
            return hour % 24;
        }
        if (hour % 12 == 0) {
            return 12;
        }
        return isAfternoon() ? hour - 12 : hour;
    }

    private final boolean isAfternoon() {
        return ((Boolean) this.isAfternoon.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float offsetHour(float angle) {
        float f10 = angle + 1.5707964f;
        return f10 < 0.0f ? f10 + 6.2831855f : f10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int toHour(float f10) {
        return ((int) ((((double) f10) + (((double) 0.2617994f) + 1.5707963267948966d)) / ((double) 0.5235988f))) % 12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int toMinute(float f10) {
        return ((int) ((((double) f10) + (((double) 0.05235988f) + 1.5707963267948966d)) / ((double) 0.10471976f))) % 60;
    }

    public static /* synthetic */ Object update$material3_release$default(TimePickerState timePickerState, float f10, boolean z, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        return timePickerState.update$material3_release(f10, z, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x00ae, code lost:
    
        if (androidx.compose.animation.core.Animatable.animateTo$default(r11, r2, r3, null, null, r6, 12, null) == r9) goto L27;
     */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object animateToCurrent$material3_release(d7.d<? super x6.t0> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof androidx.compose.material3.TimePickerState$animateToCurrent$1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.material3.TimePickerState$animateToCurrent$1 r0 = (androidx.compose.material3.TimePickerState$animateToCurrent$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            androidx.compose.material3.TimePickerState$animateToCurrent$1 r0 = new androidx.compose.material3.TimePickerState$animateToCurrent$1
            r0.<init>(r10, r11)
            goto L12
        L1a:
            java.lang.Object r11 = r6.result
            int r0 = r6.label
            r1 = 2
            r2 = 1
            e7.a r9 = e7.a.f15033i
            if (r0 == 0) goto L3f
            if (r0 == r2) goto L35
            if (r0 != r1) goto L2d
            k2.c.G(r11)
            goto Lb1
        L2d:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L35:
            float r0 = r6.F$0
            java.lang.Object r2 = r6.L$0
            androidx.compose.material3.TimePickerState r2 = (androidx.compose.material3.TimePickerState) r2
            k2.c.G(r11)
            goto L91
        L3f:
            k2.c.G(r11)
            int r11 = r10.m2204getSelectionJiIwxys$material3_release()
            androidx.compose.material3.Selection$Companion r0 = androidx.compose.material3.Selection.INSTANCE
            int r0 = r0.m1869getHourJiIwxys()
            boolean r11 = androidx.compose.material3.Selection.m1865equalsimpl0(r11, r0)
            if (r11 == 0) goto L5f
            float r11 = r10.getMinuteAngle$material3_release()
            float r0 = r10.getHourAngle$material3_release()
            x6.x r11 = androidx.compose.material3.TimePickerKt.access$valuesForAnimation(r11, r0)
            goto L6b
        L5f:
            float r11 = r10.getHourAngle$material3_release()
            float r0 = r10.getMinuteAngle$material3_release()
            x6.x r11 = androidx.compose.material3.TimePickerKt.access$valuesForAnimation(r11, r0)
        L6b:
            java.lang.Object r0 = r11.f22608i
            java.lang.Number r0 = (java.lang.Number) r0
            float r0 = r0.floatValue()
            java.lang.Object r11 = r11.f22609l
            java.lang.Number r11 = (java.lang.Number) r11
            float r11 = r11.floatValue()
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r3 = r10.currentAngle
            java.lang.Float r4 = new java.lang.Float
            r4.<init>(r0)
            r6.L$0 = r10
            r6.F$0 = r11
            r6.label = r2
            java.lang.Object r0 = r3.snapTo(r4, r6)
            if (r0 != r9) goto L8f
            goto Lb0
        L8f:
            r2 = r10
            r0 = r11
        L91:
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r11 = r2.currentAngle
            java.lang.Float r2 = new java.lang.Float
            r2.<init>(r0)
            r0 = 0
            r3 = 6
            r4 = 200(0xc8, float:2.8E-43)
            r5 = 0
            androidx.compose.animation.core.TweenSpec r3 = androidx.compose.animation.core.AnimationSpecKt.tween$default(r4, r0, r5, r3, r5)
            r6.L$0 = r5
            r6.label = r1
            r4 = 0
            r7 = 12
            r8 = 0
            r1 = r11
            java.lang.Object r11 = androidx.compose.animation.core.Animatable.animateTo$default(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r11 != r9) goto Lb1
        Lb0:
            return r9
        Lb1:
            x6.t0 r11 = x6.t0.f22605a
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerState.animateToCurrent$material3_release(d7.d):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getCenter-nOcc-ac$material3_release, reason: not valid java name */
    public final long m2203getCenternOccac$material3_release() {
        return ((IntOffset) this.center.getValue()).getPackedValue();
    }

    public final Animatable<Float, AnimationVector1D> getCurrentAngle$material3_release() {
        return this.currentAngle;
    }

    public final int getHour() {
        return toHour(getHourAngle$material3_release()) + (isAfternoon() ? 12 : 0);
    }

    public final float getHourAngle$material3_release() {
        return this.hourAngle.getFloatValue();
    }

    public final int getHourForDisplay$material3_release() {
        return hourForDisplay(getHour());
    }

    public final int getMinute() {
        return toMinute(getMinuteAngle$material3_release());
    }

    public final float getMinuteAngle$material3_release() {
        return this.minuteAngle.getFloatValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getSelection-JiIwxys$material3_release, reason: not valid java name */
    public final int m2204getSelectionJiIwxys$material3_release() {
        return ((Selection) this.selection.getValue()).m1868unboximpl();
    }

    /* JADX INFO: renamed from: getSelectorPos-RKDOV3M$material3_release, reason: not valid java name */
    public final long m2205getSelectorPosRKDOV3M$material3_release() {
        return ((DpOffset) this.selectorPos.getValue()).getPackedValue();
    }

    public final List<Integer> getValues$material3_release() {
        return Selection.m1865equalsimpl0(m2204getSelectionJiIwxys$material3_release(), Selection.INSTANCE.m1870getMinuteJiIwxys()) ? TimePickerKt.Minutes : TimePickerKt.Hours;
    }

    /* JADX INFO: renamed from: is24hour, reason: from getter */
    public final boolean getIs24hour() {
        return this.is24hour;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isAfternoonToggle$material3_release() {
        return ((Boolean) this.isAfternoonToggle.getValue()).booleanValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isInnerCircle$material3_release() {
        return ((Boolean) this.isInnerCircle.getValue()).booleanValue();
    }

    public final boolean isSelected$material3_release(int value) {
        if (Selection.m1865equalsimpl0(m2204getSelectionJiIwxys$material3_release(), Selection.INSTANCE.m1870getMinuteJiIwxys())) {
            return value == getMinute();
        }
        return getHour() == value + (isAfternoon() ? 12 : 0);
    }

    public final void moveSelector$material3_release(float x, float y10, float maxDist) {
        if (Selection.m1865equalsimpl0(m2204getSelectionJiIwxys$material3_release(), Selection.INSTANCE.m1869getHourJiIwxys()) && this.is24hour) {
            setInnerCircle$material3_release(TimePickerKt.dist(x, y10, IntOffset.m5802getXimpl(m2203getCenternOccac$material3_release()), IntOffset.m5803getYimpl(m2203getCenternOccac$material3_release())) < maxDist);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0101 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object onTap$material3_release(float r14, float r15, float r16, boolean r17, d7.d<? super x6.t0> r18) {
        /*
            Method dump skipped, instruction units count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerState.onTap$material3_release(float, float, float, boolean, d7.d):java.lang.Object");
    }

    public final void setAfternoonToggle$material3_release(boolean z) {
        this.isAfternoonToggle.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: renamed from: setCenter--gyyYBs$material3_release, reason: not valid java name */
    public final void m2206setCentergyyYBs$material3_release(long j10) {
        this.center.setValue(IntOffset.m5793boximpl(j10));
    }

    public final void setHour$material3_release(int hour) {
        setInnerCircle$material3_release(hour >= 12);
        setHourAngle$material3_release(((hour % 12) * 0.5235988f) - 1.5707964f);
    }

    public final void setHourAngle$material3_release(float f10) {
        this.hourAngle.setFloatValue(f10);
    }

    public final void setInnerCircle$material3_release(boolean z) {
        this.isInnerCircle.setValue(Boolean.valueOf(z));
    }

    public final void setMinute$material3_release(int minute) {
        setMinuteAngle$material3_release((minute * 0.10471976f) - 1.5707964f);
    }

    public final void setMinuteAngle$material3_release(float f10) {
        this.minuteAngle.setFloatValue(f10);
    }

    /* JADX INFO: renamed from: setSelection-iHAOin8$material3_release, reason: not valid java name */
    public final void m2207setSelectioniHAOin8$material3_release(int i10) {
        this.selection.setValue(Selection.m1862boximpl(i10));
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0085, code lost:
    
        if (androidx.compose.animation.core.Animatable.animateTo$default(r11, r2, r3, null, null, r6, 12, null) == r9) goto L23;
     */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object settle(d7.d<? super x6.t0> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof androidx.compose.material3.TimePickerState.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r11
            androidx.compose.material3.TimePickerState$settle$1 r0 = (androidx.compose.material3.TimePickerState.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            androidx.compose.material3.TimePickerState$settle$1 r0 = new androidx.compose.material3.TimePickerState$settle$1
            r0.<init>(r11)
            goto L12
        L1a:
            java.lang.Object r11 = r6.result
            int r0 = r6.label
            r1 = 2
            r2 = 1
            e7.a r9 = e7.a.f15033i
            if (r0 == 0) goto L40
            if (r0 == r2) goto L34
            if (r0 != r1) goto L2c
            k2.c.G(r11)
            goto L88
        L2c:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L34:
            java.lang.Object r0 = r6.L$1
            x6.x r0 = (x6.x) r0
            java.lang.Object r2 = r6.L$0
            androidx.compose.material3.TimePickerState r2 = (androidx.compose.material3.TimePickerState) r2
            k2.c.G(r11)
            goto L69
        L40:
            k2.c.G(r11)
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r11 = r10.currentAngle
            java.lang.Object r11 = r11.getValue()
            java.lang.Number r11 = (java.lang.Number) r11
            float r11 = r11.floatValue()
            float r0 = r10.getMinuteAngle$material3_release()
            x6.x r0 = androidx.compose.material3.TimePickerKt.access$valuesForAnimation(r11, r0)
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r11 = r10.currentAngle
            java.lang.Object r3 = r0.f22608i
            r6.L$0 = r10
            r6.L$1 = r0
            r6.label = r2
            java.lang.Object r11 = r11.snapTo(r3, r6)
            if (r11 != r9) goto L68
            goto L87
        L68:
            r2 = r10
        L69:
            androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r11 = r2.currentAngle
            java.lang.Object r2 = r0.f22609l
            r0 = 0
            r3 = 6
            r4 = 200(0xc8, float:2.8E-43)
            r5 = 0
            androidx.compose.animation.core.TweenSpec r3 = androidx.compose.animation.core.AnimationSpecKt.tween$default(r4, r0, r5, r3, r5)
            r6.L$0 = r5
            r6.L$1 = r5
            r6.label = r1
            r4 = 0
            r7 = 12
            r8 = 0
            r1 = r11
            java.lang.Object r11 = androidx.compose.animation.core.Animatable.animateTo$default(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r11 != r9) goto L88
        L87:
            return r9
        L88:
            x6.t0 r11 = x6.t0.f22605a
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerState.settle(d7.d):java.lang.Object");
    }

    public final Object update$material3_release(float f10, boolean z, d7.d<? super t0> dVar) {
        Object objMutate = this.mutex.mutate(MutatePriority.UserInput, new TimePickerState$update$2(this, f10, z, null), dVar);
        return objMutate == e7.a.f15033i ? objMutate : t0.f22605a;
    }
}
