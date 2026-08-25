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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_attachment", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Attachment", "Landroidx/compose/material/icons/Icons$Rounded;", "getAttachment", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AttachmentKt {
    private static ImageVector _attachment;

    public static final ImageVector getAttachment(Icons.Rounded rounded) {
        ImageVector imageVector = _attachment;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Attachment", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(17.75f, 16.0f, 7.17f);
        pathBuilderQ.curveToRelative(-2.09f, 0.0f, -3.95f, -1.53f, -4.15f, -3.61f);
        pathBuilderQ.curveTo(2.79f, 10.01f, 4.66f, 8.0f, 7.0f, 8.0f);
        pathBuilderQ.horizontalLineToRelative(12.36f);
        pathBuilderQ.curveToRelative(1.31f, 0.0f, 2.5f, 0.94f, 2.63f, 2.24f);
        pathBuilderQ.curveToRelative(0.15f, 1.5f, -1.02f, 2.76f, -2.49f, 2.76f);
        pathBuilderQ.horizontalLineTo(9.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderQ.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(8.75f);
        pathBuilderQ.curveToRelative(0.41f, 0.0f, 0.75f, -0.34f, 0.75f, -0.75f);
        pathBuilderQ.reflectiveCurveToRelative(-0.34f, -0.75f, -0.75f, -0.75f);
        pathBuilderQ.horizontalLineTo(9.14f);
        pathBuilderQ.curveToRelative(-1.31f, 0.0f, -2.5f, 0.94f, -2.63f, 2.24f);
        pathBuilderQ.curveToRelative(-0.15f, 1.5f, 1.02f, 2.76f, 2.49f, 2.76f);
        pathBuilderQ.horizontalLineToRelative(10.33f);
        pathBuilderQ.curveToRelative(2.09f, 0.0f, 3.95f, -1.53f, 4.15f, -3.61f);
        pathBuilderQ.curveToRelative(0.23f, -2.39f, -1.64f, -4.39f, -3.98f, -4.39f);
        pathBuilderQ.horizontalLineTo(7.23f);
        pathBuilderQ.curveToRelative(-2.87f, 0.0f, -5.44f, 2.1f, -5.71f, 4.96f);
        pathBuilderQ.curveToRelative(-0.3f, 3.29f, 2.26f, 6.04f, 5.48f, 6.04f);
        pathBuilderQ.horizontalLineToRelative(10.75f);
        pathBuilderQ.curveToRelative(0.41f, 0.0f, 0.75f, -0.34f, 0.75f, -0.75f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderQ, -0.34f, -0.75f, -0.75f, -0.75f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _attachment = imageVectorBuild;
        return imageVectorBuild;
    }
}
