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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_contactless", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Contactless", "Landroidx/compose/material/icons/Icons$Rounded;", "getContactless", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContactlessKt {
    private static ImageVector _contactless;

    public static final ImageVector getContactless(Icons.Rounded rounded) {
        ImageVector imageVector = _contactless;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Contactless", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.curveTo(22.0f, 6.48f, 17.52f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(8.75f, 13.68f);
        pathBuilderA.curveToRelative(-0.13f, 0.43f, -0.62f, 0.63f, -1.02f, 0.45f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.34f, -0.16f, -0.51f, -0.54f, -0.4f, -0.9f);
        pathBuilderA.curveToRelative(0.12f, -0.41f, 0.18f, -0.83f, 0.17f, -1.24f);
        pathBuilderA.curveToRelative(-0.01f, -0.41f, -0.06f, -0.8f, -0.17f, -1.18f);
        pathBuilderA.curveToRelative(-0.1f, -0.36f, 0.06f, -0.75f, 0.4f, -0.9f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.42f, -0.19f, 0.91f, 0.04f, 1.04f, 0.49f);
        pathBuilderA.curveToRelative(0.15f, 0.51f, 0.22f, 1.03f, 0.23f, 1.57f);
        pathBuilderA.curveTo(9.0f, 12.53f, 8.92f, 13.11f, 8.75f, 13.68f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.89f, 15.27f);
        pathBuilderA.curveToRelative(-0.17f, 0.41f, -0.67f, 0.57f, -1.06f, 0.35f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.33f, -0.19f, -0.46f, -0.59f, -0.32f, -0.94f);
        pathBuilderA.curveToRelative(0.33f, -0.77f, 0.49f, -1.63f, 0.49f, -2.56f);
        pathBuilderA.curveToRelative(0.0f, -0.96f, -0.18f, -1.89f, -0.53f, -2.78f);
        pathBuilderA.curveToRelative(-0.14f, -0.36f, 0.02f, -0.76f, 0.36f, -0.94f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.39f, -0.2f, 0.87f, -0.02f, 1.03f, 0.39f);
        pathBuilderA.curveToRelative(0.42f, 1.06f, 0.63f, 2.18f, 0.63f, 3.33f);
        pathBuilderA.curveTo(12.51f, 13.25f, 12.3f, 14.31f, 11.89f, 15.27f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.0f, 16.6f);
        pathBuilderA.curveToRelative(-0.17f, 0.4f, -0.64f, 0.58f, -1.02f, 0.39f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-0.35f, -0.17f, -0.52f, -0.59f, -0.37f, -0.95f);
        pathBuilderA.curveToRelative(0.59f, -1.39f, 0.89f, -2.75f, 0.89f, -4.06f);
        pathBuilderA.curveToRelative(0.0f, -1.31f, -0.3f, -2.65f, -0.88f, -4.01f);
        pathBuilderA.curveToRelative(-0.16f, -0.36f, 0.01f, -0.78f, 0.36f, -0.95f);
        pathBuilderA.curveTo(14.37f, 6.82f, 14.83f, 7.0f, 15.0f, 7.4f);
        pathBuilderA.curveToRelative(0.66f, 1.54f, 1.0f, 3.08f, 1.0f, 4.58f);
        pathBuilderA.curveTo(16.0f, 13.48f, 15.66f, 15.04f, 15.0f, 16.6f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _contactless = imageVectorBuild;
        return imageVectorBuild;
    }
}
