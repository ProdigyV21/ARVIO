package io.ktor.server.engine;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import f7.e;
import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.LinkHeader;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.http.LinkHeaderKt;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.ApplicationResponsePropertiesKt;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.server.response.ResponseCookies;
import io.ktor.server.response.ResponseHeaders;
import io.ktor.server.response.ResponsePushBuilder;
import io.ktor.server.response.UseHttp2Push;
import io.ktor.util.AttributeKey;
import io.ktor.util.internal.ExceptionUtilsJvmKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import java.util.Iterator;
import java.util.List;
import ka.y;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import r7.p;
import x6.i0;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u0000 I2\u00020\u0001:\u0005JKILMB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\r\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\r\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0011H\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u001b\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0011H\u0094@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0017H\u0094@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u001aH\u0094@ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u001b\u0010\u001f\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001dH\u0094@ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 J\u001b\u0010#\u001a\u00020\t2\u0006\u0010\"\u001a\u00020!H\u0094@ø\u0001\u0000¢\u0006\u0004\b#\u0010$J\u001b\u0010'\u001a\u00020\t2\u0006\u0010&\u001a\u00020%H¤@ø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u0013\u0010*\u001a\u00020)H¤@ø\u0001\u0000¢\u0006\u0004\b*\u0010+J\u0017\u0010-\u001a\u00020\t2\u0006\u0010,\u001a\u00020\fH$¢\u0006\u0004\b-\u0010\u0010J\u0017\u00100\u001a\u00020\t2\u0006\u0010/\u001a\u00020.H\u0017¢\u0006\u0004\b0\u00101R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00102\u001a\u0004\b3\u00104R\u0018\u00105\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u00106R$\u00109\u001a\u0002072\u0006\u00108\u001a\u0002078\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b9\u0010;R\u001b\u0010A\u001a\u00020<8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R\u0016\u0010B\u001a\u0002078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bB\u0010:R\u0017\u0010D\u001a\u00020C8\u0006¢\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bF\u0010GR\u0014\u0010H\u001a\u0002078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bH\u0010;\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006N"}, d2 = {"Lio/ktor/server/engine/BaseApplicationResponse;", "Lio/ktor/server/response/ApplicationResponse;", "Lio/ktor/server/application/ApplicationCall;", "call", "<init>", "(Lio/ktor/server/application/ApplicationCall;)V", "", "expected", "actual", "Lx6/t0;", "ensureLength", "(JJ)V", "Lio/ktor/http/HttpStatusCode;", "status", "()Lio/ktor/http/HttpStatusCode;", "value", "(Lio/ktor/http/HttpStatusCode;)V", "Lio/ktor/http/content/OutgoingContent;", "content", "commitHeaders", "(Lio/ktor/http/content/OutgoingContent;)V", "respondOutgoingContent", "(Lio/ktor/http/content/OutgoingContent;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/content/OutgoingContent$NoContent;", "respondNoContent", "(Lio/ktor/http/content/OutgoingContent$NoContent;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/content/OutgoingContent$WriteChannelContent;", "respondWriteChannelContent", "(Lio/ktor/http/content/OutgoingContent$WriteChannelContent;Ld7/d;)Ljava/lang/Object;", "", "bytes", "respondFromBytes", "([BLd7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteReadChannel;", "readChannel", "respondFromChannel", "(Lio/ktor/utils/io/ByteReadChannel;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/http/content/OutgoingContent$ProtocolUpgrade;", "upgrade", "respondUpgrade", "(Lio/ktor/http/content/OutgoingContent$ProtocolUpgrade;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteWriteChannel;", "responseChannel", "(Ld7/d;)Ljava/lang/Object;", "statusCode", "setStatus", "Lio/ktor/server/response/ResponsePushBuilder;", "builder", "push", "(Lio/ktor/server/response/ResponsePushBuilder;)V", "Lio/ktor/server/application/ApplicationCall;", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "_status", "Lio/ktor/http/HttpStatusCode;", "", "<set-?>", "isSent", "Z", "()Z", "Lio/ktor/server/response/ResponseCookies;", "cookies$delegate", "Lx6/s;", "getCookies", "()Lio/ktor/server/response/ResponseCookies;", "cookies", "responded", "Lio/ktor/server/response/ApplicationSendPipeline;", "pipeline", "Lio/ktor/server/response/ApplicationSendPipeline;", "getPipeline", "()Lio/ktor/server/response/ApplicationSendPipeline;", "isCommitted", "Companion", "BodyLengthIsTooLong", "BodyLengthIsTooSmall", "InvalidHeaderForContent", "ResponseAlreadySentException", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class BaseApplicationResponse implements ApplicationResponse {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<BaseApplicationResponse> EngineResponseAttributeKey = new AttributeKey<>("EngineResponse");
    private HttpStatusCode _status;
    private final ApplicationCall call;

    /* JADX INFO: renamed from: cookies$delegate, reason: from kotlin metadata */
    private final s cookies = new i0(new BaseApplicationResponse$cookies$2(this));
    private boolean isSent;
    private final ApplicationSendPipeline pipeline;
    private boolean responded;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\n¨\u0006\u000b"}, d2 = {"Lio/ktor/server/engine/BaseApplicationResponse$BodyLengthIsTooLong;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "Lka/y;", "", "expected", "<init>", "(J)V", "createCopy", "()Lio/ktor/server/engine/BaseApplicationResponse$BodyLengthIsTooLong;", "J", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class BodyLengthIsTooLong extends IllegalStateException implements y<BodyLengthIsTooLong> {
        private final long expected;

        public BodyLengthIsTooLong(long j10) {
            super(c.q(j10, "Body.size is too long. Expected "));
            this.expected = j10;
        }

        @Override // ka.y
        public BodyLengthIsTooLong createCopy() {
            BodyLengthIsTooLong bodyLengthIsTooLong = new BodyLengthIsTooLong(this.expected);
            ExceptionUtilsJvmKt.initCauseBridge(bodyLengthIsTooLong, this);
            return bodyLengthIsTooLong;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\b\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000bR\u0014\u0010\u0006\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/server/engine/BaseApplicationResponse$BodyLengthIsTooSmall;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "Lka/y;", "", "expected", "actual", "<init>", "(JJ)V", "createCopy", "()Lio/ktor/server/engine/BaseApplicationResponse$BodyLengthIsTooSmall;", "J", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class BodyLengthIsTooSmall extends IllegalStateException implements y<BodyLengthIsTooSmall> {
        private final long actual;
        private final long expected;

        /* JADX WARN: Illegal instructions before constructor call */
        public BodyLengthIsTooSmall(long j10, long j11) {
            StringBuilder sbR = d.r(j11, "Body.size is too small. Body: ", ", Content-Length: ");
            sbR.append(j10);
            super(sbR.toString());
            this.expected = j10;
            this.actual = j11;
        }

        @Override // ka.y
        public BodyLengthIsTooSmall createCopy() {
            BodyLengthIsTooSmall bodyLengthIsTooSmall = new BodyLengthIsTooSmall(this.expected, this.actual);
            ExceptionUtilsJvmKt.initCauseBridge(bodyLengthIsTooSmall, this);
            return bodyLengthIsTooSmall;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/server/engine/BaseApplicationResponse$Companion;", "", "<init>", "()V", "Lio/ktor/server/response/ApplicationSendPipeline;", "sendPipeline", "Lx6/t0;", "setupSendPipeline", "(Lio/ktor/server/response/ApplicationSendPipeline;)V", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/engine/BaseApplicationResponse;", "EngineResponseAttributeKey", "Lio/ktor/util/AttributeKey;", "getEngineResponseAttributeKey", "()Lio/ktor/util/AttributeKey;", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final AttributeKey<BaseApplicationResponse> getEngineResponseAttributeKey() {
            return BaseApplicationResponse.EngineResponseAttributeKey;
        }

        public final void setupSendPipeline(ApplicationSendPipeline sendPipeline) {
            sendPipeline.intercept(ApplicationSendPipeline.INSTANCE.getEngine(), new BaseApplicationResponse$Companion$setupSendPipeline$1(null));
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0000H\u0016¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000bR\u0014\u0010\u0006\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/server/engine/BaseApplicationResponse$InvalidHeaderForContent;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "Lka/y;", "", ContentDisposition.Parameters.Name, "content", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "createCopy", "()Lio/ktor/server/engine/BaseApplicationResponse$InvalidHeaderForContent;", "Ljava/lang/String;", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class InvalidHeaderForContent extends IllegalStateException implements y<InvalidHeaderForContent> {
        private final String content;
        private final String name;

        public InvalidHeaderForContent(String str, String str2) {
            super(d.n("Header ", str, " is not allowed for ", str2));
            this.name = str;
            this.content = str2;
        }

        @Override // ka.y
        public InvalidHeaderForContent createCopy() {
            InvalidHeaderForContent invalidHeaderForContent = new InvalidHeaderForContent(this.name, this.content);
            ExceptionUtilsJvmKt.initCauseBridge(invalidHeaderForContent, this);
            return invalidHeaderForContent;
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lio/ktor/server/engine/BaseApplicationResponse$ResponseAlreadySentException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "()V", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ResponseAlreadySentException extends IllegalStateException {
        public ResponseAlreadySentException() {
            super("Response has already been sent");
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.BaseApplicationResponse$commitHeaders$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"", ContentDisposition.Parameters.Name, "", "values", "Lx6/t0;", "invoke", "(Ljava/lang/String;Ljava/util/List;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements p<String, List<? extends String>, t0> {
        final /* synthetic */ OutgoingContent $content;
        final /* synthetic */ f0 $transferEncodingSet;
        final /* synthetic */ BaseApplicationResponse this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(f0 f0Var, OutgoingContent outgoingContent, BaseApplicationResponse baseApplicationResponse) {
            super(2);
            this.$transferEncodingSet = f0Var;
            this.$content = outgoingContent;
            this.this$0 = baseApplicationResponse;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((String) obj, (List<String>) obj2);
            return t0.f22605a;
        }

        public final void invoke(String str, List<String> list) {
            HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
            if (str.equals(httpHeaders.getTransferEncoding())) {
                this.$transferEncodingSet.f19738i = true;
            } else if (str.equals(httpHeaders.getUpgrade())) {
                if (!(this.$content instanceof OutgoingContent.ProtocolUpgrade)) {
                    throw new InvalidHeaderForContent(httpHeaders.getUpgrade(), "non-upgrading response");
                }
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    this.this$0.getHeaders().append(str, it.next(), false);
                }
                return;
            }
            Iterator<String> it2 = list.iterator();
            while (it2.hasNext()) {
                ResponseHeaders.append$default(this.this$0.getHeaders(), str, it2.next(), false, 4, null);
            }
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.BaseApplicationResponse$respondFromBytes$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.engine.BaseApplicationResponse", f = "BaseApplicationResponse.kt", l = {197, 198}, m = "respondFromBytes$suspendImpl")
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BaseApplicationResponse.respondFromBytes$suspendImpl(BaseApplicationResponse.this, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.BaseApplicationResponse$respondFromChannel$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.engine.BaseApplicationResponse", f = "BaseApplicationResponse.kt", l = {208, 210, 215}, m = "respondFromChannel$suspendImpl")
    public static final class C18761 extends f7.c {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C18761(d7.d<? super C18761> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BaseApplicationResponse.respondFromChannel$suspendImpl(BaseApplicationResponse.this, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.BaseApplicationResponse$respondOutgoingContent$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.engine.BaseApplicationResponse", f = "BaseApplicationResponse.kt", l = {115, 124, 132, 142, 151}, m = "respondOutgoingContent$suspendImpl")
    public static final class C18771 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C18771(d7.d<? super C18771> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BaseApplicationResponse.respondOutgoingContent$suspendImpl(BaseApplicationResponse.this, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.engine.BaseApplicationResponse$respondWriteChannelContent$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.engine.BaseApplicationResponse", f = "BaseApplicationResponse.kt", l = {169, 173}, m = "respondWriteChannelContent$suspendImpl")
    public static final class C18781 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C18781(d7.d<? super C18781> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BaseApplicationResponse.respondWriteChannelContent$suspendImpl(BaseApplicationResponse.this, null, this);
        }
    }

    public BaseApplicationResponse(ApplicationCall applicationCall) {
        this.call = applicationCall;
        ApplicationSendPipeline applicationSendPipeline = new ApplicationSendPipeline(applicationCall.getApplication().getEnvironment().getDevelopmentMode());
        applicationSendPipeline.resetFrom(applicationCall.getApplication().getSendPipeline());
        this.pipeline = applicationSendPipeline;
    }

    private final void ensureLength(long expected, long actual) {
        if (expected < actual) {
            throw new BodyLengthIsTooLong(expected);
        }
        if (expected > actual) {
            throw new BodyLengthIsTooSmall(expected, actual);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x007b, code lost:
    
        if (ka.m0.y(r11, r1, r0) == r4) goto L29;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r9v0, types: [io.ktor.server.engine.BaseApplicationResponse, io.ktor.server.response.ApplicationResponse] */
    /* JADX WARN: Type inference failed for: r9v1, types: [io.ktor.utils.io.ByteWriteChannel] */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.lang.Object, x6.t0] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object respondFromBytes$suspendImpl(io.ktor.server.engine.BaseApplicationResponse r9, byte[] r10, d7.d<? super x6.t0> r11) {
        /*
            boolean r0 = r11 instanceof io.ktor.server.engine.BaseApplicationResponse.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r11
            io.ktor.server.engine.BaseApplicationResponse$respondFromBytes$1 r0 = (io.ktor.server.engine.BaseApplicationResponse.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.engine.BaseApplicationResponse$respondFromBytes$1 r0 = new io.ktor.server.engine.BaseApplicationResponse$respondFromBytes$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L41
            if (r1 == r3) goto L38
            if (r1 != r2) goto L30
            java.lang.Object r9 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r9 = (io.ktor.utils.io.ByteWriteChannel) r9
            k2.c.G(r11)     // Catch: java.lang.Throwable -> L2e
            goto L7e
        L2e:
            r10 = move-exception
            goto L84
        L30:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L38:
            java.lang.Object r9 = r0.L$0
            r10 = r9
            byte[] r10 = (byte[]) r10
            k2.c.G(r11)
            goto L68
        L41:
            k2.c.G(r11)
            io.ktor.server.response.ResponseHeaders r11 = r9.getHeaders()
            io.ktor.http.HttpHeaders r1 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r1 = r1.getContentLength()
            java.lang.String r11 = r11.get(r1)
            if (r11 == 0) goto L5d
            long r5 = java.lang.Long.parseLong(r11)
            int r11 = r10.length
            long r7 = (long) r11
            r9.ensureLength(r5, r7)
        L5d:
            r0.L$0 = r10
            r0.label = r3
            java.lang.Object r11 = r9.responseChannel(r0)
            if (r11 != r4) goto L68
            goto L7d
        L68:
            r9 = r11
            io.ktor.utils.io.ByteWriteChannel r9 = (io.ktor.utils.io.ByteWriteChannel) r9
            ka.v2 r11 = ka.x0.f19654c     // Catch: java.lang.Throwable -> L2e
            io.ktor.server.engine.BaseApplicationResponse$respondFromBytes$3$1 r1 = new io.ktor.server.engine.BaseApplicationResponse$respondFromBytes$3$1     // Catch: java.lang.Throwable -> L2e
            r3 = 0
            r1.<init>(r9, r10, r3)     // Catch: java.lang.Throwable -> L2e
            r0.L$0 = r9     // Catch: java.lang.Throwable -> L2e
            r0.label = r2     // Catch: java.lang.Throwable -> L2e
            java.lang.Object r10 = ka.m0.y(r11, r1, r0)     // Catch: java.lang.Throwable -> L2e
            if (r10 != r4) goto L7e
        L7d:
            return r4
        L7e:
            io.ktor.utils.io.ByteWriteChannelKt.close(r9)
            x6.t0 r9 = x6.t0.f22605a
            return r9
        L84:
            r9.close(r10)     // Catch: java.lang.Throwable -> L88
            throw r10     // Catch: java.lang.Throwable -> L88
        L88:
            r10 = move-exception
            io.ktor.utils.io.ByteWriteChannelKt.close(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.BaseApplicationResponse.respondFromBytes$suspendImpl(io.ktor.server.engine.BaseApplicationResponse, byte[], d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00c6 A[Catch: all -> 0x00ee, TRY_LEAVE, TryCatch #4 {all -> 0x00ee, blocks: (B:41:0x00be, B:43:0x00c6), top: B:66:0x00be }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object respondFromChannel$suspendImpl(io.ktor.server.engine.BaseApplicationResponse r10, io.ktor.utils.io.ByteReadChannel r11, d7.d<? super x6.t0> r12) {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.BaseApplicationResponse.respondFromChannel$suspendImpl(io.ktor.server.engine.BaseApplicationResponse, io.ktor.utils.io.ByteReadChannel, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object respondNoContent$suspendImpl(BaseApplicationResponse baseApplicationResponse, OutgoingContent.NoContent noContent, d7.d<? super t0> dVar) {
        return t0.f22605a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0065, code lost:
    
        if (r7.respondUpgrade((io.ktor.http.content.OutgoingContent.ProtocolUpgrade) r8, r0) == r1) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007f, code lost:
    
        if (r7.respondFromBytes(r9, r0) == r1) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0093, code lost:
    
        if (r7.respondWriteChannelContent((io.ktor.http.content.OutgoingContent.WriteChannelContent) r8, r0) == r1) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00cf, code lost:
    
        if (r7.respondNoContent((io.ktor.http.content.OutgoingContent.NoContent) r8, r0) == r1) goto L55;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object respondOutgoingContent$suspendImpl(io.ktor.server.engine.BaseApplicationResponse r7, io.ktor.http.content.OutgoingContent r8, d7.d<? super x6.t0> r9) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.BaseApplicationResponse.respondOutgoingContent$suspendImpl(io.ktor.server.engine.BaseApplicationResponse, io.ktor.http.content.OutgoingContent, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0068, code lost:
    
        if (ka.m0.y(r8, r1, r0) == r5) goto L28;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r6v0, types: [io.ktor.server.engine.BaseApplicationResponse] */
    /* JADX WARN: Type inference failed for: r6v1, types: [io.ktor.utils.io.ByteWriteChannel] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.Object, x6.t0] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ java.lang.Object respondWriteChannelContent$suspendImpl(io.ktor.server.engine.BaseApplicationResponse r6, io.ktor.http.content.OutgoingContent.WriteChannelContent r7, d7.d<? super x6.t0> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.server.engine.BaseApplicationResponse.C18781
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.server.engine.BaseApplicationResponse$respondWriteChannelContent$1 r0 = (io.ktor.server.engine.BaseApplicationResponse.C18781) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.engine.BaseApplicationResponse$respondWriteChannelContent$1 r0 = new io.ktor.server.engine.BaseApplicationResponse$respondWriteChannelContent$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 0
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L44
            if (r1 == r4) goto L3b
            if (r1 != r2) goto L33
            java.lang.Object r6 = r0.L$0
            io.ktor.utils.io.ByteWriteChannel r6 = (io.ktor.utils.io.ByteWriteChannel) r6
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L2f io.ktor.utils.io.ClosedWriteChannelException -> L31
            goto L6b
        L2f:
            r7 = move-exception
            goto L77
        L31:
            r7 = move-exception
            goto L71
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            java.lang.Object r6 = r0.L$0
            r7 = r6
            io.ktor.http.content.OutgoingContent$WriteChannelContent r7 = (io.ktor.http.content.OutgoingContent.WriteChannelContent) r7
            k2.c.G(r8)
            goto L52
        L44:
            k2.c.G(r8)
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r8 = r6.responseChannel(r0)
            if (r8 != r5) goto L52
            goto L6a
        L52:
            r6 = r8
            io.ktor.utils.io.ByteWriteChannel r6 = (io.ktor.utils.io.ByteWriteChannel) r6
            ka.x0 r8 = ka.x0.f19652a     // Catch: java.lang.Throwable -> L2f io.ktor.utils.io.ClosedWriteChannelException -> L31
            ka.f0 r8 = io.ktor.server.engine.internal.ApplicationUtilsJvmKt.getIOBridge(r8)     // Catch: java.lang.Throwable -> L2f io.ktor.utils.io.ClosedWriteChannelException -> L31
            io.ktor.server.engine.BaseApplicationResponse$respondWriteChannelContent$2$1 r1 = new io.ktor.server.engine.BaseApplicationResponse$respondWriteChannelContent$2$1     // Catch: java.lang.Throwable -> L2f io.ktor.utils.io.ClosedWriteChannelException -> L31
            r1.<init>(r7, r6, r3)     // Catch: java.lang.Throwable -> L2f io.ktor.utils.io.ClosedWriteChannelException -> L31
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L2f io.ktor.utils.io.ClosedWriteChannelException -> L31
            r0.label = r2     // Catch: java.lang.Throwable -> L2f io.ktor.utils.io.ClosedWriteChannelException -> L31
            java.lang.Object r7 = ka.m0.y(r8, r1, r0)     // Catch: java.lang.Throwable -> L2f io.ktor.utils.io.ClosedWriteChannelException -> L31
            if (r7 != r5) goto L6b
        L6a:
            return r5
        L6b:
            io.ktor.utils.io.ByteWriteChannelKt.close(r6)
            x6.t0 r6 = x6.t0.f22605a
            return r6
        L71:
            io.ktor.util.cio.ChannelWriteException r8 = new io.ktor.util.cio.ChannelWriteException     // Catch: java.lang.Throwable -> L2f
            r8.<init>(r3, r7, r4, r3)     // Catch: java.lang.Throwable -> L2f
            throw r8     // Catch: java.lang.Throwable -> L2f
        L77:
            r6.close(r7)     // Catch: java.lang.Throwable -> L7b
            throw r7     // Catch: java.lang.Throwable -> L7b
        L7b:
            r7 = move-exception
            io.ktor.utils.io.ByteWriteChannelKt.close(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.BaseApplicationResponse.respondWriteChannelContent$suspendImpl(io.ktor.server.engine.BaseApplicationResponse, io.ktor.http.content.OutgoingContent$WriteChannelContent, d7.d):java.lang.Object");
    }

    public final void commitHeaders(OutgoingContent content) {
        if (this.responded) {
            throw new ResponseAlreadySentException();
        }
        this.responded = true;
        f0 f0Var = new f0();
        HttpStatusCode status = content.getStatus();
        if (status != null) {
            status(status);
        } else if (get_status() == null) {
            status = HttpStatusCode.INSTANCE.getOK();
            status(status);
        }
        content.getHeaders().forEach(new AnonymousClass2(f0Var, content, this));
        Long contentLength = content.getContentLength();
        if (contentLength != null) {
            getHeaders().append(HttpHeaders.INSTANCE.getContentLength(), LongKt.toStringFast(contentLength.longValue()), false);
        } else if (!f0Var.f19738i && !(content instanceof OutgoingContent.ProtocolUpgrade)) {
            if (content instanceof OutgoingContent.NoContent) {
                getHeaders().append(HttpHeaders.INSTANCE.getContentLength(), "0", false);
            } else {
                getHeaders().append(HttpHeaders.INSTANCE.getTransferEncoding(), "chunked", false);
            }
        }
        ContentType contentType = content.getContentType();
        if (contentType != null) {
            getHeaders().append(HttpHeaders.INSTANCE.getContentType(), contentType.toString(), false);
        }
        Headers headers = this.call.getRequest().getHeaders();
        HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
        String str = headers.get(httpHeaders.getConnection());
        if (str == null || this.call.getResponse().getHeaders().contains(httpHeaders.getConnection())) {
            return;
        }
        if (str.equalsIgnoreCase("close")) {
            ApplicationResponsePropertiesKt.header(this, "Connection", "close");
        } else if (str.equalsIgnoreCase("keep-alive")) {
            ApplicationResponsePropertiesKt.header(this, "Connection", "keep-alive");
        }
    }

    @Override // io.ktor.server.response.ApplicationResponse
    public final ApplicationCall getCall() {
        return this.call;
    }

    @Override // io.ktor.server.response.ApplicationResponse
    public ResponseCookies getCookies() {
        return (ResponseCookies) this.cookies.getValue();
    }

    @Override // io.ktor.server.response.ApplicationResponse
    public final ApplicationSendPipeline getPipeline() {
        return this.pipeline;
    }

    @Override // io.ktor.server.response.ApplicationResponse
    /* JADX INFO: renamed from: isCommitted, reason: from getter */
    public boolean getResponded() {
        return this.responded;
    }

    @Override // io.ktor.server.response.ApplicationResponse
    /* JADX INFO: renamed from: isSent, reason: from getter */
    public final boolean getIsSent() {
        return this.isSent;
    }

    @Override // io.ktor.server.response.ApplicationResponse
    @UseHttp2Push
    public void push(ResponsePushBuilder builder) {
        LinkHeaderKt.link(this, builder.getUrl().buildString(), LinkHeader.Rel.Prefetch);
    }

    public Object respondFromBytes(byte[] bArr, d7.d<? super t0> dVar) {
        return respondFromBytes$suspendImpl(this, bArr, dVar);
    }

    public Object respondFromChannel(ByteReadChannel byteReadChannel, d7.d<? super t0> dVar) {
        return respondFromChannel$suspendImpl(this, byteReadChannel, dVar);
    }

    public Object respondNoContent(OutgoingContent.NoContent noContent, d7.d<? super t0> dVar) {
        return respondNoContent$suspendImpl(this, noContent, dVar);
    }

    public Object respondOutgoingContent(OutgoingContent outgoingContent, d7.d<? super t0> dVar) {
        return respondOutgoingContent$suspendImpl(this, outgoingContent, dVar);
    }

    public abstract Object respondUpgrade(OutgoingContent.ProtocolUpgrade protocolUpgrade, d7.d<? super t0> dVar);

    public Object respondWriteChannelContent(OutgoingContent.WriteChannelContent writeChannelContent, d7.d<? super t0> dVar) {
        return respondWriteChannelContent$suspendImpl(this, writeChannelContent, dVar);
    }

    public abstract Object responseChannel(d7.d<? super ByteWriteChannel> dVar);

    public abstract void setStatus(HttpStatusCode statusCode);

    @Override // io.ktor.server.response.ApplicationResponse
    /* JADX INFO: renamed from: status, reason: from getter */
    public HttpStatusCode get_status() {
        return this._status;
    }

    @Override // io.ktor.server.response.ApplicationResponse
    public void status(HttpStatusCode value) {
        this._status = value;
        setStatus(value);
    }
}
