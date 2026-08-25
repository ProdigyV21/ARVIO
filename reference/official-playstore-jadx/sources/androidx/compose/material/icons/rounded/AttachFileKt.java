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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_attachFile", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AttachFile", "Landroidx/compose/material/icons/Icons$Rounded;", "getAttachFile", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AttachFileKt {
    private static ImageVector _attachFile;

    public static final ImageVector getAttachFile(Icons.Rounded rounded) {
        ImageVector imageVector = _attachFile;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AttachFile", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(16.5f, 6.75f, 10.58f);
        pathBuilderI.curveToRelative(0.0f, 2.09f, -1.53f, 3.95f, -3.61f, 4.15f);
        pathBuilderI.curveToRelative(-2.39f, 0.23f, -4.39f, -1.64f, -4.39f, -3.98f);
        pathBuilderI.verticalLineTo(5.14f);
        pathBuilderI.curveToRelative(0.0f, -1.31f, 0.94f, -2.5f, 2.24f, -2.63f);
        pathBuilderI.curveToRelative(1.5f, -0.15f, 2.76f, 1.02f, 2.76f, 2.49f);
        pathBuilderI.verticalLineToRelative(10.5f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderI.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderI.verticalLineTo(6.75f);
        pathBuilderI.curveToRelative(0.0f, -0.41f, -0.34f, -0.75f, -0.75f, -0.75f);
        pathBuilderI.reflectiveCurveToRelative(-0.75f, 0.34f, -0.75f, 0.75f);
        pathBuilderI.verticalLineToRelative(8.61f);
        pathBuilderI.curveToRelative(0.0f, 1.31f, 0.94f, 2.5f, 2.24f, 2.63f);
        pathBuilderI.curveToRelative(1.5f, 0.15f, 2.76f, -1.02f, 2.76f, -2.49f);
        pathBuilderI.verticalLineTo(5.17f);
        pathBuilderI.curveToRelative(0.0f, -2.09f, -1.53f, -3.95f, -3.61f, -4.15f);
        pathBuilderI.curveTo(9.01f, 0.79f, 7.0f, 2.66f, 7.0f, 5.0f);
        pathBuilderI.verticalLineToRelative(12.27f);
        pathBuilderI.curveToRelative(0.0f, 2.87f, 2.1f, 5.44f, 4.96f, 5.71f);
        pathBuilderI.curveToRelative(3.29f, 0.3f, 6.04f, -2.26f, 6.04f, -5.48f);
        pathBuilderI.verticalLineTo(6.75f);
        pathBuilderI.curveToRelative(0.0f, -0.41f, -0.34f, -0.75f, -0.75f, -0.75f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderI, -0.75f, 0.34f, -0.75f, 0.75f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _attachFile = imageVectorBuild;
        return imageVectorBuild;
    }
}
