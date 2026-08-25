package androidx.compose.ui.graphics.vector;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.vector.VectorProperty;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import io.ktor.http.ContentDisposition;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0096\u0001\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2@\u0010\u0011\u001a<\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\r\u0012\b\b\u0007\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\r\u0012\b\b\u0007\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a \u0001\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\u0017\u001a\u00020\u00162@\u0010\u0011\u001a<\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\r\u0012\b\b\u0007\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\r\u0012\b\b\u0007\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0017\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001aH\u0007¢\u0006\u0004\b\u0015\u0010\u001c\u001a-\u0010!\u001a\u00020\u000e*\u00020\u001d2\u0017\u0010 \u001a\u0013\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u000e0\u001e¢\u0006\u0002\b\u001fH\u0082\b¢\u0006\u0004\b!\u0010\"\u001a&\u0010'\u001a\u00020$*\u00020#2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a*\u0010+\u001a\u00020$2\u0006\u0010(\u001a\u00020$2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a$\u0010/\u001a\u0004\u0018\u00010,2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001aD\u00104\u001a\u00020\u0012*\u00020\u00122\u0006\u0010(\u001a\u00020$2\u0006\u00100\u001a\u00020$2\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\u00101\u001a\u0004\u0018\u00010,2\b\b\u0002\u0010\u0017\u001a\u00020\u0016H\u0000ø\u0001\u0000¢\u0006\u0004\b2\u00103\u001a'\u00109\u001a\u00020\u00122\u0006\u00105\u001a\u00020#2\u0006\u00106\u001a\u00020\u001a2\u0006\u00108\u001a\u000207H\u0000¢\u0006\u0004\b9\u0010:\u001a\u001b\u0010=\u001a\u000207*\u0002072\u0006\u0010<\u001a\u00020;H\u0000¢\u0006\u0004\b=\u0010>\u001a-\u0010C\u001a\u00020\u000e2\u0006\u0010?\u001a\u00020;2\u0014\b\u0002\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020A0@H\u0007¢\u0006\u0004\bC\u0010D\"\u0014\u0010E\u001a\u00020\u00068\u0006X\u0086T¢\u0006\u0006\n\u0004\bE\u0010F\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006G"}, d2 = {"Landroidx/compose/ui/unit/Dp;", "defaultWidth", "defaultHeight", "", "viewportWidth", "viewportHeight", "", ContentDisposition.Parameters.Name, "Landroidx/compose/ui/graphics/Color;", "tintColor", "Landroidx/compose/ui/graphics/BlendMode;", "tintBlendMode", "Lkotlin/Function2;", "Lx6/y;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/ui/graphics/vector/VectorComposable;", "content", "Landroidx/compose/ui/graphics/vector/VectorPainter;", "rememberVectorPainter-mlNsNFs", "(FFFFLjava/lang/String;JILr7/r;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/graphics/vector/VectorPainter;", "rememberVectorPainter", "", "autoMirror", "rememberVectorPainter-vIP8VLU", "(FFFFLjava/lang/String;JIZLr7/r;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/graphics/vector/VectorPainter;", "Landroidx/compose/ui/graphics/vector/ImageVector;", "image", "(Landroidx/compose/ui/graphics/vector/ImageVector;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/vector/VectorPainter;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lkotlin/Function1;", "Lx6/n;", "block", "mirror", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Lr7/l;)V", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/geometry/Size;", "obtainSizePx-VpY3zN4", "(Landroidx/compose/ui/unit/Density;FF)J", "obtainSizePx", "defaultSize", "obtainViewportSize-Pq9zytI", "(JFF)J", "obtainViewportSize", "Landroidx/compose/ui/graphics/ColorFilter;", "createColorFilter-xETnrds", "(JI)Landroidx/compose/ui/graphics/ColorFilter;", "createColorFilter", "viewportSize", "intrinsicColorFilter", "configureVectorPainter-T4PVSW8", "(Landroidx/compose/ui/graphics/vector/VectorPainter;JJLjava/lang/String;Landroidx/compose/ui/graphics/ColorFilter;Z)Landroidx/compose/ui/graphics/vector/VectorPainter;", "configureVectorPainter", "density", "imageVector", "Landroidx/compose/ui/graphics/vector/GroupComponent;", "root", "createVectorPainterFromImageVector", "(Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/graphics/vector/ImageVector;Landroidx/compose/ui/graphics/vector/GroupComponent;)Landroidx/compose/ui/graphics/vector/VectorPainter;", "Landroidx/compose/ui/graphics/vector/VectorGroup;", "currentGroup", "createGroupComponent", "(Landroidx/compose/ui/graphics/vector/GroupComponent;Landroidx/compose/ui/graphics/vector/VectorGroup;)Landroidx/compose/ui/graphics/vector/GroupComponent;", "group", "", "Landroidx/compose/ui/graphics/vector/VectorConfig;", "configs", "RenderVectorGroup", "(Landroidx/compose/ui/graphics/vector/VectorGroup;Ljava/util/Map;Landroidx/compose/runtime/Composer;II)V", "RootGroupName", "Ljava/lang/String;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VectorPainterKt {
    public static final String RootGroupName = "VectorRootGroup";

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ Map<String, VectorConfig> $configs;
        final /* synthetic */ VectorNode $vectorNode;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(VectorNode vectorNode, Map<String, ? extends VectorConfig> map) {
            super(2);
            this.$vectorNode = vectorNode;
            this.$configs = map;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 11) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1450046638, i10, -1, "androidx.compose.ui.graphics.vector.RenderVectorGroup.<anonymous> (VectorPainter.kt:510)");
            }
            VectorPainterKt.RenderVectorGroup((VectorGroup) this.$vectorNode, this.$configs, composer, 64, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ Map<String, VectorConfig> $configs;
        final /* synthetic */ VectorGroup $group;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(VectorGroup vectorGroup, Map<String, ? extends VectorConfig> map, int i10, int i11) {
            super(2);
            this.$group = vectorGroup;
            this.$configs = map;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            VectorPainterKt.RenderVectorGroup(this.$group, this.$configs, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    public static final void RenderVectorGroup(VectorGroup vectorGroup, Map<String, ? extends VectorConfig> map, Composer composer, int i10, int i11) {
        int i12;
        Map<String, ? extends VectorConfig> map2;
        Iterator<VectorNode> it;
        Map<String, ? extends VectorConfig> map3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-446179233);
        if ((i11 & 1) != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 14) == 0) {
            i12 = (composerStartRestartGroup.changed(vectorGroup) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        int i13 = i11 & 2;
        if (i13 != 0) {
            i12 |= 16;
        }
        if (i13 == 2 && (i12 & 91) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            map2 = map;
        } else {
            Map<String, ? extends VectorConfig> map4 = i13 != 0 ? a0.f19683i : map;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-446179233, i12, -1, "androidx.compose.ui.graphics.vector.RenderVectorGroup (VectorPainter.kt:426)");
            }
            Iterator<VectorNode> it2 = vectorGroup.iterator();
            while (it2.hasNext()) {
                VectorNode next = it2.next();
                if (next instanceof VectorPath) {
                    composerStartRestartGroup.startReplaceableGroup(-326282007);
                    VectorPath vectorPath = (VectorPath) next;
                    VectorConfig vectorConfig = map4.get(vectorPath.getName());
                    if (vectorConfig == null) {
                        vectorConfig = new VectorConfig() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$config$1
                            @Override // androidx.compose.ui.graphics.vector.VectorConfig
                            public final /* synthetic */ Object getOrDefault(VectorProperty vectorProperty, Object obj) {
                                return a.a(this, vectorProperty, obj);
                            }
                        };
                    }
                    it = it2;
                    Composer composer2 = composerStartRestartGroup;
                    map3 = map4;
                    VectorComposeKt.m4038Path9cdaXJ4((List) vectorConfig.getOrDefault(VectorProperty.PathData.INSTANCE, vectorPath.getPathData()), vectorPath.getPathFillType(), vectorPath.getName(), (Brush) vectorConfig.getOrDefault(VectorProperty.Fill.INSTANCE, vectorPath.getFill()), ((Number) vectorConfig.getOrDefault(VectorProperty.FillAlpha.INSTANCE, Float.valueOf(vectorPath.getFillAlpha()))).floatValue(), (Brush) vectorConfig.getOrDefault(VectorProperty.Stroke.INSTANCE, vectorPath.getStroke()), ((Number) vectorConfig.getOrDefault(VectorProperty.StrokeAlpha.INSTANCE, Float.valueOf(vectorPath.getStrokeAlpha()))).floatValue(), ((Number) vectorConfig.getOrDefault(VectorProperty.StrokeLineWidth.INSTANCE, Float.valueOf(vectorPath.getStrokeLineWidth()))).floatValue(), vectorPath.getStrokeLineCap(), vectorPath.getStrokeLineJoin(), vectorPath.getStrokeLineMiter(), ((Number) vectorConfig.getOrDefault(VectorProperty.TrimPathStart.INSTANCE, Float.valueOf(vectorPath.getTrimPathStart()))).floatValue(), ((Number) vectorConfig.getOrDefault(VectorProperty.TrimPathEnd.INSTANCE, Float.valueOf(vectorPath.getTrimPathEnd()))).floatValue(), ((Number) vectorConfig.getOrDefault(VectorProperty.TrimPathOffset.INSTANCE, Float.valueOf(vectorPath.getTrimPathOffset()))).floatValue(), composer2, 8, 0, 0);
                    composerStartRestartGroup = composer2;
                    composerStartRestartGroup.endReplaceableGroup();
                } else {
                    it = it2;
                    map3 = map4;
                    if (next instanceof VectorGroup) {
                        composerStartRestartGroup.startReplaceableGroup(-326280149);
                        VectorGroup vectorGroup2 = (VectorGroup) next;
                        VectorConfig vectorConfig2 = map3.get(vectorGroup2.getName());
                        if (vectorConfig2 == null) {
                            vectorConfig2 = new VectorConfig() { // from class: androidx.compose.ui.graphics.vector.VectorPainterKt$RenderVectorGroup$config$2
                                @Override // androidx.compose.ui.graphics.vector.VectorConfig
                                public final /* synthetic */ Object getOrDefault(VectorProperty vectorProperty, Object obj) {
                                    return a.a(this, vectorProperty, obj);
                                }
                            };
                        }
                        VectorComposeKt.Group(vectorGroup2.getName(), ((Number) vectorConfig2.getOrDefault(VectorProperty.Rotation.INSTANCE, Float.valueOf(vectorGroup2.getRotation()))).floatValue(), ((Number) vectorConfig2.getOrDefault(VectorProperty.PivotX.INSTANCE, Float.valueOf(vectorGroup2.getPivotX()))).floatValue(), ((Number) vectorConfig2.getOrDefault(VectorProperty.PivotY.INSTANCE, Float.valueOf(vectorGroup2.getPivotY()))).floatValue(), ((Number) vectorConfig2.getOrDefault(VectorProperty.ScaleX.INSTANCE, Float.valueOf(vectorGroup2.getScaleX()))).floatValue(), ((Number) vectorConfig2.getOrDefault(VectorProperty.ScaleY.INSTANCE, Float.valueOf(vectorGroup2.getScaleY()))).floatValue(), ((Number) vectorConfig2.getOrDefault(VectorProperty.TranslateX.INSTANCE, Float.valueOf(vectorGroup2.getTranslationX()))).floatValue(), ((Number) vectorConfig2.getOrDefault(VectorProperty.TranslateY.INSTANCE, Float.valueOf(vectorGroup2.getTranslationY()))).floatValue(), (List) vectorConfig2.getOrDefault(VectorProperty.PathData.INSTANCE, vectorGroup2.getClipPathData()), ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1450046638, true, new AnonymousClass1(next, map3)), composerStartRestartGroup, 939524096, 0);
                        composerStartRestartGroup.endReplaceableGroup();
                    } else {
                        composerStartRestartGroup.startReplaceableGroup(-326278679);
                        composerStartRestartGroup.endReplaceableGroup();
                    }
                }
                map4 = map3;
                it2 = it;
            }
            map2 = map4;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass2(vectorGroup, map2, i10, i11));
        }
    }

    /* JADX INFO: renamed from: configureVectorPainter-T4PVSW8, reason: not valid java name */
    public static final VectorPainter m4050configureVectorPainterT4PVSW8(VectorPainter vectorPainter, long j10, long j11, String str, ColorFilter colorFilter, boolean z) {
        vectorPainter.m4046setSizeuvyYCjk$ui_release(j10);
        vectorPainter.setAutoMirror$ui_release(z);
        vectorPainter.setIntrinsicColorFilter$ui_release(colorFilter);
        vectorPainter.m4047setViewportSizeuvyYCjk$ui_release(j11);
        vectorPainter.setName$ui_release(str);
        return vectorPainter;
    }

    /* JADX INFO: renamed from: configureVectorPainter-T4PVSW8$default, reason: not valid java name */
    public static /* synthetic */ VectorPainter m4051configureVectorPainterT4PVSW8$default(VectorPainter vectorPainter, long j10, long j11, String str, ColorFilter colorFilter, boolean z, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            str = RootGroupName;
        }
        return m4050configureVectorPainterT4PVSW8(vectorPainter, j10, j11, str, colorFilter, (i10 & 16) != 0 ? false : z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createColorFilter-xETnrds, reason: not valid java name */
    public static final ColorFilter m4052createColorFilterxETnrds(long j10, int i10) {
        if (j10 != Color.INSTANCE.m3508getUnspecified0d7_KjU()) {
            return ColorFilter.INSTANCE.m3516tintxETnrds(j10, i10);
        }
        return null;
    }

    public static final GroupComponent createGroupComponent(GroupComponent groupComponent, VectorGroup vectorGroup) {
        int size = vectorGroup.getSize();
        for (int i10 = 0; i10 < size; i10++) {
            VectorNode vectorNode = vectorGroup.get(i10);
            if (vectorNode instanceof VectorPath) {
                PathComponent pathComponent = new PathComponent();
                VectorPath vectorPath = (VectorPath) vectorNode;
                pathComponent.setPathData(vectorPath.getPathData());
                pathComponent.m4031setPathFillTypeoQ8Xj4U(vectorPath.getPathFillType());
                pathComponent.setName(vectorPath.getName());
                pathComponent.setFill(vectorPath.getFill());
                pathComponent.setFillAlpha(vectorPath.getFillAlpha());
                pathComponent.setStroke(vectorPath.getStroke());
                pathComponent.setStrokeAlpha(vectorPath.getStrokeAlpha());
                pathComponent.setStrokeLineWidth(vectorPath.getStrokeLineWidth());
                pathComponent.m4032setStrokeLineCapBeK7IIE(vectorPath.getStrokeLineCap());
                pathComponent.m4033setStrokeLineJoinWw9F2mQ(vectorPath.getStrokeLineJoin());
                pathComponent.setStrokeLineMiter(vectorPath.getStrokeLineMiter());
                pathComponent.setTrimPathStart(vectorPath.getTrimPathStart());
                pathComponent.setTrimPathEnd(vectorPath.getTrimPathEnd());
                pathComponent.setTrimPathOffset(vectorPath.getTrimPathOffset());
                groupComponent.insertAt(i10, pathComponent);
            } else if (vectorNode instanceof VectorGroup) {
                GroupComponent groupComponent2 = new GroupComponent();
                VectorGroup vectorGroup2 = (VectorGroup) vectorNode;
                groupComponent2.setName(vectorGroup2.getName());
                groupComponent2.setRotation(vectorGroup2.getRotation());
                groupComponent2.setScaleX(vectorGroup2.getScaleX());
                groupComponent2.setScaleY(vectorGroup2.getScaleY());
                groupComponent2.setTranslationX(vectorGroup2.getTranslationX());
                groupComponent2.setTranslationY(vectorGroup2.getTranslationY());
                groupComponent2.setPivotX(vectorGroup2.getPivotX());
                groupComponent2.setPivotY(vectorGroup2.getPivotY());
                groupComponent2.setClipPathData(vectorGroup2.getClipPathData());
                createGroupComponent(groupComponent2, vectorGroup2);
                groupComponent.insertAt(i10, groupComponent2);
            }
        }
        return groupComponent;
    }

    public static final VectorPainter createVectorPainterFromImageVector(Density density, ImageVector imageVector, GroupComponent groupComponent) {
        long jM4053obtainSizePxVpY3zN4 = m4053obtainSizePxVpY3zN4(density, imageVector.getDefaultWidth(), imageVector.getDefaultHeight());
        return m4050configureVectorPainterT4PVSW8(new VectorPainter(groupComponent), jM4053obtainSizePxVpY3zN4, m4054obtainViewportSizePq9zytI(jM4053obtainSizePxVpY3zN4, imageVector.getViewportWidth(), imageVector.getViewportHeight()), imageVector.getName(), m4052createColorFilterxETnrds(imageVector.getTintColor(), imageVector.getTintBlendMode()), imageVector.getAutoMirror());
    }

    private static final void mirror(DrawScope drawScope, l<? super DrawScope, t0> lVar) {
        long jMo3915getCenterF1C5BW0 = drawScope.mo3915getCenterF1C5BW0();
        DrawContext drawContext = drawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3929scale0AR0LA0(-1.0f, 1.0f, jMo3915getCenterF1C5BW0);
        lVar.invoke(drawScope);
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }

    /* JADX INFO: renamed from: obtainSizePx-VpY3zN4, reason: not valid java name */
    private static final long m4053obtainSizePxVpY3zN4(Density density, float f10, float f11) {
        return SizeKt.Size(density.mo285toPx0680j_4(f10), density.mo285toPx0680j_4(f11));
    }

    /* JADX INFO: renamed from: obtainViewportSize-Pq9zytI, reason: not valid java name */
    private static final long m4054obtainViewportSizePq9zytI(long j10, float f10, float f11) {
        if (Float.isNaN(f10)) {
            f10 = Size.m3305getWidthimpl(j10);
        }
        if (Float.isNaN(f11)) {
            f11 = Size.m3302getHeightimpl(j10);
        }
        return SizeKt.Size(f10, f11);
    }

    public static final VectorPainter rememberVectorPainter(ImageVector imageVector, Composer composer, int i10) {
        composer.startReplaceableGroup(1413834416);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1413834416, i10, -1, "androidx.compose.ui.graphics.vector.rememberVectorPainter (VectorPainter.kt:168)");
        }
        Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
        Object objValueOf = Integer.valueOf(imageVector.getGenId());
        composer.startReplaceableGroup(511388516);
        boolean zChanged = composer.changed(objValueOf) | composer.changed(density);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            GroupComponent groupComponent = new GroupComponent();
            createGroupComponent(groupComponent, imageVector.getRoot());
            objRememberedValue = createVectorPainterFromImageVector(density, imageVector, groupComponent);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        VectorPainter vectorPainter = (VectorPainter) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return vectorPainter;
    }

    @e
    /* JADX INFO: renamed from: rememberVectorPainter-mlNsNFs, reason: not valid java name */
    public static final VectorPainter m4055rememberVectorPaintermlNsNFs(float f10, float f11, float f12, float f13, String str, long j10, int i10, r7.r<? super Float, ? super Float, ? super Composer, ? super Integer, t0> rVar, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(-964365210);
        float f14 = (i12 & 4) != 0 ? Float.NaN : f12;
        float f15 = (i12 & 8) != 0 ? Float.NaN : f13;
        String str2 = (i12 & 16) != 0 ? RootGroupName : str;
        long jM3508getUnspecified0d7_KjU = (i12 & 32) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        int iM3418getSrcIn0nO6VwU = (i12 & 64) != 0 ? BlendMode.INSTANCE.m3418getSrcIn0nO6VwU() : i10;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-964365210, i11, -1, "androidx.compose.ui.graphics.vector.rememberVectorPainter (VectorPainter.kt:85)");
        }
        VectorPainter vectorPainterM4056rememberVectorPaintervIP8VLU = m4056rememberVectorPaintervIP8VLU(f10, f11, f14, f15, str2, jM3508getUnspecified0d7_KjU, iM3418getSrcIn0nO6VwU, false, rVar, composer, (i11 & 14) | 12582912 | (i11 & 112) | (i11 & 896) | (i11 & 7168) | (57344 & i11) | (458752 & i11) | (3670016 & i11) | ((i11 << 3) & 234881024), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return vectorPainterM4056rememberVectorPaintervIP8VLU;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00fe  */
    /* JADX INFO: renamed from: rememberVectorPainter-vIP8VLU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.graphics.vector.VectorPainter m4056rememberVectorPaintervIP8VLU(float r16, float r17, float r18, float r19, java.lang.String r20, long r21, int r23, boolean r24, r7.r<? super java.lang.Float, ? super java.lang.Float, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r25, androidx.compose.runtime.Composer r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 326
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.vector.VectorPainterKt.m4056rememberVectorPaintervIP8VLU(float, float, float, float, java.lang.String, long, int, boolean, r7.r, androidx.compose.runtime.Composer, int, int):androidx.compose.ui.graphics.vector.VectorPainter");
    }
}
