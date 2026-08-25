package androidx.compose.material.icons.filled;

import a0.a;
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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_addAPhoto", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AddAPhoto", "Landroidx/compose/material/icons/Icons$Filled;", "getAddAPhoto", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AddAPhotoKt {
    private static ImageVector _addAPhoto;

    public static final ImageVector getAddAPhoto(Icons.Filled filled) {
        ImageVector imageVector = _addAPhoto;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.AddAPhoto", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(3.0f, 4.0f, 1.0f, 2.0f, 3.0f);
        a.y(pathBuilderA, 3.0f, 2.0f, 5.0f, 3.0f);
        a.x(pathBuilderA, 3.0f, 6.0f, 0.0f, 4.0f);
        a.t(pathBuilderA, 3.0f, 6.0f, 10.0f, 7.0f);
        a.d(pathBuilderA, 3.0f, 4.0f, 7.0f);
        pathBuilderA.lineToRelative(1.83f, 2.0f);
        pathBuilderA.horizontalLineTo(21.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        c.n(pathBuilderA, 10.0f, 6.0f, 13.0f, 19.0f);
        pathBuilderA.curveToRelative(2.76f, 0.0f, 5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.24f, -5.0f, -5.0f, -5.0f);
        pathBuilderA.reflectiveCurveToRelative(-5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderA.reflectiveCurveTo(10.24f, 19.0f, 13.0f, 19.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.8f, 14.0f);
        pathBuilderA.curveToRelative(0.0f, 1.77f, 1.43f, 3.2f, 3.2f, 3.2f);
        pathBuilderA.reflectiveCurveToRelative(3.2f, -1.43f, 3.2f, -3.2f);
        pathBuilderA.reflectiveCurveToRelative(-1.43f, -3.2f, -3.2f, -3.2f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 9.8f, 12.23f, 9.8f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _addAPhoto = imageVectorBuild;
        return imageVectorBuild;
    }
}
