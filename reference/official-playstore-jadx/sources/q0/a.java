package q0;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final class a extends ClickableSpan {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f21303i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final k f21304l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f21305m;

    public a(int i10, k kVar, int i11) {
        this.f21303i = i10;
        this.f21304l = kVar;
        this.f21305m = i11;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f21303i);
        this.f21304l.f21319a.performAction(this.f21305m, bundle);
    }
}
