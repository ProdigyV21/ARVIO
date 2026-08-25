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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_supervisorAccount", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SupervisorAccount", "Landroidx/compose/material/icons/Icons$Sharp;", "getSupervisorAccount", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SupervisorAccountKt {
    private static ImageVector _supervisorAccount;

    public static final ImageVector getSupervisorAccount(Icons.Sharp sharp) {
        ImageVector imageVector = _supervisorAccount;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SupervisorAccount", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.5f, 12.0f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.49f, -1.12f, 2.49f, -2.5f);
        pathBuilderA.reflectiveCurveTo(17.88f, 7.0f, 16.5f, 7.0f);
        pathBuilderA.reflectiveCurveTo(14.0f, 8.12f, 14.0f, 9.5f);
        pathBuilderA.reflectiveCurveToRelative(1.12f, 2.5f, 2.5f, 2.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 11.0f);
        pathBuilderA.curveToRelative(1.66f, 0.0f, 2.99f, -1.34f, 2.99f, -3.0f);
        pathBuilderA.reflectiveCurveTo(10.66f, 5.0f, 9.0f, 5.0f);
        pathBuilderA.reflectiveCurveTo(6.0f, 6.34f, 6.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.5f, 14.0f);
        pathBuilderA.curveToRelative(-1.83f, 0.0f, -5.5f, 0.92f, -5.5f, 2.75f);
        b.A(pathBuilderA, 11.0f, 19.0f, 11.0f, -2.25f);
        pathBuilderA.curveToRelative(0.0f, -1.83f, -3.67f, -2.75f, -5.5f, -2.75f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 13.0f);
        pathBuilderA.curveToRelative(-2.33f, 0.0f, -7.0f, 1.17f, -7.0f, 3.5f);
        b.A(pathBuilderA, 2.0f, 19.0f, 7.0f, -2.25f);
        pathBuilderA.curveToRelative(0.0f, -0.85f, 0.33f, -2.34f, 2.37f, -3.47f);
        pathBuilderA.curveTo(10.5f, 13.1f, 9.66f, 13.0f, 9.0f, 13.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _supervisorAccount = imageVectorBuild;
        return imageVectorBuild;
    }
}
