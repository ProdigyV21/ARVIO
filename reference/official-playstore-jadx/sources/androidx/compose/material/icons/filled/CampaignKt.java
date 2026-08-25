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
import v.b;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_campaign", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Campaign", "Landroidx/compose/material/icons/Icons$Filled;", "getCampaign", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CampaignKt {
    private static ImageVector _campaign;

    public static final ImageVector getCampaign(Icons.Filled filled) {
        ImageVector imageVector = _campaign;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Campaign", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(18.0f, 11.0f, 2.0f, 4.0f, -2.0f);
        f.x(pathBuilderU, -4.0f, 16.0f, 17.61f);
        pathBuilderU.curveToRelative(0.96f, 0.71f, 2.21f, 1.65f, 3.2f, 2.39f);
        pathBuilderU.curveToRelative(0.4f, -0.53f, 0.8f, -1.07f, 1.2f, -1.6f);
        pathBuilderU.curveToRelative(-0.99f, -0.74f, -2.24f, -1.68f, -3.2f, -2.4f);
        pathBuilderU.curveToRelative(-0.4f, 0.54f, -0.8f, 1.08f, -1.2f, 1.61f);
        pathBuilderU.close();
        pathBuilderU.moveTo(20.4f, 5.6f);
        pathBuilderU.curveToRelative(-0.4f, -0.53f, -0.8f, -1.07f, -1.2f, -1.6f);
        pathBuilderU.curveToRelative(-0.99f, 0.74f, -2.24f, 1.68f, -3.2f, 2.4f);
        pathBuilderU.curveToRelative(0.4f, 0.53f, 0.8f, 1.07f, 1.2f, 1.6f);
        pathBuilderU.curveToRelative(0.96f, -0.72f, 2.21f, -1.65f, 3.2f, -2.4f);
        pathBuilderU.close();
        pathBuilderU.moveTo(4.0f, 9.0f);
        pathBuilderU.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderU.verticalLineToRelative(2.0f);
        pathBuilderU.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        v.a.o(pathBuilderU, 1.0f, 4.0f, 2.0f, -4.0f);
        pathBuilderU.horizontalLineToRelative(1.0f);
        pathBuilderU.lineToRelative(5.0f, 3.0f);
        pathBuilderU.lineTo(13.0f, 6.0f);
        b.y(pathBuilderU, 8.0f, 9.0f, 4.0f, 9.0f);
        pathBuilderU.moveTo(15.5f, 12.0f);
        pathBuilderU.curveToRelative(0.0f, -1.33f, -0.58f, -2.53f, -1.5f, -3.35f);
        pathBuilderU.verticalLineToRelative(6.69f);
        pathBuilderU.curveToRelative(0.92f, -0.81f, 1.5f, -2.01f, 1.5f, -3.34f);
        pathBuilderU.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderU.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _campaign = imageVectorBuild;
        return imageVectorBuild;
    }
}
