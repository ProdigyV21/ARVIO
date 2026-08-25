package com.arflix.tv.ui.screens.player;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.util.DataStoresKt;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$rememberPlayerClockFormat$1$1", f = "PlayerScreen.kt", l = {4681}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$rememberPlayerClockFormat$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<String> $resolvedFormat$delegate;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$rememberPlayerClockFormat$1$1(Context context, MutableState<String> mutableState, d7.d<? super PlayerScreenKt$rememberPlayerClockFormat$1$1> dVar) {
        super(2, dVar);
        this.$context = context;
        this.$resolvedFormat$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        PlayerScreenKt$rememberPlayerClockFormat$1$1 playerScreenKt$rememberPlayerClockFormat$1$1 = new PlayerScreenKt$rememberPlayerClockFormat$1$1(this.$context, this.$resolvedFormat$delegate, dVar);
        playerScreenKt$rememberPlayerClockFormat$1$1.L$0 = obj;
        return playerScreenKt$rememberPlayerClockFormat$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        MutableState<String> mutableState;
        Object next;
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                Context context = this.$context;
                mutableState = this.$resolvedFormat$delegate;
                na.j data = DataStoresKt.getSettingsDataStore(context).getData();
                this.L$0 = null;
                this.L$1 = mutableState;
                this.L$2 = null;
                this.I$0 = 0;
                this.label = 1;
                obj = na.y0.l(data, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutableState = (MutableState) this.L$1;
                k2.c.G(obj);
            }
            Iterator it = Collections.unmodifiableMap(((c1.b) ((c1.g) obj)).f7368a).entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (kotlin.text.u.K(((c1.e) ((Map.Entry) next).getKey()).f7375a, "_clock_format", false)) {
                    break;
                }
            }
            Map.Entry entry = (Map.Entry) next;
            Object value = entry != null ? entry.getValue() : null;
            String str = value instanceof String ? (String) value : null;
            if (str == null) {
                str = "24h";
            }
            mutableState.setValue(str);
        } catch (Throwable unused) {
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$rememberPlayerClockFormat$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
