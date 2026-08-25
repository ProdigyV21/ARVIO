package androidx.tv.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0081\u0001\u0010\u0013\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00040\u000e¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0004\b\u0013\u0010\u0014\u001a#\u0010\u0017\u001a\u00020\u0016*\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0001¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/tv/material3/l4;", "", "selected", "Lkotlin/Function0;", "Lx6/t0;", "onFocus", "Landroidx/compose/ui/Modifier;", "modifier", "onClick", "enabled", "Landroidx/tv/material3/c4;", "colors", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "Tab", "(Landroidx/tv/material3/l4;ZLr7/a;Landroidx/compose/ui/Modifier;Lr7/a;ZLandroidx/tv/material3/c4;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "doesTabRowHaveFocus", "Landroidx/tv/material3/y2;", "toSelectableSurfaceColors", "(Landroidx/tv/material3/c4;ZZLandroidx/compose/runtime/Composer;I)Landroidx/tv/material3/y2;", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TabKt {

    /* JADX INFO: renamed from: androidx.tv.material3.TabKt$Tab$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/layout/BoxScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends kotlin.jvm.internal.r implements r7.q<BoxScope, Composer, Integer, x6.t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ r7.q f5764i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(r7.q qVar) {
            super(3);
            this.f5764i = qVar;
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
                ComposerKt.traceEventStart(-2006685610, i10, -1, "androidx.tv.material3.Tab.<anonymous> (Tab.kt:92)");
            }
            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            composer.startReplaceableGroup(693286680);
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer, 54);
            composer.startReplaceableGroup(-1323940314);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            this.f5764i.invoke(RowScopeInstance.INSTANCE, composer, 6);
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:160:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Tab(androidx.tv.material3.l4 r37, boolean r38, r7.a<x6.t0> r39, androidx.compose.ui.Modifier r40, r7.a<x6.t0> r41, boolean r42, androidx.tv.material3.c4 r43, androidx.compose.foundation.interaction.MutableInteractionSource r44, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r45, androidx.compose.runtime.Composer r46, int r47, int r48) {
        /*
            Method dump skipped, instruction units count: 742
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.TabKt.Tab(androidx.tv.material3.l4, boolean, r7.a, androidx.compose.ui.Modifier, r7.a, boolean, androidx.tv.material3.c4, androidx.compose.foundation.interaction.MutableInteractionSource, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final y2 toSelectableSurfaceColors(c4 c4Var, boolean z, boolean z5, Composer composer, int i10) {
        composer.startReplaceableGroup(-308554712);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-308554712, i10, -1, "androidx.tv.material3.toSelectableSurfaceColors (Tab.kt:228)");
        }
        long j10 = z ? c4Var.f5881a : c4Var.f5882b;
        long j11 = z5 ? c4Var.f5883c : c4Var.f5888h;
        long j12 = c4Var.f5884d;
        long j13 = c4Var.f5885e;
        long j14 = z ? c4Var.f5886f : c4Var.f5887g;
        Color.Companion companion = Color.INSTANCE;
        long jM3507getTransparent0d7_KjU = companion.m3507getTransparent0d7_KjU();
        long jM3507getTransparent0d7_KjU2 = companion.m3507getTransparent0d7_KjU();
        long jM3507getTransparent0d7_KjU3 = companion.m3507getTransparent0d7_KjU();
        long jM3507getTransparent0d7_KjU4 = companion.m3507getTransparent0d7_KjU();
        y2 y2VarM6004colorsu3YEpmA = SelectableSurfaceDefaults.f5709a.m6004colorsu3YEpmA(jM3507getTransparent0d7_KjU, j10, jM3507getTransparent0d7_KjU2, j12, jM3507getTransparent0d7_KjU3, 0L, companion.m3507getTransparent0d7_KjU(), j11, companion.m3507getTransparent0d7_KjU(), j14, jM3507getTransparent0d7_KjU4, j13, companion.m3507getTransparent0d7_KjU(), 0L, composer, 102261126, 24966, 8224);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return y2VarM6004colorsu3YEpmA;
    }
}
