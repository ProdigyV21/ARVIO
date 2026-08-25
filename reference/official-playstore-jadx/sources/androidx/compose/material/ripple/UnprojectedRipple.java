package androidx.compose.material.ripple;

import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.lang.reflect.Method;
import kotlin.Metadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\b\u0002\u0018\u0000  2\u00020\u0001:\u0002 !B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\"\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ \u0010\u0010\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001aR\u001e\u0010\u001b\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010\u001a\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\""}, d2 = {"Landroidx/compose/material/ripple/UnprojectedRipple;", "Landroid/graphics/drawable/RippleDrawable;", "", "bounded", "<init>", "(Z)V", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "", "alpha", "calculateRippleColor-5vOe2sY", "(JF)J", "calculateRippleColor", "Lx6/t0;", "setColor-DxMtmZc", "(JF)V", "setColor", "isProjected", "()Z", "Landroid/graphics/Rect;", "getDirtyBounds", "()Landroid/graphics/Rect;", "", "radius", "trySetRadius", "(I)V", "Z", "rippleColor", "Landroidx/compose/ui/graphics/Color;", "rippleRadius", "Ljava/lang/Integer;", "projected", "Companion", "MRadiusHelper", "material-ripple_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UnprojectedRipple extends RippleDrawable {
    private static boolean setMaxRadiusFetched;
    private static Method setMaxRadiusMethod;
    private final boolean bounded;
    private boolean projected;
    private Color rippleColor;
    private Integer rippleRadius;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/material/ripple/UnprojectedRipple$MRadiusHelper;", "", "<init>", "()V", "Landroid/graphics/drawable/RippleDrawable;", "ripple", "", "radius", "Lx6/t0;", "setRadius", "(Landroid/graphics/drawable/RippleDrawable;I)V", "material-ripple_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class MRadiusHelper {
        public static final MRadiusHelper INSTANCE = new MRadiusHelper();

        private MRadiusHelper() {
        }

        public final void setRadius(RippleDrawable ripple, int radius) {
            ripple.setRadius(radius);
        }
    }

    public UnprojectedRipple(boolean z) {
        super(ColorStateList.valueOf(-16777216), null, z ? new ColorDrawable(-1) : null);
        this.bounded = z;
    }

    /* JADX INFO: renamed from: calculateRippleColor-5vOe2sY, reason: not valid java name */
    private final long m1212calculateRippleColor5vOe2sY(long color, float alpha) {
        if (Build.VERSION.SDK_INT < 28) {
            alpha *= 2;
        }
        return Color.m3471copywmQWz5c$default(color, alpha > 1.0f ? 1.0f : alpha, 0.0f, 0.0f, 0.0f, 14, null);
    }

    @Override // android.graphics.drawable.RippleDrawable, android.graphics.drawable.Drawable
    public Rect getDirtyBounds() {
        if (!this.bounded) {
            this.projected = true;
        }
        Rect dirtyBounds = super.getDirtyBounds();
        this.projected = false;
        return dirtyBounds;
    }

    @Override // android.graphics.drawable.RippleDrawable, android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public boolean isProjected() {
        return this.projected;
    }

    /* JADX INFO: renamed from: setColor-DxMtmZc, reason: not valid java name */
    public final void m1213setColorDxMtmZc(long color, float alpha) {
        long jM1212calculateRippleColor5vOe2sY = m1212calculateRippleColor5vOe2sY(color, alpha);
        Color color2 = this.rippleColor;
        if (color2 == null ? false : Color.m3473equalsimpl0(color2.m3482unboximpl(), jM1212calculateRippleColor5vOe2sY)) {
            return;
        }
        this.rippleColor = Color.m3462boximpl(jM1212calculateRippleColor5vOe2sY);
        setColor(ColorStateList.valueOf(ColorKt.m3526toArgb8_81llA(jM1212calculateRippleColor5vOe2sY)));
    }

    public final void trySetRadius(int radius) {
        Integer num = this.rippleRadius;
        if (num != null && num.intValue() == radius) {
            return;
        }
        this.rippleRadius = Integer.valueOf(radius);
        MRadiusHelper.INSTANCE.setRadius(this, radius);
    }
}
