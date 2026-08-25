package androidx.compose.material.icons.outlined;

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
import v.b;
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_token", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Token", "Landroidx/compose/material/icons/Icons$Outlined;", "getToken", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TokenKt {
    private static ImageVector _token;

    public static final ImageVector getToken(Icons.Outlined outlined) {
        ImageVector imageVector = _token;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Token", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.0f, 7.0f, -9.0f, -5.0f);
        pathBuilderR.lineTo(3.0f, 7.0f);
        pathBuilderR.verticalLineToRelative(10.0f);
        pathBuilderR.lineToRelative(9.0f, 5.0f);
        d.C(pathBuilderR, 9.0f, -5.0f, 21.0f, 7.0f);
        pathBuilderR.moveTo(12.0f, 4.29f);
        pathBuilderR.lineToRelative(5.91f, 3.28f);
        pathBuilderR.lineTo(14.9f, 9.24f);
        pathBuilderR.curveTo(14.17f, 8.48f, 13.14f, 8.0f, 12.0f, 8.0f);
        pathBuilderR.reflectiveCurveTo(9.83f, 8.48f, 9.1f, 9.24f);
        b.y(pathBuilderR, 6.09f, 7.57f, 12.0f, 4.29f);
        pathBuilderR.moveTo(11.0f, 19.16f);
        pathBuilderR.lineToRelative(-6.0f, -3.33f);
        pathBuilderR.verticalLineTo(9.26f);
        pathBuilderR.lineToRelative(3.13f, 1.74f);
        pathBuilderR.curveTo(8.04f, 11.31f, 8.0f, 11.65f, 8.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, 1.86f, 1.27f, 3.43f, 3.0f, 3.87f);
        f.r(pathBuilderR, 19.16f, 10.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderR.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderR.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderR.reflectiveCurveTo(10.0f, 13.1f, 10.0f, 12.0f);
        d.k(pathBuilderR, 13.0f, 19.16f, -3.28f);
        pathBuilderR.curveToRelative(1.73f, -0.44f, 3.0f, -2.01f, 3.0f, -3.87f);
        pathBuilderR.curveToRelative(0.0f, -0.35f, -0.04f, -0.69f, -0.13f, -1.01f);
        pathBuilderR.lineTo(19.0f, 9.26f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, 0.0f, 6.57f, 13.0f, 19.16f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _token = imageVectorBuild;
        return imageVectorBuild;
    }
}
