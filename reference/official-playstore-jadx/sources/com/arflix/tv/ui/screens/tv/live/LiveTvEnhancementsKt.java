package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ErrorOutlineKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.extractor.WavUtil;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.data.repository.IptvConfig;
import com.arflix.tv.data.repository.IptvPlaylistEntry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\r\u001a)\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00022\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a%\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\f\u0010\r\u001a)\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000b0\u000e2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a'\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u0011H\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0015\u0010\u0015\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\u0015\u0010\u0016\u001a-\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00172\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0018\u0010\u0019\u001a;\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0018\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0017¢\u0006\u0004\b\u001b\u0010\u001c\u001aG\u0010\u001f\u001a\u0004\u0018\u00010\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\t2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\u00172\u0018\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0017¢\u0006\u0004\b\u001f\u0010 \u001a/\u0010\"\u001a\u00020!2\u0006\u0010\b\u001a\u00020\u00032\u0018\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0017¢\u0006\u0004\b\"\u0010#\u001a\u0013\u0010%\u001a\u00020!*\u00020$H\u0002¢\u0006\u0004\b%\u0010&\u001ao\u00102\u001a\u00020)2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00050\u00022\u0006\u0010(\u001a\u00020\t2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020)0\u000e2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010+2\u000e\b\u0002\u0010.\u001a\b\u0012\u0004\u0012\u00020)0-2\u000e\b\u0002\u0010/\u001a\b\u0012\u0004\u0012\u00020)0-2\b\b\u0002\u00101\u001a\u000200H\u0007¢\u0006\u0004\b2\u00103\u001aC\u00109\u001a\u00020)2\u0006\u00104\u001a\u00020\u000b2\b\u00105\u001a\u0004\u0018\u00010\t2\u0006\u00106\u001a\u00020!2\u0006\u00107\u001a\u00020!2\u0006\u00108\u001a\u00020\u000b2\b\b\u0002\u00101\u001a\u000200H\u0007¢\u0006\u0004\b9\u0010:\u001a3\u0010>\u001a\u00020)2\u0006\u0010;\u001a\u00020\t2\u0006\u0010<\u001a\u00020!2\b\u0010=\u001a\u0004\u0018\u00010\t2\b\b\u0002\u00101\u001a\u000200H\u0007¢\u0006\u0004\b>\u0010?\u001a#\u0010B\u001a\u00020)2\b\u0010A\u001a\u0004\u0018\u00010@2\b\b\u0002\u00101\u001a\u000200H\u0007¢\u0006\u0004\bB\u0010C\u001aI\u0010G\u001a\u00020)2\b\u0010\b\u001a\u0004\u0018\u00010\u00032\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010E\u001a\b\u0012\u0004\u0012\u00020)0-2\u0012\u0010F\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020)0\u000eH\u0007¢\u0006\u0004\bG\u0010H\u001a/\u0010I\u001a\u00020)2\u0006\u0010\b\u001a\u00020\u00032\f\u0010F\u001a\b\u0012\u0004\u0012\u00020)0-2\b\b\u0002\u00101\u001a\u000200H\u0003¢\u0006\u0004\bI\u0010J¨\u0006M²\u0006\u0010\u0010K\u001a\u0004\u0018\u00010\t8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010L\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002"}, d2 = {"Lcom/arflix/tv/data/repository/IptvConfig;", "config", "", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "channels", "Lcom/arflix/tv/ui/screens/tv/live/TvProviderFilter;", "buildTvProviderFilters", "(Lcom/arflix/tv/data/repository/IptvConfig;Ljava/util/List;)Ljava/util/List;", "channel", "", "providerId", "", "providerMatches", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Ljava/lang/String;Lcom/arflix/tv/data/repository/IptvConfig;)Z", "Lkotlin/Function1;", "providerMatcher", "(Ljava/lang/String;Lcom/arflix/tv/data/repository/IptvConfig;)Lr7/l;", "", "knownIds", "channelPlaylistId", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Ljava/util/Set;)Ljava/lang/String;", "variantGroupKey", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;)Ljava/lang/String;", "", "buildVariantGroups", "(Ljava/util/List;)Ljava/util/Map;", "variantGroups", "collapseChannelVariants", "(Ljava/util/List;Ljava/util/Map;)Ljava/util/List;", "channelId", "allChannelsById", "displayChannelIdFor", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;)Ljava/lang/String;", "", "variantCountFor", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Ljava/util/Map;)I", "Lcom/arflix/tv/ui/screens/tv/live/Quality;", "rank", "(Lcom/arflix/tv/ui/screens/tv/live/Quality;)I", "providers", "selectedId", "Lx6/t0;", "onSelect", "Landroidx/compose/ui/focus/FocusRequester;", "focusRequester", "Lkotlin/Function0;", "onMoveUp", "onMoveDown", "Landroidx/compose/ui/Modifier;", "modifier", "ProviderSelector", "(Ljava/util/List;Ljava/lang/String;Lr7/l;Landroidx/compose/ui/focus/FocusRequester;Lr7/a;Lr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "isLoading", "warning", "matchedCount", "totalChannels", "hasGuideSource", "EpgStatusStrip", "(ZLjava/lang/String;IIZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "buffer", "matchCount", "exactChannelName", "ChannelNumberOverlay", "(Ljava/lang/String;ILjava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "Lcom/arflix/tv/ui/screens/tv/live/PlaybackDiagnostic;", "diagnostic", "PlaybackDiagnosticBanner", "(Lcom/arflix/tv/ui/screens/tv/live/PlaybackDiagnostic;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "variants", "onDismiss", "onPick", "VariantPickerOverlay", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Ljava/util/List;Lr7/a;Lr7/l;Landroidx/compose/runtime/Composer;I)V", "VariantRow", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Lr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "focusedId", "focused", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LiveTvEnhancementsKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Quality.values().length];
            try {
                iArr[Quality.K4.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Quality.FHD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Quality.HD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Quality.SD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[PlaybackDiagnosticSeverity.values().length];
            try {
                iArr2[PlaybackDiagnosticSeverity.Info.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[PlaybackDiagnosticSeverity.Warning.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[PlaybackDiagnosticSeverity.Error.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ChannelNumberOverlay(final java.lang.String r15, final int r16, final java.lang.String r17, androidx.compose.ui.Modifier r18, androidx.compose.runtime.Composer r19, final int r20, final int r21) {
        /*
            Method dump skipped, instruction units count: 203
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvEnhancementsKt.ChannelNumberOverlay(java.lang.String, int, java.lang.String, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ChannelNumberOverlay$lambda$0(String str, String str2, int i10, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i11) {
        String strStringResource;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1963071373, i11, -1, "com.arflix.tv.ui.screens.tv.live.ChannelNumberOverlay.<anonymous> (LiveTvEnhancements.kt:347)");
        }
        float f10 = 12;
        Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.i(f10, Modifier.INSTANCE), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3498getBlack0d7_KjU(), 0.78f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null);
        float fM5678constructorimpl = Dp.m5678constructorimpl(1);
        LiveColors liveColors = LiveColors.INSTANCE;
        Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(com.arflix.tv.data.repository.g.k(f10, modifierM179backgroundbw27NRU$default, fM5678constructorimpl, liveColors.m6401getDividerStrong0d7_KjU()), Dp.m5678constructorimpl(18), Dp.m5678constructorimpl(f10));
        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(androidx.compose.foundation.c.g(4, Arrangement.INSTANCE, composer, -483455358), Alignment.INSTANCE.getEnd(), composer, 54);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
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
        r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyColumnMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        LiveType liveType = LiveType.INSTANCE;
        TextKt.m6020Text4IGK_g(str, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(liveType.getNumberMono(), liveColors.m6402getFg0d7_KjU(), TextUnitKt.getSp(24), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777212, null), composer, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
        if (str2 == null) {
            if (i10 > 0) {
                composer.startReplaceGroup(1381733486);
                strStringResource = StringResources_androidKt.stringResource(R.string.live_label_matches, new Object[]{Integer.valueOf(i10)}, composer, 0);
            } else {
                composer.startReplaceGroup(1381735429);
                strStringResource = StringResources_androidKt.stringResource(R.string.live_empty_no_channel, composer, 0);
            }
            composer.endReplaceGroup();
        } else {
            strStringResource = str2;
        }
        TextKt.m6020Text4IGK_g(strStringResource, null, 0L, 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(liveType.getSectionTag(), i10 > 0 ? liveColors.m6403getFgDim0d7_KjU() : ColorKt.Color(4294937242L), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composer, 0, 3120, 55294);
        if (androidx.compose.foundation.c.D(composer)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ChannelNumberOverlay$lambda$1(String str, int i10, String str2, Modifier modifier, int i11, int i12, Composer composer, int i13) {
        ChannelNumberOverlay(str, i10, str2, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:133:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void EpgStatusStrip(final boolean r45, final java.lang.String r46, final int r47, final int r48, final boolean r49, androidx.compose.ui.Modifier r50, androidx.compose.runtime.Composer r51, final int r52, final int r53) {
        /*
            Method dump skipped, instruction units count: 794
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvEnhancementsKt.EpgStatusStrip(boolean, java.lang.String, int, int, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EpgStatusStrip$lambda$0(boolean z, String str, int i10, int i11, boolean z5, Modifier modifier, int i12, int i13, Composer composer, int i14) {
        EpgStatusStrip(z, str, i10, i11, z5, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), i13);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EpgStatusStrip$lambda$2(boolean z, String str, int i10, int i11, boolean z5, Modifier modifier, int i12, int i13, Composer composer, int i14) {
        EpgStatusStrip(z, str, i10, i11, z5, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), i13);
        return x6.t0.f22605a;
    }

    public static final void PlaybackDiagnosticBanner(PlaybackDiagnostic playbackDiagnostic, Modifier modifier, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(-331379488);
        if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(playbackDiagnostic) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        int i13 = i11 & 2;
        if (i13 != 0) {
            i12 |= 48;
        } else if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        if ((i12 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                modifier = Modifier.INSTANCE;
            }
            Modifier modifier2 = modifier;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-331379488, i12, -1, "com.arflix.tv.ui.screens.tv.live.PlaybackDiagnosticBanner (LiveTvEnhancements.kt:375)");
            }
            AnimatedVisibilityKt.AnimatedVisibility(playbackDiagnostic != null, modifier2, EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null), EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null), (String) null, ComposableLambdaKt.rememberComposableLambda(234937784, true, new d1(playbackDiagnostic, 0), composerStartRestartGroup, 54), composerStartRestartGroup, (i12 & 112) | 200064, 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.components.p1(playbackDiagnostic, modifier, i10, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlaybackDiagnosticBanner$lambda$0(PlaybackDiagnostic playbackDiagnostic, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        long jM6397getAccent0d7_KjU;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(234937784, i10, -1, "com.arflix.tv.ui.screens.tv.live.PlaybackDiagnosticBanner.<anonymous> (LiveTvEnhancements.kt:382)");
        }
        x6.t0 t0Var = x6.t0.f22605a;
        if (playbackDiagnostic != null) {
            int i11 = WhenMappings.$EnumSwitchMapping$1[playbackDiagnostic.getSeverity().ordinal()];
            if (i11 == 1) {
                jM6397getAccent0d7_KjU = LiveColors.INSTANCE.m6397getAccent0d7_KjU();
            } else if (i11 == 2) {
                jM6397getAccent0d7_KjU = ColorKt.Color(4294950986L);
            } else {
                if (i11 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                jM6397getAccent0d7_KjU = ColorKt.Color(4294929281L);
            }
            long j10 = jM6397getAccent0d7_KjU;
            Modifier.Companion companion = Modifier.INSTANCE;
            float f10 = 12;
            float f11 = 10;
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(com.arflix.tv.data.repository.g.k(f10, BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.i(f10, companion), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3498getBlack0d7_KjU(), 0.78f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(j10, 0.65f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(14), Dp.m5678constructorimpl(f11));
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment.Vertical centerVertically = companion2.getCenterVertically();
            Arrangement arrangement = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(f11, arrangement, composer, 693286680), centerVertically, composer, 54);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            IconKt.m1610Iconww6aTOc(ErrorOutlineKt.getErrorOutline(Icons.Filled.INSTANCE), (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(20)), j10, composer, 432, 0);
            MeasurePolicy measurePolicyJ = androidx.compose.foundation.c.j(companion2, androidx.compose.foundation.c.g(2, arrangement, composer, -483455358), composer, 6, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(companion);
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
            r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyJ, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            String title = playbackDiagnostic.getTitle();
            LiveType liveType = LiveType.INSTANCE;
            TextStyle cellTitle = liveType.getCellTitle();
            LiveColors liveColors = LiveColors.INSTANCE;
            TextKt.m6020Text4IGK_g(title, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(cellTitle, liveColors.m6402getFg0d7_KjU(), TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777212, null), composer, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            TextKt.m6020Text4IGK_g(playbackDiagnostic.getDetail(), null, 0L, 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 2, 0, null, TextStyle.m5205copyp1EtxEg$default(liveType.getSectionTag(), liveColors.m6403getFgDim0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composer, 0, 3120, 55294);
            if (androidx.compose.foundation.c.A(composer)) {
                ComposerKt.traceEventEnd();
            }
        } else if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
            return t0Var;
        }
        return t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlaybackDiagnosticBanner$lambda$1(PlaybackDiagnostic playbackDiagnostic, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        PlaybackDiagnosticBanner(playbackDiagnostic, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x012f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ProviderSelector(final java.util.List<com.arflix.tv.ui.screens.tv.live.TvProviderFilter> r84, final java.lang.String r85, final r7.l<? super java.lang.String, x6.t0> r86, androidx.compose.ui.focus.FocusRequester r87, r7.a<x6.t0> r88, r7.a<x6.t0> r89, androidx.compose.ui.Modifier r90, androidx.compose.runtime.Composer r91, final int r92, final int r93) {
        /*
            Method dump skipped, instruction units count: 1594
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvEnhancementsKt.ProviderSelector(java.util.List, java.lang.String, r7.l, androidx.compose.ui.focus.FocusRequester, r7.a, r7.a, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ProviderSelector$lambda$2(List list, String str, r7.l lVar, FocusRequester focusRequester, r7.a aVar, r7.a aVar2, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        ProviderSelector(list, str, lVar, focusRequester, aVar, aVar2, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    private static final String ProviderSelector$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ProviderSelector$lambda$6$0$2$0(TvProviderFilter tvProviderFilter, MutableState mutableState, FocusState focusState) {
        if (focusState.getHasFocus()) {
            mutableState.setValue(tvProviderFilter.getId());
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ProviderSelector$lambda$6$0$4$0(r7.l lVar, TvProviderFilter tvProviderFilter) {
        lVar.invoke(tvProviderFilter.getId());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ProviderSelector$lambda$7(List list, String str, r7.l lVar, FocusRequester focusRequester, r7.a aVar, r7.a aVar2, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        ProviderSelector(list, str, lVar, focusRequester, aVar, aVar2, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x041a  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x041c  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0424  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x045e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void VariantPickerOverlay(final com.arflix.tv.ui.screens.tv.live.EnrichedChannel r67, final java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel> r68, final r7.a<x6.t0> r69, final r7.l<? super com.arflix.tv.ui.screens.tv.live.EnrichedChannel, x6.t0> r70, androidx.compose.runtime.Composer r71, final int r72) {
        /*
            Method dump skipped, instruction units count: 1180
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvEnhancementsKt.VariantPickerOverlay(com.arflix.tv.ui.screens.tv.live.EnrichedChannel, java.util.List, r7.a, r7.l, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 VariantPickerOverlay$lambda$0(EnrichedChannel enrichedChannel, List list, r7.a aVar, r7.l lVar, int i10, Composer composer, int i11) {
        VariantPickerOverlay(enrichedChannel, list, aVar, lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 VariantPickerOverlay$lambda$3$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 VariantPickerOverlay$lambda$5$0$1$0(List list, FocusRequester focusRequester, r7.l lVar, r7.a aVar, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new LiveTvEnhancementsKt$VariantPickerOverlay$lambda$5$0$1$0$$inlined$itemsIndexed$default$1(new l0(17), list), new LiveTvEnhancementsKt$VariantPickerOverlay$lambda$5$0$1$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new LiveTvEnhancementsKt$VariantPickerOverlay$lambda$5$0$1$0$$inlined$itemsIndexed$default$3(list, focusRequester, lVar, aVar)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object VariantPickerOverlay$lambda$5$0$1$0$0(int i10, EnrichedChannel enrichedChannel) {
        return androidx.fragment.app.a2.j(i10, enrichedChannel.getId(), "#");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 VariantPickerOverlay$lambda$6(EnrichedChannel enrichedChannel, List list, r7.a aVar, r7.l lVar, int i10, Composer composer, int i11) {
        VariantPickerOverlay(enrichedChannel, list, aVar, lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0445  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:110:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void VariantRow(com.arflix.tv.ui.screens.tv.live.EnrichedChannel r104, r7.a<x6.t0> r105, androidx.compose.ui.Modifier r106, androidx.compose.runtime.Composer r107, int r108, int r109) {
        /*
            Method dump skipped, instruction units count: 1119
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvEnhancementsKt.VariantRow(com.arflix.tv.ui.screens.tv.live.EnrichedChannel, r7.a, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean VariantRow$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void VariantRow$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 VariantRow$lambda$3$0(MutableState mutableState, FocusState focusState) {
        VariantRow$lambda$2(mutableState, focusState.getHasFocus());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 VariantRow$lambda$6(EnrichedChannel enrichedChannel, r7.a aVar, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        VariantRow(enrichedChannel, aVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    public static final List<TvProviderFilter> buildTvProviderFilters(IptvConfig iptvConfig, List<EnrichedChannel> list) {
        List<IptvPlaylistEntry> playlists = iptvConfig.getPlaylists();
        ArrayList arrayList = new ArrayList();
        for (Object obj : playlists) {
            IptvPlaylistEntry iptvPlaylistEntry = (IptvPlaylistEntry) obj;
            if (iptvPlaylistEntry.getEnabled() && !kotlin.text.o.h0(iptvPlaylistEntry.getId())) {
                arrayList.add(obj);
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList<IptvPlaylistEntry> arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (hashSet.add(((IptvPlaylistEntry) obj2).getId())) {
                arrayList2.add(obj2);
            }
        }
        if (arrayList2.size() > 1) {
            HashSet hashSet2 = new HashSet();
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                hashSet2.add(((IptvPlaylistEntry) it.next()).getId());
            }
            final ArrayList arrayList3 = new ArrayList();
            Iterator<T> it2 = list.iterator();
            while (it2.hasNext()) {
                String strChannelPlaylistId = channelPlaylistId((EnrichedChannel) it2.next(), hashSet2);
                if (strChannelPlaylistId != null) {
                    arrayList3.add(strChannelPlaylistId);
                }
            }
            Map mapD0 = xc.d.d0(new kotlin.collections.c0<String, String>() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvEnhancementsKt$buildTvProviderFilters$$inlined$groupingBy$1
                @Override // kotlin.collections.c0
                public String keyOf(String element) {
                    return element;
                }

                @Override // kotlin.collections.c0
                public Iterator<String> sourceIterator() {
                    return arrayList3.iterator();
                }
            });
            if (mapD0.size() > 1) {
                z6.c cVarN = t7.a.n();
                cVarN.add(new TvProviderFilter(TtmlNode.COMBINE_ALL, "All providers", list.size()));
                for (IptvPlaylistEntry iptvPlaylistEntry2 : arrayList2) {
                    Integer num = (Integer) mapD0.get(iptvPlaylistEntry2.getId());
                    int iIntValue = num != null ? num.intValue() : 0;
                    if (iIntValue > 0) {
                        String id = iptvPlaylistEntry2.getId();
                        String name = iptvPlaylistEntry2.getName();
                        if (kotlin.text.o.h0(name)) {
                            name = iptvPlaylistEntry2.getId();
                        }
                        cVarN.add(new TvProviderFilter(id, name, iIntValue));
                    }
                }
                return t7.a.e(cVarN);
            }
        }
        return kotlin.collections.z.f19728i;
    }

    public static final Map<String, List<EnrichedChannel>> buildVariantGroups(List<EnrichedChannel> list) {
        return kotlin.collections.a0.f19683i;
    }

    private static final String channelPlaylistId(EnrichedChannel enrichedChannel, Set<String> set) {
        String strG0 = kotlin.text.o.G0(':', enrichedChannel.getId(), "");
        if (set.contains(strG0)) {
            return strG0;
        }
        return null;
    }

    public static final List<EnrichedChannel> collapseChannelVariants(List<EnrichedChannel> list, Map<String, ? extends List<EnrichedChannel>> map) {
        if (map.isEmpty()) {
            return list;
        }
        HashSet hashSet = new HashSet();
        z6.c cVar = new z6.c(list.size());
        for (EnrichedChannel enrichedChannel : list) {
            String strVariantGroupKey = variantGroupKey(enrichedChannel);
            List<EnrichedChannel> list2 = map.get(strVariantGroupKey);
            if (list2 == null) {
                cVar.add(enrichedChannel);
            } else if (hashSet.add(strVariantGroupKey)) {
                cVar.add(kotlin.collections.x.m0(list2));
            }
        }
        return t7.a.e(cVar);
    }

    public static final String displayChannelIdFor(String str, Map<String, EnrichedChannel> map, Map<String, ? extends List<EnrichedChannel>> map2) {
        EnrichedChannel enrichedChannel;
        EnrichedChannel enrichedChannel2;
        String id;
        if (str == null || (enrichedChannel = map.get(str)) == null) {
            return str;
        }
        List<EnrichedChannel> list = map2.get(variantGroupKey(enrichedChannel));
        return (list == null || (enrichedChannel2 = (EnrichedChannel) kotlin.collections.x.o0(list)) == null || (id = enrichedChannel2.getId()) == null) ? enrichedChannel.getId() : id;
    }

    public static final r7.l<EnrichedChannel, Boolean> providerMatcher(String str, IptvConfig iptvConfig) {
        if (str.equals(TtmlNode.COMBINE_ALL)) {
            return new w0(12);
        }
        List<IptvPlaylistEntry> playlists = iptvConfig.getPlaylists();
        ArrayList arrayList = new ArrayList();
        for (Object obj : playlists) {
            IptvPlaylistEntry iptvPlaylistEntry = (IptvPlaylistEntry) obj;
            if (iptvPlaylistEntry.getEnabled() && !kotlin.text.o.h0(iptvPlaylistEntry.getId())) {
                arrayList.add(obj);
            }
        }
        HashSet hashSet = new HashSet();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            hashSet.add(((IptvPlaylistEntry) it.next()).getId());
        }
        return new x2(hashSet, str, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean providerMatcher$lambda$0(EnrichedChannel enrichedChannel) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean providerMatcher$lambda$3(HashSet hashSet, String str, EnrichedChannel enrichedChannel) {
        return kotlin.jvm.internal.p.a(channelPlaylistId(enrichedChannel, hashSet), str);
    }

    public static final boolean providerMatches(EnrichedChannel enrichedChannel, String str, IptvConfig iptvConfig) {
        if (str.equals(TtmlNode.COMBINE_ALL)) {
            return true;
        }
        List<IptvPlaylistEntry> playlists = iptvConfig.getPlaylists();
        ArrayList arrayList = new ArrayList();
        for (Object obj : playlists) {
            IptvPlaylistEntry iptvPlaylistEntry = (IptvPlaylistEntry) obj;
            if (iptvPlaylistEntry.getEnabled() && !kotlin.text.o.h0(iptvPlaylistEntry.getId())) {
                arrayList.add(obj);
            }
        }
        HashSet hashSet = new HashSet();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            hashSet.add(((IptvPlaylistEntry) it.next()).getId());
        }
        return kotlin.jvm.internal.p.a(channelPlaylistId(enrichedChannel, hashSet), str);
    }

    private static final int rank(Quality quality) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[quality.ordinal()];
        if (i10 == 1) {
            return 4;
        }
        if (i10 == 2) {
            return 3;
        }
        if (i10 == 3) {
            return 2;
        }
        if (i10 == 4) {
            return 1;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final int variantCountFor(EnrichedChannel enrichedChannel, Map<String, ? extends List<EnrichedChannel>> map) {
        List<EnrichedChannel> list = map.get(variantGroupKey(enrichedChannel));
        if (list != null) {
            return list.size();
        }
        return 1;
    }

    public static final String variantGroupKey(EnrichedChannel enrichedChannel) {
        String string;
        String string2;
        String variantKey = enrichedChannel.getSource().getVariantKey();
        String str = null;
        if (variantKey != null && (string2 = kotlin.text.o.L0(variantKey).toString()) != null) {
            if (kotlin.text.o.h0(string2)) {
                string2 = null;
            }
            if (string2 != null) {
                return string2;
            }
        }
        String epgId = enrichedChannel.getSource().getEpgId();
        if (epgId != null && (string = kotlin.text.o.L0(epgId).toString()) != null) {
            String lowerCase = string.toLowerCase(Locale.ROOT);
            if (!kotlin.text.o.h0(lowerCase)) {
                str = lowerCase;
            }
        }
        return str == null ? enrichedChannel.getId() : str;
    }
}
