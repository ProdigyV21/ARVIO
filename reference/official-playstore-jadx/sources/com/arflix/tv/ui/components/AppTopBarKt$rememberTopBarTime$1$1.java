package com.arflix.tv.ui.components;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.util.DataStoresKt;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.AppTopBarKt$rememberTopBarTime$1$1", f = "AppTopBar.kt", l = {392}, m = "invokeSuspend", v = 2)
public final class AppTopBarKt$rememberTopBarTime$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ String $clockFormat;
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<String> $resolvedFormat$delegate;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppTopBarKt$rememberTopBarTime$1$1(Context context, String str, MutableState<String> mutableState, d7.d<? super AppTopBarKt$rememberTopBarTime$1$1> dVar) {
        super(2, dVar);
        this.$context = context;
        this.$clockFormat = str;
        this.$resolvedFormat$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        AppTopBarKt$rememberTopBarTime$1$1 appTopBarKt$rememberTopBarTime$1$1 = new AppTopBarKt$rememberTopBarTime$1$1(this.$context, this.$clockFormat, this.$resolvedFormat$delegate, dVar);
        appTopBarKt$rememberTopBarTime$1$1.L$0 = obj;
        return appTopBarKt$rememberTopBarTime$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String str;
        MutableState<String> mutableState;
        Object next;
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                Context context = this.$context;
                String str2 = this.$clockFormat;
                MutableState<String> mutableState2 = this.$resolvedFormat$delegate;
                na.j data = DataStoresKt.getSettingsDataStore(context).getData();
                this.L$0 = null;
                this.L$1 = str2;
                this.L$2 = mutableState2;
                this.L$3 = null;
                this.I$0 = 0;
                this.label = 1;
                obj = na.y0.l(data, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
                str = str2;
                mutableState = mutableState2;
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutableState = (MutableState) this.L$2;
                str = (String) this.L$1;
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
            String str3 = value instanceof String ? (String) value : null;
            if (str3 != null) {
                str = str3;
            }
            mutableState.setValue(str);
        } catch (Throwable unused) {
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((AppTopBarKt$rememberTopBarTime$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
