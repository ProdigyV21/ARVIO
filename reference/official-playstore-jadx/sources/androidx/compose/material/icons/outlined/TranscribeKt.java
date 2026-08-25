package androidx.compose.material.icons.outlined;

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
import v.d;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_transcribe", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Transcribe", "Landroidx/compose/material/icons/Icons$Outlined;", "getTranscribe", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TranscribeKt {
    private static ImageVector _transcribe;

    public static final ImageVector getTranscribe(Icons.Outlined outlined) {
        ImageVector imageVector = _transcribe;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Transcribe", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.0f, 13.0f);
        pathBuilderA.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilderA.reflectiveCurveTo(5.0f, 6.79f, 5.0f, 9.0f);
        pathBuilderA.curveTo(5.0f, 11.21f, 6.79f, 13.0f, 9.0f, 13.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 7.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.curveTo(7.0f, 7.9f, 7.9f, 7.0f, 9.0f, 7.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.39f, 15.56f);
        pathBuilderA.curveTo(13.71f, 14.7f, 11.53f, 14.0f, 9.0f, 14.0f);
        pathBuilderA.curveToRelative(-2.53f, 0.0f, -4.71f, 0.7f, -6.39f, 1.56f);
        pathBuilderA.curveTo(1.61f, 16.07f, 1.0f, 17.1f, 1.0f, 18.22f);
        a.j(pathBuilderA, 21.0f, 16.0f, -2.78f);
        pathBuilderA.curveTo(17.0f, 17.1f, 16.39f, 16.07f, 15.39f, 15.56f);
        c.C(pathBuilderA, 15.0f, 19.0f, 3.0f, -0.78f);
        pathBuilderA.curveToRelative(0.0f, -0.38f, 0.2f, -0.72f, 0.52f, -0.88f);
        pathBuilderA.curveTo(4.71f, 16.73f, 6.63f, 16.0f, 9.0f, 16.0f);
        pathBuilderA.curveToRelative(2.37f, 0.0f, 4.29f, 0.73f, 5.48f, 1.34f);
        pathBuilderA.curveTo(14.8f, 17.5f, 15.0f, 17.84f, 15.0f, 18.22f);
        f.r(pathBuilderA, 19.0f, 17.93f, 16.0f);
        pathBuilderA.lineToRelative(1.63f, -1.63f);
        pathBuilderA.curveToRelative(-2.77f, -3.02f, -2.77f, -7.56f, 0.0f, -10.74f);
        pathBuilderA.lineTo(17.93f, 2.0f);
        pathBuilderA.curveTo(14.03f, 5.89f, 14.02f, 11.95f, 17.93f, 16.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(22.92f, 10.95f);
        pathBuilderA.curveToRelative(-0.84f, -1.18f, -0.84f, -2.71f, 0.0f, -3.89f);
        pathBuilderA.lineToRelative(-1.68f, -1.69f);
        pathBuilderA.curveToRelative(-2.02f, 2.02f, -2.02f, 5.07f, 0.0f, 7.27f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 22.92f, 10.95f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _transcribe = imageVectorBuild;
        return imageVectorBuild;
    }
}
