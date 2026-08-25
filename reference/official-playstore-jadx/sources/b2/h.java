package b2;

import android.view.View;
import com.arvio.tv.R;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends r implements l {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final h f7123l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final h f7124m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7125i;

    static {
        int i10 = 1;
        f7123l = new h(i10, 0);
        f7124m = new h(i10, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(int i10, int i11) {
        super(i10);
        this.f7125i = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7125i) {
            case 0:
                Object parent = ((View) obj).getParent();
                if (parent instanceof View) {
                    return (View) parent;
                }
                return null;
            default:
                Object tag = ((View) obj).getTag(R.id.view_tree_saved_state_registry_owner);
                if (tag instanceof g) {
                    return (g) tag;
                }
                return null;
        }
    }
}
