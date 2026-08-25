package io.ktor.utils.io.jvm.javaio;

import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.utils.io.jvm.javaio.OutputAdapter$loop$1", f = "Blocking.kt", l = {319, 94}, m = "loop")
public final class OutputAdapter$loop$1$loop$1 extends c {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OutputAdapter$loop$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OutputAdapter$loop$1$loop$1(OutputAdapter$loop$1 outputAdapter$loop$1, d<? super OutputAdapter$loop$1$loop$1> dVar) {
        super(dVar);
        this.this$0 = outputAdapter$loop$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.loop(this);
    }
}
