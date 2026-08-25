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
import v.d;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_volumeUp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VolumeUp", "Landroidx/compose/material/icons/Icons$Sharp;", "getVolumeUp$annotations", "(Landroidx/compose/material/icons/Icons$Sharp;)V", "getVolumeUp", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VolumeUpKt {
    private static ImageVector _volumeUp;

    public static final ImageVector getVolumeUp(Icons.Sharp sharp) {
        ImageVector imageVector = _volumeUp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.VolumeUp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = b.a(3.0f, 9.0f, 6.0f, 4.0f);
        pathBuilderA.lineToRelative(5.0f, 5.0f);
        pathBuilderA.lineTo(12.0f, 4.0f);
        b.y(pathBuilderA, 7.0f, 9.0f, 3.0f, 9.0f);
        pathBuilderA.moveTo(16.5f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.77f, -1.02f, -3.29f, -2.5f, -4.03f);
        pathBuilderA.verticalLineToRelative(8.05f);
        pathBuilderA.curveToRelative(1.48f, -0.73f, 2.5f, -2.25f, 2.5f, -4.02f);
        d.k(pathBuilderA, 14.0f, 3.23f, 2.06f);
        pathBuilderA.curveToRelative(2.89f, 0.86f, 5.0f, 3.54f, 5.0f, 6.71f);
        pathBuilderA.reflectiveCurveToRelative(-2.11f, 5.85f, -5.0f, 6.71f);
        pathBuilderA.verticalLineToRelative(2.06f);
        pathBuilderA.curveToRelative(4.01f, -0.91f, 7.0f, -4.49f, 7.0f, -8.77f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, -2.99f, -7.86f, -7.0f, -8.77f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _volumeUp = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getVolumeUp$annotations(Icons.Sharp sharp) {
    }
}
