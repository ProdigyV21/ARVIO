package androidx.compose.material.icons.filled;

import a0.b;
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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noEncryptionGmailerrorred", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoEncryptionGmailerrorred", "Landroidx/compose/material/icons/Icons$Filled;", "getNoEncryptionGmailerrorred", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoEncryptionGmailerrorredKt {
    private static ImageVector _noEncryptionGmailerrorred;

    public static final ImageVector getNoEncryptionGmailerrorred(Icons.Filled filled) {
        ImageVector imageVector = _noEncryptionGmailerrorred;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.NoEncryptionGmailerrorred", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.9f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.71f, 1.39f, -3.1f, 3.1f, -3.1f);
        pathBuilderA.reflectiveCurveToRelative(3.1f, 1.39f, 3.1f, 3.1f);
        c.w(pathBuilderA, 2.0f, -4.27f, 20.0f, 17.17f);
        pathBuilderA.verticalLineTo(10.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineToRelative(-1.0f);
        pathBuilderA.verticalLineTo(6.0f);
        pathBuilderA.curveToRelative(0.0f, -2.76f, -2.24f, -5.0f, -5.0f, -5.0f);
        pathBuilderA.curveTo(9.79f, 1.0f, 7.93f, 2.45f, 7.27f, 4.44f);
        b.s(pathBuilderA, 8.9f, 6.07f, 6.0f);
        pathBuilderA.moveTo(2.1f, 2.1f);
        pathBuilderA.lineTo(0.69f, 3.51f);
        pathBuilderA.lineTo(5.3f, 8.13f);
        pathBuilderA.curveTo(4.55f, 8.42f, 4.0f, 9.15f, 4.0f, 10.0f);
        pathBuilderA.verticalLineToRelative(10.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(0.34f, 0.0f, 0.65f, -0.09f, 0.93f, -0.24f);
        pathBuilderA.lineToRelative(1.56f, 1.56f);
        d.C(pathBuilderA, 1.41f, -1.41f, 2.1f, 2.1f);
        pathBuilderA.moveTo(12.0f, 17.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.59f, 0.27f, -1.12f, 0.68f, -1.49f);
        pathBuilderA.lineToRelative(2.81f, 2.81f);
        pathBuilderA.curveTo(13.12f, 16.73f, 12.59f, 17.0f, 12.0f, 17.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noEncryptionGmailerrorred = imageVectorBuild;
        return imageVectorBuild;
    }
}
