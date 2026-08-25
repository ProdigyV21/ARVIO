package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import t.l0;

/* JADX INFO: loaded from: classes.dex */
public class f0 extends e implements Menu {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final k0.a f1289e;

    public f0(Context context, k0.a aVar) {
        super(context);
        if (aVar == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.f1289e = aVar;
    }

    @Override // android.view.Menu
    public final MenuItem add(CharSequence charSequence) {
        return f(this.f1289e.add(charSequence));
    }

    @Override // android.view.Menu
    public final int addIntentOptions(int i10, int i11, int i12, ComponentName componentName, Intent[] intentArr, Intent intent, int i13, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int iAddIntentOptions = this.f1289e.addIntentOptions(i10, i11, i12, componentName, intentArr, intent, i13, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i14 = 0; i14 < length; i14++) {
                menuItemArr[i14] = f(menuItemArr2[i14]);
            }
        }
        return iAddIntentOptions;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return g(this.f1289e.addSubMenu(charSequence));
    }

    @Override // android.view.Menu
    public final void clear() {
        l0 l0Var = (l0) this.f1285c;
        if (l0Var != null) {
            l0Var.clear();
        }
        l0 l0Var2 = (l0) this.f1286d;
        if (l0Var2 != null) {
            l0Var2.clear();
        }
        this.f1289e.clear();
    }

    @Override // android.view.Menu
    public final void close() {
        this.f1289e.close();
    }

    @Override // android.view.Menu
    public final MenuItem findItem(int i10) {
        return f(this.f1289e.findItem(i10));
    }

    @Override // android.view.Menu
    public final MenuItem getItem(int i10) {
        return f(this.f1289e.getItem(i10));
    }

    @Override // android.view.Menu
    public final boolean hasVisibleItems() {
        return this.f1289e.hasVisibleItems();
    }

    @Override // android.view.Menu
    public final boolean isShortcutKey(int i10, KeyEvent keyEvent) {
        return this.f1289e.isShortcutKey(i10, keyEvent);
    }

    @Override // android.view.Menu
    public final boolean performIdentifierAction(int i10, int i11) {
        return this.f1289e.performIdentifierAction(i10, i11);
    }

    @Override // android.view.Menu
    public final boolean performShortcut(int i10, KeyEvent keyEvent, int i11) {
        return this.f1289e.performShortcut(i10, keyEvent, i11);
    }

    @Override // android.view.Menu
    public final void removeGroup(int i10) {
        if (((l0) this.f1285c) != null) {
            int i11 = 0;
            while (true) {
                l0 l0Var = (l0) this.f1285c;
                if (i11 >= l0Var.f21849m) {
                    break;
                }
                if (((k0.b) l0Var.h(i11)).getGroupId() == i10) {
                    ((l0) this.f1285c).i(i11);
                    i11--;
                }
                i11++;
            }
        }
        this.f1289e.removeGroup(i10);
    }

    @Override // android.view.Menu
    public final void removeItem(int i10) {
        if (((l0) this.f1285c) != null) {
            int i11 = 0;
            while (true) {
                l0 l0Var = (l0) this.f1285c;
                if (i11 >= l0Var.f21849m) {
                    break;
                }
                if (((k0.b) l0Var.h(i11)).getItemId() == i10) {
                    ((l0) this.f1285c).i(i11);
                    break;
                }
                i11++;
            }
        }
        this.f1289e.removeItem(i10);
    }

    @Override // android.view.Menu
    public final void setGroupCheckable(int i10, boolean z, boolean z5) {
        this.f1289e.setGroupCheckable(i10, z, z5);
    }

    @Override // android.view.Menu
    public final void setGroupEnabled(int i10, boolean z) {
        this.f1289e.setGroupEnabled(i10, z);
    }

    @Override // android.view.Menu
    public final void setGroupVisible(int i10, boolean z) {
        this.f1289e.setGroupVisible(i10, z);
    }

    @Override // android.view.Menu
    public final void setQwertyMode(boolean z) {
        this.f1289e.setQwertyMode(z);
    }

    @Override // android.view.Menu
    public final int size() {
        return this.f1289e.size();
    }

    @Override // android.view.Menu
    public final MenuItem add(int i10) {
        return f(this.f1289e.add(i10));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i10) {
        return g(this.f1289e.addSubMenu(i10));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i10, int i11, int i12, CharSequence charSequence) {
        return f(this.f1289e.add(i10, i11, i12, charSequence));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i10, int i11, int i12, CharSequence charSequence) {
        return g(this.f1289e.addSubMenu(i10, i11, i12, charSequence));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i10, int i11, int i12, int i13) {
        return f(this.f1289e.add(i10, i11, i12, i13));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i10, int i11, int i12, int i13) {
        return g(this.f1289e.addSubMenu(i10, i11, i12, i13));
    }
}
