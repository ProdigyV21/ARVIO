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
import v.b;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_castForEducation", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CastForEducation", "Landroidx/compose/material/icons/Icons$Sharp;", "getCastForEducation", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CastForEducationKt {
    private static ImageVector _castForEducation;

    public static final ImageVector getCastForEducation(Icons.Sharp sharp) {
        ImageVector imageVector = _castForEducation;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.CastForEducation", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(23.0f, 3.0f, 1.0f, 3.0f, 5.0f);
        c.A(pathBuilderS, 2.0f, 3.0f, 5.0f, 18.0f);
        b.z(pathBuilderS, 14.0f, -7.0f, 2.0f, 9.0f);
        c.z(pathBuilderS, 23.0f, 3.0f, 1.0f, 18.0f);
        pathBuilderS.verticalLineToRelative(3.0f);
        pathBuilderS.horizontalLineToRelative(3.0f);
        pathBuilderS.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        d.k(pathBuilderS, 1.0f, 14.0f, 2.0f);
        pathBuilderS.curveToRelative(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderS.horizontalLineToRelative(2.0f);
        pathBuilderS.curveToRelative(0.0f, -3.87f, -3.13f, -7.0f, -7.0f, -7.0f);
        d.k(pathBuilderS, 1.0f, 10.0f, 2.0f);
        pathBuilderS.curveToRelative(4.97f, 0.0f, 9.0f, 4.03f, 9.0f, 9.0f);
        pathBuilderS.horizontalLineToRelative(2.0f);
        pathBuilderS.curveToRelative(0.0f, -6.08f, -4.93f, -11.0f, -11.0f, -11.0f);
        d.k(pathBuilderS, 11.0f, 11.09f, 2.0f);
        pathBuilderS.lineTo(14.5f, 15.0f);
        pathBuilderS.lineToRelative(3.5f, -1.91f);
        pathBuilderS.verticalLineToRelative(-2.0f);
        b.y(pathBuilderS, 14.5f, 13.0f, 11.0f, 11.09f);
        pathBuilderS.moveTo(14.5f, 6.0f);
        pathBuilderS.lineTo(9.0f, 9.0f);
        pathBuilderS.lineToRelative(5.5f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderS, 20.0f, 9.0f, -5.5f, -3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _castForEducation = imageVectorBuild;
        return imageVectorBuild;
    }
}
