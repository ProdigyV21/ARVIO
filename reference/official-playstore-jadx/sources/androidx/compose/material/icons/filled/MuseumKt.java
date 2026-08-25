package androidx.compose.material.icons.filled;

import a0.a;
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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_museum", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Museum", "Landroidx/compose/material/icons/Icons$Filled;", "getMuseum", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MuseumKt {
    private static ImageVector _museum;

    public static final ImageVector getMuseum(Icons.Filled filled) {
        ImageVector imageVector = _museum;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Museum", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderF = a.f(22.0f, 11.0f, 9.0f, 12.0f, 2.0f);
        v.a.h(pathBuilderF, 2.0f, 9.0f, 2.0f, 2.0f);
        a.A(pathBuilderF, 9.0f, 2.0f, 2.0f, 20.0f);
        b.f(pathBuilderF, -2.0f, -2.0f, -9.0f, 22.0f);
        v.a.C(pathBuilderF, 16.0f, 18.0f, -2.0f, -4.0f);
        pathBuilderF.lineToRelative(-2.0f, 3.0f);
        d.v(pathBuilderF, -2.0f, -3.0f, 4.0f, 8.0f);
        d.D(pathBuilderF, -7.0f, 2.0f, 2.0f, 3.0f);
        pathBuilderF.lineToRelative(2.0f, -3.0f);
        pathBuilderF.horizontalLineToRelative(2.0f);
        pathBuilderF.verticalLineTo(18.0f);
        pathBuilderF.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderF.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _museum = imageVectorBuild;
        return imageVectorBuild;
    }
}
