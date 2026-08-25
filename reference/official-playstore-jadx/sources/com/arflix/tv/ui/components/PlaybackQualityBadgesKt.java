package com.arflix.tv.ui.components;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.media3.extractor.WavUtil;
import coil.compose.SingletonAsyncImageKt;
import com.arflix.tv.data.model.StreamBehaviorHints;
import com.arflix.tv.data.model.StreamSource;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a#\u0010\t\u001a\u00020\b2\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\n\u001a\u0017\u0010\f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0003H\u0003¢\u0006\u0004\b\f\u0010\r\u001a\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/data/model/StreamSource;", "stream", "", "Lcom/arflix/tv/ui/components/PlaybackBadge;", "buildPlaybackBadges", "(Lcom/arflix/tv/data/model/StreamSource;)Ljava/util/List;", "Landroidx/compose/ui/Modifier;", "modifier", "Lx6/t0;", "PlaybackQualityBadgeRow", "(Lcom/arflix/tv/data/model/StreamSource;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "badge", "PlaybackBadgeItem", "(Lcom/arflix/tv/ui/components/PlaybackBadge;Landroidx/compose/runtime/Composer;I)V", "", "text", "Landroidx/compose/ui/unit/Dp;", "playbackBadgeWidth", "(Ljava/lang/String;)F", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PlaybackQualityBadgesKt {
    private static final void PlaybackBadgeItem(PlaybackBadge playbackBadge, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-549035052);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(playbackBadge) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-549035052, i11, -1, "com.arflix.tv.ui.components.PlaybackBadgeItem (PlaybackQualityBadges.kt:134)");
            }
            if (playbackBadge.getImageUrl() != null) {
                composerStartRestartGroup.startReplaceGroup(-1067675451);
                SingletonAsyncImageKt.m6053AsyncImage3HmZ8SU(playbackBadge.getImageUrl(), playbackBadge.getText(), SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(Modifier.INSTANCE, playbackBadgeWidth(playbackBadge.getText())), Dp.m5678constructorimpl(18)), null, null, null, ContentScale.INSTANCE.getFit(), 0.0f, null, 0, composerStartRestartGroup, 1572864, 952);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-1067400109);
                TextKt.m2153Text4IGK_g(playbackBadge.getText(), (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (r7.l<? super TextLayoutResult, x6.t0>) null, new TextStyle(Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.85f, 0.0f, 0.0f, 0.0f, 14, null), TextUnitKt.getSp(10), FontWeight.INSTANCE.getBold(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, TextUnitKt.getSp(0.5d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777080, (kotlin.jvm.internal.h) null), composerStartRestartGroup, 0, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
                composerStartRestartGroup = composerStartRestartGroup;
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new c3(playbackBadge, i10, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlaybackBadgeItem$lambda$0(PlaybackBadge playbackBadge, int i10, Composer composer, int i11) {
        PlaybackBadgeItem(playbackBadge, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    public static final void PlaybackQualityBadgeRow(final StreamSource streamSource, Modifier modifier, Composer composer, final int i10, final int i11) {
        int i12;
        final StreamSource streamSource2;
        final int i13;
        final int i14;
        Composer composerStartRestartGroup = composer.startRestartGroup(-919984492);
        if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(streamSource) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        int i15 = i11 & 2;
        if (i15 != 0) {
            i12 |= 48;
        } else if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        if ((i12 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            streamSource2 = streamSource;
            i13 = i10;
            i14 = i11;
        } else {
            if (i15 != 0) {
                modifier = Modifier.INSTANCE;
            }
            final Modifier modifier2 = modifier;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-919984492, i12, -1, "com.arflix.tv.ui.components.PlaybackQualityBadgeRow (PlaybackQualityBadges.kt:117)");
            }
            if (streamSource == null) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final int i16 = 0;
                    scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.components.m1
                        @Override // r7.p
                        public final Object invoke(Object obj, Object obj2) {
                            switch (i16) {
                                case 0:
                                    int iIntValue = ((Integer) obj2).intValue();
                                    return PlaybackQualityBadgesKt.PlaybackQualityBadgeRow$lambda$0(streamSource, modifier2, i10, i11, (Composer) obj, iIntValue);
                                case 1:
                                    int iIntValue2 = ((Integer) obj2).intValue();
                                    return PlaybackQualityBadgesKt.PlaybackQualityBadgeRow$lambda$2(streamSource, modifier2, i10, i11, (Composer) obj, iIntValue2);
                                default:
                                    int iIntValue3 = ((Integer) obj2).intValue();
                                    return PlaybackQualityBadgesKt.PlaybackQualityBadgeRow$lambda$4(streamSource, modifier2, i10, i11, (Composer) obj, iIntValue3);
                            }
                        }
                    });
                    return;
                }
                return;
            }
            streamSource2 = streamSource;
            i13 = i10;
            i14 = i11;
            boolean z = (i12 & 14) == 4;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = buildPlaybackBadges(streamSource2);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            List list = (List) objRememberedValue;
            if (list.isEmpty()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup2 != null) {
                    final int i17 = 1;
                    scopeUpdateScopeEndRestartGroup2.updateScope(new r7.p() { // from class: com.arflix.tv.ui.components.m1
                        @Override // r7.p
                        public final Object invoke(Object obj, Object obj2) {
                            switch (i17) {
                                case 0:
                                    int iIntValue = ((Integer) obj2).intValue();
                                    return PlaybackQualityBadgesKt.PlaybackQualityBadgeRow$lambda$0(streamSource2, modifier2, i13, i14, (Composer) obj, iIntValue);
                                case 1:
                                    int iIntValue2 = ((Integer) obj2).intValue();
                                    return PlaybackQualityBadgesKt.PlaybackQualityBadgeRow$lambda$2(streamSource2, modifier2, i13, i14, (Composer) obj, iIntValue2);
                                default:
                                    int iIntValue3 = ((Integer) obj2).intValue();
                                    return PlaybackQualityBadgesKt.PlaybackQualityBadgeRow$lambda$4(streamSource2, modifier2, i13, i14, (Composer) obj, iIntValue3);
                            }
                        }
                    });
                    return;
                }
                return;
            }
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = Arrangement.INSTANCE.m441spacedBy0680j_4(Dp.m5678constructorimpl(6));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM441spacedBy0680j_4, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier2);
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
            composerStartRestartGroup.startReplaceGroup(116446717);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                PlaybackBadgeItem((PlaybackBadge) it.next(), composerStartRestartGroup, 0);
            }
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier = modifier2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup3 = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup3 != null) {
            final StreamSource streamSource3 = streamSource2;
            final int i18 = 2;
            final int i19 = i14;
            final int i20 = i13;
            final Modifier modifier3 = modifier;
            scopeUpdateScopeEndRestartGroup3.updateScope(new r7.p() { // from class: com.arflix.tv.ui.components.m1
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    switch (i18) {
                        case 0:
                            int iIntValue = ((Integer) obj2).intValue();
                            return PlaybackQualityBadgesKt.PlaybackQualityBadgeRow$lambda$0(streamSource3, modifier3, i20, i19, (Composer) obj, iIntValue);
                        case 1:
                            int iIntValue2 = ((Integer) obj2).intValue();
                            return PlaybackQualityBadgesKt.PlaybackQualityBadgeRow$lambda$2(streamSource3, modifier3, i20, i19, (Composer) obj, iIntValue2);
                        default:
                            int iIntValue3 = ((Integer) obj2).intValue();
                            return PlaybackQualityBadgesKt.PlaybackQualityBadgeRow$lambda$4(streamSource3, modifier3, i20, i19, (Composer) obj, iIntValue3);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlaybackQualityBadgeRow$lambda$0(StreamSource streamSource, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        PlaybackQualityBadgeRow(streamSource, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlaybackQualityBadgeRow$lambda$2(StreamSource streamSource, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        PlaybackQualityBadgeRow(streamSource, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlaybackQualityBadgeRow$lambda$4(StreamSource streamSource, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        PlaybackQualityBadgeRow(streamSource, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    public static final List<PlaybackBadge> buildPlaybackBadges(StreamSource streamSource) {
        String quality = streamSource.getQuality();
        String source = streamSource.getSource();
        String description = streamSource.getDescription();
        StreamBehaviorHints behaviorHints = streamSource.getBehaviorHints();
        String strU0 = kotlin.collections.x.u0(kotlin.collections.r.X(new String[]{quality, source, description, behaviorHints != null ? behaviorHints.getFilename() : null}), " ", null, null, null, 62);
        z6.c cVarN = t7.a.n();
        BadgeRegex badgeRegex = BadgeRegex.INSTANCE;
        if (badgeRegex.getRES_4K().a(strU0)) {
            cVarN.add(new PlaybackBadge("4K", BadgeImages.INSTANCE.getUHD_4K()));
        } else if (badgeRegex.getRES_1080().a(strU0)) {
            cVarN.add(new PlaybackBadge("1080p", BadgeImages.INSTANCE.getFULL_HD_1080()));
        } else if (badgeRegex.getRES_720().a(strU0)) {
            cVarN.add(new PlaybackBadge("720p", BadgeImages.INSTANCE.getHD_720()));
        } else if (badgeRegex.getRES_480().a(strU0)) {
            cVarN.add(new PlaybackBadge("480p", null, 2, null));
        }
        if (badgeRegex.getDV().a(strU0)) {
            cVarN.add(new PlaybackBadge("DV", BadgeImages.INSTANCE.getDOLBY_VISION()));
        } else if (badgeRegex.getHDR10_PLUS().a(strU0)) {
            cVarN.add(new PlaybackBadge("HDR10+", BadgeImages.INSTANCE.getHDR10_PLUS()));
        } else if (badgeRegex.getHDR10().a(strU0)) {
            cVarN.add(new PlaybackBadge("HDR10", BadgeImages.INSTANCE.getHDR10()));
        } else if (badgeRegex.getHDR().a(strU0)) {
            cVarN.add(new PlaybackBadge("HDR", BadgeImages.INSTANCE.getHDR()));
        }
        if (badgeRegex.getIMAX().a(strU0)) {
            cVarN.add(new PlaybackBadge("IMAX", BadgeImages.INSTANCE.getIMAX()));
        }
        if (badgeRegex.getATMOS().a(strU0)) {
            cVarN.add(new PlaybackBadge("Atmos", BadgeImages.INSTANCE.getATMOS()));
        } else if (badgeRegex.getTRUEHD().a(strU0)) {
            cVarN.add(new PlaybackBadge("TrueHD", BadgeImages.INSTANCE.getTRUEHD()));
        } else if (badgeRegex.getDTS_X().a(strU0)) {
            cVarN.add(new PlaybackBadge("DTS:X", BadgeImages.INSTANCE.getDTS_X()));
        } else if (badgeRegex.getDTS_HD_MA().a(strU0)) {
            cVarN.add(new PlaybackBadge("DTS-HD MA", BadgeImages.INSTANCE.getDTS_HD_MA()));
        } else if (badgeRegex.getDTS_HD().a(strU0)) {
            cVarN.add(new PlaybackBadge("DTS-HD", BadgeImages.INSTANCE.getDTS_HD()));
        } else if (badgeRegex.getDTS().a(strU0)) {
            cVarN.add(new PlaybackBadge("DTS", BadgeImages.INSTANCE.getDTS()));
        } else if (badgeRegex.getDD_PLUS().a(strU0)) {
            cVarN.add(new PlaybackBadge("DD+", BadgeImages.INSTANCE.getDOLBY_DIGITAL_PLUS()));
        } else if (badgeRegex.getDD().a(strU0)) {
            cVarN.add(new PlaybackBadge("DD", BadgeImages.INSTANCE.getDOLBY_DIGITAL()));
        }
        return t7.a.e(cVarN);
    }

    private static final float playbackBadgeWidth(String str) {
        return kotlin.text.u.L(str, "4K", true) ? Dp.m5678constructorimpl(36) : kotlin.text.u.L(str, "1080p", true) ? Dp.m5678constructorimpl(50) : kotlin.text.u.L(str, "720p", true) ? Dp.m5678constructorimpl(44) : kotlin.text.u.L(str, "DV", true) ? Dp.m5678constructorimpl(68) : kotlin.text.u.L(str, "HDR10+", true) ? Dp.m5678constructorimpl(58) : kotlin.text.u.L(str, "HDR10", true) ? Dp.m5678constructorimpl(52) : kotlin.text.u.L(str, "HDR", true) ? Dp.m5678constructorimpl(42) : kotlin.text.u.L(str, "IMAX", true) ? Dp.m5678constructorimpl(48) : kotlin.text.u.L(str, "Atmos", true) ? Dp.m5678constructorimpl(58) : kotlin.text.u.L(str, "TrueHD", true) ? Dp.m5678constructorimpl(56) : kotlin.text.u.L(str, "DTS:X", true) ? Dp.m5678constructorimpl(52) : kotlin.text.u.L(str, "DTS-HD MA", true) ? Dp.m5678constructorimpl(72) : kotlin.text.u.L(str, "DTS-HD", true) ? Dp.m5678constructorimpl(58) : kotlin.text.u.L(str, "DTS", true) ? Dp.m5678constructorimpl(42) : kotlin.text.u.L(str, "DD+", true) ? Dp.m5678constructorimpl(44) : kotlin.text.u.L(str, "DD", true) ? Dp.m5678constructorimpl(38) : Dp.m5678constructorimpl(46);
    }
}
