package androidx.compose.material.icons.twotone;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.a;
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_contentPasteSearch", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ContentPasteSearch", "Landroidx/compose/material/icons/Icons$TwoTone;", "getContentPasteSearch", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContentPasteSearchKt {
    private static ImageVector _contentPasteSearch;

    public static final ImageVector getContentPasteSearch(Icons.TwoTone twoTone) {
        ImageVector imageVector = _contentPasteSearch;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ContentPasteSearch", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(10.0f, 16.5f);
        pathBuilderA.curveToRelative(0.0f, -3.58f, 2.92f, -6.5f, 6.5f, -6.5f);
        pathBuilderA.curveToRelative(0.89f, 0.0f, 1.73f, 0.18f, 2.5f, 0.5f);
        e.x(pathBuilderA, 5.0f, -2.0f, 3.0f, 7.0f);
        c.o(pathBuilderA, 5.0f, 5.0f, 14.0f, 5.5f);
        pathBuilderA.curveTo(10.18f, 18.23f, 10.0f, 17.39f, 10.0f, 16.5f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = a.c(10.5f, 19.0f, 5.0f, 5.0f, 2.0f);
        a.y(pathBuilderC, 3.0f, 10.0f, 5.0f, 2.0f);
        pathBuilderC.verticalLineToRelative(5.5f);
        pathBuilderC.curveToRelative(0.75f, 0.31f, 1.42f, 0.76f, 2.0f, 1.32f);
        pathBuilderC.verticalLineTo(5.0f);
        pathBuilderC.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderC.horizontalLineToRelative(-4.18f);
        pathBuilderC.curveTo(14.4f, 1.84f, 13.3f, 1.0f, 12.0f, 1.0f);
        pathBuilderC.reflectiveCurveTo(9.6f, 1.84f, 9.18f, 3.0f);
        pathBuilderC.horizontalLineTo(5.0f);
        pathBuilderC.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderC.verticalLineToRelative(14.0f);
        pathBuilderC.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderC.horizontalLineToRelative(6.82f);
        pathBuilderC.curveTo(11.27f, 20.42f, 10.81f, 19.75f, 10.5f, 19.0f);
        pathBuilderC.close();
        pathBuilderC.moveTo(12.0f, 3.0f);
        pathBuilderC.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderC.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderC.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderC, 11.45f, 3.0f, 12.0f, 3.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(20.3f, 18.9f);
        pathBuilderA2.curveToRelative(0.4f, -0.7f, 0.7f, -1.5f, 0.7f, -2.4f);
        pathBuilderA2.curveToRelative(0.0f, -2.5f, -2.0f, -4.5f, -4.5f, -4.5f);
        pathBuilderA2.reflectiveCurveTo(12.0f, 14.0f, 12.0f, 16.5f);
        pathBuilderA2.reflectiveCurveToRelative(2.0f, 4.5f, 4.5f, 4.5f);
        pathBuilderA2.curveToRelative(0.9f, 0.0f, 1.7f, -0.3f, 2.4f, -0.7f);
        pathBuilderA2.lineToRelative(2.7f, 2.7f);
        d.C(pathBuilderA2, 1.4f, -1.4f, 20.3f, 18.9f);
        pathBuilderA2.moveTo(16.5f, 19.0f);
        pathBuilderA2.curveToRelative(-1.4f, 0.0f, -2.5f, -1.1f, -2.5f, -2.5f);
        pathBuilderA2.curveToRelative(0.0f, -1.4f, 1.1f, -2.5f, 2.5f, -2.5f);
        pathBuilderA2.reflectiveCurveToRelative(2.5f, 1.1f, 2.5f, 2.5f);
        pathBuilderA2.curveTo(19.0f, 17.9f, 17.9f, 19.0f, 16.5f, 19.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _contentPasteSearch = imageVectorBuild;
        return imageVectorBuild;
    }
}
