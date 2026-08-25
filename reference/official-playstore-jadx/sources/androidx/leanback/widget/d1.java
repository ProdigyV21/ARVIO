package androidx.leanback.widget;

import androidx.leanback.widget.SearchEditText;

/* JADX INFO: loaded from: classes3.dex */
public final class d1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ SearchEditText f3078i;

    public d1(SearchEditText searchEditText) {
        this.f3078i = searchEditText;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SearchEditText.a aVar = this.f3078i.f3017r;
        if (aVar != null) {
            aVar.a();
        }
    }
}
