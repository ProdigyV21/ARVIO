package androidx.compose.material.icons.twotone;

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
import v.c;
import v.d;
import x6.e;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_forwardToInbox", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ForwardToInbox", "Landroidx/compose/material/icons/Icons$TwoTone;", "getForwardToInbox$annotations", "(Landroidx/compose/material/icons/Icons$TwoTone;)V", "getForwardToInbox", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ForwardToInboxKt {
    private static ImageVector _forwardToInbox;

    public static final ImageVector getForwardToInbox(Icons.TwoTone twoTone) {
        ImageVector imageVector = _forwardToInbox;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ForwardToInbox", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(13.0f, 18.0f, 4.0f, 8.0f);
        pathBuilderA.lineToRelative(8.0f, 5.0f);
        pathBuilderA.lineToRelative(8.0f, -5.0f);
        pathBuilderA.verticalLineToRelative(5.0f);
        pathBuilderA.lineToRelative(-2.0f, 0.0f);
        pathBuilderA.curveTo(15.24f, 13.0f, 13.0f, 15.24f, 13.0f, 18.0f);
        c.z(pathBuilderA, 13.0f, 18.0f, 12.0f, 11.0f);
        pathBuilderA.lineTo(4.0f, 6.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.h(pathBuilderA, 16.0f, 12.0f, 11.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = v.a.q(20.0f, 4.0f, 4.0f);
        pathBuilderQ.curveTo(2.9f, 4.0f, 2.0f, 4.9f, 2.0f, 6.0f);
        pathBuilderQ.verticalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        d.y(pathBuilderQ, 9.0f, -2.0f, 4.0f, 8.0f);
        pathBuilderQ.lineToRelative(8.0f, 5.0f);
        b.u(pathBuilderQ, 8.0f, -5.0f, 5.0f, 2.0f);
        pathBuilderQ.verticalLineTo(6.0f);
        pathBuilderQ.curveTo(22.0f, 4.9f, 21.1f, 4.0f, 20.0f, 4.0f);
        v.a.p(pathBuilderQ, 12.0f, 11.0f, 4.0f, 6.0f);
        d.q(pathBuilderQ, 16.0f, 12.0f, 11.0f);
        pathBuilderQ.moveTo(19.0f, 15.0f);
        pathBuilderQ.lineToRelative(4.0f, 4.0f);
        b.u(pathBuilderQ, -4.0f, 4.0f, -3.0f, -4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.p(pathBuilderQ, -2.0f, 4.0f, 15.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _forwardToInbox = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getForwardToInbox$annotations(Icons.TwoTone twoTone) {
    }
}
