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
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_science", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Science", "Landroidx/compose/material/icons/Icons$Rounded;", "getScience", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScienceKt {
    private static ImageVector _science;

    public static final ImageVector getScience(Icons.Rounded rounded) {
        ImageVector imageVector = _science;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Science", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = a.n(20.54f, 17.73f, 15.0f, 11.0f, 5.0f);
        pathBuilderN.horizontalLineToRelative(1.0f);
        pathBuilderN.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderN.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderN.horizontalLineTo(8.0f);
        pathBuilderN.curveTo(7.45f, 3.0f, 7.0f, 3.45f, 7.0f, 4.0f);
        pathBuilderN.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        f.y(pathBuilderN, 1.0f, 6.0f, -5.54f, 6.73f);
        pathBuilderN.curveTo(3.14f, 18.12f, 3.0f, 18.56f, 3.0f, 19.0f);
        pathBuilderN.curveToRelative(0.01f, 1.03f, 0.82f, 2.0f, 2.0f, 2.0f);
        pathBuilderN.horizontalLineTo(19.0f);
        pathBuilderN.curveToRelative(1.19f, 0.0f, 2.0f, -0.97f, 2.0f, -2.0f);
        pathBuilderN.curveTo(21.0f, 18.56f, 20.86f, 18.12f, 20.54f, 17.73f);
        pathBuilderN.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderN.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _science = imageVectorBuild;
        return imageVectorBuild;
    }
}
