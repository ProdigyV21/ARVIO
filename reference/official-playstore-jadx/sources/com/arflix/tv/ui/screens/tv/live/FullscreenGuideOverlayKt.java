package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.model.IptvProgram;
import com.google.android.gms.cast.MediaError;
import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.ZoneId;
import j$.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\b\u0005\u001a}\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0001\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u00002\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0014\u0010\r\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\n0\f2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0001¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u008b\u0001\u0010\u001e\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00132\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\b\u001a\u00020\u00002\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0014\u0010\r\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\n0\fH\u0003¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u000f\u0010 \u001a\u00020\nH\u0003¢\u0006\u0004\b \u0010!\u001a7\u0010\"\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0000H\u0003¢\u0006\u0004\b\"\u0010#\u001a1\u0010+\u001a\u00020\n2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u0010(\u001a\u00020'2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0003¢\u0006\u0004\b)\u0010*\u001aG\u00101\u001a\u00020\n2\u0006\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001c2\b\u0010/\u001a\u0004\u0018\u00010.2\u0006\u0010\b\u001a\u00020\u00002\f\u00100\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0003¢\u0006\u0004\b1\u00102\u001a\u001f\u00103\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0000H\u0003¢\u0006\u0004\b3\u00104\u001a'\u00109\u001a\u00020\n2\u0006\u0010%\u001a\u00020$2\u0006\u00105\u001a\u00020'2\u0006\u00106\u001a\u00020'H\u0003¢\u0006\u0004\b7\u00108\u001a\u001f\u0010;\u001a\u00020$2\u0006\u0010:\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001cH\u0003¢\u0006\u0004\b;\u0010<\u001a\u0013\u0010=\u001a\u00020\u0000*\u00020\u0002H\u0002¢\u0006\u0004\b=\u0010>\u001a\u001f\u0010?\u001a\u00020$2\u0006\u0010:\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001cH\u0003¢\u0006\u0004\b?\u0010<\"\u0014\u0010A\u001a\u00020@8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010B¨\u0006D²\u0006\u000e\u0010\u001d\u001a\u00020\u001c8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010C\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002"}, d2 = {"", "visible", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "channel", "Lcom/arflix/tv/data/model/IptvNowNext;", "guide", "Lcom/arflix/tv/data/model/IptvProgram;", "selectedProgram", "isTouchDevice", "Lkotlin/Function0;", "Lx6/t0;", "onDismiss", "Lkotlin/Function1;", "onProgramSelect", "onLeftClick", "Landroidx/compose/ui/Modifier;", "modifier", "FullscreenGuideOverlay", "(ZLcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Lcom/arflix/tv/data/model/IptvNowNext;Lcom/arflix/tv/data/model/IptvProgram;ZLr7/a;Lr7/l;Lr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "", "pastCount", "liveCount", "futureCount", "", "Lcom/arflix/tv/ui/screens/tv/live/GuideProgramItem;", "items", "anchorIndex", "catchupSupported", "", "nowMillis", "FullscreenGuideContent", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Lcom/arflix/tv/data/model/IptvProgram;IIILjava/util/List;IZJZLr7/a;Lr7/l;Landroidx/compose/runtime/Composer;II)V", "GuideSheetHandle", "(Landroidx/compose/runtime/Composer;I)V", "GuideTimelineSummary", "(IIIZZLandroidx/compose/runtime/Composer;I)V", "", "label", "value", "Landroidx/compose/ui/graphics/Color;", "accent", "GuideTimelinePill-cf5BqRc", "(Ljava/lang/String;Ljava/lang/String;JLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "GuideTimelinePill", "item", "selected", "Landroidx/compose/ui/focus/FocusRequester;", "focusRequester", "onClick", "GuideProgramRow", "(Lcom/arflix/tv/ui/screens/tv/live/GuideProgramItem;ZJLandroidx/compose/ui/focus/FocusRequester;ZLr7/a;Landroidx/compose/runtime/Composer;I)V", "GuideEmptyState", "(ZZLandroidx/compose/runtime/Composer;I)V", "fg", "bg", "GuideChip-1wkBAMs", "(Ljava/lang/String;JJLandroidx/compose/runtime/Composer;I)V", "GuideChip", "program", "timelineDateLabel", "(Lcom/arflix/tv/data/model/IptvProgram;JLandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "supportsFullscreenCatchup", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;)Z", "startsLabel", "j$/time/format/DateTimeFormatter", "timelineDateFormatter", "Lj$/time/format/DateTimeFormatter;", "focused", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class FullscreenGuideOverlayKt {
    private static final DateTimeFormatter timelineDateFormatter = DateTimeFormatter.ofPattern("EEE d MMM", Locale.getDefault());

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[GuideProgramState.values().length];
            try {
                iArr[GuideProgramState.Live.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[GuideProgramState.PastPlayable.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[GuideProgramState.PastUnavailable.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[GuideProgramState.Future.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:143:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x03d6  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x047b  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0487  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x04af  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0513  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0533  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0591  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x05b2  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x05b4  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x05c2  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x05c5  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x05dd  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0633  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0635  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x063e  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0640  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0648  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x064a  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0653  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0655  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x065f  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0661  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0665  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0667  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x066f  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x067d  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x06b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void FullscreenGuideContent(final com.arflix.tv.ui.screens.tv.live.EnrichedChannel r83, final com.arflix.tv.data.model.IptvProgram r84, final int r85, final int r86, final int r87, final java.util.List<com.arflix.tv.ui.screens.tv.live.GuideProgramItem> r88, final int r89, final boolean r90, final long r91, final boolean r93, final r7.a<x6.t0> r94, final r7.l<? super com.arflix.tv.data.model.IptvProgram, x6.t0> r95, androidx.compose.runtime.Composer r96, final int r97, final int r98) {
        /*
            Method dump skipped, instruction units count: 1765
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.FullscreenGuideOverlayKt.FullscreenGuideContent(com.arflix.tv.ui.screens.tv.live.EnrichedChannel, com.arflix.tv.data.model.IptvProgram, int, int, int, java.util.List, int, boolean, long, boolean, r7.a, r7.l, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FullscreenGuideContent$lambda$0$3$0(List list, final boolean z, final boolean z5, IptvProgram iptvProgram, long j10, int i10, FocusRequester focusRequester, r7.l lVar, LazyListScope lazyListScope) {
        if (list.isEmpty()) {
            LazyListScope.CC.i(lazyListScope, null, null, ComposableLambdaKt.composableLambdaInstance(-1627189929, true, new r7.q() { // from class: com.arflix.tv.ui.screens.tv.live.f0
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return FullscreenGuideOverlayKt.FullscreenGuideContent$lambda$0$3$0$0(z, z5, (LazyItemScope) obj, (Composer) obj2, iIntValue);
                }
            }), 3, null);
        } else {
            lazyListScope.items(list.size(), new FullscreenGuideOverlayKt$FullscreenGuideContent$lambda$0$3$0$$inlined$itemsIndexed$default$1(new l0(0), list), new FullscreenGuideOverlayKt$FullscreenGuideContent$lambda$0$3$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new FullscreenGuideOverlayKt$FullscreenGuideContent$lambda$0$3$0$$inlined$itemsIndexed$default$3(list, iptvProgram, j10, i10, focusRequester, z5, lVar)));
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FullscreenGuideContent$lambda$0$3$0$0(boolean z, boolean z5, LazyItemScope lazyItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1627189929, i10, -1, "com.arflix.tv.ui.screens.tv.live.FullscreenGuideContent.<anonymous>.<anonymous>.<anonymous>.<anonymous> (FullscreenGuideOverlay.kt:362)");
            }
            GuideEmptyState(z, z5, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object FullscreenGuideContent$lambda$0$3$0$1(int i10, GuideProgramItem guideProgramItem) {
        return guideProgramItem.getState() + ":" + guideProgramItem.getProgram().getStartUtcMillis() + ":" + guideProgramItem.getProgram().getTitle() + ":" + i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FullscreenGuideContent$lambda$1(EnrichedChannel enrichedChannel, IptvProgram iptvProgram, int i10, int i11, int i12, List list, int i13, boolean z, long j10, boolean z5, r7.a aVar, r7.l lVar, int i14, int i15, Composer composer, int i16) {
        FullscreenGuideContent(enrichedChannel, iptvProgram, i10, i11, i12, list, i13, z, j10, z5, aVar, lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i14 | 1), RecomposeScopeImplKt.updateChangedFlags(i15));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:143:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x040b  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0408 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void FullscreenGuideOverlay(boolean r27, final com.arflix.tv.ui.screens.tv.live.EnrichedChannel r28, com.arflix.tv.data.model.IptvNowNext r29, final com.arflix.tv.data.model.IptvProgram r30, final boolean r31, final r7.a<x6.t0> r32, final r7.l<? super com.arflix.tv.data.model.IptvProgram, x6.t0> r33, r7.a<x6.t0> r34, androidx.compose.ui.Modifier r35, androidx.compose.runtime.Composer r36, int r37, int r38) {
        /*
            Method dump skipped, instruction units count: 1484
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.FullscreenGuideOverlayKt.FullscreenGuideOverlay(boolean, com.arflix.tv.ui.screens.tv.live.EnrichedChannel, com.arflix.tv.data.model.IptvNowNext, com.arflix.tv.data.model.IptvProgram, boolean, r7.a, r7.l, r7.a, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FullscreenGuideOverlay$lambda$0(boolean z, EnrichedChannel enrichedChannel, IptvNowNext iptvNowNext, IptvProgram iptvProgram, boolean z5, r7.a aVar, r7.l lVar, r7.a aVar2, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        FullscreenGuideOverlay(z, enrichedChannel, iptvNowNext, iptvProgram, z5, aVar, lVar, aVar2, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int FullscreenGuideOverlay$lambda$11$0(int i10) {
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int FullscreenGuideOverlay$lambda$12$0(int i10) {
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int FullscreenGuideOverlay$lambda$13$0(int i10) {
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int FullscreenGuideOverlay$lambda$14$0(int i10) {
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FullscreenGuideOverlay$lambda$15(boolean z, r7.a aVar, final r7.a aVar2, EnrichedChannel enrichedChannel, IptvProgram iptvProgram, List list, List list2, List list3, List list4, int i10, boolean z5, r7.l lVar, MutableLongState mutableLongState, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i11) {
        RoundedCornerShape roundedCornerShapeM798RoundedCornerShape0680j_4;
        Modifier modifierM533paddingqDBjuR0$default;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1340466476, i11, -1, "com.arflix.tv.ui.screens.tv.live.FullscreenGuideOverlay.<anonymous> (FullscreenGuideOverlay.kt:198)");
        }
        if (z) {
            float f10 = 24;
            float f11 = 14;
            roundedCornerShapeM798RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m799RoundedCornerShapea9UjIt4(Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(f11), Dp.m5678constructorimpl(f11));
        } else {
            roundedCornerShapeM798RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(18));
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(companion, 0.0f, 1, null);
        composer.startReplaceableGroup(733328855);
        Alignment.Companion companion2 = Alignment.INSTANCE;
        MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composer, 0, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion3.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxSize$default);
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
        r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(companion, 0.0f, 1, null);
        Color.Companion companion4 = Color.INSTANCE;
        Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(modifierFillMaxSize$default2, Color.m3471copywmQWz5c$default(companion4.m3498getBlack0d7_KjU(), z ? 0.52f : 0.42f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null);
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion5 = Composer.INSTANCE;
        if (objRememberedValue == companion5.getEmpty()) {
            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
            composer.updateRememberedValue(objRememberedValue);
        }
        BoxKt.Box(ClickableKt.m212clickableO2vRcR0$default(modifierM179backgroundbw27NRU$default, (MutableInteractionSource) objRememberedValue, null, false, null, null, aVar, 28, null), composer, 0);
        Modifier modifierAlign = boxScopeInstance.align(companion, z ? companion2.getBottomCenter() : companion2.getCenterEnd());
        if (z) {
            modifierM533paddingqDBjuR0$default = PaddingKt.m530paddingVpY3zN4(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), 0.82f), Dp.m5678constructorimpl(8), Dp.m5678constructorimpl(6));
        } else {
            Modifier modifierFillMaxHeight$default = SizeKt.fillMaxHeight$default(SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(520)), 0.0f, 1, null);
            float f12 = 24;
            modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(modifierFillMaxHeight$default, 0.0f, Dp.m5678constructorimpl(f12), Dp.m5678constructorimpl(f12), Dp.m5678constructorimpl(f12), 1, null);
        }
        Modifier modifierThen = modifierAlign.then(modifierM533paddingqDBjuR0$default);
        boolean zChanged = composer.changed(aVar2);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == companion5.getEmpty()) {
            objRememberedValue2 = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.screens.tv.live.FullscreenGuideOverlayKt$FullscreenGuideOverlay$3$1$2$1
                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return m6394invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m6394invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    boolean z10;
                    if (KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY()) && Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent), Key.INSTANCE.m4169getDirectionLeftEK5gGoQ())) {
                        r7.a<x6.t0> aVar3 = aVar2;
                        if (aVar3 != null) {
                            aVar3.invoke();
                        }
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    return Boolean.valueOf(z10);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(BackgroundKt.background$default(BorderKt.border(ClipKt.clip(KeyInputModifierKt.onPreviewKeyEvent(modifierThen, (r7.l) objRememberedValue2), roundedCornerShapeM798RoundedCornerShape0680j_4), BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(companion4.m3509getWhite0d7_KjU(), z ? 0.16f : 0.12f, 0.0f, 0.0f, 0.0f, 14, null)), roundedCornerShapeM798RoundedCornerShape0680j_4), Brush.Companion.m3436verticalGradient8A3gB4$default(Brush.INSTANCE, new x6.x[]{new x6.x(Float.valueOf(0.0f), Color.m3462boximpl(ColorKt.Color(z ? 4112523819L : 4061731618L))), new x6.x(Float.valueOf(1.0f), Color.m3462boximpl(ColorKt.Color(4027124238L)))}, 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), Dp.m5678constructorimpl(z ? 14 : 18));
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyD2 = a0.c.d(companion2, false, composer, 0, -1323940314);
        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM529padding3ABfNKs);
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor2);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composer);
        r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyD2, composerM2991constructorimpl2, currentCompositionLocalMap2);
        if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
            a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
        }
        a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
        FullscreenGuideContent(enrichedChannel, iptvProgram, list.size(), list2.size(), list3.size(), list4, i10, z5, mutableLongState.getLongValue(), z, aVar, lVar, composer, 0, 0);
        if (androidx.compose.foundation.c.A(composer)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FullscreenGuideOverlay$lambda$16(boolean z, EnrichedChannel enrichedChannel, IptvNowNext iptvNowNext, IptvProgram iptvProgram, boolean z5, r7.a aVar, r7.l lVar, r7.a aVar2, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        FullscreenGuideOverlay(z, enrichedChannel, iptvNowNext, iptvProgram, z5, aVar, lVar, aVar2, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean FullscreenGuideOverlay$lambda$6$0(long j10, MutableLongState mutableLongState, IptvProgram iptvProgram) {
        return iptvProgram.getEndUtcMillis() <= mutableLongState.getLongValue() && iptvProgram.getEndUtcMillis() >= j10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String FullscreenGuideOverlay$lambda$6$1(IptvProgram iptvProgram) {
        long startUtcMillis = iptvProgram.getStartUtcMillis();
        long endUtcMillis = iptvProgram.getEndUtcMillis();
        String title = iptvProgram.getTitle();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(startUtcMillis);
        sb2.append(":");
        sb2.append(endUtcMillis);
        return a0.c.p(sb2, ":", title);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final GuideProgramItem FullscreenGuideOverlay$lambda$6$3(boolean z, IptvProgram iptvProgram) {
        return new GuideProgramItem(iptvProgram, z ? GuideProgramState.PastPlayable : GuideProgramState.PastUnavailable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean FullscreenGuideOverlay$lambda$8$1(MutableLongState mutableLongState, IptvProgram iptvProgram) {
        return iptvProgram.getStartUtcMillis() > mutableLongState.getLongValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String FullscreenGuideOverlay$lambda$8$2(IptvProgram iptvProgram) {
        long startUtcMillis = iptvProgram.getStartUtcMillis();
        long endUtcMillis = iptvProgram.getEndUtcMillis();
        String title = iptvProgram.getTitle();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(startUtcMillis);
        sb2.append(":");
        sb2.append(endUtcMillis);
        return a0.c.p(sb2, ":", title);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final GuideProgramItem FullscreenGuideOverlay$lambda$8$4(IptvProgram iptvProgram) {
        return new GuideProgramItem(iptvProgram, GuideProgramState.Future);
    }

    /* JADX INFO: renamed from: GuideChip-1wkBAMs, reason: not valid java name */
    private static final void m6391GuideChip1wkBAMs(String str, long j10, long j11, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(25485998);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(j10) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(j11) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(25485998, i11, -1, "com.arflix.tv.ui.screens.tv.live.GuideChip (FullscreenGuideOverlay.kt:692)");
            }
            Modifier modifierH = androidx.fragment.app.a2.h(2, BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.i(5, Modifier.INSTANCE), j11, null, 2, null), Dp.m5678constructorimpl(6), composerStartRestartGroup, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getBadge(), j10, TextUnitKt.getSp(9), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777212, null), composer2, i11 & 14, 3072, 57342);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.screens.settings.z1(str, j10, j11, i10, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 GuideChip_1wkBAMs$lambda$1(String str, long j10, long j11, int i10, Composer composer, int i11) {
        m6391GuideChip1wkBAMs(str, j10, j11, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void GuideEmptyState(final boolean z, final boolean z5, Composer composer, final int i10) {
        int i11;
        String strStringResource;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(25917357);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z5) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(25917357, i11, -1, "com.arflix.tv.ui.screens.tv.live.GuideEmptyState (FullscreenGuideOverlay.kt:664)");
            }
            if (z) {
                composerStartRestartGroup.startReplaceGroup(-863216256);
                strStringResource = StringResources_androidKt.stringResource(R.string.live_empty_no_timeline_yet, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-863218940);
                strStringResource = StringResources_androidKt.stringResource(R.string.live_empty_no_timeline_channel, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            }
            float f10 = 14;
            Modifier modifierN = com.arflix.tv.data.repository.g.n(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5678constructorimpl(z5 ? 118 : 150)), f10);
            Color.Companion companion = Color.INSTANCE;
            Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(BorderKt.border(BackgroundKt.m179backgroundbw27NRU$default(modifierN, Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.055f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.08f, 0.0f, 0.0f, 0.0f, 14, null)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10))), z5 ? Dp.m5678constructorimpl(f10) : Dp.m5678constructorimpl(18));
            Alignment center = Alignment.INSTANCE.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM529padding3ABfNKs);
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
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(strStringResource, null, 0L, 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 3, 0, null, TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getBodySynopsis(), LiveColors.INSTANCE.m6403getFgDim0d7_KjU(), TextUnitKt.getSp(z5 ? 12 : 13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777212, null), composer2, 0, 3120, 55294);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.tv.live.m0
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return FullscreenGuideOverlayKt.GuideEmptyState$lambda$1(z, z5, i10, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 GuideEmptyState$lambda$1(boolean z, boolean z5, int i10, Composer composer, int i11) {
        GuideEmptyState(z, z5, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0591  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x05c1  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x05d8  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x05f8  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x05ff  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0607  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x065a  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0666  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x066a  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x068e  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x06c7  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x06d3  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x06d7  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x06fb  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x070f  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x075b  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0778  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0798  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x07ac  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x07ea  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0820  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0827  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0830  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0937  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x0948  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x09b8  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x09c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void GuideProgramRow(final com.arflix.tv.ui.screens.tv.live.GuideProgramItem r108, final boolean r109, final long r110, final androidx.compose.ui.focus.FocusRequester r112, final boolean r113, final r7.a<x6.t0> r114, androidx.compose.runtime.Composer r115, final int r116) {
        /*
            Method dump skipped, instruction units count: 2533
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.FullscreenGuideOverlayKt.GuideProgramRow(com.arflix.tv.ui.screens.tv.live.GuideProgramItem, boolean, long, androidx.compose.ui.focus.FocusRequester, boolean, r7.a, androidx.compose.runtime.Composer, int):void");
    }

    private static final boolean GuideProgramRow$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void GuideProgramRow$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 GuideProgramRow$lambda$3$0(MutableState mutableState, FocusState focusState) {
        GuideProgramRow$lambda$2(mutableState, focusState.getHasFocus());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float GuideProgramRow$lambda$5$2$1$0$0(float f10) {
        return f10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 GuideProgramRow$lambda$6(GuideProgramItem guideProgramItem, boolean z, long j10, FocusRequester focusRequester, boolean z5, r7.a aVar, int i10, Composer composer, int i11) {
        GuideProgramRow(guideProgramItem, z, j10, focusRequester, z5, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void GuideSheetHandle(Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(437785644);
        if (i10 == 0 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(437785644, i10, -1, "com.arflix.tv.ui.screens.tv.live.GuideSheetHandle (FullscreenGuideOverlay.kt:397)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierM564height3ABfNKs = SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), Dp.m5678constructorimpl(8));
            Alignment center = Alignment.INSTANCE.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM564height3ABfNKs);
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
            SpacerKt.Spacer(BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(42)), Dp.m5678constructorimpl(4)), MediaError.DetailedErrorCode.GENERIC), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.24f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composerStartRestartGroup, 0);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.j(i10, 7));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 GuideSheetHandle$lambda$1(int i10, Composer composer, int i11) {
        GuideSheetHandle(composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: GuideTimelinePill-cf5BqRc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6392GuideTimelinePillcf5BqRc(java.lang.String r40, java.lang.String r41, long r42, androidx.compose.ui.Modifier r44, androidx.compose.runtime.Composer r45, int r46, int r47) {
        /*
            Method dump skipped, instruction units count: 536
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.FullscreenGuideOverlayKt.m6392GuideTimelinePillcf5BqRc(java.lang.String, java.lang.String, long, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 GuideTimelinePill_cf5BqRc$lambda$1(String str, String str2, long j10, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        m6392GuideTimelinePillcf5BqRc(str, str2, j10, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    private static final void GuideTimelineSummary(int i10, final int i11, final int i12, final boolean z, final boolean z5, Composer composer, final int i13) {
        final int i14;
        int i15;
        Composer composerStartRestartGroup = composer.startRestartGroup(-396520001);
        if ((i13 & 6) == 0) {
            i14 = i10;
            i15 = (composerStartRestartGroup.changed(i14) ? 4 : 2) | i13;
        } else {
            i14 = i10;
            i15 = i13;
        }
        if ((i13 & 48) == 0) {
            i15 |= composerStartRestartGroup.changed(i11) ? 32 : 16;
        }
        if ((i13 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i15 |= composerStartRestartGroup.changed(i12) ? 256 : 128;
        }
        if ((i13 & 3072) == 0) {
            i15 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        if ((i13 & 24576) == 0) {
            i15 |= composerStartRestartGroup.changed(z5) ? 16384 : 8192;
        }
        if ((i15 & 9363) == 9362 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-396520001, i15, -1, "com.arflix.tv.ui.screens.tv.live.GuideTimelineSummary (FullscreenGuideOverlay.kt:422)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            float f10 = 14;
            Modifier modifierN = com.arflix.tv.data.repository.g.n(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), f10);
            Color.Companion companion2 = Color.INSTANCE;
            Modifier modifierBorder = BorderKt.border(BackgroundKt.m179backgroundbw27NRU$default(modifierN, Color.m3471copywmQWz5c$default(companion2.m3509getWhite0d7_KjU(), z5 ? 0.055f : 0.045f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), BorderStrokeKt.m207BorderStrokecXLIe8U(Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(companion2.m3509getWhite0d7_KjU(), 0.08f, 0.0f, 0.0f, 0.0f, 14, null)), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10)));
            float f11 = 5;
            Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(modifierBorder, Dp.m5678constructorimpl(f11));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement arrangement = Arrangement.INSTANCE;
            if (!z5) {
                f11 = 7;
            }
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(f11));
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM441spacedBy0680j_4, centerVertically, composerStartRestartGroup, 48);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM529padding3ABfNKs);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            String strStringResource = StringResources_androidKt.stringResource(R.string.live_label_aired, composerStartRestartGroup, 0);
            String strValueOf = String.valueOf(i14);
            LiveColors liveColors = LiveColors.INSTANCE;
            m6392GuideTimelinePillcf5BqRc(strStringResource, strValueOf, z ? liveColors.m6397getAccent0d7_KjU() : liveColors.m6404getFgMute0d7_KjU(), androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null), composerStartRestartGroup, 0, 0);
            String strStringResource2 = StringResources_androidKt.stringResource(R.string.live, composerStartRestartGroup, 0);
            String strValueOf2 = String.valueOf(i11 > 1 ? 1 : i11);
            LiveColors liveColors2 = LiveColors.INSTANCE;
            m6392GuideTimelinePillcf5BqRc(strStringResource2, strValueOf2, liveColors2.m6407getLiveRed0d7_KjU(), androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null), composerStartRestartGroup, 0, 0);
            m6392GuideTimelinePillcf5BqRc(StringResources_androidKt.stringResource(R.string.later, composerStartRestartGroup, 0), String.valueOf(i12), liveColors2.m6403getFgDim0d7_KjU(), androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null), composerStartRestartGroup, 0, 0);
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.tv.live.n0
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return FullscreenGuideOverlayKt.GuideTimelineSummary$lambda$1(i14, i11, i12, z, z5, i13, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 GuideTimelineSummary$lambda$1(int i10, int i11, int i12, boolean z, boolean z5, int i13, Composer composer, int i14) {
        GuideTimelineSummary(i10, i11, i12, z, z5, composer, RecomposeScopeImplKt.updateChangedFlags(i13 | 1));
        return x6.t0.f22605a;
    }

    private static final String startsLabel(IptvProgram iptvProgram, long j10, Composer composer, int i10) {
        String strStringResource;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1066922616, i10, -1, "com.arflix.tv.ui.screens.tv.live.startsLabel (FullscreenGuideOverlay.kt:729)");
        }
        long startUtcMillis = (iptvProgram.getStartUtcMillis() - j10) / 60000;
        if (startUtcMillis < 0) {
            startUtcMillis = 0;
        }
        if (startUtcMillis < 60) {
            composer.startReplaceGroup(1184555978);
            strStringResource = StringResources_androidKt.stringResource(R.string.live_label_starts_in_min, new Object[]{Integer.valueOf((int) startUtcMillis)}, composer, 0);
            composer.endReplaceGroup();
        } else if (startUtcMillis < 1440) {
            composer.startReplaceGroup(1184559080);
            long j11 = 60;
            strStringResource = StringResources_androidKt.stringResource(R.string.live_label_starts_in_hm, new Object[]{Integer.valueOf((int) (startUtcMillis / j11)), Integer.valueOf((int) (startUtcMillis % j11))}, composer, 0);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(1184562648);
            strStringResource = StringResources_androidKt.stringResource(R.string.live_label_starts_later, composer, 0);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return strStringResource;
    }

    private static final boolean supportsFullscreenCatchup(EnrichedChannel enrichedChannel) {
        String catchupSource;
        IptvChannel source = enrichedChannel.getSource();
        if (source.getCatchupDays() > 0) {
            return true;
        }
        String catchupType = source.getCatchupType();
        return !(catchupType == null || kotlin.text.o.h0(catchupType)) || !((catchupSource = source.getCatchupSource()) == null || kotlin.text.o.h0(catchupSource)) || kotlin.text.o.T(source.getStreamUrl(), "/timeshift/", true) || source.getXtreamStreamId() != null || kotlin.text.o.T(source.getStreamUrl(), "/live/", true);
    }

    private static final String timelineDateLabel(IptvProgram iptvProgram, long j10, Composer composer, int i10) {
        String strStringResource;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(322594954, i10, -1, "com.arflix.tv.ui.screens.tv.live.timelineDateLabel (FullscreenGuideOverlay.kt:707)");
        }
        ZoneId zoneIdSystemDefault = ZoneId.systemDefault();
        LocalDate localDate = Instant.ofEpochMilli(j10).atZone(zoneIdSystemDefault).f();
        LocalDate localDate2 = Instant.ofEpochMilli(iptvProgram.getStartUtcMillis()).atZone(zoneIdSystemDefault).f();
        if (kotlin.jvm.internal.p.a(localDate2, localDate.minusDays(1L))) {
            composer.startReplaceGroup(776116407);
            strStringResource = StringResources_androidKt.stringResource(R.string.live_label_yesterday, composer, 0);
            composer.endReplaceGroup();
        } else if (kotlin.jvm.internal.p.a(localDate2, localDate)) {
            composer.startReplaceGroup(776118419);
            strStringResource = StringResources_androidKt.stringResource(R.string.live_label_today, composer, 0);
            composer.endReplaceGroup();
        } else if (kotlin.jvm.internal.p.a(localDate2, localDate.plusDays(1L))) {
            composer.startReplaceGroup(776120694);
            strStringResource = StringResources_androidKt.stringResource(R.string.live_label_tomorrow, composer, 0);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(776123325);
            composer.endReplaceGroup();
            strStringResource = timelineDateFormatter.format(localDate2);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return strStringResource;
    }
}
