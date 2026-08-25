package androidx.compose.material.icons.automirrored.sharp;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_phoneMissed", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhoneMissed", "Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;", "getPhoneMissed", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhoneMissedKt {
    private static ImageVector _phoneMissed;

    public static final ImageVector getPhoneMissed(Icons.AutoMirrored.Sharp sharp) {
        ImageVector imageVector = _phoneMissed;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Sharp.PhoneMissed", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(23.32f, 16.67f);
        pathBuilderA.curveToRelative(-2.95f, -2.79f, -6.93f, -4.51f, -11.31f, -4.51f);
        pathBuilderA.curveToRelative(-4.39f, 0.0f, -8.37f, 1.72f, -11.31f, 4.51f);
        pathBuilderA.lineToRelative(-0.69f, 0.69f);
        pathBuilderA.lineTo(3.65f, 21.0f);
        pathBuilderA.lineToRelative(3.93f, -2.72f);
        pathBuilderA.lineToRelative(-0.01f, -3.49f);
        pathBuilderA.curveToRelative(1.4f, -0.45f, 2.9f, -0.7f, 4.44f, -0.7f);
        pathBuilderA.curveToRelative(1.55f, 0.0f, 3.04f, 0.24f, 4.44f, 0.7f);
        pathBuilderA.lineToRelative(-0.01f, 3.49f);
        pathBuilderA.lineTo(20.37f, 21.0f);
        pathBuilderA.lineToRelative(3.64f, -3.64f);
        pathBuilderA.curveToRelative(0.0f, -0.01f, -0.52f, -0.52f, -0.69f, -0.69f);
        a.z(pathBuilderA, 7.0f, 6.43f, 4.94f, 4.94f);
        pathBuilderA.lineToRelative(7.07f, -7.07f);
        pathBuilderA.lineToRelative(-1.41f, -1.42f);
        pathBuilderA.lineToRelative(-5.66f, 5.66f);
        b.p(pathBuilderA, 8.4f, 5.0f, 11.0f, 3.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.verticalLineToRelative(6.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _phoneMissed = imageVectorBuild;
        return imageVectorBuild;
    }
}
