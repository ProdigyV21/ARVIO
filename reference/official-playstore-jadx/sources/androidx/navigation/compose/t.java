package androidx.navigation.compose;

import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.runtime.State;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class t extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Map f4602i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ f f4603l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ r7.l f4604m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.l f4605n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ State f4606o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(Map map, f fVar, r7.l lVar, r7.l lVar2, State state) {
        super(1);
        this.f4602i = map;
        this.f4603l = fVar;
        this.f4604m = lVar;
        this.f4605n = lVar2;
        this.f4606o = state;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // r7.l
    public final Object invoke(Object obj) {
        float fFloatValue;
        AnimatedContentTransitionScope animatedContentTransitionScope = (AnimatedContentTransitionScope) obj;
        if (!((List) this.f4606o.getValue()).contains(animatedContentTransitionScope.getInitialState())) {
            return AnimatedContentKt.togetherWith(EnterTransition.INSTANCE.getNone(), ExitTransition.INSTANCE.getNone());
        }
        String str = ((androidx.navigation.i) animatedContentTransitionScope.getInitialState()).f4685p;
        Map map = this.f4602i;
        Float f10 = (Float) map.get(str);
        if (f10 != null) {
            fFloatValue = f10.floatValue();
        } else {
            map.put(((androidx.navigation.i) animatedContentTransitionScope.getInitialState()).f4685p, Float.valueOf(0.0f));
            fFloatValue = 0.0f;
        }
        if (!kotlin.jvm.internal.p.a(((androidx.navigation.i) animatedContentTransitionScope.getTargetState()).f4685p, ((androidx.navigation.i) animatedContentTransitionScope.getInitialState()).f4685p)) {
            fFloatValue = ((Boolean) this.f4603l.f4563c.getValue()).booleanValue() ? fFloatValue - 1.0f : fFloatValue + 1.0f;
        }
        float f11 = fFloatValue;
        map.put(((androidx.navigation.i) animatedContentTransitionScope.getTargetState()).f4685p, Float.valueOf(f11));
        return new ContentTransform((EnterTransition) this.f4604m.invoke(animatedContentTransitionScope), (ExitTransition) this.f4605n.invoke(animatedContentTransitionScope), f11, null, 8, null);
    }
}
