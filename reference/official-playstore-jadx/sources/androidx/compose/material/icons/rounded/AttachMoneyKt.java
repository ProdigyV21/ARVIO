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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_attachMoney", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AttachMoney", "Landroidx/compose/material/icons/Icons$Rounded;", "getAttachMoney", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AttachMoneyKt {
    private static ImageVector _attachMoney;

    public static final ImageVector getAttachMoney(Icons.Rounded rounded) {
        ImageVector imageVector = _attachMoney;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AttachMoney", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.8f, 10.9f);
        pathBuilderA.curveToRelative(-2.27f, -0.59f, -3.0f, -1.2f, -3.0f, -2.15f);
        pathBuilderA.curveToRelative(0.0f, -1.09f, 1.01f, -1.85f, 2.7f, -1.85f);
        pathBuilderA.curveToRelative(1.42f, 0.0f, 2.13f, 0.54f, 2.39f, 1.4f);
        pathBuilderA.curveToRelative(0.12f, 0.4f, 0.45f, 0.7f, 0.87f, 0.7f);
        pathBuilderA.horizontalLineToRelative(0.3f);
        pathBuilderA.curveToRelative(0.66f, 0.0f, 1.13f, -0.65f, 0.9f, -1.27f);
        pathBuilderA.curveToRelative(-0.42f, -1.18f, -1.4f, -2.16f, -2.96f, -2.54f);
        pathBuilderA.verticalLineTo(4.5f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(10.0f, 3.67f, 10.0f, 4.5f);
        pathBuilderA.verticalLineToRelative(0.66f);
        pathBuilderA.curveToRelative(-1.94f, 0.42f, -3.5f, 1.68f, -3.5f, 3.61f);
        pathBuilderA.curveToRelative(0.0f, 2.31f, 1.91f, 3.46f, 4.7f, 4.13f);
        pathBuilderA.curveToRelative(2.5f, 0.6f, 3.0f, 1.48f, 3.0f, 2.41f);
        pathBuilderA.curveToRelative(0.0f, 0.69f, -0.49f, 1.79f, -2.7f, 1.79f);
        pathBuilderA.curveToRelative(-1.65f, 0.0f, -2.5f, -0.59f, -2.83f, -1.43f);
        pathBuilderA.curveToRelative(-0.15f, -0.39f, -0.49f, -0.67f, -0.9f, -0.67f);
        pathBuilderA.horizontalLineToRelative(-0.28f);
        pathBuilderA.curveToRelative(-0.67f, 0.0f, -1.14f, 0.68f, -0.89f, 1.3f);
        pathBuilderA.curveToRelative(0.57f, 1.39f, 1.9f, 2.21f, 3.4f, 2.53f);
        pathBuilderA.verticalLineToRelative(0.67f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderA.verticalLineToRelative(-0.65f);
        pathBuilderA.curveToRelative(1.95f, -0.37f, 3.5f, -1.5f, 3.5f, -3.55f);
        pathBuilderA.curveToRelative(0.0f, -2.84f, -2.43f, -3.81f, -4.7f, -4.4f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _attachMoney = imageVectorBuild;
        return imageVectorBuild;
    }
}
