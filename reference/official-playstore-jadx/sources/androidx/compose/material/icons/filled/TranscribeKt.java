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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_transcribe", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Transcribe", "Landroidx/compose/material/icons/Icons$Filled;", "getTranscribe", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TranscribeKt {
    private static ImageVector _transcribe;

    public static final ImageVector getTranscribe(Icons.Filled filled) {
        ImageVector imageVector = _transcribe;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Transcribe", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(17.93f, 16.0f, 1.63f, -1.63f);
        pathBuilderR.curveToRelative(-2.77f, -3.02f, -2.77f, -7.56f, 0.0f, -10.74f);
        pathBuilderR.lineTo(17.93f, 2.0f);
        pathBuilderR.curveTo(14.03f, 5.89f, 14.02f, 11.95f, 17.93f, 16.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(22.92f, 10.95f);
        pathBuilderR.curveToRelative(-0.84f, -1.18f, -0.84f, -2.71f, 0.0f, -3.89f);
        pathBuilderR.lineToRelative(-1.68f, -1.69f);
        pathBuilderR.curveToRelative(-2.02f, 2.02f, -2.02f, 5.07f, 0.0f, 7.27f);
        c.z(pathBuilderR, 22.92f, 10.95f, 9.0f, 13.0f);
        pathBuilderR.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderR.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilderR.reflectiveCurveTo(5.0f, 6.79f, 5.0f, 9.0f);
        pathBuilderR.curveTo(5.0f, 11.21f, 6.79f, 13.0f, 9.0f, 13.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(15.39f, 15.56f);
        pathBuilderR.curveTo(13.71f, 14.7f, 11.53f, 14.0f, 9.0f, 14.0f);
        pathBuilderR.curveToRelative(-2.53f, 0.0f, -4.71f, 0.7f, -6.39f, 1.56f);
        pathBuilderR.curveTo(1.61f, 16.07f, 1.0f, 17.1f, 1.0f, 18.22f);
        w.a.j(pathBuilderR, 21.0f, 16.0f, -2.78f);
        pathBuilderR.curveTo(17.0f, 17.1f, 16.39f, 16.07f, 15.39f, 15.56f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _transcribe = imageVectorBuild;
        return imageVectorBuild;
    }
}
