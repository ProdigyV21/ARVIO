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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_contactless", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Contactless", "Landroidx/compose/material/icons/Icons$Filled;", "getContactless", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContactlessKt {
    private static ImageVector _contactless;

    public static final ImageVector getContactless(Icons.Filled filled) {
        ImageVector imageVector = _contactless;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Contactless", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.curveTo(22.0f, 6.48f, 17.52f, 2.0f, 12.0f, 2.0f);
        a.p(pathBuilderA, 8.46f, 14.45f, 7.1f, 13.83f);
        pathBuilderA.curveToRelative(0.28f, -0.61f, 0.41f, -1.24f, 0.4f, -1.86f);
        pathBuilderA.curveToRelative(-0.01f, -0.63f, -0.14f, -1.24f, -0.4f, -1.8f);
        pathBuilderA.lineToRelative(1.36f, -0.63f);
        pathBuilderA.curveToRelative(0.35f, 0.75f, 0.53f, 1.56f, 0.54f, 2.4f);
        pathBuilderA.curveTo(9.01f, 12.8f, 8.83f, 13.64f, 8.46f, 14.45f);
        a.z(pathBuilderA, 11.53f, 16.01f, -1.3f, -0.74f);
        pathBuilderA.curveToRelative(0.52f, -0.92f, 0.78f, -1.98f, 0.78f, -3.15f);
        pathBuilderA.curveToRelative(0.0f, -1.19f, -0.27f, -2.33f, -0.8f, -3.4f);
        pathBuilderA.lineToRelative(1.34f, -0.67f);
        pathBuilderA.curveToRelative(0.64f, 1.28f, 0.96f, 2.65f, 0.96f, 4.07f);
        pathBuilderA.curveTo(12.51f, 13.55f, 12.18f, 14.86f, 11.53f, 16.01f);
        a.z(pathBuilderA, 14.67f, 17.33f, -1.35f, -0.66f);
        pathBuilderA.curveToRelative(0.78f, -1.6f, 1.18f, -3.18f, 1.18f, -4.69f);
        pathBuilderA.curveToRelative(0.0f, -1.51f, -0.4f, -3.07f, -1.18f, -4.64f);
        pathBuilderA.lineToRelative(1.34f, -0.67f);
        pathBuilderA.curveTo(15.56f, 8.45f, 16.0f, 10.23f, 16.0f, 11.98f);
        pathBuilderA.curveTo(16.0f, 13.72f, 15.56f, 15.52f, 14.67f, 17.33f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _contactless = imageVectorBuild;
        return imageVectorBuild;
    }
}
