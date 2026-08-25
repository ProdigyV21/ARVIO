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
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_assistantDirection", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AssistantDirection", "Landroidx/compose/material/icons/Icons$Filled;", "getAssistantDirection$annotations", "(Landroidx/compose/material/icons/Icons$Filled;)V", "getAssistantDirection", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AssistantDirectionKt {
    private static ImageVector _assistantDirection;

    public static final ImageVector getAssistantDirection(Icons.Filled filled) {
        ImageVector imageVector = _assistantDirection;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.AssistantDirection", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(14.0f, 10.0f, 9.0f, 10.0f);
        pathBuilderJ.curveToRelative(-0.6f, 0.0f, -1.0f, 0.4f, -1.0f, 1.0f);
        b.z(pathBuilderJ, 4.0f, 2.0f, -3.0f, 4.0f);
        pathBuilderJ.verticalLineToRelative(2.5f);
        pathBuilderJ.lineToRelative(3.5f, -3.5f);
        b.y(pathBuilderJ, 14.0f, 7.5f, 14.0f, 10.0f);
        pathBuilderJ.moveTo(12.0f, 1.0f);
        pathBuilderJ.curveTo(5.9f, 1.0f, 1.0f, 5.9f, 1.0f, 12.0f);
        pathBuilderJ.reflectiveCurveToRelative(4.9f, 11.0f, 11.0f, 11.0f);
        pathBuilderJ.reflectiveCurveToRelative(11.0f, -4.9f, 11.0f, -11.0f);
        pathBuilderJ.reflectiveCurveTo(18.1f, 1.0f, 12.0f, 1.0f);
        a.z(pathBuilderJ, 19.73f, 12.58f, -7.19f, 7.22f);
        pathBuilderJ.curveToRelative(-0.35f, 0.27f, -0.79f, 0.27f, -1.15f, 0.0f);
        pathBuilderJ.lineTo(4.2f, 12.58f);
        pathBuilderJ.curveToRelative(-0.27f, -0.36f, -0.27f, -0.8f, 0.0f, -1.16f);
        pathBuilderJ.lineToRelative(7.19f, -7.22f);
        pathBuilderJ.curveToRelative(0.35f, -0.27f, 0.79f, -0.27f, 1.15f, 0.0f);
        pathBuilderJ.lineToRelative(7.19f, 7.22f);
        pathBuilderJ.curveToRelative(0.36f, 0.27f, 0.36f, 0.8f, 0.0f, 1.16f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _assistantDirection = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getAssistantDirection$annotations(Icons.Filled filled) {
    }
}
