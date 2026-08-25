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
import v.d;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_switchAccount", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SwitchAccount", "Landroidx/compose/material/icons/Icons$Outlined;", "getSwitchAccount", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwitchAccountKt {
    private static ImageVector _switchAccount;

    public static final ImageVector getSwitchAccount(Icons.Outlined outlined) {
        ImageVector imageVector = _switchAccount;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SwitchAccount", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = f.a(4.0f, 6.0f, 2.0f, 14.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        d.z(pathBuilderA, 14.0f, -2.0f, 4.0f, 6.0f);
        pathBuilderA.moveTo(14.0f, 11.0f);
        pathBuilderA.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderA.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.0f, 1.34f, -3.0f, 3.0f);
        pathBuilderA.curveTo(11.0f, 9.66f, 12.34f, 11.0f, 14.0f, 11.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.0f, 7.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveTo(13.45f, 7.0f, 14.0f, 7.0f);
        a.r(pathBuilderA, 20.0f, 2.0f, 8.0f);
        pathBuilderA.curveTo(6.9f, 2.0f, 6.0f, 2.9f, 6.0f, 4.0f);
        pathBuilderA.verticalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.verticalLineTo(4.0f);
        pathBuilderA.curveTo(22.0f, 2.9f, 21.1f, 2.0f, 20.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(10.69f, 16.0f);
        pathBuilderA.curveToRelative(0.95f, -0.63f, 2.09f, -1.0f, 3.31f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(2.36f, 0.37f, 3.31f, 1.0f);
        v.a.x(pathBuilderA, 10.69f, 20.0f, 15.73f);
        pathBuilderA.curveTo(18.53f, 14.06f, 16.4f, 13.0f, 14.0f, 13.0f);
        pathBuilderA.reflectiveCurveToRelative(-4.53f, 1.06f, -6.0f, 2.73f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.m(pathBuilderA, 4.0f, 12.0f, 15.73f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _switchAccount = imageVectorBuild;
        return imageVectorBuild;
    }
}
