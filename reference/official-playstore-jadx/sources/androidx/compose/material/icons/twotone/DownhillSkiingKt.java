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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_downhillSkiing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DownhillSkiing", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDownhillSkiing", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DownhillSkiingKt {
    private static ImageVector _downhillSkiing;

    public static final ImageVector getDownhillSkiing(Icons.TwoTone twoTone) {
        ImageVector imageVector = _downhillSkiing;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.DownhillSkiing", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.5f, 4.5f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveTo(18.5f, 3.4f, 18.5f, 4.5f);
        a.z(pathBuilderA, 15.78f, 20.9f, 0.76f, 0.27f);
        pathBuilderA.curveToRelative(0.62f, 0.21f, 1.27f, 0.33f, 1.96f, 0.33f);
        pathBuilderA.curveToRelative(0.84f, 0.0f, 1.65f, -0.18f, 2.38f, -0.5f);
        pathBuilderA.lineTo(22.0f, 22.13f);
        pathBuilderA.curveTo(20.95f, 22.68f, 19.76f, 23.0f, 18.5f, 23.0f);
        pathBuilderA.curveToRelative(-0.86f, 0.0f, -1.68f, -0.14f, -2.45f, -0.41f);
        pathBuilderA.lineTo(2.0f, 17.47f);
        pathBuilderA.lineToRelative(0.5f, -1.41f);
        pathBuilderA.lineToRelative(6.9f, 2.51f);
        pathBuilderA.lineToRelative(1.72f, -4.44f);
        pathBuilderA.lineTo(7.55f, 10.4f);
        pathBuilderA.curveTo(6.66f, 9.46f, 6.88f, 7.93f, 8.0f, 7.28f);
        pathBuilderA.lineToRelative(3.48f, -2.01f);
        pathBuilderA.curveToRelative(1.1f, -0.64f, 2.52f, -0.1f, 2.91f, 1.11f);
        pathBuilderA.lineToRelative(0.33f, 1.08f);
        pathBuilderA.curveToRelative(0.44f, 1.42f, 1.48f, 2.57f, 2.83f, 3.14f);
        pathBuilderA.lineTo(18.07f, 9.0f);
        pathBuilderA.lineToRelative(1.43f, 0.46f);
        pathBuilderA.lineToRelative(-1.12f, 3.45f);
        pathBuilderA.curveToRelative(-2.45f, -0.4f, -4.48f, -2.07f, -5.38f, -4.32f);
        pathBuilderA.lineToRelative(-2.53f, 1.45f);
        pathBuilderA.lineToRelative(3.03f, 3.46f);
        pathBuilderA.lineToRelative(-2.22f, 5.76f);
        pathBuilderA.lineToRelative(3.09f, 1.12f);
        pathBuilderA.lineToRelative(2.1f, -6.44f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.46f, 0.18f, 0.94f, 0.31f, 1.44f, 0.41f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 15.78f, 20.9f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _downhillSkiing = imageVectorBuild;
        return imageVectorBuild;
    }
}
