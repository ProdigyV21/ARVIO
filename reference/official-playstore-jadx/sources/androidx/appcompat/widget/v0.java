package androidx.appcompat.widget;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class v0 implements ListAdapter, SpinnerAdapter {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public SpinnerAdapter f1783i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ListAdapter f1784l;

    @Override // android.widget.ListAdapter
    public final boolean areAllItemsEnabled() {
        ListAdapter listAdapter = this.f1784l;
        if (listAdapter != null) {
            return listAdapter.areAllItemsEnabled();
        }
        return true;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        SpinnerAdapter spinnerAdapter = this.f1783i;
        if (spinnerAdapter == null) {
            return 0;
        }
        return spinnerAdapter.getCount();
    }

    @Override // android.widget.SpinnerAdapter
    public final View getDropDownView(int i10, View view, ViewGroup viewGroup) {
        SpinnerAdapter spinnerAdapter = this.f1783i;
        if (spinnerAdapter == null) {
            return null;
        }
        return spinnerAdapter.getDropDownView(i10, view, viewGroup);
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i10) {
        SpinnerAdapter spinnerAdapter = this.f1783i;
        if (spinnerAdapter == null) {
            return null;
        }
        return spinnerAdapter.getItem(i10);
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i10) {
        SpinnerAdapter spinnerAdapter = this.f1783i;
        if (spinnerAdapter == null) {
            return -1L;
        }
        return spinnerAdapter.getItemId(i10);
    }

    @Override // android.widget.Adapter
    public final int getItemViewType(int i10) {
        return 0;
    }

    @Override // android.widget.Adapter
    public final View getView(int i10, View view, ViewGroup viewGroup) {
        return getDropDownView(i10, view, viewGroup);
    }

    @Override // android.widget.Adapter
    public final int getViewTypeCount() {
        return 1;
    }

    @Override // android.widget.Adapter
    public final boolean hasStableIds() {
        SpinnerAdapter spinnerAdapter = this.f1783i;
        return spinnerAdapter != null && spinnerAdapter.hasStableIds();
    }

    @Override // android.widget.Adapter
    public final boolean isEmpty() {
        return getCount() == 0;
    }

    @Override // android.widget.ListAdapter
    public final boolean isEnabled(int i10) {
        ListAdapter listAdapter = this.f1784l;
        if (listAdapter != null) {
            return listAdapter.isEnabled(i10);
        }
        return true;
    }

    @Override // android.widget.Adapter
    public final void registerDataSetObserver(DataSetObserver dataSetObserver) {
        SpinnerAdapter spinnerAdapter = this.f1783i;
        if (spinnerAdapter != null) {
            spinnerAdapter.registerDataSetObserver(dataSetObserver);
        }
    }

    @Override // android.widget.Adapter
    public final void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        SpinnerAdapter spinnerAdapter = this.f1783i;
        if (spinnerAdapter != null) {
            spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
        }
    }
}
