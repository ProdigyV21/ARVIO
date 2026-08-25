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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_airlines", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Airlines", "Landroidx/compose/material/icons/Icons$Rounded;", "getAirlines", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AirlinesKt {
    private static ImageVector _airlines;

    public static final ImageVector getAirlines(Icons.Rounded rounded) {
        ImageVector imageVector = _airlines;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Airlines", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(19.59f, 4.0f, -5.01f);
        pathBuilderA.curveToRelative(-0.99f, 0.0f, -1.91f, 0.49f, -2.47f, 1.3f);
        pathBuilderA.lineTo(2.0f, 20.0f);
        pathBuilderA.horizontalLineToRelative(17.0f);
        pathBuilderA.lineToRelative(2.56f, -13.63f);
        pathBuilderA.curveTo(21.79f, 5.14f, 20.84f, 4.0f, 19.59f, 4.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.5f, 14.0f);
        pathBuilderA.curveToRelative(-1.38f, 0.0f, -2.5f, -1.12f, -2.5f, -2.5f);
        pathBuilderA.curveToRelative(0.0f, -1.38f, 1.12f, -2.5f, 2.5f, -2.5f);
        pathBuilderA.reflectiveCurveToRelative(2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderA.curveTo(17.0f, 12.88f, 15.88f, 14.0f, 14.5f, 14.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _airlines = imageVectorBuild;
        return imageVectorBuild;
    }
}
