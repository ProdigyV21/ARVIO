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
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noFlash", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoFlash", "Landroidx/compose/material/icons/Icons$Sharp;", "getNoFlash", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoFlashKt {
    private static ImageVector _noFlash;

    public static final ImageVector getNoFlash(Icons.Sharp sharp) {
        ImageVector imageVector = _noFlash;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.NoFlash", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(2.45f, 2.45f, 1.04f, 3.87f);
        pathBuilderJ.lineToRelative(5.3f, 5.3f);
        b.p(pathBuilderJ, 6.14f, 9.4f, 2.0f, 22.0f);
        f.y(pathBuilderJ, 16.0f, -1.17f, 2.13f, 2.13f);
        d.C(pathBuilderJ, 1.41f, -1.41f, 2.45f, 2.45f);
        pathBuilderJ.moveTo(10.0f, 20.0f);
        pathBuilderJ.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.95f, 1.4f, -3.57f, 3.25f, -3.92f);
        pathBuilderJ.lineToRelative(1.57f, 1.57f);
        pathBuilderJ.curveToRelative(-0.26f, -0.09f, -0.53f, -0.15f, -0.82f, -0.15f);
        pathBuilderJ.curveToRelative(-1.38f, 0.0f, -2.5f, 1.12f, -2.5f, 2.5f);
        pathBuilderJ.curveToRelative(0.0f, 1.38f, 1.12f, 2.5f, 2.5f, 2.5f);
        pathBuilderJ.curveToRelative(1.38f, 0.0f, 2.5f, -1.12f, 2.5f, -2.5f);
        pathBuilderJ.curveToRelative(0.0f, -0.29f, -0.06f, -0.56f, -0.15f, -0.82f);
        pathBuilderJ.lineToRelative(1.57f, 1.57f);
        pathBuilderJ.curveTo(13.57f, 18.6f, 11.95f, 20.0f, 10.0f, 20.0f);
        a.p(pathBuilderJ, 18.0f, 15.17f, 10.83f, 8.0f);
        a0.a.n(pathBuilderJ, 1.75f, 1.28f, 1.4f, 18.0f);
        a.B(pathBuilderJ, 15.17f, 20.4f, 5.6f, 22.0f);
        b.n(pathBuilderJ, 19.0f, 11.0f, 7.0f, -1.0f);
        pathBuilderJ.verticalLineTo(2.0f);
        pathBuilderJ.horizontalLineToRelative(4.0f);
        pathBuilderJ.lineTo(20.4f, 5.6f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noFlash = imageVectorBuild;
        return imageVectorBuild;
    }
}
