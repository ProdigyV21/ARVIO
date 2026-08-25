package io.ktor.http.content;

import f4.f;
import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.core.Input;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import r7.a;
import x6.e;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004%&'(B\u001f\b\u0004\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u0014\u001a\u0004\u0018\u00010\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0019\u001a\u0004\u0018\u00010\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u001a8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\"\u001a\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b!\u0010\u001e\u001a\u0004\b \u0010\u000eR\u0013\u0010$\u001a\u0004\u0018\u00010\u001a8F¢\u0006\u0006\u001a\u0004\b#\u0010\u001c\u0082\u0001\u0004)*+,¨\u0006-"}, d2 = {"Lio/ktor/http/content/PartData;", "", "Lkotlin/Function0;", "Lx6/t0;", "dispose", "Lio/ktor/http/Headers;", "headers", "<init>", "(Lr7/a;Lio/ktor/http/Headers;)V", "Lr7/a;", "getDispose", "()Lr7/a;", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "Lio/ktor/http/ContentDisposition;", "contentDisposition$delegate", "Lx6/s;", "getContentDisposition", "()Lio/ktor/http/ContentDisposition;", "contentDisposition", "Lio/ktor/http/ContentType;", "contentType$delegate", "getContentType", "()Lio/ktor/http/ContentType;", "contentType", "", "getPartName", "()Ljava/lang/String;", "getPartName$annotations", "()V", "partName", "getPartHeaders", "getPartHeaders$annotations", "partHeaders", "getName", ContentDisposition.Parameters.Name, "BinaryChannelItem", "BinaryItem", "FileItem", "FormItem", "Lio/ktor/http/content/PartData$BinaryChannelItem;", "Lio/ktor/http/content/PartData$BinaryItem;", "Lio/ktor/http/content/PartData$FileItem;", "Lio/ktor/http/content/PartData$FormItem;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class PartData {

    /* JADX INFO: renamed from: contentDisposition$delegate, reason: from kotlin metadata */
    private final s contentDisposition;

    /* JADX INFO: renamed from: contentType$delegate, reason: from kotlin metadata */
    private final s contentType;
    private final a<t0> dispose;
    private final Headers headers;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/http/content/PartData$BinaryChannelItem;", "Lio/ktor/http/content/PartData;", "Lkotlin/Function0;", "Lio/ktor/utils/io/ByteReadChannel;", "provider", "Lio/ktor/http/Headers;", "partHeaders", "<init>", "(Lr7/a;Lio/ktor/http/Headers;)V", "Lr7/a;", "getProvider", "()Lr7/a;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class BinaryChannelItem extends PartData {
        private final a<ByteReadChannel> provider;

        /* JADX INFO: renamed from: io.ktor.http.content.PartData$BinaryChannelItem$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements a<t0> {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

            public AnonymousClass1() {
                super(0);
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m6683invoke() {
            }

            @Override // r7.a
            public /* bridge */ /* synthetic */ Object invoke() {
                m6683invoke();
                return t0.f22605a;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public BinaryChannelItem(a<? extends ByteReadChannel> aVar, Headers headers) {
            super(AnonymousClass1.INSTANCE, headers, null);
            this.provider = aVar;
        }

        public final a<ByteReadChannel> getProvider() {
            return this.provider;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lio/ktor/http/content/PartData$BinaryItem;", "Lio/ktor/http/content/PartData;", "Lkotlin/Function0;", "Lio/ktor/utils/io/core/Input;", "provider", "Lx6/t0;", "dispose", "Lio/ktor/http/Headers;", "partHeaders", "<init>", "(Lr7/a;Lr7/a;Lio/ktor/http/Headers;)V", "Lr7/a;", "getProvider", "()Lr7/a;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class BinaryItem extends PartData {
        private final a<Input> provider;

        /* JADX WARN: Multi-variable type inference failed */
        public BinaryItem(a<? extends Input> aVar, a<t0> aVar2, Headers headers) {
            super(aVar2, headers, null);
            this.provider = aVar;
        }

        public final a<Input> getProvider() {
            return this.provider;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/http/content/PartData$FileItem;", "Lio/ktor/http/content/PartData;", "Lkotlin/Function0;", "Lio/ktor/utils/io/core/Input;", "provider", "Lx6/t0;", "dispose", "Lio/ktor/http/Headers;", "partHeaders", "<init>", "(Lr7/a;Lr7/a;Lio/ktor/http/Headers;)V", "Lr7/a;", "getProvider", "()Lr7/a;", "", "originalFileName", "Ljava/lang/String;", "getOriginalFileName", "()Ljava/lang/String;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class FileItem extends PartData {
        private final String originalFileName;
        private final a<Input> provider;

        /* JADX WARN: Multi-variable type inference failed */
        public FileItem(a<? extends Input> aVar, a<t0> aVar2, Headers headers) {
            super(aVar2, headers, 0 == true ? 1 : 0);
            this.provider = aVar;
            ContentDisposition contentDisposition = getContentDisposition();
            this.originalFileName = contentDisposition != null ? contentDisposition.parameter(ContentDisposition.Parameters.FileName) : null;
        }

        public final String getOriginalFileName() {
            return this.originalFileName;
        }

        public final a<Input> getProvider() {
            return this.provider;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lio/ktor/http/content/PartData$FormItem;", "Lio/ktor/http/content/PartData;", "", "value", "Lkotlin/Function0;", "Lx6/t0;", "dispose", "Lio/ktor/http/Headers;", "partHeaders", "<init>", "(Ljava/lang/String;Lr7/a;Lio/ktor/http/Headers;)V", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class FormItem extends PartData {
        private final String value;

        public FormItem(String str, a<t0> aVar, Headers headers) {
            super(aVar, headers, null);
            this.value = str;
        }

        public final String getValue() {
            return this.value;
        }
    }

    public /* synthetic */ PartData(a aVar, Headers headers, h hVar) {
        this(aVar, headers);
    }

    @e
    public static /* synthetic */ void getPartHeaders$annotations() {
    }

    @e
    public static /* synthetic */ void getPartName$annotations() {
    }

    public final ContentDisposition getContentDisposition() {
        return (ContentDisposition) this.contentDisposition.getValue();
    }

    public final ContentType getContentType() {
        return (ContentType) this.contentType.getValue();
    }

    public final a<t0> getDispose() {
        return this.dispose;
    }

    public final Headers getHeaders() {
        return this.headers;
    }

    public final String getName() {
        ContentDisposition contentDisposition = getContentDisposition();
        if (contentDisposition != null) {
            return contentDisposition.getName();
        }
        return null;
    }

    public final Headers getPartHeaders() {
        return this.headers;
    }

    public final String getPartName() {
        return getName();
    }

    private PartData(a<t0> aVar, Headers headers) {
        this.dispose = aVar;
        this.headers = headers;
        this.contentDisposition = f.o(3, new PartData$contentDisposition$2(this));
        this.contentType = f.o(3, new PartData$contentType$2(this));
    }
}
