package androidx.compose.material.icons.rounded;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_downloadDone", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DownloadDone", "Landroidx/compose/material/icons/Icons$Rounded;", "getDownloadDone", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DownloadDoneKt {
    private static ImageVector _downloadDone;

    public static final ImageVector getDownloadDone(Icons.Rounded rounded) {
        ImageVector imageVector = _downloadDone;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DownloadDone", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(6.0f, 18.0f, 12.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.lineTo(6.0f, 20.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.01f, 13.9f);
        pathBuilderA.curveToRelative(-0.78f, 0.77f, -2.04f, 0.77f, -2.82f, -0.01f);
        pathBuilderA.lineTo(6.0f, 11.7f);
        pathBuilderA.curveToRelative(-0.55f, -0.55f, -0.54f, -1.44f, 0.03f, -1.97f);
        pathBuilderA.curveToRelative(0.54f, -0.52f, 1.4f, -0.5f, 1.92f, 0.02f);
        pathBuilderA.lineTo(9.6f, 11.4f);
        pathBuilderA.lineToRelative(6.43f, -6.43f);
        pathBuilderA.curveToRelative(0.54f, -0.54f, 1.41f, -0.54f, 1.95f, 0.0f);
        pathBuilderA.lineToRelative(0.04f, 0.04f);
        pathBuilderA.curveToRelative(0.54f, 0.54f, 0.54f, 1.42f, -0.01f, 1.96f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderA, -7.0f, 6.93f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _downloadDone = imageVectorBuild;
        return imageVectorBuild;
    }
}
