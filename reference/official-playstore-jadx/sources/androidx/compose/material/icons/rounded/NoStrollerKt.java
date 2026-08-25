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
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noStroller", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoStroller", "Landroidx/compose/material/icons/Icons$Rounded;", "getNoStroller", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoStrollerKt {
    private static ImageVector _noStroller;

    public static final ImageVector getNoStroller(Icons.Rounded rounded) {
        ImageVector imageVector = _noStroller;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.NoStroller", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.0f, 20.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveTo(8.0f, 18.9f, 8.0f, 20.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.3f, 4.1f);
        pathBuilderA.curveTo(13.03f, 3.4f, 11.56f, 3.0f, 10.0f, 3.0f);
        pathBuilderA.curveTo(8.77f, 3.0f, 7.61f, 3.26f, 6.54f, 3.71f);
        d.C(pathBuilderA, 4.37f, 4.37f, 14.3f, 4.1f);
        pathBuilderA.moveTo(20.49f, 21.9f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.lineToRelative(-9.82f, -9.82f);
        pathBuilderA.lineTo(3.51f, 3.51f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineToRelative(7.26f, 7.26f);
        pathBuilderA.lineToRelative(-2.66f, 3.12f);
        pathBuilderA.curveToRelative(-0.55f, 0.65f, -0.09f, 1.65f, 0.76f, 1.65f);
        pathBuilderA.horizontalLineToRelative(6.66f);
        pathBuilderA.lineToRelative(1.17f, 1.17f);
        pathBuilderA.curveToRelative(-0.88f, 0.33f, -1.47f, 1.25f, -1.26f, 2.28f);
        pathBuilderA.curveToRelative(0.15f, 0.76f, 0.78f, 1.39f, 1.54f, 1.54f);
        pathBuilderA.curveToRelative(1.03f, 0.21f, 1.95f, -0.38f, 2.28f, -1.26f);
        pathBuilderA.lineToRelative(1.2f, 1.2f);
        pathBuilderA.curveTo(19.46f, 22.29f, 20.09f, 22.29f, 20.49f, 21.9f);
        c.z(pathBuilderA, 20.49f, 21.9f, 17.0f, 6.27f);
        pathBuilderA.curveTo(17.58f, 5.59f, 17.97f, 5.0f, 18.65f, 5.0f);
        pathBuilderA.curveToRelative(0.68f, 0.0f, 1.22f, 0.52f, 1.33f, 1.21f);
        pathBuilderA.curveTo(20.08f, 6.66f, 20.48f, 7.0f, 20.96f, 7.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.06f, 0.0f, -0.11f, -0.01f, -0.16f);
        pathBuilderA.lineToRelative(0.0f, -0.01f);
        pathBuilderA.curveTo(21.65f, 4.22f, 20.3f, 3.0f, 18.65f, 3.0f);
        pathBuilderA.curveToRelative(-1.66f, 0.0f, -2.54f, 1.27f, -3.18f, 2.03f);
        pathBuilderA.lineToRelative(-3.5f, 4.11f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.e(pathBuilderA, 17.0f, 14.17f, 6.27f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noStroller = imageVectorBuild;
        return imageVectorBuild;
    }
}
