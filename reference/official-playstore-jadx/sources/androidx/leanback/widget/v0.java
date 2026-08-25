package androidx.leanback.widget;

import android.os.SystemClock;
import android.view.MotionEvent;

/* JADX INFO: loaded from: classes3.dex */
public final class v0 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3150i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SearchBar f3151l;

    public /* synthetic */ v0(SearchBar searchBar, int i10) {
        this.f3150i = i10;
        this.f3151l = searchBar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3150i) {
            case 0:
                SearchBar searchBar = this.f3151l;
                searchBar.setSearchQueryInternal(searchBar.f3004l.getText().toString());
                break;
            default:
                SearchBar searchBar2 = this.f3151l;
                searchBar2.f3004l.requestFocusFromTouch();
                searchBar2.f3004l.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, searchBar2.f3004l.getWidth(), searchBar2.f3004l.getHeight(), 0));
                searchBar2.f3004l.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, searchBar2.f3004l.getWidth(), searchBar2.f3004l.getHeight(), 0));
                break;
        }
    }
}
