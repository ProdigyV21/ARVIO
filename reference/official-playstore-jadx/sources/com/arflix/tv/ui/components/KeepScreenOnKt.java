package com.arflix.tv.ui.components;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.Window;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0019\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"", "active", "Lx6/t0;", "KeepScreenOn", "(ZLandroidx/compose/runtime/Composer;II)V", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class KeepScreenOnKt {
    public static final void KeepScreenOn(final boolean z, Composer composer, final int i10, final int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(1234811015);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i12 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                z = true;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1234811015, i12, -1, "com.arflix.tv.ui.components.KeepScreenOn (KeepScreenOn.kt:19)");
            }
            Context context = (Context) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            View view = (View) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalView());
            Boolean boolValueOf = Boolean.valueOf(z);
            int i14 = i12 & 14;
            boolean zChangedInstance = (i14 == 4) | composerStartRestartGroup.changedInstance(context) | composerStartRestartGroup.changedInstance(view);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new i0(z, context, view, 0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            EffectsKt.DisposableEffect(boolValueOf, context, view, (r7.l) objRememberedValue, composerStartRestartGroup, i14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.components.j0
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return KeepScreenOnKt.KeepScreenOn$lambda$1(z, i10, i11, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult KeepScreenOn$lambda$0$0(boolean z, Context context, final View view, DisposableEffectScope disposableEffectScope) {
        if (!z) {
            return new DisposableEffectResult() { // from class: com.arflix.tv.ui.components.KeepScreenOnKt$KeepScreenOn$lambda$0$0$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                }
            };
        }
        Activity activity = (Activity) ga.r.K(ga.r.I(ga.r.M(context, new com.arflix.tv.data.api.c(5)), new r7.l<Object, Boolean>() { // from class: com.arflix.tv.ui.components.KeepScreenOnKt$KeepScreenOn$lambda$0$0$$inlined$filterIsInstance$1
            @Override // r7.l
            public final Boolean invoke(Object obj) {
                return Boolean.valueOf(obj instanceof Activity);
            }
        }));
        final Window window = activity != null ? activity.getWindow() : null;
        final boolean keepScreenOn = view.getKeepScreenOn();
        view.setKeepScreenOn(true);
        if (window != null) {
            KeepScreenOnRegistry.INSTANCE.acquire(window);
        }
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.components.KeepScreenOnKt$KeepScreenOn$lambda$0$0$$inlined$onDispose$2
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                view.setKeepScreenOn(keepScreenOn);
                Window window2 = window;
                if (window2 != null) {
                    KeepScreenOnRegistry.INSTANCE.release(window2);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Context KeepScreenOn$lambda$0$0$1(Context context) {
        ContextWrapper contextWrapper = context instanceof ContextWrapper ? (ContextWrapper) context : null;
        if (contextWrapper != null) {
            return contextWrapper.getBaseContext();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 KeepScreenOn$lambda$1(boolean z, int i10, int i11, Composer composer, int i12) {
        KeepScreenOn(z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }
}
