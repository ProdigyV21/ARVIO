package com.arflix.tv.ui.components;

import androidx.activity.compose.BackHandlerKt;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material.icons.filled.VolumeUpKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.ui.focus.ArvioDpadFocusKt;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.ui.theme.ColorKt;
import com.google.android.gms.cast.MediaError;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\u001aQ\u0010\f\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0007¢\u0006\u0004\b\f\u0010\r\u001a5\u0010\u0012\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00002\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0003¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0016²\u0006\u000e\u0010\u0015\u001a\u00020\u00148\n@\nX\u008a\u008e\u0002"}, d2 = {"", "isVisible", "", "Lcom/arflix/tv/ui/components/AudioTrack;", "audioTracks", "", "selectedTrackId", "Lkotlin/Function1;", "Lx6/t0;", "onSelect", "Lkotlin/Function0;", "onClose", "AudioTrackSelector", "(ZLjava/util/List;Ljava/lang/String;Lr7/l;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "track", "isSelected", "isFocused", "onClick", "AudioTrackItem", "(Lcom/arflix/tv/ui/components/AudioTrack;ZZLr7/a;Landroidx/compose/runtime/Composer;I)V", "", "focusedIndex", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class AudioTrackSelectorKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void AudioTrackItem(AudioTrack audioTrack, boolean z, boolean z5, r7.a<x6.t0> aVar, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1508447344);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(audioTrack) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(z5) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1508447344, i11, -1, "com.arflix.tv.ui.components.AudioTrackItem (AudioTrackSelector.kt:193)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            float f10 = 8;
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.l(f10, PaddingKt.m531paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), 0.0f, Dp.m5678constructorimpl(4), 1, null), z5 ? ColorKt.getPink() : z ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null) : Color.INSTANCE.m3507getTransparent0d7_KjU()), Dp.m5678constructorimpl((!z || z5) ? 0 : 1), (!z || z5) ? Color.INSTANCE.m3507getTransparent0d7_KjU() : ColorKt.getPink()), Dp.m5678constructorimpl(16), Dp.m5678constructorimpl(12));
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment.Vertical centerVertically = companion2.getCenterVertically();
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.HorizontalOrVertical spaceBetween = arrangement.getSpaceBetween();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            Modifier modifierA = androidx.compose.foundation.layout.e.a(RowScopeInstance.INSTANCE, companion, 1.0f, false, 2, null);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion2, arrangement.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierA);
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
            r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyK, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            String label = audioTrack.getLabel();
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextKt.m6020Text4IGK_g(label, null, z5 ? Color.INSTANCE.m3509getWhite0d7_KjU() : z ? ColorKt.getTextPrimary() : ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getBody(), composerStartRestartGroup, 0, 0, 65530);
            composerStartRestartGroup = composerStartRestartGroup;
            z6.c cVarN = t7.a.n();
            String codec = audioTrack.getCodec();
            if (codec != null) {
                cVarN.add(codec.toUpperCase(Locale.ROOT));
            }
            Integer channels = audioTrack.getChannels();
            if (channels != null) {
                int iIntValue = channels.intValue();
                cVarN.add(iIntValue != 1 ? iIntValue != 2 ? iIntValue != 6 ? iIntValue != 8 ? androidx.fragment.app.a2.i(iIntValue, "ch") : "7.1" : "5.1" : "Stereo" : "Mono");
            }
            if (audioTrack.isDefault()) {
                cVarN.add("Default");
            }
            z6.c cVarE = t7.a.e(cVarN);
            if (cVarE.isEmpty()) {
                composerStartRestartGroup.startReplaceGroup(283541144);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(283290850);
                TextKt.m6020Text4IGK_g(kotlin.collections.x.u0(cVarE, " • ", null, null, null, 62), null, Color.m3471copywmQWz5c$default(z5 ? Color.INSTANCE.m3509getWhite0d7_KjU() : ColorKt.getTextSecondary(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getCaption(), composerStartRestartGroup, 0, 0, 65530);
                composerStartRestartGroup = composerStartRestartGroup;
                composerStartRestartGroup.endReplaceGroup();
            }
            androidx.compose.foundation.c.y(composerStartRestartGroup);
            if (z) {
                composerStartRestartGroup.startReplaceGroup(935317715);
                IconKt.m1610Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.selected, composerStartRestartGroup, 0), SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(20)), z5 ? Color.INSTANCE.m3509getWhite0d7_KjU() : ColorKt.getPink(), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(935568846);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new p(audioTrack, z, z5, aVar, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AudioTrackItem$lambda$1(AudioTrack audioTrack, boolean z, boolean z5, r7.a aVar, int i10, Composer composer, int i11) {
        AudioTrackItem(audioTrack, z, z5, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    public static final void AudioTrackSelector(boolean z, List<AudioTrack> list, String str, r7.l<? super AudioTrack, x6.t0> lVar, r7.a<x6.t0> aVar, Composer composer, int i10) {
        int i11;
        List<AudioTrack> list2;
        String str2;
        r7.l<? super AudioTrack, x6.t0> lVar2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(44630547);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            list2 = list;
            i11 |= composerStartRestartGroup.changed(list2) ? 32 : 16;
        } else {
            list2 = list;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            str2 = str;
            i11 |= composerStartRestartGroup.changed(str2) ? 256 : 128;
        } else {
            str2 = str;
        }
        if ((i10 & 3072) == 0) {
            lVar2 = lVar;
            i11 |= composerStartRestartGroup.changedInstance(lVar2) ? 2048 : 1024;
        } else {
            lVar2 = lVar;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 16384 : 8192;
        }
        if ((i11 & 9363) == 9362 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(44630547, i11, -1, "com.arflix.tv.ui.components.AudioTrackSelector (AudioTrackSelector.kt:73)");
            }
            int i12 = i11 & 14;
            boolean z5 = i12 == 4;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableIntState mutableIntState = (MutableIntState) objRememberedValue;
            boolean z10 = (i11 & 57344) == 16384;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new g(1, aVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            BackHandlerKt.BackHandler(z, (r7.a) objRememberedValue2, composerStartRestartGroup, i12, 0);
            composer2 = composerStartRestartGroup;
            AnimatedVisibilityKt.AnimatedVisibility(z, (Modifier) null, EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null), EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null), (String) null, ComposableLambdaKt.rememberComposableLambda(420596795, true, new j(aVar, mutableIntState, list2, lVar2, str2), composerStartRestartGroup, 54), composer2, 200064 | i12, 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new o(z, list, str, lVar, aVar, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AudioTrackSelector$lambda$3$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AudioTrackSelector$lambda$4(final r7.a aVar, final MutableIntState mutableIntState, final List list, final r7.l lVar, String str, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        ColumnScopeInstance columnScopeInstance;
        Modifier.Companion companion;
        int i11;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(420596795, i10, -1, "com.arflix.tv.ui.components.AudioTrackSelector.<anonymous> (AudioTrackSelector.kt:85)");
        }
        Modifier.Companion companion2 = Modifier.INSTANCE;
        Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxSize$default(companion2, 0.0f, 1, null), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3498getBlack0d7_KjU(), 0.9f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null);
        boolean zChanged = composer.changed(aVar) | composer.changed(mutableIntState) | composer.changed(list) | composer.changed(lVar);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.components.AudioTrackSelectorKt$AudioTrackSelector$2$1$1
                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return m6152invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m6152invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    boolean z = false;
                    if (KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                        long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                        Key.Companion companion3 = Key.INSTANCE;
                        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4182getEscapeEK5gGoQ())) {
                            aVar.invoke();
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4171getDirectionUpEK5gGoQ())) {
                            if (mutableIntState.getIntValue() > 0) {
                                mutableIntState.setIntValue(mutableIntState.getIntValue() - 1);
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4166getDirectionDownEK5gGoQ())) {
                            if (mutableIntState.getIntValue() < list.size() - 1) {
                                mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4179getEnterEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion3.m4165getDirectionCenterEK5gGoQ())) {
                            AudioTrack audioTrack = (AudioTrack) kotlin.collections.x.p0(mutableIntState.getIntValue(), list);
                            if (audioTrack != null) {
                                lVar.invoke(audioTrack);
                            }
                        }
                        z = true;
                    }
                    return Boolean.valueOf(z);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(modifierM179backgroundbw27NRU$default, (r7.l) objRememberedValue);
        Alignment.Companion companion3 = Alignment.INSTANCE;
        Alignment center = companion3.getCenter();
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion4.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierOnKeyEvent);
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
        r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        float f10 = 16;
        Modifier modifierM = com.arflix.tv.data.repository.g.m(24, com.arflix.tv.data.repository.g.l(f10, SizeKt.m583width3ABfNKs(companion2, Dp.m5678constructorimpl(MediaError.DetailedErrorCode.DASH_MANIFEST_UNKNOWN)), androidx.compose.ui.graphics.ColorKt.Color(4279900698L)), composer, -483455358);
        Arrangement arrangement = Arrangement.INSTANCE;
        MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion3, arrangement.getTop(), composer, 0, -1323940314);
        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM);
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
        r7.p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyK, composerM2991constructorimpl2, currentCompositionLocalMap2);
        if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
            a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
        }
        a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
        ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
        Alignment.Vertical centerVertically = companion3.getCenterVertically();
        Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(companion2, 0.0f, 0.0f, 0.0f, Dp.m5678constructorimpl(20), 7, null);
        composer.startReplaceableGroup(693286680);
        MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(arrangement, centerVertically, composer, 48, -1323940314);
        int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor3 = companion4.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default);
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor3);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composer);
        r7.p pVarU3 = a0.c.u(companion4, composerM2991constructorimpl3, measurePolicyG, composerM2991constructorimpl3, currentCompositionLocalMap3);
        if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
            a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
        }
        a0.c.y(composer, qVarModifierMaterializerOf3, composer, 0, 2058660585);
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        IconKt.m1610Iconww6aTOc(VolumeUpKt.getVolumeUp(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion2, Dp.m5678constructorimpl(28)), ColorKt.getPink(), composer, 3504, 0);
        androidx.compose.material3.d.w(12, companion2, composer, 6);
        String strStringResource = StringResources_androidKt.stringResource(R.string.audio_track, composer, 0);
        ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
        TextKt.m6020Text4IGK_g(strStringResource, null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getSectionTitle(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
        Composer composer2 = composer;
        composer2.endReplaceableGroup();
        composer2.endNode();
        composer2.endReplaceableGroup();
        composer2.endReplaceableGroup();
        if (list.isEmpty()) {
            composer2.startReplaceGroup(703045835);
            Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), 0.0f, Dp.m5678constructorimpl(32), 1, null);
            Alignment center2 = companion3.getCenter();
            composer2.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center2, false, composer2, 6);
            composer2.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composer2.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor4 = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
            if (composer2.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor4);
            } else {
                composer2.useNode();
            }
            Composer composerM2991constructorimpl4 = Updater.m2991constructorimpl(composer2);
            r7.p pVarU4 = a0.c.u(companion4, composerM2991constructorimpl4, measurePolicyRememberBoxMeasurePolicy2, composerM2991constructorimpl4, currentCompositionLocalMap4);
            if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
            }
            a0.c.y(composer2, qVarModifierMaterializerOf4, composer2, 0, 2058660585);
            columnScopeInstance = columnScopeInstance2;
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.no_audio_tracks, composer2, 0), null, ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getBody(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            composer2 = composer;
            com.arflix.tv.data.repository.g.q(composer2);
            companion = companion2;
            i11 = 0;
        } else {
            columnScopeInstance = columnScopeInstance2;
            composer2.startReplaceGroup(703588335);
            int size = list.size() * 60;
            if (size > 300) {
                size = 300;
            }
            companion = companion2;
            i11 = 0;
            Modifier modifierArvioDpadFocusGroup$default = ArvioDpadFocusKt.arvioDpadFocusGroup$default(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(size)), null, false, 3, null);
            boolean zChanged2 = composer2.changed(list) | composer2.changed(str) | composer2.changed(mutableIntState) | composer2.changed(lVar);
            Object objRememberedValue2 = composer2.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                com.arflix.tv.data.repository.b0 b0Var = new com.arflix.tv.data.repository.b0(list, (Object) str, lVar, (Object) mutableIntState, 1);
                composer2.updateRememberedValue(b0Var);
                objRememberedValue2 = b0Var;
            }
            LazyDslKt.LazyColumn(modifierArvioDpadFocusGroup$default, null, null, false, null, null, null, false, (r7.l) objRememberedValue2, composer2, 0, 254);
            composer2.endReplaceGroup();
        }
        com.arflix.tv.data.repository.g.p(f10, companion, composer2, 6);
        TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.press_back_to_close, composer2, i11), columnScopeInstance.align(companion, companion3.getCenterHorizontally()), Color.m3471copywmQWz5c$default(ColorKt.getTextSecondary(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getCaption(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65528);
        if (androidx.compose.foundation.c.A(composer)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AudioTrackSelector$lambda$4$1$0$2$0(List list, String str, r7.l lVar, MutableIntState mutableIntState, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), null, new AudioTrackSelectorKt$AudioTrackSelector$lambda$4$1$0$2$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new AudioTrackSelectorKt$AudioTrackSelector$lambda$4$1$0$2$0$$inlined$itemsIndexed$default$3(list, str, lVar, mutableIntState)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AudioTrackSelector$lambda$5(boolean z, List list, String str, r7.l lVar, r7.a aVar, int i10, Composer composer, int i11) {
        AudioTrackSelector(z, list, str, lVar, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }
}
