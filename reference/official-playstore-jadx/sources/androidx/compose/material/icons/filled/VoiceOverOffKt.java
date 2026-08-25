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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_voiceOverOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VoiceOverOff", "Landroidx/compose/material/icons/Icons$Filled;", "getVoiceOverOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VoiceOverOffKt {
    private static ImageVector _voiceOverOff;

    public static final ImageVector getVoiceOverOff(Icons.Filled filled) {
        ImageVector imageVector = _voiceOverOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.VoiceOverOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.99f, 9.18f);
        pathBuilderA.curveToRelative(0.0f, -0.06f, 0.01f, -0.12f, 0.01f, -0.18f);
        pathBuilderA.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilderA.curveToRelative(-0.06f, 0.0f, -0.12f, 0.01f, -0.18f, 0.01f);
        b.C(pathBuilderA, 4.17f, 4.17f, 6.89f, 5.62f);
        pathBuilderA.lineTo(4.27f, 3.0f);
        pathBuilderA.lineTo(3.0f, 4.27f);
        pathBuilderA.lineToRelative(2.62f, 2.62f);
        pathBuilderA.curveTo(5.23f, 7.5f, 5.0f, 8.22f, 5.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderA.curveToRelative(0.78f, 0.0f, 1.5f, -0.23f, 2.11f, -0.62f);
        pathBuilderA.lineTo(19.73f, 21.0f);
        pathBuilderA.lineTo(21.0f, 19.73f);
        b.D(pathBuilderA, -8.62f, -8.62f, -5.49f, -5.49f);
        pathBuilderA.moveTo(9.0f, 15.0f);
        pathBuilderA.curveToRelative(-2.67f, 0.0f, -8.0f, 1.34f, -8.0f, 4.0f);
        e.l(pathBuilderA, 2.0f, 16.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -2.66f, -5.33f, -4.0f, -8.0f, -4.0f);
        a.z(pathBuilderA, 16.76f, 5.36f, -1.68f, 1.69f);
        pathBuilderA.curveToRelative(0.84f, 1.18f, 0.84f, 2.71f, 0.0f, 3.89f);
        pathBuilderA.lineToRelative(1.68f, 1.69f);
        pathBuilderA.curveToRelative(2.02f, -2.02f, 2.02f, -5.07f, 0.0f, -7.27f);
        a.z(pathBuilderA, 20.07f, 2.0f, -1.63f, 1.63f);
        pathBuilderA.curveToRelative(2.77f, 3.02f, 2.77f, 7.56f, 0.0f, 10.74f);
        pathBuilderA.lineTo(20.07f, 16.0f);
        pathBuilderA.curveToRelative(3.9f, -3.89f, 3.91f, -9.95f, 0.0f, -14.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _voiceOverOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
