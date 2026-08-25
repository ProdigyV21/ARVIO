package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.work.impl.WorkDatabase;
import g8.v0;
import j8.t0;
import java.util.concurrent.atomic.AtomicBoolean;
import t.l0;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1283a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1284b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f1285c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f1286d;

    public e(c9.f fVar, androidx.work.impl.constraints.j jVar, v0 v0Var) {
        this.f1283a = 1;
        this.f1284b = fVar;
        this.f1285c = jVar;
        this.f1286d = v0Var;
    }

    public c2.g c() {
        WorkDatabase workDatabase = (WorkDatabase) this.f1284b;
        workDatabase.a();
        if (((AtomicBoolean) this.f1285c).compareAndSet(false, true)) {
            return (c2.g) ((x6.i0) this.f1286d).getValue();
        }
        String strD = d();
        workDatabase.a();
        workDatabase.b();
        return workDatabase.h().getWritableDatabase().g0(strD);
    }

    public abstract String d();

    public abstract f9.c e();

    public MenuItem f(MenuItem menuItem) {
        if (!(menuItem instanceof k0.b)) {
            return menuItem;
        }
        k0.b bVar = (k0.b) menuItem;
        if (((l0) this.f1285c) == null) {
            this.f1285c = new l0(0);
        }
        MenuItem menuItem2 = (MenuItem) ((l0) this.f1285c).get(bVar);
        if (menuItem2 != null) {
            return menuItem2;
        }
        y yVar = new y((Context) this.f1284b, bVar);
        ((l0) this.f1285c).put(bVar, yVar);
        return yVar;
    }

    public SubMenu g(SubMenu subMenu) {
        if (!(subMenu instanceof k0.c)) {
            return subMenu;
        }
        k0.c cVar = (k0.c) subMenu;
        if (((l0) this.f1286d) == null) {
            this.f1286d = new l0(0);
        }
        SubMenu subMenu2 = (SubMenu) ((l0) this.f1286d).get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        j0 j0Var = new j0((Context) this.f1284b, cVar);
        ((l0) this.f1286d).put(cVar, j0Var);
        return j0Var;
    }

    public void h(c2.g gVar) {
        if (gVar == ((c2.g) ((x6.i0) this.f1286d).getValue())) {
            ((AtomicBoolean) this.f1285c).set(false);
        }
    }

    public String toString() {
        switch (this.f1283a) {
            case 1:
                return getClass().getSimpleName() + ": " + e();
            default:
                return super.toString();
        }
    }

    public e(Context context) {
        this.f1283a = 0;
        this.f1284b = context;
    }

    public e(WorkDatabase workDatabase) {
        this.f1283a = 2;
        this.f1284b = workDatabase;
        this.f1285c = new AtomicBoolean(false);
        this.f1286d = new x6.i0(new t0(this, 12));
    }
}
