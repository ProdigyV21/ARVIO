package com.arflix.tv.ui.components;

import android.content.Context;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.MenuKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.unit.Dp;
import androidx.lifecycle.compose.FlowExtKt;
import com.arflix.tv.util.DataStoresKt;
import java.util.LinkedHashMap;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001d\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0015\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001d\u0010\b\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\n\u0010\u0007\u001a\u001f\u0010\f\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000¢\u0006\u0004\b\f\u0010\t\u001a#\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00000\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u0015\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0000¢\u0006\u0004\b\u000f\u0010\u0007\u001a\u0017\u0010\u0011\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0011\u0010\u0007\u001a\u0017\u0010\u0013\u001a\u00020\u00122\b\u0010\u0010\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u0015\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0012¢\u0006\u0004\b\u0016\u0010\u0017\u001a \u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0005\u001a\u00020\u0000H\u0086@¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u000f\u0010\u001d\u001a\u00020\u0012H\u0007¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u0017\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u001f\u0010 \u001a5\u0010&\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u00002\b\b\u0002\u0010\"\u001a\u00020!2\b\b\u0002\u0010$\u001a\u00020#2\b\b\u0002\u0010%\u001a\u00020#H\u0007¢\u0006\u0004\b&\u0010'\"\u0014\u0010(\u001a\u00020\u00008\u0006X\u0086T¢\u0006\u0006\n\u0004\b(\u0010)\"\u0014\u0010*\u001a\u00020\u00008\u0006X\u0086T¢\u0006\u0006\n\u0004\b*\u0010)\"\u001a\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00000\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,\"\u001a\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00000\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010,\"\u0014\u0010.\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b.\u0010)\"\u0014\u00100\u001a\u00020/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101¨\u00062²\u0006\f\u0010\u0015\u001a\u00020\u00128\nX\u008a\u0084\u0002²\u0006\f\u0010\u0015\u001a\u00020\u00128\nX\u008a\u0084\u0002"}, d2 = {"", "profileId", "Lc1/e;", "profileCardLayoutModeKey", "(Ljava/lang/String;)Lc1/e;", "rowKey", "catalogueRowLayoutPreferenceName", "(Ljava/lang/String;)Ljava/lang/String;", "profileCatalogueRowLayoutPreferenceName", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "catalogueRowLayoutPreferencePrefixFor", "preferenceName", "catalogueRowLayoutKeyFromPreferenceName", "profileCatalogueRowLayoutModeKey", "(Ljava/lang/String;Ljava/lang/String;)Lc1/e;", "normalizeCatalogueRowLayoutKey", "raw", "normalizeCardLayoutMode", "Lcom/arflix/tv/ui/components/CardLayoutMode;", "parseCardLayoutMode", "(Ljava/lang/String;)Lcom/arflix/tv/ui/components/CardLayoutMode;", "mode", "toggledCardLayoutMode", "(Lcom/arflix/tv/ui/components/CardLayoutMode;)Ljava/lang/String;", "Landroid/content/Context;", "context", "Lx6/t0;", "toggleCatalogueRowLayoutMode", "(Landroid/content/Context;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "rememberCardLayoutMode", "(Landroidx/compose/runtime/Composer;I)Lcom/arflix/tv/ui/components/CardLayoutMode;", "rememberCatalogueRowLayoutMode", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Lcom/arflix/tv/ui/components/CardLayoutMode;", "Landroidx/compose/ui/Modifier;", "modifier", "", "enabled", "forceFocused", "CatalogueRowLayoutToggleButton", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/runtime/Composer;II)V", "CARD_LAYOUT_MODE_LANDSCAPE", "Ljava/lang/String;", "CARD_LAYOUT_MODE_POSTER", "cardLayoutModeKey", "Lc1/e;", "activeProfileIdKey", "CATALOGUE_ROW_LAYOUT_PREFIX", "Lkotlin/text/m;", "ALPHANUMERIC_REGEX", "Lkotlin/text/m;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class CardLayoutModeKt {
    public static final String CARD_LAYOUT_MODE_LANDSCAPE = "Landscape";
    public static final String CARD_LAYOUT_MODE_POSTER = "Poster";
    private static final String CATALOGUE_ROW_LAYOUT_PREFIX = "catalogue_row_layout_";
    private static final c1.e cardLayoutModeKey = new c1.e("card_layout_mode");
    private static final c1.e activeProfileIdKey = new c1.e("active_profile_id");
    private static final kotlin.text.m ALPHANUMERIC_REGEX = new kotlin.text.m("[^a-z0-9_.:-]+");

    /* JADX INFO: renamed from: com.arflix.tv.ui.components.CardLayoutModeKt$toggleCatalogueRowLayoutMode$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.components.CardLayoutModeKt", f = "CardLayoutMode.kt", l = {MenuKt.InTransitionDuration, 122}, m = "toggleCatalogueRowLayoutMode", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CardLayoutModeKt.toggleCatalogueRowLayoutMode(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.components.CardLayoutModeKt$toggleCatalogueRowLayoutMode$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.components.CardLayoutModeKt$toggleCatalogueRowLayoutMode$2", f = "CardLayoutMode.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ c1.e $key;
        final /* synthetic */ String $profileId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, c1.e eVar, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$profileId = str;
            this.$key = eVar;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$profileId, this.$key, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
            return ((AnonymousClass2) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            c1.e eVarProfileCardLayoutModeKey = CardLayoutModeKt.profileCardLayoutModeKey(this.$profileId);
            LinkedHashMap linkedHashMap = bVar.f7368a;
            String str = (String) linkedHashMap.get(eVarProfileCardLayoutModeKey);
            if (str == null) {
                str = (String) linkedHashMap.get(CardLayoutModeKt.cardLayoutModeKey);
            }
            String strNormalizeCardLayoutMode = CardLayoutModeKt.normalizeCardLayoutMode(str);
            String str2 = (String) linkedHashMap.get(this.$key);
            if (str2 != null) {
                strNormalizeCardLayoutMode = str2;
            }
            bVar.d(this.$key, CardLayoutModeKt.toggledCardLayoutMode(CardLayoutModeKt.parseCardLayoutMode(strNormalizeCardLayoutMode)));
            return x6.t0.f22605a;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:94:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CatalogueRowLayoutToggleButton(java.lang.String r38, androidx.compose.ui.Modifier r39, boolean r40, boolean r41, androidx.compose.runtime.Composer r42, int r43, int r44) {
        /*
            Method dump skipped, instruction units count: 464
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.CardLayoutModeKt.CatalogueRowLayoutToggleButton(java.lang.String, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CatalogueRowLayoutToggleButton$lambda$1$0(boolean z, ka.k0 k0Var, Context context, String str) {
        x6.t0 t0Var = x6.t0.f22605a;
        if (!z) {
            return t0Var;
        }
        ka.m0.p(k0Var, null, 0, new CardLayoutModeKt$CatalogueRowLayoutToggleButton$1$1$1(context, str, null), 3);
        return t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CatalogueRowLayoutToggleButton$lambda$2(boolean z, boolean z5, CardLayoutMode cardLayoutMode, BoxScope boxScope, boolean z10, Composer composer, int i10) {
        int i11;
        long jM3509getWhite0d7_KjU;
        int i12;
        Object obj;
        float f10;
        if ((i10 & 48) == 0) {
            i11 = i10 | (composer.changed(z10) ? 32 : 16);
        } else {
            i11 = i10;
        }
        if ((i11 & 145) == 144 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1502794029, i11, -1, "com.arflix.tv.ui.components.CatalogueRowLayoutToggleButton.<anonymous> (CardLayoutMode.kt:202)");
            }
            boolean z11 = z10 || z;
            long jM3471copywmQWz5c$default = !z5 ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3498getBlack0d7_KjU(), 0.4f, 0.0f, 0.0f, 0.0f, 14, null) : z11 ? Color.INSTANCE.m3509getWhite0d7_KjU() : Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.08f, 0.0f, 0.0f, 0.0f, 14, null);
            final long jM3471copywmQWz5c$default2 = !z5 ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null) : z11 ? Color.INSTANCE.m3498getBlack0d7_KjU() : Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null);
            Modifier.Companion companion = Modifier.INSTANCE;
            float f11 = 8;
            Modifier modifierL = com.arflix.tv.data.repository.g.l(f11, SizeKt.fillMaxSize$default(companion, 0.0f, 1, null), jM3471copywmQWz5c$default);
            float fM5678constructorimpl = z11 ? Dp.m5678constructorimpl((float) 1.5d) : Dp.m5678constructorimpl(1);
            if (z11) {
                jM3509getWhite0d7_KjU = Color.INSTANCE.m3509getWhite0d7_KjU();
                i12 = 14;
                obj = null;
                f10 = 0.5f;
            } else {
                jM3509getWhite0d7_KjU = Color.INSTANCE.m3509getWhite0d7_KjU();
                i12 = 14;
                obj = null;
                f10 = 0.15f;
            }
            Modifier modifierK = com.arflix.tv.data.repository.g.k(f11, modifierL, fM5678constructorimpl, Color.m3471copywmQWz5c$default(jM3509getWhite0d7_KjU, f10, 0.0f, 0.0f, 0.0f, i12, obj));
            Alignment center = Alignment.INSTANCE.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierK);
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
            final boolean z12 = cardLayoutMode == CardLayoutMode.POSTER;
            Modifier modifierM578size3ABfNKs = SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(16));
            boolean zChanged = composer.changed(z12) | composer.changed(jM3471copywmQWz5c$default2);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new r7.l() { // from class: com.arflix.tv.ui.components.u
                    @Override // r7.l
                    public final Object invoke(Object obj2) {
                        return CardLayoutModeKt.CatalogueRowLayoutToggleButton$lambda$2$0$0$0(z12, jM3471copywmQWz5c$default2, (DrawScope) obj2);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            CanvasKt.Canvas(modifierM578size3ABfNKs, (r7.l) objRememberedValue, composer, 6);
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CatalogueRowLayoutToggleButton$lambda$2$0$0$0(boolean z, long j10, DrawScope drawScope) {
        Path Path = AndroidPath_androidKt.Path();
        float fM3305getWidthimpl = Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc());
        float fM3302getHeightimpl = Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc());
        float f10 = z ? fM3305getWidthimpl * 0.6f : fM3305getWidthimpl;
        float f11 = z ? fM3302getHeightimpl : 0.6f * fM3302getHeightimpl;
        float f12 = (fM3305getWidthimpl - f10) / 2.0f;
        float f13 = (fM3302getHeightimpl - f11) / 2.0f;
        float f14 = f12 + f10;
        float f15 = f13 + f11;
        Path.moveTo(f12, f13);
        if (z) {
            float f16 = f10 * 0.2f;
            Path.lineTo(f14, f13);
            float f17 = (f11 / 2.0f) + f13;
            Path.quadraticBezierTo(f14 - f16, f17, f14, f15);
            Path.lineTo(f12, f15);
            Path.quadraticBezierTo(f16 + f12, f17, f12, f13);
        } else {
            float f18 = f11 * 0.2f;
            float f19 = (f10 / 2.0f) + f12;
            Path.quadraticBezierTo(f19, f13 + f18, f14, f13);
            Path.lineTo(f14, f15);
            Path.quadraticBezierTo(f19, f15 - f18, f12, f15);
            Path.lineTo(f12, f13);
        }
        Path.close();
        androidx.compose.ui.graphics.drawscope.c.G(drawScope, Path, j10, 0.0f, new Stroke(drawScope.mo285toPx0680j_4(Dp.m5678constructorimpl((float) 1.5d)), 0.0f, StrokeCap.INSTANCE.m3804getRoundKaPHkGw(), StrokeJoin.INSTANCE.m3815getRoundLxFBmk8(), null, 18, null), null, 0, 52, null);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CatalogueRowLayoutToggleButton$lambda$3(String str, Modifier modifier, boolean z, boolean z5, int i10, int i11, Composer composer, int i12) {
        CatalogueRowLayoutToggleButton(str, modifier, z, z5, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    public static final String catalogueRowLayoutKeyFromPreferenceName(String str, String str2) {
        String strR0 = kotlin.text.o.r0(str2, catalogueRowLayoutPreferencePrefixFor(str));
        if (strR0.equals(str2) || kotlin.text.o.h0(strR0)) {
            return null;
        }
        return strR0;
    }

    public static final String catalogueRowLayoutPreferenceName(String str) {
        return androidx.compose.material3.d.C(CATALOGUE_ROW_LAYOUT_PREFIX, normalizeCatalogueRowLayoutKey(str));
    }

    public static final String catalogueRowLayoutPreferencePrefixFor(String str) {
        return a0.c.l("profile_", str, "_catalogue_row_layout_");
    }

    public static final String normalizeCardLayoutMode(String str) {
        String string;
        return (str == null || (string = kotlin.text.o.L0(str).toString()) == null || !string.equalsIgnoreCase(CARD_LAYOUT_MODE_POSTER)) ? CARD_LAYOUT_MODE_LANDSCAPE : CARD_LAYOUT_MODE_POSTER;
    }

    public static final String normalizeCatalogueRowLayoutKey(String str) {
        String strM0 = kotlin.text.o.M0(ALPHANUMERIC_REGEX.f("_", kotlin.text.o.L0(str).toString().toLowerCase(Locale.ROOT)), '_');
        return kotlin.text.o.h0(strM0) ? "default" : strM0;
    }

    public static final CardLayoutMode parseCardLayoutMode(String str) {
        return kotlin.jvm.internal.p.a(normalizeCardLayoutMode(str), CARD_LAYOUT_MODE_POSTER) ? CardLayoutMode.POSTER : CardLayoutMode.LANDSCAPE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final c1.e profileCardLayoutModeKey(String str) {
        return new c1.e(a0.c.l("profile_", str, "_card_layout_mode"));
    }

    public static final c1.e profileCatalogueRowLayoutModeKey(String str, String str2) {
        return new c1.e(profileCatalogueRowLayoutPreferenceName(str, str2));
    }

    public static final String profileCatalogueRowLayoutPreferenceName(String str, String str2) {
        return androidx.compose.material3.d.n("profile_", str, "_", catalogueRowLayoutPreferenceName(str2));
    }

    public static final CardLayoutMode rememberCardLayoutMode(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(647532115, i10, -1, "com.arflix.tv.ui.components.rememberCardLayoutMode (CardLayoutMode.kt:136)");
        }
        Context context = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
        boolean zChanged = composer.changed(context);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = na.y0.h(new na.n0(DataStoresKt.getProfilesDataStore(context).getData(), DataStoresKt.getSettingsDataStore(context).getData(), new CardLayoutModeKt$rememberCardLayoutMode$modeFlow$1$1(null)));
            composer.updateRememberedValue(objRememberedValue);
        }
        CardLayoutMode cardLayoutModeRememberCardLayoutMode$lambda$1 = rememberCardLayoutMode$lambda$1(FlowExtKt.collectAsStateWithLifecycle((na.j<? extends CardLayoutMode>) objRememberedValue, CardLayoutMode.LANDSCAPE, (androidx.lifecycle.y) null, (androidx.lifecycle.q) null, (d7.j) null, composer, 48, 14));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return cardLayoutModeRememberCardLayoutMode$lambda$1;
    }

    private static final CardLayoutMode rememberCardLayoutMode$lambda$1(State<? extends CardLayoutMode> state) {
        return state.getValue();
    }

    public static final CardLayoutMode rememberCatalogueRowLayoutMode(String str, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1649494525, i10, -1, "com.arflix.tv.ui.components.rememberCatalogueRowLayoutMode (CardLayoutMode.kt:152)");
        }
        Context context = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
        boolean z = (((i10 & 14) ^ 6) > 4 && composer.changed(str)) || (i10 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = normalizeCatalogueRowLayoutKey(str);
            composer.updateRememberedValue(objRememberedValue);
        }
        String str2 = (String) objRememberedValue;
        boolean zChanged = composer.changed(context) | composer.changed(str2);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = na.y0.h(new na.n0(DataStoresKt.getProfilesDataStore(context).getData(), DataStoresKt.getSettingsDataStore(context).getData(), new CardLayoutModeKt$rememberCatalogueRowLayoutMode$rowModeFlow$1$1(str2, null)));
            composer.updateRememberedValue(objRememberedValue2);
        }
        CardLayoutMode cardLayoutModeRememberCatalogueRowLayoutMode$lambda$2 = rememberCatalogueRowLayoutMode$lambda$2(FlowExtKt.collectAsStateWithLifecycle((na.j<? extends CardLayoutMode>) objRememberedValue2, CardLayoutMode.LANDSCAPE, (androidx.lifecycle.y) null, (androidx.lifecycle.q) null, (d7.j) null, composer, 48, 14));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return cardLayoutModeRememberCatalogueRowLayoutMode$lambda$2;
    }

    private static final CardLayoutMode rememberCatalogueRowLayoutMode$lambda$2(State<? extends CardLayoutMode> state) {
        return state.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object toggleCatalogueRowLayoutMode(android.content.Context r7, java.lang.String r8, d7.d<? super x6.t0> r9) {
        /*
            boolean r0 = r9 instanceof com.arflix.tv.ui.components.CardLayoutModeKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r9
            com.arflix.tv.ui.components.CardLayoutModeKt$toggleCatalogueRowLayoutMode$1 r0 = (com.arflix.tv.ui.components.CardLayoutModeKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.ui.components.CardLayoutModeKt$toggleCatalogueRowLayoutMode$1 r0 = new com.arflix.tv.ui.components.CardLayoutModeKt$toggleCatalogueRowLayoutMode$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            r4 = 0
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L63
            if (r1 == r3) goto L4b
            if (r1 != r2) goto L43
            java.lang.Object r7 = r0.L$5
            c1.e r7 = (c1.e) r7
            java.lang.Object r7 = r0.L$4
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r0.L$3
            com.arflix.tv.di.RepositoryAccessEntryPoint r8 = (com.arflix.tv.di.RepositoryAccessEntryPoint) r8
            java.lang.Object r1 = r0.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r0.L$1
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r0 = r0.L$0
            android.content.Context r0 = (android.content.Context) r0
            k2.c.G(r9)
            goto Lad
        L43:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L4b:
            java.lang.Object r7 = r0.L$3
            com.arflix.tv.di.RepositoryAccessEntryPoint r7 = (com.arflix.tv.di.RepositoryAccessEntryPoint) r7
            java.lang.Object r8 = r0.L$2
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r1 = r0.L$1
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r0.L$0
            android.content.Context r1 = (android.content.Context) r1
            k2.c.G(r9)
            r6 = r9
            r9 = r7
            r7 = r1
            r1 = r6
            goto L87
        L63:
            k2.c.G(r9)
            java.lang.String r8 = normalizeCatalogueRowLayoutKey(r8)
            java.lang.Class<com.arflix.tv.di.RepositoryAccessEntryPoint> r9 = com.arflix.tv.di.RepositoryAccessEntryPoint.class
            java.lang.Object r9 = dagger.hilt.android.EntryPointAccessors.fromApplication(r7, r9)
            com.arflix.tv.di.RepositoryAccessEntryPoint r9 = (com.arflix.tv.di.RepositoryAccessEntryPoint) r9
            com.arflix.tv.data.repository.ProfileManager r1 = r9.profileManager()
            r0.L$0 = r7
            r0.L$1 = r4
            r0.L$2 = r8
            r0.L$3 = r9
            r0.label = r3
            java.lang.Object r1 = r1.getProfileId(r0)
            if (r1 != r5) goto L87
            goto Laa
        L87:
            java.lang.String r1 = (java.lang.String) r1
            c1.e r8 = profileCatalogueRowLayoutModeKey(r1, r8)
            z0.g r7 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r7)
            com.arflix.tv.ui.components.CardLayoutModeKt$toggleCatalogueRowLayoutMode$2 r3 = new com.arflix.tv.ui.components.CardLayoutModeKt$toggleCatalogueRowLayoutMode$2
            r3.<init>(r1, r8, r4)
            r0.L$0 = r4
            r0.L$1 = r4
            r0.L$2 = r4
            r0.L$3 = r9
            r0.L$4 = r1
            r0.L$5 = r4
            r0.label = r2
            java.lang.Object r7 = androidx.work.impl.t.o(r7, r3, r0)
            if (r7 != r5) goto Lab
        Laa:
            return r5
        Lab:
            r8 = r9
            r7 = r1
        Lad:
            com.arflix.tv.data.repository.CloudSyncInvalidationBus r8 = r8.cloudSyncInvalidationBus()
            com.arflix.tv.data.repository.CloudSyncScope r9 = com.arflix.tv.data.repository.CloudSyncScope.PROFILE_SETTINGS
            java.lang.String r0 = "catalogue row layout"
            r8.markDirty(r9, r7, r0)
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.CardLayoutModeKt.toggleCatalogueRowLayoutMode(android.content.Context, java.lang.String, d7.d):java.lang.Object");
    }

    public static final String toggledCardLayoutMode(CardLayoutMode cardLayoutMode) {
        return cardLayoutMode == CardLayoutMode.POSTER ? CARD_LAYOUT_MODE_LANDSCAPE : CARD_LAYOUT_MODE_POSTER;
    }
}
