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
import v.c;
import v.e;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_addAPhoto", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AddAPhoto", "Landroidx/compose/material/icons/Icons$Sharp;", "getAddAPhoto", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AddAPhotoKt {
    private static ImageVector _addAPhoto;

    public static final ImageVector getAddAPhoto(Icons.Sharp sharp) {
        ImageVector imageVector = _addAPhoto;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.AddAPhoto", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(3.0f, 4.0f, 3.0f, 1.0f, 2.0f);
        e.l(pathBuilderT, 3.0f, 3.0f, 2.0f);
        pathBuilderT.lineTo(5.0f, 6.0f);
        pathBuilderT.verticalLineToRelative(3.0f);
        pathBuilderT.lineTo(3.0f, 9.0f);
        pathBuilderT.lineTo(3.0f, 6.0f);
        pathBuilderT.lineTo(0.0f, 6.0f);
        f.n(pathBuilderT, 0.0f, 4.0f, 3.0f);
        pathBuilderT.moveTo(6.0f, 10.0f);
        pathBuilderT.lineTo(6.0f, 7.0f);
        c.A(pathBuilderT, 3.0f, 9.0f, 4.0f, 7.0f);
        pathBuilderT.lineToRelative(1.83f, 2.0f);
        pathBuilderT.lineTo(23.0f, 6.0f);
        pathBuilderT.verticalLineToRelative(16.0f);
        pathBuilderT.lineTo(3.0f, 22.0f);
        f.n(pathBuilderT, 3.0f, 10.0f, 3.0f);
        pathBuilderT.moveTo(13.0f, 19.0f);
        pathBuilderT.curveToRelative(2.76f, 0.0f, 5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderT.reflectiveCurveToRelative(-2.24f, -5.0f, -5.0f, -5.0f);
        pathBuilderT.reflectiveCurveToRelative(-5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderT.reflectiveCurveToRelative(2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderT.close();
        pathBuilderT.moveTo(10.0f, 14.0f);
        pathBuilderT.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderT.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderT.reflectiveCurveToRelative(-1.34f, -3.0f, -3.0f, -3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderT, -3.0f, 1.34f, -3.0f, 3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _addAPhoto = imageVectorBuild;
        return imageVectorBuild;
    }
}
