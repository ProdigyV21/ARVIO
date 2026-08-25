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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_checklist", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Checklist", "Landroidx/compose/material/icons/Icons$Rounded;", "getChecklist", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChecklistKt {
    private static ImageVector _checklist;

    public static final ImageVector getChecklist(Icons.Rounded rounded) {
        ImageVector imageVector = _checklist;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Checklist", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(22.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(-7.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(7.0f);
        pathBuilderA.curveTo(21.55f, 9.0f, 22.0f, 8.55f, 22.0f, 8.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(13.0f, 16.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(7.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(-7.0f);
        pathBuilderA.curveTo(13.45f, 15.0f, 13.0f, 15.45f, 13.0f, 16.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(10.47f, 4.63f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineToRelative(-4.23f, 4.25f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.42f, 0.0f);
        pathBuilderA.lineTo(2.7f, 8.16f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderA.lineToRelative(1.42f, 1.42f);
        pathBuilderA.lineToRelative(3.54f, -3.54f);
        pathBuilderA.curveTo(9.45f, 4.25f, 10.09f, 4.25f, 10.47f, 4.63f);
        pathBuilderA.close();
        pathBuilderA.moveTo(10.48f, 12.64f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineToRelative(-4.23f, 4.25f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.42f, 0.0f);
        pathBuilderA.lineTo(2.7f, 16.16f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.reflectiveCurveToRelative(1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderA.lineToRelative(1.42f, 1.42f);
        pathBuilderA.lineToRelative(3.54f, -3.54f);
        pathBuilderA.curveTo(9.45f, 12.25f, 10.09f, 12.25f, 10.48f, 12.64f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 10.48f, 12.64f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _checklist = imageVectorBuild;
        return imageVectorBuild;
    }
}
