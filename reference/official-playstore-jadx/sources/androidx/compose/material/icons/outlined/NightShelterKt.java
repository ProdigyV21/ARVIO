package androidx.compose.material.icons.outlined;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nightShelter", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NightShelter", "Landroidx/compose/material/icons/Icons$Outlined;", "getNightShelter", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NightShelterKt {
    private static ImageVector _nightShelter;

    public static final ImageVector getNightShelter(Icons.Outlined outlined) {
        ImageVector imageVector = _nightShelter;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.NightShelter", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = e.s(12.0f, 5.5f, 6.0f, 4.5f, 9.0f);
        pathBuilderS.horizontalLineTo(6.0f);
        pathBuilderS.verticalLineToRelative(-9.0f);
        pathBuilderS.lineTo(12.0f, 5.5f);
        pathBuilderS.moveTo(12.0f, 3.0f);
        a.h(pathBuilderS, 4.0f, 9.0f, 12.0f, 16.0f);
        pathBuilderS.verticalLineTo(9.0f);
        b.y(pathBuilderS, 12.0f, 3.0f, 12.0f, 3.0f);
        a.C(pathBuilderS, 15.0f, 12.0f, -3.5f, 3.5f);
        w.a.m(pathBuilderS, 8.0f, 11.0f, 7.0f, 7.0f);
        a0.a.j(pathBuilderS, 1.0f, -1.5f, 8.0f, 18.0f);
        pathBuilderS.horizontalLineToRelative(1.0f);
        pathBuilderS.verticalLineToRelative(-4.0f);
        pathBuilderS.curveTo(17.0f, 12.9f, 16.1f, 12.0f, 15.0f, 12.0f);
        pathBuilderS.close();
        pathBuilderS.moveTo(9.75f, 12.5f);
        pathBuilderS.curveToRelative(-0.69f, 0.0f, -1.25f, 0.56f, -1.25f, 1.25f);
        pathBuilderS.curveTo(8.5f, 14.44f, 9.06f, 15.0f, 9.75f, 15.0f);
        pathBuilderS.reflectiveCurveTo(11.0f, 14.44f, 11.0f, 13.75f);
        pathBuilderS.curveTo(11.0f, 13.06f, 10.44f, 12.5f, 9.75f, 12.5f);
        pathBuilderS.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderS.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nightShelter = imageVectorBuild;
        return imageVectorBuild;
    }
}
