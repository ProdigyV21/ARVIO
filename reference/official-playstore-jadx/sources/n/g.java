package n;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import androidx.appcompat.view.menu.f0;

/* JADX INFO: loaded from: classes.dex */
public final class g extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f20386a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f20387b;

    public g(Context context, b bVar) {
        this.f20386a = context;
        this.f20387b = bVar;
    }

    @Override // android.view.ActionMode
    public final void finish() {
        this.f20387b.a();
    }

    @Override // android.view.ActionMode
    public final View getCustomView() {
        return this.f20387b.c();
    }

    @Override // android.view.ActionMode
    public final Menu getMenu() {
        return new f0(this.f20386a, this.f20387b.d());
    }

    @Override // android.view.ActionMode
    public final MenuInflater getMenuInflater() {
        return this.f20387b.e();
    }

    @Override // android.view.ActionMode
    public final CharSequence getSubtitle() {
        return this.f20387b.g();
    }

    @Override // android.view.ActionMode
    public final Object getTag() {
        return this.f20387b.f20372i;
    }

    @Override // android.view.ActionMode
    public final CharSequence getTitle() {
        return this.f20387b.h();
    }

    @Override // android.view.ActionMode
    public final boolean getTitleOptionalHint() {
        return this.f20387b.f20373l;
    }

    @Override // android.view.ActionMode
    public final void invalidate() {
        this.f20387b.i();
    }

    @Override // android.view.ActionMode
    public final boolean isTitleOptional() {
        return this.f20387b.j();
    }

    @Override // android.view.ActionMode
    public final void setCustomView(View view) {
        this.f20387b.k(view);
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(CharSequence charSequence) {
        this.f20387b.m(charSequence);
    }

    @Override // android.view.ActionMode
    public final void setTag(Object obj) {
        this.f20387b.f20372i = obj;
    }

    @Override // android.view.ActionMode
    public final void setTitle(CharSequence charSequence) {
        this.f20387b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public final void setTitleOptionalHint(boolean z) {
        this.f20387b.p(z);
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(int i10) {
        this.f20387b.l(i10);
    }

    @Override // android.view.ActionMode
    public final void setTitle(int i10) {
        this.f20387b.n(i10);
    }
}
