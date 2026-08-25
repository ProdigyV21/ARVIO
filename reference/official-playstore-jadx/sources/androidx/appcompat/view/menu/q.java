package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.view.d2;
import androidx.core.view.f2;
import com.google.android.gms.cast.CredentialsData;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class q implements k0.a {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final int[] f1341y = {1, 4, 5, 3, 2, 0};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1342a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Resources f1343b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1344c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f1345d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public o f1346e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f1347f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f1348g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1349h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f1350i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList f1351j;
    public boolean k;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public CharSequence f1353m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Drawable f1354n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public View f1355o;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public t f1362v;
    public boolean x;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1352l = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1356p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1357q = false;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1358r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1359s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final ArrayList f1360t = new ArrayList();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final CopyOnWriteArrayList f1361u = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f1363w = false;

    public q(Context context) {
        boolean zB;
        boolean z = false;
        this.f1342a = context;
        Resources resources = context.getResources();
        this.f1343b = resources;
        this.f1347f = new ArrayList();
        this.f1348g = new ArrayList();
        this.f1349h = true;
        this.f1350i = new ArrayList();
        this.f1351j = new ArrayList();
        this.k = true;
        if (resources.getConfiguration().keyboard != 1) {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            Method method = f2.f2233a;
            if (Build.VERSION.SDK_INT >= 28) {
                zB = d2.b(viewConfiguration);
            } else {
                Resources resources2 = context.getResources();
                int identifier = resources2.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", CredentialsData.CREDENTIALS_TYPE_ANDROID);
                zB = identifier != 0 && resources2.getBoolean(identifier);
            }
            if (zB) {
                z = true;
            }
        }
        this.f1345d = z;
    }

    public final t a(int i10, int i11, int i12, CharSequence charSequence) {
        int i13;
        int i14 = ((-65536) & i12) >> 16;
        if (i14 < 0 || i14 >= 6) {
            throw new IllegalArgumentException("order does not contain a valid category.");
        }
        int i15 = (f1341y[i14] << 16) | (65535 & i12);
        t tVar = new t(this, i10, i11, i12, i15, charSequence, this.f1352l);
        ArrayList arrayList = this.f1347f;
        int size = arrayList.size() - 1;
        while (true) {
            if (size < 0) {
                i13 = 0;
                break;
            }
            if (((t) arrayList.get(size)).f1370d <= i15) {
                i13 = size + 1;
                break;
            }
            size--;
        }
        arrayList.add(i13, tVar);
        p(true);
        return tVar;
    }

    @Override // android.view.Menu
    public final MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public final int addIntentOptions(int i10, int i11, int i12, ComponentName componentName, Intent[] intentArr, Intent intent, int i13, MenuItem[] menuItemArr) {
        int i14;
        PackageManager packageManager = this.f1342a.getPackageManager();
        List<ResolveInfo> listQueryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = listQueryIntentActivityOptions != null ? listQueryIntentActivityOptions.size() : 0;
        if ((i13 & 1) == 0) {
            removeGroup(i10);
        }
        for (int i15 = 0; i15 < size; i15++) {
            ResolveInfo resolveInfo = listQueryIntentActivityOptions.get(i15);
            int i16 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i16 < 0 ? intent : intentArr[i16]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            t tVarA = a(i10, i11, i12, resolveInfo.loadLabel(packageManager));
            tVarA.setIcon(resolveInfo.loadIcon(packageManager));
            tVarA.f1373g = intent2;
            if (menuItemArr != null && (i14 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i14] = tVarA;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public final void b(d0 d0Var, Context context) {
        this.f1361u.add(new WeakReference(d0Var));
        d0Var.i(context, this);
        this.k = true;
    }

    public final void c(boolean z) {
        if (this.f1359s) {
            return;
        }
        this.f1359s = true;
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f1361u;
        for (WeakReference weakReference : copyOnWriteArrayList) {
            d0 d0Var = (d0) weakReference.get();
            if (d0Var == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                d0Var.b(this, z);
            }
        }
        this.f1359s = false;
    }

    @Override // android.view.Menu
    public final void clear() {
        t tVar = this.f1362v;
        if (tVar != null) {
            d(tVar);
        }
        this.f1347f.clear();
        p(true);
    }

    public final void clearHeader() {
        this.f1354n = null;
        this.f1353m = null;
        this.f1355o = null;
        p(false);
    }

    @Override // android.view.Menu
    public final void close() {
        c(true);
    }

    public boolean d(t tVar) {
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f1361u;
        boolean zD = false;
        if (!copyOnWriteArrayList.isEmpty() && this.f1362v == tVar) {
            w();
            for (WeakReference weakReference : copyOnWriteArrayList) {
                d0 d0Var = (d0) weakReference.get();
                if (d0Var != null) {
                    zD = d0Var.d(tVar);
                    if (zD) {
                        break;
                    }
                } else {
                    copyOnWriteArrayList.remove(weakReference);
                }
            }
            v();
            if (zD) {
                this.f1362v = null;
            }
        }
        return zD;
    }

    public boolean e(q qVar, MenuItem menuItem) {
        o oVar = this.f1346e;
        return oVar != null && oVar.b(qVar, menuItem);
    }

    public boolean f(t tVar) {
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f1361u;
        boolean zG = false;
        if (copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        w();
        for (WeakReference weakReference : copyOnWriteArrayList) {
            d0 d0Var = (d0) weakReference.get();
            if (d0Var != null) {
                zG = d0Var.g(tVar);
                if (zG) {
                    break;
                }
            } else {
                copyOnWriteArrayList.remove(weakReference);
            }
        }
        v();
        if (zG) {
            this.f1362v = tVar;
        }
        return zG;
    }

    @Override // android.view.Menu
    public final MenuItem findItem(int i10) {
        MenuItem menuItemFindItem;
        ArrayList arrayList = this.f1347f;
        int size = arrayList.size();
        for (int i11 = 0; i11 < size; i11++) {
            t tVar = (t) arrayList.get(i11);
            if (tVar.f1367a == i10) {
                return tVar;
            }
            if (tVar.hasSubMenu() && (menuItemFindItem = tVar.f1380o.findItem(i10)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    public final t g(int i10, KeyEvent keyEvent) {
        ArrayList arrayList = this.f1360t;
        arrayList.clear();
        h(arrayList, i10, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return (t) arrayList.get(0);
        }
        boolean zN = n();
        for (int i11 = 0; i11 < size; i11++) {
            t tVar = (t) arrayList.get(i11);
            char c10 = zN ? tVar.f1376j : tVar.f1374h;
            char[] cArr = keyData.meta;
            if ((c10 == cArr[0] && (metaState & 2) == 0) || ((c10 == cArr[2] && (metaState & 2) != 0) || (zN && c10 == '\b' && i10 == 67))) {
                return tVar;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public final MenuItem getItem(int i10) {
        return (MenuItem) this.f1347f.get(i10);
    }

    public final void h(List list, int i10, KeyEvent keyEvent) {
        boolean zN = n();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i10 == 67) {
            ArrayList arrayList = this.f1347f;
            int size = arrayList.size();
            for (int i11 = 0; i11 < size; i11++) {
                t tVar = (t) arrayList.get(i11);
                if (tVar.hasSubMenu()) {
                    tVar.f1380o.h(list, i10, keyEvent);
                }
                char c10 = zN ? tVar.f1376j : tVar.f1374h;
                if ((modifiers & 69647) == ((zN ? tVar.k : tVar.f1375i) & 69647) && c10 != 0) {
                    char[] cArr = keyData.meta;
                    if ((c10 == cArr[0] || c10 == cArr[2] || (zN && c10 == '\b' && i10 == 67)) && tVar.isEnabled()) {
                        list.add(tVar);
                    }
                }
            }
        }
    }

    @Override // android.view.Menu
    public final boolean hasVisibleItems() {
        if (this.x) {
            return true;
        }
        ArrayList arrayList = this.f1347f;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (((t) arrayList.get(i10)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public final void i() {
        ArrayList arrayListL = l();
        if (this.k) {
            CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f1361u;
            boolean zH = false;
            for (WeakReference weakReference : copyOnWriteArrayList) {
                d0 d0Var = (d0) weakReference.get();
                if (d0Var == null) {
                    copyOnWriteArrayList.remove(weakReference);
                } else {
                    zH |= d0Var.h();
                }
            }
            ArrayList arrayList = this.f1350i;
            ArrayList arrayList2 = this.f1351j;
            if (zH) {
                arrayList.clear();
                arrayList2.clear();
                int size = arrayListL.size();
                for (int i10 = 0; i10 < size; i10++) {
                    t tVar = (t) arrayListL.get(i10);
                    if ((tVar.x & 32) == 32) {
                        arrayList.add(tVar);
                    } else {
                        arrayList2.add(tVar);
                    }
                }
            } else {
                arrayList.clear();
                arrayList2.clear();
                arrayList2.addAll(l());
            }
            this.k = false;
        }
    }

    @Override // android.view.Menu
    public final boolean isShortcutKey(int i10, KeyEvent keyEvent) {
        return g(i10, keyEvent) != null;
    }

    public String j() {
        return "android:menu:actionviewstates";
    }

    public q k() {
        return this;
    }

    public final ArrayList l() {
        boolean z = this.f1349h;
        ArrayList arrayList = this.f1348g;
        if (!z) {
            return arrayList;
        }
        arrayList.clear();
        ArrayList arrayList2 = this.f1347f;
        int size = arrayList2.size();
        for (int i10 = 0; i10 < size; i10++) {
            t tVar = (t) arrayList2.get(i10);
            if (tVar.isVisible()) {
                arrayList.add(tVar);
            }
        }
        this.f1349h = false;
        this.k = true;
        return arrayList;
    }

    public boolean m() {
        return this.f1363w;
    }

    public boolean n() {
        return this.f1344c;
    }

    public boolean o() {
        return this.f1345d;
    }

    public final void p(boolean z) {
        if (this.f1356p) {
            this.f1357q = true;
            if (z) {
                this.f1358r = true;
                return;
            }
            return;
        }
        if (z) {
            this.f1349h = true;
            this.k = true;
        }
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f1361u;
        if (copyOnWriteArrayList.isEmpty()) {
            return;
        }
        w();
        for (WeakReference weakReference : copyOnWriteArrayList) {
            d0 d0Var = (d0) weakReference.get();
            if (d0Var == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                d0Var.e();
            }
        }
        v();
    }

    @Override // android.view.Menu
    public final boolean performIdentifierAction(int i10, int i11) {
        return q(findItem(i10), null, i11);
    }

    @Override // android.view.Menu
    public final boolean performShortcut(int i10, KeyEvent keyEvent, int i11) {
        t tVarG = g(i10, keyEvent);
        boolean zQ = tVarG != null ? q(tVarG, null, i11) : false;
        if ((i11 & 2) != 0) {
            c(true);
        }
        return zQ;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean q(android.view.MenuItem r7, androidx.appcompat.view.menu.d0 r8, int r9) {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.q.q(android.view.MenuItem, androidx.appcompat.view.menu.d0, int):boolean");
    }

    public final void r(d0 d0Var) {
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f1361u;
        for (WeakReference weakReference : copyOnWriteArrayList) {
            d0 d0Var2 = (d0) weakReference.get();
            if (d0Var2 == null || d0Var2 == d0Var) {
                copyOnWriteArrayList.remove(weakReference);
            }
        }
    }

    @Override // android.view.Menu
    public final void removeGroup(int i10) {
        ArrayList arrayList = this.f1347f;
        int size = arrayList.size();
        int i11 = 0;
        int i12 = 0;
        while (true) {
            if (i12 >= size) {
                i12 = -1;
                break;
            } else if (((t) arrayList.get(i12)).f1368b == i10) {
                break;
            } else {
                i12++;
            }
        }
        if (i12 >= 0) {
            int size2 = arrayList.size() - i12;
            while (true) {
                int i13 = i11 + 1;
                if (i11 >= size2 || ((t) arrayList.get(i12)).f1368b != i10) {
                    break;
                }
                if (i12 >= 0 && i12 < arrayList.size()) {
                    arrayList.remove(i12);
                }
                i11 = i13;
            }
            p(true);
        }
    }

    @Override // android.view.Menu
    public final void removeItem(int i10) {
        ArrayList arrayList = this.f1347f;
        int size = arrayList.size();
        int i11 = 0;
        while (true) {
            if (i11 >= size) {
                i11 = -1;
                break;
            } else if (((t) arrayList.get(i11)).f1367a == i10) {
                break;
            } else {
                i11++;
            }
        }
        if (i11 < 0 || i11 >= arrayList.size()) {
            return;
        }
        arrayList.remove(i11);
        p(true);
    }

    public final void s(Bundle bundle) {
        MenuItem menuItemFindItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(j());
        int size = this.f1347f.size();
        for (int i10 = 0; i10 < size; i10++) {
            MenuItem item = getItem(i10);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((i0) item.getSubMenu()).s(bundle);
            }
        }
        int i11 = bundle.getInt("android:menu:expandedactionview");
        if (i11 <= 0 || (menuItemFindItem = findItem(i11)) == null) {
            return;
        }
        menuItemFindItem.expandActionView();
    }

    @Override // android.view.Menu
    public final void setGroupCheckable(int i10, boolean z, boolean z5) {
        ArrayList arrayList = this.f1347f;
        int size = arrayList.size();
        for (int i11 = 0; i11 < size; i11++) {
            t tVar = (t) arrayList.get(i11);
            if (tVar.f1368b == i10) {
                tVar.x = (tVar.x & (-5)) | (z5 ? 4 : 0);
                tVar.setCheckable(z);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.f1363w = z;
    }

    @Override // android.view.Menu
    public final void setGroupEnabled(int i10, boolean z) {
        ArrayList arrayList = this.f1347f;
        int size = arrayList.size();
        for (int i11 = 0; i11 < size; i11++) {
            t tVar = (t) arrayList.get(i11);
            if (tVar.f1368b == i10) {
                tVar.setEnabled(z);
            }
        }
    }

    @Override // android.view.Menu
    public final void setGroupVisible(int i10, boolean z) {
        ArrayList arrayList = this.f1347f;
        int size = arrayList.size();
        boolean z5 = false;
        for (int i11 = 0; i11 < size; i11++) {
            t tVar = (t) arrayList.get(i11);
            if (tVar.f1368b == i10) {
                int i12 = tVar.x;
                int i13 = (i12 & (-9)) | (z ? 0 : 8);
                tVar.x = i13;
                if (i12 != i13) {
                    z5 = true;
                }
            }
        }
        if (z5) {
            p(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.f1344c = z;
        p(false);
    }

    @Override // android.view.Menu
    public final int size() {
        return this.f1347f.size();
    }

    public final void t(Bundle bundle) {
        int size = this.f1347f.size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i10 = 0; i10 < size; i10++) {
            MenuItem item = getItem(i10);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((i0) item.getSubMenu()).t(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(j(), sparseArray);
        }
    }

    public final void u(int i10, CharSequence charSequence, int i11, Drawable drawable, View view) {
        if (view != null) {
            this.f1355o = view;
            this.f1353m = null;
            this.f1354n = null;
        } else {
            if (i10 > 0) {
                this.f1353m = this.f1343b.getText(i10);
            } else if (charSequence != null) {
                this.f1353m = charSequence;
            }
            if (i11 > 0) {
                this.f1354n = androidx.core.content.a.b(this.f1342a, i11);
            } else if (drawable != null) {
                this.f1354n = drawable;
            }
            this.f1355o = null;
        }
        p(false);
    }

    public final void v() {
        this.f1356p = false;
        if (this.f1357q) {
            this.f1357q = false;
            p(this.f1358r);
        }
    }

    public final void w() {
        if (this.f1356p) {
            return;
        }
        this.f1356p = true;
        this.f1357q = false;
        this.f1358r = false;
    }

    @Override // android.view.Menu
    public final MenuItem add(int i10) {
        return a(0, 0, 0, this.f1343b.getString(i10));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i10) {
        return addSubMenu(0, 0, 0, this.f1343b.getString(i10));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i10, int i11, int i12, CharSequence charSequence) {
        return a(i10, i11, i12, charSequence);
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i10, int i11, int i12, CharSequence charSequence) {
        t tVarA = a(i10, i11, i12, charSequence);
        i0 i0Var = new i0(this.f1342a, this, tVarA);
        tVarA.f1380o = i0Var;
        i0Var.setHeaderTitle(tVarA.f1371e);
        return i0Var;
    }

    @Override // android.view.Menu
    public final MenuItem add(int i10, int i11, int i12, int i13) {
        return a(i10, i11, i12, this.f1343b.getString(i13));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i10, int i11, int i12, int i13) {
        return addSubMenu(i10, i11, i12, this.f1343b.getString(i13));
    }
}
