package androidx.compose.material.icons.automirrored.filled;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_manageSearch", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ManageSearch", "Landroidx/compose/material/icons/Icons$AutoMirrored$Filled;", "getManageSearch", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ManageSearchKt {
    private static ImageVector _manageSearch;

    public static final ImageVector getManageSearch(Icons.AutoMirrored.Filled filled) {
        ImageVector imageVector = _manageSearch;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Filled.ManageSearch", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = a.c(7.0f, 9.0f, 2.0f, 7.0f, 5.0f);
        a.B(pathBuilderC, 9.0f, 7.0f, 12.0f, 2.0f);
        a.t(pathBuilderC, 2.0f, 5.0f, 12.0f);
        pathBuilderC.moveTo(20.59f, 19.0f);
        pathBuilderC.lineToRelative(-3.83f, -3.83f);
        pathBuilderC.curveTo(15.96f, 15.69f, 15.02f, 16.0f, 14.0f, 16.0f);
        pathBuilderC.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderC.reflectiveCurveToRelative(2.24f, -5.0f, 5.0f, -5.0f);
        pathBuilderC.reflectiveCurveToRelative(5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderC.curveToRelative(0.0f, 1.02f, -0.31f, 1.96f, -0.83f, 2.75f);
        b.y(pathBuilderC, 22.0f, 17.59f, 20.59f, 19.0f);
        pathBuilderC.moveTo(17.0f, 11.0f);
        pathBuilderC.curveToRelative(0.0f, -1.65f, -1.35f, -3.0f, -3.0f, -3.0f);
        pathBuilderC.reflectiveCurveToRelative(-3.0f, 1.35f, -3.0f, 3.0f);
        pathBuilderC.reflectiveCurveToRelative(1.35f, 3.0f, 3.0f, 3.0f);
        pathBuilderC.reflectiveCurveTo(17.0f, 12.65f, 17.0f, 11.0f);
        b.w(pathBuilderC, 2.0f, 19.0f, 10.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.m(pathBuilderC, 2.0f, 19.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _manageSearch = imageVectorBuild;
        return imageVectorBuild;
    }
}
