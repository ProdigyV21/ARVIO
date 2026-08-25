package androidx.compose.material.icons.sharp;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_personalInjury", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PersonalInjury", "Landroidx/compose/material/icons/Icons$Sharp;", "getPersonalInjury", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PersonalInjuryKt {
    private static ImageVector _personalInjury;

    public static final ImageVector getPersonalInjury(Icons.Sharp sharp) {
        ImageVector imageVector = _personalInjury;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.PersonalInjury", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, -2.21f, 1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilderA.reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 2.21f, -1.79f, 4.0f, -4.0f, 4.0f);
        pathBuilderA.reflectiveCurveTo(8.0f, 8.21f, 8.0f, 6.0f);
        a.g(pathBuilderA, 17.0f, 22.0f, 1.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.lineToRelative(0.0f, -4.78f);
        pathBuilderA.curveToRelative(0.0f, -1.12f, -0.61f, -2.15f, -1.61f, -2.66f);
        pathBuilderA.curveToRelative(-0.43f, -0.22f, -0.9f, -0.43f, -1.39f, -0.62f);
        c.z(pathBuilderA, 17.0f, 22.0f, 12.34f, 17.0f);
        pathBuilderA.lineTo(15.0f, 11.33f);
        pathBuilderA.curveTo(14.07f, 11.12f, 13.07f, 11.0f, 12.0f, 11.0f);
        pathBuilderA.curveToRelative(-2.53f, 0.0f, -4.71f, 0.7f, -6.39f, 1.56f);
        pathBuilderA.curveTo(4.61f, 13.07f, 4.0f, 14.1f, 4.0f, 15.22f);
        pathBuilderA.lineTo(4.0f, 22.0f);
        pathBuilderA.horizontalLineToRelative(2.34f);
        pathBuilderA.curveTo(6.12f, 21.55f, 6.0f, 21.04f, 6.0f, 20.5f);
        pathBuilderA.curveTo(6.0f, 18.57f, 7.57f, 17.0f, 9.5f, 17.0f);
        a.x(pathBuilderA, 12.34f, 10.0f, 22.0f);
        pathBuilderA.lineToRelative(1.41f, -3.0f);
        pathBuilderA.horizontalLineTo(9.5f);
        pathBuilderA.curveTo(8.67f, 19.0f, 8.0f, 19.67f, 8.0f, 20.5f);
        pathBuilderA.reflectiveCurveTo(8.67f, 22.0f, 9.5f, 22.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderA, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _personalInjury = imageVectorBuild;
        return imageVectorBuild;
    }
}
