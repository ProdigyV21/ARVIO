package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import androidx.appcompat.view.menu.d0;

/* JADX INFO: loaded from: classes.dex */
public final class m implements d0, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Context f1329i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public LayoutInflater f1330l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public q f1331m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ExpandedMenuView f1332n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public d0.a f1333o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public l f1334p;

    public m(Context context) {
        this.f1329i = context;
        this.f1330l = LayoutInflater.from(context);
    }

    @Override // androidx.appcompat.view.menu.d0
    public final void b(q qVar, boolean z) {
        d0.a aVar = this.f1333o;
        if (aVar != null) {
            aVar.b(qVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.d0
    public final void c(d0.a aVar) {
        throw null;
    }

    @Override // androidx.appcompat.view.menu.d0
    public final boolean d(t tVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.d0
    public final void e() {
        l lVar = this.f1334p;
        if (lVar != null) {
            lVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.d0
    public final boolean f(i0 i0Var) {
        boolean zHasVisibleItems = i0Var.hasVisibleItems();
        Context context = i0Var.f1342a;
        if (!zHasVisibleItems) {
            return false;
        }
        r rVar = new r();
        rVar.f1364i = i0Var;
        androidx.appcompat.app.p pVar = new androidx.appcompat.app.p(context);
        m mVar = new m(pVar.getContext());
        rVar.f1366m = mVar;
        mVar.f1333o = rVar;
        i0Var.b(mVar, context);
        m mVar2 = rVar.f1366m;
        if (mVar2.f1334p == null) {
            mVar2.f1334p = new l(mVar2);
        }
        l lVar = mVar2.f1334p;
        androidx.appcompat.app.l lVar2 = pVar.f1147a;
        lVar2.k = lVar;
        lVar2.f1082l = rVar;
        View view = i0Var.f1355o;
        if (view != null) {
            lVar2.f1076e = view;
        } else {
            lVar2.f1074c = i0Var.f1354n;
            pVar.setTitle(i0Var.f1353m);
        }
        lVar2.f1081j = rVar;
        androidx.appcompat.app.q qVarCreate = pVar.create();
        rVar.f1365l = qVarCreate;
        qVarCreate.setOnDismissListener(rVar);
        WindowManager.LayoutParams attributes = rVar.f1365l.getWindow().getAttributes();
        attributes.type = 1003;
        attributes.flags |= 131072;
        rVar.f1365l.show();
        d0.a aVar = this.f1333o;
        if (aVar == null) {
            return true;
        }
        aVar.c(i0Var);
        return true;
    }

    @Override // androidx.appcompat.view.menu.d0
    public final boolean g(t tVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.d0
    public final boolean h() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.d0
    public final void i(Context context, q qVar) {
        if (this.f1329i != null) {
            this.f1329i = context;
            if (this.f1330l == null) {
                this.f1330l = LayoutInflater.from(context);
            }
        }
        this.f1331m = qVar;
        l lVar = this.f1334p;
        if (lVar != null) {
            lVar.notifyDataSetChanged();
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i10, long j10) {
        this.f1331m.q(this.f1334p.getItem(i10), this, 0);
    }
}
