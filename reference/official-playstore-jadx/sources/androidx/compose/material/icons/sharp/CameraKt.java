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
import v.a;
import v.b;
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_camera", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Camera", "Landroidx/compose/material/icons/Icons$Sharp;", "getCamera", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CameraKt {
    private static ImageVector _camera;

    public static final ImageVector getCamera(Icons.Sharp sharp) {
        ImageVector imageVector = _camera;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Camera", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(9.4f, 10.5f, 4.77f, -8.26f);
        pathBuilderR.curveTo(13.47f, 2.09f, 12.75f, 2.0f, 12.0f, 2.0f);
        pathBuilderR.curveToRelative(-2.4f, 0.0f, -4.6f, 0.85f, -6.32f, 2.25f);
        b.D(pathBuilderR, 3.66f, 6.35f, 0.06f, -0.1f);
        pathBuilderR.moveTo(21.54f, 9.0f);
        pathBuilderR.curveToRelative(-0.92f, -2.92f, -3.15f, -5.26f, -6.0f, -6.34f);
        f.n(pathBuilderR, 11.88f, 9.0f, 9.66f);
        pathBuilderR.moveTo(21.8f, 10.0f);
        pathBuilderR.horizontalLineToRelative(-7.49f);
        pathBuilderR.lineToRelative(0.29f, 0.5f);
        pathBuilderR.lineToRelative(4.76f, 8.25f);
        pathBuilderR.curveTo(21.0f, 16.97f, 22.0f, 14.61f, 22.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, -0.69f, -0.07f, -1.35f, -0.2f, -2.0f);
        a.z(pathBuilderR, 8.54f, 12.0f, -3.9f, -6.75f);
        pathBuilderR.curveTo(3.01f, 7.03f, 2.0f, 9.39f, 2.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, 0.69f, 0.07f, 1.35f, 0.2f, 2.0f);
        c.v(pathBuilderR, 7.49f, -1.15f, -2.0f);
        pathBuilderR.moveTo(2.46f, 15.0f);
        pathBuilderR.curveToRelative(0.92f, 2.92f, 3.15f, 5.26f, 6.0f, 6.34f);
        b.y(pathBuilderR, 12.12f, 15.0f, 2.46f, 15.0f);
        pathBuilderR.moveTo(13.73f, 15.0f);
        pathBuilderR.lineToRelative(-3.9f, 6.76f);
        pathBuilderR.curveToRelative(0.7f, 0.15f, 1.42f, 0.24f, 2.17f, 0.24f);
        pathBuilderR.curveToRelative(2.4f, 0.0f, 4.6f, -0.85f, 6.32f, -2.25f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, -3.66f, -6.35f, -0.93f, 1.6f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _camera = imageVectorBuild;
        return imageVectorBuild;
    }
}
