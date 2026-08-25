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
import v.a;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rotate90DegreesCw", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Rotate90DegreesCw", "Landroidx/compose/material/icons/Icons$Outlined;", "getRotate90DegreesCw", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Rotate90DegreesCwKt {
    private static ImageVector _rotate90DegreesCw;

    public static final ImageVector getRotate90DegreesCw(Icons.Outlined outlined) {
        ImageVector imageVector = _rotate90DegreesCw;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Rotate90DegreesCw", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(2.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 4.97f, 4.03f, 9.0f, 9.0f, 9.0f);
        pathBuilderA.curveToRelative(1.76f, 0.0f, 3.4f, -0.51f, 4.79f, -1.38f);
        pathBuilderA.lineToRelative(-1.46f, -1.46f);
        pathBuilderA.curveTo(13.34f, 19.69f, 12.2f, 20.0f, 11.0f, 20.0f);
        pathBuilderA.curveToRelative(-3.86f, 0.0f, -7.0f, -3.14f, -7.0f, -7.0f);
        pathBuilderA.reflectiveCurveToRelative(3.14f, -7.0f, 7.0f, -7.0f);
        pathBuilderA.horizontalLineToRelative(0.17f);
        pathBuilderA.lineTo(9.59f, 7.59f);
        pathBuilderA.lineTo(11.0f, 9.0f);
        pathBuilderA.lineToRelative(4.0f, -4.0f);
        pathBuilderA.lineToRelative(-4.0f, -4.0f);
        pathBuilderA.lineTo(9.58f, 2.41f);
        pathBuilderA.lineTo(11.17f, 4.0f);
        pathBuilderA.horizontalLineTo(11.0f);
        pathBuilderA.curveTo(6.03f, 4.0f, 2.0f, 8.03f, 2.0f, 13.0f);
        a.z(pathBuilderA, 11.0f, 13.0f, 6.0f, 6.0f);
        pathBuilderA.lineToRelative(6.0f, -6.0f);
        d.C(pathBuilderA, -6.0f, -6.0f, 11.0f, 13.0f);
        pathBuilderA.moveTo(17.0f, 16.17f);
        pathBuilderA.lineTo(13.83f, 13.0f);
        pathBuilderA.lineTo(17.0f, 9.83f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA, 20.17f, 13.0f, 17.0f, 16.17f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rotate90DegreesCw = imageVectorBuild;
        return imageVectorBuild;
    }
}
