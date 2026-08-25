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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_workOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WorkOff", "Landroidx/compose/material/icons/Icons$Filled;", "getWorkOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WorkOffKt {
    private static ImageVector _workOff;

    public static final ImageVector getWorkOff(Icons.Filled filled) {
        ImageVector imageVector = _workOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.WorkOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(23.0f, 21.74f, -1.46f, -1.46f);
        pathBuilderR.lineTo(7.21f, 5.95f);
        pathBuilderR.lineTo(3.25f, 1.99f);
        pathBuilderR.lineTo(1.99f, 3.25f);
        pathBuilderR.lineToRelative(2.7f, 2.7f);
        pathBuilderR.horizontalLineToRelative(-0.64f);
        pathBuilderR.curveToRelative(-1.11f, 0.0f, -1.99f, 0.89f, -1.99f, 2.0f);
        pathBuilderR.lineToRelative(-0.01f, 11.0f);
        pathBuilderR.curveToRelative(0.0f, 1.11f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(15.64f);
        b.y(pathBuilderR, 21.74f, 23.0f, 23.0f, 21.74f);
        pathBuilderR.moveTo(22.0f, 7.95f);
        pathBuilderR.curveToRelative(0.05f, -1.11f, -0.84f, -2.0f, -1.95f, -1.95f);
        pathBuilderR.horizontalLineToRelative(-4.0f);
        pathBuilderR.verticalLineTo(3.95f);
        pathBuilderR.curveToRelative(0.0f, -1.11f, -0.89f, -2.0f, -2.0f, -1.95f);
        pathBuilderR.horizontalLineToRelative(-4.0f);
        pathBuilderR.curveToRelative(-1.11f, -0.05f, -2.0f, 0.84f, -2.0f, 1.95f);
        a0.b.o(pathBuilderR, 0.32f, 13.95f, 14.0f, 7.95f);
        c.B(pathBuilderR, 14.05f, 6.0f, 10.0f, 3.95f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderR, 4.05f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _workOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
