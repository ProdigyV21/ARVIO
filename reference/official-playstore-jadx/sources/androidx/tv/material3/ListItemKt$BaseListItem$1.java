package androidx.tv.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/layout/BoxScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/layout/BoxScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ListItemKt$BaseListItem$1 extends kotlin.jvm.internal.r implements r7.q<BoxScope, Composer, Integer, x6.t0> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ float f5656i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ PaddingValues f5657l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.q f5658m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.p f5659n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ float f5660o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ r7.p f5661p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ TextStyle f5662q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ r7.p f5663r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ r7.p f5664s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ TextStyle f5665t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ListItemKt$BaseListItem$1(float f10, PaddingValues paddingValues, r7.q qVar, r7.p pVar, float f11, r7.p pVar2, TextStyle textStyle, r7.p pVar3, r7.p pVar4, TextStyle textStyle2) {
        super(3);
        this.f5656i = f10;
        this.f5657l = paddingValues;
        this.f5658m = qVar;
        this.f5659n = pVar;
        this.f5660o = f11;
        this.f5661p = pVar2;
        this.f5662q = textStyle;
        this.f5663r = pVar3;
        this.f5664s = pVar4;
        this.f5665t = textStyle2;
    }

    @Override // r7.q
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((BoxScope) obj, (Composer) obj2, ((Number) obj3).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(BoxScope boxScope, Composer composer, int i10) {
        int i11;
        ComposeUiNode.Companion companion;
        int i12;
        int i13;
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2081398503, i10, -1, "androidx.tv.material3.BaseListItem.<anonymous> (ListItem.kt:299)");
        }
        Modifier.Companion companion2 = Modifier.INSTANCE;
        Modifier modifierPadding = PaddingKt.padding(SizeKt.m563defaultMinSizeVpY3zN4$default(companion2, 0.0f, this.f5656i, 1, null), this.f5657l);
        Alignment.Companion companion3 = Alignment.INSTANCE;
        Alignment.Vertical centerVertically = companion3.getCenterVertically();
        composer.startReplaceableGroup(693286680);
        Arrangement arrangement = Arrangement.INSTANCE;
        MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(arrangement, centerVertically, composer, 48, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion4.getConstructor();
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
        r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyG, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        composer.startReplaceableGroup(25294625);
        r7.q qVar = this.f5658m;
        if (qVar == null) {
            i13 = 733328855;
            i12 = -1323940314;
            companion = companion4;
        } else {
            float f10 = this.f5660o;
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(SizeKt.m562defaultMinSizeVpY3zN4(companion2, f10, f10), s.f6362r);
            Alignment center = companion3.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierGraphicsLayer);
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
            r7.p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting()) {
                i11 = 6;
            } else {
                i11 = 6;
                if (!kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                }
                a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
                qVar.invoke(BoxScopeInstance.INSTANCE, composer, Integer.valueOf(i11));
                composer.endReplaceableGroup();
                composer.endNode();
                composer.endReplaceableGroup();
                composer.endReplaceableGroup();
                companion = companion4;
                i12 = -1323940314;
                i13 = 733328855;
                SpacerKt.Spacer(PaddingKt.m533paddingqDBjuR0$default(companion2, 0.0f, 0.0f, ListItemDefaults.f5647h, 0.0f, 11, null), composer, i11);
            }
            a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
            qVar.invoke(BoxScopeInstance.INSTANCE, composer, Integer.valueOf(i11));
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            companion = companion4;
            i12 = -1323940314;
            i13 = 733328855;
            SpacerKt.Spacer(PaddingKt.m533paddingqDBjuR0$default(companion2, 0.0f, 0.0f, ListItemDefaults.f5647h, 0.0f, 11, null), composer, i11);
        }
        composer.endReplaceableGroup();
        Modifier modifierAlign = rowScopeInstance.align(androidx.compose.foundation.layout.e.a(rowScopeInstance, companion2, 1.0f, false, 2, null), companion3.getCenterVertically());
        composer.startReplaceableGroup(i13);
        MeasurePolicy measurePolicyD = a0.c.d(companion3, false, composer, 0, i12);
        int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor3 = companion.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierAlign);
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
        r7.p pVarU3 = a0.c.u(companion, composerM2991constructorimpl3, measurePolicyD, composerM2991constructorimpl3, currentCompositionLocalMap3);
        if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
            a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
        }
        a0.c.y(composer, qVarModifierMaterializerOf3, composer, 0, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        composer.startReplaceableGroup(-483455358);
        MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion3, arrangement.getTop(), composer, 0, i12);
        int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor4 = companion.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(companion2);
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor4);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl4 = Updater.m2991constructorimpl(composer);
        r7.p pVarU4 = a0.c.u(companion, composerM2991constructorimpl4, measurePolicyK, composerM2991constructorimpl4, currentCompositionLocalMap4);
        if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
            a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
        }
        a0.c.y(composer, qVarModifierMaterializerOf4, composer, 0, 2058660585);
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        composer.startReplaceableGroup(1509815837);
        r7.p pVar = this.f5661p;
        if (pVar != null) {
            ProvidableCompositionLocal providableCompositionLocal = o1.f6258a;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>) providableCompositionLocal.provides(Color.m3462boximpl(Color.m3471copywmQWz5c$default(((Color) composer.consume(providableCompositionLocal)).m3482unboximpl(), 0.6f, 0.0f, 0.0f, 0.0f, 14, null))), ComposableLambdaKt.composableLambda(composer, 676560428, true, new ListItemKt$BaseListItem$1$1$2$1$1$1(pVar)), composer, ProvidedValue.$stable | 48);
        }
        composer.endReplaceableGroup();
        TextKt.ProvideTextStyle(this.f5662q, this.f5663r, composer, 0);
        composer.startReplaceableGroup(1509838206);
        r7.p pVar2 = this.f5664s;
        if (pVar2 != null) {
            ProvidableCompositionLocal providableCompositionLocal2 = o1.f6258a;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>) providableCompositionLocal2.provides(Color.m3462boximpl(Color.m3471copywmQWz5c$default(((Color) composer.consume(providableCompositionLocal2)).m3482unboximpl(), 0.8f, 0.0f, 0.0f, 0.0f, 14, null))), ComposableLambdaKt.composableLambda(composer, 499227811, true, new ListItemKt$BaseListItem$1$1$2$1$2$1(pVar2)), composer, ProvidedValue.$stable | 48);
        }
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(25356578);
        r7.p pVar3 = this.f5659n;
        if (pVar3 != null) {
            Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(companion2, ListItemDefaults.f5648i, 0.0f, 0.0f, 0.0f, 14, null);
            composer.startReplaceableGroup(i13);
            MeasurePolicy measurePolicyD2 = a0.c.d(companion3, false, composer, 0, i12);
            int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap5 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor5 = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf5 = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor5);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl5 = Updater.m2991constructorimpl(composer);
            r7.p pVarU5 = a0.c.u(companion, composerM2991constructorimpl5, measurePolicyD2, composerM2991constructorimpl5, currentCompositionLocalMap5);
            if (composerM2991constructorimpl5.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                a0.c.w(currentCompositeKeyHash5, composerM2991constructorimpl5, currentCompositeKeyHash5, pVarU5);
            }
            a0.c.y(composer, qVarModifierMaterializerOf5, composer, 0, 2058660585);
            ProvidableCompositionLocal providableCompositionLocal3 = o1.f6258a;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>) providableCompositionLocal3.provides(composer.consume(providableCompositionLocal3)), ComposableLambdaKt.composableLambda(composer, -2017627603, true, new ListItemKt$BaseListItem$1$1$3$1$1(this.f5665t, pVar3)), composer, ProvidedValue.$stable | 48);
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
        }
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
