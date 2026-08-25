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
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_addPhotoAlternate", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AddPhotoAlternate", "Landroidx/compose/material/icons/Icons$Sharp;", "getAddPhotoAlternate", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AddPhotoAlternateKt {
    private static ImageVector _addPhotoAlternate;

    public static final ImageVector getAddPhotoAlternate(Icons.Sharp sharp) {
        ImageVector imageVector = _addPhotoAlternate;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.AddPhotoAlternate", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(19.0f, 7.0f, 2.99f);
        pathBuilderI.reflectiveCurveToRelative(-1.99f, 0.01f, -2.0f, 0.0f);
        pathBuilderI.lineTo(17.0f, 7.0f);
        pathBuilderI.horizontalLineToRelative(-3.0f);
        pathBuilderI.reflectiveCurveToRelative(0.01f, -1.99f, 0.0f, -2.0f);
        c.A(pathBuilderI, 3.0f, 17.0f, 2.0f, 2.0f);
        a0.a.B(pathBuilderI, 3.0f, 3.0f, 2.0f, -3.0f);
        pathBuilderI.moveTo(16.0f, 11.0f);
        pathBuilderI.lineTo(16.0f, 8.0f);
        pathBuilderI.horizontalLineToRelative(-3.0f);
        pathBuilderI.lineTo(13.0f, 5.0f);
        a.h(pathBuilderI, 3.0f, 5.0f, 16.0f, 16.0f);
        f.n(pathBuilderI, 19.0f, 11.0f, -3.0f);
        pathBuilderI.moveTo(5.0f, 19.0f);
        pathBuilderI.lineToRelative(3.0f, -4.0f);
        pathBuilderI.lineToRelative(2.0f, 3.0f);
        pathBuilderI.lineToRelative(3.0f, -4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderI, 4.0f, 5.0f, 5.0f, 19.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _addPhotoAlternate = imageVectorBuild;
        return imageVectorBuild;
    }
}
