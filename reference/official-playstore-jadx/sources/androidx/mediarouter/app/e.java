package androidx.mediarouter.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.mediarouter.media.i0;
import com.arvio.tv.R;
import java.util.ArrayList;
import m2.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class e extends ArrayAdapter implements AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final LayoutInflater f4277i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Drawable f4278l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Drawable f4279m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Drawable f4280n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Drawable f4281o;

    public e(Context context, ArrayList arrayList) {
        super(context, 0, arrayList);
        this.f4277i = LayoutInflater.from(context);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(new int[]{R.attr.mediaRouteDefaultIconDrawable, R.attr.mediaRouteTvIconDrawable, R.attr.mediaRouteSpeakerIconDrawable, R.attr.mediaRouteSpeakerGroupIconDrawable});
        this.f4278l = f0.o(context, typedArrayObtainStyledAttributes.getResourceId(0, 0));
        this.f4279m = f0.o(context, typedArrayObtainStyledAttributes.getResourceId(1, 0));
        this.f4280n = f0.o(context, typedArrayObtainStyledAttributes.getResourceId(2, 0));
        this.f4281o = f0.o(context, typedArrayObtainStyledAttributes.getResourceId(3, 0));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean areAllItemsEnabled() {
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0090  */
    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View getView(int r7, android.view.View r8, android.view.ViewGroup r9) {
        /*
            r6 = this;
            r0 = 0
            if (r8 != 0) goto Lc
            android.view.LayoutInflater r8 = r6.f4277i
            r1 = 2131624060(0x7f0e007c, float:1.887529E38)
            android.view.View r8 = r8.inflate(r1, r9, r0)
        Lc:
            java.lang.Object r7 = r6.getItem(r7)
            androidx.mediarouter.media.i0 r7 = (androidx.mediarouter.media.i0) r7
            r9 = 2131427723(0x7f0b018b, float:1.847707E38)
            android.view.View r9 = r8.findViewById(r9)
            android.widget.TextView r9 = (android.widget.TextView) r9
            r1 = 2131427721(0x7f0b0189, float:1.8477066E38)
            android.view.View r1 = r8.findViewById(r1)
            android.widget.TextView r1 = (android.widget.TextView) r1
            java.lang.String r2 = r7.f4389d
            r9.setText(r2)
            java.lang.String r2 = r7.f4390e
            int r3 = r7.f4394i
            r4 = 1
            r5 = 2
            if (r3 == r5) goto L33
            if (r3 != r4) goto L45
        L33:
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L45
            r3 = 80
            r9.setGravity(r3)
            r1.setVisibility(r0)
            r1.setText(r2)
            goto L54
        L45:
            r0 = 16
            r9.setGravity(r0)
            r9 = 8
            r1.setVisibility(r9)
            java.lang.String r9 = ""
            r1.setText(r9)
        L54:
            boolean r9 = r7.f4392g
            r8.setEnabled(r9)
            r9 = 2131427722(0x7f0b018a, float:1.8477068E38)
            android.view.View r9 = r8.findViewById(r9)
            android.widget.ImageView r9 = (android.widget.ImageView) r9
            if (r9 == 0) goto Lae
            android.net.Uri r0 = r7.f4391f
            if (r0 == 0) goto L90
            android.content.Context r1 = r6.getContext()     // Catch: java.io.IOException -> L7c
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch: java.io.IOException -> L7c
            java.io.InputStream r1 = r1.openInputStream(r0)     // Catch: java.io.IOException -> L7c
            r2 = 0
            android.graphics.drawable.Drawable r0 = android.graphics.drawable.Drawable.createFromStream(r1, r2)     // Catch: java.io.IOException -> L7c
            if (r0 == 0) goto L90
            goto Lab
        L7c:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Failed to load "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.String r2 = "MediaRouteChooserDialog"
            android.util.Log.w(r2, r0, r1)
        L90:
            int r0 = r7.f4398n
            if (r0 == r4) goto La8
            if (r0 == r5) goto La5
            java.util.ArrayList r7 = r7.f4406v
            boolean r7 = r7.isEmpty()
            if (r7 != 0) goto La2
            android.graphics.drawable.Drawable r7 = r6.f4281o
        La0:
            r0 = r7
            goto Lab
        La2:
            android.graphics.drawable.Drawable r7 = r6.f4278l
            goto La0
        La5:
            android.graphics.drawable.Drawable r7 = r6.f4280n
            goto La0
        La8:
            android.graphics.drawable.Drawable r7 = r6.f4279m
            goto La0
        Lab:
            r9.setImageDrawable(r0)
        Lae:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.e.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean isEnabled(int i10) {
        return ((i0) getItem(i10)).f4392g;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i10, long j10) {
        i0 i0Var = (i0) getItem(i10);
        ImageView imageView = (ImageView) view.findViewById(R.id.mr_chooser_route_icon);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.mr_chooser_route_progress_bar);
        if (imageView != null && progressBar != null) {
            imageView.setVisibility(8);
            progressBar.setVisibility(0);
        }
        i0Var.g(true);
    }
}
