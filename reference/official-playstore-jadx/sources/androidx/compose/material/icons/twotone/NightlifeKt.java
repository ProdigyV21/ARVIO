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
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nightlife", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Nightlife", "Landroidx/compose/material/icons/Icons$TwoTone;", "getNightlife", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NightlifeKt {
    private static ImageVector _nightlife;

    public static final ImageVector getNightlife(Icons.TwoTone twoTone) {
        ImageVector imageVector = _nightlife;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Nightlife", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(1.0f, 5.0f, 14.0f, -6.0f, 9.0f);
        f.u(pathBuilderG, 4.0f, 2.0f, 2.0f, 5.0f);
        e.l(pathBuilderG, -2.0f, 2.0f, -4.0f);
        c.z(pathBuilderG, 1.0f, 5.0f, 10.1f, 9.0f);
        pathBuilderG.lineToRelative(1.4f, -2.0f);
        pathBuilderG.horizontalLineTo(4.49f);
        pathBuilderG.lineToRelative(1.4f, 2.0f);
        pathBuilderG.horizontalLineTo(10.1f);
        pathBuilderG.close();
        a.C(pathBuilderG, 17.0f, 5.0f, 5.0f, 3.0f);
        f.y(pathBuilderG, -3.0f, 9.0f, 0.0f, 0.0f);
        pathBuilderG.curveToRelative(0.0f, 1.66f, -1.34f, 3.0f, -3.0f, 3.0f);
        pathBuilderG.reflectiveCurveToRelative(-3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderG.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderG.curveToRelative(0.35f, 0.0f, 0.69f, 0.06f, 1.0f, 0.17f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderG, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nightlife = imageVectorBuild;
        return imageVectorBuild;
    }
}
