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
import v.c;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_sendAndArchive", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SendAndArchive", "Landroidx/compose/material/icons/Icons$Rounded;", "getSendAndArchive$annotations", "(Landroidx/compose/material/icons/Icons$Rounded;)V", "getSendAndArchive", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SendAndArchiveKt {
    private static ImageVector _sendAndArchive;

    public static final ImageVector getSendAndArchive(Icons.Rounded rounded) {
        ImageVector imageVector = _sendAndArchive;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SendAndArchive", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.0f, 12.0f);
        pathBuilderA.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderA.reflectiveCurveTo(19.76f, 12.0f, 17.0f, 12.0f);
        a.z(pathBuilderA, 19.15f, 17.85f, -1.79f, 1.79f);
        pathBuilderA.curveToRelative(-0.2f, 0.2f, -0.51f, 0.2f, -0.71f, 0.0f);
        pathBuilderA.lineToRelative(-1.79f, -1.79f);
        pathBuilderA.curveTo(14.54f, 17.54f, 14.76f, 17.0f, 15.21f, 17.0f);
        pathBuilderA.horizontalLineToRelative(1.29f);
        pathBuilderA.verticalLineToRelative(-2.5f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderA.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderA.verticalLineTo(17.0f);
        pathBuilderA.horizontalLineToRelative(1.29f);
        pathBuilderA.curveTo(19.24f, 17.0f, 19.46f, 17.54f, 19.15f, 17.85f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(17.0f, 10.0f);
        pathBuilderA2.curveToRelative(0.1f, 0.0f, 0.19f, 0.01f, 0.28f, 0.01f);
        pathBuilderA2.lineTo(3.0f, 4.0f);
        pathBuilderA2.verticalLineToRelative(6.0f);
        pathBuilderA2.lineToRelative(8.0f, 2.0f);
        pathBuilderA2.lineToRelative(-8.0f, 2.0f);
        pathBuilderA2.verticalLineToRelative(6.0f);
        pathBuilderA2.lineToRelative(7.0f, -2.95f);
        pathBuilderA2.curveToRelative(0.0f, -0.02f, 0.0f, -0.03f, 0.0f, -0.05f);
        pathBuilderA2.curveTo(10.0f, 13.13f, 13.13f, 10.0f, 17.0f, 10.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sendAndArchive = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getSendAndArchive$annotations(Icons.Rounded rounded) {
    }
}
