package androidx.navigation;

import android.app.Activity;
import android.content.Context;
import java.util.Iterator;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@z0("activity")
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/navigation/c;", "Landroidx/navigation/a1;", "Landroidx/navigation/a;", "navigation-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class c extends a1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Activity f4530c;

    public c(Context context) {
        Object next;
        Iterator it = ga.r.M(context, b.f4521l).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((Context) next) instanceof Activity) {
                    break;
                }
            }
        }
        this.f4530c = (Activity) next;
    }

    @Override // androidx.navigation.a1
    public final d0 a() {
        return new a(this);
    }

    @Override // androidx.navigation.a1
    public final d0 c(d0 d0Var) {
        throw new IllegalStateException(androidx.compose.material3.d.j(((a) d0Var).f4662p, " does not have an Intent set.", new StringBuilder("Destination ")).toString());
    }

    @Override // androidx.navigation.a1
    public final boolean f() {
        Activity activity = this.f4530c;
        if (activity == null) {
            return false;
        }
        activity.finish();
        return true;
    }
}
