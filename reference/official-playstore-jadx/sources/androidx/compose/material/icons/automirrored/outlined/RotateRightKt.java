package androidx.compose.material.icons.automirrored.outlined;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rotateRight", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RotateRight", "Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;", "getRotateRight", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RotateRightKt {
    private static ImageVector _rotateRight;

    public static final ImageVector getRotateRight(Icons.AutoMirrored.Outlined outlined) {
        ImageVector imageVector = _rotateRight;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Outlined.RotateRight", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(15.55f, 5.55f, 11.0f, 1.0f, 3.07f);
        pathBuilderS.curveTo(7.06f, 4.56f, 4.0f, 7.92f, 4.0f, 12.0f);
        pathBuilderS.reflectiveCurveToRelative(3.05f, 7.44f, 7.0f, 7.93f);
        pathBuilderS.verticalLineToRelative(-2.02f);
        pathBuilderS.curveToRelative(-2.84f, -0.48f, -5.0f, -2.94f, -5.0f, -5.91f);
        pathBuilderS.reflectiveCurveToRelative(2.16f, -5.43f, 5.0f, -5.91f);
        d.l(pathBuilderS, 11.0f, 10.0f, 4.55f, -4.45f);
        pathBuilderS.moveTo(19.93f, 11.0f);
        pathBuilderS.curveToRelative(-0.17f, -1.39f, -0.72f, -2.73f, -1.62f, -3.89f);
        pathBuilderS.lineToRelative(-1.42f, 1.42f);
        pathBuilderS.curveToRelative(0.54f, 0.75f, 0.88f, 1.6f, 1.02f, 2.47f);
        pathBuilderS.horizontalLineToRelative(2.02f);
        pathBuilderS.close();
        pathBuilderS.moveTo(13.0f, 17.9f);
        pathBuilderS.verticalLineToRelative(2.02f);
        pathBuilderS.curveToRelative(1.39f, -0.17f, 2.74f, -0.71f, 3.9f, -1.61f);
        pathBuilderS.lineToRelative(-1.44f, -1.44f);
        pathBuilderS.curveToRelative(-0.75f, 0.54f, -1.59f, 0.89f, -2.46f, 1.03f);
        a.z(pathBuilderS, 16.89f, 15.48f, 1.42f, 1.41f);
        pathBuilderS.curveToRelative(0.9f, -1.16f, 1.45f, -2.5f, 1.62f, -3.89f);
        pathBuilderS.horizontalLineToRelative(-2.02f);
        pathBuilderS.curveToRelative(-0.14f, 0.87f, -0.48f, 1.72f, -1.02f, 2.48f);
        pathBuilderS.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderS.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rotateRight = imageVectorBuild;
        return imageVectorBuild;
    }
}
