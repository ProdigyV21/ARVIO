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
import v.a;
import v.c;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_downloading", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Downloading", "Landroidx/compose/material/icons/Icons$Filled;", "getDownloading", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DownloadingKt {
    private static ImageVector _downloading;

    public static final ImageVector getDownloading(Icons.Filled filled) {
        ImageVector imageVector = _downloading;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Downloading", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.32f, 4.26f);
        pathBuilderA.curveTo(16.84f, 3.05f, 15.01f, 2.25f, 13.0f, 2.05f);
        pathBuilderA.verticalLineToRelative(2.02f);
        pathBuilderA.curveToRelative(1.46f, 0.18f, 2.79f, 0.76f, 3.9f, 1.62f);
        c.z(pathBuilderA, 18.32f, 4.26f, 19.93f, 11.0f);
        pathBuilderA.horizontalLineToRelative(2.02f);
        pathBuilderA.curveToRelative(-0.2f, -2.01f, -1.0f, -3.84f, -2.21f, -5.32f);
        pathBuilderA.lineTo(18.31f, 7.1f);
        pathBuilderA.curveTo(19.17f, 8.21f, 19.75f, 9.54f, 19.93f, 11.0f);
        a.z(pathBuilderA, 18.31f, 16.9f, 1.43f, 1.43f);
        pathBuilderA.curveToRelative(1.21f, -1.48f, 2.01f, -3.32f, 2.21f, -5.32f);
        pathBuilderA.horizontalLineToRelative(-2.02f);
        pathBuilderA.curveTo(19.75f, 14.46f, 19.17f, 15.79f, 18.31f, 16.9f);
        d.k(pathBuilderA, 13.0f, 19.93f, 2.02f);
        pathBuilderA.curveToRelative(2.01f, -0.2f, 3.84f, -1.0f, 5.32f, -2.21f);
        pathBuilderA.lineToRelative(-1.43f, -1.43f);
        pathBuilderA.curveTo(15.79f, 19.17f, 14.46f, 19.75f, 13.0f, 19.93f);
        b.t(pathBuilderA, 13.0f, 12.0f, 7.0f, -2.0f);
        a0.a.o(pathBuilderA, 5.0f, 7.0f, 5.0f, 5.0f);
        f.v(pathBuilderA, 5.0f, -5.0f, 13.0f);
        pathBuilderA.moveTo(11.0f, 19.93f);
        pathBuilderA.verticalLineToRelative(2.02f);
        pathBuilderA.curveToRelative(-5.05f, -0.5f, -9.0f, -4.76f, -9.0f, -9.95f);
        pathBuilderA.reflectiveCurveToRelative(3.95f, -9.45f, 9.0f, -9.95f);
        pathBuilderA.verticalLineToRelative(2.02f);
        pathBuilderA.curveTo(7.05f, 4.56f, 4.0f, 7.92f, 4.0f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 7.05f, 19.44f, 11.0f, 19.93f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _downloading = imageVectorBuild;
        return imageVectorBuild;
    }
}
