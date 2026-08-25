package androidx.compose.material.icons.filled;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_photoSizeSelectLarge", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhotoSizeSelectLarge", "Landroidx/compose/material/icons/Icons$Filled;", "getPhotoSizeSelectLarge", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhotoSizeSelectLargeKt {
    private static ImageVector _photoSizeSelectLarge;

    public static final ImageVector getPhotoSizeSelectLarge(Icons.Filled filled) {
        ImageVector imageVector = _photoSizeSelectLarge;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.PhotoSizeSelectLarge", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(21.0f, 15.0f, 2.0f, 2.0f, -2.0f);
        c.r(pathBuilderK, -2.0f, 21.0f, 11.0f, 2.0f);
        b.l(pathBuilderK, 2.0f, -2.0f, -2.0f);
        a.C(pathBuilderK, 23.0f, 19.0f, -2.0f, 2.0f);
        pathBuilderK.curveToRelative(1.0f, 0.0f, 2.0f, -1.0f, 2.0f, -2.0f);
        b.w(pathBuilderK, 13.0f, 3.0f, 2.0f, 2.0f);
        d.q(pathBuilderK, -2.0f, 13.0f, 3.0f);
        a.C(pathBuilderK, 21.0f, 7.0f, 2.0f, 2.0f);
        d.q(pathBuilderK, -2.0f, 21.0f, 7.0f);
        c.m(pathBuilderK, 21.0f, 3.0f, 2.0f, 2.0f);
        pathBuilderK.curveToRelative(0.0f, -1.0f, -1.0f, -2.0f, -2.0f, -2.0f);
        b.w(pathBuilderK, 1.0f, 7.0f, 2.0f, 2.0f);
        b.y(pathBuilderK, 1.0f, 9.0f, 1.0f, 7.0f);
        a.C(pathBuilderK, 17.0f, 3.0f, 2.0f, 2.0f);
        d.q(pathBuilderK, -2.0f, 17.0f, 3.0f);
        a.C(pathBuilderK, 17.0f, 19.0f, 2.0f, 2.0f);
        b.B(pathBuilderK, -2.0f, -2.0f, 3.0f, 3.0f);
        pathBuilderK.curveTo(2.0f, 3.0f, 1.0f, 4.0f, 1.0f, 5.0f);
        d.q(pathBuilderK, 2.0f, 3.0f, 3.0f);
        a.C(pathBuilderK, 9.0f, 3.0f, 2.0f, 2.0f);
        b.y(pathBuilderK, 9.0f, 5.0f, 9.0f, 3.0f);
        a.C(pathBuilderK, 5.0f, 3.0f, 2.0f, 2.0f);
        b.y(pathBuilderK, 5.0f, 5.0f, 5.0f, 3.0f);
        pathBuilderK.moveTo(1.0f, 11.0f);
        pathBuilderK.verticalLineToRelative(8.0f);
        pathBuilderK.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderK.horizontalLineToRelative(12.0f);
        b.y(pathBuilderK, 15.0f, 11.0f, 1.0f, 11.0f);
        pathBuilderK.moveTo(3.0f, 19.0f);
        pathBuilderK.lineToRelative(2.5f, -3.21f);
        pathBuilderK.lineToRelative(1.79f, 2.15f);
        pathBuilderK.lineToRelative(2.5f, -3.22f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderK, 13.0f, 19.0f, 3.0f, 19.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _photoSizeSelectLarge = imageVectorBuild;
        return imageVectorBuild;
    }
}
