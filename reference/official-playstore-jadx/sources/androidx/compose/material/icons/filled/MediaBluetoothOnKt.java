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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_mediaBluetoothOn", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MediaBluetoothOn", "Landroidx/compose/material/icons/Icons$Filled;", "getMediaBluetoothOn", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MediaBluetoothOnKt {
    private static ImageVector _mediaBluetoothOn;

    public static final ImageVector getMediaBluetoothOn(Icons.Filled filled) {
        ImageVector imageVector = _mediaBluetoothOn;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.MediaBluetoothOn", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(9.0f, 3.0f, 0.01f, 10.55f);
        pathBuilderR.curveToRelative(-0.6f, -0.34f, -1.28f, -0.55f, -2.0f, -0.55f);
        pathBuilderR.curveTo(4.79f, 13.0f, 3.0f, 14.79f, 3.0f, 17.0f);
        pathBuilderR.reflectiveCurveToRelative(1.79f, 4.0f, 4.01f, 4.0f);
        pathBuilderR.reflectiveCurveTo(11.0f, 19.21f, 11.0f, 17.0f);
        pathBuilderR.verticalLineTo(7.0f);
        pathBuilderR.horizontalLineToRelative(4.0f);
        pathBuilderR.verticalLineTo(3.0f);
        pathBuilderR.horizontalLineTo(9.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(21.0f, 12.43f);
        b.A(pathBuilderR, 17.57f, 9.0f, -0.6f, 4.55f);
        pathBuilderR.lineToRelative(-2.75f, -2.75f);
        pathBuilderR.lineToRelative(-0.85f, 0.85f);
        pathBuilderR.lineTo(16.73f, 15.0f);
        pathBuilderR.lineToRelative(-3.35f, 3.35f);
        pathBuilderR.lineToRelative(0.85f, 0.85f);
        b.o(pathBuilderR, 2.75f, -2.75f, 21.0f, 0.6f);
        pathBuilderR.lineTo(21.0f, 17.57f);
        b.y(pathBuilderR, 18.42f, 15.0f, 21.0f, 12.43f);
        pathBuilderR.moveTo(18.17f, 11.3f);
        pathBuilderR.lineToRelative(1.13f, 1.13f);
        c.e(pathBuilderR, -1.13f, 1.13f, 11.3f);
        pathBuilderR.moveTo(19.3f, 17.57f);
        pathBuilderR.lineToRelative(-1.13f, 1.13f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.j(pathBuilderR, -2.26f, 19.3f, 17.57f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _mediaBluetoothOn = imageVectorBuild;
        return imageVectorBuild;
    }
}
