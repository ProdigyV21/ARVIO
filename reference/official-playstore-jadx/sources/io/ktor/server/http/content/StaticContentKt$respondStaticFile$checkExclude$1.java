package io.ktor.server.http.content;

import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.server.http.content.StaticContentKt", f = "StaticContent.kt", l = {548}, m = "respondStaticFile$checkExclude")
public final class StaticContentKt$respondStaticFile$checkExclude$1 extends c {
    int label;
    /* synthetic */ Object result;

    public StaticContentKt$respondStaticFile$checkExclude$1(d<? super StaticContentKt$respondStaticFile$checkExclude$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return StaticContentKt.respondStaticFile$checkExclude(null, null, null, this);
    }
}
