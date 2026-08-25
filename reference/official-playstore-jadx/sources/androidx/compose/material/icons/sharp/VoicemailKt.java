package androidx.compose.material.icons.sharp;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_voicemail", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Voicemail", "Landroidx/compose/material/icons/Icons$Sharp;", "getVoicemail", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VoicemailKt {
    private static ImageVector _voicemail;

    public static final ImageVector getVoicemail(Icons.Sharp sharp) {
        ImageVector imageVector = _voicemail;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Voicemail", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.5f, 6.0f);
        pathBuilderA.curveTo(15.46f, 6.0f, 13.0f, 8.46f, 13.0f, 11.5f);
        pathBuilderA.curveToRelative(0.0f, 1.33f, 0.47f, 2.55f, 1.26f, 3.5f);
        pathBuilderA.lineTo(9.74f, 15.0f);
        pathBuilderA.curveToRelative(0.79f, -0.95f, 1.26f, -2.17f, 1.26f, -3.5f);
        pathBuilderA.curveTo(11.0f, 8.46f, 8.54f, 6.0f, 5.5f, 6.0f);
        pathBuilderA.reflectiveCurveTo(0.0f, 8.46f, 0.0f, 11.5f);
        pathBuilderA.reflectiveCurveTo(2.46f, 17.0f, 5.5f, 17.0f);
        pathBuilderA.horizontalLineToRelative(13.0f);
        pathBuilderA.curveToRelative(3.04f, 0.0f, 5.5f, -2.46f, 5.5f, -5.5f);
        pathBuilderA.reflectiveCurveTo(21.54f, 6.0f, 18.5f, 6.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.5f, 15.0f);
        pathBuilderA.curveTo(3.57f, 15.0f, 2.0f, 13.43f, 2.0f, 11.5f);
        pathBuilderA.reflectiveCurveTo(3.57f, 8.0f, 5.5f, 8.0f);
        pathBuilderA.reflectiveCurveTo(9.0f, 9.57f, 9.0f, 11.5f);
        pathBuilderA.reflectiveCurveTo(7.43f, 15.0f, 5.5f, 15.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.5f, 15.0f);
        pathBuilderA.curveToRelative(-1.93f, 0.0f, -3.5f, -1.57f, -3.5f, -3.5f);
        pathBuilderA.reflectiveCurveTo(16.57f, 8.0f, 18.5f, 8.0f);
        pathBuilderA.reflectiveCurveTo(22.0f, 9.57f, 22.0f, 11.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 20.43f, 15.0f, 18.5f, 15.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _voicemail = imageVectorBuild;
        return imageVectorBuild;
    }
}
