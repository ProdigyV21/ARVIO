package com.google.android.gms.cast.framework.media;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.common.internal.t;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbk extends ArrayAdapter implements View.OnClickListener {
    private final Context zza;
    private int zzb;

    public zzbk(Context context, List list, int i10) {
        super(context, R.layout.cast_tracks_chooser_dialog_row_layout, list == null ? new ArrayList() : list);
        this.zza = context;
        this.zzb = i10;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0084  */
    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View getView(int r7, android.view.View r8, android.view.ViewGroup r9) {
        /*
            r6 = this;
            r0 = 0
            if (r8 != 0) goto L30
            android.content.Context r8 = r6.zza
            java.lang.String r1 = "layout_inflater"
            java.lang.Object r8 = r8.getSystemService(r1)
            android.view.LayoutInflater r8 = (android.view.LayoutInflater) r8
            com.google.android.gms.common.internal.t.i(r8)
            int r1 = com.google.android.gms.cast.framework.R.layout.cast_tracks_chooser_dialog_row_layout
            android.view.View r8 = r8.inflate(r1, r9, r0)
            com.google.android.gms.cast.framework.media.zzbj r9 = new com.google.android.gms.cast.framework.media.zzbj
            int r1 = com.google.android.gms.cast.framework.R.id.text
            android.view.View r1 = r8.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            int r2 = com.google.android.gms.cast.framework.R.id.radio
            android.view.View r2 = r8.findViewById(r2)
            android.widget.RadioButton r2 = (android.widget.RadioButton) r2
            r3 = 0
            r9.<init>(r6, r1, r2, r3)
            r8.setTag(r9)
            goto L39
        L30:
            java.lang.Object r9 = r8.getTag()
            com.google.android.gms.cast.framework.media.zzbj r9 = (com.google.android.gms.cast.framework.media.zzbj) r9
            com.google.android.gms.common.internal.t.i(r9)
        L39:
            android.widget.RadioButton r1 = r9.zzb
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)
            r1.setTag(r2)
            int r2 = r6.zzb
            r3 = 1
            if (r2 != r7) goto L49
            r2 = r3
            goto L4a
        L49:
            r2 = r0
        L4a:
            r1.setChecked(r2)
            r8.setOnClickListener(r6)
            java.lang.Object r1 = r6.getItem(r7)
            com.google.android.gms.cast.MediaTrack r1 = (com.google.android.gms.cast.MediaTrack) r1
            com.google.android.gms.common.internal.t.i(r1)
            java.lang.String r2 = r1.getName()
            java.util.Locale r4 = r1.getLanguageLocale()
            boolean r5 = android.text.TextUtils.isEmpty(r2)
            if (r5 == 0) goto L95
            int r1 = r1.getSubtype()
            r2 = 2
            if (r1 != r2) goto L77
            android.content.Context r7 = r6.zza
            int r0 = com.google.android.gms.cast.framework.R.string.cast_tracks_chooser_dialog_closed_captions
            java.lang.String r2 = r7.getString(r0)
            goto L95
        L77:
            if (r4 == 0) goto L84
            java.lang.String r2 = r4.getDisplayLanguage()
            boolean r1 = android.text.TextUtils.isEmpty(r2)
            if (r1 != 0) goto L84
            goto L95
        L84:
            android.content.Context r1 = r6.zza
            int r2 = com.google.android.gms.cast.framework.R.string.cast_tracks_chooser_dialog_default_track_name
            int r7 = r7 + r3
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r0] = r7
            java.lang.String r2 = r1.getString(r2, r3)
        L95:
            android.widget.TextView r7 = r9.zza
            r7.setText(r2)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzbk.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        zzbj zzbjVar = (zzbj) view.getTag();
        t.i(zzbjVar);
        Object tag = zzbjVar.zzb.getTag();
        t.i(tag);
        this.zzb = ((Integer) tag).intValue();
        notifyDataSetChanged();
    }

    public final MediaTrack zza() {
        int i10 = this.zzb;
        if (i10 < 0 || i10 >= getCount()) {
            return null;
        }
        return (MediaTrack) getItem(this.zzb);
    }
}
