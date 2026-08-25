package androidx.core.view;

import android.text.TextUtils;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
public final class f1 extends h1 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f2232o;

    public f1(int i10, Class cls, int i11, int i12, int i13) {
        this.f2232o = i13;
        this.f2238i = i10;
        this.f2241n = cls;
        this.f2240m = i11;
        this.f2239l = i12;
    }

    @Override // androidx.core.view.h1
    public final Object c(View view) {
        switch (this.f2232o) {
            case 0:
                return Boolean.valueOf(o1.d(view));
            case 1:
                return o1.b(view);
            default:
                return Boolean.valueOf(o1.c(view));
        }
    }

    @Override // androidx.core.view.h1
    public final void d(View view, Object obj) {
        switch (this.f2232o) {
            case 0:
                o1.j(view, ((Boolean) obj).booleanValue());
                break;
            case 1:
                o1.h(view, (CharSequence) obj);
                break;
            default:
                o1.g(view, ((Boolean) obj).booleanValue());
                break;
        }
    }

    @Override // androidx.core.view.h1
    public final boolean g(Object obj, Object obj2) {
        switch (this.f2232o) {
            case 0:
                Boolean bool = (Boolean) obj;
                Boolean bool2 = (Boolean) obj2;
                return !((bool != null && bool.booleanValue()) == (bool2 != null && bool2.booleanValue()));
            case 1:
                return !TextUtils.equals((CharSequence) obj, (CharSequence) obj2);
            default:
                Boolean bool3 = (Boolean) obj;
                Boolean bool4 = (Boolean) obj2;
                return !((bool3 != null && bool3.booleanValue()) == (bool4 != null && bool4.booleanValue()));
        }
    }
}
