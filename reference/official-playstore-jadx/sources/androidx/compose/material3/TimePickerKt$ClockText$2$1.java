package androidx.compose.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TimePickerKt$ClockText$2$1 extends r implements l<SemanticsPropertyReceiver, t0> {
    final /* synthetic */ boolean $autoSwitchToMinute;
    final /* synthetic */ MutableState<Offset> $center$delegate;
    final /* synthetic */ float $maxDist;
    final /* synthetic */ k0 $scope;
    final /* synthetic */ boolean $selected;
    final /* synthetic */ TimePickerState $state;

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockText$2$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements r7.a<Boolean> {
        final /* synthetic */ boolean $autoSwitchToMinute;
        final /* synthetic */ MutableState<Offset> $center$delegate;
        final /* synthetic */ float $maxDist;
        final /* synthetic */ k0 $scope;
        final /* synthetic */ TimePickerState $state;

        /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockText$2$1$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.material3.TimePickerKt$ClockText$2$1$1$1", f = "TimePicker.kt", l = {1496}, m = "invokeSuspend")
        public static final class C01161 extends j implements p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ boolean $autoSwitchToMinute;
            final /* synthetic */ MutableState<Offset> $center$delegate;
            final /* synthetic */ float $maxDist;
            final /* synthetic */ TimePickerState $state;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01161(TimePickerState timePickerState, float f10, boolean z, MutableState<Offset> mutableState, d7.d<? super C01161> dVar) {
                super(2, dVar);
                this.$state = timePickerState;
                this.$maxDist = f10;
                this.$autoSwitchToMinute = z;
                this.$center$delegate = mutableState;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new C01161(this.$state, this.$maxDist, this.$autoSwitchToMinute, this.$center$delegate, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    TimePickerState timePickerState = this.$state;
                    float fM3236getXimpl = Offset.m3236getXimpl(TimePickerKt.ClockText$lambda$28(this.$center$delegate));
                    float fM3237getYimpl = Offset.m3237getYimpl(TimePickerKt.ClockText$lambda$28(this.$center$delegate));
                    float f10 = this.$maxDist;
                    boolean z = this.$autoSwitchToMinute;
                    this.label = 1;
                    Object objOnTap$material3_release = timePickerState.onTap$material3_release(fM3236getXimpl, fM3237getYimpl, f10, z, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objOnTap$material3_release == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                return ((C01161) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(k0 k0Var, TimePickerState timePickerState, float f10, boolean z, MutableState<Offset> mutableState) {
            super(0);
            this.$scope = k0Var;
            this.$state = timePickerState;
            this.$maxDist = f10;
            this.$autoSwitchToMinute = z;
            this.$center$delegate = mutableState;
        }

        @Override // r7.a
        public final Boolean invoke() {
            m0.p(this.$scope, null, 0, new C01161(this.$state, this.$maxDist, this.$autoSwitchToMinute, this.$center$delegate, null), 3);
            return Boolean.TRUE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimePickerKt$ClockText$2$1(boolean z, k0 k0Var, TimePickerState timePickerState, float f10, boolean z5, MutableState<Offset> mutableState) {
        super(1);
        this.$selected = z;
        this.$scope = k0Var;
        this.$state = timePickerState;
        this.$maxDist = f10;
        this.$autoSwitchToMinute = z5;
        this.$center$delegate = mutableState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SemanticsPropertyReceiver) obj);
        return t0.f22605a;
    }

    public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new AnonymousClass1(this.$scope, this.$state, this.$maxDist, this.$autoSwitchToMinute, this.$center$delegate), 1, null);
        SemanticsPropertiesKt.setSelected(semanticsPropertyReceiver, this.$selected);
    }
}
