package io.ktor.server.response;

import d7.d;
import e7.a;
import io.ktor.http.ContentType;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.OutputStreamContent;
import io.ktor.http.content.WriterContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.http.content.LocalFileContent;
import io.ktor.server.http.content.LocalFileContentKt;
import io.ktor.util.reflect.TypeInfoJvmKt;
import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import kotlin.jvm.internal.r;
import kotlin.reflect.b0;
import kotlin.reflect.q;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\u001aX\u0010\f\u001a\u00020\b*\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001aX\u0010\u0010\u001a\u00020\b*\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032'\u0010\u000f\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\r\u001aB\u0010\u0018\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0019\b\u0002\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\b0\u0015¢\u0006\u0002\b\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a:\u0010\u0018\u001a\u00020\b*\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u00112\u0019\b\u0002\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\b0\u0015¢\u0006\u0002\b\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u001b\u001ad\u0010\f\u001a\u00020\b*\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u001e\u001ad\u0010\u0010\u001a\u00020\b*\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2'\u0010\u000f\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\u0002\b\nH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u001e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/http/ContentType;", "contentType", "Lio/ktor/http/HttpStatusCode;", "status", "Lkotlin/Function2;", "Ljava/io/Writer;", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "writer", "respondTextWriter", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;Lr7/p;Ld7/d;)Ljava/lang/Object;", "Ljava/io/OutputStream;", "producer", "respondOutputStream", "Ljava/io/File;", "baseDir", "", "fileName", "Lkotlin/Function1;", "Lio/ktor/http/content/OutgoingContent;", "configure", "respondFile", "(Lio/ktor/server/application/ApplicationCall;Ljava/io/File;Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "file", "(Lio/ktor/server/application/ApplicationCall;Ljava/io/File;Lr7/l;Ld7/d;)Ljava/lang/Object;", "", "contentLength", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/ContentType;Lio/ktor/http/HttpStatusCode;Ljava/lang/Long;Lr7/p;Ld7/d;)Ljava/lang/Object;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ApplicationResponseFunctionsJvmKt {

    /* JADX INFO: renamed from: io.ktor.server.response.ApplicationResponseFunctionsJvmKt$respondFile$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/http/content/OutgoingContent;", "Lx6/t0;", "invoke", "(Lio/ktor/http/content/OutgoingContent;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<OutgoingContent, t0> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        public final void invoke(OutgoingContent outgoingContent) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((OutgoingContent) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.response.ApplicationResponseFunctionsJvmKt$respondFile$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/http/content/OutgoingContent;", "Lx6/t0;", "invoke", "(Lio/ktor/http/content/OutgoingContent;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements l<OutgoingContent, t0> {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        public AnonymousClass4() {
            super(1);
        }

        public final void invoke(OutgoingContent outgoingContent) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((OutgoingContent) obj);
            return t0.f22605a;
        }
    }

    public static final Object respondFile(ApplicationCall applicationCall, File file, String str, l<? super OutgoingContent, t0> lVar, d<? super t0> dVar) {
        LocalFileContent localFileContentLocalFileContent$default = LocalFileContentKt.LocalFileContent$default(file, str, (ContentType) null, 4, (Object) null);
        lVar.invoke(localFileContentLocalFileContent$default);
        if (localFileContentLocalFileContent$default == null && !(localFileContentLocalFileContent$default instanceof byte[])) {
            ApplicationResponse response = applicationCall.getResponse();
            q qVarA = l0.a(LocalFileContent.class);
            ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(b0.t(qVarA), l0.f19747a.b(LocalFileContent.class), qVarA));
        }
        Object objExecute = applicationCall.getResponse().getPipeline().execute(applicationCall, localFileContentLocalFileContent$default, dVar);
        return objExecute == a.f15033i ? objExecute : t0.f22605a;
    }

    public static /* synthetic */ Object respondFile$default(ApplicationCall applicationCall, File file, String str, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            lVar = AnonymousClass2.INSTANCE;
        }
        return respondFile(applicationCall, file, str, lVar, dVar);
    }

    public static final Object respondOutputStream(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, p<? super OutputStream, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) {
        if (contentType == null) {
            contentType = ContentType.Application.INSTANCE.getOctetStream();
        }
        Object objExecute = applicationCall.getResponse().getPipeline().execute(applicationCall, new OutputStreamContent(pVar, contentType, httpStatusCode, null, 8, null), dVar);
        return objExecute == a.f15033i ? objExecute : t0.f22605a;
    }

    public static /* synthetic */ Object respondOutputStream$default(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, p pVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            contentType = null;
        }
        if ((i10 & 2) != 0) {
            httpStatusCode = null;
        }
        return respondOutputStream(applicationCall, contentType, httpStatusCode, pVar, dVar);
    }

    public static final Object respondTextWriter(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, p<? super Writer, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) {
        Object objExecute = applicationCall.getResponse().getPipeline().execute(applicationCall, new WriterContent(pVar, ApplicationResponseFunctionsKt.defaultTextContentType(applicationCall, contentType), httpStatusCode, null, 8, null), dVar);
        return objExecute == a.f15033i ? objExecute : t0.f22605a;
    }

    public static /* synthetic */ Object respondTextWriter$default(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, p pVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            contentType = null;
        }
        if ((i10 & 2) != 0) {
            httpStatusCode = null;
        }
        return respondTextWriter(applicationCall, contentType, httpStatusCode, pVar, dVar);
    }

    public static /* synthetic */ Object respondOutputStream$default(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Long l10, p pVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            contentType = null;
        }
        if ((i10 & 2) != 0) {
            httpStatusCode = null;
        }
        if ((i10 & 4) != 0) {
            l10 = null;
        }
        return respondOutputStream(applicationCall, contentType, httpStatusCode, l10, pVar, dVar);
    }

    public static /* synthetic */ Object respondTextWriter$default(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Long l10, p pVar, d dVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            contentType = null;
        }
        if ((i10 & 2) != 0) {
            httpStatusCode = null;
        }
        if ((i10 & 4) != 0) {
            l10 = null;
        }
        return respondTextWriter(applicationCall, contentType, httpStatusCode, l10, pVar, dVar);
    }

    public static /* synthetic */ Object respondFile$default(ApplicationCall applicationCall, File file, l lVar, d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass4.INSTANCE;
        }
        return respondFile(applicationCall, file, lVar, dVar);
    }

    public static final Object respondOutputStream(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Long l10, p<? super OutputStream, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) {
        if (contentType == null) {
            contentType = ContentType.Application.INSTANCE.getOctetStream();
        }
        Object objExecute = applicationCall.getResponse().getPipeline().execute(applicationCall, new OutputStreamContent(pVar, contentType, httpStatusCode, l10), dVar);
        return objExecute == a.f15033i ? objExecute : t0.f22605a;
    }

    public static final Object respondTextWriter(ApplicationCall applicationCall, ContentType contentType, HttpStatusCode httpStatusCode, Long l10, p<? super Writer, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) {
        Object objExecute = applicationCall.getResponse().getPipeline().execute(applicationCall, new WriterContent(pVar, ApplicationResponseFunctionsKt.defaultTextContentType(applicationCall, contentType), httpStatusCode, l10), dVar);
        return objExecute == a.f15033i ? objExecute : t0.f22605a;
    }

    public static final Object respondFile(ApplicationCall applicationCall, File file, l<? super OutgoingContent, t0> lVar, d<? super t0> dVar) {
        LocalFileContent localFileContent = new LocalFileContent(file, null, 2, null);
        lVar.invoke(localFileContent);
        Object objExecute = applicationCall.getResponse().getPipeline().execute(applicationCall, localFileContent, dVar);
        return objExecute == a.f15033i ? objExecute : t0.f22605a;
    }
}
