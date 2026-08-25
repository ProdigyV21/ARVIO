package com.arflix.tv.ui.screens.profile;

import android.content.Context;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.tv.material3.IconKt;
import androidx.tv.material3.TextKt;
import coil.compose.SingletonAsyncImageKt;
import coil.request.ImageRequest;
import com.arflix.tv.R;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.data.model.ProfileColors;
import com.arflix.tv.ui.components.AvatarRegistry;
import com.arflix.tv.ui.components.AvatarRegistryKt;
import com.arflix.tv.ui.screens.details.q0;
import com.arflix.tv.ui.screens.player.a1;
import com.arflix.tv.util.ProfileAvatarFiles;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.io.File;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a¿\u0001\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\b\u001a\u00020\u00052\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00002\b\b\u0002\u0010\f\u001a\u00020\u000b2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eH\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001aõ\u0001\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\b\u001a\u00020\u00052\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u00022\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00002\b\b\u0002\u0010\f\u001a\u00020\u000b2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eH\u0007¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u008b\u0002\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\b\u001a\u00020\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\n\u001a\u0004\u0018\u00010\u00002\u0006\u0010\f\u001a\u00020\u000b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\u0006\u0010\u001d\u001a\u00020\u00002\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e2\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eH\u0003¢\u0006\u0004\b\u001e\u0010\u001f\u001a[\u0010'\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u00002\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0006\u0010$\u001a\u00020#H\u0003¢\u0006\u0004\b%\u0010&\u001a3\u0010+\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u000b2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eH\u0003¢\u0006\u0004\b+\u0010,\u001a7\u00101\u001a\u00020\u00032\u0006\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u000b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\b\b\u0002\u00100\u001a\u00020\u000bH\u0003¢\u0006\u0004\b1\u00102\u001aK\u00109\u001a\u00020\u00032\u0006\u00103\u001a\u00020\u00002\u0006\u00104\u001a\u00020\u000b2\b\b\u0002\u00105\u001a\u00020\u000b2\b\b\u0002\u00106\u001a\u00020\u000b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\b\b\u0002\u00108\u001a\u000207H\u0003¢\u0006\u0004\b9\u0010:¨\u0006@²\u0006\u0010\u0010<\u001a\u0004\u0018\u00010;8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010=\u001a\u00020\u00058\n@\nX\u008a\u008e\u0002²\u0006\f\u0010?\u001a\u00020>8\nX\u008a\u0084\u0002²\u0006\u000e\u0010=\u001a\u00020\u00058\n@\nX\u008a\u008e\u0002"}, d2 = {"", ContentDisposition.Parameters.Name, "Lkotlin/Function1;", "Lx6/t0;", "onNameChange", "", "selectedColorIndex", "onColorSelected", "selectedAvatarId", "onAvatarSelected", "selectedAvatarImageUri", "", "useCustomAvatarImage", "onAvatarImageSelected", "Lkotlin/Function0;", "onRemoveAvatarImage", "onConfirm", "onDismiss", "AddProfileDialog", "(Ljava/lang/String;Lr7/l;ILr7/l;ILr7/l;Ljava/lang/String;ZLr7/l;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;III)V", "Lcom/arflix/tv/data/model/Profile;", "profile", "onDelete", "onShowPinSetup", "onRemovePin", "EditProfileDialog", "(Lcom/arflix/tv/data/model/Profile;Ljava/lang/String;Lr7/l;ILr7/l;ILr7/l;Ljava/lang/String;ZLr7/l;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;III)V", LinkHeader.Parameters.Title, "autoFocusNameInput", "confirmLabel", "ProfileDialogContent", "(Ljava/lang/String;ZLjava/lang/String;Lr7/l;ILr7/l;ILr7/l;Ljava/lang/String;ZLr7/l;Lr7/a;Ljava/lang/String;Lr7/a;Lr7/a;Lr7/a;Lcom/arflix/tv/data/model/Profile;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/ui/unit/Dp;", ContentDisposition.Parameters.Size, "iconPadding", "Landroidx/compose/ui/unit/TextUnit;", "letterSize", "ProfileAvatarPreview-slyKXm0", "(Ljava/lang/String;IILjava/lang/String;ZLcom/arflix/tv/data/model/Profile;FFJLandroidx/compose/runtime/Composer;I)V", "ProfileAvatarPreview", "hasCustomAvatar", "onUpload", "onRemove", "AvatarImageButtons", "(ZLr7/a;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "avatarId", "isSelected", "onClick", "isNone", "AvatarGridItem", "(IZLr7/a;ZLandroidx/compose/runtime/Composer;II)V", "text", "isPrimary", "isDestructive", "enabled", "Landroidx/compose/ui/Modifier;", "modifier", "DialogButton", "(Ljava/lang/String;ZZZLr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "Landroid/widget/EditText;", "editTextRef", "isFocused", "", "scale", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ProfileDialogsKt {
    /* JADX WARN: Removed duplicated region for block: B:104:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:158:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AddProfileDialog(final java.lang.String r40, final r7.l<? super java.lang.String, x6.t0> r41, final int r42, final r7.l<? super java.lang.Integer, x6.t0> r43, int r44, r7.l<? super java.lang.Integer, x6.t0> r45, java.lang.String r46, boolean r47, r7.l<? super java.lang.String, x6.t0> r48, r7.a<x6.t0> r49, final r7.a<x6.t0> r50, final r7.a<x6.t0> r51, androidx.compose.runtime.Composer r52, final int r53, final int r54, final int r55) {
        /*
            Method dump skipped, instruction units count: 638
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileDialogsKt.AddProfileDialog(java.lang.String, r7.l, int, r7.l, int, r7.l, java.lang.String, boolean, r7.l, r7.a, r7.a, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AddProfileDialog$lambda$0$0(int i10) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AddProfileDialog$lambda$1$0(String str) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AddProfileDialog$lambda$3(String str, r7.l lVar, int i10, r7.l lVar2, int i11, r7.l lVar3, String str2, boolean z, r7.l lVar4, r7.a aVar, r7.a aVar2, r7.a aVar3, int i12, int i13, int i14, Composer composer, int i15) {
        AddProfileDialog(str, lVar, i10, lVar2, i11, lVar3, str2, z, lVar4, aVar, aVar2, aVar3, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), RecomposeScopeImplKt.updateChangedFlags(i13), i14);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:117:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0131  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void AvatarGridItem(final int r45, final boolean r46, r7.a<x6.t0> r47, boolean r48, androidx.compose.runtime.Composer r49, int r50, int r51) {
        /*
            Method dump skipped, instruction units count: 897
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileDialogsKt.AvatarGridItem(int, boolean, r7.a, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AvatarGridItem$lambda$10(int i10, boolean z, r7.a aVar, boolean z5, int i11, int i12, Composer composer, int i13) {
        AvatarGridItem(i10, z, aVar, z5, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return t0.f22605a;
    }

    private static final float AvatarGridItem$lambda$3(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v5 */
    public static final t0 AvatarGridItem$lambda$4(long j10, long j11, boolean z, int i10, boolean z5, Composer composer, int i11) {
        Integer num;
        ComposeUiNode.Companion companion;
        Modifier.Companion companion2;
        int i12;
        float f10;
        Object obj;
        ?? r62;
        Composer composer2 = composer;
        if ((i11 & 3) == 2 && composer2.getSkipping()) {
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(445943469, i11, -1, "com.arflix.tv.ui.screens.profile.AvatarGridItem.<anonymous> (ProfileDialogs.kt:891)");
            }
            Modifier.Companion companion3 = Modifier.INSTANCE;
            Modifier modifierBackground$default = BackgroundKt.background$default(SizeKt.fillMaxSize$default(companion3, 0.0f, 1, null), Brush.Companion.m3435verticalGradient8A3gB4$default(Brush.INSTANCE, t7.a.E(Color.m3462boximpl(j10), Color.m3462boximpl(j11)), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null);
            Alignment.Companion companion4 = Alignment.INSTANCE;
            Alignment center = companion4.getCenter();
            composer2.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer2, 6);
            composer2.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion5.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierBackground$default);
            if (composer2.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor);
            } else {
                composer2.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer2);
            r7.p pVarU = a0.c.u(companion5, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer2, qVarModifierMaterializerOf, composer2, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            if (z) {
                composer2.startReplaceGroup(-1910416768);
                companion = companion5;
                num = 0;
                TextKt.m6020Text4IGK_g("Aa", null, Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null), TextUnitKt.getSp(20), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 200070, 0, 131026);
                composer2 = composer;
                composer2.endReplaceGroup();
                companion2 = companion3;
                i12 = 1;
                f10 = 0.0f;
                r62 = 0;
                obj = null;
            } else {
                num = 0;
                companion = companion5;
                composer2.startReplaceGroup(-1910192731);
                companion2 = companion3;
                i12 = 1;
                f10 = 0.0f;
                obj = null;
                r62 = 0;
                AvatarRegistryKt.AvatarIcon(i10, PaddingKt.m529padding3ABfNKs(SizeKt.fillMaxSize$default(companion2, 0.0f, 1, null), Dp.m5678constructorimpl(5)), composer2, 48, 0);
                composer2.endReplaceGroup();
            }
            if (z5) {
                composer2.startReplaceGroup(-1909947552);
                Modifier modifierN = com.arflix.tv.data.repository.g.n(SizeKt.fillMaxSize$default(companion2, f10, i12, obj), 10);
                Color.Companion companion6 = Color.INSTANCE;
                Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(modifierN, Color.m3471copywmQWz5c$default(companion6.m3498getBlack0d7_KjU(), 0.35f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null);
                Alignment center2 = companion4.getCenter();
                composer2.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center2, r62, composer2, 6);
                composer2.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, r62);
                CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM179backgroundbw27NRU$default);
                if (composer2.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor2);
                } else {
                    composer2.useNode();
                }
                Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composer2);
                r7.p pVarU2 = a0.c.u(companion, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy2, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                a0.c.y(composer2, qVarModifierMaterializerOf2, composer2, num, 2058660585);
                IconKt.m5992Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.selected, composer2, r62), SizeKt.m578size3ABfNKs(companion2, Dp.m5678constructorimpl(22)), companion6.m3509getWhite0d7_KjU(), composer2, 3456, 0);
                com.arflix.tv.data.repository.g.q(composer2);
            } else {
                composer2.startReplaceGroup(-1909348849);
                composer2.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AvatarGridItem$lambda$5$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AvatarGridItem$lambda$6$0(MutableIntState mutableIntState, FocusState focusState) {
        mutableIntState.setIntValue(focusState.isFocused() ? 1 : 0);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AvatarGridItem$lambda$8$0(MutableIntState mutableIntState, FocusState focusState) {
        mutableIntState.setIntValue(focusState.isFocused() ? 1 : 0);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AvatarGridItem$lambda$9(r7.p pVar, BoxScope boxScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1770620207, i10, -1, "com.arflix.tv.ui.screens.profile.AvatarGridItem.<anonymous> (ProfileDialogs.kt:975)");
            }
            if (a0.c.A(composer, 6, pVar)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    private static final void AvatarImageButtons(boolean z, r7.a<t0> aVar, r7.a<t0> aVar2, Composer composer, int i10) {
        int i11;
        int i12;
        r7.a<t0> aVar3;
        r7.a<t0> aVar4;
        Composer composerStartRestartGroup = composer.startRestartGroup(2015905138);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar2) ? 256 : 128;
        }
        int i13 = i11;
        if ((i13 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            aVar3 = aVar;
            aVar4 = aVar2;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2015905138, i13, -1, "com.arflix.tv.ui.screens.profile.AvatarImageButtons (ProfileDialogs.kt:842)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            MeasurePolicy measurePolicyJ = androidx.compose.foundation.c.j(Alignment.INSTANCE, androidx.compose.foundation.c.g(8, Arrangement.INSTANCE, composerStartRestartGroup, -483455358), composerStartRestartGroup, 6, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyJ, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            if (z) {
                composerStartRestartGroup.startReplaceGroup(-1397353067);
                i12 = R.string.profile_change_photo;
            } else {
                composerStartRestartGroup.startReplaceGroup(-1397351435);
                i12 = R.string.profile_upload_photo;
            }
            String strStringResource = StringResources_androidKt.stringResource(i12, composerStartRestartGroup, 0);
            composerStartRestartGroup.endReplaceGroup();
            DialogButton(strStringResource, false, false, false, aVar, SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), composerStartRestartGroup, ((i13 << 9) & 57344) | 196656, 12);
            if (z) {
                composerStartRestartGroup.startReplaceGroup(-368020953);
                aVar3 = aVar;
                aVar4 = aVar2;
                DialogButton(StringResources_androidKt.stringResource(R.string.profile_remove_photo, composerStartRestartGroup, 0), false, false, false, aVar4, SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), composerStartRestartGroup, ((i13 << 6) & 57344) | 196656, 12);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                aVar3 = aVar;
                aVar4 = aVar2;
                composerStartRestartGroup.startReplaceGroup(-367787430);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new q0(z, aVar3, aVar4, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 AvatarImageButtons$lambda$1(boolean z, r7.a aVar, r7.a aVar2, int i10, Composer composer, int i11) {
        AvatarImageButtons(z, aVar, aVar2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:128:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:159:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0151  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void DialogButton(java.lang.String r34, boolean r35, boolean r36, boolean r37, r7.a<x6.t0> r38, androidx.compose.ui.Modifier r39, androidx.compose.runtime.Composer r40, int r41, int r42) {
        /*
            Method dump skipped, instruction units count: 1020
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileDialogsKt.DialogButton(java.lang.String, boolean, boolean, boolean, r7.a, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 DialogButton$lambda$3(boolean z, String str, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(303550196, i10, -1, "com.arflix.tv.ui.screens.profile.DialogButton.<anonymous> (ProfileDialogs.kt:1009)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer);
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            long sp = TextUnitKt.getSp(13);
            FontWeight medium = FontWeight.INSTANCE.getMedium();
            Color.Companion companion3 = Color.INSTANCE;
            TextKt.m6020Text4IGK_g(str, PaddingKt.m530paddingVpY3zN4(companion, Dp.m5678constructorimpl(18), Dp.m5678constructorimpl(10)), z ? companion3.m3509getWhite0d7_KjU() : Color.m3471copywmQWz5c$default(companion3.m3509getWhite0d7_KjU(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null), sp, null, medium, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 199728, 0, 131024);
            if (androidx.compose.foundation.c.D(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 DialogButton$lambda$4$0(boolean z, r7.a aVar) {
        if (z) {
            aVar.invoke();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 DialogButton$lambda$6$0(boolean z, r7.a aVar) {
        if (z) {
            aVar.invoke();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 DialogButton$lambda$7$0(MutableIntState mutableIntState, FocusState focusState) {
        mutableIntState.setIntValue(focusState.isFocused() ? 1 : 0);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 DialogButton$lambda$8(r7.p pVar, BoxScope boxScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1770378774, i10, -1, "com.arflix.tv.ui.screens.profile.DialogButton.<anonymous> (ProfileDialogs.kt:1073)");
            }
            if (a0.c.A(composer, 6, pVar)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 DialogButton$lambda$9(String str, boolean z, boolean z5, boolean z10, r7.a aVar, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        DialogButton(str, z, z5, z10, aVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:204:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x012a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void EditProfileDialog(final com.arflix.tv.data.model.Profile r35, final java.lang.String r36, final r7.l<? super java.lang.String, x6.t0> r37, final int r38, final r7.l<? super java.lang.Integer, x6.t0> r39, int r40, r7.l<? super java.lang.Integer, x6.t0> r41, java.lang.String r42, boolean r43, r7.l<? super java.lang.String, x6.t0> r44, r7.a<x6.t0> r45, final r7.a<x6.t0> r46, final r7.a<x6.t0> r47, final r7.a<x6.t0> r48, r7.a<x6.t0> r49, r7.a<x6.t0> r50, androidx.compose.runtime.Composer r51, final int r52, final int r53, final int r54) {
        /*
            Method dump skipped, instruction units count: 861
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileDialogsKt.EditProfileDialog(com.arflix.tv.data.model.Profile, java.lang.String, r7.l, int, r7.l, int, r7.l, java.lang.String, boolean, r7.l, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 EditProfileDialog$lambda$0$0(int i10) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 EditProfileDialog$lambda$1$0(String str) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 EditProfileDialog$lambda$5(Profile profile, String str, r7.l lVar, int i10, r7.l lVar2, int i11, r7.l lVar3, String str2, boolean z, r7.l lVar4, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, int i12, int i13, int i14, Composer composer, int i15) {
        EditProfileDialog(profile, str, lVar, i10, lVar2, i11, lVar3, str2, z, lVar4, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), RecomposeScopeImplKt.updateChangedFlags(i13), i14);
        return t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: ProfileAvatarPreview-slyKXm0, reason: not valid java name */
    private static final void m6277ProfileAvatarPreviewslyKXm0(final String str, final int i10, final int i11, final String str2, final boolean z, final Profile profile, final float f10, final float f11, final long j10, Composer composer, final int i12) {
        String str3;
        int i13;
        File fileLocalFile;
        x6.x xVar;
        Composer composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        r7.p<? super Composer, ? super Integer, t0> pVar;
        Uri uri;
        Composer composerStartRestartGroup = composer.startRestartGroup(-120531611);
        if ((i12 & 6) == 0) {
            str3 = str;
            i13 = (composerStartRestartGroup.changed(str3) ? 4 : 2) | i12;
        } else {
            str3 = str;
            i13 = i12;
        }
        if ((i12 & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        if ((i12 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changed(i11) ? 256 : 128;
        }
        if ((i12 & 3072) == 0) {
            i13 |= composerStartRestartGroup.changed(str2) ? 2048 : 1024;
        }
        if ((i12 & 24576) == 0) {
            i13 |= composerStartRestartGroup.changed(z) ? 16384 : 8192;
        }
        if ((196608 & i12) == 0) {
            i13 |= composerStartRestartGroup.changed(profile) ? 131072 : 65536;
        }
        if ((1572864 & i12) == 0) {
            i13 |= composerStartRestartGroup.changed(f10) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((12582912 & i12) == 0) {
            i13 |= composerStartRestartGroup.changed(f11) ? 8388608 : 4194304;
        }
        if ((100663296 & i12) == 0) {
            i13 |= composerStartRestartGroup.changed(j10) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((38347923 & i13) == 38347922 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-120531611, i13, -1, "com.arflix.tv.ui.screens.profile.ProfileAvatarPreview (ProfileDialogs.kt:774)");
            }
            Context context = (Context) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            boolean zChanged = composerStartRestartGroup.changed(profile != null ? Long.valueOf(profile.getAvatarImageVersion()) : null) | composerStartRestartGroup.changed(context) | composerStartRestartGroup.changed(profile != null ? profile.getId() : null) | ((57344 & i13) == 16384) | ((i13 & 7168) == 2048);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (str2 != null || !z || profile == null || (fileLocalFile = ProfileAvatarFiles.INSTANCE.localFile(context, profile)) == null || !fileLocalFile.exists() || fileLocalFile.length() <= 0) ? null : fileLocalFile;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Object obj = (File) objRememberedValue;
            if (str2 != null && (uri = Uri.parse(str2)) != null) {
                obj = uri;
            }
            if (obj != null) {
                composerStartRestartGroup.startReplaceGroup(-163722999);
                SingletonAsyncImageKt.m6053AsyncImage3HmZ8SU(new ImageRequest.Builder(context).data(obj).memoryCacheKey("profile-avatar-preview-" + (profile != null ? profile.getId() : null) + "-" + (profile != null ? Long.valueOf(profile.getAvatarImageVersion()) : null) + "-" + str2).build(), null, BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(SizeKt.m578size3ABfNKs(Modifier.INSTANCE, f10), 12), ColorKt.Color(4280427042L), null, 2, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, composerStartRestartGroup, 1572912, 952);
                composerStartRestartGroup.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final int i14 = 0;
                    final String str4 = str3;
                    pVar = new r7.p() { // from class: com.arflix.tv.ui.screens.profile.j
                        @Override // r7.p
                        public final Object invoke(Object obj2, Object obj3) {
                            switch (i14) {
                                case 0:
                                    return ProfileDialogsKt.ProfileAvatarPreview_slyKXm0$lambda$2(str4, i10, i11, str2, z, profile, f10, f11, j10, i12, (Composer) obj2, ((Integer) obj3).intValue());
                                default:
                                    return ProfileDialogsKt.ProfileAvatarPreview_slyKXm0$lambda$4(str4, i10, i11, str2, z, profile, f10, f11, j10, i12, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        }
                    };
                    scopeUpdateScopeEndRestartGroup.updateScope(pVar);
                }
                return;
            }
            composerStartRestartGroup.startReplaceGroup(-163211747);
            composerStartRestartGroup.endReplaceGroup();
            if (i11 > 0) {
                x6.x xVarGradientColors = AvatarRegistry.INSTANCE.gradientColors(i11);
                xVar = new x6.x(Color.m3462boximpl(((Color) xVarGradientColors.f22608i).m3482unboximpl()), Color.m3462boximpl(((Color) xVarGradientColors.f22609l).m3482unboximpl()));
            } else {
                long jColor = ColorKt.Color(ProfileColors.INSTANCE.getByIndex(i10));
                xVar = new x6.x(Color.m3462boximpl(jColor), Color.m3462boximpl(jColor));
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierBackground$default = BackgroundKt.background$default(com.arflix.tv.data.repository.g.n(SizeKt.m578size3ABfNKs(companion, f10), 12), Brush.Companion.m3435verticalGradient8A3gB4$default(Brush.INSTANCE, t7.a.E(xVar.f22608i, xVar.f22609l), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierBackground$default);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            if (i11 > 0) {
                composerStartRestartGroup.startReplaceGroup(1080903422);
                AvatarRegistryKt.AvatarIcon(i11, PaddingKt.m529padding3ABfNKs(SizeKt.fillMaxSize$default(companion, 0.0f, 1, null), f11), composerStartRestartGroup, (i13 >> 6) & 14, 0);
                composerStartRestartGroup.endReplaceGroup();
                composer2 = composerStartRestartGroup;
            } else {
                composerStartRestartGroup.startReplaceGroup(1081088120);
                Character chA0 = kotlin.text.o.a0(str);
                composer2 = composerStartRestartGroup;
                TextKt.m6020Text4IGK_g(chA0 != null ? String.valueOf(chA0.charValue()).toUpperCase(Locale.ROOT) : "?", null, Color.INSTANCE.m3509getWhite0d7_KjU(), j10, null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, ((i13 >> 15) & 7168) | 196992, 0, 131026);
                composer2.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final int i15 = 1;
            pVar = new r7.p() { // from class: com.arflix.tv.ui.screens.profile.j
                @Override // r7.p
                public final Object invoke(Object obj2, Object obj3) {
                    switch (i15) {
                        case 0:
                            return ProfileDialogsKt.ProfileAvatarPreview_slyKXm0$lambda$2(str, i10, i11, str2, z, profile, f10, f11, j10, i12, (Composer) obj2, ((Integer) obj3).intValue());
                        default:
                            return ProfileDialogsKt.ProfileAvatarPreview_slyKXm0$lambda$4(str, i10, i11, str2, z, profile, f10, f11, j10, i12, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }
            };
            scopeUpdateScopeEndRestartGroup.updateScope(pVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileAvatarPreview_slyKXm0$lambda$2(String str, int i10, int i11, String str2, boolean z, Profile profile, float f10, float f11, long j10, int i12, Composer composer, int i13) {
        m6277ProfileAvatarPreviewslyKXm0(str, i10, i11, str2, z, profile, f10, f11, j10, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileAvatarPreview_slyKXm0$lambda$4(String str, int i10, int i11, String str2, boolean z, Profile profile, float f10, float f11, long j10, int i12, Composer composer, int i13) {
        m6277ProfileAvatarPreviewslyKXm0(str, i10, i11, str2, z, profile, f10, f11, j10, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1));
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:170:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x038f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void ProfileDialogContent(final java.lang.String r38, final boolean r39, final java.lang.String r40, final r7.l<? super java.lang.String, x6.t0> r41, final int r42, final r7.l<? super java.lang.Integer, x6.t0> r43, final int r44, final r7.l<? super java.lang.Integer, x6.t0> r45, final java.lang.String r46, final boolean r47, final r7.l<? super java.lang.String, x6.t0> r48, final r7.a<x6.t0> r49, final java.lang.String r50, final r7.a<x6.t0> r51, final r7.a<x6.t0> r52, final r7.a<x6.t0> r53, com.arflix.tv.data.model.Profile r54, r7.a<x6.t0> r55, r7.a<x6.t0> r56, androidx.compose.runtime.Composer r57, final int r58, final int r59, final int r60) {
        /*
            Method dump skipped, instruction units count: 976
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileDialogsKt.ProfileDialogContent(java.lang.String, boolean, java.lang.String, r7.l, int, r7.l, int, r7.l, java.lang.String, boolean, r7.l, r7.a, java.lang.String, r7.a, r7.a, r7.a, com.arflix.tv.data.model.Profile, r7.a, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    private static final void ProfileDialogContent$hideKeyboard(MutableState<EditText> mutableState, Context context, EditText editText) {
        if (editText != null) {
            editText.post(new l(context, editText, 0));
        }
    }

    public static /* synthetic */ void ProfileDialogContent$hideKeyboard$default(MutableState mutableState, Context context, EditText editText, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            editText = ProfileDialogContent$lambda$1(mutableState);
        }
        ProfileDialogContent$hideKeyboard(mutableState, context, editText);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ProfileDialogContent$hideKeyboard$lambda$5(Context context, EditText editText) {
        Object systemService = context.getSystemService("input_method");
        InputMethodManager inputMethodManager = systemService instanceof InputMethodManager ? (InputMethodManager) systemService : null;
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
        editText.clearFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EditText ProfileDialogContent$lambda$1(MutableState<EditText> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0665  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x06ba  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x06c6  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x06ca  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x06ee  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0713  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0779  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0785  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0789  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x07af  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x07df  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0819  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x087c  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x08bf  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x03a1  */
    /* JADX WARN: Type inference failed for: r7v44 */
    /* JADX WARN: Type inference failed for: r7v45, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r7v78 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 ProfileDialogContent$lambda$10(boolean r92, android.content.res.Configuration r93, java.lang.String r94, final java.lang.String r95, int r96, int r97, java.lang.String r98, boolean r99, com.arflix.tv.data.model.Profile r100, boolean r101, final android.content.Context r102, final e.g r103, r7.a r104, r7.a r105, r7.a r106, androidx.compose.runtime.MutableState r107, final java.lang.String r108, final r7.l r109, final r7.a r110, final androidx.compose.ui.focus.FocusRequester r111, java.lang.String r112, r7.a r113, r7.a r114, r7.l r115, androidx.compose.runtime.Composer r116, int r117) {
        /*
            Method dump skipped, instruction units count: 5349
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileDialogsKt.ProfileDialogContent$lambda$10(boolean, android.content.res.Configuration, java.lang.String, java.lang.String, int, int, java.lang.String, boolean, com.arflix.tv.data.model.Profile, boolean, android.content.Context, e.g, r7.a, r7.a, r7.a, androidx.compose.runtime.MutableState, java.lang.String, r7.l, r7.a, androidx.compose.ui.focus.FocusRequester, java.lang.String, r7.a, r7.a, r7.l, androidx.compose.runtime.Composer, int):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$0$0$0(e.g gVar, MutableState mutableState, Context context) {
        ProfileDialogContent$launchAvatarPicker(gVar, mutableState, context);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$0$1$0(MutableState mutableState, Context context) {
        EditText editTextProfileDialogContent$lambda$1 = ProfileDialogContent$lambda$1(mutableState);
        if (editTextProfileDialogContent$lambda$1 != null) {
            editTextProfileDialogContent$lambda$1.requestFocus();
            editTextProfileDialogContent$lambda$1.postDelayed(new l(editTextProfileDialogContent$lambda$1, context, 2), 100L);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EditText ProfileDialogContent$lambda$10$0$0$2$0$0(String str, String str2, MutableState mutableState, final r7.l lVar, r7.a aVar, Context context, FocusRequester focusRequester, Context context2) {
        EditText editText = new EditText(context2);
        mutableState.setValue(editText);
        editText.setText(str);
        editText.setTextColor(-1);
        editText.setHintTextColor(-7829368);
        editText.setHint(str2);
        editText.setTextSize(16.0f);
        editText.setBackground(null);
        editText.setPadding(36, 32, 36, 32);
        editText.setSingleLine(true);
        editText.setInputType(1);
        editText.setImeOptions(6);
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.addTextChangedListener(new TextWatcher() { // from class: com.arflix.tv.ui.screens.profile.ProfileDialogsKt$ProfileDialogContent$lambda$10$0$0$2$0$0$0$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s10) {
                String string;
                r7.l lVar2 = lVar;
                if (s10 == null || (string = s10.toString()) == null) {
                    string = "";
                }
                lVar2.invoke(string);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }
        });
        editText.setOnEditorActionListener(new q(editText, aVar, mutableState, context, focusRequester, 1));
        return editText;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ProfileDialogContent$lambda$10$0$0$2$0$0$0$1(EditText editText, r7.a aVar, MutableState mutableState, Context context, FocusRequester focusRequester, TextView textView, int i10, KeyEvent keyEvent) {
        String string;
        boolean z = i10 == 6;
        boolean z5 = keyEvent != null && keyEvent.getKeyCode() == 66 && keyEvent.getAction() == 1;
        if (!z && !z5) {
            return false;
        }
        ProfileDialogContent$hideKeyboard(mutableState, context, editText);
        Editable text = editText.getText();
        if (text == null || (string = text.toString()) == null || !(!kotlin.text.o.h0(string))) {
            try {
                focusRequester.requestFocus();
            } catch (Throwable unused) {
            }
        } else {
            aVar.invoke();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$0$2$1$0(String str, EditText editText) {
        if (!kotlin.jvm.internal.p.a(editText.getText().toString(), str)) {
            editText.setText(str);
            editText.setSelection(str.length());
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$0$3$0$0$0(r7.a aVar, MutableState mutableState, Context context) {
        ProfileDialogContent$hideKeyboard$default(mutableState, context, null, 4, null);
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$0$3$0$1$0(r7.a aVar, MutableState mutableState, Context context) {
        ProfileDialogContent$hideKeyboard$default(mutableState, context, null, 4, null);
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$0$3$0$2$0(r7.a aVar, MutableState mutableState, Context context) {
        ProfileDialogContent$hideKeyboard$default(mutableState, context, null, 4, null);
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$0$4$0$0(r7.a aVar, MutableState mutableState, Context context) {
        ProfileDialogContent$hideKeyboard$default(mutableState, context, null, 4, null);
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$0$4$1$0$0(r7.a aVar, MutableState mutableState, Context context) {
        ProfileDialogContent$hideKeyboard$default(mutableState, context, null, 4, null);
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$0$4$1$1$0(r7.a aVar, MutableState mutableState, Context context) {
        ProfileDialogContent$hideKeyboard$default(mutableState, context, null, 4, null);
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$0$5$0$0(int i10, List list, int i11, r7.l lVar, LazyListScope lazyListScope) {
        if (i10 == 0) {
            LazyListScope.CC.i(lazyListScope, null, null, ComposableLambdaKt.composableLambdaInstance(-113518186, true, new v(i11, 0, lVar)), 3, null);
        }
        LazyListScope.CC.k(lazyListScope, list.size(), null, null, ComposableLambdaKt.composableLambdaInstance(-1563070104, true, new w(i11, 0, list, lVar)), 6, null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$0$5$0$0$0(int i10, r7.l lVar, LazyItemScope lazyItemScope, Composer composer, int i11) {
        if ((i11 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-113518186, i11, -1, "com.arflix.tv.ui.screens.profile.ProfileDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ProfileDialogs.kt:486)");
            }
            boolean z = i10 == 0;
            boolean zChanged = composer.changed(lVar);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new g(lVar, 1);
                composer.updateRememberedValue(objRememberedValue);
            }
            AvatarGridItem(0, z, (r7.a) objRememberedValue, true, composer, 3078, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$0$5$0$0$0$0$0(r7.l lVar) {
        lVar.invoke(0);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$0$5$0$0$1(List list, int i10, r7.l lVar, LazyItemScope lazyItemScope, int i11, Composer composer, int i12) {
        if ((i12 & 48) == 0) {
            i12 |= composer.changed(i11) ? 32 : 16;
        }
        if ((i12 & 145) == 144 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1563070104, i12, -1, "com.arflix.tv.ui.screens.profile.ProfileDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ProfileDialogs.kt:495)");
            }
            int iIntValue = ((Number) list.get(i11)).intValue();
            boolean z = i10 == iIntValue;
            boolean zChanged = composer.changed(lVar) | composer.changed(iIntValue);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new a1(iIntValue, 2, lVar);
                composer.updateRememberedValue(objRememberedValue);
            }
            AvatarGridItem(iIntValue, z, (r7.a) objRememberedValue, false, composer, 0, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$0$5$0$0$1$0$0(r7.l lVar, int i10) {
        lVar.invoke(Integer.valueOf(i10));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$1$0$0$0(e.g gVar, MutableState mutableState, Context context) {
        ProfileDialogContent$launchAvatarPicker(gVar, mutableState, context);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$1$0$1$0(MutableState mutableState, Context context) {
        EditText editTextProfileDialogContent$lambda$1 = ProfileDialogContent$lambda$1(mutableState);
        if (editTextProfileDialogContent$lambda$1 != null) {
            editTextProfileDialogContent$lambda$1.requestFocus();
            editTextProfileDialogContent$lambda$1.postDelayed(new l(editTextProfileDialogContent$lambda$1, context, 1), 100L);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EditText ProfileDialogContent$lambda$10$0$1$0$2$0$0(String str, String str2, MutableState mutableState, final r7.l lVar, r7.a aVar, Context context, FocusRequester focusRequester, Context context2) {
        EditText editText = new EditText(context2);
        mutableState.setValue(editText);
        editText.setText(str);
        editText.setTextColor(-1);
        editText.setHintTextColor(-7829368);
        editText.setHint(str2);
        editText.setTextSize(16.0f);
        editText.setBackground(null);
        editText.setPadding(36, 32, 36, 32);
        editText.setSingleLine(true);
        editText.setInputType(1);
        editText.setImeOptions(6);
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.addTextChangedListener(new TextWatcher() { // from class: com.arflix.tv.ui.screens.profile.ProfileDialogsKt$ProfileDialogContent$lambda$10$0$1$0$2$0$0$0$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s10) {
                String string;
                r7.l lVar2 = lVar;
                if (s10 == null || (string = s10.toString()) == null) {
                    string = "";
                }
                lVar2.invoke(string);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }
        });
        editText.setOnEditorActionListener(new q(editText, aVar, mutableState, context, focusRequester, 0));
        return editText;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ProfileDialogContent$lambda$10$0$1$0$2$0$0$0$1(EditText editText, r7.a aVar, MutableState mutableState, Context context, FocusRequester focusRequester, TextView textView, int i10, KeyEvent keyEvent) {
        String string;
        boolean z = i10 == 6;
        boolean z5 = keyEvent != null && keyEvent.getKeyCode() == 66 && keyEvent.getAction() == 1;
        if (!z && !z5) {
            return false;
        }
        ProfileDialogContent$hideKeyboard(mutableState, context, editText);
        Editable text = editText.getText();
        if (text == null || (string = text.toString()) == null || !(!kotlin.text.o.h0(string))) {
            try {
                focusRequester.requestFocus();
            } catch (Throwable unused) {
            }
        } else {
            aVar.invoke();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$1$0$2$1$0(String str, EditText editText) {
        if (!kotlin.jvm.internal.p.a(editText.getText().toString(), str)) {
            editText.setText(str);
            editText.setSelection(str.length());
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$1$0$3$0$0$0(r7.a aVar, MutableState mutableState, Context context) {
        ProfileDialogContent$hideKeyboard$default(mutableState, context, null, 4, null);
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$1$0$3$0$1$0(r7.a aVar, MutableState mutableState, Context context) {
        ProfileDialogContent$hideKeyboard$default(mutableState, context, null, 4, null);
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$1$0$3$0$2$0(r7.a aVar, MutableState mutableState, Context context) {
        ProfileDialogContent$hideKeyboard$default(mutableState, context, null, 4, null);
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$1$0$4$0$0(r7.a aVar, MutableState mutableState, Context context) {
        ProfileDialogContent$hideKeyboard$default(mutableState, context, null, 4, null);
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$1$0$4$1$0$0(r7.a aVar, MutableState mutableState, Context context) {
        ProfileDialogContent$hideKeyboard$default(mutableState, context, null, 4, null);
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$1$0$4$1$1$0(r7.a aVar, MutableState mutableState, Context context) {
        ProfileDialogContent$hideKeyboard$default(mutableState, context, null, 4, null);
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$1$1$0$0$0(int i10, List list, int i11, r7.l lVar, LazyListScope lazyListScope) {
        if (i10 == 0) {
            LazyListScope.CC.i(lazyListScope, null, null, ComposableLambdaKt.composableLambdaInstance(1054605754, true, new v(i11, 1, lVar)), 3, null);
        }
        LazyListScope.CC.k(lazyListScope, list.size(), null, null, ComposableLambdaKt.composableLambdaInstance(1870182924, true, new w(i11, 1, list, lVar)), 6, null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$1$1$0$0$0$0(int i10, r7.l lVar, LazyItemScope lazyItemScope, Composer composer, int i11) {
        if ((i11 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1054605754, i11, -1, "com.arflix.tv.ui.screens.profile.ProfileDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ProfileDialogs.kt:736)");
            }
            boolean z = i10 == 0;
            boolean zChanged = composer.changed(lVar);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new g(lVar, 0);
                composer.updateRememberedValue(objRememberedValue);
            }
            AvatarGridItem(0, z, (r7.a) objRememberedValue, true, composer, 3078, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$1$1$0$0$0$0$0$0(r7.l lVar) {
        lVar.invoke(0);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$1$1$0$0$0$1(List list, int i10, r7.l lVar, LazyItemScope lazyItemScope, int i11, Composer composer, int i12) {
        if ((i12 & 48) == 0) {
            i12 |= composer.changed(i11) ? 32 : 16;
        }
        if ((i12 & 145) == 144 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1870182924, i12, -1, "com.arflix.tv.ui.screens.profile.ProfileDialogContent.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (ProfileDialogs.kt:745)");
            }
            int iIntValue = ((Number) list.get(i11)).intValue();
            boolean z = i10 == iIntValue;
            boolean zChanged = composer.changed(lVar) | composer.changed(iIntValue);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new a1(iIntValue, 1, lVar);
                composer.updateRememberedValue(objRememberedValue);
            }
            AvatarGridItem(iIntValue, z, (r7.a) objRememberedValue, false, composer, 0, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$10$0$1$1$0$0$0$1$0$0(r7.l lVar, int i10) {
        lVar.invoke(Integer.valueOf(i10));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$11(String str, boolean z, String str2, r7.l lVar, int i10, r7.l lVar2, int i11, r7.l lVar3, String str3, boolean z5, r7.l lVar4, r7.a aVar, String str4, r7.a aVar2, r7.a aVar3, r7.a aVar4, Profile profile, r7.a aVar5, r7.a aVar6, int i12, int i13, int i14, Composer composer, int i15) {
        ProfileDialogContent(str, z, str2, lVar, i10, lVar2, i11, lVar3, str3, z5, lVar4, aVar, str4, aVar2, aVar3, aVar4, profile, aVar5, aVar6, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), RecomposeScopeImplKt.updateChangedFlags(i13), i14);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$6$0(r7.l lVar, Uri uri) {
        if (uri != null) {
            lVar.invoke(uri.toString());
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ProfileDialogContent$lambda$9$0(r7.a aVar, MutableState mutableState, Context context) {
        ProfileDialogContent$hideKeyboard$default(mutableState, context, null, 4, null);
        aVar.invoke();
        return t0.f22605a;
    }

    private static final void ProfileDialogContent$launchAvatarPicker(e.g gVar, MutableState<EditText> mutableState, Context context) {
        t0 t0Var;
        ProfileDialogContent$hideKeyboard$default(mutableState, context, null, 4, null);
        androidx.activity.result.j jVar = gVar.f14948a.f14932a;
        if (jVar != null) {
            jVar.a("image/*");
            t0Var = t0.f22605a;
        } else {
            t0Var = null;
        }
        if (t0Var == null) {
            throw new IllegalStateException("Launcher has not been initialized");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ProfileDialogContent$showKeyboard(Context context, EditText editText) {
        editText.post(new l(context, editText, 3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ProfileDialogContent$showKeyboard$lambda$4(Context context, EditText editText) {
        Object systemService = context.getSystemService("input_method");
        InputMethodManager inputMethodManager = systemService instanceof InputMethodManager ? (InputMethodManager) systemService : null;
        if ((inputMethodManager != null ? inputMethodManager.showSoftInput(editText, 1) : false) || inputMethodManager == null) {
            return;
        }
        inputMethodManager.showSoftInput(editText, 2);
    }
}
