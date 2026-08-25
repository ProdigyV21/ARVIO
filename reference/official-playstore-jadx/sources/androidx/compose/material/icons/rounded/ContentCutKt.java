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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_contentCut", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ContentCut", "Landroidx/compose/material/icons/Icons$Rounded;", "getContentCut", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContentCutKt {
    private static ImageVector _contentCut;

    public static final ImageVector getContentCut(Icons.Rounded rounded) {
        ImageVector imageVector = _contentCut;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ContentCut", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.64f, 7.64f);
        pathBuilderA.curveToRelative(0.29f, -0.62f, 0.42f, -1.33f, 0.34f, -2.09f);
        pathBuilderA.curveToRelative(-0.19f, -1.73f, -1.54f, -3.2f, -3.26f, -3.49f);
        pathBuilderA.curveToRelative(-2.77f, -0.48f, -5.14f, 1.89f, -4.66f, 4.65f);
        pathBuilderA.curveToRelative(0.3f, 1.72f, 1.76f, 3.07f, 3.49f, 3.26f);
        pathBuilderA.curveToRelative(0.76f, 0.08f, 1.46f, -0.05f, 2.09f, -0.34f);
        pathBuilderA.lineTo(10.0f, 12.0f);
        pathBuilderA.lineToRelative(-2.36f, 2.36f);
        pathBuilderA.curveToRelative(-0.62f, -0.29f, -1.33f, -0.42f, -2.09f, -0.34f);
        pathBuilderA.curveToRelative(-1.73f, 0.19f, -3.2f, 1.54f, -3.49f, 3.26f);
        pathBuilderA.curveToRelative(-0.48f, 2.77f, 1.89f, 5.13f, 4.65f, 4.65f);
        pathBuilderA.curveToRelative(1.72f, -0.3f, 3.07f, -1.76f, 3.26f, -3.49f);
        pathBuilderA.curveToRelative(0.08f, -0.76f, -0.05f, -1.46f, -0.34f, -2.09f);
        pathBuilderA.lineTo(12.0f, 14.0f);
        pathBuilderA.lineToRelative(7.59f, 7.59f);
        pathBuilderA.curveToRelative(0.89f, 0.89f, 2.41f, 0.26f, 2.41f, -1.0f);
        pathBuilderA.verticalLineToRelative(-0.01f);
        pathBuilderA.curveToRelative(0.0f, -0.37f, -0.15f, -0.73f, -0.41f, -1.0f);
        c.z(pathBuilderA, 9.64f, 7.64f, 6.0f, 8.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.89f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.89f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(6.0f, 20.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.89f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.89f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 12.5f);
        pathBuilderA.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.22f, 0.5f, -0.5f, 0.5f);
        a.p(pathBuilderA, 19.59f, 2.41f, 13.0f, 9.0f);
        pathBuilderA.lineToRelative(2.0f, 2.0f);
        pathBuilderA.lineToRelative(6.59f, -6.59f);
        pathBuilderA.curveToRelative(0.26f, -0.26f, 0.41f, -0.62f, 0.41f, -1.0f);
        pathBuilderA.lineTo(22.0f, 3.4f);
        pathBuilderA.curveToRelative(0.0f, -1.25f, -1.52f, -1.88f, -2.41f, -0.99f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _contentCut = imageVectorBuild;
        return imageVectorBuild;
    }
}
