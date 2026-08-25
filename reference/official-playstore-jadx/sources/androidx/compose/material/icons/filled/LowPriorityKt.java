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
import v.a;
import v.b;
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_lowPriority", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LowPriority", "Landroidx/compose/material/icons/Icons$Filled;", "getLowPriority", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LowPriorityKt {
    private static ImageVector _lowPriority;

    public static final ImageVector getLowPriority(Icons.Filled filled) {
        ImageVector imageVector = _lowPriority;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.LowPriority", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = c.b(14.0f, 5.0f, 8.0f, 2.0f, -8.0f);
        a.C(pathBuilderB, 14.0f, 10.5f, 8.0f, 2.0f);
        e.r(pathBuilderB, -8.0f, 14.0f, 16.0f, 8.0f);
        e.D(pathBuilderB, 2.0f, -8.0f, 2.0f, 11.5f);
        pathBuilderB.curveTo(2.0f, 15.08f, 4.92f, 18.0f, 8.5f, 18.0f);
        pathBuilderB.lineTo(9.0f, 18.0f);
        pathBuilderB.verticalLineToRelative(2.0f);
        pathBuilderB.lineToRelative(3.0f, -3.0f);
        b.u(pathBuilderB, -3.0f, -3.0f, 2.0f, -0.5f);
        pathBuilderB.curveTo(6.02f, 16.0f, 4.0f, 13.98f, 4.0f, 11.5f);
        pathBuilderB.reflectiveCurveTo(6.02f, 7.0f, 8.5f, 7.0f);
        pathBuilderB.lineTo(12.0f, 7.0f);
        pathBuilderB.lineTo(12.0f, 5.0f);
        pathBuilderB.lineTo(8.5f, 5.0f);
        pathBuilderB.curveTo(4.92f, 5.0f, 2.0f, 7.92f, 2.0f, 11.5f);
        pathBuilderB.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _lowPriority = imageVectorBuild;
        return imageVectorBuild;
    }
}
