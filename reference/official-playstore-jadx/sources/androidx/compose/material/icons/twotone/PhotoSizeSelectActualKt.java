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
import v.b;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_photoSizeSelectActual", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhotoSizeSelectActual", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPhotoSizeSelectActual", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhotoSizeSelectActualKt {
    private static ImageVector _photoSizeSelectActual;

    public static final ImageVector getPhotoSizeSelectActual(Icons.TwoTone twoTone) {
        ImageVector imageVector = _photoSizeSelectActual;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PhotoSizeSelectActual", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = a.n(3.08f, 5.0f, 3.0f, 5.08f, 19.0f);
        pathBuilderN.horizontalLineToRelative(17.92f);
        pathBuilderN.curveToRelative(0.03f, -0.02f, 0.06f, -0.06f, 0.08f, -0.08f);
        pathBuilderN.verticalLineTo(5.08f);
        pathBuilderN.lineTo(20.92f, 5.0f);
        pathBuilderN.horizontalLineTo(3.08f);
        pathBuilderN.close();
        pathBuilderN.moveTo(5.0f, 17.0f);
        pathBuilderN.lineToRelative(3.5f, -4.5f);
        pathBuilderN.lineToRelative(2.5f, 3.01f);
        pathBuilderN.lineTo(14.5f, 11.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.b(pathBuilderN, 4.5f, 6.0f, 5.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = v.a.j(21.0f, 3.0f, 3.0f, 3.0f);
        pathBuilderJ.curveTo(2.0f, 3.0f, 1.0f, 4.0f, 1.0f, 5.0f);
        pathBuilderJ.verticalLineToRelative(14.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(18.0f);
        pathBuilderJ.curveToRelative(1.0f, 0.0f, 2.0f, -1.0f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(23.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.0f, -1.0f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(21.0f, 18.92f);
        pathBuilderJ.curveToRelative(-0.02f, 0.03f, -0.06f, 0.06f, -0.08f, 0.08f);
        pathBuilderJ.lineTo(3.0f, 19.0f);
        pathBuilderJ.lineTo(3.0f, 5.08f);
        pathBuilderJ.lineTo(3.08f, 5.0f);
        pathBuilderJ.horizontalLineToRelative(17.83f);
        pathBuilderJ.curveToRelative(0.03f, 0.02f, 0.06f, 0.06f, 0.08f, 0.08f);
        b.f(pathBuilderJ, 13.84f, 11.0f, 15.51f);
        pathBuilderJ.lineTo(8.5f, 12.5f);
        pathBuilderJ.lineTo(5.0f, 17.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, y.a.e(pathBuilderJ, 14.0f, -4.5f, -6.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _photoSizeSelectActual = imageVectorBuild;
        return imageVectorBuild;
    }
}
