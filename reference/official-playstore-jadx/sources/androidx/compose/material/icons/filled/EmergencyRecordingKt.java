package androidx.compose.material.icons.filled;

import a0.b;
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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_emergencyRecording", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EmergencyRecording", "Landroidx/compose/material/icons/Icons$Filled;", "getEmergencyRecording", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EmergencyRecordingKt {
    private static ImageVector _emergencyRecording;

    public static final ImageVector getEmergencyRecording(Icons.Filled filled) {
        ImageVector imageVector = _emergencyRecording;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.EmergencyRecording", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(18.0f, 10.48f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.curveTo(2.9f, 4.0f, 2.0f, 4.9f, 2.0f, 6.0f);
        pathBuilderA.verticalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        e.v(pathBuilderA, -4.48f, 4.0f, 3.98f, -11.0f);
        c.z(pathBuilderA, 18.0f, 10.48f, 12.0f, 12.0f);
        pathBuilderA.lineToRelative(3.0f, 1.73f);
        pathBuilderA.lineToRelative(-1.0f, 1.73f);
        b.m(pathBuilderA, -3.0f, -1.73f, 17.0f, 9.0f);
        pathBuilderA.verticalLineToRelative(-3.27f);
        pathBuilderA.lineToRelative(-3.0f, 1.73f);
        pathBuilderA.lineToRelative(-1.0f, -1.73f);
        pathBuilderA.lineTo(8.0f, 12.0f);
        pathBuilderA.lineToRelative(-3.0f, -1.73f);
        pathBuilderA.lineToRelative(1.0f, -1.73f);
        v.b.o(pathBuilderA, 3.0f, 1.73f, 7.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(3.27f);
        pathBuilderA.lineToRelative(3.0f, -1.73f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.b.e(pathBuilderA, 1.0f, 1.73f, 12.0f, 12.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _emergencyRecording = imageVectorBuild;
        return imageVectorBuild;
    }
}
