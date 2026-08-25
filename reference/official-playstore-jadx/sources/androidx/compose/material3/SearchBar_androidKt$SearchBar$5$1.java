package androidx.compose.material3;

import androidx.compose.ui.focus.FocusManager;
import f7.j;
import ka.k0;
import ka.s0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.SearchBar_androidKt$SearchBar$5$1", f = "SearchBar.android.kt", l = {291}, m = "invokeSuspend")
public final class SearchBar_androidKt$SearchBar$5$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ FocusManager $focusManager;
    final /* synthetic */ boolean $shouldClearFocus;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchBar_androidKt$SearchBar$5$1(boolean z, FocusManager focusManager, d7.d<? super SearchBar_androidKt$SearchBar$5$1> dVar) {
        super(2, dVar);
        this.$shouldClearFocus = z;
        this.$focusManager = focusManager;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SearchBar_androidKt$SearchBar$5$1(this.$shouldClearFocus, this.$focusManager, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            if (this.$shouldClearFocus) {
                this.label = 1;
                Object objA = s0.a(100L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            }
            return t0.f22605a;
        }
        if (i10 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        androidx.compose.ui.focus.b.a(this.$focusManager, false, 1, null);
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((SearchBar_androidKt$SearchBar$5$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
