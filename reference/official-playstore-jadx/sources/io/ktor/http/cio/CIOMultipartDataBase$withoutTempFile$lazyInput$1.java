package io.ktor.http.cio;

import io.ktor.http.cio.MultipartEvent;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/cio/MultipartInput;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class CIOMultipartDataBase$withoutTempFile$lazyInput$1 extends r implements a<MultipartInput> {
    final /* synthetic */ ByteBuffer $buffer;
    final /* synthetic */ f0 $closed;
    final /* synthetic */ MultipartEvent.MultipartPart $part;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CIOMultipartDataBase$withoutTempFile$lazyInput$1(f0 f0Var, ByteBuffer byteBuffer, MultipartEvent.MultipartPart multipartPart) {
        super(0);
        this.$closed = f0Var;
        this.$buffer = byteBuffer;
        this.$part = multipartPart;
    }

    @Override // r7.a
    public final MultipartInput invoke() {
        if (this.$closed.f19738i) {
            throw new IllegalStateException("Already disposed");
        }
        return new MultipartInput(this.$buffer, this.$part.getBody());
    }
}
