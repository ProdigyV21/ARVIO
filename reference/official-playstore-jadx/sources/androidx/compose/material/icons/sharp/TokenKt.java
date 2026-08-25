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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_token", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Token", "Landroidx/compose/material/icons/Icons$Sharp;", "getToken", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TokenKt {
    private static ImageVector _token;

    public static final ImageVector getToken(Icons.Sharp sharp) {
        ImageVector imageVector = _token;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Token", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.97f, 6.43f, 12.0f, 2.0f);
        pathBuilderJ.lineTo(4.03f, 6.43f);
        pathBuilderJ.lineTo(9.1f, 9.24f);
        pathBuilderJ.curveTo(9.83f, 8.48f, 10.86f, 8.0f, 12.0f, 8.0f);
        pathBuilderJ.reflectiveCurveToRelative(2.17f, 0.48f, 2.9f, 1.24f);
        c.z(pathBuilderJ, 19.97f, 6.43f, 10.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderJ.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderJ.reflectiveCurveTo(10.0f, 13.1f, 10.0f, 12.0f);
        a.p(pathBuilderJ, 11.0f, 21.44f, 3.0f, 17.0f);
        pathBuilderJ.verticalLineTo(8.14f);
        pathBuilderJ.lineToRelative(5.13f, 2.85f);
        pathBuilderJ.curveTo(8.04f, 11.31f, 8.0f, 11.65f, 8.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.86f, 1.27f, 3.43f, 3.0f, 3.87f);
        w.a.k(pathBuilderJ, 21.44f, 13.0f, 21.44f, -5.57f);
        pathBuilderJ.curveToRelative(1.73f, -0.44f, 3.0f, -2.01f, 3.0f, -3.87f);
        pathBuilderJ.curveToRelative(0.0f, -0.35f, -0.04f, -0.69f, -0.13f, -1.01f);
        pathBuilderJ.lineTo(21.0f, 8.14f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderJ, 21.0f, 17.0f, 13.0f, 21.44f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _token = imageVectorBuild;
        return imageVectorBuild;
    }
}
