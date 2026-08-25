package androidx.appcompat.view.menu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.e0;
import com.arvio.tv.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class l extends BaseAdapter {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1327i = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ m f1328l;

    public l(m mVar) {
        this.f1328l = mVar;
        b();
    }

    public final void b() {
        q qVar = this.f1328l.f1331m;
        t tVar = qVar.f1362v;
        if (tVar != null) {
            qVar.i();
            ArrayList arrayList = qVar.f1351j;
            int size = arrayList.size();
            for (int i10 = 0; i10 < size; i10++) {
                if (((t) arrayList.get(i10)) == tVar) {
                    this.f1327i = i10;
                    return;
                }
            }
        }
        this.f1327i = -1;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final t getItem(int i10) {
        m mVar = this.f1328l;
        q qVar = mVar.f1331m;
        qVar.i();
        ArrayList arrayList = qVar.f1351j;
        mVar.getClass();
        int i11 = this.f1327i;
        if (i11 >= 0 && i10 >= i11) {
            i10++;
        }
        return (t) arrayList.get(i10);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        m mVar = this.f1328l;
        q qVar = mVar.f1331m;
        qVar.i();
        int size = qVar.f1351j.size();
        mVar.getClass();
        return this.f1327i < 0 ? size : size - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i10) {
        return i10;
    }

    @Override // android.widget.Adapter
    public final View getView(int i10, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f1328l.f1330l.inflate(R.layout.abc_list_menu_item_layout, viewGroup, false);
        }
        ((e0.a) view).c(getItem(i10));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        b();
        super.notifyDataSetChanged();
    }
}
