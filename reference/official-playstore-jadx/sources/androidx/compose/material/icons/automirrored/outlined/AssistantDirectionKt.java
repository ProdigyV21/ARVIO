package androidx.compose.material.icons.automirrored.outlined;

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
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_assistantDirection", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AssistantDirection", "Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;", "getAssistantDirection", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AssistantDirectionKt {
    private static ImageVector _assistantDirection;

    public static final ImageVector getAssistantDirection(Icons.AutoMirrored.Outlined outlined) {
        ImageVector imageVector = _assistantDirection;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Outlined.AssistantDirection", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 1.0f);
        pathBuilderA.curveTo(5.9f, 1.0f, 1.0f, 5.9f, 1.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.9f, 11.0f, 11.0f, 11.0f);
        pathBuilderA.reflectiveCurveToRelative(11.0f, -4.9f, 11.0f, -11.0f);
        pathBuilderA.reflectiveCurveTo(18.1f, 1.0f, 12.0f, 1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 21.0f);
        pathBuilderA.curveToRelative(-4.99f, 0.0f, -9.0f, -4.01f, -9.0f, -9.0f);
        pathBuilderA.reflectiveCurveToRelative(4.01f, -9.0f, 9.0f, -9.0f);
        pathBuilderA.reflectiveCurveToRelative(9.0f, 4.01f, 9.0f, 9.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 16.99f, 21.0f, 12.0f, 21.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.73f, 11.42f, 12.54f, 4.2f);
        pathBuilderJ.curveToRelative(-0.36f, -0.27f, -0.8f, -0.27f, -1.15f, 0.0f);
        pathBuilderJ.lineTo(4.2f, 11.42f);
        pathBuilderJ.curveToRelative(-0.27f, 0.36f, -0.27f, 0.8f, 0.0f, 1.16f);
        pathBuilderJ.lineToRelative(7.19f, 7.22f);
        pathBuilderJ.curveToRelative(0.36f, 0.27f, 0.8f, 0.27f, 1.15f, 0.0f);
        pathBuilderJ.lineToRelative(7.19f, -7.22f);
        pathBuilderJ.curveTo(20.09f, 12.22f, 20.09f, 11.69f, 19.73f, 11.42f);
        a.z(pathBuilderJ, 13.5f, 14.5f, -1.41f, -1.41f);
        b.m(pathBuilderJ, 13.17f, 12.0f, 10.0f, 3.0f);
        pathBuilderJ.horizontalLineTo(8.0f);
        pathBuilderJ.verticalLineToRelative(-4.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.6f, 0.4f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(4.17f);
        pathBuilderJ.lineToRelative(-1.09f, -1.09f);
        pathBuilderJ.lineTo(13.5f, 7.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderJ, 17.0f, 11.0f, 13.5f, 14.5f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _assistantDirection = imageVectorBuild;
        return imageVectorBuild;
    }
}
