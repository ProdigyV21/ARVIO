package io.ktor.serialization.kotlinx;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.utils.io.ByteReadChannel;
import k2.c;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.serialization.kotlinx.KotlinxSerializationConverter$deserialize$fromExtension$2", f = "KotlinxSerializationConverter.kt", l = {}, m = "invokeSuspend")
public final class KotlinxSerializationConverter$deserialize$fromExtension$2 extends j implements p<Object, d<? super Boolean>, Object> {
    final /* synthetic */ ByteReadChannel $content;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinxSerializationConverter$deserialize$fromExtension$2(ByteReadChannel byteReadChannel, d<? super KotlinxSerializationConverter$deserialize$fromExtension$2> dVar) {
        super(2, dVar);
        this.$content = byteReadChannel;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        KotlinxSerializationConverter$deserialize$fromExtension$2 kotlinxSerializationConverter$deserialize$fromExtension$2 = new KotlinxSerializationConverter$deserialize$fromExtension$2(this.$content, dVar);
        kotlinxSerializationConverter$deserialize$fromExtension$2.L$0 = obj;
        return kotlinxSerializationConverter$deserialize$fromExtension$2;
    }

    @Override // r7.p
    public final Object invoke(Object obj, d<? super Boolean> dVar) {
        return ((KotlinxSerializationConverter$deserialize$fromExtension$2) create(obj, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        c.G(obj);
        return Boolean.valueOf(this.L$0 != null || this.$content.isClosedForRead());
    }
}
