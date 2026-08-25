package io.ktor.client.request.forms;

import androidx.fragment.app.a2;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import f7.c;
import f7.e;
import io.ktor.client.request.forms.PreparedPart;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.PartData;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.Output;
import io.ktor.utils.io.core.OutputKt;
import io.ktor.utils.io.core.StringsKt;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.text.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\b\u001a\u00020\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u0018R\u0014\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001cR\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R(\u0010#\u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010!8\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, d2 = {"Lio/ktor/client/request/forms/MultiPartFormDataContent;", "Lio/ktor/http/content/OutgoingContent$WriteChannelContent;", "", "Lio/ktor/http/content/PartData;", "parts", "", "boundary", "Lio/ktor/http/ContentType;", "contentType", "<init>", "(Ljava/util/List;Ljava/lang/String;Lio/ktor/http/ContentType;)V", "Lio/ktor/utils/io/ByteWriteChannel;", "channel", "Lx6/t0;", "writeTo", "(Lio/ktor/utils/io/ByteWriteChannel;Ld7/d;)Ljava/lang/Object;", "Ljava/lang/String;", "getBoundary", "()Ljava/lang/String;", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "", "BOUNDARY_BYTES", "[B", "LAST_BOUNDARY_BYTES", "", "BODY_OVERHEAD_SIZE", "I", "PART_OVERHEAD_SIZE", "Lio/ktor/client/request/forms/PreparedPart;", "rawParts", "Ljava/util/List;", "", "<set-?>", "contentLength", "Ljava/lang/Long;", "getContentLength", "()Ljava/lang/Long;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MultiPartFormDataContent extends OutgoingContent.WriteChannelContent {
    private final int BODY_OVERHEAD_SIZE;
    private final byte[] BOUNDARY_BYTES;
    private final byte[] LAST_BOUNDARY_BYTES;
    private final int PART_OVERHEAD_SIZE;
    private final String boundary;
    private Long contentLength;
    private final ContentType contentType;
    private final List<PreparedPart> rawParts;

    /* JADX INFO: renamed from: io.ktor.client.request.forms.MultiPartFormDataContent$writeTo$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.request.forms.MultiPartFormDataContent", f = "FormDataContent.kt", l = {AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, 117, 118, 123, 127, 131, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO}, m = "writeTo")
    public static final class AnonymousClass1 extends c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MultiPartFormDataContent.this.writeTo(null, this);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ MultiPartFormDataContent(List list, String str, ContentType contentType, int i10, h hVar) {
        str = (i10 & 2) != 0 ? FormDataContentKt.generateBoundary() : str;
        this(list, str, (i10 & 4) != 0 ? ContentType.MultiPart.INSTANCE.getFormData().withParameter("boundary", str) : contentType);
    }

    public final String getBoundary() {
        return this.boundary;
    }

    @Override // io.ktor.http.content.OutgoingContent
    public Long getContentLength() {
        return this.contentLength;
    }

    @Override // io.ktor.http.content.OutgoingContent
    public ContentType getContentType() {
        return this.contentType;
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x0141, code lost:
    
        if (r10 == r3) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01a6, code lost:
    
        if (r10 == r3) goto L85;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ce A[Catch: all -> 0x0033, TryCatch #0 {all -> 0x0033, blocks: (B:13:0x002e, B:44:0x00c8, B:46:0x00ce, B:50:0x00eb, B:53:0x0102, B:79:0x017e, B:83:0x0197, B:43:0x00c1), top: B:94:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0102 A[Catch: all -> 0x0033, PHI: r1 r4 r5 r9
      0x0102: PHI (r1v19 java.util.Iterator<io.ktor.client.request.forms.PreparedPart>) = 
      (r1v5 java.util.Iterator<io.ktor.client.request.forms.PreparedPart>)
      (r1v20 java.util.Iterator<io.ktor.client.request.forms.PreparedPart>)
     binds: [B:38:0x00a3, B:51:0x00fe] A[DONT_GENERATE, DONT_INLINE]
      0x0102: PHI (r4v21 io.ktor.client.request.forms.PreparedPart) = (r4v6 io.ktor.client.request.forms.PreparedPart), (r4v23 io.ktor.client.request.forms.PreparedPart) binds: [B:38:0x00a3, B:51:0x00fe] A[DONT_GENERATE, DONT_INLINE]
      0x0102: PHI (r5v12 io.ktor.client.request.forms.MultiPartFormDataContent) = 
      (r5v3 io.ktor.client.request.forms.MultiPartFormDataContent)
      (r5v13 io.ktor.client.request.forms.MultiPartFormDataContent)
     binds: [B:38:0x00a3, B:51:0x00fe] A[DONT_GENERATE, DONT_INLINE]
      0x0102: PHI (r9v37 ??) = (r9v11 ??), (r9v55 ??) binds: [B:38:0x00a3, B:51:0x00fe] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #0 {all -> 0x0033, blocks: (B:13:0x002e, B:44:0x00c8, B:46:0x00ce, B:50:0x00eb, B:53:0x0102, B:79:0x017e, B:83:0x0197, B:43:0x00c1), top: B:94:0x001f }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0120 A[Catch: all -> 0x008c, TRY_LEAVE, TryCatch #5 {all -> 0x008c, blocks: (B:57:0x011c, B:59:0x0120, B:63:0x0145, B:72:0x0156, B:74:0x015a, B:71:0x0155, B:32:0x0087, B:37:0x00a0, B:40:0x00b7, B:69:0x0153, B:68:0x0150, B:65:0x014b, B:60:0x012c, B:27:0x006f), top: B:94:0x001f, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0156 A[Catch: all -> 0x008c, TryCatch #5 {all -> 0x008c, blocks: (B:57:0x011c, B:59:0x0120, B:63:0x0145, B:72:0x0156, B:74:0x015a, B:71:0x0155, B:32:0x0087, B:37:0x00a0, B:40:0x00b7, B:69:0x0153, B:68:0x0150, B:65:0x014b, B:60:0x012c, B:27:0x006f), top: B:94:0x001f, inners: #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0197 A[Catch: all -> 0x0033, TRY_LEAVE, TryCatch #0 {all -> 0x0033, blocks: (B:13:0x002e, B:44:0x00c8, B:46:0x00ce, B:50:0x00eb, B:53:0x0102, B:79:0x017e, B:83:0x0197, B:43:0x00c1), top: B:94:0x001f }] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v10, types: [io.ktor.utils.io.ByteWriteChannel] */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v19, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r4v32 */
    /* JADX WARN: Type inference failed for: r4v33 */
    /* JADX WARN: Type inference failed for: r4v34 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r9v0, types: [io.ktor.utils.io.ByteWriteChannel] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v23, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v3, types: [io.ktor.utils.io.ByteWriteChannel] */
    /* JADX WARN: Type inference failed for: r9v37, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v39, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v4, types: [io.ktor.utils.io.ByteWriteChannel] */
    /* JADX WARN: Type inference failed for: r9v40, types: [io.ktor.utils.io.ByteWriteChannel, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v41 */
    /* JADX WARN: Type inference failed for: r9v44 */
    /* JADX WARN: Type inference failed for: r9v48 */
    /* JADX WARN: Type inference failed for: r9v49 */
    /* JADX WARN: Type inference failed for: r9v50 */
    /* JADX WARN: Type inference failed for: r9v51 */
    /* JADX WARN: Type inference failed for: r9v52 */
    /* JADX WARN: Type inference failed for: r9v53 */
    /* JADX WARN: Type inference failed for: r9v54 */
    /* JADX WARN: Type inference failed for: r9v55 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:82:0x0194 -> B:20:0x0047). Please report as a decompilation issue!!! */
    @Override // io.ktor.http.content.OutgoingContent.WriteChannelContent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object writeTo(io.ktor.utils.io.ByteWriteChannel r9, d7.d<? super x6.t0> r10) {
        /*
            Method dump skipped, instruction units count: 462
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.request.forms.MultiPartFormDataContent.writeTo(io.ktor.utils.io.ByteWriteChannel, d7.d):java.lang.Object");
    }

    public MultiPartFormDataContent(List<? extends PartData> list, String str, ContentType contentType) {
        byte[] bArrEncodeToByteArray;
        byte[] bArrEncodeToByteArray2;
        PreparedPart channelPart;
        this.boundary = str;
        this.contentType = contentType;
        String strL = a0.c.l("--", str, "\r\n");
        Charset charset = a.f19924a;
        if (p.a(charset, charset)) {
            bArrEncodeToByteArray = strL.getBytes(charset);
        } else {
            bArrEncodeToByteArray = CharsetJVMKt.encodeToByteArray(charset.newEncoder(), strL, 0, strL.length());
        }
        this.BOUNDARY_BYTES = bArrEncodeToByteArray;
        String strL2 = a0.c.l("--", str, "--\r\n");
        if (p.a(charset, charset)) {
            bArrEncodeToByteArray2 = strL2.getBytes(charset);
        } else {
            bArrEncodeToByteArray2 = CharsetJVMKt.encodeToByteArray(charset.newEncoder(), strL2, 0, strL2.length());
        }
        this.LAST_BOUNDARY_BYTES = bArrEncodeToByteArray2;
        this.BODY_OVERHEAD_SIZE = bArrEncodeToByteArray2.length;
        this.PART_OVERHEAD_SIZE = (FormDataContentKt.RN_BYTES.length * 2) + bArrEncodeToByteArray.length;
        ArrayList arrayList = new ArrayList(s.U(list, 10));
        Iterator<T> it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                PartData partData = (PartData) it.next();
                BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
                for (Map.Entry<String, List<String>> entry : partData.getHeaders().entries()) {
                    String key = entry.getKey();
                    List<String> value = entry.getValue();
                    StringBuilder sbP = a2.p(key, ": ");
                    sbP.append(x.u0(value, "; ", null, null, null, 62));
                    StringsKt.writeText$default(bytePacketBuilder, sbP.toString(), 0, 0, (Charset) null, 14, (Object) null);
                    OutputKt.writeFully$default((Output) bytePacketBuilder, FormDataContentKt.RN_BYTES, 0, 0, 6, (Object) null);
                }
                Headers headers = partData.getHeaders();
                HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
                String str2 = headers.get(httpHeaders.getContentLength());
                Long lValueOf = str2 != null ? Long.valueOf(Long.parseLong(str2)) : null;
                if (partData instanceof PartData.FileItem) {
                    byte[] bytes$default = StringsKt.readBytes$default(bytePacketBuilder.build(), 0, 1, null);
                    channelPart = new PreparedPart.InputPart(bytes$default, ((PartData.FileItem) partData).getProvider(), lValueOf != null ? Long.valueOf(lValueOf.longValue() + ((long) this.PART_OVERHEAD_SIZE) + ((long) bytes$default.length)) : null);
                } else if (partData instanceof PartData.BinaryItem) {
                    byte[] bytes$default2 = StringsKt.readBytes$default(bytePacketBuilder.build(), 0, 1, null);
                    channelPart = new PreparedPart.InputPart(bytes$default2, ((PartData.BinaryItem) partData).getProvider(), lValueOf != null ? Long.valueOf(lValueOf.longValue() + ((long) this.PART_OVERHEAD_SIZE) + ((long) bytes$default2.length)) : null);
                } else if (partData instanceof PartData.FormItem) {
                    BytePacketBuilder bytePacketBuilder2 = new BytePacketBuilder(null, 1, null);
                    try {
                        StringsKt.writeText$default(bytePacketBuilder2, ((PartData.FormItem) partData).getValue(), 0, 0, (Charset) null, 14, (Object) null);
                        byte[] bytes$default3 = StringsKt.readBytes$default(bytePacketBuilder2.build(), 0, 1, null);
                        MultiPartFormDataContent$rawParts$1$provider$1 multiPartFormDataContent$rawParts$1$provider$1 = new MultiPartFormDataContent$rawParts$1$provider$1(bytes$default3);
                        if (lValueOf == null) {
                            StringsKt.writeText$default(bytePacketBuilder, httpHeaders.getContentLength() + ": " + bytes$default3.length, 0, 0, (Charset) null, 14, (Object) null);
                            OutputKt.writeFully$default((Output) bytePacketBuilder, FormDataContentKt.RN_BYTES, 0, 0, 6, (Object) null);
                        }
                        channelPart = new PreparedPart.InputPart(StringsKt.readBytes$default(bytePacketBuilder.build(), 0, 1, null), multiPartFormDataContent$rawParts$1$provider$1, Long.valueOf(bytes$default3.length + this.PART_OVERHEAD_SIZE + r4.length));
                    } catch (Throwable th) {
                        bytePacketBuilder2.release();
                        throw th;
                    }
                } else if (partData instanceof PartData.BinaryChannelItem) {
                    byte[] bytes$default4 = StringsKt.readBytes$default(bytePacketBuilder.build(), 0, 1, null);
                    channelPart = new PreparedPart.ChannelPart(bytes$default4, ((PartData.BinaryChannelItem) partData).getProvider(), lValueOf != null ? Long.valueOf(lValueOf.longValue() + ((long) this.PART_OVERHEAD_SIZE) + ((long) bytes$default4.length)) : null);
                } else {
                    throw new NoWhenBranchMatchedException();
                }
                arrayList.add(channelPart);
            } else {
                this.rawParts = arrayList;
                Long lValueOf2 = 0L;
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        l = lValueOf2;
                        break;
                    }
                    Long size = ((PreparedPart) it2.next()).getSize();
                    if (size == null) {
                        break;
                    } else {
                        lValueOf2 = lValueOf2 != null ? Long.valueOf(size.longValue() + lValueOf2.longValue()) : null;
                    }
                }
                this.contentLength = l != null ? Long.valueOf(l.longValue() + ((long) this.BODY_OVERHEAD_SIZE)) : l;
                return;
            }
        }
    }
}
