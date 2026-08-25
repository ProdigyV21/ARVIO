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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_addLink", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AddLink", "Landroidx/compose/material/icons/Icons$Rounded;", "getAddLink", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AddLinkKt {
    private static ImageVector _addLink;

    public static final ImageVector getAddLink(Icons.Rounded rounded) {
        ImageVector imageVector = _addLink;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AddLink", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(9.0f, 11.0f, 6.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.horizontalLineTo(9.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveTo(8.0f, 11.45f, 8.45f, 11.0f, 9.0f, 11.0f);
        a.p(pathBuilderA, 20.93f, 12.0f, 20.93f, 12.0f);
        pathBuilderA.curveToRelative(0.62f, 0.0f, 1.07f, -0.59f, 0.93f, -1.19f);
        pathBuilderA.curveTo(21.32f, 8.62f, 19.35f, 7.0f, 17.0f, 7.0f);
        pathBuilderA.horizontalLineToRelative(-3.05f);
        pathBuilderA.curveTo(13.43f, 7.0f, 13.0f, 7.43f, 13.0f, 7.95f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, 0.52f, 0.43f, 0.95f, 0.95f, 0.95f);
        pathBuilderA.horizontalLineTo(17.0f);
        pathBuilderA.curveToRelative(1.45f, 0.0f, 2.67f, 1.0f, 3.01f, 2.34f);
        pathBuilderA.curveTo(20.12f, 11.68f, 20.48f, 12.0f, 20.93f, 12.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(3.96f, 11.38f);
        pathBuilderA.curveTo(4.24f, 9.91f, 5.62f, 8.9f, 7.12f, 8.9f);
        pathBuilderA.lineToRelative(2.93f, 0.0f);
        pathBuilderA.curveTo(10.57f, 8.9f, 11.0f, 8.47f, 11.0f, 7.95f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveTo(11.0f, 7.43f, 10.57f, 7.0f, 10.05f, 7.0f);
        pathBuilderA.lineTo(7.22f, 7.0f);
        pathBuilderA.curveToRelative(-2.61f, 0.0f, -4.94f, 1.91f, -5.19f, 4.51f);
        pathBuilderA.curveTo(1.74f, 14.49f, 4.08f, 17.0f, 7.0f, 17.0f);
        pathBuilderA.horizontalLineToRelative(3.05f);
        pathBuilderA.curveToRelative(0.52f, 0.0f, 0.95f, -0.43f, 0.95f, -0.95f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, -0.52f, -0.43f, -0.95f, -0.95f, -0.95f);
        pathBuilderA.horizontalLineTo(7.0f);
        pathBuilderA.curveTo(5.09f, 15.1f, 3.58f, 13.36f, 3.96f, 11.38f);
        a.p(pathBuilderA, 18.0f, 12.0f, 18.0f, 12.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.verticalLineToRelative(-2.0f);
        pathBuilderA.curveTo(19.0f, 12.45f, 18.55f, 12.0f, 18.0f, 12.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _addLink = imageVectorBuild;
        return imageVectorBuild;
    }
}
