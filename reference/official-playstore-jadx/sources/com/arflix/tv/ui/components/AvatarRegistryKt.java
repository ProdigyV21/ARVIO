package com.arflix.tv.ui.components;

import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import com.arflix.tv.R;
import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0002\u0010\u0003\u001a!\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"", "raw", "avatarCategoryLabel", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Ljava/lang/String;", "", "avatarId", "Landroidx/compose/ui/Modifier;", "modifier", "Lx6/t0;", "AvatarIcon", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class AvatarRegistryKt {
    public static final void AvatarIcon(int i10, Modifier modifier, Composer composer, int i11, int i12) {
        int i13;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1639485377);
        if ((i11 & 6) == 0) {
            i13 = (composerStartRestartGroup.changed(i10) ? 4 : 2) | i11;
        } else {
            i13 = i11;
        }
        int i14 = i12 & 2;
        if (i14 != 0) {
            i13 |= 48;
        } else if ((i11 & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        if ((i13 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i14 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1639485377, i13, -1, "com.arflix.tv.ui.components.AvatarIcon (AvatarRegistry.kt:251)");
            }
            ImageKt.Image(PainterResources_androidKt.painterResource(AvatarRegistry.INSTANCE.getDrawableRes(i10), composerStartRestartGroup, 0), StringResources_androidKt.stringResource(R.string.component_avatar, composerStartRestartGroup, 0), SizeKt.fillMaxSize$default(modifier, 0.0f, 1, null), (Alignment) null, ContentScale.INSTANCE.getFit(), 0.0f, (ColorFilter) null, composerStartRestartGroup, 24576, MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        Modifier modifier2 = modifier;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new q(i10, modifier2, i11, i12, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AvatarIcon$lambda$0(int i10, Modifier modifier, int i11, int i12, Composer composer, int i13) {
        AvatarIcon(i10, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String avatarCategoryLabel(java.lang.String r3, androidx.compose.runtime.Composer r4, int r5) {
        /*
            boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r0 == 0) goto Lf
            r0 = -1
            java.lang.String r1 = "com.arflix.tv.ui.components.avatarCategoryLabel (AvatarRegistry.kt:239)"
            r2 = 1640549664(0x61c8cd20, float:4.630162E20)
            androidx.compose.runtime.ComposerKt.traceEventStart(r2, r5, r0, r1)
        Lf:
            int r5 = r3.hashCode()
            r0 = 0
            switch(r5) {
                case -1968740153: goto L63;
                case -1056078198: goto L4a;
                case 74219460: goto L31;
                case 807717335: goto L18;
                default: goto L17;
            }
        L17:
            goto L6b
        L18:
            java.lang.String r5 = "Animals"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L21
            goto L6b
        L21:
            r3 = 315231979(0x12ca0eeb, float:1.275167E-27)
            r4.startReplaceGroup(r3)
            int r3 = com.arflix.tv.R.string.avatar_cat_animals
            java.lang.String r3 = androidx.compose.ui.res.StringResources_androidKt.stringResource(r3, r4, r0)
            r4.endReplaceGroup()
            goto L84
        L31:
            java.lang.String r5 = "Media"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L3a
            goto L6b
        L3a:
            r3 = 315236009(0x12ca1ea9, float:1.2755551E-27)
            r4.startReplaceGroup(r3)
            int r3 = com.arflix.tv.R.string.avatar_cat_media
            java.lang.String r3 = androidx.compose.ui.res.StringResources_androidKt.stringResource(r3, r4, r0)
            r4.endReplaceGroup()
            goto L84
        L4a:
            java.lang.String r5 = "Characters"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L53
            goto L6b
        L53:
            r3 = 315234030(0x12ca16ee, float:1.2753645E-27)
            r4.startReplaceGroup(r3)
            int r3 = com.arflix.tv.R.string.avatar_cat_characters
            java.lang.String r3 = androidx.compose.ui.res.StringResources_androidKt.stringResource(r3, r4, r0)
            r4.endReplaceGroup()
            goto L84
        L63:
            java.lang.String r5 = "Nature"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L75
        L6b:
            r5 = 315239587(0x12ca2ca3, float:1.2758996E-27)
            r4.startReplaceGroup(r5)
            r4.endReplaceGroup()
            goto L84
        L75:
            r3 = 315237866(0x12ca25ea, float:1.2757339E-27)
            r4.startReplaceGroup(r3)
            int r3 = com.arflix.tv.R.string.avatar_cat_nature
            java.lang.String r3 = androidx.compose.ui.res.StringResources_androidKt.stringResource(r3, r4, r0)
            r4.endReplaceGroup()
        L84:
            boolean r4 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r4 == 0) goto L8d
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        L8d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.AvatarRegistryKt.avatarCategoryLabel(java.lang.String, androidx.compose.runtime.Composer, int):java.lang.String");
    }
}
