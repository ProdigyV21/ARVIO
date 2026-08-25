package androidx.cursoradapter.widget;

import android.database.Cursor;
import android.widget.Filter;
import androidx.appcompat.widget.c4;

/* JADX INFO: loaded from: classes3.dex */
public final class d extends Filter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f2413a;

    @Override // android.widget.Filter
    public final CharSequence convertResultToString(Object obj) {
        return ((c4) this.f2413a).d((Cursor) obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0031  */
    @Override // android.widget.Filter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.widget.Filter.FilterResults performFiltering(java.lang.CharSequence r5) {
        /*
            r4 = this;
            androidx.cursoradapter.widget.b r0 = r4.f2413a
            androidx.appcompat.widget.c4 r0 = (androidx.appcompat.widget.c4) r0
            androidx.appcompat.widget.SearchView r1 = r0.f1539u
            if (r5 != 0) goto Lb
            java.lang.String r5 = ""
            goto Lf
        Lb:
            java.lang.String r5 = r5.toString()
        Lf:
            int r2 = r1.getVisibility()
            r3 = 0
            if (r2 != 0) goto L31
            int r1 = r1.getWindowVisibility()
            if (r1 == 0) goto L1d
            goto L31
        L1d:
            android.app.SearchableInfo r1 = r0.f1540v     // Catch: java.lang.RuntimeException -> L29
            android.database.Cursor r5 = r0.h(r1, r5)     // Catch: java.lang.RuntimeException -> L29
            if (r5 == 0) goto L31
            r5.getCount()     // Catch: java.lang.RuntimeException -> L29
            goto L32
        L29:
            r5 = move-exception
            java.lang.String r0 = "SuggestionsAdapter"
            java.lang.String r1 = "Search suggestions query threw an exception."
            android.util.Log.w(r0, r1, r5)
        L31:
            r5 = r3
        L32:
            android.widget.Filter$FilterResults r0 = new android.widget.Filter$FilterResults
            r0.<init>()
            if (r5 == 0) goto L42
            int r1 = r5.getCount()
            r0.count = r1
            r0.values = r5
            return r0
        L42:
            r5 = 0
            r0.count = r5
            r0.values = r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.cursoradapter.widget.d.performFiltering(java.lang.CharSequence):android.widget.Filter$FilterResults");
    }

    @Override // android.widget.Filter
    public final void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        b bVar = this.f2413a;
        Cursor cursor = bVar.f2408m;
        Object obj = filterResults.values;
        if (obj == null || obj == cursor) {
            return;
        }
        ((c4) bVar).c((Cursor) obj);
    }
}
