package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import com.arvio.tv.R;
import java.io.Serializable;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class z implements i3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Serializable f1842a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Serializable f1843b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f1844c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Serializable f1845d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Serializable f1846e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f1847f;

    public static boolean b(int[] iArr, int i10) {
        for (int i11 : iArr) {
            if (i11 == i10) {
                return true;
            }
        }
        return false;
    }

    public static ColorStateList d(Context context, int i10) {
        int iC = d4.c(context, R.attr.colorControlHighlight);
        return new ColorStateList(new int[][]{d4.f1549b, d4.f1551d, d4.f1550c, d4.f1553f}, new int[]{d4.b(context, R.attr.colorButtonNormal), androidx.core.graphics.a.c(iC, i10), androidx.core.graphics.a.c(iC, i10), i10});
    }

    public static LayerDrawable e(j3 j3Var, Context context, int i10) {
        BitmapDrawable bitmapDrawable;
        BitmapDrawable bitmapDrawable2;
        BitmapDrawable bitmapDrawable3;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(i10);
        Drawable drawableE = j3Var.e(context, R.drawable.abc_star_black_48dp);
        Drawable drawableE2 = j3Var.e(context, R.drawable.abc_star_half_black_48dp);
        if ((drawableE instanceof BitmapDrawable) && drawableE.getIntrinsicWidth() == dimensionPixelSize && drawableE.getIntrinsicHeight() == dimensionPixelSize) {
            bitmapDrawable = (BitmapDrawable) drawableE;
            bitmapDrawable2 = new BitmapDrawable(bitmapDrawable.getBitmap());
        } else {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawableE.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            drawableE.draw(canvas);
            bitmapDrawable = new BitmapDrawable(bitmapCreateBitmap);
            bitmapDrawable2 = new BitmapDrawable(bitmapCreateBitmap);
        }
        bitmapDrawable2.setTileModeX(Shader.TileMode.REPEAT);
        if ((drawableE2 instanceof BitmapDrawable) && drawableE2.getIntrinsicWidth() == dimensionPixelSize && drawableE2.getIntrinsicHeight() == dimensionPixelSize) {
            bitmapDrawable3 = (BitmapDrawable) drawableE2;
        } else {
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap2);
            drawableE2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            drawableE2.draw(canvas2);
            bitmapDrawable3 = new BitmapDrawable(bitmapCreateBitmap2);
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bitmapDrawable, bitmapDrawable3, bitmapDrawable2});
        layerDrawable.setId(0, android.R.id.background);
        layerDrawable.setId(1, android.R.id.secondaryProgress);
        layerDrawable.setId(2, android.R.id.progress);
        return layerDrawable;
    }

    public static void g(Drawable drawable, int i10, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterG;
        int[] iArr = z1.f1848a;
        Drawable drawableMutate = drawable.mutate();
        if (mode == null) {
            mode = a0.f1502b;
        }
        PorterDuff.Mode mode2 = a0.f1502b;
        synchronized (a0.class) {
            porterDuffColorFilterG = j3.g(i10, mode);
        }
        drawableMutate.setColorFilter(porterDuffColorFilterG);
    }

    public void a(String str, String str2) {
        HashMap map = (HashMap) this.f1847f;
        if (map == null) {
            throw new IllegalStateException("Property \"autoMetadata\" has not been set");
        }
        map.put(str, str2);
    }

    public com.google.android.datatransport.runtime.h c() {
        String strM = ((String) this.f1842a) == null ? " transportName" : "";
        if (((com.google.android.datatransport.runtime.n) this.f1844c) == null) {
            strM = strM.concat(" encodedPayload");
        }
        if (((Long) this.f1845d) == null) {
            strM = androidx.compose.material3.d.m(strM, " eventMillis");
        }
        if (((Long) this.f1846e) == null) {
            strM = androidx.compose.material3.d.m(strM, " uptimeMillis");
        }
        if (((HashMap) this.f1847f) == null) {
            strM = androidx.compose.material3.d.m(strM, " autoMetadata");
        }
        if (strM.isEmpty()) {
            return new com.google.android.datatransport.runtime.h((String) this.f1842a, (Integer) this.f1843b, (com.google.android.datatransport.runtime.n) this.f1844c, ((Long) this.f1845d).longValue(), ((Long) this.f1846e).longValue(), (HashMap) this.f1847f);
        }
        throw new IllegalStateException("Missing required properties:".concat(strM));
    }

    public ColorStateList f(Context context, int i10) {
        if (i10 == R.drawable.abc_edit_text_material) {
            return qb.d.C(context, R.color.abc_tint_edittext);
        }
        if (i10 == 2131230786) {
            return qb.d.C(context, R.color.abc_tint_switch_track);
        }
        if (i10 != R.drawable.abc_switch_thumb_material) {
            if (i10 == R.drawable.abc_btn_default_mtrl_shape) {
                return d(context, d4.c(context, R.attr.colorButtonNormal));
            }
            if (i10 == R.drawable.abc_btn_borderless_material) {
                return d(context, 0);
            }
            if (i10 == R.drawable.abc_btn_colored_material) {
                return d(context, d4.c(context, R.attr.colorAccent));
            }
            if (i10 == 2131230781 || i10 == R.drawable.abc_spinner_textfield_background_material) {
                return qb.d.C(context, R.color.abc_tint_spinner);
            }
            if (b((int[]) this.f1843b, i10)) {
                return d4.d(context, R.attr.colorControlNormal);
            }
            if (b((int[]) this.f1846e, i10)) {
                return qb.d.C(context, R.color.abc_tint_default);
            }
            if (b((int[]) this.f1847f, i10)) {
                return qb.d.C(context, R.color.abc_tint_btn_checkable);
            }
            if (i10 == R.drawable.abc_seekbar_thumb_material) {
                return qb.d.C(context, R.color.abc_tint_seek_thumb);
            }
            return null;
        }
        int[][] iArr = new int[3][];
        int[] iArr2 = new int[3];
        ColorStateList colorStateListD = d4.d(context, R.attr.colorSwitchThumbNormal);
        if (colorStateListD == null || !colorStateListD.isStateful()) {
            iArr[0] = d4.f1549b;
            iArr2[0] = d4.b(context, R.attr.colorSwitchThumbNormal);
            iArr[1] = d4.f1552e;
            iArr2[1] = d4.c(context, R.attr.colorControlActivated);
            iArr[2] = d4.f1553f;
            iArr2[2] = d4.c(context, R.attr.colorSwitchThumbNormal);
        } else {
            int[] iArr3 = d4.f1549b;
            iArr[0] = iArr3;
            iArr2[0] = colorStateListD.getColorForState(iArr3, 0);
            iArr[1] = d4.f1552e;
            iArr2[1] = d4.c(context, R.attr.colorControlActivated);
            iArr[2] = d4.f1553f;
            iArr2[2] = colorStateListD.getDefaultColor();
        }
        return new ColorStateList(iArr, iArr2);
    }
}
