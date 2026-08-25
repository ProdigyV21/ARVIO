package androidx.compose.material.icons.sharp;

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
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wash", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Wash", "Landroidx/compose/material/icons/Icons$Sharp;", "getWash", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WashKt {
    private static ImageVector _wash;

    public static final ImageVector getWash(Icons.Sharp sharp) {
        ImageVector imageVector = _wash;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Wash", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderN = a.n(9.12f, 5.0f, 1.0f, 12.68f, 23.0f);
        v.a.o(pathBuilderN, 18.0f, -2.5f, -7.0f, -1.0f);
        a.C(pathBuilderN, 9.0f, 17.0f, -9.0f, -1.0f);
        a0.a.y(pathBuilderN, 10.0f, -2.5f, 12.0f, -1.0f);
        b.w(pathBuilderN, 8.0f, 10.0f, 8.86f);
        pathBuilderN.lineToRelative(1.88f, -3.3f);
        v.b.y(pathBuilderN, 9.12f, 5.0f, 9.12f, 5.0f);
        pathBuilderN.moveTo(13.5f, 9.0f);
        pathBuilderN.curveTo(14.33f, 9.0f, 15.0f, 8.33f, 15.0f, 7.5f);
        pathBuilderN.curveTo(15.0f, 6.66f, 13.5f, 5.0f, 13.5f, 5.0f);
        pathBuilderN.reflectiveCurveTo(12.0f, 6.66f, 12.0f, 7.5f);
        pathBuilderN.curveTo(12.0f, 8.33f, 12.67f, 9.0f, 13.5f, 9.0f);
        pathBuilderN.close();
        pathBuilderN.moveTo(18.5f, 1.0f);
        pathBuilderN.curveToRelative(0.0f, 0.0f, -2.5f, 2.83f, -2.5f, 4.5f);
        pathBuilderN.curveTo(16.0f, 6.88f, 17.12f, 8.0f, 18.5f, 8.0f);
        pathBuilderN.reflectiveCurveTo(21.0f, 6.88f, 21.0f, 5.5f);
        pathBuilderN.curveTo(21.0f, 3.83f, 18.5f, 1.0f, 18.5f, 1.0f);
        pathBuilderN.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderN.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wash = imageVectorBuild;
        return imageVectorBuild;
    }
}
