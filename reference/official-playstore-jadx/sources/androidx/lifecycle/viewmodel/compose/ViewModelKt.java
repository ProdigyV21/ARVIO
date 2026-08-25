package androidx.lifecycle.viewmodel.compose;

import androidx.compose.runtime.Composer;
import androidx.lifecycle.d1;
import androidx.lifecycle.g1;
import androidx.lifecycle.h1;
import androidx.lifecycle.i1;
import androidx.lifecycle.j1;
import androidx.lifecycle.k;
import androidx.lifecycle.y0;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import o1.a;
import o1.c;
import r7.l;
import x6.e;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a>\u0010\b\u001a\u00028\u0000\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0087\b¢\u0006\u0004\b\b\u0010\t\u001aH\u0010\b\u001a\u00028\u0000\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0087\b¢\u0006\u0004\b\b\u0010\f\u001aI\u0010\b\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u00002\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0004\b\b\u0010\u000f\u001aS\u0010\b\u001a\u00028\u0000\"\b\b\u0000\u0010\u0001*\u00020\u00002\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\b\u0010\u0010\u001aM\u0010\b\u001a\u00028\u0000\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0019\b\b\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00028\u00000\u0011¢\u0006\u0002\b\u0012H\u0087\b¢\u0006\u0004\b\b\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/lifecycle/d1;", "VM", "Landroidx/lifecycle/j1;", "viewModelStoreOwner", "", "key", "Landroidx/lifecycle/g1;", "factory", "viewModel", "(Landroidx/lifecycle/j1;Ljava/lang/String;Landroidx/lifecycle/g1;Landroidx/compose/runtime/Composer;II)Landroidx/lifecycle/d1;", "Lo1/c;", "extras", "(Landroidx/lifecycle/j1;Ljava/lang/String;Landroidx/lifecycle/g1;Lo1/c;Landroidx/compose/runtime/Composer;II)Landroidx/lifecycle/d1;", "Ljava/lang/Class;", "modelClass", "(Ljava/lang/Class;Landroidx/lifecycle/j1;Ljava/lang/String;Landroidx/lifecycle/g1;Landroidx/compose/runtime/Composer;II)Landroidx/lifecycle/d1;", "(Ljava/lang/Class;Landroidx/lifecycle/j1;Ljava/lang/String;Landroidx/lifecycle/g1;Lo1/c;Landroidx/compose/runtime/Composer;II)Landroidx/lifecycle/d1;", "Lkotlin/Function1;", "Lx6/n;", "initializer", "(Landroidx/lifecycle/j1;Ljava/lang/String;Lr7/l;Landroidx/compose/runtime/Composer;II)Landroidx/lifecycle/d1;", "lifecycle-viewmodel-compose_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ViewModelKt {
    public static final d1 a(j1 j1Var, Class cls, String str, g1 g1Var, c cVar) {
        h1 h1Var;
        g1 defaultViewModelProviderFactory;
        if (g1Var != null) {
            h1Var = new h1(j1Var.getViewModelStore(), g1Var, cVar);
        } else if (j1Var instanceof k) {
            h1Var = new h1(j1Var.getViewModelStore(), ((k) j1Var).getDefaultViewModelProviderFactory(), cVar);
        } else {
            i1 viewModelStore = j1Var.getViewModelStore();
            boolean z = j1Var instanceof k;
            if (z) {
                defaultViewModelProviderFactory = ((k) j1Var).getDefaultViewModelProviderFactory();
            } else {
                if (y0.f3312b == null) {
                    y0.f3312b = new y0(1);
                }
                defaultViewModelProviderFactory = y0.f3312b;
            }
            h1Var = new h1(viewModelStore, defaultViewModelProviderFactory, z ? ((k) j1Var).getDefaultViewModelCreationExtras() : a.f20721b);
        }
        return str != null ? h1Var.b(cls, str) : h1Var.a(cls);
    }

    @e
    public static final <VM extends d1> VM viewModel(j1 j1Var, String str, g1 g1Var, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-384969861);
        if ((i11 & 1) != 0 && LocalViewModelStoreOwner.f3301a.getCurrent(composer, 6) == null) {
            throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
        }
        p.h();
        throw null;
    }

    public static final <VM extends d1> VM viewModel(j1 j1Var, String str, g1 g1Var, c cVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1729797275);
        if ((i11 & 1) != 0 && (j1Var = LocalViewModelStoreOwner.f3301a.getCurrent(composer, 6)) == null) {
            throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
        }
        if ((i11 & 8) != 0) {
            if (j1Var instanceof k) {
                ((k) j1Var).getDefaultViewModelCreationExtras();
            } else {
                a aVar = a.f20721b;
            }
        }
        p.h();
        throw null;
    }

    @e
    public static final /* synthetic */ d1 viewModel(Class cls, j1 j1Var, String str, g1 g1Var, Composer composer, int i10, int i11) {
        c defaultViewModelCreationExtras;
        composer.startReplaceableGroup(1324836815);
        if ((i11 & 2) != 0 && (j1Var = LocalViewModelStoreOwner.f3301a.getCurrent(composer, 6)) == null) {
            throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
        }
        if ((i11 & 4) != 0) {
            str = null;
        }
        if ((i11 & 8) != 0) {
            g1Var = null;
        }
        if (j1Var instanceof k) {
            defaultViewModelCreationExtras = ((k) j1Var).getDefaultViewModelCreationExtras();
        } else {
            defaultViewModelCreationExtras = a.f20721b;
        }
        d1 d1VarA = a(j1Var, cls, str, g1Var, defaultViewModelCreationExtras);
        composer.endReplaceableGroup();
        return d1VarA;
    }

    public static final <VM extends d1> VM viewModel(Class<VM> cls, j1 j1Var, String str, g1 g1Var, c cVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-1439476281);
        if ((i11 & 2) != 0 && (j1Var = LocalViewModelStoreOwner.f3301a.getCurrent(composer, 6)) == null) {
            throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
        }
        if ((i11 & 4) != 0) {
            str = null;
        }
        if ((i11 & 8) != 0) {
            g1Var = null;
        }
        if ((i11 & 16) != 0) {
            if (j1Var instanceof k) {
                cVar = ((k) j1Var).getDefaultViewModelCreationExtras();
            } else {
                cVar = a.f20721b;
            }
        }
        VM vm = (VM) a(j1Var, cls, str, g1Var, cVar);
        composer.endReplaceableGroup();
        return vm;
    }

    public static final <VM extends d1> VM viewModel(j1 j1Var, String str, l<? super c, ? extends VM> lVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(419377738);
        if ((i11 & 1) != 0 && LocalViewModelStoreOwner.f3301a.getCurrent(composer, 6) == null) {
            throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
        }
        p.h();
        throw null;
    }
}
