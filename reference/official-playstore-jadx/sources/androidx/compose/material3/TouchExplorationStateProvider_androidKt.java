package androidx.compose.material3;

import android.content.Context;
import android.view.accessibility.AccessibilityManager;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.p;
import androidx.lifecycle.y;
import androidx.media3.exoplayer.RendererCapabilities;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0015\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0001¢\u0006\u0004\b\u0002\u0010\u0003\u001a9\u0010\u000b\u001a\u00020\u0007*\u00020\u00042\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0003¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/compose/runtime/State;", "", "touchExplorationState", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "Landroidx/lifecycle/r;", "Lkotlin/Function1;", "Landroidx/lifecycle/p;", "Lx6/t0;", "handleEvent", "Lkotlin/Function0;", "onDispose", "ObserveState", "(Landroidx/lifecycle/r;Lr7/l;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TouchExplorationStateProvider_androidKt {

    /* JADX INFO: renamed from: androidx.compose.material3.TouchExplorationStateProvider_androidKt$ObserveState$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<p, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(p pVar) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((p) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TouchExplorationStateProvider_androidKt$ObserveState$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements r7.a<t0> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m2316invoke() {
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m2316invoke();
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TouchExplorationStateProvider_androidKt$ObserveState$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements r7.p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ l<p, t0> $handleEvent;
        final /* synthetic */ r7.a<t0> $onDispose;
        final /* synthetic */ androidx.lifecycle.r $this_ObserveState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(androidx.lifecycle.r rVar, l<? super p, t0> lVar, r7.a<t0> aVar, int i10, int i11) {
            super(2);
            this.$this_ObserveState = rVar;
            this.$handleEvent = lVar;
            this.$onDispose = aVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TouchExplorationStateProvider_androidKt.ObserveState(this.$this_ObserveState, this.$handleEvent, this.$onDispose, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ObserveState(androidx.lifecycle.r rVar, l<? super p, t0> lVar, r7.a<t0> aVar, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1703772404);
        if ((Integer.MIN_VALUE & i11) != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changedInstance(rVar) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 |= 48;
        } else if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(lVar) ? 32 : 16;
        }
        int i14 = i11 & 2;
        if (i14 != 0) {
            i12 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(aVar) ? 256 : 128;
        }
        if ((i12 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                lVar = AnonymousClass1.INSTANCE;
            }
            if (i14 != 0) {
                aVar = AnonymousClass2.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1703772404, i12, -1, "androidx.compose.material3.ObserveState (TouchExplorationStateProvider.android.kt:65)");
            }
            composerStartRestartGroup.startReplaceableGroup(-1018043936);
            boolean zChangedInstance = ((i12 & 112) == 32) | composerStartRestartGroup.changedInstance(rVar) | ((i12 & 896) == 256);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new TouchExplorationStateProvider_androidKt$ObserveState$3$1(rVar, lVar, aVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            EffectsKt.DisposableEffect(rVar, (l<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue, composerStartRestartGroup, i12 & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        l<? super p, t0> lVar2 = lVar;
        r7.a<t0> aVar2 = aVar;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass4(rVar, lVar2, aVar2, i10, i11));
        }
    }

    public static final State<Boolean> touchExplorationState(Composer composer, int i10) {
        composer.startReplaceableGroup(-906157724);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-906157724, i10, -1, "androidx.compose.material3.touchExplorationState (TouchExplorationStateProvider.android.kt:39)");
        }
        Context context = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
        composer.startReplaceableGroup(-1014858715);
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = (AccessibilityManager) context.getSystemService("accessibility");
            composer.updateRememberedValue(objRememberedValue);
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) objRememberedValue;
        Object objL = androidx.compose.foundation.c.l(composer, -1014858590);
        if (objL == companion.getEmpty()) {
            objL = new Listener();
            composer.updateRememberedValue(objL);
        }
        Listener listener = (Listener) objL;
        composer.endReplaceableGroup();
        androidx.lifecycle.r lifecycle = ((y) composer.consume(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner())).getLifecycle();
        composer.startReplaceableGroup(-1014858487);
        boolean zChangedInstance = composer.changedInstance(accessibilityManager);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new TouchExplorationStateProvider_androidKt$touchExplorationState$1$1(listener, accessibilityManager);
            composer.updateRememberedValue(objRememberedValue2);
        }
        l lVar = (l) objRememberedValue2;
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(-1014858321);
        boolean zChangedInstance2 = composer.changedInstance(accessibilityManager);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new TouchExplorationStateProvider_androidKt$touchExplorationState$2$1(listener, accessibilityManager);
            composer.updateRememberedValue(objRememberedValue3);
        }
        composer.endReplaceableGroup();
        ObserveState(lifecycle, lVar, (r7.a) objRememberedValue3, composer, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return listener;
    }
}
