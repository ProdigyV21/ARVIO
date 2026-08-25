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
import v.b;
import v.c;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_signLanguage", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SignLanguage", "Landroidx/compose/material/icons/Icons$Sharp;", "getSignLanguage", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SignLanguageKt {
    private static ImageVector _signLanguage;

    public static final ImageVector getSignLanguage(Icons.Sharp sharp) {
        ImageVector imageVector = _signLanguage;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SignLanguage", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(12.49f, 13.0f, -1.39f, -2.7f);
        pathBuilderR.lineTo(12.49f, 9.0f);
        c.f(pathBuilderR, 19.0f, 15.2f, 24.0f, 4.5f);
        a.w(pathBuilderR, -2.0f, 10.0f, -1.0f, 3.0f);
        f.u(pathBuilderR, -2.0f, 7.0f, -1.0f, 2.0f);
        f.u(pathBuilderR, -2.0f, 8.0f, -1.0f, 3.5f);
        e.y(pathBuilderR, -2.0f, 12.49f, 11.78f, 7.12f);
        pathBuilderR.curveToRelative(-0.84f, 0.4f, -1.17f, 0.62f, -1.63f, 1.19f);
        pathBuilderR.lineTo(6.76f, 4.74f);
        d.C(pathBuilderR, 1.45f, -1.38f, 11.78f, 7.12f);
        pathBuilderR.moveTo(9.64f, 9.21f);
        pathBuilderR.curveTo(9.41f, 9.76f, 9.35f, 10.45f, 9.44f, 11.0f);
        pathBuilderR.horizontalLineTo(8.58f);
        pathBuilderR.lineTo(5.62f, 7.89f);
        d.C(pathBuilderR, 1.45f, -1.38f, 9.64f, 9.21f);
        pathBuilderR.moveTo(21.98f, 12.34f);
        pathBuilderR.lineTo(22.0f, 3.35f);
        pathBuilderR.lineToRelative(-1.9f, -0.1f);
        pathBuilderR.lineToRelative(-1.0f, 2.86f);
        pathBuilderR.lineTo(13.3f, 0.0f);
        pathBuilderR.lineToRelative(-1.45f, 1.38f);
        pathBuilderR.lineToRelative(4.09f, 4.3f);
        pathBuilderR.lineToRelative(-0.73f, 0.69f);
        pathBuilderR.lineTo(9.74f, 0.64f);
        pathBuilderR.lineTo(8.3f, 2.0f);
        pathBuilderR.lineToRelative(3.36f, 3.53f);
        pathBuilderR.lineToRelative(1.06f, 1.11f);
        pathBuilderR.lineToRelative(2.65f, 2.33f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderR, 5.08f, 4.83f, 21.98f, 12.34f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _signLanguage = imageVectorBuild;
        return imageVectorBuild;
    }
}
