package io.ktor.client.request.forms;

import io.ktor.utils.io.core.ByteReadPacket;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class FormDslKt$formData$1$part$7 extends r implements a<t0> {
    final /* synthetic */ Object $value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormDslKt$formData$1$part$7(Object obj) {
        super(0);
        this.$value = obj;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m6674invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m6674invoke() {
        ((ByteReadPacket) this.$value).close();
    }
}
