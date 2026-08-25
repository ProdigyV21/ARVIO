package androidx.compose.material.icons.filled;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.a;
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_howToReg", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HowToReg", "Landroidx/compose/material/icons/Icons$Filled;", "getHowToReg", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HowToRegKt {
    private static ImageVector _howToReg;

    public static final ImageVector getHowToReg(Icons.Filled filled) {
        ImageVector imageVector = _howToReg;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.HowToReg", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        PathFillType.Companion companion = PathFillType.INSTANCE;
        int iM3733getEvenOddRgk1Os = companion.m3733getEvenOddRgk1Os();
        Color.Companion companion2 = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion2.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion3 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion3.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion4 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion4.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(9.0f, 17.0f, 3.0f, -2.94f);
        pathBuilderR.curveToRelative(-0.39f, -0.04f, -0.68f, -0.06f, -1.0f, -0.06f);
        pathBuilderR.curveToRelative(-2.67f, 0.0f, -8.0f, 1.34f, -8.0f, 4.0f);
        pathBuilderR.verticalLineToRelative(2.0f);
        pathBuilderR.horizontalLineToRelative(9.0f);
        pathBuilderR.lineToRelative(-3.0f, -3.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(11.0f, 12.0f);
        pathBuilderR.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderR.reflectiveCurveToRelative(-1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilderR.reflectiveCurveToRelative(-4.0f, 1.79f, -4.0f, 4.0f);
        pathBuilderR.reflectiveCurveToRelative(1.79f, 4.0f, 4.0f, 4.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), iM3733getEvenOddRgk1Os, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int iM3733getEvenOddRgk1Os2 = companion.m3733getEvenOddRgk1Os();
        SolidColor solidColor2 = new SolidColor(companion2.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion3.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion4.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(15.47f, 20.5f, 12.0f, 17.0f);
        pathBuilderJ.lineToRelative(1.4f, -1.41f);
        pathBuilderJ.lineToRelative(2.07f, 2.08f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderJ, 5.13f, -5.17f, 1.4f, 1.41f), iM3733getEvenOddRgk1Os2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _howToReg = imageVectorBuild;
        return imageVectorBuild;
    }
}
