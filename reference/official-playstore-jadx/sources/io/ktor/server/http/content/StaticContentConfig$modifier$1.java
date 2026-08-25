package io.ktor.server.http.content;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.server.application.ApplicationCall;
import k2.c;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: Add missing generic type declarations: [Resource] */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0003H\u008a@"}, d2 = {"", "Resource", "<anonymous parameter 0>", "Lio/ktor/server/application/ApplicationCall;", "<anonymous parameter 1>", "Lx6/t0;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.server.http.content.StaticContentConfig$modifier$1", f = "StaticContent.kt", l = {}, m = "invokeSuspend")
public final class StaticContentConfig$modifier$1<Resource> extends j implements q<Resource, ApplicationCall, d<? super t0>, Object> {
    int label;

    public StaticContentConfig$modifier$1(d<? super StaticContentConfig$modifier$1> dVar) {
        super(3, dVar);
    }

    @Override // r7.q
    public final Object invoke(Resource resource, ApplicationCall applicationCall, d<? super t0> dVar) {
        return new StaticContentConfig$modifier$1(dVar).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        c.G(obj);
        return t0.f22605a;
    }
}
