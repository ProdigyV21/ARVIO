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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_speakerNotesOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SpeakerNotesOff", "Landroidx/compose/material/icons/Icons$Filled;", "getSpeakerNotesOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SpeakerNotesOffKt {
    private static ImageVector _speakerNotesOff;

    public static final ImageVector getSpeakerNotesOff(Icons.Filled filled) {
        ImageVector imageVector = _speakerNotesOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.SpeakerNotesOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(10.54f, 11.0f, -0.54f, -0.54f);
        pathBuilderR.lineTo(7.54f, 8.0f);
        pathBuilderR.lineTo(6.0f, 6.46f);
        pathBuilderR.lineTo(2.38f, 2.84f);
        pathBuilderR.lineTo(1.27f, 1.73f);
        pathBuilderR.lineTo(0.0f, 3.0f);
        pathBuilderR.lineToRelative(2.01f, 2.01f);
        pathBuilderR.lineTo(2.0f, 22.0f);
        pathBuilderR.lineToRelative(4.0f, -4.0f);
        pathBuilderR.horizontalLineToRelative(9.0f);
        pathBuilderR.lineToRelative(5.73f, 5.73f);
        pathBuilderR.lineTo(22.0f, 22.46f);
        d.l(pathBuilderR, 17.54f, 18.0f, -7.0f, -7.0f);
        pathBuilderR.moveTo(8.0f, 14.0f);
        a.h(pathBuilderR, 6.0f, 14.0f, -2.0f, 2.0f);
        b.f(pathBuilderR, 2.0f, 6.0f, 11.0f);
        pathBuilderR.lineTo(6.0f, 9.0f);
        d.C(pathBuilderR, 2.0f, 2.0f, 6.0f, 11.0f);
        pathBuilderR.moveTo(20.0f, 2.0f);
        pathBuilderR.lineTo(4.08f, 2.0f);
        pathBuilderR.lineTo(10.0f, 7.92f);
        b.A(pathBuilderR, 10.0f, 6.0f, 8.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(-7.92f);
        pathBuilderR.lineToRelative(1.0f, 1.0f);
        a.h(pathBuilderR, 18.0f, 9.0f, 2.0f, -4.92f);
        pathBuilderR.lineToRelative(6.99f, 6.99f);
        pathBuilderR.curveTo(21.14f, 17.95f, 22.0f, 17.08f, 22.0f, 16.0f);
        pathBuilderR.lineTo(22.0f, 4.0f);
        pathBuilderR.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _speakerNotesOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
