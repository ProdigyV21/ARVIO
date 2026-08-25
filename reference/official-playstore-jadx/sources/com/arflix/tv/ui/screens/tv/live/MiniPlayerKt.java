package com.arflix.tv.ui.screens.tv.live;

import android.content.Context;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.AlphaKt;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.WavUtil;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.ui.PlayerView;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.util.MediaBadgesKt;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0004\u001a\u0097\u0001\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0001\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000b2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u0017\u0010\u0018\u001aG\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0001\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0014\u001a\u00020\u00132\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000e2\b\b\u0002\u0010\u0016\u001a\u00020\u0015H\u0003¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u0019\u0010\u001b\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u0015H\u0003¢\u0006\u0004\b\u001b\u0010\u001c\u001ai\u0010\u001e\u001a\u00020\f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001d\u001a\u00020\u00132\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0011\u001a\u00020\u00102\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000e2\b\b\u0002\u0010\u0016\u001a\u00020\u0015H\u0003¢\u0006\u0004\b\u001e\u0010\u001f\u001a1\u0010 \u001a\u00020\f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0011\u001a\u00020\u00102\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000eH\u0003¢\u0006\u0004\b \u0010!\u001a'\u0010#\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u00102\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000eH\u0003¢\u0006\u0004\b#\u0010$\u001a\u0017\u0010'\u001a\u00020\f2\u0006\u0010&\u001a\u00020%H\u0003¢\u0006\u0004\b'\u0010(\u001a\u0017\u0010*\u001a\u00020\f2\u0006\u0010)\u001a\u00020\tH\u0003¢\u0006\u0004\b*\u0010+\u001a+\u0010,\u001a\u00020\f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0003¢\u0006\u0004\b,\u0010-\u001a\u0019\u0010.\u001a\u00020\f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0003¢\u0006\u0004\b.\u0010/\u001a\u0019\u00102\u001a\u00020\t2\b\u00101\u001a\u0004\u0018\u000100H\u0000¢\u0006\u0004\b2\u00103\u001a\u0017\u00105\u001a\u00020\t2\u0006\u00104\u001a\u00020\u0004H\u0000¢\u0006\u0004\b5\u00106\u001a\u0019\u00107\u001a\u00020\t2\b\u00101\u001a\u0004\u0018\u000100H\u0000¢\u0006\u0004\b7\u00103\u001a\u001b\u00109\u001a\u0004\u0018\u0001082\b\u00101\u001a\u0004\u0018\u000100H\u0000¢\u0006\u0004\b9\u0010:¨\u0006<²\u0006\f\u0010;\u001a\u0002088\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/media3/exoplayer/ExoPlayer;", "exoPlayer", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "channel", "", "clockTickMillis", "Lcom/arflix/tv/data/model/IptvNowNext;", "nowNext", "", "", "favoriteSet", "Lkotlin/Function1;", "Lx6/t0;", "onFavoriteToggle", "Lkotlin/Function0;", "onFullscreenClick", "", "variantCount", "onOpenVariants", "", "compact", "Landroidx/compose/ui/Modifier;", "modifier", "MiniPlayerRow", "(Landroidx/media3/exoplayer/ExoPlayer;Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;JLcom/arflix/tv/data/model/IptvNowNext;Ljava/util/Set;Lr7/l;Lr7/a;ILr7/a;ZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;III)V", "VideoCard", "(Landroidx/media3/exoplayer/ExoPlayer;Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;ZLr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "LiveBug", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "isFavorite", "InfoColumn", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;JLcom/arflix/tv/data/model/IptvNowNext;ZLr7/l;ILr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "ChannelIdentityRow", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;ILr7/a;Landroidx/compose/runtime/Composer;I)V", "count", "SourceBadge", "(ILr7/a;Landroidx/compose/runtime/Composer;I)V", "Lcom/arflix/tv/ui/screens/tv/live/Quality;", "q", "QualityBadge", "(Lcom/arflix/tv/ui/screens/tv/live/Quality;Landroidx/compose/runtime/Composer;I)V", "text", "LangBadge", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "NowCard", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;JLcom/arflix/tv/data/model/IptvNowNext;Landroidx/compose/runtime/Composer;I)V", "NextRow", "(Lcom/arflix/tv/data/model/IptvNowNext;Landroidx/compose/runtime/Composer;I)V", "Lcom/arflix/tv/data/model/IptvProgram;", TtmlNode.TAG_P, "formatTimeWindow", "(Lcom/arflix/tv/data/model/IptvProgram;)Ljava/lang/String;", "utcMillis", "formatClock", "(J)Ljava/lang/String;", "remainingLabel", "", "progressOf", "(Lcom/arflix/tv/data/model/IptvProgram;)Ljava/lang/Float;", "alpha", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MiniPlayerKt {
    private static final void ChannelIdentityRow(EnrichedChannel enrichedChannel, int i10, r7.a<x6.t0> aVar, Composer composer, int i11) {
        int i12;
        Composer composer2;
        int i13;
        Composer composerStartRestartGroup = composer.startRestartGroup(151706245);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(enrichedChannel) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(aVar) ? 256 : 128;
        }
        int i14 = i12;
        if ((i14 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(151706245, i14, -1, "com.arflix.tv.ui.screens.tv.live.ChannelIdentityRow (MiniPlayer.kt:274)");
            }
            Alignment.Companion companion = Alignment.INSTANCE;
            Alignment.Vertical centerVertically = companion.getCenterVertically();
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.HorizontalOrVertical horizontalOrVerticalG = androidx.compose.foundation.c.g(12, arrangement, composerStartRestartGroup, 693286680);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalG, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion2);
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
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            if (enrichedChannel != null) {
                composerStartRestartGroup.startReplaceGroup(-1739302523);
                ChannelLogoKt.m6366ChannelLogouFdPcIQ(enrichedChannel, Dp.m5678constructorimpl(30), null, composerStartRestartGroup, (i14 & 14) | 48, 4);
                MeasurePolicy measurePolicyJ = androidx.compose.foundation.c.j(companion, androidx.compose.foundation.c.g(1, arrangement, composerStartRestartGroup, -483455358), composerStartRestartGroup, 6, -1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(companion2);
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composerStartRestartGroup);
                r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyJ, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(8, arrangement, composerStartRestartGroup, 693286680), companion.getCenterVertically(), composerStartRestartGroup, 54);
                composerStartRestartGroup.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor3 = companion3.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(companion2);
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composerStartRestartGroup);
                r7.p pVarU3 = a0.c.u(companion3, composerM2991constructorimpl3, measurePolicyRowMeasurePolicy2, composerM2991constructorimpl3, currentCompositionLocalMap3);
                if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf3, composerStartRestartGroup, 0, 2058660585);
                String strStringResource = StringResources_androidKt.stringResource(R.string.live_label_ch, new Object[]{Integer.valueOf(enrichedChannel.getNumber())}, composerStartRestartGroup, 0);
                LiveType liveType = LiveType.INSTANCE;
                TextStyle sectionTag = liveType.getSectionTag();
                LiveColors liveColors = LiveColors.INSTANCE;
                TextKt.m6020Text4IGK_g(strStringResource, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(sectionTag, liveColors.m6404getFgMute0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composerStartRestartGroup, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
                TextKt.m6020Text4IGK_g(MediaBadgesKt.formatGenreName(enrichedChannel.getGenre().name()), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(liveType.getSectionTag(), liveColors.m6404getFgMute0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composerStartRestartGroup, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
                TextKt.m6020Text4IGK_g(enrichedChannel.getName(), null, 0L, 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(liveType.getChannelName(), liveColors.m6402getFg0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composerStartRestartGroup, 0, 3120, 55294);
                MeasurePolicy measurePolicyC = androidx.compose.foundation.c.C(companion, androidx.compose.foundation.c.g(5, arrangement, composerStartRestartGroup, 693286680), composerStartRestartGroup, 6, -1323940314);
                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor4 = companion3.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(companion2);
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor4);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl4 = Updater.m2991constructorimpl(composerStartRestartGroup);
                r7.p pVarU4 = a0.c.u(companion3, composerM2991constructorimpl4, measurePolicyC, composerM2991constructorimpl4, currentCompositionLocalMap4);
                if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                    a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf4, composerStartRestartGroup, 0, 2058660585);
                QualityBadge(enrichedChannel.getQuality(), composerStartRestartGroup, 0);
                if (i10 > 1) {
                    composerStartRestartGroup.startReplaceGroup(2102859092);
                    SourceBadge(i10, aVar, composerStartRestartGroup, (i14 >> 3) & 126);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(2102944621);
                    composerStartRestartGroup.endReplaceGroup();
                }
                String country = enrichedChannel.getCountry();
                if (country == null || country.equals(enrichedChannel.getLang())) {
                    country = null;
                }
                if (country == null) {
                    composerStartRestartGroup.startReplaceGroup(2103013719);
                    composerStartRestartGroup.endReplaceGroup();
                    i13 = 0;
                } else {
                    composerStartRestartGroup.startReplaceGroup(2103013720);
                    i13 = 0;
                    LangBadge(country, composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                }
                LangBadge(enrichedChannel.getLang(), composerStartRestartGroup, i13);
                com.arflix.tv.data.repository.g.v(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
            } else {
                composerStartRestartGroup.startReplaceGroup(-1737913320);
                Modifier modifierN = com.arflix.tv.data.repository.g.n(SizeKt.m578size3ABfNKs(companion2, Dp.m5678constructorimpl(48)), 10);
                LiveColors liveColors2 = LiveColors.INSTANCE;
                BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(modifierN, liveColors2.m6409getPanel0d7_KjU(), null, 2, null), composerStartRestartGroup, 0);
                composer2 = composerStartRestartGroup;
                TextKt.m6020Text4IGK_g("—", null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getChannelName(), liveColors2.m6404getFgMute0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composer2, 6, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
                composer2.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.components.p1(enrichedChannel, i10, aVar, i11, 6));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ChannelIdentityRow$lambda$1(EnrichedChannel enrichedChannel, int i10, r7.a aVar, int i11, Composer composer, int i12) {
        ChannelIdentityRow(enrichedChannel, i10, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:83:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void InfoColumn(final com.arflix.tv.ui.screens.tv.live.EnrichedChannel r17, final long r18, com.arflix.tv.data.model.IptvNowNext r20, final boolean r21, final r7.l<? super java.lang.String, x6.t0> r22, final int r23, final r7.a<x6.t0> r24, androidx.compose.ui.Modifier r25, androidx.compose.runtime.Composer r26, final int r27, final int r28) {
        /*
            Method dump skipped, instruction units count: 360
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.MiniPlayerKt.InfoColumn(com.arflix.tv.ui.screens.tv.live.EnrichedChannel, long, com.arflix.tv.data.model.IptvNowNext, boolean, r7.l, int, r7.a, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 InfoColumn$lambda$1(EnrichedChannel enrichedChannel, long j10, IptvNowNext iptvNowNext, boolean z, r7.l lVar, int i10, r7.a aVar, Modifier modifier, int i11, int i12, Composer composer, int i13) {
        InfoColumn(enrichedChannel, j10, iptvNowNext, z, lVar, i10, aVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return x6.t0.f22605a;
    }

    private static final void LangBadge(String str, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-444731616);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-444731616, i11, -1, "com.arflix.tv.ui.screens.tv.live.LangBadge (MiniPlayer.kt:354)");
            }
            Modifier modifierI = com.arflix.tv.data.repository.g.i(4, Modifier.INSTANCE);
            LiveColors liveColors = LiveColors.INSTANCE;
            Modifier modifierH = androidx.fragment.app.a2.h(2, BackgroundKt.m179backgroundbw27NRU$default(modifierI, liveColors.m6409getPanel0d7_KjU(), null, 2, null), Dp.m5678constructorimpl(6), composerStartRestartGroup, 733328855);
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
            TextKt.m6020Text4IGK_g(str.toUpperCase(Locale.ROOT), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getBadge(), liveColors.m6403getFgDim0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composer2, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.screens.collections.b(str, i10, 8));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LangBadge$lambda$1(String str, int i10, Composer composer, int i11) {
        LangBadge(str, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void LiveBug(Modifier modifier, Composer composer, int i10, int i11) {
        Modifier modifier2;
        int i12;
        Modifier modifier3;
        Composer composerStartRestartGroup = composer.startRestartGroup(725327917);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
            modifier2 = modifier;
        } else if ((i10 & 6) == 0) {
            modifier2 = modifier;
            i12 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i10;
        } else {
            modifier2 = modifier;
            i12 = i10;
        }
        if ((i12 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier modifier4 = i13 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(725327917, i12, -1, "com.arflix.tv.ui.screens.tv.live.LiveBug (MiniPlayer.kt:217)");
            }
            State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(InfiniteTransitionKt.rememberInfiniteTransition("live-bug", composerStartRestartGroup, 6, 0), 1.0f, 0.55f, AnimationSpecKt.m93infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(900, 0, null, 6, null), RepeatMode.Reverse, 0L, 4, null), "live-alpha", composerStartRestartGroup, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
            float f10 = 6;
            float f11 = 8;
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.n(modifier4, f10), ColorKt.Color(2852126720L), null, 2, null), Dp.m5678constructorimpl(f11), Dp.m5678constructorimpl(4));
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(f10, Arrangement.INSTANCE, composerStartRestartGroup, 693286680), Alignment.INSTANCE.getCenterVertically(), composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            BoxKt.Box(BackgroundKt.m178backgroundbw27NRU(AlphaKt.alpha(SizeKt.m578size3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(f11)), LiveBug$lambda$0(stateAnimateFloat)), LiveColors.INSTANCE.m6407getLiveRed0d7_KjU(), RoundedCornerShapeKt.getCircleShape()), composerStartRestartGroup, 0);
            modifier3 = modifier4;
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.live_badge_live, composerStartRestartGroup, 0), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getBadge(), Color.INSTANCE.m3509getWhite0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composerStartRestartGroup, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            composerStartRestartGroup = composerStartRestartGroup;
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.components.n1(modifier3, i10, i11, 5));
        }
    }

    private static final float LiveBug$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveBug$lambda$2(Modifier modifier, int i10, int i11, Composer composer, int i12) {
        LiveBug(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:184:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0114  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void MiniPlayerRow(final androidx.media3.exoplayer.ExoPlayer r31, final com.arflix.tv.ui.screens.tv.live.EnrichedChannel r32, final long r33, final com.arflix.tv.data.model.IptvNowNext r35, final java.util.Set<java.lang.String> r36, final r7.l<? super java.lang.String, x6.t0> r37, r7.a<x6.t0> r38, int r39, r7.a<x6.t0> r40, boolean r41, androidx.compose.ui.Modifier r42, androidx.compose.runtime.Composer r43, final int r44, final int r45, final int r46) {
        /*
            Method dump skipped, instruction units count: 984
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.MiniPlayerKt.MiniPlayerRow(androidx.media3.exoplayer.ExoPlayer, com.arflix.tv.ui.screens.tv.live.EnrichedChannel, long, com.arflix.tv.data.model.IptvNowNext, java.util.Set, r7.l, r7.a, int, r7.a, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MiniPlayerRow$lambda$2(ExoPlayer exoPlayer, EnrichedChannel enrichedChannel, long j10, IptvNowNext iptvNowNext, Set set, r7.l lVar, r7.a aVar, int i10, r7.a aVar2, boolean z, Modifier modifier, int i11, int i12, int i13, Composer composer, int i14) {
        MiniPlayerRow(exoPlayer, enrichedChannel, j10, iptvNowNext, set, lVar, aVar, i10, aVar2, z, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), RecomposeScopeImplKt.updateChangedFlags(i12), i13);
        return x6.t0.f22605a;
    }

    private static final void NextRow(final IptvNowNext iptvNowNext, Composer composer, final int i10) {
        int i11;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar;
        IptvProgram next;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1486533409);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(iptvNowNext) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1486533409, i11, -1, "com.arflix.tv.ui.screens.tv.live.NextRow (MiniPlayer.kt:423)");
            }
            if (iptvNowNext == null || (next = iptvNowNext.getNext()) == null) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final int i12 = 0;
                    pVar = new r7.p() { // from class: com.arflix.tv.ui.screens.tv.live.r2
                        @Override // r7.p
                        public final Object invoke(Object obj, Object obj2) {
                            int i13 = i12;
                            Composer composer3 = (Composer) obj;
                            int iIntValue = ((Integer) obj2).intValue();
                            switch (i13) {
                                case 0:
                                    return MiniPlayerKt.NextRow$lambda$0(iptvNowNext, i10, composer3, iIntValue);
                                default:
                                    return MiniPlayerKt.NextRow$lambda$2(iptvNowNext, i10, composer3, iIntValue);
                            }
                        }
                    };
                    scopeUpdateScopeEndRestartGroup.updateScope(pVar);
                }
                return;
            }
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalG = androidx.compose.foundation.c.g(10, Arrangement.INSTANCE, composerStartRestartGroup, 693286680);
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalG, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            String strStringResource = StringResources_androidKt.stringResource(R.string.live_badge_next, composerStartRestartGroup, 0);
            LiveType liveType = LiveType.INSTANCE;
            TextStyle sectionTag = liveType.getSectionTag();
            LiveColors liveColors = LiveColors.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(strStringResource, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(sectionTag, liveColors.m6404getFgMute0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composer2, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            TextKt.m6020Text4IGK_g(formatClock(next.getStartUtcMillis()), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(liveType.getTimeMono(), liveColors.m6403getFgDim0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composer2, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            TextKt.m6020Text4IGK_g(next.getTitle(), androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null), 0L, 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(liveType.getCellTitle(), liveColors.m6403getFgDim0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composer2, 0, 3120, 55292);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final int i13 = 1;
            pVar = new r7.p() { // from class: com.arflix.tv.ui.screens.tv.live.r2
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int i132 = i13;
                    Composer composer3 = (Composer) obj;
                    int iIntValue = ((Integer) obj2).intValue();
                    switch (i132) {
                        case 0:
                            return MiniPlayerKt.NextRow$lambda$0(iptvNowNext, i10, composer3, iIntValue);
                        default:
                            return MiniPlayerKt.NextRow$lambda$2(iptvNowNext, i10, composer3, iIntValue);
                    }
                }
            };
            scopeUpdateScopeEndRestartGroup.updateScope(pVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 NextRow$lambda$0(IptvNowNext iptvNowNext, int i10, Composer composer, int i11) {
        NextRow(iptvNowNext, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 NextRow$lambda$2(IptvNowNext iptvNowNext, int i10, Composer composer, int i11) {
        NextRow(iptvNowNext, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void NowCard(EnrichedChannel enrichedChannel, long j10, IptvNowNext iptvNowNext, Composer composer, int i10) {
        int i11;
        String name;
        Composer composerStartRestartGroup = composer.startRestartGroup(935752645);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(enrichedChannel) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(j10) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(iptvNowNext) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(935752645, i11, -1, "com.arflix.tv.ui.screens.tv.live.NowCard (MiniPlayer.kt:367)");
            }
            IptvProgram now = iptvNowNext != null ? iptvNowNext.getNow() : null;
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierClip = ClipKt.clip(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(LiveDims.INSTANCE.m6420getCardRadiusD9Ej5fM()));
            LiveColors liveColors = LiveColors.INSTANCE;
            float f10 = 10;
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(BackgroundKt.m179backgroundbw27NRU$default(modifierClip, liveColors.m6411getPanelRaised0d7_KjU(), null, 2, null), Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(8));
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.HorizontalOrVertical horizontalOrVerticalG = androidx.compose.foundation.c.g(4, arrangement, composerStartRestartGroup, -483455358);
            Alignment.Companion companion2 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyJ = androidx.compose.foundation.c.j(companion2, horizontalOrVerticalG, composerStartRestartGroup, 6, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyJ, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(f10, arrangement, composerStartRestartGroup, 693286680), companion2.getCenterVertically(), composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(companion);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composerStartRestartGroup);
            r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyRowMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            String strStringResource = StringResources_androidKt.stringResource(R.string.live_badge_now, composerStartRestartGroup, 0);
            LiveType liveType = LiveType.INSTANCE;
            int i12 = 1;
            TextKt.m6020Text4IGK_g(strStringResource, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(liveType.getSectionTag(), liveColors.m6397getAccent0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composerStartRestartGroup, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            TextKt.m6020Text4IGK_g(formatTimeWindow(now), null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(liveType.getTimeMono(), liveColors.m6402getFg0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composerStartRestartGroup, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            Composer composer2 = composerStartRestartGroup;
            SpacerKt.Spacer(androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null), composer2, 0);
            String strRemainingLabel = remainingLabel(now);
            if (kotlin.text.o.h0(strRemainingLabel)) {
                composer2.startReplaceGroup(-509402941);
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(-509568233);
                TextKt.m6020Text4IGK_g(strRemainingLabel, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(liveType.getTimeMono(), liveColors.m6397getAccent0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composer2, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
                composer2 = composer2;
                composer2.endReplaceGroup();
            }
            androidx.compose.foundation.c.y(composer2);
            if (now == null || (name = now.getTitle()) == null) {
                name = enrichedChannel != null ? enrichedChannel.getName() : null;
            }
            if (name == null) {
                composer2.startReplaceGroup(-1602268661);
                name = StringResources_androidKt.stringResource(R.string.live_empty_no_programme, composer2, 0);
            } else {
                composer2.startReplaceGroup(-1602269622);
            }
            composer2.endReplaceGroup();
            String str = name;
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(liveType.getProgramTitle(), liveColors.m6402getFg0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null);
            TextOverflow.Companion companion4 = TextOverflow.INSTANCE;
            Composer composer3 = composer2;
            TextKt.m6020Text4IGK_g(str, null, 0L, 0L, null, null, null, 0L, null, null, 0L, companion4.m5632getEllipsisgIe3tQ8(), false, 2, 0, null, textStyleM5205copyp1EtxEg$default, composer3, 0, 3120, 55294);
            composerStartRestartGroup = composer3;
            String description = now != null ? now.getDescription() : null;
            if (description == null || kotlin.text.o.h0(description)) {
                composerStartRestartGroup.startReplaceGroup(1869768615);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1869546314);
                TextKt.m6020Text4IGK_g(now.getDescription(), null, 0L, 0L, null, null, null, 0L, null, null, 0L, companion4.m5632getEllipsisgIe3tQ8(), false, 2, 0, null, TextStyle.m5205copyp1EtxEg$default(liveType.getBodySynopsis(), liveColors.m6403getFgDim0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null), composerStartRestartGroup, 0, 3120, 55294);
                composerStartRestartGroup = composerStartRestartGroup;
                composerStartRestartGroup.endReplaceGroup();
            }
            if (now == null || j10 < 0) {
                now = null;
            }
            Float fProgressOf = progressOf(now);
            if (fProgressOf != null) {
                composerStartRestartGroup.startReplaceGroup(1869880742);
                boolean zChanged = composerStartRestartGroup.changed(fProgressOf);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new u(fProgressOf, i12);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ProgressIndicatorKt.m1768LinearProgressIndicator_5eSRE((r7.a<Float>) objRememberedValue, com.arflix.tv.data.repository.g.n(SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), Dp.m5678constructorimpl(3)), 2), liveColors.m6397getAccent0d7_KjU(), liveColors.m6409getPanel0d7_KjU(), 0, composerStartRestartGroup, 0, 16);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1870158471);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.screens.home.g0(enrichedChannel, j10, iptvNowNext, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 NowCard$lambda$1(EnrichedChannel enrichedChannel, long j10, IptvNowNext iptvNowNext, int i10, Composer composer, int i11) {
        NowCard(enrichedChannel, j10, iptvNowNext, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void QualityBadge(Quality quality, Composer composer, int i10) {
        int i11;
        Composer composer2;
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(185605705);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(quality.ordinal()) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
            i12 = 2;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(185605705, i11, -1, "com.arflix.tv.ui.screens.tv.live.QualityBadge (MiniPlayer.kt:341)");
            }
            Modifier modifierI = com.arflix.tv.data.repository.g.i(4, Modifier.INSTANCE);
            LiveColors liveColors = LiveColors.INSTANCE;
            Modifier modifierH = androidx.fragment.app.a2.h(2, BackgroundKt.m179backgroundbw27NRU$default(modifierI, liveColors.m6409getPanel0d7_KjU(), null, 2, null), Dp.m5678constructorimpl(6), composerStartRestartGroup, 733328855);
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
            String label = quality.getLabel();
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getBadge(), liveColors.m6402getFg0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null);
            i12 = 2;
            TextKt.m6020Text4IGK_g(label, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, textStyleM5205copyp1EtxEg$default, composer2, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.screens.collections.k(quality, i10, i12));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 QualityBadge$lambda$1(Quality quality, int i10, Composer composer, int i11) {
        QualityBadge(quality, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void SourceBadge(int i10, r7.a<x6.t0> aVar, Composer composer, int i11) {
        int i12;
        Modifier modifierM214clickableXHw0xAI$default;
        Composer composer2;
        int i13;
        Composer composerStartRestartGroup = composer.startRestartGroup(-533790651);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(i10) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(aVar) ? 32 : 16;
        }
        if ((i12 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
            i13 = 2;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-533790651, i12, -1, "com.arflix.tv.ui.screens.tv.live.SourceBadge (MiniPlayer.kt:327)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierI = com.arflix.tv.data.repository.g.i(4, companion);
            LiveColors liveColors = LiveColors.INSTANCE;
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(modifierI, liveColors.m6409getPanel0d7_KjU(), null, 2, null);
            if (aVar != null) {
                composerStartRestartGroup.startReplaceGroup(-1871699709);
                boolean z = (i12 & 112) == 32;
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new l(13, aVar);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                modifierM214clickableXHw0xAI$default = ClickableKt.m214clickableXHw0xAI$default(companion, false, null, null, (r7.a) objRememberedValue, 7, null);
            } else {
                composerStartRestartGroup.startReplaceGroup(-1871698579);
                modifierM214clickableXHw0xAI$default = companion;
            }
            composerStartRestartGroup.endReplaceGroup();
            Modifier modifierH = androidx.fragment.app.a2.h(2, modifierM179backgroundbw27NRU$default.then(modifierM214clickableXHw0xAI$default), Dp.m5678constructorimpl(6), composerStartRestartGroup, 733328855);
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
            String strStringResource = StringResources_androidKt.stringResource(R.string.live_label_sources, new Object[]{Integer.valueOf(i10)}, composerStartRestartGroup, 0);
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(LiveType.INSTANCE.getBadge(), liveColors.m6397getAccent0d7_KjU(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null);
            composer2 = composerStartRestartGroup;
            i13 = 2;
            TextKt.m6020Text4IGK_g(strStringResource, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, textStyleM5205copyp1EtxEg$default, composer2, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.screens.settings.telegram.f(i10, aVar, i11, i13));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceBadge$lambda$0$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceBadge$lambda$2(int i10, r7.a aVar, int i11, Composer composer, int i12) {
        SourceBadge(i10, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0401  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:157:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0199  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void VideoCard(androidx.media3.exoplayer.ExoPlayer r41, com.arflix.tv.ui.screens.tv.live.EnrichedChannel r42, boolean r43, r7.a<x6.t0> r44, androidx.compose.ui.Modifier r45, androidx.compose.runtime.Composer r46, int r47, int r48) {
        /*
            Method dump skipped, instruction units count: 1067
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.MiniPlayerKt.VideoCard(androidx.media3.exoplayer.ExoPlayer, com.arflix.tv.ui.screens.tv.live.EnrichedChannel, boolean, r7.a, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 VideoCard$lambda$0$0(r7.a aVar) {
        if (aVar != null) {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlayerView VideoCard$lambda$1$0$0(ExoPlayer exoPlayer, Context context) {
        PlayerView playerView = new PlayerView(context);
        playerView.setPlayer(exoPlayer);
        playerView.setUseController(false);
        playerView.setKeepContentOnPlayerReset(true);
        return playerView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 VideoCard$lambda$1$1$0(ExoPlayer exoPlayer, PlayerView playerView) {
        if (playerView.getPlayer() != exoPlayer) {
            playerView.setPlayer(exoPlayer);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 VideoCard$lambda$1$2$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 VideoCard$lambda$1$3$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 VideoCard$lambda$2(ExoPlayer exoPlayer, EnrichedChannel enrichedChannel, boolean z, r7.a aVar, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        VideoCard(exoPlayer, enrichedChannel, z, aVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    public static final String formatClock(long j10) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j10);
        return String.format("%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(11)), Integer.valueOf(calendar.get(12))}, 2));
    }

    public static final String formatTimeWindow(IptvProgram iptvProgram) {
        return iptvProgram == null ? "—" : androidx.compose.foundation.c.t(formatClock(iptvProgram.getStartUtcMillis()), " – ", formatClock(iptvProgram.getEndUtcMillis()));
    }

    public static final Float progressOf(IptvProgram iptvProgram) {
        if (iptvProgram == null) {
            return null;
        }
        float endUtcMillis = iptvProgram.getEndUtcMillis() - iptvProgram.getStartUtcMillis();
        if (endUtcMillis <= 0.0f) {
            return null;
        }
        return Float.valueOf(qb.d.m((System.currentTimeMillis() - iptvProgram.getStartUtcMillis()) / endUtcMillis, 0.0f, 1.0f));
    }

    public static final String remainingLabel(IptvProgram iptvProgram) {
        StringBuilder sb2;
        if (iptvProgram == null) {
            return "";
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long startUtcMillis = iptvProgram.getStartUtcMillis();
        if (jCurrentTimeMillis > iptvProgram.getEndUtcMillis() || startUtcMillis > jCurrentTimeMillis) {
            return "";
        }
        long endUtcMillis = (iptvProgram.getEndUtcMillis() - jCurrentTimeMillis) / 60000;
        if (endUtcMillis < 0) {
            endUtcMillis = 0;
        }
        if (endUtcMillis >= 60) {
            long j10 = 60;
            long j11 = endUtcMillis / j10;
            endUtcMillis %= j10;
            sb2 = new StringBuilder();
            sb2.append(j11);
            sb2.append("h ");
        } else {
            sb2 = new StringBuilder();
        }
        sb2.append(endUtcMillis);
        sb2.append("m left");
        return sb2.toString();
    }
}
