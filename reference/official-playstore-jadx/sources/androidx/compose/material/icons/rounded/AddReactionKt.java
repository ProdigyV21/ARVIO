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
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_addReaction", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AddReaction", "Landroidx/compose/material/icons/Icons$Rounded;", "getAddReaction", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AddReactionKt {
    private static ImageVector _addReaction;

    public static final ImageVector getAddReaction(Icons.Rounded rounded) {
        ImageVector imageVector = _addReaction;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AddReaction", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(24.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(-1.0f);
        pathBuilderA.verticalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineTo(5.0f);
        pathBuilderA.horizontalLineToRelative(-1.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.verticalLineTo(2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(1.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.curveTo(23.55f, 3.0f, 24.0f, 3.45f, 24.0f, 4.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(21.52f, 8.95f);
        pathBuilderA.curveTo(21.83f, 9.91f, 22.0f, 10.94f, 22.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 5.52f, -4.48f, 10.0f, -10.0f, 10.0f);
        pathBuilderA.reflectiveCurveTo(2.0f, 17.52f, 2.0f, 12.0f);
        pathBuilderA.curveTo(2.0f, 6.48f, 6.48f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.curveToRelative(1.5f, 0.0f, 2.92f, 0.34f, 4.2f, 0.94f);
        pathBuilderA.curveTo(16.08f, 3.27f, 16.0f, 3.62f, 16.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 1.35f, 0.9f, 2.5f, 2.13f, 2.87f);
        pathBuilderA.curveTo(18.5f, 8.1f, 19.65f, 9.0f, 21.0f, 9.0f);
        pathBuilderA.curveTo(21.18f, 9.0f, 21.35f, 8.98f, 21.52f, 8.95f);
        pathBuilderA.close();
        pathBuilderA.moveTo(7.0f, 9.5f);
        pathBuilderA.curveTo(7.0f, 10.33f, 7.67f, 11.0f, 8.5f, 11.0f);
        pathBuilderA.reflectiveCurveTo(10.0f, 10.33f, 10.0f, 9.5f);
        pathBuilderA.reflectiveCurveTo(9.33f, 8.0f, 8.5f, 8.0f);
        pathBuilderA.reflectiveCurveTo(7.0f, 8.67f, 7.0f, 9.5f);
        a.r(pathBuilderA, 16.31f, 14.0f, 7.69f);
        pathBuilderA.curveToRelative(-0.38f, 0.0f, -0.63f, 0.42f, -0.44f, 0.75f);
        pathBuilderA.curveTo(8.2f, 16.39f, 9.97f, 17.5f, 12.0f, 17.5f);
        pathBuilderA.reflectiveCurveToRelative(3.8f, -1.11f, 4.75f, -2.75f);
        pathBuilderA.curveTo(16.94f, 14.42f, 16.7f, 14.0f, 16.31f, 14.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.0f, 9.5f);
        pathBuilderA.curveTo(17.0f, 8.67f, 16.33f, 8.0f, 15.5f, 8.0f);
        pathBuilderA.reflectiveCurveTo(14.0f, 8.67f, 14.0f, 9.5f);
        pathBuilderA.reflectiveCurveToRelative(0.67f, 1.5f, 1.5f, 1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 17.0f, 10.33f, 17.0f, 9.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _addReaction = imageVectorBuild;
        return imageVectorBuild;
    }
}
