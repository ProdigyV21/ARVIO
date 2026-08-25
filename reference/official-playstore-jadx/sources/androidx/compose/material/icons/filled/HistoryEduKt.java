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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_historyEdu", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HistoryEdu", "Landroidx/compose/material/icons/Icons$Filled;", "getHistoryEdu", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HistoryEduKt {
    private static ImageVector _historyEdu;

    public static final ImageVector getHistoryEdu(Icons.Filled filled) {
        ImageVector imageVector = _historyEdu;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.HistoryEdu", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(9.0f, 4.0f, 1.38f);
        pathBuilderI.curveToRelative(-0.83f, -0.33f, -1.72f, -0.5f, -2.61f, -0.5f);
        pathBuilderI.curveToRelative(-1.79f, 0.0f, -3.58f, 0.68f, -4.95f, 2.05f);
        a0.a.m(pathBuilderI, 3.33f, 3.33f, 1.11f, 1.11f);
        pathBuilderI.curveToRelative(0.86f, 0.86f, 1.98f, 1.31f, 3.11f, 1.36f);
        a0.a.s(pathBuilderI, 15.0f, 6.0f, 3.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderI.horizontalLineToRelative(10.0f);
        pathBuilderI.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        c.n(pathBuilderI, 4.0f, 9.0f, 7.89f, 10.41f);
        pathBuilderI.verticalLineTo(8.26f);
        pathBuilderI.horizontalLineTo(5.61f);
        pathBuilderI.lineTo(4.57f, 7.22f);
        pathBuilderI.curveTo(5.14f, 7.0f, 5.76f, 6.88f, 6.39f, 6.88f);
        pathBuilderI.curveToRelative(1.34f, 0.0f, 2.59f, 0.52f, 3.54f, 1.46f);
        pathBuilderI.lineToRelative(1.41f, 1.41f);
        pathBuilderI.lineToRelative(-0.2f, 0.2f);
        pathBuilderI.curveToRelative(-0.51f, 0.51f, -1.19f, 0.8f, -1.92f, 0.8f);
        pathBuilderI.curveTo(8.75f, 10.75f, 8.29f, 10.63f, 7.89f, 10.41f);
        pathBuilderI.close();
        pathBuilderI.moveTo(19.0f, 17.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderI.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        e.l(pathBuilderI, -2.0f, -6.0f, -2.59f);
        pathBuilderI.curveToRelative(0.57f, -0.23f, 1.1f, -0.57f, 1.56f, -1.03f);
        pathBuilderI.lineToRelative(0.2f, -0.2f);
        b.m(pathBuilderI, 15.59f, 14.0f, 17.0f, -1.41f);
        b.o(pathBuilderI, -6.0f, -5.97f, 6.0f, 8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderI, 17.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _historyEdu = imageVectorBuild;
        return imageVectorBuild;
    }
}
