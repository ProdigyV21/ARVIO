package androidx.fragment.app;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
final class FragmentManagerViewModel extends androidx.lifecycle.d1 {
    private static final androidx.lifecycle.g1 FACTORY = new f1();
    private static final String TAG = "FragmentManager";
    private final boolean mStateAutomaticallySaved;
    private final HashMap<String, c0> mRetainedFragments = new HashMap<>();
    private final HashMap<String, FragmentManagerViewModel> mChildNonConfigs = new HashMap<>();
    private final HashMap<String, androidx.lifecycle.i1> mViewModelStores = new HashMap<>();
    private boolean mHasBeenCleared = false;
    private boolean mHasSavedSnapshot = false;
    private boolean mIsStateSaved = false;

    public FragmentManagerViewModel(boolean z) {
        this.mStateAutomaticallySaved = z;
    }

    private void clearNonConfigStateInternal(String str) {
        FragmentManagerViewModel fragmentManagerViewModel = this.mChildNonConfigs.get(str);
        if (fragmentManagerViewModel != null) {
            fragmentManagerViewModel.onCleared();
            this.mChildNonConfigs.remove(str);
        }
        androidx.lifecycle.i1 i1Var = this.mViewModelStores.get(str);
        if (i1Var != null) {
            i1Var.a();
            this.mViewModelStores.remove(str);
        }
    }

    public static FragmentManagerViewModel getInstance(androidx.lifecycle.i1 i1Var) {
        return (FragmentManagerViewModel) new androidx.lifecycle.h1(i1Var, FACTORY, 0).a(FragmentManagerViewModel.class);
    }

    public void addRetainedFragment(c0 c0Var) {
        if (this.mIsStateSaved) {
            if (b1.G(2)) {
                Log.v(TAG, "Ignoring addRetainedFragment as the state is already saved");
            }
        } else {
            if (this.mRetainedFragments.containsKey(c0Var.mWho)) {
                return;
            }
            this.mRetainedFragments.put(c0Var.mWho, c0Var);
            if (b1.G(2)) {
                Log.v(TAG, "Updating retained Fragments: Added " + c0Var);
            }
        }
    }

    public void clearNonConfigState(c0 c0Var) {
        if (b1.G(3)) {
            Log.d(TAG, "Clearing non-config state for " + c0Var);
        }
        clearNonConfigStateInternal(c0Var.mWho);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && FragmentManagerViewModel.class == obj.getClass()) {
            FragmentManagerViewModel fragmentManagerViewModel = (FragmentManagerViewModel) obj;
            if (this.mRetainedFragments.equals(fragmentManagerViewModel.mRetainedFragments) && this.mChildNonConfigs.equals(fragmentManagerViewModel.mChildNonConfigs) && this.mViewModelStores.equals(fragmentManagerViewModel.mViewModelStores)) {
                return true;
            }
        }
        return false;
    }

    public c0 findRetainedFragmentByWho(String str) {
        return this.mRetainedFragments.get(str);
    }

    public FragmentManagerViewModel getChildNonConfig(c0 c0Var) {
        FragmentManagerViewModel fragmentManagerViewModel = this.mChildNonConfigs.get(c0Var.mWho);
        if (fragmentManagerViewModel != null) {
            return fragmentManagerViewModel;
        }
        FragmentManagerViewModel fragmentManagerViewModel2 = new FragmentManagerViewModel(this.mStateAutomaticallySaved);
        this.mChildNonConfigs.put(c0Var.mWho, fragmentManagerViewModel2);
        return fragmentManagerViewModel2;
    }

    public Collection<c0> getRetainedFragments() {
        return new ArrayList(this.mRetainedFragments.values());
    }

    @Deprecated
    public d1 getSnapshot() {
        if (this.mRetainedFragments.isEmpty() && this.mChildNonConfigs.isEmpty() && this.mViewModelStores.isEmpty()) {
            return null;
        }
        HashMap map = new HashMap();
        for (Map.Entry<String, FragmentManagerViewModel> entry : this.mChildNonConfigs.entrySet()) {
            d1 snapshot = entry.getValue().getSnapshot();
            if (snapshot != null) {
                map.put(entry.getKey(), snapshot);
            }
        }
        this.mHasSavedSnapshot = true;
        if (this.mRetainedFragments.isEmpty() && map.isEmpty() && this.mViewModelStores.isEmpty()) {
            return null;
        }
        return new d1(new ArrayList(this.mRetainedFragments.values()), map, new HashMap(this.mViewModelStores));
    }

    public androidx.lifecycle.i1 getViewModelStore(c0 c0Var) {
        androidx.lifecycle.i1 i1Var = this.mViewModelStores.get(c0Var.mWho);
        if (i1Var != null) {
            return i1Var;
        }
        androidx.lifecycle.i1 i1Var2 = new androidx.lifecycle.i1();
        this.mViewModelStores.put(c0Var.mWho, i1Var2);
        return i1Var2;
    }

    public int hashCode() {
        return this.mViewModelStores.hashCode() + ((this.mChildNonConfigs.hashCode() + (this.mRetainedFragments.hashCode() * 31)) * 31);
    }

    public boolean isCleared() {
        return this.mHasBeenCleared;
    }

    @Override // androidx.lifecycle.d1
    public void onCleared() {
        if (b1.G(3)) {
            Log.d(TAG, "onCleared called for " + this);
        }
        this.mHasBeenCleared = true;
    }

    public void removeRetainedFragment(c0 c0Var) {
        if (this.mIsStateSaved) {
            if (b1.G(2)) {
                Log.v(TAG, "Ignoring removeRetainedFragment as the state is already saved");
            }
        } else {
            if (this.mRetainedFragments.remove(c0Var.mWho) == null || !b1.G(2)) {
                return;
            }
            Log.v(TAG, "Updating retained Fragments: Removed " + c0Var);
        }
    }

    @Deprecated
    public void restoreFromSnapshot(d1 d1Var) {
        this.mRetainedFragments.clear();
        this.mChildNonConfigs.clear();
        this.mViewModelStores.clear();
        if (d1Var != null) {
            for (c0 c0Var : d1Var.f2707a) {
                if (c0Var != null) {
                    this.mRetainedFragments.put(c0Var.mWho, c0Var);
                }
            }
            for (Map.Entry entry : d1Var.f2708b.entrySet()) {
                FragmentManagerViewModel fragmentManagerViewModel = new FragmentManagerViewModel(this.mStateAutomaticallySaved);
                fragmentManagerViewModel.restoreFromSnapshot((d1) entry.getValue());
                this.mChildNonConfigs.put((String) entry.getKey(), fragmentManagerViewModel);
            }
            this.mViewModelStores.putAll(d1Var.f2709c);
        }
        this.mHasSavedSnapshot = false;
    }

    public void setIsStateSaved(boolean z) {
        this.mIsStateSaved = z;
    }

    public boolean shouldDestroy(c0 c0Var) {
        if (this.mRetainedFragments.containsKey(c0Var.mWho)) {
            return this.mStateAutomaticallySaved ? this.mHasBeenCleared : !this.mHasSavedSnapshot;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("FragmentManagerViewModel{");
        sb2.append(Integer.toHexString(System.identityHashCode(this)));
        sb2.append("} Fragments (");
        Iterator<c0> it = this.mRetainedFragments.values().iterator();
        while (it.hasNext()) {
            sb2.append(it.next());
            if (it.hasNext()) {
                sb2.append(", ");
            }
        }
        sb2.append(") Child Non Config (");
        Iterator<String> it2 = this.mChildNonConfigs.keySet().iterator();
        while (it2.hasNext()) {
            sb2.append(it2.next());
            if (it2.hasNext()) {
                sb2.append(", ");
            }
        }
        sb2.append(") ViewModelStores (");
        Iterator<String> it3 = this.mViewModelStores.keySet().iterator();
        while (it3.hasNext()) {
            sb2.append(it3.next());
            if (it3.hasNext()) {
                sb2.append(", ");
            }
        }
        sb2.append(')');
        return sb2.toString();
    }

    public void clearNonConfigState(String str) {
        if (b1.G(3)) {
            Log.d(TAG, "Clearing non-config state for saved state of Fragment " + str);
        }
        clearNonConfigStateInternal(str);
    }
}
