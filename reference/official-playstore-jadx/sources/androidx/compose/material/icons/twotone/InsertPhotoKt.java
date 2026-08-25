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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_insertPhoto", "Landroidx/compose/ui/graphics/vector/ImageVector;", "InsertPhoto", "Landroidx/compose/material/icons/Icons$TwoTone;", "getInsertPhoto", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InsertPhotoKt {
    private static ImageVector _insertPhoto;

    public static final ImageVector getInsertPhoto(Icons.TwoTone twoTone) {
        ImageVector imageVector = _insertPhoto;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.InsertPhoto", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(5.0f, 19.0f, 14.0f, 19.0f, 5.0f);
        d.f(pathBuilderI, 5.0f, 5.0f, 14.0f);
        pathBuilderI.moveTo(9.0f, 13.14f);
        pathBuilderI.lineToRelative(2.14f, 2.58f);
        pathBuilderI.lineToRelative(3.0f, -3.87f);
        pathBuilderI.lineTo(18.0f, 17.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderI, 6.0f, 17.0f, 3.0f, -3.86f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI2 = a.i(3.0f, 5.0f, 14.0f);
        pathBuilderI2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderI2.horizontalLineToRelative(14.0f);
        pathBuilderI2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI2.lineTo(21.0f, 5.0f);
        pathBuilderI2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderI2.lineTo(5.0f, 3.0f);
        pathBuilderI2.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        a.p(pathBuilderI2, 19.0f, 19.0f, 5.0f, 19.0f);
        b.g(pathBuilderI2, 5.0f, 5.0f, 14.0f, 14.0f);
        pathBuilderI2.moveTo(14.14f, 11.86f);
        pathBuilderI2.lineToRelative(-3.0f, 3.86f);
        pathBuilderI2.lineTo(9.0f, 13.14f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.d(pathBuilderI2, 6.0f, 17.0f, 12.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _insertPhoto = imageVectorBuild;
        return imageVectorBuild;
    }
}
