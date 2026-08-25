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
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sensorDoor", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SensorDoor", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSensorDoor", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SensorDoorKt {
    private static ImageVector _sensorDoor;

    public static final ImageVector getSensorDoor(Icons.TwoTone twoTone) {
        ImageVector imageVector = _sensorDoor;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SensorDoor", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = f.b(18.0f, 4.0f, 16.0f, 6.0f, 4.0f);
        pathBuilderB.horizontalLineTo(18.0f);
        pathBuilderB.moveTo(15.5f, 10.5f);
        pathBuilderB.curveToRelative(-0.83f, 0.0f, -1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderB.reflectiveCurveToRelative(0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderB.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderB, 16.33f, 10.5f, 15.5f, 10.5f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB2 = f.b(18.0f, 4.0f, 16.0f, 6.0f, 4.0f);
        f.s(pathBuilderB2, 18.0f, 18.0f, 2.0f, 6.0f);
        pathBuilderB2.curveTo(4.9f, 2.0f, 4.0f, 2.9f, 4.0f, 4.0f);
        f.t(pathBuilderB2, 18.0f, 16.0f, 4.0f);
        pathBuilderB2.curveTo(20.0f, 2.9f, 19.1f, 2.0f, 18.0f, 2.0f);
        c.z(pathBuilderB2, 18.0f, 2.0f, 15.5f, 10.5f);
        pathBuilderB2.curveToRelative(-0.83f, 0.0f, -1.5f, 0.67f, -1.5f, 1.5f);
        pathBuilderB2.reflectiveCurveToRelative(0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderB2.curveToRelative(0.83f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderB2, 16.33f, 10.5f, 15.5f, 10.5f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sensorDoor = imageVectorBuild;
        return imageVectorBuild;
    }
}
