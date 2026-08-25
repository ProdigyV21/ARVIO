package com.arflix.tv.ui.components;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.tv.material3.TextKt;
import coil.compose.SingletonAsyncImageKt;
import coil.request.ImageRequest;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.ui.skin.ArvioFocusKt;
import com.arflix.tv.ui.skin.ArvioSkin;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a÷\u0001\u0010 \u001a\u00020\u00182\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u00042\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00172\b\b\u0002\u0010\u001d\u001a\u00020\u001cH\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001a=\u0010#\u001a\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u001d\u001a\u00020\u001cH\u0003¢\u0006\u0004\b!\u0010\"\u001a{\u0010'\u001a\u00020\u00182\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u00042\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00172\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010$\u001a\u00020\u0004H\u0007¢\u0006\u0004\b%\u0010&\u001aO\u0010/\u001a\u00020\u00182\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u00022\b\u0010)\u001a\u0004\u0018\u00010\u00062\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020\u00142\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0007¢\u0006\u0004\b-\u0010.\"\u0014\u00101\u001a\u0002008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102¨\u00064²\u0006\u000e\u00103\u001a\u00020\u00048\n@\nX\u008a\u008e\u0002²\u0006\u000e\u00103\u001a\u00020\u00048\n@\nX\u008a\u008e\u0002"}, d2 = {"Lcom/arflix/tv/data/model/MediaItem;", "item", "Landroidx/compose/ui/unit/Dp;", "width", "", "isLandscape", "", "logoImageUrl", "focusImageUrl", "enableFocusedImageSwap", "animateFocus", "showLogoImage", "raiseOnFocus", "showProgress", "showTitle", "showSubtitle", "", "titleMaxLines", "subtitleMaxLines", "isFocusedOverride", "", "focusedScale", "enableSystemFocus", "Lkotlin/Function0;", "Lx6/t0;", "onFocused", "onClick", "onLongClick", "Landroidx/compose/ui/Modifier;", "modifier", "MediaCard-L-7BDBA", "(Lcom/arflix/tv/data/model/MediaItem;FZLjava/lang/String;Ljava/lang/String;ZZZZZZZIIZFZLr7/a;Lr7/a;Lr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;IIII)V", "MediaCard", "PlaceholderCard-PBTpf3Q", "(FZZZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "PlaceholderCard", "useWhiteBorder", "PosterCard-iWtaglI", "(Lcom/arflix/tv/data/model/MediaItem;FZZLr7/a;Lr7/a;Lr7/a;Landroidx/compose/ui/Modifier;ZLandroidx/compose/runtime/Composer;II)V", "PosterCard", "height", "trailerKey", "", "trailerDelayMs", "trailerVolume", "FeaturedMediaCard-ZUYZQmM", "(Lcom/arflix/tv/data/model/MediaItem;FFLjava/lang/String;JFLr7/a;Landroidx/compose/runtime/Composer;I)V", "FeaturedMediaCard", "Landroidx/compose/ui/graphics/Brush;", "missingArtworkBrush", "Landroidx/compose/ui/graphics/Brush;", "isFocused", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MediaCardKt {
    private static final Brush missingArtworkBrush = Brush.Companion.m3429linearGradientmHitzGk$default(Brush.INSTANCE, t7.a.E(Color.m3462boximpl(ColorKt.Color(4280230707L)), Color.m3462boximpl(ColorKt.Color(4279045396L))), 0, 0, 0, 14, (Object) null);

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MediaType.values().length];
            try {
                iArr[MediaType.TV.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MediaType.MOVIE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: FeaturedMediaCard-ZUYZQmM, reason: not valid java name */
    public static final void m6164FeaturedMediaCardZUYZQmM(final MediaItem mediaItem, final float f10, final float f11, final String str, final long j10, final float f12, final r7.a<x6.t0> aVar, Composer composer, final int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-990249389);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(mediaItem) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(f10) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(f11) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changed(str) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changed(j10) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(f12) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((599187 & i11) == 599186 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-990249389, i11, -1, "com.arflix.tv.ui.components.FeaturedMediaCard (MediaCard.kt:710)");
            }
            ArvioSkin arvioSkin = ArvioSkin.INSTANCE;
            Shape shapeM6502rememberArvioCardShapekHDZbjc = ArvioFocusKt.m6502rememberArvioCardShapekHDZbjc(arvioSkin.getRadius(composerStartRestartGroup, 6).m6517getMdD9Ej5fM(), composerStartRestartGroup, 0, 0);
            String backdrop = mediaItem.getBackdrop();
            if (backdrop == null) {
                backdrop = mediaItem.getImage();
            }
            if (kotlin.text.o.h0(backdrop)) {
                backdrop = null;
            }
            final String str2 = backdrop;
            composer2 = composerStartRestartGroup;
            ArvioFocusKt.m6499ArvioFocusableSurfaceaewEPWQ(SizeKt.m580sizeVpY3zN4(Modifier.INSTANCE, f10, f11), shapeM6502rememberArvioCardShapekHDZbjc, ColorKt.Color(4279900698L), 1.0f, 0.97f, Dp.m5678constructorimpl((float) 2.5d), 0.0f, 0.0f, arvioSkin.getColors(composerStartRestartGroup, 6).m6491getFocusOutline0d7_KjU(), 0.0f, false, 0L, 0L, false, false, false, false, false, true, aVar, null, null, ComposableLambdaKt.rememberComposableLambda(-102673895, true, new r7.r() { // from class: com.arflix.tv.ui.components.r0
                @Override // r7.r
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    int iIntValue = ((Integer) obj4).intValue();
                    return MediaCardKt.FeaturedMediaCard_ZUYZQmM$lambda$1(str2, mediaItem, str, j10, f12, (BoxScope) obj, ((Boolean) obj2).booleanValue(), (Composer) obj3, iIntValue);
                }
            }, composerStartRestartGroup, 54), composer2, 224640, ((i11 << 9) & 1879048192) | 102260736, RendererCapabilities.DECODER_SUPPORT_MASK, 3325632);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.components.s0
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return MediaCardKt.FeaturedMediaCard_ZUYZQmM$lambda$2(mediaItem, f10, f11, str, j10, f12, aVar, i10, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FeaturedMediaCard_ZUYZQmM$lambda$1(String str, MediaItem mediaItem, String str2, long j10, float f10, BoxScope boxScope, boolean z, Composer composer, int i10) {
        int i11;
        int i12;
        float f11;
        char c10;
        Composer composer2 = composer;
        if ((i10 & 6) == 0) {
            i11 = i10 | (composer2.changed(boxScope) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i11 & 131) == 130 && composer2.getSkipping()) {
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-102673895, i11, -1, "com.arflix.tv.ui.components.FeaturedMediaCard.<anonymous> (MediaCard.kt:727)");
            }
            if (str != null) {
                composer2.startReplaceGroup(-741234151);
                c10 = 2;
                i12 = 1;
                f11 = 0.0f;
                SingletonAsyncImageKt.m6053AsyncImage3HmZ8SU(str, mediaItem.getTitle(), SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, composer, 1573248, 952);
                composer2 = composer;
                composer2.endReplaceGroup();
            } else {
                i12 = 1;
                f11 = 0.0f;
                c10 = 2;
                composer2.startReplaceGroup(-741024343);
                composer2.endReplaceGroup();
            }
            if (str2 != null) {
                composer2.startReplaceGroup(-740971488);
                TrailerPlayerKt.TrailerPlayer(str2, SizeKt.fillMaxSize$default(Modifier.INSTANCE, f11, i12, null), j10, f10, null, composer2, 48, 16);
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(-740768407);
                composer2.endReplaceGroup();
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(companion, f11, i12, null);
            Brush.Companion companion2 = Brush.INSTANCE;
            Float fValueOf = Float.valueOf(f11);
            Color.Companion companion3 = Color.INSTANCE;
            x6.x xVar = new x6.x(fValueOf, Color.m3462boximpl(companion3.m3507getTransparent0d7_KjU()));
            x6.x xVar2 = new x6.x(Float.valueOf(0.55f), Color.m3462boximpl(companion3.m3507getTransparent0d7_KjU()));
            x6.x xVar3 = new x6.x(Float.valueOf(1.0f), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion3.m3498getBlack0d7_KjU(), 0.85f, 0.0f, 0.0f, 0.0f, 14, null)));
            x6.x[] xVarArr = new x6.x[3];
            xVarArr[0] = xVar;
            xVarArr[i12] = xVar2;
            xVarArr[c10] = xVar3;
            BoxKt.Box(BackgroundKt.background$default(modifierFillMaxSize$default, Brush.Companion.m3436verticalGradient8A3gB4$default(companion2, xVarArr, 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), composer2, 6);
            TextKt.m6020Text4IGK_g(mediaItem.getTitle(), PaddingKt.m530paddingVpY3zN4(boxScope.align(companion, Alignment.INSTANCE.getBottomStart()), Dp.m5678constructorimpl(10), Dp.m5678constructorimpl(8)), companion3.m3509getWhite0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, ArvioSkin.INSTANCE.getTypography(composer2, 6).getCardTitle(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 3120, 55288);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FeaturedMediaCard_ZUYZQmM$lambda$2(MediaItem mediaItem, float f10, float f11, String str, long j10, float f12, r7.a aVar, int i10, Composer composer, int i11) {
        m6164FeaturedMediaCardZUYZQmM(mediaItem, f10, f11, str, j10, f12, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x031e  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0368  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x038d  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x04e9  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x04f1  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x04f4  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x0514  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x052f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x0534 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:373:0x053a  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0543  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x0545  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x054c  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x0592  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x0594  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x05ba  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x05bc  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x05c2  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x0619  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x061c  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x062d  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x063b  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x0649  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0695  */
    /* JADX WARN: Removed duplicated region for block: B:427:0x06a8  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x06dc  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x06e8  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x06ec  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x070b  */
    /* JADX WARN: Removed duplicated region for block: B:443:0x0745  */
    /* JADX WARN: Removed duplicated region for block: B:444:0x0747  */
    /* JADX WARN: Removed duplicated region for block: B:451:0x0757  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:478:0x09de  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:483:0x09ed  */
    /* JADX WARN: Removed duplicated region for block: B:496:0x0ad5  */
    /* JADX WARN: Removed duplicated region for block: B:499:0x0ae6  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0135  */
    /* JADX INFO: renamed from: MediaCard-L-7BDBA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6165MediaCardL7BDBA(final com.arflix.tv.data.model.MediaItem r90, float r91, boolean r92, java.lang.String r93, java.lang.String r94, boolean r95, boolean r96, boolean r97, boolean r98, boolean r99, boolean r100, boolean r101, int r102, int r103, boolean r104, float r105, boolean r106, r7.a<x6.t0> r107, r7.a<x6.t0> r108, r7.a<x6.t0> r109, androidx.compose.ui.Modifier r110, androidx.compose.runtime.Composer r111, final int r112, final int r113, final int r114, final int r115) {
        /*
            Method dump skipped, instruction units count: 2864
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.MediaCardKt.m6165MediaCardL7BDBA(com.arflix.tv.data.model.MediaItem, float, boolean, java.lang.String, java.lang.String, boolean, boolean, boolean, boolean, boolean, boolean, boolean, int, int, boolean, float, boolean, r7.a, r7.a, r7.a, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MediaCard_L_7BDBA$lambda$15$0$0(r7.a aVar, MutableState mutableState, boolean z) {
        MediaCard_L_7BDBA$lambda$5(mutableState, z);
        if (z) {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x03ad  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0353  */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v85 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v30 */
    /* JADX WARN: Type inference failed for: r5v4, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 MediaCard_L_7BDBA$lambda$15$1(coil.request.ImageRequest r84, com.arflix.tv.data.model.MediaItem r85, androidx.compose.ui.graphics.Brush r86, boolean r87, boolean r88, boolean r89, coil.request.ImageRequest r90, boolean r91, boolean r92, boolean r93, androidx.compose.foundation.layout.BoxScope r94, boolean r95, androidx.compose.runtime.Composer r96, int r97) {
        /*
            Method dump skipped, instruction units count: 3076
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.MediaCardKt.MediaCard_L_7BDBA$lambda$15$1(coil.request.ImageRequest, com.arflix.tv.data.model.MediaItem, androidx.compose.ui.graphics.Brush, boolean, boolean, boolean, coil.request.ImageRequest, boolean, boolean, boolean, androidx.compose.foundation.layout.BoxScope, boolean, androidx.compose.runtime.Composer, int):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MediaCard_L_7BDBA$lambda$16(MediaItem mediaItem, float f10, boolean z, String str, String str2, boolean z5, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, int i10, int i11, boolean z16, float f11, boolean z17, r7.a aVar, r7.a aVar2, r7.a aVar3, Modifier modifier, int i12, int i13, int i14, int i15, Composer composer, int i16) {
        m6165MediaCardL7BDBA(mediaItem, f10, z, str, str2, z5, z10, z11, z12, z13, z14, z15, i10, i11, z16, f11, z17, aVar, aVar2, aVar3, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), RecomposeScopeImplKt.updateChangedFlags(i13), RecomposeScopeImplKt.updateChangedFlags(i14), i15);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MediaCard_L_7BDBA$lambda$2(MediaItem mediaItem, float f10, boolean z, String str, String str2, boolean z5, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, int i10, int i11, boolean z16, float f11, boolean z17, r7.a aVar, r7.a aVar2, r7.a aVar3, Modifier modifier, int i12, int i13, int i14, int i15, Composer composer, int i16) {
        m6165MediaCardL7BDBA(mediaItem, f10, z, str, str2, z5, z10, z11, z12, z13, z14, z15, i10, i11, z16, f11, z17, aVar, aVar2, aVar3, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), RecomposeScopeImplKt.updateChangedFlags(i13), RecomposeScopeImplKt.updateChangedFlags(i14), i15);
        return x6.t0.f22605a;
    }

    private static final boolean MediaCard_L_7BDBA$lambda$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void MediaCard_L_7BDBA$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:112:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01bf  */
    /* JADX INFO: renamed from: PlaceholderCard-PBTpf3Q, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6166PlaceholderCardPBTpf3Q(final float r18, final boolean r19, boolean r20, boolean r21, androidx.compose.ui.Modifier r22, androidx.compose.runtime.Composer r23, final int r24, final int r25) {
        /*
            Method dump skipped, instruction units count: 620
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.MediaCardKt.m6166PlaceholderCardPBTpf3Q(float, boolean, boolean, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlaceholderCard_PBTpf3Q$lambda$1(float f10, boolean z, boolean z5, boolean z10, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        m6166PlaceholderCardPBTpf3Q(f10, z, z5, z10, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0112  */
    /* JADX INFO: renamed from: PosterCard-iWtaglI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6167PosterCardiWtaglI(final com.arflix.tv.data.model.MediaItem r73, float r74, boolean r75, boolean r76, r7.a<x6.t0> r77, r7.a<x6.t0> r78, r7.a<x6.t0> r79, androidx.compose.ui.Modifier r80, boolean r81, androidx.compose.runtime.Composer r82, final int r83, final int r84) {
        /*
            Method dump skipped, instruction units count: 1519
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.MediaCardKt.m6167PosterCardiWtaglI(com.arflix.tv.data.model.MediaItem, float, boolean, boolean, r7.a, r7.a, r7.a, androidx.compose.ui.Modifier, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PosterCard_iWtaglI$lambda$2(MediaItem mediaItem, float f10, boolean z, boolean z5, r7.a aVar, r7.a aVar2, r7.a aVar3, Modifier modifier, boolean z10, int i10, int i11, Composer composer, int i12) {
        m6167PosterCardiWtaglI(mediaItem, f10, z, z5, aVar, aVar2, aVar3, modifier, z10, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    private static final boolean PosterCard_iWtaglI$lambda$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void PosterCard_iWtaglI$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PosterCard_iWtaglI$lambda$8$0$0(r7.a aVar, MutableState mutableState, boolean z) {
        PosterCard_iWtaglI$lambda$5(mutableState, z);
        if (z) {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PosterCard_iWtaglI$lambda$8$1(ImageRequest imageRequest, MediaItem mediaItem, BoxScope boxScope, boolean z, Composer composer, int i10) {
        if ((i10 & TsExtractor.TS_STREAM_TYPE_AC3) == 128 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1825850796, i10, -1, "com.arflix.tv.ui.components.PosterCard.<anonymous>.<anonymous> (MediaCard.kt:648)");
            }
            if (imageRequest != null) {
                composer.startReplaceGroup(-1839670483);
                SingletonAsyncImageKt.m6053AsyncImage3HmZ8SU(imageRequest, mediaItem.getTitle(), SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, composer, 1573248, 952);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(-1839432682);
                composer.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PosterCard_iWtaglI$lambda$9(MediaItem mediaItem, float f10, boolean z, boolean z5, r7.a aVar, r7.a aVar2, r7.a aVar3, Modifier modifier, boolean z10, int i10, int i11, Composer composer, int i12) {
        m6167PosterCardiWtaglI(mediaItem, f10, z, z5, aVar, aVar2, aVar3, modifier, z10, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }
}
