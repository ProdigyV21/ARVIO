package androidx.hilt.navigation.compose;

import android.content.Context;
import android.content.ContextWrapper;
import androidx.activity.t;
import androidx.compose.runtime.Composer;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.d1;
import androidx.lifecycle.g1;
import androidx.lifecycle.j1;
import androidx.lifecycle.k;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import o1.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0006\u001a\u00028\u0000\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0087\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0019\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/lifecycle/d1;", "VM", "Landroidx/lifecycle/j1;", "viewModelStoreOwner", "", "key", "hiltViewModel", "(Landroidx/lifecycle/j1;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/lifecycle/d1;", "Landroidx/lifecycle/g1;", "createHiltViewModelFactory", "(Landroidx/lifecycle/j1;Landroidx/compose/runtime/Composer;I)Landroidx/lifecycle/g1;", "hilt-navigation-compose_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HiltViewModelKt {
    public static final g1 createHiltViewModelFactory(j1 j1Var, Composer composer, int i10) {
        composer.startReplaceableGroup(1770922558);
        g1 g1VarCreateInternal = null;
        if (j1Var instanceof k) {
            Context baseContext = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            g1 defaultViewModelProviderFactory = ((k) j1Var).getDefaultViewModelProviderFactory();
            while (baseContext instanceof ContextWrapper) {
                if (baseContext instanceof t) {
                    t tVar = (t) baseContext;
                    g1VarCreateInternal = HiltViewModelFactory.createInternal(tVar, tVar, null, defaultViewModelProviderFactory);
                } else {
                    baseContext = ((ContextWrapper) baseContext).getBaseContext();
                }
            }
            throw new IllegalStateException("Expected an activity context for creating a HiltViewModelFactory but instead found: " + baseContext);
        }
        composer.endReplaceableGroup();
        return g1VarCreateInternal;
    }

    public static final <VM extends d1> VM hiltViewModel(j1 j1Var, String str, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1890788296);
        if ((i11 & 1) != 0 && (j1Var = LocalViewModelStoreOwner.f3301a.getCurrent(composer, 0)) == null) {
            throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
        }
        createHiltViewModelFactory(j1Var, composer, 8);
        composer.startReplaceableGroup(1729797275);
        if (j1Var instanceof k) {
            ((k) j1Var).getDefaultViewModelCreationExtras();
        } else {
            a aVar = a.f20721b;
        }
        p.h();
        throw null;
    }
}
