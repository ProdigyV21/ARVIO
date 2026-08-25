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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sportsEsports", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SportsEsports", "Landroidx/compose/material/icons/Icons$Rounded;", "getSportsEsports", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SportsEsportsKt {
    private static ImageVector _sportsEsports;

    public static final ImageVector getSportsEsports(Icons.Rounded rounded) {
        ImageVector imageVector = _sportsEsports;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SportsEsports", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.58f, 16.09f, -1.09f, -7.66f);
        pathBuilderR.curveTo(20.21f, 6.46f, 18.52f, 5.0f, 16.53f, 5.0f);
        pathBuilderR.horizontalLineTo(7.47f);
        pathBuilderR.curveTo(5.48f, 5.0f, 3.79f, 6.46f, 3.51f, 8.43f);
        pathBuilderR.lineToRelative(-1.09f, 7.66f);
        pathBuilderR.curveTo(2.2f, 17.63f, 3.39f, 19.0f, 4.94f, 19.0f);
        pathBuilderR.horizontalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(0.68f, 0.0f, 1.32f, -0.27f, 1.8f, -0.75f);
        pathBuilderR.lineTo(9.0f, 16.0f);
        pathBuilderR.horizontalLineToRelative(6.0f);
        pathBuilderR.lineToRelative(2.25f, 2.25f);
        pathBuilderR.curveToRelative(0.48f, 0.48f, 1.13f, 0.75f, 1.8f, 0.75f);
        pathBuilderR.horizontalLineToRelative(0.0f);
        pathBuilderR.curveTo(20.61f, 19.0f, 21.8f, 17.63f, 21.58f, 16.09f);
        c.C(pathBuilderR, 11.0f, 11.0f, 9.0f, 2.0f);
        w.a.A(pathBuilderR, 8.0f, -2.0f, 6.0f, -1.0f);
        w.a.C(pathBuilderR, 2.0f, 8.0f, 1.0f, 2.0f);
        c.D(pathBuilderR, 2.0f, 11.0f, 15.0f, 10.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR.curveTo(16.0f, 9.55f, 15.55f, 10.0f, 15.0f, 10.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(17.0f, 13.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR.curveTo(18.0f, 12.55f, 17.55f, 13.0f, 17.0f, 13.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sportsEsports = imageVectorBuild;
        return imageVectorBuild;
    }
}
