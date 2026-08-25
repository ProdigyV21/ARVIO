package androidx.lifecycle;

import android.view.View;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes3.dex */
public final class k1 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final k1 f3253l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final k1 f3254m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final k1 f3255n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final k1 f3256o;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3257i;

    static {
        int i10 = 1;
        f3253l = new k1(i10, 0);
        f3254m = new k1(i10, 1);
        f3255n = new k1(i10, 2);
        f3256o = new k1(i10, 3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k1(int i10, int i11) {
        super(i10);
        this.f3257i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f3257i) {
            case 0:
                Object parent = ((View) obj).getParent();
                if (parent instanceof View) {
                    return (View) parent;
                }
                return null;
            case 1:
                Object tag = ((View) obj).getTag(R.id.view_tree_lifecycle_owner);
                if (tag instanceof y) {
                    return (y) tag;
                }
                return null;
            case 2:
                Object parent2 = ((View) obj).getParent();
                if (parent2 instanceof View) {
                    return (View) parent2;
                }
                return null;
            default:
                Object tag2 = ((View) obj).getTag(R.id.view_tree_view_model_store_owner);
                if (tag2 instanceof j1) {
                    return (j1) tag2;
                }
                return null;
        }
    }
}
