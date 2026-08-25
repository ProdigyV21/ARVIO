package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.Window;
import androidx.appcompat.view.menu.d0;

/* JADX INFO: loaded from: classes.dex */
public interface y1 {
    boolean a();

    boolean b();

    boolean c();

    void collapseActionView();

    void d(Menu menu, d0.a aVar);

    boolean e();

    void f();

    boolean g();

    Context getContext();

    CharSequence getTitle();

    boolean h();

    void i(int i10);

    void j(CharSequence charSequence);

    androidx.core.view.n2 k(int i10, long j10);

    void l();

    void m(boolean z);

    void n();

    void o();

    void p(int i10);

    void q(int i10);

    void r(int i10);

    int s();

    void setIcon(int i10);

    void setIcon(Drawable drawable);

    void setTitle(CharSequence charSequence);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    void t();
}
