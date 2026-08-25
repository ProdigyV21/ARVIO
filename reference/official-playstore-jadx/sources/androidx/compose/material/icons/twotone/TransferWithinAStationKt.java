package androidx.compose.material.icons.twotone;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_transferWithinAStation", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TransferWithinAStation", "Landroidx/compose/material/icons/Icons$TwoTone;", "getTransferWithinAStation", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TransferWithinAStationKt {
    private static ImageVector _transferWithinAStation;

    public static final ImageVector getTransferWithinAStation(Icons.TwoTone twoTone) {
        ImageVector imageVector = _transferWithinAStation;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.TransferWithinAStation", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(16.49f, 13.75f, 14.0f, 16.25f);
        pathBuilderJ.lineToRelative(2.49f, 2.5f);
        pathBuilderJ.lineTo(16.49f, 17.0f);
        e.p(pathBuilderJ, 22.0f, 17.0f, -1.5f, -5.51f);
        pathBuilderJ.moveTo(19.51f, 19.75f);
        a.h(pathBuilderJ, 14.0f, 19.75f, 1.5f, 5.51f);
        pathBuilderJ.lineTo(19.51f, 23.0f);
        b.y(pathBuilderJ, 22.0f, 20.5f, 19.51f, 18.0f);
        pathBuilderJ.moveTo(7.5f, 3.5f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(9.55f, 10.4f);
        pathBuilderJ.curveTo(10.85f, 12.0f, 12.8f, 13.0f, 15.0f, 13.0f);
        pathBuilderJ.verticalLineToRelative(-2.0f);
        pathBuilderJ.curveToRelative(-1.85f, 0.0f, -3.45f, -1.0f, -4.35f, -2.45f);
        pathBuilderJ.lineToRelative(-0.95f, -1.6f);
        pathBuilderJ.curveTo(9.35f, 6.35f, 8.7f, 6.0f, 8.0f, 6.0f);
        pathBuilderJ.curveToRelative(-0.25f, 0.0f, -0.5f, 0.05f, -0.75f, 0.15f);
        pathBuilderJ.lineTo(2.0f, 8.3f);
        pathBuilderJ.lineTo(2.0f, 13.0f);
        pathBuilderJ.horizontalLineToRelative(2.0f);
        pathBuilderJ.lineTo(4.0f, 9.65f);
        pathBuilderJ.lineToRelative(1.75f, -0.75f);
        pathBuilderJ.lineTo(3.0f, 23.0f);
        pathBuilderJ.horizontalLineToRelative(2.1f);
        pathBuilderJ.lineToRelative(1.75f, -8.0f);
        a.h(pathBuilderJ, 9.0f, 17.0f, 6.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(-7.55f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderJ, 8.95f, 13.4f, 0.6f, -3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _transferWithinAStation = imageVectorBuild;
        return imageVectorBuild;
    }
}
