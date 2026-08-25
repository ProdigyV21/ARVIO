package androidx.compose.ui.graphics.vector;

import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u0087\u0001\u0010\u0012\u001a\u00020\u000e2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001a¦\u0001\u0010'\u001a\u00020\u000e2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u00022\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u001b\u001a\u00020\u00022\b\b\u0002\u0010\u001c\u001a\u00020\u00022\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010!\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020\u00022\b\b\u0002\u0010#\u001a\u00020\u00022\b\b\u0002\u0010$\u001a\u00020\u0002H\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006("}, d2 = {"", ContentDisposition.Parameters.Name, "", "rotation", "pivotX", "pivotY", "scaleX", "scaleY", "translationX", "translationY", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "clipPathData", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/ui/graphics/vector/VectorComposable;", "content", "Group", "(Ljava/lang/String;FFFFFFFLjava/util/List;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "pathData", "Landroidx/compose/ui/graphics/PathFillType;", "pathFillType", "Landroidx/compose/ui/graphics/Brush;", "fill", "fillAlpha", "stroke", "strokeAlpha", "strokeLineWidth", "Landroidx/compose/ui/graphics/StrokeCap;", "strokeLineCap", "Landroidx/compose/ui/graphics/StrokeJoin;", "strokeLineJoin", "strokeLineMiter", "trimPathStart", "trimPathEnd", "trimPathOffset", "Path-9cdaXJ4", "(Ljava/util/List;ILjava/lang/String;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/Brush;FFIIFFFFLandroidx/compose/runtime/Composer;III)V", "Path", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VectorComposeKt {

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/ui/graphics/vector/GroupComponent;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements r7.a<GroupComponent> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final GroupComponent invoke() {
            return new GroupComponent();
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.vector.VectorComposeKt$Group$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ List<PathNode> $clipPathData;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ String $name;
        final /* synthetic */ float $pivotX;
        final /* synthetic */ float $pivotY;
        final /* synthetic */ float $rotation;
        final /* synthetic */ float $scaleX;
        final /* synthetic */ float $scaleY;
        final /* synthetic */ float $translationX;
        final /* synthetic */ float $translationY;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(String str, float f10, float f11, float f12, float f13, float f14, float f15, float f16, List<? extends PathNode> list, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
            super(2);
            this.$name = str;
            this.$rotation = f10;
            this.$pivotX = f11;
            this.$pivotY = f12;
            this.$scaleX = f13;
            this.$scaleY = f14;
            this.$translationX = f15;
            this.$translationY = f16;
            this.$clipPathData = list;
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
            VectorComposeKt.Group(this.$name, this.$rotation, this.$pivotX, this.$pivotY, this.$scaleX, this.$scaleY, this.$translationX, this.$translationY, this.$clipPathData, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:163:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Group(java.lang.String r21, float r22, float r23, float r24, float r25, float r26, float r27, float r28, java.util.List<? extends androidx.compose.ui.graphics.vector.PathNode> r29, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 600
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.vector.VectorComposeKt.Group(java.lang.String, float, float, float, float, float, float, float, java.util.List, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: renamed from: Path-9cdaXJ4, reason: not valid java name */
    public static final void m4038Path9cdaXJ4(List<? extends PathNode> list, int i10, String str, Brush brush, float f10, Brush brush2, float f11, float f12, int i11, int i12, float f13, float f14, float f15, float f16, Composer composer, int i13, int i14, int i15) {
        int i16;
        float f17;
        int i17;
        int i18;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1478270750);
        int defaultFillType = (i15 & 2) != 0 ? VectorKt.getDefaultFillType() : i10;
        String str2 = (i15 & 4) != 0 ? "" : str;
        Brush brush3 = (i15 & 8) != 0 ? null : brush;
        float f18 = (i15 & 16) != 0 ? 1.0f : f10;
        Brush brush4 = (i15 & 32) == 0 ? brush2 : null;
        float f19 = (i15 & 64) != 0 ? 1.0f : f11;
        float f20 = (i15 & 128) != 0 ? 0.0f : f12;
        int defaultStrokeLineCap = (i15 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : i11;
        int defaultStrokeLineJoin = (i15 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : i12;
        float f21 = (i15 & 1024) != 0 ? 4.0f : f13;
        float f22 = (i15 & 2048) != 0 ? 0.0f : f14;
        float f23 = (i15 & 4096) != 0 ? 1.0f : f15;
        float f24 = (i15 & 8192) != 0 ? 0.0f : f16;
        if (ComposerKt.isTraceInProgress()) {
            i16 = defaultFillType;
            f17 = f18;
            i17 = i13;
            i18 = i14;
            ComposerKt.traceEventStart(-1478270750, i17, i18, "androidx.compose.ui.graphics.vector.Path (VectorCompose.kt:114)");
        } else {
            i16 = defaultFillType;
            f17 = f18;
            i17 = i13;
            i18 = i14;
        }
        VectorComposeKt$Path$1 vectorComposeKt$Path$1 = VectorComposeKt$Path$1.INSTANCE;
        composerStartRestartGroup.startReplaceableGroup(1886828752);
        if (!(composerStartRestartGroup.getApplier() instanceof VectorApplier)) {
            ComposablesKt.invalidApplier();
        }
        composerStartRestartGroup.startNode();
        if (composerStartRestartGroup.getInserting()) {
            composerStartRestartGroup.createNode(new VectorComposeKt$Path9cdaXJ4$$inlined$ComposeNode$1(vectorComposeKt$Path$1));
        } else {
            composerStartRestartGroup.useNode();
        }
        Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
        Updater.m2998setimpl(composerM2991constructorimpl, str2, VectorComposeKt$Path$2$1.INSTANCE);
        Updater.m2998setimpl(composerM2991constructorimpl, list, VectorComposeKt$Path$2$2.INSTANCE);
        Updater.m2998setimpl(composerM2991constructorimpl, PathFillType.m3726boximpl(i16), VectorComposeKt$Path$2$3.INSTANCE);
        Updater.m2998setimpl(composerM2991constructorimpl, brush3, VectorComposeKt$Path$2$4.INSTANCE);
        Updater.m2998setimpl(composerM2991constructorimpl, Float.valueOf(f17), VectorComposeKt$Path$2$5.INSTANCE);
        Updater.m2998setimpl(composerM2991constructorimpl, brush4, VectorComposeKt$Path$2$6.INSTANCE);
        Updater.m2998setimpl(composerM2991constructorimpl, Float.valueOf(f19), VectorComposeKt$Path$2$7.INSTANCE);
        Updater.m2998setimpl(composerM2991constructorimpl, Float.valueOf(f20), VectorComposeKt$Path$2$8.INSTANCE);
        Updater.m2998setimpl(composerM2991constructorimpl, StrokeJoin.m3806boximpl(defaultStrokeLineJoin), VectorComposeKt$Path$2$9.INSTANCE);
        Updater.m2998setimpl(composerM2991constructorimpl, StrokeCap.m3796boximpl(defaultStrokeLineCap), VectorComposeKt$Path$2$10.INSTANCE);
        Updater.m2998setimpl(composerM2991constructorimpl, Float.valueOf(f21), VectorComposeKt$Path$2$11.INSTANCE);
        Updater.m2998setimpl(composerM2991constructorimpl, Float.valueOf(f22), VectorComposeKt$Path$2$12.INSTANCE);
        Updater.m2998setimpl(composerM2991constructorimpl, Float.valueOf(f23), VectorComposeKt$Path$2$13.INSTANCE);
        Updater.m2998setimpl(composerM2991constructorimpl, Float.valueOf(f24), VectorComposeKt$Path$2$14.INSTANCE);
        composerStartRestartGroup.endNode();
        composerStartRestartGroup.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            int i19 = i18;
            scopeUpdateScopeEndRestartGroup.updateScope(new VectorComposeKt$Path$3(list, i16, str2, brush3, f17, brush4, f19, f20, defaultStrokeLineCap, defaultStrokeLineJoin, f21, f22, f23, f24, i17, i19, i15));
        }
    }
}
