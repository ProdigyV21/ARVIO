package io.ktor.server.http.content;

import d7.d;
import f7.c;
import f7.e;
import f7.j;
import ga.p;
import io.ktor.http.CacheControl;
import io.ktor.http.ContentType;
import io.ktor.http.FileContentTypeJvmKt;
import io.ktor.http.FileContentTypeKt;
import io.ktor.http.HeaderValue;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.routing.Route;
import io.ktor.util.AttributeKey;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.collections.z;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a7\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0002H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a]\u0010\u0007\u001a\u0004\u0018\u00010\u00122\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00022\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eH\u0000¢\u0006\u0004\b\u0007\u0010\u0013\u001a\u008d\u0001\u0010\u001c\u001a\u00020\u0019*\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00002\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00022\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00100\u000e2\u001a\b\u0002\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00020\u000e2*\b\u0002\u0010\u001b\u001a$\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0017H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u00ad\u0001\u0010\"\u001a\u00020\u0019*\u00020\t2\u0006\u0010\u001e\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00022\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\u001a\b\u0002\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00020\u000e2*\b\u0002\u0010\u001f\u001a$\b\u0001\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00172\u0014\b\u0002\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020 0\u000eH\u0080@ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\"&\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00020$8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\" \u0010,\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0002*\u00020)8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006-"}, d2 = {"Ljava/io/File;", "file", "", "Lio/ktor/http/HeaderValue;", "acceptEncoding", "Lio/ktor/server/http/content/CompressedFileType;", "compressedTypes", "bestCompressionFit", "(Ljava/io/File;Ljava/util/List;Ljava/util/List;)Lio/ktor/server/http/content/CompressedFileType;", "Lio/ktor/server/application/ApplicationCall;", "call", "", "resource", "packageName", "Lkotlin/Function1;", "Ljava/net/URL;", "Lio/ktor/http/ContentType;", "contentType", "Lio/ktor/server/http/content/CompressedResource;", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lr7/l;)Lio/ktor/server/http/content/CompressedResource;", "requestedFile", "Lio/ktor/http/CacheControl;", "cacheControl", "Lkotlin/Function3;", "Ld7/d;", "Lx6/t0;", "", "modify", "respondStaticFile", "(Lio/ktor/server/application/ApplicationCall;Ljava/io/File;Ljava/util/List;Lr7/l;Lr7/l;Lr7/q;Ld7/d;)Ljava/lang/Object;", "requestedResource", "modifier", "", "exclude", "respondStaticResource", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lr7/l;Lr7/l;Lr7/q;Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/util/AttributeKey;", "compressedKey", "Lio/ktor/util/AttributeKey;", "getCompressedKey", "()Lio/ktor/util/AttributeKey;", "Lio/ktor/server/routing/Route;", "getStaticContentEncodedTypes", "(Lio/ktor/server/routing/Route;)Ljava/util/List;", "staticContentEncodedTypes", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PreCompressedKt {
    private static final AttributeKey<List<CompressedFileType>> compressedKey = new AttributeKey<>("StaticContentCompressed");

    /* JADX INFO: renamed from: io.ktor.server.http.content.PreCompressedKt$bestCompressionFit$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/server/http/content/CompressedFileType;", "invoke", "(Lio/ktor/server/http/content/CompressedFileType;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements l<CompressedFileType, Boolean> {
        final /* synthetic */ Set<String> $acceptedEncodings;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(Set<String> set) {
            super(1);
            this.$acceptedEncodings = set;
        }

        @Override // r7.l
        public final Boolean invoke(CompressedFileType compressedFileType) {
            return Boolean.valueOf(this.$acceptedEncodings.contains(compressedFileType.getEncoding()));
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.PreCompressedKt$bestCompressionFit$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/server/http/content/CompressedResource;", "it", "Lio/ktor/server/http/content/CompressedFileType;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements l<CompressedFileType, CompressedResource> {
        final /* synthetic */ ApplicationCall $call;
        final /* synthetic */ l<URL, ContentType> $contentType;
        final /* synthetic */ String $packageName;
        final /* synthetic */ String $resource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(String str, ApplicationCall applicationCall, String str2, l<? super URL, ContentType> lVar) {
            super(1);
            this.$resource = str;
            this.$call = applicationCall;
            this.$packageName = str2;
            this.$contentType = lVar;
        }

        @Override // r7.l
        public final CompressedResource invoke(CompressedFileType compressedFileType) {
            String str = this.$resource + '.' + compressedFileType.getExtension();
            x xVarResolveResource$default = StaticContentResolutionKt.resolveResource$default(this.$call.getApplication(), str, this.$packageName, (ClassLoader) null, new PreCompressedKt$bestCompressionFit$4$resolved$1(str, this.$resource, this.$contentType), 4, (Object) null);
            if (xVarResolveResource$default == null) {
                return null;
            }
            return new CompressedResource((URL) xVarResolveResource$default.f22608i, (OutgoingContent.ReadChannelContent) xVarResolveResource$default.f22609l, compressedFileType);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.PreCompressedKt$respondStaticFile$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.http.content.PreCompressedKt", f = "PreCompressed.kt", l = {115, 184, 125, 191}, m = "respondStaticFile")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PreCompressedKt.respondStaticFile(null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.PreCompressedKt$respondStaticFile$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/http/ContentType;", "it", "Ljava/io/File;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements l<File, ContentType> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        @Override // r7.l
        public final ContentType invoke(File file) {
            return FileContentTypeJvmKt.defaultForFile(ContentType.INSTANCE, file);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.PreCompressedKt$respondStaticFile$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lio/ktor/http/CacheControl;", "it", "Ljava/io/File;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C18843 extends r implements l<File, List<? extends CacheControl>> {
        public static final C18843 INSTANCE = new C18843();

        public C18843() {
            super(1);
        }

        @Override // r7.l
        public final List<CacheControl> invoke(File file) {
            return z.f19728i;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.PreCompressedKt$respondStaticFile$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Ljava/io/File;", "<anonymous parameter 0>", "Lio/ktor/server/application/ApplicationCall;", "<anonymous parameter 1>", "Lx6/t0;", "<anonymous>", "(Ljava/io/File;Lio/ktor/server/application/ApplicationCall;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.http.content.PreCompressedKt$respondStaticFile$4", f = "PreCompressed.kt", l = {}, m = "invokeSuspend")
    public static final class C18854 extends j implements q<File, ApplicationCall, d<? super t0>, Object> {
        int label;

        public C18854(d<? super C18854> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public final Object invoke(File file, ApplicationCall applicationCall, d<? super t0> dVar) {
            return new C18854(dVar).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.PreCompressedKt$respondStaticResource$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.http.content.PreCompressedKt", f = "PreCompressed.kt", l = {184, 157, 191, 198, 174, 205}, m = "respondStaticResource")
    public static final class C18861 extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C18861(d<? super C18861> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PreCompressedKt.respondStaticResource(null, null, null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.PreCompressedKt$respondStaticResource$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/http/ContentType;", "it", "Ljava/net/URL;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C18872 extends r implements l<URL, ContentType> {
        public static final C18872 INSTANCE = new C18872();

        public C18872() {
            super(1);
        }

        @Override // r7.l
        public final ContentType invoke(URL url) {
            return FileContentTypeKt.defaultForFileExtension(ContentType.INSTANCE, StaticContentResolutionKt.extension(url.getPath()));
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.PreCompressedKt$respondStaticResource$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "Lio/ktor/http/CacheControl;", "it", "Ljava/net/URL;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C18883 extends r implements l<URL, List<? extends CacheControl>> {
        public static final C18883 INSTANCE = new C18883();

        public C18883() {
            super(1);
        }

        @Override // r7.l
        public final List<CacheControl> invoke(URL url) {
            return z.f19728i;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.PreCompressedKt$respondStaticResource$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Ljava/net/URL;", "<anonymous parameter 0>", "Lio/ktor/server/application/ApplicationCall;", "<anonymous parameter 1>", "Lx6/t0;", "<anonymous>", "(Ljava/net/URL;Lio/ktor/server/application/ApplicationCall;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.http.content.PreCompressedKt$respondStaticResource$4", f = "PreCompressed.kt", l = {}, m = "invokeSuspend")
    public static final class C18894 extends j implements q<URL, ApplicationCall, d<? super t0>, Object> {
        int label;

        public C18894(d<? super C18894> dVar) {
            super(3, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return t0.f22605a;
        }

        @Override // r7.q
        public final Object invoke(URL url, ApplicationCall applicationCall, d<? super t0> dVar) {
            return new C18894(dVar).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.PreCompressedKt$respondStaticResource$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Ljava/net/URL;", "invoke", "(Ljava/net/URL;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass5 extends r implements l<URL, Boolean> {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        public AnonymousClass5() {
            super(1);
        }

        @Override // r7.l
        public final Boolean invoke(URL url) {
            return Boolean.FALSE;
        }
    }

    public static final CompressedFileType bestCompressionFit(File file, List<HeaderValue> list, List<? extends CompressedFileType> list2) {
        ArrayList arrayList = new ArrayList(s.U(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((HeaderValue) it.next()).getValue());
        }
        Set setG1 = kotlin.collections.x.g1(arrayList);
        Object obj = null;
        if (list2 == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : list2) {
            if (setG1.contains(((CompressedFileType) obj2).getEncoding())) {
                arrayList2.add(obj2);
            }
        }
        Iterator it2 = arrayList2.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            if (((CompressedFileType) next).file(file).isFile()) {
                obj = next;
                break;
            }
        }
        return (CompressedFileType) obj;
    }

    public static final AttributeKey<List<CompressedFileType>> getCompressedKey() {
        return compressedKey;
    }

    public static final List<CompressedFileType> getStaticContentEncodedTypes(Route route) {
        List<CompressedFileType> list = (List) route.getAttributes().getOrNull(compressedKey);
        if (list != null) {
            return list;
        }
        Route parent = route.getParent();
        if (parent != null) {
            return getStaticContentEncodedTypes(parent);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x015b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x015c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object respondStaticFile(io.ktor.server.application.ApplicationCall r21, java.io.File r22, java.util.List<? extends io.ktor.server.http.content.CompressedFileType> r23, r7.l<? super java.io.File, io.ktor.http.ContentType> r24, r7.l<? super java.io.File, ? extends java.util.List<? extends io.ktor.http.CacheControl>> r25, r7.q<? super java.io.File, ? super io.ktor.server.application.ApplicationCall, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r26, d7.d<? super x6.t0> r27) {
        /*
            Method dump skipped, instruction units count: 349
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.http.content.PreCompressedKt.respondStaticFile(io.ktor.server.application.ApplicationCall, java.io.File, java.util.List, r7.l, r7.l, r7.q, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object respondStaticFile$default(ApplicationCall applicationCall, File file, List list, l lVar, l lVar2, q qVar, d dVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            lVar = AnonymousClass2.INSTANCE;
        }
        l lVar3 = lVar;
        if ((i10 & 8) != 0) {
            lVar2 = C18843.INSTANCE;
        }
        l lVar4 = lVar2;
        if ((i10 & 16) != 0) {
            qVar = new C18854(null);
        }
        return respondStaticFile(applicationCall, file, list, lVar3, lVar4, qVar, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x0202 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0203 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object respondStaticResource(io.ktor.server.application.ApplicationCall r20, java.lang.String r21, java.lang.String r22, java.util.List<? extends io.ktor.server.http.content.CompressedFileType> r23, r7.l<? super java.net.URL, io.ktor.http.ContentType> r24, r7.l<? super java.net.URL, ? extends java.util.List<? extends io.ktor.http.CacheControl>> r25, r7.q<? super java.net.URL, ? super io.ktor.server.application.ApplicationCall, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r26, r7.l<? super java.net.URL, java.lang.Boolean> r27, d7.d<? super x6.t0> r28) {
        /*
            Method dump skipped, instruction units count: 534
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.http.content.PreCompressedKt.respondStaticResource(io.ktor.server.application.ApplicationCall, java.lang.String, java.lang.String, java.util.List, r7.l, r7.l, r7.q, r7.l, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object respondStaticResource$default(ApplicationCall applicationCall, String str, String str2, List list, l lVar, l lVar2, q qVar, l lVar3, d dVar, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            lVar = C18872.INSTANCE;
        }
        l lVar4 = lVar;
        if ((i10 & 16) != 0) {
            lVar2 = C18883.INSTANCE;
        }
        return respondStaticResource(applicationCall, str, str2, list, lVar4, lVar2, (i10 & 32) != 0 ? new C18894(null) : qVar, (i10 & 64) != 0 ? AnonymousClass5.INSTANCE : lVar3, dVar);
    }

    public static final CompressedResource bestCompressionFit(ApplicationCall applicationCall, String str, String str2, List<HeaderValue> list, List<? extends CompressedFileType> list2, l<? super URL, ContentType> lVar) {
        ArrayList arrayList = new ArrayList(s.U(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((HeaderValue) it.next()).getValue());
        }
        Set setG1 = kotlin.collections.x.g1(arrayList);
        if (list2 != null) {
            return (CompressedResource) ga.r.K(ga.r.Q(ga.r.I(new p(list2, 3), new AnonymousClass3(setG1)), new AnonymousClass4(str, applicationCall, str2, lVar)));
        }
        return null;
    }
}
