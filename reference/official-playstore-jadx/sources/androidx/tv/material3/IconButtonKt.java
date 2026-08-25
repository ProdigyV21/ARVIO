package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u009f\u0001\u0010\u0019\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u001c\u0010\u0018\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u009f\u0001\u0010\u001b\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u001c\u0010\u0018\u001a\u0018\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u0014¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0004\b\u001b\u0010\u001a¨\u0006\u001c"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "onClick", "Landroidx/compose/ui/Modifier;", "modifier", "onLongClick", "", "enabled", "Landroidx/tv/material3/j;", "scale", "Landroidx/tv/material3/g;", "glow", "Landroidx/tv/material3/k;", "shape", "Landroidx/tv/material3/f;", "colors", "Landroidx/tv/material3/e;", "border", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "IconButton", "(Lr7/a;Landroidx/compose/ui/Modifier;Lr7/a;ZLandroidx/tv/material3/j;Landroidx/tv/material3/g;Landroidx/tv/material3/k;Landroidx/tv/material3/f;Landroidx/tv/material3/e;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/q;Landroidx/compose/runtime/Composer;III)V", "OutlinedIconButton", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class IconButtonKt {

    /* JADX INFO: renamed from: androidx.tv.material3.IconButtonKt$IconButton$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/layout/BoxScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends kotlin.jvm.internal.r implements r7.q<BoxScope, Composer, Integer, x6.t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ r7.q f5633i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(r7.q qVar) {
            super(3);
            this.f5633i = qVar;
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
                ComposerKt.traceEventStart(-687199219, i10, -1, "androidx.tv.material3.IconButton.<anonymous> (IconButton.kt:89)");
            }
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            this.f5633i.invoke(BoxScopeInstance.INSTANCE, composer, 6);
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.tv.material3.IconButtonKt$OutlinedIconButton$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/layout/BoxScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C06392 extends kotlin.jvm.internal.r implements r7.q<BoxScope, Composer, Integer, x6.t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ r7.q f5634i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C06392(r7.q qVar) {
            super(3);
            this.f5634i = qVar;
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
                ComposerKt.traceEventStart(-45144885, i10, -1, "androidx.tv.material3.OutlinedIconButton.<anonymous> (IconButton.kt:157)");
            }
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            this.f5634i.invoke(BoxScopeInstance.INSTANCE, composer, 6);
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    public static final void IconButton(r7.a<x6.t0> aVar, Modifier modifier, r7.a<x6.t0> aVar2, boolean z, j jVar, g gVar, k kVar, f fVar, e eVar, MutableInteractionSource mutableInteractionSource, r7.q<? super BoxScope, ? super Composer, ? super Integer, x6.t0> qVar, Composer composer, int i10, int i11, int i12) {
        j jVar2;
        g gVar2;
        k kVar2;
        Modifier modifier2;
        g gVar3;
        f fVarM5989colorsoq7We08;
        Composer composer2;
        int i13;
        int i14;
        e eVarBorder;
        composer.startReplaceableGroup(-1483826292);
        Modifier modifier3 = (i12 & 2) != 0 ? Modifier.INSTANCE : modifier;
        r7.a<x6.t0> aVar3 = (i12 & 4) != 0 ? null : aVar2;
        boolean z5 = (i12 & 8) != 0 ? true : z;
        if ((i12 & 16) != 0) {
            IconButtonDefaults iconButtonDefaults = IconButtonDefaults.f5629a;
            jVar2 = new j(1.1f);
        } else {
            jVar2 = jVar;
        }
        if ((i12 & 32) != 0) {
            IconButtonDefaults iconButtonDefaults2 = IconButtonDefaults.f5629a;
            s1 s1Var = s1.f6376c;
            gVar2 = new g(s1Var, s1Var, s1Var);
        } else {
            gVar2 = gVar;
        }
        if ((i12 & 64) != 0) {
            RoundedCornerShape roundedCornerShape = IconButtonDefaults.f5630b;
            kVar2 = new k(roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape);
        } else {
            kVar2 = kVar;
        }
        if ((i12 & 128) != 0) {
            gVar3 = gVar2;
            modifier2 = modifier3;
            fVarM5989colorsoq7We08 = IconButtonDefaults.f5629a.m5989colorsoq7We08(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 100663296, 255);
        } else {
            modifier2 = modifier3;
            gVar3 = gVar2;
            fVarM5989colorsoq7We08 = fVar;
        }
        if ((i12 & 256) != 0) {
            composer2 = composer;
            i13 = i10;
            i14 = i12;
            eVarBorder = IconButtonDefaults.f5629a.border(null, null, null, null, null, composer2, 196608, 31);
        } else {
            composer2 = composer;
            i13 = i10;
            i14 = i12;
            eVarBorder = eVar;
        }
        MutableInteractionSource mutableInteractionSource2 = (i14 & 512) != 0 ? null : mutableInteractionSource;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1483826292, i13, i11, "androidx.tv.material3.IconButton (IconButton.kt:75)");
        }
        g gVar4 = gVar3;
        SurfaceKt.m6007Surface05tvjtU(aVar, SizeKt.m578size3ABfNKs(SemanticsModifierKt.semantics$default(modifier2, false, t1.f6410i, 1, null), IconButtonDefaults.f5632d), aVar3, z5, 0.0f, l.d(kVar2), l.b(fVarM5989colorsoq7We08), l.c(jVar2), l.a(eVarBorder), new e1(gVar4.f5994a, gVar4.f5995b, gVar4.f5996c), mutableInteractionSource2, ComposableLambdaKt.composableLambda(composer2, -687199219, true, new AnonymousClass2(qVar)), composer2, i13 & 8078, ((i13 >> 27) & 14) | 48, 16);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    public static final void OutlinedIconButton(r7.a<x6.t0> aVar, Modifier modifier, r7.a<x6.t0> aVar2, boolean z, j jVar, g gVar, k kVar, f fVar, e eVar, MutableInteractionSource mutableInteractionSource, r7.q<? super BoxScope, ? super Composer, ? super Integer, x6.t0> qVar, Composer composer, int i10, int i11, int i12) {
        j jVar2;
        g gVar2;
        k kVar2;
        Modifier modifier2;
        g gVar3;
        f fVarM6002colorsoq7We08;
        Composer composer2;
        int i13;
        int i14;
        e eVarBorder;
        composer.startReplaceableGroup(1139254602);
        Modifier modifier3 = (i12 & 2) != 0 ? Modifier.INSTANCE : modifier;
        r7.a<x6.t0> aVar3 = (i12 & 4) != 0 ? null : aVar2;
        boolean z5 = (i12 & 8) != 0 ? true : z;
        if ((i12 & 16) != 0) {
            OutlinedIconButtonDefaults outlinedIconButtonDefaults = OutlinedIconButtonDefaults.f5698a;
            jVar2 = new j(1.1f);
        } else {
            jVar2 = jVar;
        }
        if ((i12 & 32) != 0) {
            OutlinedIconButtonDefaults outlinedIconButtonDefaults2 = OutlinedIconButtonDefaults.f5698a;
            s1 s1Var = s1.f6376c;
            gVar2 = new g(s1Var, s1Var, s1Var);
        } else {
            gVar2 = gVar;
        }
        if ((i12 & 64) != 0) {
            RoundedCornerShape roundedCornerShape = OutlinedIconButtonDefaults.f5699b;
            kVar2 = new k(roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape);
        } else {
            kVar2 = kVar;
        }
        if ((i12 & 128) != 0) {
            gVar3 = gVar2;
            modifier2 = modifier3;
            fVarM6002colorsoq7We08 = OutlinedIconButtonDefaults.f5698a.m6002colorsoq7We08(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 100663296, 255);
        } else {
            modifier2 = modifier3;
            gVar3 = gVar2;
            fVarM6002colorsoq7We08 = fVar;
        }
        if ((i12 & 256) != 0) {
            composer2 = composer;
            i13 = i10;
            i14 = i12;
            eVarBorder = OutlinedIconButtonDefaults.f5698a.border(null, null, null, null, null, composer2, 196608, 31);
        } else {
            composer2 = composer;
            i13 = i10;
            i14 = i12;
            eVarBorder = eVar;
        }
        MutableInteractionSource mutableInteractionSource2 = (i14 & 512) != 0 ? null : mutableInteractionSource;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1139254602, i13, i11, "androidx.tv.material3.OutlinedIconButton (IconButton.kt:141)");
        }
        g gVar4 = gVar3;
        SurfaceKt.m6007Surface05tvjtU(aVar, SizeKt.m578size3ABfNKs(SemanticsModifierKt.semantics$default(modifier2, false, u1.f6439i, 1, null), OutlinedIconButtonDefaults.f5700c), aVar3, z5, 0.0f, l.d(kVar2), l.b(fVarM6002colorsoq7We08), l.c(jVar2), l.a(eVarBorder), new e1(gVar4.f5994a, gVar4.f5995b, gVar4.f5996c), mutableInteractionSource2, ComposableLambdaKt.composableLambda(composer2, -45144885, true, new C06392(qVar)), composer2, i13 & 8078, ((i13 >> 27) & 14) | 48, 16);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }
}
