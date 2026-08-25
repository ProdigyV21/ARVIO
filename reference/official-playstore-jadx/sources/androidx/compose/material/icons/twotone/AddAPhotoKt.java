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
import v.c;
import v.d;
import v.e;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_addAPhoto", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AddAPhoto", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAddAPhoto", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AddAPhotoKt {
    private static ImageVector _addAPhoto;

    public static final ImageVector getAddAPhoto(Icons.TwoTone twoTone) {
        ImageVector imageVector = _addAPhoto;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.AddAPhoto", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = d.s(6.0f, 7.0f, 3.0f, 5.0f, 10.0f);
        c.w(pathBuilderS, 10.0f, 16.0f, 21.0f, 8.0f);
        pathBuilderS.horizontalLineToRelative(-4.05f);
        pathBuilderS.lineToRelative(-1.83f, -2.0f);
        pathBuilderS.lineTo(9.0f, 6.0f);
        a.n(pathBuilderS, 1.0f, 6.0f, 7.0f);
        pathBuilderS.moveTo(13.0f, 9.0f);
        pathBuilderS.curveToRelative(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderS.reflectiveCurveToRelative(-2.24f, 5.0f, -5.0f, 5.0f);
        pathBuilderS.reflectiveCurveToRelative(-5.0f, -2.24f, -5.0f, -5.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderS, 2.24f, -5.0f, 5.0f, -5.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(21.0f, 6.0f, -3.17f, 16.0f, 4.0f);
        v.a.h(pathBuilderI, 9.0f, 4.0f, 2.0f, 6.12f);
        pathBuilderI.lineToRelative(1.83f, 2.0f);
        pathBuilderI.lineTo(21.0f, 8.0f);
        pathBuilderI.verticalLineToRelative(12.0f);
        pathBuilderI.lineTo(5.0f, 20.0f);
        pathBuilderI.lineTo(5.0f, 10.0f);
        pathBuilderI.lineTo(3.0f, 10.0f);
        pathBuilderI.verticalLineToRelative(10.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderI.horizontalLineToRelative(16.0f);
        pathBuilderI.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI.lineTo(23.0f, 8.0f);
        pathBuilderI.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderI.close();
        pathBuilderI.moveTo(8.0f, 14.0f);
        pathBuilderI.curveToRelative(0.0f, 2.76f, 2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderI.reflectiveCurveToRelative(5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderI.reflectiveCurveToRelative(-2.24f, -5.0f, -5.0f, -5.0f);
        pathBuilderI.reflectiveCurveToRelative(-5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderI.close();
        pathBuilderI.moveTo(13.0f, 11.0f);
        pathBuilderI.curveToRelative(1.65f, 0.0f, 3.0f, 1.35f, 3.0f, 3.0f);
        pathBuilderI.reflectiveCurveToRelative(-1.35f, 3.0f, -3.0f, 3.0f);
        pathBuilderI.reflectiveCurveToRelative(-3.0f, -1.35f, -3.0f, -3.0f);
        pathBuilderI.reflectiveCurveToRelative(1.35f, -3.0f, 3.0f, -3.0f);
        v.a.p(pathBuilderI, 5.0f, 9.0f, 5.0f, 6.0f);
        pathBuilderI.horizontalLineToRelative(3.0f);
        pathBuilderI.lineTo(8.0f, 4.0f);
        pathBuilderI.lineTo(5.0f, 4.0f);
        pathBuilderI.lineTo(5.0f, 1.0f);
        pathBuilderI.lineTo(3.0f, 1.0f);
        d.r(pathBuilderI, 3.0f, 0.0f, 4.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.s(pathBuilderI, 3.0f, 3.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _addAPhoto = imageVectorBuild;
        return imageVectorBuild;
    }
}
