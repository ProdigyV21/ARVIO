package com.arflix.tv.ui.focus;

import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.relocation.BringIntoViewResponderKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRestorerKt;
import androidx.compose.ui.input.key.Key;
import androidx.media3.exoplayer.RendererCapabilities;
import com.arflix.tv.d;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0001\u001a\u0015\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000b\u001a+\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"arvioDpadFocusGroup", "Landroidx/compose/ui/Modifier;", "restoreFocusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "enableFocusRestorer", "", "arvioManualBringIntoViewBoundary", "isArvioDpadNavigationKey", "key", "Landroidx/compose/ui/input/key/Key;", "isArvioDpadNavigationKey-YVgTNJs", "(J)Z", "rememberArvioDpadRepeatGate", "Lcom/arflix/tv/ui/focus/ArvioDpadRepeatGate;", "minRepeatIntervalMs", "", "horizontalMinRepeatIntervalMs", "verticalMinRepeatIntervalMs", "(JJJLandroidx/compose/runtime/Composer;II)Lcom/arflix/tv/ui/focus/ArvioDpadRepeatGate;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ArvioDpadFocusKt {
    public static final Modifier arvioDpadFocusGroup(Modifier modifier, FocusRequester focusRequester, boolean z) {
        return FocusableKt.focusGroup(modifier.then(!z ? Modifier.INSTANCE : focusRequester != null ? FocusRestorerKt.focusRestorer(Modifier.INSTANCE, new d(focusRequester, 1)) : FocusRestorerKt.focusRestorer$default(Modifier.INSTANCE, null, 1, null)));
    }

    public static /* synthetic */ Modifier arvioDpadFocusGroup$default(Modifier modifier, FocusRequester focusRequester, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            focusRequester = null;
        }
        if ((i10 & 2) != 0) {
            z = true;
        }
        return arvioDpadFocusGroup(modifier, focusRequester, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FocusRequester arvioDpadFocusGroup$lambda$0(FocusRequester focusRequester) {
        return focusRequester;
    }

    public static final Modifier arvioManualBringIntoViewBoundary(Modifier modifier) {
        return BringIntoViewResponderKt.bringIntoViewResponder(modifier, ArvioNoOpBringIntoViewResponder.INSTANCE);
    }

    /* JADX INFO: renamed from: isArvioDpadNavigationKey-YVgTNJs, reason: not valid java name */
    public static final boolean m6195isArvioDpadNavigationKeyYVgTNJs(long j10) {
        Key.Companion companion = Key.INSTANCE;
        return Key.m4093equalsimpl0(j10, companion.m4169getDirectionLeftEK5gGoQ()) || Key.m4093equalsimpl0(j10, companion.m4170getDirectionRightEK5gGoQ()) || Key.m4093equalsimpl0(j10, companion.m4171getDirectionUpEK5gGoQ()) || Key.m4093equalsimpl0(j10, companion.m4166getDirectionDownEK5gGoQ());
    }

    public static final ArvioDpadRepeatGate rememberArvioDpadRepeatGate(long j10, long j11, long j12, Composer composer, int i10, int i11) {
        if ((i11 & 1) != 0) {
            j10 = 82;
        }
        if ((i11 & 2) != 0) {
            j11 = j10;
        }
        if ((i11 & 4) != 0) {
            j12 = j10;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1461681426, i10, -1, "com.arflix.tv.ui.focus.rememberArvioDpadRepeatGate (ArvioDpadFocus.kt:89)");
        }
        boolean z = ((((i10 & 112) ^ 48) > 32 && composer.changed(j11)) || (i10 & 48) == 32) | ((((i10 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(j12)) || (i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new ArvioDpadRepeatGate(j11, j12);
            composer.updateRememberedValue(objRememberedValue);
        }
        ArvioDpadRepeatGate arvioDpadRepeatGate = (ArvioDpadRepeatGate) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return arvioDpadRepeatGate;
    }
}
