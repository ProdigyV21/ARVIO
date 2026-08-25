package androidx.cursoradapter.widget;

import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.appcompat.widget.c4;
import androidx.appcompat.widget.t2;

/* JADX INFO: loaded from: classes3.dex */
public abstract class b extends BaseAdapter implements Filterable, c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2406i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f2407l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Cursor f2408m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2409n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public a f2410o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public t2 f2411p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public d f2412q;

    public abstract void b(View view, Cursor cursor);

    public void c(Cursor cursor) {
        Cursor cursor2 = this.f2408m;
        if (cursor == cursor2) {
            cursor2 = null;
        } else {
            if (cursor2 != null) {
                a aVar = this.f2410o;
                if (aVar != null) {
                    cursor2.unregisterContentObserver(aVar);
                }
                t2 t2Var = this.f2411p;
                if (t2Var != null) {
                    cursor2.unregisterDataSetObserver(t2Var);
                }
            }
            this.f2408m = cursor;
            if (cursor != null) {
                a aVar2 = this.f2410o;
                if (aVar2 != null) {
                    cursor.registerContentObserver(aVar2);
                }
                t2 t2Var2 = this.f2411p;
                if (t2Var2 != null) {
                    cursor.registerDataSetObserver(t2Var2);
                }
                this.f2409n = cursor.getColumnIndexOrThrow("_id");
                this.f2406i = true;
                notifyDataSetChanged();
            } else {
                this.f2409n = -1;
                this.f2406i = false;
                notifyDataSetInvalidated();
            }
        }
        if (cursor2 != null) {
            cursor2.close();
        }
    }

    public abstract String d(Cursor cursor);

    public abstract View e(ViewGroup viewGroup);

    @Override // android.widget.Adapter
    public final int getCount() {
        Cursor cursor;
        if (!this.f2406i || (cursor = this.f2408m) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i10, View view, ViewGroup viewGroup) {
        if (!this.f2406i) {
            return null;
        }
        this.f2408m.moveToPosition(i10);
        if (view == null) {
            c4 c4Var = (c4) this;
            view = c4Var.f1538t.inflate(c4Var.f1537s, viewGroup, false);
        }
        b(view, this.f2408m);
        return view;
    }

    @Override // android.widget.Filterable
    public final Filter getFilter() {
        if (this.f2412q == null) {
            d dVar = new d();
            dVar.f2413a = this;
            this.f2412q = dVar;
        }
        return this.f2412q;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i10) {
        Cursor cursor;
        if (!this.f2406i || (cursor = this.f2408m) == null) {
            return null;
        }
        cursor.moveToPosition(i10);
        return this.f2408m;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i10) {
        Cursor cursor;
        if (this.f2406i && (cursor = this.f2408m) != null && cursor.moveToPosition(i10)) {
            return this.f2408m.getLong(this.f2409n);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i10, View view, ViewGroup viewGroup) {
        if (!this.f2406i) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!this.f2408m.moveToPosition(i10)) {
            throw new IllegalStateException(a0.c.i(i10, "couldn't move cursor to position "));
        }
        if (view == null) {
            view = e(viewGroup);
        }
        b(view, this.f2408m);
        return view;
    }
}
