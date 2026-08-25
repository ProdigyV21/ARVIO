package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.e0;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class n extends BaseAdapter {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final q f1335i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1336l = -1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1337m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f1338n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final LayoutInflater f1339o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f1340p;

    public n(q qVar, LayoutInflater layoutInflater, boolean z, int i10) {
        this.f1338n = z;
        this.f1339o = layoutInflater;
        this.f1335i = qVar;
        this.f1340p = i10;
        b();
    }

    public final void b() {
        q qVar = this.f1335i;
        t tVar = qVar.f1362v;
        if (tVar != null) {
            qVar.i();
            ArrayList arrayList = qVar.f1351j;
            int size = arrayList.size();
            for (int i10 = 0; i10 < size; i10++) {
                if (((t) arrayList.get(i10)) == tVar) {
                    this.f1336l = i10;
                    return;
                }
            }
        }
        this.f1336l = -1;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final t getItem(int i10) {
        ArrayList arrayListL;
        boolean z = this.f1338n;
        q qVar = this.f1335i;
        if (z) {
            qVar.i();
            arrayListL = qVar.f1351j;
        } else {
            arrayListL = qVar.l();
        }
        int i11 = this.f1336l;
        if (i11 >= 0 && i10 >= i11) {
            i10++;
        }
        return (t) arrayListL.get(i10);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        ArrayList arrayListL;
        boolean z = this.f1338n;
        q qVar = this.f1335i;
        if (z) {
            qVar.i();
            arrayListL = qVar.f1351j;
        } else {
            arrayListL = qVar.l();
        }
        return this.f1336l < 0 ? arrayListL.size() : arrayListL.size() - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i10) {
        return i10;
    }

    @Override // android.widget.Adapter
    public final View getView(int i10, View view, ViewGroup viewGroup) {
        boolean z = false;
        if (view == null) {
            view = this.f1339o.inflate(this.f1340p, viewGroup, false);
        }
        int i11 = getItem(i10).f1368b;
        int i12 = i10 - 1;
        int i13 = i12 >= 0 ? getItem(i12).f1368b : i11;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        if (this.f1335i.m() && i11 != i13) {
            z = true;
        }
        listMenuItemView.setGroupDividerEnabled(z);
        e0.a aVar = (e0.a) view;
        if (this.f1337m) {
            listMenuItemView.setForceShowIcon(true);
        }
        aVar.c(getItem(i10));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        b();
        super.notifyDataSetChanged();
    }
}
