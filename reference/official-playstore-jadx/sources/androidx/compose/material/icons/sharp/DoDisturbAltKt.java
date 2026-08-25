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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_doDisturbAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DoDisturbAlt", "Landroidx/compose/material/icons/Icons$Sharp;", "getDoDisturbAlt", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DoDisturbAltKt {
    private static ImageVector _doDisturbAlt;

    public static final ImageVector getDoDisturbAlt(Icons.Sharp sharp) {
        ImageVector imageVector = _doDisturbAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.DoDisturbAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.5f, 2.0f, 2.0f, 6.5f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.5f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.5f, 10.0f, -10.0f);
        pathBuilderA.reflectiveCurveTo(17.5f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(4.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -4.4f, 3.6f, -8.0f, 8.0f, -8.0f);
        pathBuilderA.curveToRelative(1.8f, 0.0f, 3.5f, 0.6f, 4.9f, 1.7f);
        pathBuilderA.lineTo(5.7f, 16.9f);
        pathBuilderA.curveTo(4.6f, 15.5f, 4.0f, 13.8f, 4.0f, 12.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 20.0f);
        pathBuilderA.curveToRelative(-1.8f, 0.0f, -3.5f, -0.6f, -4.9f, -1.7f);
        pathBuilderA.lineTo(18.3f, 7.1f);
        pathBuilderA.curveTo(19.4f, 8.5f, 20.0f, 10.2f, 20.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 4.4f, -3.6f, 8.0f, -8.0f, 8.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _doDisturbAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
