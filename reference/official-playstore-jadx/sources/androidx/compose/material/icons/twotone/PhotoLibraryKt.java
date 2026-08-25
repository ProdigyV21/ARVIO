package androidx.compose.material.icons.twotone;

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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_photoLibrary", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhotoLibrary", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPhotoLibrary", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhotoLibraryKt {
    private static ImageVector _photoLibrary;

    public static final ImageVector getPhotoLibrary(Icons.TwoTone twoTone) {
        ImageVector imageVector = _photoLibrary;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PhotoLibrary", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(8.0f, 16.0f, 12.0f, 20.0f, 4.0f);
        d.f(pathBuilderI, 8.0f, 4.0f, 12.0f);
        pathBuilderI.moveTo(11.5f, 11.67f);
        pathBuilderI.lineToRelative(1.69f, 2.26f);
        pathBuilderI.lineToRelative(2.48f, -3.09f);
        pathBuilderI.lineTo(19.0f, 15.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderI, 9.0f, 15.0f, 2.5f, -3.33f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(22.0f, 16.0f, 22.0f, 4.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.lineTo(8.0f, 2.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        a.p(pathBuilderJ, 20.0f, 16.0f, 8.0f, 16.0f);
        b.g(pathBuilderJ, 8.0f, 4.0f, 12.0f, 12.0f);
        pathBuilderJ.moveTo(15.67f, 10.83f);
        pathBuilderJ.lineToRelative(-2.48f, 3.09f);
        pathBuilderJ.lineToRelative(-1.69f, -2.25f);
        f.n(pathBuilderJ, 9.0f, 15.0f, 10.0f);
        a.C(pathBuilderJ, 4.0f, 22.0f, 14.0f, -2.0f);
        pathBuilderJ.lineTo(4.0f, 20.0f);
        pathBuilderJ.lineTo(4.0f, 6.0f);
        pathBuilderJ.lineTo(2.0f, 6.0f);
        pathBuilderJ.verticalLineToRelative(14.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _photoLibrary = imageVectorBuild;
        return imageVectorBuild;
    }
}
