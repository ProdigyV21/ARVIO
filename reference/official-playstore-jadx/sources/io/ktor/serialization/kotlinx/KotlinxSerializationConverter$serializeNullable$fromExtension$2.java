package io.ktor.serialization.kotlinx;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.http.content.OutgoingContent;
import k2.c;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/http/content/OutgoingContent;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.serialization.kotlinx.KotlinxSerializationConverter$serializeNullable$fromExtension$2", f = "KotlinxSerializationConverter.kt", l = {}, m = "invokeSuspend")
public final class KotlinxSerializationConverter$serializeNullable$fromExtension$2 extends j implements p<OutgoingContent, d<? super Boolean>, Object> {
    /* synthetic */ Object L$0;
    int label;

    public KotlinxSerializationConverter$serializeNullable$fromExtension$2(d<? super KotlinxSerializationConverter$serializeNullable$fromExtension$2> dVar) {
        super(2, dVar);
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        KotlinxSerializationConverter$serializeNullable$fromExtension$2 kotlinxSerializationConverter$serializeNullable$fromExtension$2 = new KotlinxSerializationConverter$serializeNullable$fromExtension$2(dVar);
        kotlinxSerializationConverter$serializeNullable$fromExtension$2.L$0 = obj;
        return kotlinxSerializationConverter$serializeNullable$fromExtension$2;
    }

    @Override // r7.p
    public final Object invoke(OutgoingContent outgoingContent, d<? super Boolean> dVar) {
        return ((KotlinxSerializationConverter$serializeNullable$fromExtension$2) create(outgoingContent, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        c.G(obj);
        return Boolean.valueOf(((OutgoingContent) this.L$0) != null);
    }
}
