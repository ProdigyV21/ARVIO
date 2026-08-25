package io.ktor.client.request.forms;

import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/core/Input;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class FormDslKt$formData$1$part$6 extends r implements a<Input> {
    final /* synthetic */ Object $value;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FormDslKt$formData$1$part$6(Object obj) {
        super(0);
        this.$value = obj;
    }

    @Override // r7.a
    public final Input invoke() {
        return ((ByteReadPacket) this.$value).copy();
    }
}
