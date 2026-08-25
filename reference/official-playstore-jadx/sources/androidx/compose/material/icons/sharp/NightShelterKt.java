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
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nightShelter", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NightShelter", "Landroidx/compose/material/icons/Icons$Sharp;", "getNightShelter", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NightShelterKt {
    private static ImageVector _nightShelter;

    public static final ImageVector getNightShelter(Icons.Sharp sharp) {
        ImageVector imageVector = _nightShelter;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.NightShelter", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(12.0f, 3.0f, 4.0f, 9.0f, 12.0f);
        b.z(pathBuilderS, 16.0f, 9.0f, 12.0f, 3.0f);
        pathBuilderS.moveTo(9.75f, 12.5f);
        pathBuilderS.curveToRelative(0.69f, 0.0f, 1.25f, 0.56f, 1.25f, 1.25f);
        pathBuilderS.reflectiveCurveTo(10.44f, 15.0f, 9.75f, 15.0f);
        pathBuilderS.reflectiveCurveTo(8.5f, 14.44f, 8.5f, 13.75f);
        pathBuilderS.reflectiveCurveTo(9.06f, 12.5f, 9.75f, 12.5f);
        v.b.w(pathBuilderS, 17.0f, 18.0f, -1.0f, -1.5f);
        a.m(pathBuilderS, 8.0f, 18.0f, 7.0f, -7.0f);
        a0.a.j(pathBuilderS, 1.0f, 4.5f, 3.5f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.m(pathBuilderS, 17.0f, 18.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nightShelter = imageVectorBuild;
        return imageVectorBuild;
    }
}
