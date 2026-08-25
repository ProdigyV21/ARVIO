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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_controlCamera", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ControlCamera", "Landroidx/compose/material/icons/Icons$Filled;", "getControlCamera", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ControlCameraKt {
    private static ImageVector _controlCamera;

    public static final ImageVector getControlCamera(Icons.Filled filled) {
        ImageVector imageVector = _controlCamera;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ControlCamera", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(15.54f, 5.54f, 13.77f, 7.3f);
        pathBuilderJ.lineTo(12.0f, 5.54f);
        pathBuilderJ.lineTo(10.23f, 7.3f);
        b.y(pathBuilderJ, 8.46f, 5.54f, 12.0f, 2.0f);
        pathBuilderJ.moveTo(18.46f, 15.54f);
        pathBuilderJ.lineToRelative(-1.76f, -1.77f);
        pathBuilderJ.lineTo(18.46f, 12.0f);
        pathBuilderJ.lineToRelative(-1.76f, -1.77f);
        d.C(pathBuilderJ, 1.76f, -1.77f, 22.0f, 12.0f);
        pathBuilderJ.moveTo(8.46f, 18.46f);
        pathBuilderJ.lineToRelative(1.77f, -1.76f);
        pathBuilderJ.lineTo(12.0f, 18.46f);
        pathBuilderJ.lineToRelative(1.77f, -1.76f);
        d.C(pathBuilderJ, 1.77f, 1.76f, 12.0f, 22.0f);
        pathBuilderJ.moveTo(5.54f, 8.46f);
        pathBuilderJ.lineToRelative(1.76f, 1.77f);
        pathBuilderJ.lineTo(5.54f, 12.0f);
        pathBuilderJ.lineToRelative(1.76f, 1.77f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderJ, -1.76f, 1.77f, 2.0f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(12.0f, 12.0f, -3.0f, 0.0f);
        pathBuilderB.arcToRelative(3.0f, 3.0f, 0.0f, true, true, 6.0f, 0.0f);
        pathBuilderB.arcToRelative(3.0f, 3.0f, 0.0f, true, true, -6.0f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _controlCamera = imageVectorBuild;
        return imageVectorBuild;
    }
}
