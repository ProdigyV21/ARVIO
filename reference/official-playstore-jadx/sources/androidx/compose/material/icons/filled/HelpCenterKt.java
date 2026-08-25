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
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_helpCenter", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HelpCenter", "Landroidx/compose/material/icons/Icons$Filled;", "getHelpCenter$annotations", "(Landroidx/compose/material/icons/Icons$Filled;)V", "getHelpCenter", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HelpCenterKt {
    private static ImageVector _helpCenter;

    public static final ImageVector getHelpCenter(Icons.Filled filled) {
        ImageVector imageVector = _helpCenter;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.HelpCenter", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(19.0f, 3.0f, 5.0f);
        pathBuilderQ.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderQ.verticalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(5.0f);
        pathBuilderQ.curveTo(21.0f, 3.9f, 20.1f, 3.0f, 19.0f, 3.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(12.01f, 18.0f);
        pathBuilderQ.curveToRelative(-0.7f, 0.0f, -1.26f, -0.56f, -1.26f, -1.26f);
        pathBuilderQ.curveToRelative(0.0f, -0.71f, 0.56f, -1.25f, 1.26f, -1.25f);
        pathBuilderQ.curveToRelative(0.71f, 0.0f, 1.25f, 0.54f, 1.25f, 1.25f);
        pathBuilderQ.curveTo(13.25f, 17.43f, 12.72f, 18.0f, 12.01f, 18.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(15.02f, 10.6f);
        pathBuilderQ.curveToRelative(-0.76f, 1.11f, -1.48f, 1.46f, -1.87f, 2.17f);
        pathBuilderQ.curveToRelative(-0.16f, 0.29f, -0.22f, 0.48f, -0.22f, 1.41f);
        pathBuilderQ.horizontalLineToRelative(-1.82f);
        pathBuilderQ.curveToRelative(0.0f, -0.49f, -0.08f, -1.29f, 0.31f, -1.98f);
        pathBuilderQ.curveToRelative(0.49f, -0.87f, 1.42f, -1.39f, 1.96f, -2.16f);
        pathBuilderQ.curveToRelative(0.57f, -0.81f, 0.25f, -2.33f, -1.37f, -2.33f);
        pathBuilderQ.curveToRelative(-1.06f, 0.0f, -1.58f, 0.8f, -1.8f, 1.48f);
        pathBuilderQ.lineTo(8.56f, 8.49f);
        pathBuilderQ.curveTo(9.01f, 7.15f, 10.22f, 6.0f, 11.99f, 6.0f);
        pathBuilderQ.curveToRelative(1.48f, 0.0f, 2.49f, 0.67f, 3.01f, 1.52f);
        pathBuilderQ.curveTo(15.44f, 8.24f, 15.7f, 9.59f, 15.02f, 10.6f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _helpCenter = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getHelpCenter$annotations(Icons.Filled filled) {
    }
}
