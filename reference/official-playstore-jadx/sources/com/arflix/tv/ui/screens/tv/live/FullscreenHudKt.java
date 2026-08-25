package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsKt;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.focus.FocusChangedModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.WavUtil;
import androidx.tv.material3.TextKt;
import com.arflix.tv.data.model.IptvNowNext;
import com.google.android.gms.cast.MediaError;
import j$.time.Instant;
import j$.time.LocalDateTime;
import j$.time.ZoneId;
import j$.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\u001a×\u0002\u0010 \u001a\u00020\u00102\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0016\b\u0002\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001a2\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\u0016\b\u0002\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001a2\b\b\u0002\u0010\u001f\u001a\u00020\u001eH\u0007¢\u0006\u0004\b \u0010!\u001aW\u0010&\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\f2\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u001a2\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\b\b\u0002\u0010\u001f\u001a\u00020\u001eH\u0003¢\u0006\u0004\b&\u0010'\u001aM\u0010/\u001a\u00020\u00102\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020\u00062\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010+\u001a\u00020\b2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010,2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0003¢\u0006\u0004\b/\u00100\u001a\u0017\u00102\u001a\u00020\u00062\u0006\u00101\u001a\u00020\fH\u0002¢\u0006\u0004\b2\u00103\u001a\u0017\u00105\u001a\u00020\u00062\u0006\u00104\u001a\u00020\fH\u0002¢\u0006\u0004\b5\u00103\u001a%\u00107\u001a\u00020\u00102\u0006\u00106\u001a\u00020\u00062\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0003¢\u0006\u0004\b7\u00108¨\u0006@²\u0006\u000e\u00109\u001a\u00020\b8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010:\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010;\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010<\u001a\u00020\b8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010=\u001a\u00020\b8\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010>\u001a\u0004\u0018\u00010\f8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010?\u001a\u00020\b8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010?\u001a\u00020\b8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010?\u001a\u00020\b8\n@\nX\u008a\u008e\u0002"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "channel", "Lcom/arflix/tv/data/model/IptvNowNext;", "nowNext", "", "pokeSignal", "", "categoryName", "", "isCatchupMode", "isPlaying", "isBuffering", "", "playbackPositionMs", "playbackDurationMs", "Lkotlin/Function0;", "Lx6/t0;", "onBackClick", "onGuideClick", "onPlayPauseClick", "onRewindClick", "onFastForwardClick", "onPreviousCatchupClick", "onNextCatchupClick", "onReplayClick", "onGoLiveClick", "Lkotlin/Function1;", "onSeekToPosition", "onOpenQuickZap", "onVisibilityChanged", "Landroidx/compose/ui/Modifier;", "modifier", "FullscreenHud", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Lcom/arflix/tv/data/model/IptvNowNext;ILjava/lang/String;ZZZJJLr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/l;Lr7/a;Lr7/l;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;IIII)V", "", "progress", "positionMs", "durationMs", "HudSeekBar", "(FJJLr7/l;Lr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/ui/graphics/vector/ImageVector;", "icon", "contentDescription", "emphasis", "Landroidx/compose/ui/focus/FocusRequester;", "focusRequester", "onClick", "HudIconButton", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/focus/FocusRequester;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "millis", "formatHeaderDateTime", "(J)Ljava/lang/String;", "ms", "formatPlaybackDuration", "label", "HudActionButton", "(Ljava/lang/String;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "visible", "lastPoke", "clockMillis", "localIsPlaying", "initialFocusApplied", "frozenElapsedMs", "isFocused", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class FullscreenHudKt {
    /* JADX WARN: Removed duplicated region for block: B:104:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x034f  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0356  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x035a  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0368  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0376  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x03b0  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x03e5  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x03fa  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x03fc  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0425  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x043c  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0455  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x0464  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0480  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0490  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x049e  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x04b7  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x04c1  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x04cb  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x05b8  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x05c4  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x05c8  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x05e7  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x0624  */
    /* JADX WARN: Removed duplicated region for block: B:378:0x06a9  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x06d5  */
    /* JADX WARN: Removed duplicated region for block: B:384:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0136  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void FullscreenHud(final com.arflix.tv.ui.screens.tv.live.EnrichedChannel r57, final com.arflix.tv.data.model.IptvNowNext r58, final int r59, java.lang.String r60, boolean r61, boolean r62, boolean r63, long r64, long r66, r7.a<x6.t0> r68, r7.a<x6.t0> r69, r7.a<x6.t0> r70, r7.a<x6.t0> r71, r7.a<x6.t0> r72, r7.a<x6.t0> r73, r7.a<x6.t0> r74, r7.a<x6.t0> r75, r7.a<x6.t0> r76, r7.l<? super java.lang.Long, x6.t0> r77, r7.a<x6.t0> r78, r7.l<? super java.lang.Boolean, x6.t0> r79, androidx.compose.ui.Modifier r80, androidx.compose.runtime.Composer r81, final int r82, final int r83, final int r84, final int r85) {
        /*
            Method dump skipped, instruction units count: 1777
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.FullscreenHudKt.FullscreenHud(com.arflix.tv.ui.screens.tv.live.EnrichedChannel, com.arflix.tv.data.model.IptvNowNext, int, java.lang.String, boolean, boolean, boolean, long, long, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, r7.l, r7.a, r7.l, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean FullscreenHud$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final boolean FullscreenHud$lambda$13(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void FullscreenHud$lambda$14(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean FullscreenHud$lambda$17(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void FullscreenHud$lambda$18(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void FullscreenHud$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0530  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0554  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x057a  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0585  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x05e7  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x05f3  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x05f7  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0608  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x061b  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0660  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x066c  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0670  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0681  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0694  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x069d  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x06c1  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x070d  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0719  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x071d  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x072e  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0741  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x074a  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0750  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0752  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0757  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x075a  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x076b  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0818  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0824  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0828  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0839  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x084e  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0863  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x091a  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x092d  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x09b3  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x09be  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0a58  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0a66  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0bb0  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0c0c  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0c18  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0c1c  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0c2d  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0c40  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0c74  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0c80  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0c84  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0c95  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0ca8  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0d8c  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0d98  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0d9c  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0dad  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0dc0  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0dd7  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0ddf  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0e18  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x0e20  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x0e4f  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0e54  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0e5e  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0e63  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0e77  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0e7f  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x0eb4  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0ebc  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0ef5  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0efd  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0f73  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0f7f  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0f83  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0f94  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0fa7  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0fbe  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0fc6  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0ff1  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x1025  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x1031  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x1046  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x1085  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0492  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0499  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x049d  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x04a4  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x04a8  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x04ad  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x04b0  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x04bb  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x04d3  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x04e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 FullscreenHud$lambda$21(com.arflix.tv.data.model.IptvNowNext r117, com.arflix.tv.ui.screens.tv.live.EnrichedChannel r118, boolean r119, long r120, boolean r122, long r123, r7.a r125, java.lang.String r126, androidx.compose.runtime.MutableLongState r127, r7.l r128, r7.a r129, r7.a r130, r7.a r131, androidx.compose.ui.focus.FocusRequester r132, androidx.compose.runtime.MutableState r133, r7.a r134, r7.a r135, r7.a r136, r7.a r137, r7.a r138, r7.a r139, androidx.compose.animation.AnimatedVisibilityScope r140, androidx.compose.runtime.Composer r141, int r142) {
        /*
            Method dump skipped, instruction units count: 4235
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.FullscreenHudKt.FullscreenHud$lambda$21(com.arflix.tv.data.model.IptvNowNext, com.arflix.tv.ui.screens.tv.live.EnrichedChannel, boolean, long, boolean, long, r7.a, java.lang.String, androidx.compose.runtime.MutableLongState, r7.l, r7.a, r7.a, r7.a, androidx.compose.ui.focus.FocusRequester, androidx.compose.runtime.MutableState, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FullscreenHud$lambda$21$0$0$0$0$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long FullscreenHud$lambda$21$0$2(MutableState<Long> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FullscreenHud$lambda$21$0$5$1$1$0$0(r7.a aVar) {
        if (aVar != null) {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FullscreenHud$lambda$21$0$5$1$1$1$0(r7.a aVar) {
        if (aVar != null) {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FullscreenHud$lambda$21$0$5$1$1$2$0(r7.a aVar, MutableState mutableState) {
        FullscreenHud$lambda$14(mutableState, !FullscreenHud$lambda$13(mutableState));
        if (aVar != null) {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FullscreenHud$lambda$21$0$5$1$1$3$0(r7.a aVar) {
        if (aVar != null) {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FullscreenHud$lambda$21$0$5$1$1$4$0(r7.a aVar) {
        if (aVar != null) {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FullscreenHud$lambda$21$0$5$1$2$0$0(r7.a aVar) {
        if (aVar != null) {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FullscreenHud$lambda$21$0$5$1$2$1$0(r7.a aVar) {
        if (aVar != null) {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FullscreenHud$lambda$22(EnrichedChannel enrichedChannel, IptvNowNext iptvNowNext, int i10, String str, boolean z, boolean z5, boolean z10, long j10, long j11, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, r7.a aVar7, r7.a aVar8, r7.a aVar9, r7.l lVar, r7.a aVar10, r7.l lVar2, Modifier modifier, int i11, int i12, int i13, int i14, Composer composer, int i15) {
        FullscreenHud(enrichedChannel, iptvNowNext, i10, str, z, z5, z10, j10, j11, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, aVar9, lVar, aVar10, lVar2, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), RecomposeScopeImplKt.updateChangedFlags(i12), RecomposeScopeImplKt.updateChangedFlags(i13), i14);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult FullscreenHud$lambda$6$0(final r7.l lVar, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.FullscreenHudKt$FullscreenHud$lambda$6$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                r7.l lVar2 = lVar;
                if (lVar2 != null) {
                    lVar2.invoke(Boolean.FALSE);
                }
            }
        };
    }

    private static final void HudActionButton(final String str, final r7.a<x6.t0> aVar, Composer composer, final int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1890525412);
        int i12 = 4;
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 32 : 16;
        }
        int i13 = i11;
        if ((i13 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1890525412, i13, -1, "com.arflix.tv.ui.screens.tv.live.HudActionButton (FullscreenHud.kt:680)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState = (MutableState) objRememberedValue;
            Modifier modifierI = com.arflix.tv.data.repository.g.i(MediaError.DetailedErrorCode.GENERIC, Modifier.INSTANCE);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new s(mutableState, i12);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierH = androidx.fragment.app.a2.h(7, ClickableKt.m214clickableXHw0xAI$default(BackgroundKt.m179backgroundbw27NRU$default(FocusChangedModifierKt.onFocusChanged(modifierI, (r7.l) objRememberedValue2), HudActionButton$lambda$1(mutableState) ? Color.INSTANCE.m3509getWhite0d7_KjU() : LiveColors.INSTANCE.m6397getAccent0d7_KjU(), null, 2, null), false, null, null, aVar, 7, null), Dp.m5678constructorimpl(14), composerStartRestartGroup, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getBadge(), HudActionButton$lambda$1(mutableState) ? Color.INSTANCE.m3498getBlack0d7_KjU() : LiveColors.INSTANCE.m6399getBg0d7_KjU(), TextUnitKt.getSp(11), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777208, null), composer2, i13 & 14, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.tv.live.r0
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return FullscreenHudKt.HudActionButton$lambda$5(str, aVar, i10, (Composer) obj, iIntValue);
                }
            });
        }
    }

    private static final boolean HudActionButton$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void HudActionButton$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 HudActionButton$lambda$3$0(MutableState mutableState, FocusState focusState) {
        HudActionButton$lambda$2(mutableState, focusState.isFocused());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 HudActionButton$lambda$5(String str, r7.a aVar, int i10, Composer composer, int i11) {
        HudActionButton(str, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:130:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0132  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void HudIconButton(androidx.compose.ui.graphics.vector.ImageVector r24, java.lang.String r25, androidx.compose.ui.Modifier r26, boolean r27, androidx.compose.ui.focus.FocusRequester r28, r7.a<x6.t0> r29, androidx.compose.runtime.Composer r30, int r31, int r32) {
        /*
            Method dump skipped, instruction units count: 569
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.FullscreenHudKt.HudIconButton(androidx.compose.ui.graphics.vector.ImageVector, java.lang.String, androidx.compose.ui.Modifier, boolean, androidx.compose.ui.focus.FocusRequester, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean HudIconButton$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void HudIconButton$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 HudIconButton$lambda$3$0(MutableState mutableState, FocusState focusState) {
        HudIconButton$lambda$2(mutableState, focusState.isFocused());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 HudIconButton$lambda$5(ImageVector imageVector, String str, Modifier modifier, boolean z, FocusRequester focusRequester, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        HudIconButton(imageVector, str, modifier, z, focusRequester, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r7v6 */
    private static final void HudSeekBar(final float f10, final long j10, final long j11, final r7.l<? super Long, x6.t0> lVar, final r7.a<x6.t0> aVar, Modifier modifier, Composer composer, final int i10, final int i11) {
        int i12;
        r7.a<x6.t0> aVar2;
        Modifier modifier2;
        Object obj;
        Object obj2;
        int i13;
        ?? r72;
        final Modifier modifier3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1424513260);
        if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(f10) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(j10) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(j11) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(lVar) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            aVar2 = aVar;
            i12 |= composerStartRestartGroup.changedInstance(aVar2) ? 16384 : 8192;
        } else {
            aVar2 = aVar;
        }
        int i14 = i11 & 32;
        if (i14 != 0) {
            i12 |= 196608;
            modifier2 = modifier;
        } else {
            modifier2 = modifier;
            if ((i10 & 196608) == 0) {
                i12 |= composerStartRestartGroup.changed(modifier2) ? 131072 : 65536;
            }
        }
        if ((i12 & 74899) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i14 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1424513260, i12, -1, "com.arflix.tv.ui.screens.tv.live.HudSeekBar (FullscreenHud.kt:525)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableState = (MutableState) objRememberedValue;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(modifier2, 0.0f, 1, null);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new s(mutableState, 5);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierFocusable$default = FocusableKt.focusable$default(FocusChangedModifierKt.onFocusChanged(modifierFillMaxWidth$default, (r7.l) objRememberedValue2), false, null, 3, null);
            boolean z = ((i12 & 57344) == 16384) | ((i12 & 7168) == 2048) | ((i12 & 112) == 32) | ((i12 & 896) == 256);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue3 == companion.getEmpty()) {
                final r7.a<x6.t0> aVar3 = aVar2;
                obj2 = null;
                i13 = 0;
                r72 = 1;
                obj = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.screens.tv.live.FullscreenHudKt$HudSeekBar$2$1
                    @Override // r7.l
                    public /* bridge */ /* synthetic */ Object invoke(Object obj3) {
                        return m6396invokeZmokQxo(((KeyEvent) obj3).m4390unboximpl());
                    }

                    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                    public final Boolean m6396invokeZmokQxo(android.view.KeyEvent keyEvent) {
                        r7.l<Long, x6.t0> lVar2;
                        boolean z5 = false;
                        if (KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                            int keyCode = keyEvent.getKeyCode();
                            if (keyCode != 19) {
                                if (keyCode == 21) {
                                    r7.l<Long, x6.t0> lVar3 = lVar;
                                    if (lVar3 != null) {
                                        long j12 = j10 - 10000;
                                        lVar3.invoke(Long.valueOf(j12 >= 0 ? j12 : 0L));
                                        z5 = true;
                                    }
                                } else if (keyCode == 22 && (lVar2 = lVar) != null) {
                                    long j13 = j11;
                                    if (j13 <= 0) {
                                        j13 = j10 + 300000;
                                    }
                                    long j14 = j10 + 10000;
                                    if (j14 <= j13) {
                                        j13 = j14;
                                    }
                                    lVar2.invoke(Long.valueOf(j13));
                                    z5 = true;
                                }
                            } else {
                                r7.a<x6.t0> aVar4 = aVar3;
                                if (aVar4 != null) {
                                    aVar4.invoke();
                                    z5 = true;
                                }
                            }
                        }
                        return Boolean.valueOf(z5);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj);
            } else {
                obj = objRememberedValue3;
                obj2 = null;
                i13 = 0;
                r72 = 1;
            }
            Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(KeyInputModifierKt.onKeyEvent(modifierFocusable$default, (r7.l) obj), 0.0f, Dp.m5678constructorimpl(4), r72, obj2);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            Arrangement.Vertical top = Arrangement.INSTANCE.getTop();
            Alignment.Companion companion2 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion2, top, composerStartRestartGroup, i13, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, i13);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(i13, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Modifier modifierM564height3ABfNKs = SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, r72, obj2), Dp.m5678constructorimpl(HudSeekBar$lambda$1(mutableState) ? 16 : 6));
            Alignment centerStart = companion2.getCenterStart();
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1800872008, r72, new r7.q() { // from class: com.arflix.tv.ui.screens.tv.live.s0
                @Override // r7.q
                public final Object invoke(Object obj3, Object obj4, Object obj5) {
                    int iIntValue = ((Integer) obj5).intValue();
                    return FullscreenHudKt.HudSeekBar$lambda$5$0(f10, mutableState, (BoxWithConstraintsScope) obj3, (Composer) obj4, iIntValue);
                }
            }, composerStartRestartGroup, 54);
            Modifier modifier4 = modifier2;
            BoxWithConstraintsKt.BoxWithConstraints(modifierM564height3ABfNKs, centerStart, false, composableLambdaRememberComposableLambda, composerStartRestartGroup, 3120, 4);
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.tv.live.t0
                @Override // r7.p
                public final Object invoke(Object obj3, Object obj4) {
                    int iIntValue = ((Integer) obj4).intValue();
                    return FullscreenHudKt.HudSeekBar$lambda$6(f10, j10, j11, lVar, aVar, modifier3, i10, i11, (Composer) obj3, iIntValue);
                }
            });
        }
    }

    private static final boolean HudSeekBar$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void HudSeekBar$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 HudSeekBar$lambda$3$0(MutableState mutableState, FocusState focusState) {
        HudSeekBar$lambda$2(mutableState, focusState.isFocused());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 HudSeekBar$lambda$5$0(float f10, MutableState mutableState, BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, int i10) {
        BoxWithConstraintsScope boxWithConstraintsScope2;
        int i11;
        if ((i10 & 6) == 0) {
            boxWithConstraintsScope2 = boxWithConstraintsScope;
            i11 = i10 | (composer.changed(boxWithConstraintsScope2) ? 4 : 2);
        } else {
            boxWithConstraintsScope2 = boxWithConstraintsScope;
            i11 = i10;
        }
        if ((i11 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1800872008, i11, -1, "com.arflix.tv.ui.screens.tv.live.HudSeekBar.<anonymous>.<anonymous> (FullscreenHud.kt:570)");
            }
            float fMo467getMaxWidthD9Ej5fM = boxWithConstraintsScope2.mo467getMaxWidthD9Ej5fM();
            float fM = qb.d.m(f10, 0.0f, 1.0f);
            Modifier.Companion companion = Modifier.INSTANCE;
            float f11 = 4;
            BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), Dp.m5678constructorimpl(HudSeekBar$lambda$1(mutableState) ? 6 : 4)), f11), HudSeekBar$lambda$1(mutableState) ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.35f, 0.0f, 0.0f, 0.0f, 14, null) : LiveColors.INSTANCE.m6409getPanel0d7_KjU(), null, 2, null), composer, 0);
            BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth(companion, fM), HudSeekBar$lambda$1(mutableState) ? Dp.m5678constructorimpl(6) : Dp.m5678constructorimpl(f11)), f11), LiveColors.INSTANCE.m6397getAccent0d7_KjU(), null, 2, null), composer, 0);
            if (HudSeekBar$lambda$1(mutableState)) {
                composer.startReplaceGroup(-1340620767);
                float f12 = 16;
                float fM5678constructorimpl = Dp.m5678constructorimpl(fMo467getMaxWidthD9Ej5fM - Dp.m5678constructorimpl(f12));
                float f13 = 0;
                float fM5678constructorimpl2 = Dp.m5678constructorimpl(f13);
                if (fM5678constructorimpl < fM5678constructorimpl2) {
                    fM5678constructorimpl = fM5678constructorimpl2;
                }
                BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(ClipKt.clip(SizeKt.m578size3ABfNKs(PaddingKt.m533paddingqDBjuR0$default(companion, ((Dp) qb.d.p(Dp.m5676boximpl(Dp.m5678constructorimpl(Dp.m5678constructorimpl(fMo467getMaxWidthD9Ej5fM * fM) - Dp.m5678constructorimpl(8))), Dp.m5676boximpl(Dp.m5678constructorimpl(f13)), Dp.m5676boximpl(Dp.m5678constructorimpl(fM5678constructorimpl)))).m5692unboximpl(), 0.0f, 0.0f, 0.0f, 14, null), Dp.m5678constructorimpl(f12)), RoundedCornerShapeKt.getCircleShape()), Color.INSTANCE.m3509getWhite0d7_KjU(), null, 2, null), composer, 0);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(-1340181590);
                composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 HudSeekBar$lambda$6(float f10, long j10, long j11, r7.l lVar, r7.a aVar, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        HudSeekBar(f10, j10, j11, lVar, aVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    private static final String formatHeaderDateTime(long j10) {
        try {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(j10), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("EEE, MMM d, h:mm a", Locale.US));
        } catch (Exception unused) {
            return "";
        }
    }

    private static final String formatPlaybackDuration(long j10) {
        long j11 = j10 / 1000;
        if (j11 < 0) {
            j11 = 0;
        }
        long j12 = j11 / 3600;
        long j13 = (j11 % 3600) / 60;
        long j14 = j11 % 60;
        return j12 > 0 ? String.format("%d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j12), Long.valueOf(j13), Long.valueOf(j14)}, 3)) : String.format("%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j13), Long.valueOf(j14)}, 2));
    }
}
