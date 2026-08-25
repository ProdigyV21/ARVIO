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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_touchApp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TouchApp", "Landroidx/compose/material/icons/Icons$Rounded;", "getTouchApp", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TouchAppKt {
    private static ImageVector _touchApp;

    public static final ImageVector getTouchApp(Icons.Rounded rounded) {
        ImageVector imageVector = _touchApp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.TouchApp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(8.79f, 9.24f, 5.5f);
        pathBuilderA.curveToRelative(0.0f, -1.38f, 1.12f, -2.5f, 2.5f, -2.5f);
        pathBuilderA.reflectiveCurveToRelative(2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderA.verticalLineToRelative(3.74f);
        pathBuilderA.curveToRelative(1.21f, -0.81f, 2.0f, -2.18f, 2.0f, -3.74f);
        pathBuilderA.curveToRelative(0.0f, -2.49f, -2.01f, -4.5f, -4.5f, -4.5f);
        pathBuilderA.reflectiveCurveToRelative(-4.5f, 2.01f, -4.5f, 4.5f);
        pathBuilderA.curveTo(6.79f, 7.06f, 7.58f, 8.43f, 8.79f, 9.24f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.29f, 11.71f);
        pathBuilderA.curveToRelative(-0.28f, -0.14f, -0.58f, -0.21f, -0.89f, -0.21f);
        pathBuilderA.horizontalLineToRelative(-0.61f);
        pathBuilderA.verticalLineToRelative(-6.0f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(-1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderA.verticalLineToRelative(10.74f);
        pathBuilderA.lineToRelative(-3.44f, -0.72f);
        pathBuilderA.curveToRelative(-0.37f, -0.08f, -0.76f, 0.04f, -1.03f, 0.31f);
        pathBuilderA.curveToRelative(-0.43f, 0.44f, -0.43f, 1.14f, 0.0f, 1.58f);
        pathBuilderA.lineToRelative(4.01f, 4.01f);
        pathBuilderA.curveTo(9.71f, 21.79f, 10.22f, 22.0f, 10.75f, 22.0f);
        pathBuilderA.horizontalLineToRelative(6.1f);
        pathBuilderA.curveToRelative(1.0f, 0.0f, 1.84f, -0.73f, 1.98f, -1.72f);
        pathBuilderA.lineToRelative(0.63f, -4.47f);
        pathBuilderA.curveToRelative(0.12f, -0.85f, -0.32f, -1.69f, -1.09f, -2.07f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 14.29f, 11.71f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _touchApp = imageVectorBuild;
        return imageVectorBuild;
    }
}
