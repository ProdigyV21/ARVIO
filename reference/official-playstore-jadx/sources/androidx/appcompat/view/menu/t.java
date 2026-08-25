package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class t implements k0.b {
    public androidx.core.view.e A;
    public MenuItem.OnActionExpandListener B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1367a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1368b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1369c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1370d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f1371e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f1372f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Intent f1373g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public char f1374h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public char f1376j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Drawable f1377l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final q f1379n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public i0 f1380o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public MenuItem.OnMenuItemClickListener f1381p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public CharSequence f1382q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public CharSequence f1383r;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1389y;
    public View z;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1375i = 4096;
    public int k = 4096;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1378m = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ColorStateList f1384s = null;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public PorterDuff.Mode f1385t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1386u = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f1387v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f1388w = false;
    public int x = 16;
    public boolean C = false;

    public t(q qVar, int i10, int i11, int i12, int i13, CharSequence charSequence, int i14) {
        this.f1379n = qVar;
        this.f1367a = i11;
        this.f1368b = i10;
        this.f1369c = i12;
        this.f1370d = i13;
        this.f1371e = charSequence;
        this.f1389y = i14;
    }

    public static void c(StringBuilder sb2, int i10, int i11, String str) {
        if ((i10 & i11) == i11) {
            sb2.append(str);
        }
    }

    @Override // k0.b
    public final androidx.core.view.e a() {
        return this.A;
    }

    @Override // k0.b
    public final k0.b b(androidx.core.view.e eVar) {
        this.z = null;
        this.A = eVar;
        this.f1379n.p(true);
        androidx.core.view.e eVar2 = this.A;
        if (eVar2 != null) {
            eVar2.d(new a8.e(this, 2));
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final boolean collapseActionView() {
        if ((this.f1389y & 8) == 0) {
            return false;
        }
        if (this.z == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.B;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.f1379n.d(this);
        }
        return false;
    }

    public final Drawable d(Drawable drawable) {
        if (drawable != null && this.f1388w && (this.f1386u || this.f1387v)) {
            drawable = drawable.mutate();
            if (this.f1386u) {
                j0.a.h(drawable, this.f1384s);
            }
            if (this.f1387v) {
                j0.a.i(drawable, this.f1385t);
            }
            this.f1388w = false;
        }
        return drawable;
    }

    public final boolean e() {
        androidx.core.view.e eVar;
        if ((this.f1389y & 8) == 0) {
            return false;
        }
        if (this.z == null && (eVar = this.A) != null) {
            this.z = eVar.b(this);
        }
        return this.z != null;
    }

    @Override // android.view.MenuItem
    public final boolean expandActionView() {
        if (!e()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.B;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.f1379n.f(this);
        }
        return false;
    }

    public final void f(boolean z) {
        if (z) {
            this.x |= 32;
        } else {
            this.x &= -33;
        }
    }

    @Override // android.view.MenuItem
    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // android.view.MenuItem
    public final View getActionView() {
        View view = this.z;
        if (view != null) {
            return view;
        }
        androidx.core.view.e eVar = this.A;
        if (eVar == null) {
            return null;
        }
        View viewB = eVar.b(this);
        this.z = viewB;
        return viewB;
    }

    @Override // k0.b, android.view.MenuItem
    public final int getAlphabeticModifiers() {
        return this.k;
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return this.f1376j;
    }

    @Override // k0.b, android.view.MenuItem
    public final CharSequence getContentDescription() {
        return this.f1382q;
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return this.f1368b;
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        Drawable drawable = this.f1377l;
        if (drawable != null) {
            return d(drawable);
        }
        int i10 = this.f1378m;
        if (i10 == 0) {
            return null;
        }
        Drawable drawableO = m2.f0.o(this.f1379n.f1342a, i10);
        this.f1378m = 0;
        this.f1377l = drawableO;
        return d(drawableO);
    }

    @Override // k0.b, android.view.MenuItem
    public final ColorStateList getIconTintList() {
        return this.f1384s;
    }

    @Override // k0.b, android.view.MenuItem
    public final PorterDuff.Mode getIconTintMode() {
        return this.f1385t;
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return this.f1373g;
    }

    @Override // android.view.MenuItem
    public final int getItemId() {
        return this.f1367a;
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // k0.b, android.view.MenuItem
    public final int getNumericModifiers() {
        return this.f1375i;
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return this.f1374h;
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return this.f1369c;
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return this.f1380o;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitle() {
        return this.f1371e;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f1372f;
        return charSequence != null ? charSequence : this.f1371e;
    }

    @Override // k0.b, android.view.MenuItem
    public final CharSequence getTooltipText() {
        return this.f1383r;
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        return this.f1380o != null;
    }

    @Override // android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return this.C;
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        return (this.x & 1) == 1;
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        return (this.x & 2) == 2;
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        return (this.x & 16) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        androidx.core.view.e eVar = this.A;
        return (eVar == null || !eVar.c()) ? (this.x & 8) == 0 : (this.x & 8) == 0 && this.A.a();
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(View view) {
        int i10;
        this.z = view;
        this.A = null;
        if (view != null && view.getId() == -1 && (i10 = this.f1367a) > 0) {
            view.setId(i10);
        }
        q qVar = this.f1379n;
        qVar.k = true;
        qVar.p(true);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c10) {
        if (this.f1376j == c10) {
            return this;
        }
        this.f1376j = Character.toLowerCase(c10);
        this.f1379n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z) {
        int i10 = this.x;
        int i11 = (z ? 1 : 0) | (i10 & (-2));
        this.x = i11;
        if (i10 != i11) {
            this.f1379n.p(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z) {
        int i10 = this.x;
        int i11 = i10 & 4;
        q qVar = this.f1379n;
        if (i11 == 0) {
            int i12 = (i10 & (-3)) | (z ? 2 : 0);
            this.x = i12;
            if (i10 != i12) {
                qVar.p(false);
            }
            return this;
        }
        ArrayList arrayList = qVar.f1347f;
        int size = arrayList.size();
        qVar.w();
        for (int i13 = 0; i13 < size; i13++) {
            t tVar = (t) arrayList.get(i13);
            if (tVar.f1368b == this.f1368b && (tVar.x & 4) != 0 && tVar.isCheckable()) {
                boolean z5 = tVar == this;
                int i14 = tVar.x;
                int i15 = (z5 ? 2 : 0) | (i14 & (-3));
                tVar.x = i15;
                if (i14 != i15) {
                    tVar.f1379n.p(false);
                }
            }
        }
        qVar.v();
        return this;
    }

    @Override // android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setContentDescription(CharSequence charSequence) {
        setContentDescription(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z) {
        if (z) {
            this.x |= 16;
        } else {
            this.x &= -17;
        }
        this.f1379n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.f1378m = 0;
        this.f1377l = drawable;
        this.f1388w = true;
        this.f1379n.p(false);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f1384s = colorStateList;
        this.f1386u = true;
        this.f1388w = true;
        this.f1379n.p(false);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f1385t = mode;
        this.f1387v = true;
        this.f1388w = true;
        this.f1379n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        this.f1373g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c10) {
        if (this.f1374h == c10) {
            return this;
        }
        this.f1374h = c10;
        this.f1379n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.B = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f1381p = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c10, char c11) {
        this.f1374h = c10;
        this.f1376j = Character.toLowerCase(c11);
        this.f1379n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final void setShowAsAction(int i10) {
        int i11 = i10 & 3;
        if (i11 != 0 && i11 != 1 && i11 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.f1389y = i10;
        q qVar = this.f1379n;
        qVar.k = true;
        qVar.p(true);
    }

    @Override // android.view.MenuItem
    public final MenuItem setShowAsActionFlags(int i10) {
        setShowAsAction(i10);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.f1371e = charSequence;
        this.f1379n.p(false);
        i0 i0Var = this.f1380o;
        if (i0Var != null) {
            i0Var.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f1372f = charSequence;
        this.f1379n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setTooltipText(CharSequence charSequence) {
        setTooltipText(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z) {
        int i10 = this.x;
        int i11 = (z ? 0 : 8) | (i10 & (-9));
        this.x = i11;
        if (i10 != i11) {
            q qVar = this.f1379n;
            qVar.f1349h = true;
            qVar.p(true);
        }
        return this;
    }

    public final String toString() {
        CharSequence charSequence = this.f1371e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    @Override // k0.b, android.view.MenuItem
    public final k0.b setContentDescription(CharSequence charSequence) {
        this.f1382q = charSequence;
        this.f1379n.p(false);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final k0.b setTooltipText(CharSequence charSequence) {
        this.f1383r = charSequence;
        this.f1379n.p(false);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c10, int i10) {
        if (this.f1376j == c10 && this.k == i10) {
            return this;
        }
        this.f1376j = Character.toLowerCase(c10);
        this.k = KeyEvent.normalizeMetaState(i10);
        this.f1379n.p(false);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setNumericShortcut(char c10, int i10) {
        if (this.f1374h == c10 && this.f1375i == i10) {
            return this;
        }
        this.f1374h = c10;
        this.f1375i = KeyEvent.normalizeMetaState(i10);
        this.f1379n.p(false);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setShortcut(char c10, char c11, int i10, int i11) {
        this.f1374h = c10;
        this.f1375i = KeyEvent.normalizeMetaState(i10);
        this.f1376j = Character.toLowerCase(c11);
        this.k = KeyEvent.normalizeMetaState(i11);
        this.f1379n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i10) {
        this.f1377l = null;
        this.f1378m = i10;
        this.f1388w = true;
        this.f1379n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i10) {
        setTitle(this.f1379n.f1342a.getString(i10));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(int i10) {
        int i11;
        q qVar = this.f1379n;
        Context context = qVar.f1342a;
        View viewInflate = LayoutInflater.from(context).inflate(i10, (ViewGroup) new LinearLayout(context), false);
        this.z = viewInflate;
        this.A = null;
        if (viewInflate != null && viewInflate.getId() == -1 && (i11 = this.f1367a) > 0) {
            viewInflate.setId(i11);
        }
        qVar.k = true;
        qVar.p(true);
        return this;
    }
}
