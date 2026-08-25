package androidx.navigation;

import androidx.lifecycle.d1;
import androidx.lifecycle.g1;
import androidx.lifecycle.i1;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00132\u00020\u00012\u00020\u0002:\u0001\u0014B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H\u0014¢\u0006\u0004\b\n\u0010\u0004J\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u000e\u0010\u000fR \u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Landroidx/navigation/NavControllerViewModel;", "Landroidx/lifecycle/d1;", "Landroidx/navigation/x0;", "<init>", "()V", "", "backStackEntryId", "Lx6/t0;", "clear", "(Ljava/lang/String;)V", "onCleared", "Landroidx/lifecycle/i1;", "getViewModelStore", "(Ljava/lang/String;)Landroidx/lifecycle/i1;", "toString", "()Ljava/lang/String;", "", "viewModelStores", "Ljava/util/Map;", "Companion", "androidx/navigation/u", "navigation-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class NavControllerViewModel extends d1 implements x0 {
    public static final u Companion = new u();
    private static final g1 FACTORY = new t();
    private final Map<String, i1> viewModelStores = new LinkedHashMap();

    @q7.n
    public static final NavControllerViewModel getInstance(i1 i1Var) {
        Companion.getClass();
        return u.a(i1Var);
    }

    public final void clear(String backStackEntryId) {
        i1 i1VarRemove = this.viewModelStores.remove(backStackEntryId);
        if (i1VarRemove != null) {
            i1VarRemove.a();
        }
    }

    @Override // androidx.navigation.x0
    public i1 getViewModelStore(String backStackEntryId) {
        i1 i1Var = this.viewModelStores.get(backStackEntryId);
        if (i1Var != null) {
            return i1Var;
        }
        i1 i1Var2 = new i1();
        this.viewModelStores.put(backStackEntryId, i1Var2);
        return i1Var2;
    }

    @Override // androidx.lifecycle.d1
    public void onCleared() {
        Iterator<i1> it = this.viewModelStores.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.viewModelStores.clear();
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("NavControllerViewModel{");
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        sb2.append("} ViewModelStores (");
        Iterator<String> it = this.viewModelStores.keySet().iterator();
        while (it.hasNext()) {
            sb2.append(it.next());
            if (it.hasNext()) {
                sb2.append(", ");
            }
        }
        sb2.append(')');
        return sb2.toString();
    }
}
