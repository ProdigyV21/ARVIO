package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.Log;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public final class a0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final PorterDuff.Mode f1502b = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static a0 f1503c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public j3 f1504a;

    public static synchronized a0 a() {
        try {
            if (f1503c == null) {
                c();
            }
        } catch (Throwable th) {
            throw th;
        }
        return f1503c;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [int[], java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r3v5, types: [int[], java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r3v7, types: [int[], java.io.Serializable] */
    /* JADX WARN: Type inference failed for: r4v1, types: [int[], java.io.Serializable] */
    public static synchronized void c() {
        if (f1503c == null) {
            a0 a0Var = new a0();
            f1503c = a0Var;
            a0Var.f1504a = j3.c();
            j3 j3Var = f1503c.f1504a;
            z zVar = new z();
            zVar.f1842a = new int[]{2131230796, 2131230794, 2131230720};
            zVar.f1843b = new int[]{2131230744, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};
            zVar.f1844c = new int[]{2131230793, 2131230795, 2131230737, R.drawable.abc_text_cursor_material, 2131230790, 2131230791, 2131230792};
            zVar.f1845d = new int[]{2131230769, R.drawable.abc_cab_background_internal_bg, 2131230768};
            zVar.f1846e = new int[]{R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};
            zVar.f1847f = new int[]{R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material, R.drawable.abc_btn_check_material_anim, R.drawable.abc_btn_radio_material_anim};
            j3Var.l(zVar);
        }
    }

    public static void d(Drawable drawable, g4 g4Var, int[] iArr) {
        PorterDuff.Mode mode = j3.f1609h;
        int[] state = drawable.getState();
        int[] iArr2 = z1.f1848a;
        if (drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        if ((drawable instanceof LayerDrawable) && drawable.isStateful()) {
            drawable.setState(new int[0]);
            drawable.setState(state);
        }
        boolean z = g4Var.f1590b;
        if (z || g4Var.f1589a) {
            PorterDuffColorFilter porterDuffColorFilterG = null;
            ColorStateList colorStateList = z ? (ColorStateList) g4Var.f1591c : null;
            PorterDuff.Mode mode2 = g4Var.f1589a ? (PorterDuff.Mode) g4Var.f1592d : j3.f1609h;
            if (colorStateList != null && mode2 != null) {
                porterDuffColorFilterG = j3.g(colorStateList.getColorForState(iArr, 0), mode2);
            }
            drawable.setColorFilter(porterDuffColorFilterG);
        } else {
            drawable.clearColorFilter();
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    public final synchronized Drawable b(Context context, int i10) {
        return this.f1504a.e(context, i10);
    }
}
