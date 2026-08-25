package io.ktor.client.request.forms;

import com.google.common.util.concurrent.r0;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.a;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0001\u0010\u0002\u001a\u001f\u0010\u0007\u001a\u00020\u0006*\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0004H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\"\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"", "generateBoundary", "()Ljava/lang/String;", "Lio/ktor/utils/io/core/Input;", "Lio/ktor/utils/io/ByteWriteChannel;", "channel", "Lx6/t0;", "copyTo", "(Lio/ktor/utils/io/core/Input;Lio/ktor/utils/io/ByteWriteChannel;Ld7/d;)Ljava/lang/Object;", "", "RN_BYTES", "[B", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormDataContentKt {
    private static final byte[] RN_BYTES;

    /* JADX INFO: renamed from: io.ktor.client.request.forms.FormDataContentKt$copyTo$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.request.forms.FormDataContentKt", f = "FormDataContent.kt", l = {162, 177, 184, 184}, m = "copyTo")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FormDataContentKt.copyTo(null, null, this);
        }
    }

    static {
        Charset charset = a.f19924a;
        RN_BYTES = p.a(charset, charset) ? "\r\n".getBytes(charset) : CharsetJVMKt.encodeToByteArray(charset.newEncoder(), "\r\n", 0, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Path cross not found for [B:10:0x002a, B:20:0x006f], limit reached: 54 */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ec A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ee A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00d5 -> B:27:0x0087). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object copyTo(io.ktor.utils.io.core.Input r18, io.ktor.utils.io.ByteWriteChannel r19, d7.d<? super x6.t0> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.request.forms.FormDataContentKt.copyTo(io.ktor.utils.io.core.Input, io.ktor.utils.io.ByteWriteChannel, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String generateBoundary() {
        StringBuilder sb2 = new StringBuilder();
        for (int i10 = 0; i10 < 32; i10++) {
            int iD = v7.d.f22178l.d();
            r0.c(16);
            sb2.append(Integer.toString(iD, 16));
        }
        return o.I0(70, sb2.toString());
    }
}
