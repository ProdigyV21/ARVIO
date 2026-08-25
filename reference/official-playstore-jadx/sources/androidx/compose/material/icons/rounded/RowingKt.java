package androidx.compose.material.icons.rounded;

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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rowing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Rowing", "Landroidx/compose/material/icons/Icons$Rounded;", "getRowing", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RowingKt {
    private static ImageVector _rowing;

    public static final ImageVector getRowing(Icons.Rounded rounded) {
        ImageVector imageVector = _rowing;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Rowing", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(4.75f, 18.25f);
        pathBuilderA.curveToRelative(-0.41f, 0.41f, -0.41f, 1.09f, 0.0f, 1.5f);
        pathBuilderA.curveToRelative(0.41f, 0.41f, 1.09f, 0.41f, 1.5f, 0.0f);
        pathBuilderA.lineTo(9.0f, 17.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        b.D(pathBuilderA, -2.5f, -2.5f, -3.75f, 3.75f);
        pathBuilderA.moveTo(15.0f, 5.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        a.z(pathBuilderA, 20.29f, 20.3f, -2.0f, -2.01f);
        pathBuilderA.curveToRelative(-0.18f, -0.18f, -0.44f, -0.29f, -0.71f, -0.29f);
        pathBuilderA.lineTo(16.5f, 18.0f);
        pathBuilderA.lineToRelative(-6.29f, -6.29f);
        pathBuilderA.curveToRelative(0.79f, -0.33f, 1.66f, -0.87f, 2.29f, -1.39f);
        pathBuilderA.verticalLineToRelative(2.27f);
        pathBuilderA.lineToRelative(3.58f, 3.58f);
        pathBuilderA.curveToRelative(0.57f, -0.55f, 0.92f, -1.32f, 0.92f, -2.16f);
        pathBuilderA.lineTo(17.0f, 8.26f);
        pathBuilderA.curveTo(17.0f, 7.02f, 15.98f, 6.0f, 14.74f, 6.0f);
        pathBuilderA.horizontalLineToRelative(-0.02f);
        pathBuilderA.curveToRelative(-0.34f, 0.0f, -0.67f, 0.09f, -0.96f, 0.23f);
        pathBuilderA.curveToRelative(-0.26f, 0.12f, -0.5f, 0.29f, -0.69f, 0.5f);
        pathBuilderA.lineToRelative(-1.4f, 1.55f);
        pathBuilderA.curveTo(10.61f, 9.45f, 8.66f, 10.35f, 7.0f, 10.32f);
        pathBuilderA.curveToRelative(-0.6f, 0.0f, -1.08f, 0.48f, -1.08f, 1.08f);
        pathBuilderA.curveToRelative(0.0f, 0.6f, 0.48f, 1.08f, 1.08f, 1.08f);
        pathBuilderA.curveToRelative(0.31f, 0.0f, 0.61f, -0.03f, 0.9f, -0.07f);
        pathBuilderA.lineToRelative(7.11f, 7.09f);
        pathBuilderA.verticalLineToRelative(1.08f);
        pathBuilderA.curveToRelative(0.0f, 0.26f, 0.1f, 0.52f, 0.29f, 0.7f);
        pathBuilderA.lineToRelative(1.99f, 2.01f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.42f, 0.0f);
        pathBuilderA.lineToRelative(1.58f, -1.58f);
        pathBuilderA.curveToRelative(0.39f, -0.38f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rowing = imageVectorBuild;
        return imageVectorBuild;
    }
}
