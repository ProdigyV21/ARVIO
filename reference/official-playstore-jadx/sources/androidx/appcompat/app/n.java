package androidx.appcompat.app;

import android.widget.ArrayAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class n extends ArrayAdapter {
    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final long getItemId(int i10) {
        return i10;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final boolean hasStableIds() {
        return true;
    }
}
