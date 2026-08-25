package androidx.compose.material.icons.outlined;

import a0.a;
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
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_contentCut", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ContentCut", "Landroidx/compose/material/icons/Icons$Outlined;", "getContentCut", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContentCutKt {
    private static ImageVector _contentCut;

    public static final ImageVector getContentCut(Icons.Outlined outlined) {
        ImageVector imageVector = _contentCut;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.ContentCut", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.64f, 7.64f);
        pathBuilderA.curveToRelative(0.23f, -0.5f, 0.36f, -1.05f, 0.36f, -1.64f);
        pathBuilderA.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilderA.reflectiveCurveTo(2.0f, 3.79f, 2.0f, 6.0f);
        pathBuilderA.reflectiveCurveToRelative(1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderA.curveToRelative(0.59f, 0.0f, 1.14f, -0.13f, 1.64f, -0.36f);
        pathBuilderA.lineTo(10.0f, 12.0f);
        pathBuilderA.lineToRelative(-2.36f, 2.36f);
        pathBuilderA.curveTo(7.14f, 14.13f, 6.59f, 14.0f, 6.0f, 14.0f);
        pathBuilderA.curveToRelative(-2.21f, 0.0f, -4.0f, 1.79f, -4.0f, 4.0f);
        pathBuilderA.reflectiveCurveToRelative(1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderA.reflectiveCurveToRelative(4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA.curveToRelative(0.0f, -0.59f, -0.13f, -1.14f, -0.36f, -1.64f);
        pathBuilderA.lineTo(12.0f, 14.0f);
        a.m(pathBuilderA, 7.0f, 7.0f, 3.0f, -1.0f);
        c.z(pathBuilderA, 9.64f, 7.64f, 6.0f, 8.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.89f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.89f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(6.0f, 20.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.89f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.89f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 12.5f);
        pathBuilderA.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.22f, 0.5f, -0.5f, 0.5f);
        v.a.z(pathBuilderA, 19.0f, 3.0f, -6.0f, 6.0f);
        pathBuilderA.lineToRelative(2.0f, 2.0f);
        pathBuilderA.lineToRelative(7.0f, -7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.d(pathBuilderA, 22.0f, 3.0f, -3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _contentCut = imageVectorBuild;
        return imageVectorBuild;
    }
}
