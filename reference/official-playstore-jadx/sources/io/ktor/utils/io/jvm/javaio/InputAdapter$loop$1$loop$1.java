package io.ktor.utils.io.jvm.javaio;

import androidx.media3.extractor.flac.FlacConstants;
import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.utils.io.jvm.javaio.InputAdapter$loop$1", f = "Blocking.kt", l = {319, FlacConstants.STREAM_INFO_BLOCK_SIZE}, m = "loop")
public final class InputAdapter$loop$1$loop$1 extends c {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ InputAdapter$loop$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InputAdapter$loop$1$loop$1(InputAdapter$loop$1 inputAdapter$loop$1, d<? super InputAdapter$loop$1$loop$1> dVar) {
        super(dVar);
        this.this$0 = inputAdapter$loop$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.loop(this);
    }
}
