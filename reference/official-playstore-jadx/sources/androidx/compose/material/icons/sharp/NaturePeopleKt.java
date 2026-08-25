package androidx.compose.material.icons.sharp;

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
import v.b;
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_naturePeople", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NaturePeople", "Landroidx/compose/material/icons/Icons$Sharp;", "getNaturePeople", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NaturePeopleKt {
    private static ImageVector _naturePeople;

    public static final ImageVector getNaturePeople(Icons.Sharp sharp) {
        ImageVector imageVector = _naturePeople;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.NaturePeople", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(22.17f, 9.17f);
        pathBuilderA.curveToRelative(0.0f, -3.91f, -3.19f, -7.06f, -7.11f, -7.0f);
        pathBuilderA.curveToRelative(-3.83f, 0.06f, -6.99f, 3.37f, -6.88f, 7.19f);
        pathBuilderA.curveToRelative(0.09f, 3.38f, 2.58f, 6.16f, 5.83f, 6.7f);
        c.o(pathBuilderA, 20.0f, 6.0f, -3.0f, 1.0f);
        a.A(pathBuilderA, -5.0f, 2.0f, 5.0f, 1.0f);
        b.z(pathBuilderA, 5.0f, 16.0f, -2.0f, -3.0f);
        pathBuilderA.verticalLineToRelative(-3.88f);
        pathBuilderA.curveToRelative(3.47f, -0.41f, 6.17f, -3.36f, 6.17f, -6.95f);
        pathBuilderA.close();
        pathBuilderA.moveTo(4.5f, 11.0f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(5.33f, 8.0f, 4.5f, 8.0f);
        pathBuilderA.reflectiveCurveTo(3.0f, 8.67f, 3.0f, 9.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 3.67f, 11.0f, 4.5f, 11.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _naturePeople = imageVectorBuild;
        return imageVectorBuild;
    }
}
