package androidx.appcompat.widget;

import android.R;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class b4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f1514a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextView f1515b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ImageView f1516c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ImageView f1517d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ImageView f1518e;

    public b4(View view) {
        this.f1514a = (TextView) view.findViewById(R.id.text1);
        this.f1515b = (TextView) view.findViewById(R.id.text2);
        this.f1516c = (ImageView) view.findViewById(R.id.icon1);
        this.f1517d = (ImageView) view.findViewById(R.id.icon2);
        this.f1518e = (ImageView) view.findViewById(com.arvio.tv.R.id.edit_query);
    }
}
