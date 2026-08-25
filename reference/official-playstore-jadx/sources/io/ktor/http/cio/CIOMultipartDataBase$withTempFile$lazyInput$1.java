package io.ktor.http.cio;

import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.streams.InputKt;
import java.io.File;
import java.io.FileInputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/utils/io/core/Input;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class CIOMultipartDataBase$withTempFile$lazyInput$1 extends r implements a<Input> {
    final /* synthetic */ f0 $closed;
    final /* synthetic */ File $tmp;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CIOMultipartDataBase$withTempFile$lazyInput$1(f0 f0Var, File file) {
        super(0);
        this.$closed = f0Var;
        this.$tmp = file;
    }

    @Override // r7.a
    public final Input invoke() {
        if (this.$closed.f19738i) {
            throw new IllegalStateException("Already disposed");
        }
        return InputKt.asInput$default(new FileInputStream(this.$tmp), null, 1, null);
    }
}
