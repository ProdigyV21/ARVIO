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
import v.b;
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_deleteForever", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DeleteForever", "Landroidx/compose/material/icons/Icons$Rounded;", "getDeleteForever", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DeleteForeverKt {
    private static ImageVector _deleteForever;

    public static final ImageVector getDeleteForever(Icons.Rounded rounded) {
        ImageVector imageVector = _deleteForever;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DeleteForever", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(6.0f, 19.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(8.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        a.v(pathBuilderA, 7.0f, 6.0f, 19.0f);
        pathBuilderA.moveTo(9.17f, 12.59f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderA.lineTo(12.0f, 12.59f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        pathBuilderA.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderA.reflectiveCurveToRelative(0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.lineTo(13.41f, 14.0f);
        pathBuilderA.lineToRelative(1.41f, 1.41f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderA.reflectiveCurveToRelative(-1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderA.lineTo(12.0f, 15.41f);
        pathBuilderA.lineToRelative(-1.41f, 1.41f);
        pathBuilderA.curveToRelative(-0.39f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        b.y(pathBuilderA, 10.59f, 14.0f, 9.17f, 12.59f);
        pathBuilderA.moveTo(18.0f, 4.0f);
        pathBuilderA.horizontalLineToRelative(-2.5f);
        pathBuilderA.lineToRelative(-0.71f, -0.71f);
        pathBuilderA.curveTo(14.61f, 3.11f, 14.35f, 3.0f, 14.09f, 3.0f);
        pathBuilderA.horizontalLineTo(9.91f);
        pathBuilderA.curveToRelative(-0.26f, 0.0f, -0.52f, 0.11f, -0.7f, 0.29f);
        pathBuilderA.lineTo(8.5f, 4.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.curveTo(5.45f, 4.0f, 5.0f, 4.45f, 5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(12.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 18.55f, 4.0f, 18.0f, 4.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _deleteForever = imageVectorBuild;
        return imageVectorBuild;
    }
}
