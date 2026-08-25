package com.arflix.tv.ui.components;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.StreamSource;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.StreamSelectorKt$StreamSelector$8$1", f = "StreamSelector.kt", l = {}, m = "invokeSuspend", v = 2)
public final class StreamSelectorKt$StreamSelector$8$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ List<StreamSource> $flatStreams;
    final /* synthetic */ MutableState<String> $focusZone$delegate;
    final /* synthetic */ MutableIntState $focusedIndex$delegate;
    final /* synthetic */ boolean $isVisible;
    final /* synthetic */ r7.l<StreamSource, x6.t0> $onFocusedStream;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public StreamSelectorKt$StreamSelector$8$1(boolean z, List<StreamSource> list, r7.l<? super StreamSource, x6.t0> lVar, MutableState<String> mutableState, MutableIntState mutableIntState, d7.d<? super StreamSelectorKt$StreamSelector$8$1> dVar) {
        super(2, dVar);
        this.$isVisible = z;
        this.$flatStreams = list;
        this.$onFocusedStream = lVar;
        this.$focusZone$delegate = mutableState;
        this.$focusedIndex$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new StreamSelectorKt$StreamSelector$8$1(this.$isVisible, this.$flatStreams, this.$onFocusedStream, this.$focusZone$delegate, this.$focusedIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (this.$isVisible && kotlin.jvm.internal.p.a(StreamSelectorKt.StreamSelector$lambda$19(this.$focusZone$delegate), "streams")) {
            StreamSource streamSource = (StreamSource) kotlin.collections.x.p0(this.$focusedIndex$delegate.getIntValue(), this.$flatStreams);
            if (streamSource != null) {
                this.$onFocusedStream.invoke(streamSource);
            }
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((StreamSelectorKt$StreamSelector$8$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
