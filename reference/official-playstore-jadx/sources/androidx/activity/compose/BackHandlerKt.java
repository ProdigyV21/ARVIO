package androidx.activity.compose;

import androidx.activity.l0;
import androidx.activity.n0;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.y;
import androidx.navigation.compose.h;
import androidx.tv.material3.o0;
import e.d;
import e.e;
import kotlin.Metadata;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a'\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b²\u0006\u0012\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\nX\u008a\u0084\u0002"}, d2 = {"", "enabled", "Lkotlin/Function0;", "Lx6/t0;", "onBack", "BackHandler", "(ZLr7/a;Landroidx/compose/runtime/Composer;II)V", "currentOnBack", "activity-compose_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BackHandlerKt {
    public static final void BackHandler(boolean z, a<t0> aVar, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(-361453782);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i11 & 2) != 0) {
            i12 |= 48;
        } else if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(aVar) ? 32 : 16;
        }
        if ((i12 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                z = true;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-361453782, i12, -1, "androidx.activity.compose.BackHandler (BackHandler.kt:81)");
            }
            State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(aVar, composerStartRestartGroup, (i12 >> 3) & 14);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new e(z, stateRememberUpdatedState);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            e eVar = (e) objRememberedValue;
            boolean z5 = (i12 & 14) == 4;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new o0(1, eVar, z);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            EffectsKt.SideEffect((a) objRememberedValue2, composerStartRestartGroup, 0);
            n0 current = LocalOnBackPressedDispatcherOwner.f929a.getCurrent(composerStartRestartGroup, 6);
            if (current == null) {
                throw new IllegalStateException("No OnBackPressedDispatcherOwner was provided via LocalOnBackPressedDispatcherOwner");
            }
            l0 onBackPressedDispatcher = current.getOnBackPressedDispatcher();
            y yVar = (y) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(onBackPressedDispatcher) | composerStartRestartGroup.changedInstance(yVar);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new h(onBackPressedDispatcher, yVar, eVar, 5);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            EffectsKt.DisposableEffect(yVar, onBackPressedDispatcher, (l) objRememberedValue3, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        boolean z10 = z;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new d(z10, aVar, i10, i11, 0));
        }
    }
}
