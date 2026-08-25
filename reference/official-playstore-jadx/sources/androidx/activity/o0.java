package androidx.activity;

import android.view.View;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes.dex */
public final class o0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final o0 f978l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final o0 f979m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final o0 f980n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final o0 f981o;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f982i;

    static {
        int i10 = 1;
        f978l = new o0(i10, 0);
        f979m = new o0(i10, 1);
        f980n = new o0(i10, 2);
        f981o = new o0(i10, 3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o0(int i10, int i11) {
        super(i10);
        this.f982i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f982i) {
            case 0:
                Object parent = ((View) obj).getParent();
                if (parent instanceof View) {
                    return (View) parent;
                }
                return null;
            case 1:
                Object tag = ((View) obj).getTag(R.id.report_drawn);
                if (tag instanceof x) {
                    return (x) tag;
                }
                return null;
            case 2:
                Object parent2 = ((View) obj).getParent();
                if (parent2 instanceof View) {
                    return (View) parent2;
                }
                return null;
            default:
                Object tag2 = ((View) obj).getTag(R.id.view_tree_on_back_pressed_dispatcher_owner);
                if (tag2 instanceof n0) {
                    return (n0) tag2;
                }
                return null;
        }
    }
}
