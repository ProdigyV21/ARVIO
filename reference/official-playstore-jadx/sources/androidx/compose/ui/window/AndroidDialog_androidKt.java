package androidx.compose.ui.window;

import a0.c;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.g;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.exoplayer.RendererCapabilities;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a:\u0010\u0007\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a,\u0010\u000b\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\t2\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0005H\u0003¢\u0006\u0004\b\u000b\u0010\f¨\u0006\u000e²\u0006\u0017\u0010\r\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00058\nX\u008a\u0084\u0002"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "onDismissRequest", "Landroidx/compose/ui/window/DialogProperties;", "properties", "Landroidx/compose/runtime/Composable;", "content", "Dialog", "(Lr7/a;Landroidx/compose/ui/window/DialogProperties;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/ui/Modifier;", "modifier", "DialogLayout", "(Landroidx/compose/ui/Modifier;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "currentContent", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidDialog_androidKt {

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<DisposableEffectScope, DisposableEffectResult> {
        final /* synthetic */ DialogWrapper $dialog;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(DialogWrapper dialogWrapper) {
            super(1);
            this.$dialog = dialogWrapper;
        }

        @Override // r7.l
        public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
            this.$dialog.show();
            final DialogWrapper dialogWrapper = this.$dialog;
            return new DisposableEffectResult() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$1$invoke$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                    dialogWrapper.dismiss();
                    dialogWrapper.disposeComposition();
                }
            };
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements r7.a<t0> {
        final /* synthetic */ DialogWrapper $dialog;
        final /* synthetic */ LayoutDirection $layoutDirection;
        final /* synthetic */ r7.a<t0> $onDismissRequest;
        final /* synthetic */ DialogProperties $properties;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(DialogWrapper dialogWrapper, r7.a<t0> aVar, DialogProperties dialogProperties, LayoutDirection layoutDirection) {
            super(0);
            this.$dialog = dialogWrapper;
            this.$onDismissRequest = aVar;
            this.$properties = dialogProperties;
            this.$layoutDirection = layoutDirection;
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m5931invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m5931invoke() {
            this.$dialog.updateParameters(this.$onDismissRequest, this.$properties, this.$layoutDirection);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidDialog_androidKt$Dialog$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ r7.a<t0> $onDismissRequest;
        final /* synthetic */ DialogProperties $properties;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(r7.a<t0> aVar, DialogProperties dialogProperties, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
            super(2);
            this.$onDismissRequest = aVar;
            this.$properties = dialogProperties;
            this.$content = pVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AndroidDialog_androidKt.Dialog(this.$onDismissRequest, this.$properties, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidDialog_androidKt$DialogLayout$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C06292 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ Modifier $modifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C06292(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
            super(2);
            this.$modifier = modifier;
            this.$content = pVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AndroidDialog_androidKt.DialogLayout(this.$modifier, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Dialog(r7.a<x6.t0> r21, androidx.compose.ui.window.DialogProperties r22, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r23, androidx.compose.runtime.Composer r24, int r25, int r26) {
        /*
            Method dump skipped, instruction units count: 321
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.window.AndroidDialog_androidKt.Dialog(r7.a, androidx.compose.ui.window.DialogProperties, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p<Composer, Integer, t0> Dialog$lambda$0(State<? extends p<? super Composer, ? super Integer, t0>> state) {
        return (p) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DialogLayout(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1177876616);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 14) == 0) {
            i12 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i11 & 2) != 0) {
            i12 |= 48;
        } else if ((i10 & 112) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 32 : 16;
        }
        if ((i12 & 91) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1177876616, i12, -1, "androidx.compose.ui.window.DialogLayout (AndroidDialog.android.kt:452)");
            }
            C06281 c06281 = new MeasurePolicy() { // from class: androidx.compose.ui.window.AndroidDialog_androidKt.DialogLayout.1

                /* JADX INFO: renamed from: androidx.compose.ui.window.AndroidDialog_androidKt$DialogLayout$1$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                public static final class C01231 extends r implements l<Placeable.PlacementScope, t0> {
                    final /* synthetic */ List<Placeable> $placeables;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public C01231(List<? extends Placeable> list) {
                        super(1);
                        this.$placeables = list;
                    }

                    @Override // r7.l
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((Placeable.PlacementScope) obj);
                        return t0.f22605a;
                    }

                    public final void invoke(Placeable.PlacementScope placementScope) {
                        List<Placeable> list = this.$placeables;
                        int size = list.size();
                        for (int i10 = 0; i10 < size; i10++) {
                            Placeable.PlacementScope.placeRelative$default(placementScope, list.get(i10), 0, 0, 0.0f, 4, null);
                        }
                    }
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                    return g.a(this, intrinsicMeasureScope, list, i14);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                    return g.b(this, intrinsicMeasureScope, list, i14);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
                    Object obj;
                    ArrayList arrayList = new ArrayList(list.size());
                    int size = list.size();
                    for (int i14 = 0; i14 < size; i14++) {
                        arrayList.add(list.get(i14).mo4631measureBRTryo0(j10));
                    }
                    Object obj2 = null;
                    int i15 = 1;
                    if (arrayList.isEmpty()) {
                        obj = null;
                    } else {
                        obj = arrayList.get(0);
                        int width = ((Placeable) obj).getWidth();
                        int iW = t7.a.w(arrayList);
                        if (1 <= iW) {
                            int i16 = 1;
                            while (true) {
                                Object obj3 = arrayList.get(i16);
                                int width2 = ((Placeable) obj3).getWidth();
                                if (width < width2) {
                                    obj = obj3;
                                    width = width2;
                                }
                                if (i16 == iW) {
                                    break;
                                }
                                i16++;
                            }
                        }
                    }
                    Placeable placeable = (Placeable) obj;
                    int width3 = placeable != null ? placeable.getWidth() : Constraints.m5648getMinWidthimpl(j10);
                    if (!arrayList.isEmpty()) {
                        Object obj4 = arrayList.get(0);
                        int height = ((Placeable) obj4).getHeight();
                        int iW2 = t7.a.w(arrayList);
                        if (1 <= iW2) {
                            while (true) {
                                Object obj5 = arrayList.get(i15);
                                int height2 = ((Placeable) obj5).getHeight();
                                if (height < height2) {
                                    obj4 = obj5;
                                    height = height2;
                                }
                                if (i15 == iW2) {
                                    break;
                                }
                                i15++;
                            }
                        }
                        obj2 = obj4;
                    }
                    Placeable placeable2 = (Placeable) obj2;
                    return MeasureScope.CC.q(measureScope, width3, placeable2 != null ? placeable2.getHeight() : Constraints.m5647getMinHeightimpl(j10), null, new C01231(arrayList), 4, null);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                    return g.c(this, intrinsicMeasureScope, list, i14);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                    return g.d(this, intrinsicMeasureScope, list, i14);
                }
            };
            int i14 = ((i12 >> 3) & 14) | RendererCapabilities.DECODER_SUPPORT_MASK | ((i12 << 3) & 112);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier);
            int i15 = ((i14 << 9) & 7168) | 6;
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
            Updater.m2998setimpl(composerM2991constructorimpl, c06281, companion.getSetMeasurePolicy());
            Updater.m2998setimpl(composerM2991constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            p<ComposeUiNode, Integer, t0> setCompositeKeyHash = companion.getSetCompositeKeyHash();
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, setCompositeKeyHash);
            }
            c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            pVar.invoke(composerStartRestartGroup, Integer.valueOf((i15 >> 9) & 14));
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C06292(modifier, pVar, i10, i11));
        }
    }
}
