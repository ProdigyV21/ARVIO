package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpec;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "", "velocity", "Lx6/t0;", "<anonymous>", "(Lka/k0;F)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.AppBarKt$BottomAppBar$appBarDragModifier$2$1", f = "AppBar.kt", l = {594}, m = "invokeSuspend")
public final class AppBarKt$BottomAppBar$appBarDragModifier$2$1 extends j implements q<k0, Float, d7.d<? super t0>, Object> {
    final /* synthetic */ BottomAppBarScrollBehavior $scrollBehavior;
    /* synthetic */ float F$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppBarKt$BottomAppBar$appBarDragModifier$2$1(BottomAppBarScrollBehavior bottomAppBarScrollBehavior, d7.d<? super AppBarKt$BottomAppBar$appBarDragModifier$2$1> dVar) {
        super(3, dVar);
        this.$scrollBehavior = bottomAppBarScrollBehavior;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((k0) obj, ((Number) obj2).floatValue(), (d7.d<? super t0>) obj3);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            float f10 = this.F$0;
            BottomAppBarState state = this.$scrollBehavior.getState();
            DecayAnimationSpec<Float> flingAnimationSpec = this.$scrollBehavior.getFlingAnimationSpec();
            AnimationSpec<Float> snapAnimationSpec = this.$scrollBehavior.getSnapAnimationSpec();
            this.label = 1;
            Object obj2 = AppBarKt.settleAppBarBottom(state, f10, flingAnimationSpec, snapAnimationSpec, this);
            e7.a aVar = e7.a.f15033i;
            if (obj2 == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0.f22605a;
    }

    public final Object invoke(k0 k0Var, float f10, d7.d<? super t0> dVar) {
        AppBarKt$BottomAppBar$appBarDragModifier$2$1 appBarKt$BottomAppBar$appBarDragModifier$2$1 = new AppBarKt$BottomAppBar$appBarDragModifier$2$1(this.$scrollBehavior, dVar);
        appBarKt$BottomAppBar$appBarDragModifier$2$1.F$0 = f10;
        return appBarKt$BottomAppBar$appBarDragModifier$2$1.invokeSuspend(t0.f22605a);
    }
}
