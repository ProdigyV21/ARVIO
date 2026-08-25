package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a¦\u0001\u0010\u0015\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0013\u0010\u0006\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00022\u0013\u0010\u0007\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00022\u0013\u0010\b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00022\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000bH\u0001ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a5\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0002H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\"\u001a\u0010\u001c\u001a\u00020\r8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u001a\u0010 \u001a\u00020\r8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b \u0010\u001d\u001a\u0004\b!\u0010\u001f\"\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$\"\u0014\u0010%\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010$\"\u0014\u0010&\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010$\"\u0014\u0010'\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010$\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006("}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "buttons", "Landroidx/compose/ui/Modifier;", "modifier", "icon", LinkHeader.Parameters.Title, "text", "Landroidx/compose/ui/graphics/Shape;", "shape", "Landroidx/compose/ui/graphics/Color;", "containerColor", "Landroidx/compose/ui/unit/Dp;", "tonalElevation", "buttonContentColor", "iconContentColor", "titleContentColor", "textContentColor", "AlertDialogContent-4hvqGtA", "(Lr7/p;Landroidx/compose/ui/Modifier;Lr7/p;Lr7/p;Lr7/p;Landroidx/compose/ui/graphics/Shape;JFJJJJLandroidx/compose/runtime/Composer;III)V", "AlertDialogContent", "mainAxisSpacing", "crossAxisSpacing", "content", "AlertDialogFlowRow-ixp7dh8", "(FFLr7/p;Landroidx/compose/runtime/Composer;I)V", "AlertDialogFlowRow", "DialogMinWidth", "F", "getDialogMinWidth", "()F", "DialogMaxWidth", "getDialogMaxWidth", "Landroidx/compose/foundation/layout/PaddingValues;", "DialogPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "IconPadding", "TitlePadding", "TextPadding", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AlertDialogKt {
    private static final PaddingValues DialogPadding;
    private static final PaddingValues IconPadding;
    private static final PaddingValues TextPadding;
    private static final PaddingValues TitlePadding;
    private static final float DialogMinWidth = Dp.m5678constructorimpl(280);
    private static final float DialogMaxWidth = Dp.m5678constructorimpl(560);

    static {
        float f10 = 24;
        DialogPadding = PaddingKt.m522PaddingValues0680j_4(Dp.m5678constructorimpl(f10));
        float f11 = 16;
        IconPadding = PaddingKt.m526PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m5678constructorimpl(f11), 7, null);
        TitlePadding = PaddingKt.m526PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m5678constructorimpl(f11), 7, null);
        TextPadding = PaddingKt.m526PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m5678constructorimpl(f10), 7, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:152:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00fc  */
    /* JADX INFO: renamed from: AlertDialogContent-4hvqGtA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1215AlertDialogContent4hvqGtA(r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r29, androidx.compose.ui.Modifier r30, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r31, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r32, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r33, androidx.compose.ui.graphics.Shape r34, long r35, float r37, long r38, long r40, long r42, long r44, androidx.compose.runtime.Composer r46, int r47, int r48, int r49) {
        /*
            Method dump skipped, instruction units count: 529
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AlertDialogKt.m1215AlertDialogContent4hvqGtA(r7.p, androidx.compose.ui.Modifier, r7.p, r7.p, r7.p, androidx.compose.ui.graphics.Shape, long, float, long, long, long, long, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: renamed from: AlertDialogFlowRow-ixp7dh8, reason: not valid java name */
    public static final void m1216AlertDialogFlowRowixp7dh8(final float f10, final float f11, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(586821353);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(f10) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(f11) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(586821353, i11, -1, "androidx.compose.material3.AlertDialogFlowRow (AlertDialog.kt:129)");
            }
            composerStartRestartGroup.startReplaceableGroup(-1133133582);
            boolean z = ((i11 & 14) == 4) | ((i11 & 112) == 32);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.AlertDialogKt$AlertDialogFlowRow$1$1

                    /* JADX INFO: renamed from: androidx.compose.material3.AlertDialogKt$AlertDialogFlowRow$1$1$2, reason: invalid class name */
                    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                    public static final class AnonymousClass2 extends r implements l<Placeable.PlacementScope, t0> {
                        final /* synthetic */ List<Integer> $crossAxisPositions;
                        final /* synthetic */ int $mainAxisLayoutSize;
                        final /* synthetic */ float $mainAxisSpacing;
                        final /* synthetic */ List<List<Placeable>> $sequences;
                        final /* synthetic */ MeasureScope $this_Layout;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public AnonymousClass2(List<List<Placeable>> list, MeasureScope measureScope, float f10, int i10, List<Integer> list2) {
                            super(1);
                            this.$sequences = list;
                            this.$this_Layout = measureScope;
                            this.$mainAxisSpacing = f10;
                            this.$mainAxisLayoutSize = i10;
                            this.$crossAxisPositions = list2;
                        }

                        @Override // r7.l
                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            invoke((Placeable.PlacementScope) obj);
                            return t0.f22605a;
                        }

                        public final void invoke(Placeable.PlacementScope placementScope) {
                            List<List<Placeable>> list = this.$sequences;
                            MeasureScope measureScope = this.$this_Layout;
                            float f10 = this.$mainAxisSpacing;
                            int i10 = this.$mainAxisLayoutSize;
                            List<Integer> list2 = this.$crossAxisPositions;
                            int size = list.size();
                            for (int i11 = 0; i11 < size; i11++) {
                                List<Placeable> list3 = list.get(i11);
                                int size2 = list3.size();
                                int[] iArr = new int[size2];
                                int i12 = 0;
                                while (i12 < size2) {
                                    iArr[i12] = list3.get(i12).getWidth() + (i12 < t7.a.w(list3) ? measureScope.mo279roundToPx0680j_4(f10) : 0);
                                    i12++;
                                }
                                Arrangement.Horizontal end = Arrangement.INSTANCE.getEnd();
                                int[] iArr2 = new int[size2];
                                for (int i13 = 0; i13 < size2; i13++) {
                                    iArr2[i13] = 0;
                                }
                                end.arrange(measureScope, i10, iArr, measureScope.getLayoutDirection(), iArr2);
                                int size3 = list3.size();
                                for (int i14 = 0; i14 < size3; i14++) {
                                    Placeable.PlacementScope.place$default(placementScope, list3.get(i14), iArr2[i14], list2.get(i11).intValue(), 0.0f, 4, null);
                                }
                            }
                        }
                    }

                    private static final boolean measure_3p2s80s$canAddToCurrentSequence(List<Placeable> list, i0 i0Var, MeasureScope measureScope, float f12, long j10, Placeable placeable) {
                        if (list.isEmpty()) {
                            return true;
                        }
                        return placeable.getWidth() + (measureScope.mo279roundToPx0680j_4(f12) + i0Var.f19744i) <= Constraints.m5646getMaxWidthimpl(j10);
                    }

                    private static final void measure_3p2s80s$startNewSequence(List<List<Placeable>> list, i0 i0Var, MeasureScope measureScope, float f12, List<Placeable> list2, List<Integer> list3, i0 i0Var2, List<Integer> list4, i0 i0Var3, i0 i0Var4) {
                        if (!list.isEmpty()) {
                            i0Var.f19744i = measureScope.mo279roundToPx0680j_4(f12) + i0Var.f19744i;
                        }
                        list.add(0, x.c1(list2));
                        list3.add(Integer.valueOf(i0Var2.f19744i));
                        list4.add(Integer.valueOf(i0Var.f19744i));
                        i0Var.f19744i += i0Var2.f19744i;
                        i0Var3.f19744i = Math.max(i0Var3.f19744i, i0Var4.f19744i);
                        list2.clear();
                        i0Var4.f19744i = 0;
                        i0Var2.f19744i = 0;
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return androidx.compose.ui.layout.g.a(this, intrinsicMeasureScope, list, i12);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return androidx.compose.ui.layout.g.b(this, intrinsicMeasureScope, list, i12);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        ArrayList arrayList3 = new ArrayList();
                        i0 i0Var = new i0();
                        i0 i0Var2 = new i0();
                        ArrayList arrayList4 = new ArrayList();
                        i0 i0Var3 = new i0();
                        i0 i0Var4 = new i0();
                        float f12 = f10;
                        float f13 = f11;
                        int size = list.size();
                        int i12 = 0;
                        while (i12 < size) {
                            ArrayList arrayList5 = arrayList;
                            i0 i0Var5 = i0Var2;
                            Placeable placeableMo4631measureBRTryo0 = list.get(i12).mo4631measureBRTryo0(j10);
                            int i13 = i12;
                            i0 i0Var6 = i0Var3;
                            i0Var3 = i0Var6;
                            int i14 = size;
                            if (measure_3p2s80s$canAddToCurrentSequence(arrayList4, i0Var6, measureScope, f12, j10, placeableMo4631measureBRTryo0)) {
                                arrayList = arrayList5;
                                i0Var2 = i0Var5;
                            } else {
                                arrayList = arrayList5;
                                i0Var2 = i0Var5;
                                measure_3p2s80s$startNewSequence(arrayList, i0Var2, measureScope, f13, arrayList4, arrayList2, i0Var4, arrayList3, i0Var, i0Var3);
                            }
                            if (!arrayList4.isEmpty()) {
                                i0Var3.f19744i = measureScope.mo279roundToPx0680j_4(f12) + i0Var3.f19744i;
                            }
                            arrayList4.add(placeableMo4631measureBRTryo0);
                            i0Var3.f19744i = placeableMo4631measureBRTryo0.getWidth() + i0Var3.f19744i;
                            i0Var4.f19744i = Math.max(i0Var4.f19744i, placeableMo4631measureBRTryo0.getHeight());
                            i12 = i13 + 1;
                            size = i14;
                        }
                        if (!arrayList4.isEmpty()) {
                            measure_3p2s80s$startNewSequence(arrayList, i0Var2, measureScope, f11, arrayList4, arrayList2, i0Var4, arrayList3, i0Var, i0Var3);
                        }
                        int iMax = Math.max(i0Var.f19744i, Constraints.m5648getMinWidthimpl(j10));
                        return MeasureScope.CC.q(measureScope, iMax, Math.max(i0Var2.f19744i, Constraints.m5647getMinHeightimpl(j10)), null, new AnonymousClass2(arrayList, measureScope, f10, iMax, arrayList3), 4, null);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return androidx.compose.ui.layout.g.c(this, intrinsicMeasureScope, list, i12);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return androidx.compose.ui.layout.g.d(this, intrinsicMeasureScope, list, i12);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            composerStartRestartGroup.endReplaceableGroup();
            int i12 = (i11 >> 6) & 14;
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            Modifier.Companion companion = Modifier.INSTANCE;
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
            int i13 = ((i12 << 9) & 7168) | 6;
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
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            pVar.invoke(composerStartRestartGroup, Integer.valueOf((i13 >> 9) & 14));
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AlertDialogKt$AlertDialogFlowRow$2(f10, f11, pVar, i10));
        }
    }

    public static final float getDialogMaxWidth() {
        return DialogMaxWidth;
    }

    public static final float getDialogMinWidth() {
        return DialogMinWidth;
    }
}
