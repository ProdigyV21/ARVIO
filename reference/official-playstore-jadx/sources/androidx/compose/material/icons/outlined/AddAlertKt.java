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
import v.c;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_addAlert", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AddAlert", "Landroidx/compose/material/icons/Icons$Outlined;", "getAddAlert", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AddAlertKt {
    private static ImageVector _addAlert;

    public static final ImageVector getAddAlert(Icons.Outlined outlined) {
        ImageVector imageVector = _addAlert;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.AddAlert", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(10.01f, 21.01f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.89f, 1.99f, 1.99f, 1.99f);
        pathBuilderA.reflectiveCurveToRelative(1.99f, -0.89f, 1.99f, -1.99f);
        f.x(pathBuilderA, -3.98f, 12.0f, 6.0f);
        pathBuilderA.curveToRelative(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
        d.r(pathBuilderA, 7.0f, 7.0f, 18.0f, -7.0f);
        pathBuilderA.curveToRelative(0.0f, -2.76f, 2.24f, -5.0f, 5.0f, -5.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 1.5f);
        pathBuilderA.curveToRelative(-0.83f, 0.0f, -1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderA.verticalLineToRelative(1.17f);
        pathBuilderA.curveTo(7.36f, 4.85f, 5.0f, 7.65f, 5.0f, 11.0f);
        e.v(pathBuilderA, 6.0f, -2.0f, 2.0f, 1.0f);
        f.y(pathBuilderA, 18.0f, -1.0f, -2.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(-6.0f);
        pathBuilderA.curveToRelative(0.0f, -3.35f, -2.36f, -6.15f, -5.5f, -6.83f);
        pathBuilderA.lineTo(13.5f, 3.0f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        b.w(pathBuilderA, 13.0f, 8.0f, -2.0f, 3.0f);
        a.h(pathBuilderA, 8.0f, 11.0f, 2.0f, 3.0f);
        b.z(pathBuilderA, 3.0f, 2.0f, -3.0f, 3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.c(pathBuilderA, -2.0f, -3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _addAlert = imageVectorBuild;
        return imageVectorBuild;
    }
}
