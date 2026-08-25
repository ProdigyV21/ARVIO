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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noBackpack", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoBackpack", "Landroidx/compose/material/icons/Icons$Rounded;", "getNoBackpack", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoBackpackKt {
    private static ImageVector _noBackpack;

    public static final ImageVector getNoBackpack(Icons.Rounded rounded) {
        ImageVector imageVector = _noBackpack;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.NoBackpack", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(6.98f, 4.15f);
        pathBuilderA.curveToRelative(0.01f, 0.0f, 0.01f, -0.01f, 0.02f, -0.01f);
        pathBuilderA.verticalLineTo(3.5f);
        pathBuilderA.curveTo(7.0f, 2.67f, 7.67f, 2.0f, 8.5f, 2.0f);
        pathBuilderA.reflectiveCurveTo(10.0f, 2.67f, 10.0f, 3.5f);
        c.i(pathBuilderA, 4.0f, 4.0f, 3.5f);
        pathBuilderA.curveTo(14.0f, 2.67f, 14.67f, 2.0f, 15.5f, 2.0f);
        pathBuilderA.reflectiveCurveTo(17.0f, 2.67f, 17.0f, 3.5f);
        pathBuilderA.verticalLineToRelative(0.64f);
        pathBuilderA.curveToRelative(1.72f, 0.45f, 3.0f, 2.0f, 3.0f, 3.86f);
        pathBuilderA.verticalLineToRelative(9.17f);
        pathBuilderA.lineToRelative(-2.03f, -2.03f);
        pathBuilderA.curveTo(17.98f, 15.09f, 18.0f, 15.05f, 18.0f, 15.0f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        d.q(pathBuilderA, -2.17f, 6.98f, 4.15f);
        pathBuilderA.moveTo(20.49f, 21.9f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderA.lineToRelative(-0.14f, -0.14f);
        pathBuilderA.curveTo(18.65f, 21.91f, 18.34f, 22.0f, 18.0f, 22.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.verticalLineTo(8.0f);
        pathBuilderA.curveToRelative(0.0f, -0.36f, 0.06f, -0.69f, 0.15f, -1.02f);
        pathBuilderA.lineTo(2.1f, 4.93f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderA.lineToRelative(16.97f, 16.97f);
        pathBuilderA.curveTo(20.88f, 20.88f, 20.88f, 21.51f, 20.49f, 21.9f);
        a.z(pathBuilderA, 11.17f, 14.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(7.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderA, 11.17f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noBackpack = imageVectorBuild;
        return imageVectorBuild;
    }
}
