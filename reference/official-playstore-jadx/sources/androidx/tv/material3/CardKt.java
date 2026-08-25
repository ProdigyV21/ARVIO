package androidx.tv.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.media3.exoplayer.RendererCapabilities;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0095\u0001\u0010\u0017\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0004\b\u0017\u0010\u0018\u001aÜ\u0001\u0010 \u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u001c\u0010\u001a\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u00152\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\u0013\b\u0002\u0010\u001c\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00142\u0013\b\u0002\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00142\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0007¢\u0006\u0004\b \u0010!\u001aÜ\u0001\u0010$\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u001c\u0010\u001a\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u00152\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\u0013\b\u0002\u0010\u001c\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00142\u0013\b\u0002\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00142\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010#\u001a\u00020\"2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0007¢\u0006\u0004\b$\u0010%\u001aÜ\u0001\u0010&\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u001c\u0010\u001a\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u00152\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00142\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\u0013\b\u0002\u0010\u001c\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00142\u0013\b\u0002\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00142\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0007¢\u0006\u0004\b&\u0010!\u001aL\u0010'\u001a\u00020\u00012\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00142\u0013\b\u0002\u0010\u001c\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00142\u0013\b\u0002\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0014H\u0001¢\u0006\u0004\b'\u0010(¨\u0006)"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "onClick", "Landroidx/compose/ui/Modifier;", "modifier", "onLongClick", "Landroidx/tv/material3/w;", "shape", "Landroidx/tv/material3/n;", "colors", "Landroidx/tv/material3/v;", "scale", "Landroidx/tv/material3/m;", "border", "Landroidx/tv/material3/r;", "glow", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "Card", "(Lr7/a;Landroidx/compose/ui/Modifier;Lr7/a;Landroidx/tv/material3/w;Landroidx/tv/material3/n;Landroidx/tv/material3/v;Landroidx/tv/material3/m;Landroidx/tv/material3/r;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/layout/BoxScope;", "image", LinkHeader.Parameters.Title, MediaTrack.ROLE_SUBTITLE, MediaTrack.ROLE_DESCRIPTION, "Landroidx/compose/foundation/layout/PaddingValues;", "contentPadding", "ClassicCard", "(Lr7/a;Lr7/q;Lr7/p;Landroidx/compose/ui/Modifier;Lr7/a;Lr7/p;Lr7/p;Landroidx/tv/material3/w;Landroidx/tv/material3/n;Landroidx/tv/material3/v;Landroidx/tv/material3/m;Landroidx/tv/material3/r;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/ui/graphics/Brush;", "scrimBrush", "CompactCard", "(Lr7/a;Lr7/q;Lr7/p;Landroidx/compose/ui/Modifier;Lr7/a;Lr7/p;Lr7/p;Landroidx/tv/material3/w;Landroidx/tv/material3/n;Landroidx/tv/material3/v;Landroidx/tv/material3/m;Landroidx/tv/material3/r;Landroidx/compose/ui/graphics/Brush;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "WideClassicCard", "CardContent", "(Lr7/p;Lr7/p;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CardKt {

    /* JADX INFO: renamed from: androidx.tv.material3.CardKt$Card$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/layout/BoxScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements r7.q<BoxScope, Composer, Integer, x6.t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ r7.q f5546i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(r7.q qVar) {
            super(3);
            this.f5546i = qVar;
        }

        @Override // r7.q
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((BoxScope) obj, (Composer) obj2, ((Number) obj3).intValue());
            return x6.t0.f22605a;
        }

        public final void invoke(BoxScope boxScope, Composer composer, int i10) {
            if ((i10 & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1222576783, i10, -1, "androidx.tv.material3.Card.<anonymous> (Card.kt:95)");
            }
            composer.startReplaceableGroup(-483455358);
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(Alignment.INSTANCE, Arrangement.INSTANCE.getTop(), composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            this.f5546i.invoke(ColumnScopeInstance.INSTANCE, composer, 6);
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.tv.material3.CardKt$CardContent$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C06351 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, x6.t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ r7.p f5547i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06351(r7.p pVar) {
            super(2);
            this.f5547i = pVar;
        }

        @Override // r7.p
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return x6.t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1620688215, i10, -1, "androidx.tv.material3.CardContent.<anonymous> (Card.kt:342)");
            }
            if (a0.c.A(composer, 0, this.f5547i)) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.tv.material3.CardKt$CardContent$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, x6.t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ r7.p f5548i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(r7.p pVar) {
            super(2);
            this.f5548i = pVar;
        }

        @Override // r7.p
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return x6.t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-697698866, i10, -1, "androidx.tv.material3.CardContent.<anonymous> (Card.kt:344)");
            }
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(Modifier.INSTANCE, s.f6356l);
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierGraphicsLayer);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            if (a0.c.B(this.f5548i, composer, 0)) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.tv.material3.CardKt$CardContent$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, x6.t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ r7.p f5549i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(r7.p pVar) {
            super(2);
            this.f5549i = pVar;
        }

        @Override // r7.p
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return x6.t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1667989873, i10, -1, "androidx.tv.material3.CardContent.<anonymous> (Card.kt:347)");
            }
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(Modifier.INSTANCE, s.f6357m);
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierGraphicsLayer);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            if (a0.c.B(this.f5549i, composer, 0)) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.tv.material3.CardKt$ClassicCard$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/layout/ColumnScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/layout/ColumnScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C06361 extends kotlin.jvm.internal.r implements r7.q<ColumnScope, Composer, Integer, x6.t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ PaddingValues f5550i;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final /* synthetic */ r7.q f5551l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final /* synthetic */ r7.p f5552m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public final /* synthetic */ r7.p f5553n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public final /* synthetic */ r7.p f5554o;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06361(PaddingValues paddingValues, r7.q qVar, r7.p pVar, r7.p pVar2, r7.p pVar3) {
            super(3);
            this.f5550i = paddingValues;
            this.f5551l = qVar;
            this.f5552m = pVar;
            this.f5553n = pVar2;
            this.f5554o = pVar3;
        }

        @Override // r7.q
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((ColumnScope) obj, (Composer) obj2, ((Number) obj3).intValue());
            return x6.t0.f22605a;
        }

        public final void invoke(ColumnScope columnScope, Composer composer, int i10) {
            if ((i10 & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(858466157, i10, -1, "androidx.tv.material3.ClassicCard.<anonymous> (Card.kt:167)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierPadding = PaddingKt.padding(companion, this.f5550i);
            composer.startReplaceableGroup(-483455358);
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.Vertical top = arrangement.getTop();
            Alignment.Companion companion2 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion2, top, composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierPadding);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Alignment alignment = CardDefaults.f5543b;
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(alignment, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
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
            r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
            this.f5551l.invoke(BoxScopeInstance.INSTANCE, composer, 6);
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            composer.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK2 = androidx.compose.foundation.c.k(companion2, arrangement.getTop(), composer, 0, -1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(companion);
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
            r7.p pVarU3 = a0.c.u(companion3, composerM2991constructorimpl3, measurePolicyK2, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composer, qVarModifierMaterializerOf3, composer, 0, 2058660585);
            CardKt.CardContent(this.f5552m, this.f5553n, this.f5554o, composer, 0, 0);
            if (androidx.compose.foundation.c.A(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.tv.material3.CardKt$CompactCard$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/layout/ColumnScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/layout/ColumnScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C06371 extends kotlin.jvm.internal.r implements r7.q<ColumnScope, Composer, Integer, x6.t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ Brush f5555i;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final /* synthetic */ r7.q f5556l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final /* synthetic */ r7.p f5557m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public final /* synthetic */ r7.p f5558n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public final /* synthetic */ r7.p f5559o;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06371(Brush brush, r7.q qVar, r7.p pVar, r7.p pVar2, r7.p pVar3) {
            super(3);
            this.f5555i = brush;
            this.f5556l = qVar;
            this.f5557m = pVar;
            this.f5558n = pVar2;
            this.f5559o = pVar3;
        }

        @Override // r7.q
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((ColumnScope) obj, (Composer) obj2, ((Number) obj3).intValue());
            return x6.t0.f22605a;
        }

        /* JADX WARN: Removed duplicated region for block: B:48:0x015c  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x0168  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x016c  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x018b  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x01a4  */
        /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void invoke(androidx.compose.foundation.layout.ColumnScope r18, androidx.compose.runtime.Composer r19, int r20) {
            /*
                Method dump skipped, instruction units count: 424
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.CardKt.C06371.invoke(androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):void");
        }
    }

    /* JADX INFO: renamed from: androidx.tv.material3.CardKt$WideClassicCard$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/layout/ColumnScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/layout/ColumnScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C06381 extends kotlin.jvm.internal.r implements r7.q<ColumnScope, Composer, Integer, x6.t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ PaddingValues f5560i;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final /* synthetic */ r7.q f5561l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public final /* synthetic */ r7.p f5562m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public final /* synthetic */ r7.p f5563n;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public final /* synthetic */ r7.p f5564o;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06381(PaddingValues paddingValues, r7.q qVar, r7.p pVar, r7.p pVar2, r7.p pVar3) {
            super(3);
            this.f5560i = paddingValues;
            this.f5561l = qVar;
            this.f5562m = pVar;
            this.f5563n = pVar2;
            this.f5564o = pVar3;
        }

        @Override // r7.q
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((ColumnScope) obj, (Composer) obj2, ((Number) obj3).intValue());
            return x6.t0.f22605a;
        }

        /* JADX WARN: Removed duplicated region for block: B:43:0x0138  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x0144  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x0148  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x0167  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x0180  */
        /* JADX WARN: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void invoke(androidx.compose.foundation.layout.ColumnScope r18, androidx.compose.runtime.Composer r19, int r20) {
            /*
                Method dump skipped, instruction units count: 388
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.CardKt.C06381.invoke(androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):void");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:179:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00f8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Card(r7.a<x6.t0> r41, androidx.compose.ui.Modifier r42, r7.a<x6.t0> r43, androidx.tv.material3.w r44, androidx.tv.material3.n r45, androidx.tv.material3.v r46, androidx.tv.material3.m r47, androidx.tv.material3.r r48, androidx.compose.foundation.interaction.MutableInteractionSource r49, r7.q<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r50, androidx.compose.runtime.Composer r51, int r52, int r53) {
        /*
            Method dump skipped, instruction units count: 738
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.CardKt.Card(r7.a, androidx.compose.ui.Modifier, r7.a, androidx.tv.material3.w, androidx.tv.material3.n, androidx.tv.material3.v, androidx.tv.material3.m, androidx.tv.material3.r, androidx.compose.foundation.interaction.MutableInteractionSource, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void CardContent(r7.p<? super Composer, ? super Integer, x6.t0> pVar, r7.p<? super Composer, ? super Integer, x6.t0> pVar2, r7.p<? super Composer, ? super Integer, x6.t0> pVar3, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(1583572198);
        if ((i11 & 1) != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changedInstance(pVar) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        int i13 = i11 & 2;
        if (i13 != 0) {
            i12 |= 48;
        } else if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar2) ? 32 : 16;
        }
        int i14 = i11 & 4;
        if (i14 != 0) {
            i12 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar3) ? 256 : 128;
        }
        if ((i12 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                pVar2 = k1.f6156g;
            }
            if (i14 != 0) {
                pVar3 = k1.f6157h;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1583572198, i12, -1, "androidx.tv.material3.CardContent (Card.kt:341)");
            }
            MaterialTheme materialTheme = MaterialTheme.f5670a;
            TextKt.ProvideTextStyle(materialTheme.getTypography(composerStartRestartGroup, 6).f6306h, ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1620688215, true, new C06351(pVar)), composerStartRestartGroup, 48);
            TextKt.ProvideTextStyle(materialTheme.getTypography(composerStartRestartGroup, 6).f6309l, ComposableLambdaKt.composableLambda(composerStartRestartGroup, -697698866, true, new AnonymousClass2(pVar2)), composerStartRestartGroup, 48);
            TextKt.ProvideTextStyle(materialTheme.getTypography(composerStartRestartGroup, 6).f6309l, ComposableLambdaKt.composableLambda(composerStartRestartGroup, -1667989873, true, new AnonymousClass3(pVar3)), composerStartRestartGroup, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        r7.p<? super Composer, ? super Integer, x6.t0> pVar4 = pVar2;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar5 = pVar3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new androidx.navigation.compose.z(i10, i11, 1, pVar, pVar4, pVar5));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:231:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ClassicCard(r7.a<x6.t0> r35, r7.q<? super androidx.compose.foundation.layout.BoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r36, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r37, androidx.compose.ui.Modifier r38, r7.a<x6.t0> r39, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r40, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r41, androidx.tv.material3.w r42, androidx.tv.material3.n r43, androidx.tv.material3.v r44, androidx.tv.material3.m r45, androidx.tv.material3.r r46, androidx.compose.foundation.layout.PaddingValues r47, androidx.compose.foundation.interaction.MutableInteractionSource r48, androidx.compose.runtime.Composer r49, int r50, int r51, int r52) {
        /*
            Method dump skipped, instruction units count: 889
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.CardKt.ClassicCard(r7.a, r7.q, r7.p, androidx.compose.ui.Modifier, r7.a, r7.p, r7.p, androidx.tv.material3.w, androidx.tv.material3.n, androidx.tv.material3.v, androidx.tv.material3.m, androidx.tv.material3.r, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:228:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CompactCard(r7.a<x6.t0> r35, r7.q<? super androidx.compose.foundation.layout.BoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r36, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r37, androidx.compose.ui.Modifier r38, r7.a<x6.t0> r39, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r40, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r41, androidx.tv.material3.w r42, androidx.tv.material3.n r43, androidx.tv.material3.v r44, androidx.tv.material3.m r45, androidx.tv.material3.r r46, androidx.compose.ui.graphics.Brush r47, androidx.compose.foundation.interaction.MutableInteractionSource r48, androidx.compose.runtime.Composer r49, int r50, int r51, int r52) {
        /*
            Method dump skipped, instruction units count: 889
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.CardKt.CompactCard(r7.a, r7.q, r7.p, androidx.compose.ui.Modifier, r7.a, r7.p, r7.p, androidx.tv.material3.w, androidx.tv.material3.n, androidx.tv.material3.v, androidx.tv.material3.m, androidx.tv.material3.r, androidx.compose.ui.graphics.Brush, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:231:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void WideClassicCard(r7.a<x6.t0> r35, r7.q<? super androidx.compose.foundation.layout.BoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r36, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r37, androidx.compose.ui.Modifier r38, r7.a<x6.t0> r39, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r40, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r41, androidx.tv.material3.w r42, androidx.tv.material3.n r43, androidx.tv.material3.v r44, androidx.tv.material3.m r45, androidx.tv.material3.r r46, androidx.compose.foundation.layout.PaddingValues r47, androidx.compose.foundation.interaction.MutableInteractionSource r48, androidx.compose.runtime.Composer r49, int r50, int r51, int r52) {
        /*
            Method dump skipped, instruction units count: 889
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.CardKt.WideClassicCard(r7.a, r7.q, r7.p, androidx.compose.ui.Modifier, r7.a, r7.p, r7.p, androidx.tv.material3.w, androidx.tv.material3.n, androidx.tv.material3.v, androidx.tv.material3.m, androidx.tv.material3.r, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
