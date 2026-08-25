package androidx.compose.material.icons.twotone;

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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cropRotate", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CropRotate", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCropRotate", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CropRotateKt {
    private static ImageVector _cropRotate;

    public static final ImageVector getCropRotate(Icons.TwoTone twoTone) {
        ImageVector imageVector = _cropRotate;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.CropRotate", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.95f, 24.0f);
        pathBuilderA.curveToRelative(0.23f, 0.0f, 0.44f, -0.02f, 0.66f, -0.03f);
        pathBuilderA.lineTo(8.8f, 20.15f);
        pathBuilderA.lineToRelative(-1.33f, 1.34f);
        pathBuilderA.curveTo(4.2f, 19.93f, 1.86f, 16.76f, 1.5f, 13.0f);
        pathBuilderA.lineTo(0.0f, 13.0f);
        pathBuilderA.curveToRelative(0.51f, 6.16f, 5.66f, 11.0f, 11.95f, 11.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.05f, 0.0f);
        pathBuilderA.curveToRelative(-0.23f, 0.0f, -0.44f, 0.02f, -0.66f, 0.04f);
        pathBuilderA.lineToRelative(3.81f, 3.81f);
        pathBuilderA.lineToRelative(1.33f, -1.33f);
        pathBuilderA.curveTo(19.8f, 4.07f, 22.14f, 7.24f, 22.5f, 11.0f);
        pathBuilderA.lineTo(24.0f, 11.0f);
        pathBuilderA.curveToRelative(-0.51f, -6.16f, -5.66f, -11.0f, -11.95f, -11.0f);
        b.w(pathBuilderA, 16.0f, 6.0f, -6.0f, 2.0f);
        e.g(pathBuilderA, 6.0f, 6.0f, 2.0f);
        pathBuilderA.lineTo(18.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, -1.11f, -0.9f, -2.0f, -2.0f, -2.0f);
        b.w(pathBuilderA, 18.0f, 18.0f, 2.0f, -2.0f);
        pathBuilderA.lineTo(8.0f, 16.0f);
        pathBuilderA.lineTo(8.0f, 4.0f);
        pathBuilderA.lineTo(6.0f, 4.0f);
        d.r(pathBuilderA, 2.0f, 4.0f, 6.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.verticalLineToRelative(8.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.h(pathBuilderA, 8.0f, 2.0f, 2.0f, -2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cropRotate = imageVectorBuild;
        return imageVectorBuild;
    }
}
