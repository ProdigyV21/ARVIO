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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_android", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Android", "Landroidx/compose/material/icons/Icons$Rounded;", "getAndroid", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidKt {
    private static ImageVector _android;

    public static final ImageVector getAndroid(Icons.Rounded rounded) {
        ImageVector imageVector = _android;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Android", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(17.6f, 9.48f, 1.84f, -3.18f);
        pathBuilderR.curveToRelative(0.16f, -0.31f, 0.04f, -0.69f, -0.26f, -0.85f);
        pathBuilderR.curveToRelative(-0.29f, -0.15f, -0.65f, -0.06f, -0.83f, 0.22f);
        pathBuilderR.lineToRelative(-1.88f, 3.24f);
        pathBuilderR.curveToRelative(-2.86f, -1.21f, -6.08f, -1.21f, -8.94f, 0.0f);
        pathBuilderR.lineTo(5.65f, 5.67f);
        pathBuilderR.curveToRelative(-0.19f, -0.29f, -0.58f, -0.38f, -0.87f, -0.2f);
        pathBuilderR.curveTo(4.5f, 5.65f, 4.41f, 6.01f, 4.56f, 6.3f);
        pathBuilderR.lineTo(6.4f, 9.48f);
        pathBuilderR.curveTo(3.3f, 11.25f, 1.28f, 14.44f, 1.0f, 18.0f);
        pathBuilderR.horizontalLineToRelative(22.0f);
        pathBuilderR.curveTo(22.72f, 14.44f, 20.7f, 11.25f, 17.6f, 9.48f);
        pathBuilderR.close();
        pathBuilderR.moveTo(7.0f, 15.25f);
        pathBuilderR.curveToRelative(-0.69f, 0.0f, -1.25f, -0.56f, -1.25f, -1.25f);
        pathBuilderR.curveToRelative(0.0f, -0.69f, 0.56f, -1.25f, 1.25f, -1.25f);
        pathBuilderR.reflectiveCurveTo(8.25f, 13.31f, 8.25f, 14.0f);
        pathBuilderR.curveTo(8.25f, 14.69f, 7.69f, 15.25f, 7.0f, 15.25f);
        pathBuilderR.close();
        pathBuilderR.moveTo(17.0f, 15.25f);
        pathBuilderR.curveToRelative(-0.69f, 0.0f, -1.25f, -0.56f, -1.25f, -1.25f);
        pathBuilderR.curveToRelative(0.0f, -0.69f, 0.56f, -1.25f, 1.25f, -1.25f);
        pathBuilderR.reflectiveCurveToRelative(1.25f, 0.56f, 1.25f, 1.25f);
        pathBuilderR.curveTo(18.25f, 14.69f, 17.69f, 15.25f, 17.0f, 15.25f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _android = imageVectorBuild;
        return imageVectorBuild;
    }
}
