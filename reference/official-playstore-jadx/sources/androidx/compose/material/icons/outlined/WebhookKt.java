package androidx.compose.material.icons.outlined;

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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_webhook", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Webhook", "Landroidx/compose/material/icons/Icons$Outlined;", "getWebhook", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WebhookKt {
    private static ImageVector _webhook;

    public static final ImageVector getWebhook(Icons.Outlined outlined) {
        ImageVector imageVector = _webhook;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Webhook", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(10.0f, 15.0f, 5.88f, 0.0f);
        pathBuilderR.curveToRelative(0.27f, -0.31f, 0.67f, -0.5f, 1.12f, -0.5f);
        pathBuilderR.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderR.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderR.curveToRelative(-0.44f, 0.0f, -0.84f, -0.19f, -1.12f, -0.5f);
        pathBuilderR.lineToRelative(-3.98f, 0.0f);
        pathBuilderR.curveToRelative(-0.46f, 2.28f, -2.48f, 4.0f, -4.9f, 4.0f);
        pathBuilderR.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderR.curveToRelative(0.0f, -2.42f, 1.72f, -4.44f, 4.0f, -4.9f);
        pathBuilderR.lineToRelative(0.0f, 2.07f);
        pathBuilderR.curveTo(4.84f, 13.58f, 4.0f, 14.7f, 4.0f, 16.0f);
        pathBuilderR.curveToRelative(0.0f, 1.65f, 1.35f, 3.0f, 3.0f, 3.0f);
        pathBuilderR.reflectiveCurveToRelative(3.0f, -1.35f, 3.0f, -3.0f);
        f.r(pathBuilderR, 15.0f, 12.5f, 4.0f);
        pathBuilderR.curveToRelative(1.65f, 0.0f, 3.0f, 1.35f, 3.0f, 3.0f);
        pathBuilderR.horizontalLineToRelative(2.0f);
        pathBuilderR.curveToRelative(0.0f, -2.76f, -2.24f, -5.0f, -5.0f, -5.0f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderR.curveToRelative(0.0f, 1.43f, 0.6f, 2.71f, 1.55f, 3.62f);
        pathBuilderR.lineToRelative(-2.35f, 3.9f);
        pathBuilderR.curveTo(6.02f, 14.66f, 5.5f, 15.27f, 5.5f, 16.0f);
        pathBuilderR.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilderR.reflectiveCurveToRelative(1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilderR.curveToRelative(0.0f, -0.16f, -0.02f, -0.31f, -0.07f, -0.45f);
        pathBuilderR.lineToRelative(3.38f, -5.63f);
        pathBuilderR.curveTo(10.49f, 9.61f, 9.5f, 8.42f, 9.5f, 7.0f);
        pathBuilderR.curveTo(9.5f, 5.35f, 10.85f, 4.0f, 12.5f, 4.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(17.0f, 13.0f);
        pathBuilderR.curveToRelative(-0.64f, 0.0f, -1.23f, 0.2f, -1.72f, 0.54f);
        pathBuilderR.lineToRelative(-3.05f, -5.07f);
        pathBuilderR.curveTo(11.53f, 8.35f, 11.0f, 7.74f, 11.0f, 7.0f);
        pathBuilderR.curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderR.reflectiveCurveTo(14.0f, 6.17f, 14.0f, 7.0f);
        pathBuilderR.curveToRelative(0.0f, 0.15f, -0.02f, 0.29f, -0.06f, 0.43f);
        pathBuilderR.lineToRelative(2.19f, 3.65f);
        pathBuilderR.curveTo(16.41f, 11.03f, 16.7f, 11.0f, 17.0f, 11.0f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.curveToRelative(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderR.curveToRelative(0.0f, 2.76f, -2.24f, 5.0f, -5.0f, 5.0f);
        pathBuilderR.curveToRelative(-1.85f, 0.0f, -3.47f, -1.01f, -4.33f, -2.5f);
        pathBuilderR.lineToRelative(2.67f, 0.0f);
        pathBuilderR.curveTo(15.82f, 18.82f, 16.39f, 19.0f, 17.0f, 19.0f);
        pathBuilderR.curveToRelative(1.65f, 0.0f, 3.0f, -1.35f, 3.0f, -3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderR, 18.65f, 13.0f, 17.0f, 13.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _webhook = imageVectorBuild;
        return imageVectorBuild;
    }
}
