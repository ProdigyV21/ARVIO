package androidx.compose.material.icons.outlined;

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
import v.b;
import v.c;
import v.d;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_paragliding", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Paragliding", "Landroidx/compose/material/icons/Icons$Outlined;", "getParagliding", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ParaglidingKt {
    private static ImageVector _paragliding;

    public static final ImageVector getParagliding(Icons.Outlined outlined) {
        ImageVector imageVector = _paragliding;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Paragliding", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 17.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.curveTo(14.0f, 16.1f, 13.1f, 17.0f, 12.0f, 17.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.48f, 17.94f);
        pathBuilderA.curveTo(14.68f, 18.54f, 14.0f, 19.0f, 12.0f, 19.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.68f, -0.46f, -3.48f, -1.06f);
        pathBuilderA.curveTo(8.04f, 17.55f, 7.0f, 16.76f, 7.0f, 14.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveToRelative(0.0f, 2.7f, 0.93f, 4.41f, 2.3f, 5.5f);
        pathBuilderA.curveToRelative(0.5f, 0.4f, 1.1f, 0.7f, 1.7f, 0.9f);
        a.j(pathBuilderA, 24.0f, 6.0f, -3.6f);
        pathBuilderA.curveToRelative(0.6f, -0.2f, 1.2f, -0.5f, 1.7f, -0.9f);
        pathBuilderA.curveToRelative(1.37f, -1.09f, 2.3f, -2.8f, 2.3f, -5.5f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.curveTo(17.0f, 16.76f, 15.96f, 17.55f, 15.48f, 17.94f);
        d.k(pathBuilderA, 23.0f, 4.25f, 3.49f);
        pathBuilderA.curveToRelative(0.0f, 0.8f, -0.88f, 1.26f, -1.56f, 0.83f);
        pathBuilderA.curveTo(21.3f, 8.48f, 21.16f, 8.39f, 21.0f, 8.31f);
        pathBuilderA.lineTo(19.0f, 13.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.lineToRelative(-1.5f, -6.28f);
        pathBuilderA.curveTo(14.4f, 6.58f, 13.22f, 6.5f, 12.0f, 6.5f);
        pathBuilderA.reflectiveCurveTo(9.6f, 6.58f, 8.5f, 6.72f);
        pathBuilderA.lineTo(7.0f, 13.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.lineTo(3.0f, 8.31f);
        pathBuilderA.curveTo(2.84f, 8.39f, 2.7f, 8.48f, 2.56f, 8.57f);
        pathBuilderA.curveTo(1.88f, 9.0f, 1.0f, 8.55f, 1.0f, 7.74f);
        pathBuilderA.verticalLineTo(4.25f);
        pathBuilderA.curveTo(1.0f, 1.9f, 5.92f, 0.0f, 12.0f, 0.0f);
        pathBuilderA.reflectiveCurveTo(23.0f, 1.9f, 23.0f, 4.25f);
        pathBuilderA.close();
        pathBuilderA.moveTo(6.9f, 6.98f);
        pathBuilderA.curveTo(5.97f, 7.17f, 5.12f, 7.41f, 4.37f, 7.69f);
        d.C(pathBuilderA, 1.51f, 3.55f, 6.9f, 6.98f);
        pathBuilderA.moveTo(19.63f, 7.69f);
        pathBuilderA.curveToRelative(-0.75f, -0.28f, -1.6f, -0.52f, -2.53f, -0.71f);
        d.C(pathBuilderA, 1.02f, 4.25f, 19.63f, 7.69f);
        pathBuilderA.moveTo(21.0f, 4.31f);
        pathBuilderA.curveTo(20.65f, 3.63f, 17.57f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(3.35f, 3.63f, 3.0f, 4.31f);
        pathBuilderA.verticalLineToRelative(1.77f);
        pathBuilderA.curveTo(5.34f, 5.07f, 8.56f, 4.5f, 12.0f, 4.5f);
        pathBuilderA.reflectiveCurveToRelative(6.66f, 0.57f, 9.0f, 1.58f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA, 4.31f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _paragliding = imageVectorBuild;
        return imageVectorBuild;
    }
}
