package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class a implements k0.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CharSequence f1247a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CharSequence f1248b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Intent f1249c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public char f1250d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1251e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public char f1252f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1253g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Drawable f1254h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Context f1255i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f1256j;
    public CharSequence k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ColorStateList f1257l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public PorterDuff.Mode f1258m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1259n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1260o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1261p;

    @Override // k0.b
    public final androidx.core.view.e a() {
        return null;
    }

    @Override // k0.b
    public final k0.b b(androidx.core.view.e eVar) {
        throw new UnsupportedOperationException();
    }

    public final void c() {
        Drawable drawable = this.f1254h;
        if (drawable != null) {
            if (this.f1259n || this.f1260o) {
                this.f1254h = drawable;
                Drawable drawableMutate = drawable.mutate();
                this.f1254h = drawableMutate;
                if (this.f1259n) {
                    j0.a.h(drawableMutate, this.f1257l);
                }
                if (this.f1260o) {
                    j0.a.i(this.f1254h, this.f1258m);
                }
            }
        }
    }

    @Override // android.view.MenuItem
    public final boolean collapseActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean expandActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final View getActionView() {
        return null;
    }

    @Override // k0.b, android.view.MenuItem
    public final int getAlphabeticModifiers() {
        return this.f1253g;
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return this.f1252f;
    }

    @Override // k0.b, android.view.MenuItem
    public final CharSequence getContentDescription() {
        return this.f1256j;
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return 0;
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        return this.f1254h;
    }

    @Override // k0.b, android.view.MenuItem
    public final ColorStateList getIconTintList() {
        return this.f1257l;
    }

    @Override // k0.b, android.view.MenuItem
    public final PorterDuff.Mode getIconTintMode() {
        return this.f1258m;
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return this.f1249c;
    }

    @Override // android.view.MenuItem
    public final int getItemId() {
        return R.id.home;
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // k0.b, android.view.MenuItem
    public final int getNumericModifiers() {
        return this.f1251e;
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return this.f1250d;
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return 0;
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitle() {
        return this.f1247a;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f1248b;
        return charSequence != null ? charSequence : this.f1247a;
    }

    @Override // k0.b, android.view.MenuItem
    public final CharSequence getTooltipText() {
        return this.k;
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        return (this.f1261p & 1) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        return (this.f1261p & 2) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        return (this.f1261p & 16) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        return (this.f1261p & 8) == 0;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c10) {
        this.f1252f = Character.toLowerCase(c10);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z) {
        this.f1261p = (z ? 1 : 0) | (this.f1261p & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z) {
        this.f1261p = (z ? 2 : 0) | (this.f1261p & (-3));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setContentDescription(CharSequence charSequence) {
        this.f1256j = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z) {
        this.f1261p = (z ? 16 : 0) | (this.f1261p & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.f1254h = drawable;
        c();
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f1257l = colorStateList;
        this.f1259n = true;
        c();
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f1258m = mode;
        this.f1260o = true;
        c();
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        this.f1249c = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c10) {
        this.f1250d = c10;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c10, char c11) {
        this.f1250d = c10;
        this.f1252f = Character.toLowerCase(c11);
        return this;
    }

    @Override // android.view.MenuItem
    public final void setShowAsAction(int i10) {
    }

    @Override // android.view.MenuItem
    public final MenuItem setShowAsActionFlags(int i10) {
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.f1247a = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f1248b = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTooltipText(CharSequence charSequence) {
        this.k = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z) {
        this.f1261p = (this.f1261p & 8) | (z ? 0 : 8);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(int i10) {
        throw new UnsupportedOperationException();
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c10, int i10) {
        this.f1252f = Character.toLowerCase(c10);
        this.f1253g = KeyEvent.normalizeMetaState(i10);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final k0.b setContentDescription(CharSequence charSequence) {
        this.f1256j = charSequence;
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setNumericShortcut(char c10, int i10) {
        this.f1250d = c10;
        this.f1251e = KeyEvent.normalizeMetaState(i10);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i10) {
        this.f1247a = this.f1255i.getResources().getString(i10);
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final k0.b setTooltipText(CharSequence charSequence) {
        this.k = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i10) {
        this.f1254h = androidx.core.content.a.b(this.f1255i, i10);
        c();
        return this;
    }

    @Override // k0.b, android.view.MenuItem
    public final MenuItem setShortcut(char c10, char c11, int i10, int i11) {
        this.f1250d = c10;
        this.f1251e = KeyEvent.normalizeMetaState(i10);
        this.f1252f = Character.toLowerCase(c11);
        this.f1253g = KeyEvent.normalizeMetaState(i11);
        return this;
    }
}
