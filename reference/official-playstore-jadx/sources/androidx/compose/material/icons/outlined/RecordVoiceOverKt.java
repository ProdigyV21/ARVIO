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
import v.b;
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_recordVoiceOver", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RecordVoiceOver", "Landroidx/compose/material/icons/Icons$Outlined;", "getRecordVoiceOver", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RecordVoiceOverKt {
    private static ImageVector _recordVoiceOver;

    public static final ImageVector getRecordVoiceOver(Icons.Outlined outlined) {
        ImageVector imageVector = _recordVoiceOver;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.RecordVoiceOver", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.0f, 13.0f);
        pathBuilderA.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilderA.reflectiveCurveToRelative(-4.0f, 1.79f, -4.0f, 4.0f);
        pathBuilderA.reflectiveCurveToRelative(1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 7.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 15.0f);
        pathBuilderA.curveToRelative(-2.67f, 0.0f, -8.0f, 1.34f, -8.0f, 4.0f);
        e.l(pathBuilderA, 2.0f, 16.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -2.66f, -5.33f, -4.0f, -8.0f, -4.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(3.0f, 19.0f);
        pathBuilderA.curveToRelative(0.22f, -0.72f, 3.31f, -2.0f, 6.0f, -2.0f);
        pathBuilderA.curveToRelative(2.7f, 0.0f, 5.8f, 1.29f, 6.0f, 2.0f);
        c.z(pathBuilderA, 3.0f, 19.0f, 15.08f, 7.05f);
        pathBuilderA.curveToRelative(0.84f, 1.18f, 0.84f, 2.71f, 0.0f, 3.89f);
        pathBuilderA.lineToRelative(1.68f, 1.69f);
        pathBuilderA.curveToRelative(2.02f, -2.02f, 2.02f, -5.07f, 0.0f, -7.27f);
        b.C(pathBuilderA, -1.68f, 1.69f, 20.07f, 2.0f);
        pathBuilderA.lineToRelative(-1.63f, 1.63f);
        pathBuilderA.curveToRelative(2.77f, 3.02f, 2.77f, 7.56f, 0.0f, 10.74f);
        pathBuilderA.lineTo(20.07f, 16.0f);
        pathBuilderA.curveToRelative(3.9f, -3.89f, 3.91f, -9.95f, 0.0f, -14.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _recordVoiceOver = imageVectorBuild;
        return imageVectorBuild;
    }
}
