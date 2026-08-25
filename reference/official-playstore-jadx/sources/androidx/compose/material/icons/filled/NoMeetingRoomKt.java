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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noMeetingRoom", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoMeetingRoom", "Landroidx/compose/material/icons/Icons$Filled;", "getNoMeetingRoom", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoMeetingRoomKt {
    private static ImageVector _noMeetingRoom;

    public static final ImageVector getNoMeetingRoom(Icons.Filled filled) {
        ImageVector imageVector = _noMeetingRoom;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.NoMeetingRoom", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(11.0f, 11.0f, -1.0f, 2.0f, 2.0f);
        pathBuilderK.verticalLineToRelative(-1.0f);
        pathBuilderK.lineToRelative(9.73f, 9.73f);
        pathBuilderK.lineTo(20.46f, 23.0f);
        pathBuilderK.lineTo(14.0f, 16.54f);
        pathBuilderK.lineTo(14.0f, 21.0f);
        a.h(pathBuilderK, 3.0f, 21.0f, -2.0f, 2.0f);
        pathBuilderK.lineTo(5.0f, 7.54f);
        pathBuilderK.lineToRelative(-4.0f, -4.0f);
        d.C(pathBuilderK, 1.27f, -1.27f, 11.0f, 11.0f);
        pathBuilderK.moveTo(14.0f, 11.49f);
        pathBuilderK.lineTo(5.51f, 3.0f);
        a.h(pathBuilderK, 14.0f, 3.0f, 1.0f, 5.0f);
        pathBuilderK.verticalLineToRelative(12.49f);
        pathBuilderK.lineToRelative(-2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.x(pathBuilderK, 17.0f, 6.0f, -3.0f, 5.49f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noMeetingRoom = imageVectorBuild;
        return imageVectorBuild;
    }
}
