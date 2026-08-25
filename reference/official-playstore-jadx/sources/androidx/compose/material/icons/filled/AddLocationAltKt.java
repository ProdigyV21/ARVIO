package androidx.compose.material.icons.filled;

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
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_addLocationAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AddLocationAlt", "Landroidx/compose/material/icons/Icons$Filled;", "getAddLocationAlt", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AddLocationAltKt {
    private static ImageVector _addLocationAlt;

    public static final ImageVector getAddLocationAlt(Icons.Filled filled) {
        ImageVector imageVector = _addLocationAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.AddLocationAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(20.0f, 1.0f, 3.0f, 3.0f, 2.0f);
        e.g(pathBuilderU, -3.0f, 3.0f, -2.0f);
        pathBuilderU.lineTo(18.0f, 6.0f);
        c.A(pathBuilderU, -3.0f, 15.0f, 4.0f, 3.0f);
        f.n(pathBuilderU, 18.0f, 1.0f, 2.0f);
        pathBuilderU.moveTo(12.0f, 13.0f);
        pathBuilderU.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderU.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderU.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderU.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        v.a.p(pathBuilderU, 14.0f, 3.25f, 14.0f, 7.0f);
        e.g(pathBuilderU, 3.0f, 3.0f, 2.92f);
        pathBuilderU.curveToRelative(0.05f, 0.39f, 0.08f, 0.79f, 0.08f, 1.2f);
        pathBuilderU.curveToRelative(0.0f, 3.32f, -2.67f, 7.25f, -8.0f, 11.8f);
        pathBuilderU.curveToRelative(-5.33f, -4.55f, -8.0f, -8.48f, -8.0f, -11.8f);
        pathBuilderU.curveTo(4.0f, 6.22f, 7.8f, 3.0f, 12.0f, 3.0f);
        pathBuilderU.curveToRelative(0.68f, 0.0f, 1.35f, 0.08f, 2.0f, 0.25f);
        pathBuilderU.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderU.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _addLocationAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
