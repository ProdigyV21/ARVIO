package io.ktor.util.cio;

import d7.d;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.ByteWriteChannelKt;
import io.ktor.utils.io.jvm.javaio.BlockingKt;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.text.a;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a)\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001b\u0010\t\u001a\u00020\b*\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\n\u001a\u001b\u0010\f\u001a\u00020\u000b*\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lio/ktor/utils/io/ByteWriteChannel;", "", "string", "Ljava/nio/charset/Charset;", HttpAuthHeader.Parameters.Charset, "Lx6/t0;", "write", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/lang/String;Ljava/nio/charset/Charset;Ld7/d;)Ljava/lang/Object;", "Ljava/io/BufferedWriter;", "bufferedWriter", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/nio/charset/Charset;)Ljava/io/BufferedWriter;", "Ljava/io/Writer;", "writer", "(Lio/ktor/utils/io/ByteWriteChannel;Ljava/nio/charset/Charset;)Ljava/io/Writer;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OutputStreamAdaptersKt {
    public static final BufferedWriter bufferedWriter(ByteWriteChannel byteWriteChannel, Charset charset) {
        return new BufferedWriter(new OutputStreamWriter(BlockingKt.toOutputStream$default(byteWriteChannel, null, 1, null), charset), 8192);
    }

    public static /* synthetic */ BufferedWriter bufferedWriter$default(ByteWriteChannel byteWriteChannel, Charset charset, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            charset = a.f19924a;
        }
        return bufferedWriter(byteWriteChannel, charset);
    }

    @e
    public static final Object write(ByteWriteChannel byteWriteChannel, String str, Charset charset, d<? super t0> dVar) {
        Object objWriteFully = ByteWriteChannelKt.writeFully(byteWriteChannel, str.getBytes(charset), dVar);
        return objWriteFully == e7.a.f15033i ? objWriteFully : t0.f22605a;
    }

    public static /* synthetic */ Object write$default(ByteWriteChannel byteWriteChannel, String str, Charset charset, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            charset = a.f19924a;
        }
        return write(byteWriteChannel, str, charset, dVar);
    }

    public static final Writer writer(ByteWriteChannel byteWriteChannel, Charset charset) {
        return new OutputStreamWriter(BlockingKt.toOutputStream$default(byteWriteChannel, null, 1, null), charset);
    }

    public static /* synthetic */ Writer writer$default(ByteWriteChannel byteWriteChannel, Charset charset, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            charset = a.f19924a;
        }
        return writer(byteWriteChannel, charset);
    }
}
