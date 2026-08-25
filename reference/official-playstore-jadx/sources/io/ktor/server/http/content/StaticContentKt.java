package io.ktor.server.http.content;

import androidx.compose.material3.MenuKt;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.CacheControl;
import io.ktor.http.ContentType;
import io.ktor.http.HttpMethod;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationPluginKt;
import io.ktor.server.application.CreatePluginUtilsKt;
import io.ktor.server.application.RouteScopedPlugin;
import io.ktor.server.routing.Route;
import io.ktor.server.routing.RoutingBuilderKt;
import io.ktor.util.AttributeKey;
import io.ktor.util.PathKt;
import io.ktor.util.pipeline.PipelineContext;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import k2.c;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.r;
import l7.i;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u001aN\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00012\u001f\b\u0002\u0010\n\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t¢\u0006\u0004\b\u000b\u0010\f\u001aP\u0010\u000f\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00012\u001f\b\u0002\u0010\n\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t¢\u0006\u0004\b\u000f\u0010\u0010\u001a@\u0010\u0015\u001a\u00020\b*\u00020\u00002\u0014\b\u0002\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\u0011\"\u00020\u00122\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u001d\u0010\u0018\u001a\u00020\u0003*\u0004\u0018\u00010\u00032\u0006\u0010\u0017\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0018\u0010\u0019\u001a,\u0010\u001a\u001a\u00020\u0000*\u00020\u00002\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a4\u0010\u001a\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0007¢\u0006\u0004\b\u001a\u0010\u001c\u001a\u001b\u0010\u001e\u001a\u00020\b*\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001b\u0010\u001e\u001a\u00020\b*\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u001e\u0010 \u001a%\u0010\u0017\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u001d\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0017\u0010!\u001a#\u0010\u0017\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u0003H\u0007¢\u0006\u0004\b\u0017\u0010\"\u001a\u001b\u0010$\u001a\u00020\b*\u00020\u00002\u0006\u0010#\u001a\u00020\u0001H\u0007¢\u0006\u0004\b$\u0010\u001f\u001a\u001b\u0010$\u001a\u00020\b*\u00020\u00002\u0006\u0010#\u001a\u00020\u0003H\u0007¢\u0006\u0004\b$\u0010 \u001a!\u0010&\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u00012\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b&\u0010'\u001a1\u0010(\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010(\u001a\u00020\u00012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0004\b(\u0010)\u001a\u001f\u0010*\u001a\u00020\b*\u00020\u00002\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0004\b*\u0010\u001f\u001a'\u0010+\u001a\u00020\b*\u00020\u00002\u0006\u0010(\u001a\u00020\u00012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0004\b+\u0010!\u001a\u0011\u0010.\u001a\u00020-*\u00020,¢\u0006\u0004\b.\u0010/\u001aO\u00105\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u00100\u001a\u00020-2'\u00104\u001a#\b\u0001\u0012\u0004\u0012\u00020,\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b02\u0012\u0006\u0012\u0004\u0018\u00010301¢\u0006\u0002\b\tH\u0002ø\u0001\u0000¢\u0006\u0004\b5\u00106\u001a½\u0001\u0010B\u001a\u00020\b*\u00020,2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u000e\u00108\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u0001072\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002090\u00062\u0018\u0010<\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020;070\u00062(\u0010>\u001a$\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020,\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b02\u0012\u0006\u0012\u0004\u0018\u0001030=2\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020-0\u00062\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u0001072\b\u0010A\u001a\u0004\u0018\u00010\u0001H\u0082@ø\u0001\u0000¢\u0006\u0004\bB\u0010C\u001a¿\u0001\u0010E\u001a\u00020\b*\u00020,2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u00012\u000e\u00108\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u0001072\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002090\u00062\u0018\u0010<\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020;070\u00062(\u0010D\u001a$\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020,\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b02\u0012\u0006\u0012\u0004\u0018\u0001030=2\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020-0\u00062\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u0001072\b\u0010A\u001a\u0004\u0018\u00010\u0001H\u0082@ø\u0001\u0000¢\u0006\u0004\bE\u0010F\"\u0014\u0010G\u001a\u00020\u00018\u0002X\u0082T¢\u0006\u0006\n\u0004\bG\u0010H\"\u001a\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00030I8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bJ\u0010K\"\u001a\u0010M\u001a\b\u0012\u0004\u0012\u00020\b0L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bM\u0010N\"\u001a\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00010I8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bO\u0010K\",\u0010T\u001a\u0004\u0018\u00010\u0003*\u00020\u00002\b\u0010P\u001a\u0004\u0018\u00010\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010 \"2\u0010Z\u001a\u0004\u0018\u00010\u0001*\u00020\u00002\b\u0010P\u001a\u0004\u0018\u00010\u00018F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\bX\u0010Y\u001a\u0004\bU\u0010V\"\u0004\bW\u0010\u001f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006["}, d2 = {"Lio/ktor/server/routing/Route;", "", "remotePath", "Ljava/io/File;", "dir", "index", "Lkotlin/Function1;", "Lio/ktor/server/http/content/StaticContentConfig;", "Lx6/t0;", "Lx6/n;", "block", "staticFiles", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Ljava/io/File;Ljava/lang/String;Lr7/l;)Lio/ktor/server/routing/Route;", "basePackage", "Ljava/net/URL;", "staticResources", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lr7/l;)Lio/ktor/server/routing/Route;", "", "Lio/ktor/server/http/content/CompressedFileType;", "types", "configure", "preCompressed", "(Lio/ktor/server/routing/Route;[Lio/ktor/server/http/content/CompressedFileType;Lr7/l;)V", "file", "combine", "(Ljava/io/File;Ljava/io/File;)Ljava/io/File;", "static", "(Lio/ktor/server/routing/Route;Lr7/l;)Lio/ktor/server/routing/Route;", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Lr7/l;)Lio/ktor/server/routing/Route;", "localPath", "default", "(Lio/ktor/server/routing/Route;Ljava/lang/String;)V", "(Lio/ktor/server/routing/Route;Ljava/io/File;)V", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Ljava/lang/String;)V", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Ljava/io/File;)V", "folder", "files", "resourcePackage", "combinePackage", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "resource", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "resources", "defaultResource", "Lio/ktor/server/application/ApplicationCall;", "", "isStaticContent", "(Lio/ktor/server/application/ApplicationCall;)Z", "autoHead", "Lkotlin/Function2;", "Ld7/d;", "", "handler", "staticContentRoute", "(Lio/ktor/server/routing/Route;Ljava/lang/String;ZLr7/p;)Lio/ktor/server/routing/Route;", "", "compressedTypes", "Lio/ktor/http/ContentType;", "contentType", "Lio/ktor/http/CacheControl;", "cacheControl", "Lkotlin/Function3;", "modify", "exclude", "extensions", "defaultPath", "respondStaticFile", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Ljava/io/File;Ljava/util/List;Lr7/l;Lr7/l;Lr7/q;Lr7/l;Ljava/util/List;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "modifier", "respondStaticResource", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lr7/l;Lr7/l;Lr7/q;Lr7/l;Ljava/util/List;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "pathParameterName", "Ljava/lang/String;", "Lio/ktor/util/AttributeKey;", "staticRootFolderKey", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/application/RouteScopedPlugin;", "StaticContentAutoHead", "Lio/ktor/server/application/RouteScopedPlugin;", "staticBasePackageName", "value", "getStaticRootFolder", "(Lio/ktor/server/routing/Route;)Ljava/io/File;", "setStaticRootFolder", "staticRootFolder", "getStaticBasePackage", "(Lio/ktor/server/routing/Route;)Ljava/lang/String;", "setStaticBasePackage", "getStaticBasePackage$annotations", "(Lio/ktor/server/routing/Route;)V", "staticBasePackage", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StaticContentKt {
    private static final String pathParameterName = "static-content-path-parameter";
    private static final AttributeKey<File> staticRootFolderKey = new AttributeKey<>("BaseFolder");
    private static final RouteScopedPlugin<t0> StaticContentAutoHead = CreatePluginUtilsKt.createRouteScopedPlugin("StaticContentAutoHead", StaticContentKt$StaticContentAutoHead$1.INSTANCE);
    private static final AttributeKey<String> staticBasePackageName = new AttributeKey<>("BasePackage");

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$default$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.http.content.StaticContentKt$default$1", f = "StaticContent.kt", l = {298}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ List<CompressedFileType> $compressedTypes;
        final /* synthetic */ File $file;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(File file, List<? extends CompressedFileType> list, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$file = file;
            this.$compressedTypes = list;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$file, this.$compressedTypes, dVar);
            anonymousClass1.L$0 = pipelineContext;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                ApplicationCall applicationCall = (ApplicationCall) ((PipelineContext) this.L$0).getContext();
                File file = this.$file;
                List<CompressedFileType> list = this.$compressedTypes;
                this.label = 1;
                Object objRespondStaticFile$default = PreCompressedKt.respondStaticFile$default(applicationCall, file, list, null, null, null, this, 28, null);
                a aVar = a.f15033i;
                if (objRespondStaticFile$default == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$defaultResource$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.http.content.StaticContentKt$defaultResource$1", f = "StaticContent.kt", l = {409}, m = "invokeSuspend")
    public static final class C18921 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ List<CompressedFileType> $compressedTypes;
        final /* synthetic */ String $packageName;
        final /* synthetic */ String $resource;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18921(String str, String str2, List<? extends CompressedFileType> list, d<? super C18921> dVar) {
            super(3, dVar);
            this.$resource = str;
            this.$packageName = str2;
            this.$compressedTypes = list;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            C18921 c18921 = new C18921(this.$resource, this.$packageName, this.$compressedTypes, dVar);
            c18921.L$0 = pipelineContext;
            return c18921.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                ApplicationCall applicationCall = (ApplicationCall) ((PipelineContext) this.L$0).getContext();
                String str = this.$resource;
                String str2 = this.$packageName;
                List<CompressedFileType> list = this.$compressedTypes;
                this.label = 1;
                Object objRespondStaticResource$default = PreCompressedKt.respondStaticResource$default(applicationCall, str, str2, list, null, null, null, null, this, MenuKt.InTransitionDuration, null);
                a aVar = a.f15033i;
                if (objRespondStaticResource$default == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$file$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.http.content.StaticContentKt$file$1", f = "StaticContent.kt", l = {317}, m = "invokeSuspend")
    public static final class C18931 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ List<CompressedFileType> $compressedTypes;
        final /* synthetic */ File $file;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18931(File file, List<? extends CompressedFileType> list, d<? super C18931> dVar) {
            super(3, dVar);
            this.$file = file;
            this.$compressedTypes = list;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            C18931 c18931 = new C18931(this.$file, this.$compressedTypes, dVar);
            c18931.L$0 = pipelineContext;
            return c18931.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                ApplicationCall applicationCall = (ApplicationCall) ((PipelineContext) this.L$0).getContext();
                File file = this.$file;
                List<CompressedFileType> list = this.$compressedTypes;
                this.label = 1;
                Object objRespondStaticFile$default = PreCompressedKt.respondStaticFile$default(applicationCall, file, list, null, null, null, this, 28, null);
                a aVar = a.f15033i;
                if (objRespondStaticFile$default == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$files$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.http.content.StaticContentKt$files$1", f = "StaticContent.kt", l = {338}, m = "invokeSuspend")
    public static final class C18941 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ List<CompressedFileType> $compressedTypes;
        final /* synthetic */ File $dir;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18941(File file, List<? extends CompressedFileType> list, d<? super C18941> dVar) {
            super(3, dVar);
            this.$dir = file;
            this.$compressedTypes = list;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            C18941 c18941 = new C18941(this.$dir, this.$compressedTypes, dVar);
            c18941.L$0 = pipelineContext;
            return c18941.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String strU0;
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                return t0Var;
            }
            c.G(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            List<String> all = ((ApplicationCall) pipelineContext.getContext()).getParameters().getAll(StaticContentKt.pathParameterName);
            if (all != null && (strU0 = x.u0(all, File.separator, null, null, null, 62)) != null) {
                File fileCombineSafe = PathKt.combineSafe(this.$dir, strU0);
                ApplicationCall applicationCall = (ApplicationCall) pipelineContext.getContext();
                List<CompressedFileType> list = this.$compressedTypes;
                this.label = 1;
                Object objRespondStaticFile$default = PreCompressedKt.respondStaticFile$default(applicationCall, fileCombineSafe, list, null, null, null, this, 28, null);
                a aVar = a.f15033i;
                if (objRespondStaticFile$default == aVar) {
                    return aVar;
                }
            }
            return t0Var;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$resource$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.http.content.StaticContentKt$resource$1", f = "StaticContent.kt", l = {374}, m = "invokeSuspend")
    public static final class C18951 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ List<CompressedFileType> $compressedTypes;
        final /* synthetic */ String $packageName;
        final /* synthetic */ String $resource;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18951(String str, String str2, List<? extends CompressedFileType> list, d<? super C18951> dVar) {
            super(3, dVar);
            this.$resource = str;
            this.$packageName = str2;
            this.$compressedTypes = list;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            C18951 c18951 = new C18951(this.$resource, this.$packageName, this.$compressedTypes, dVar);
            c18951.L$0 = pipelineContext;
            return c18951.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                ApplicationCall applicationCall = (ApplicationCall) ((PipelineContext) this.L$0).getContext();
                String str = this.$resource;
                String str2 = this.$packageName;
                List<CompressedFileType> list = this.$compressedTypes;
                this.label = 1;
                Object objRespondStaticResource$default = PreCompressedKt.respondStaticResource$default(applicationCall, str, str2, list, null, null, null, null, this, MenuKt.InTransitionDuration, null);
                a aVar = a.f15033i;
                if (objRespondStaticResource$default == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$resources$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.http.content.StaticContentKt$resources$1", f = "StaticContent.kt", l = {392}, m = "invokeSuspend")
    public static final class C18961 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ List<CompressedFileType> $compressedTypes;
        final /* synthetic */ String $packageName;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18961(String str, List<? extends CompressedFileType> list, d<? super C18961> dVar) {
            super(3, dVar);
            this.$packageName = str;
            this.$compressedTypes = list;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            C18961 c18961 = new C18961(this.$packageName, this.$compressedTypes, dVar);
            c18961.L$0 = pipelineContext;
            return c18961.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String strU0;
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                return t0Var;
            }
            c.G(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            List<String> all = ((ApplicationCall) pipelineContext.getContext()).getParameters().getAll(StaticContentKt.pathParameterName);
            if (all != null && (strU0 = x.u0(all, File.separator, null, null, null, 62)) != null) {
                ApplicationCall applicationCall = (ApplicationCall) pipelineContext.getContext();
                String str = this.$packageName;
                List<CompressedFileType> list = this.$compressedTypes;
                this.label = 1;
                Object objRespondStaticResource$default = PreCompressedKt.respondStaticResource$default(applicationCall, strU0, str, list, null, null, null, null, this, MenuKt.InTransitionDuration, null);
                a aVar = a.f15033i;
                if (objRespondStaticResource$default == aVar) {
                    return aVar;
                }
            }
            return t0Var;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$respondStaticFile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.http.content.StaticContentKt", f = "StaticContent.kt", l = {464, 466, 468, 472, 473, 480}, m = "respondStaticFile")
    public static final class C18971 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C18971(d<? super C18971> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return StaticContentKt.respondStaticFile(null, null, null, null, null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$respondStaticResource$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.server.http.content.StaticContentKt", f = "StaticContent.kt", l = {497, 509, 522, 533}, m = "respondStaticResource")
    public static final class C18981 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C18981(d<? super C18981> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return StaticContentKt.respondStaticResource(null, null, null, null, null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$staticContentRoute$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18991 extends r implements l<Route, t0> {
        final /* synthetic */ boolean $autoHead;
        final /* synthetic */ p<ApplicationCall, d<? super t0>, Object> $handler;

        /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$staticContentRoute$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C02381 extends r implements l<Route, t0> {
            final /* synthetic */ boolean $autoHead;
            final /* synthetic */ p<ApplicationCall, d<? super t0>, Object> $handler;

            /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$staticContentRoute$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
            @e(c = "io.ktor.server.http.content.StaticContentKt$staticContentRoute$1$1$1", f = "StaticContent.kt", l = {429}, m = "invokeSuspend")
            public static final class C02391 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
                final /* synthetic */ p<ApplicationCall, d<? super t0>, Object> $handler;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public C02391(p<? super ApplicationCall, ? super d<? super t0>, ? extends Object> pVar, d<? super C02391> dVar) {
                    super(3, dVar);
                    this.$handler = pVar;
                }

                @Override // r7.q
                public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
                    C02391 c02391 = new C02391(this.$handler, dVar);
                    c02391.L$0 = pipelineContext;
                    return c02391.invokeSuspend(t0.f22605a);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    int i10 = this.label;
                    if (i10 == 0) {
                        c.G(obj);
                        PipelineContext pipelineContext = (PipelineContext) this.L$0;
                        p<ApplicationCall, d<? super t0>, Object> pVar = this.$handler;
                        ApplicationCall applicationCall = (ApplicationCall) pipelineContext.getContext();
                        this.label = 1;
                        Object objInvoke = pVar.invoke(applicationCall, this);
                        a aVar = a.f15033i;
                        if (objInvoke == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        c.G(obj);
                    }
                    return t0.f22605a;
                }
            }

            /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$staticContentRoute$1$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class AnonymousClass2 extends r implements l<Route, t0> {
                final /* synthetic */ p<ApplicationCall, d<? super t0>, Object> $handler;

                /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$staticContentRoute$1$1$2$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
                @e(c = "io.ktor.server.http.content.StaticContentKt$staticContentRoute$1$1$2$1", f = "StaticContent.kt", l = {435}, m = "invokeSuspend")
                public static final class C02401 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
                    final /* synthetic */ p<ApplicationCall, d<? super t0>, Object> $handler;
                    private /* synthetic */ Object L$0;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public C02401(p<? super ApplicationCall, ? super d<? super t0>, ? extends Object> pVar, d<? super C02401> dVar) {
                        super(3, dVar);
                        this.$handler = pVar;
                    }

                    @Override // r7.q
                    public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
                        C02401 c02401 = new C02401(this.$handler, dVar);
                        c02401.L$0 = pipelineContext;
                        return c02401.invokeSuspend(t0.f22605a);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        int i10 = this.label;
                        if (i10 == 0) {
                            c.G(obj);
                            PipelineContext pipelineContext = (PipelineContext) this.L$0;
                            p<ApplicationCall, d<? super t0>, Object> pVar = this.$handler;
                            ApplicationCall applicationCall = (ApplicationCall) pipelineContext.getContext();
                            this.label = 1;
                            Object objInvoke = pVar.invoke(applicationCall, this);
                            a aVar = a.f15033i;
                            if (objInvoke == aVar) {
                                return aVar;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            c.G(obj);
                        }
                        return t0.f22605a;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public AnonymousClass2(p<? super ApplicationCall, ? super d<? super t0>, ? extends Object> pVar) {
                    super(1);
                    this.$handler = pVar;
                }

                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Route) obj);
                    return t0.f22605a;
                }

                public final void invoke(Route route) {
                    ApplicationPluginKt.install$default(route, StaticContentKt.StaticContentAutoHead, (l) null, 2, (Object) null);
                    route.handle(new C02401(this.$handler, null));
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C02381(boolean z, p<? super ApplicationCall, ? super d<? super t0>, ? extends Object> pVar) {
                super(1);
                this.$autoHead = z;
                this.$handler = pVar;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Route) obj);
                return t0.f22605a;
            }

            public final void invoke(Route route) {
                RoutingBuilderKt.get(route, new C02391(this.$handler, null));
                if (this.$autoHead) {
                    RoutingBuilderKt.method(route, HttpMethod.INSTANCE.getHead(), new AnonymousClass2(this.$handler));
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C18991(boolean z, p<? super ApplicationCall, ? super d<? super t0>, ? extends Object> pVar) {
            super(1);
            this.$autoHead = z;
            this.$handler = pVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            RoutingBuilderKt.route(route, "{static-content-path-parameter...}", new C02381(this.$autoHead, this.$handler));
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$staticFiles$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/http/content/StaticContentConfig;", "Ljava/io/File;", "Lx6/t0;", "invoke", "(Lio/ktor/server/http/content/StaticContentConfig;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19001 extends r implements l<StaticContentConfig<File>, t0> {
        public static final C19001 INSTANCE = new C19001();

        public C19001() {
            super(1);
        }

        public final void invoke(StaticContentConfig<File> staticContentConfig) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((StaticContentConfig<File>) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$staticFiles$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/application/ApplicationCall;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/server/application/ApplicationCall;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.http.content.StaticContentKt$staticFiles$2", f = "StaticContent.kt", l = {174}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<ApplicationCall, d<? super t0>, Object> {
        final /* synthetic */ l<File, List<CacheControl>> $cacheControl;
        final /* synthetic */ List<CompressedFileType> $compressedTypes;
        final /* synthetic */ l<File, ContentType> $contentType;
        final /* synthetic */ String $defaultPath;
        final /* synthetic */ File $dir;
        final /* synthetic */ l<File, Boolean> $exclude;
        final /* synthetic */ List<String> $extensions;
        final /* synthetic */ String $index;
        final /* synthetic */ q<File, ApplicationCall, d<? super t0>, Object> $modify;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(String str, File file, List<? extends CompressedFileType> list, l<? super File, ContentType> lVar, l<? super File, ? extends List<? extends CacheControl>> lVar2, q<? super File, ? super ApplicationCall, ? super d<? super t0>, ? extends Object> qVar, l<? super File, Boolean> lVar3, List<String> list2, String str2, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$index = str;
            this.$dir = file;
            this.$compressedTypes = list;
            this.$contentType = lVar;
            this.$cacheControl = lVar2;
            this.$modify = qVar;
            this.$exclude = lVar3;
            this.$extensions = list2;
            this.$defaultPath = str2;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$index, this.$dir, this.$compressedTypes, this.$contentType, this.$cacheControl, this.$modify, this.$exclude, this.$extensions, this.$defaultPath, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(ApplicationCall applicationCall, d<? super t0> dVar) {
            return ((AnonymousClass2) create(applicationCall, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                ApplicationCall applicationCall = (ApplicationCall) this.L$0;
                String str = this.$index;
                File file = this.$dir;
                List<CompressedFileType> list = this.$compressedTypes;
                l<File, ContentType> lVar = this.$contentType;
                l<File, List<CacheControl>> lVar2 = this.$cacheControl;
                q<File, ApplicationCall, d<? super t0>, Object> qVar = this.$modify;
                l<File, Boolean> lVar3 = this.$exclude;
                List<String> list2 = this.$extensions;
                String str2 = this.$defaultPath;
                this.label = 1;
                Object objRespondStaticFile = StaticContentKt.respondStaticFile(applicationCall, str, file, list, lVar, lVar2, qVar, lVar3, list2, str2, this);
                a aVar = a.f15033i;
                if (objRespondStaticFile == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$staticResources$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/http/content/StaticContentConfig;", "Ljava/net/URL;", "Lx6/t0;", "invoke", "(Lio/ktor/server/http/content/StaticContentConfig;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19011 extends r implements l<StaticContentConfig<URL>, t0> {
        public static final C19011 INSTANCE = new C19011();

        public C19011() {
            super(1);
        }

        public final void invoke(StaticContentConfig<URL> staticContentConfig) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((StaticContentConfig<URL>) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$staticResources$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/application/ApplicationCall;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/server/application/ApplicationCall;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.http.content.StaticContentKt$staticResources$2", f = "StaticContent.kt", l = {214}, m = "invokeSuspend")
    public static final class C19022 extends j implements p<ApplicationCall, d<? super t0>, Object> {
        final /* synthetic */ String $basePackage;
        final /* synthetic */ l<URL, List<CacheControl>> $cacheControl;
        final /* synthetic */ List<CompressedFileType> $compressedTypes;
        final /* synthetic */ l<URL, ContentType> $contentType;
        final /* synthetic */ String $defaultPath;
        final /* synthetic */ l<URL, Boolean> $exclude;
        final /* synthetic */ List<String> $extensions;
        final /* synthetic */ String $index;
        final /* synthetic */ q<URL, ApplicationCall, d<? super t0>, Object> $modifier;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19022(String str, String str2, List<? extends CompressedFileType> list, l<? super URL, ContentType> lVar, l<? super URL, ? extends List<? extends CacheControl>> lVar2, q<? super URL, ? super ApplicationCall, ? super d<? super t0>, ? extends Object> qVar, l<? super URL, Boolean> lVar3, List<String> list2, String str3, d<? super C19022> dVar) {
            super(2, dVar);
            this.$index = str;
            this.$basePackage = str2;
            this.$compressedTypes = list;
            this.$contentType = lVar;
            this.$cacheControl = lVar2;
            this.$modifier = qVar;
            this.$exclude = lVar3;
            this.$extensions = list2;
            this.$defaultPath = str3;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C19022 c19022 = new C19022(this.$index, this.$basePackage, this.$compressedTypes, this.$contentType, this.$cacheControl, this.$modifier, this.$exclude, this.$extensions, this.$defaultPath, dVar);
            c19022.L$0 = obj;
            return c19022;
        }

        @Override // r7.p
        public final Object invoke(ApplicationCall applicationCall, d<? super t0> dVar) {
            return ((C19022) create(applicationCall, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                ApplicationCall applicationCall = (ApplicationCall) this.L$0;
                String str = this.$index;
                String str2 = this.$basePackage;
                List<CompressedFileType> list = this.$compressedTypes;
                l<URL, ContentType> lVar = this.$contentType;
                l<URL, List<CacheControl>> lVar2 = this.$cacheControl;
                q<URL, ApplicationCall, d<? super t0>, Object> qVar = this.$modifier;
                l<URL, Boolean> lVar3 = this.$exclude;
                List<String> list2 = this.$extensions;
                String str3 = this.$defaultPath;
                this.label = 1;
                Object objRespondStaticResource = StaticContentKt.respondStaticResource(applicationCall, str, str2, list, lVar, lVar2, qVar, lVar3, list2, str3, this);
                a aVar = a.f15033i;
                if (objRespondStaticResource == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    private static final File combine(File file, File file2) {
        return file == null ? file2 : i.t0(file, file2);
    }

    private static final String combinePackage(String str, String str2) {
        return str == null ? str2 : str2 == null ? str : androidx.compose.foundation.c.m('.', str, str2);
    }

    @x6.e
    /* JADX INFO: renamed from: default, reason: not valid java name */
    public static final void m6715default(Route route, String str) {
        m6714default(route, new File(str));
    }

    @x6.e
    public static final void defaultResource(Route route, String str, String str2) {
        RoutingBuilderKt.get(route, new C18921(str, combinePackage(getStaticBasePackage(route), str2), PreCompressedKt.getStaticContentEncodedTypes(route), null));
    }

    public static /* synthetic */ void defaultResource$default(Route route, String str, String str2, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        defaultResource(route, str, str2);
    }

    @x6.e
    public static final void file(Route route, String str, String str2) {
        file(route, str, new File(str2));
    }

    public static /* synthetic */ void file$default(Route route, String str, String str2, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = str;
        }
        file(route, str, str2);
    }

    @x6.e
    public static final void files(Route route, String str) {
        files(route, new File(str));
    }

    public static final String getStaticBasePackage(Route route) {
        String str = (String) route.getAttributes().getOrNull(staticBasePackageName);
        if (str != null) {
            return str;
        }
        Route parent = route.getParent();
        if (parent != null) {
            return getStaticBasePackage(parent);
        }
        return null;
    }

    @x6.e
    public static /* synthetic */ void getStaticBasePackage$annotations(Route route) {
    }

    public static final File getStaticRootFolder(Route route) {
        File file = (File) route.getAttributes().getOrNull(staticRootFolderKey);
        if (file != null) {
            return file;
        }
        Route parent = route.getParent();
        if (parent != null) {
            return getStaticRootFolder(parent);
        }
        return null;
    }

    public static final boolean isStaticContent(ApplicationCall applicationCall) {
        return applicationCall.getParameters().contains(pathParameterName);
    }

    public static final void preCompressed(Route route, CompressedFileType[] compressedFileTypeArr, l<? super Route, t0> lVar) {
        Collection staticContentEncodedTypes = PreCompressedKt.getStaticContentEncodedTypes(route);
        if (staticContentEncodedTypes == null) {
            staticContentEncodedTypes = z.f19728i;
        }
        route.getAttributes().put(PreCompressedKt.getCompressedKey(), x.g0(x.I0(staticContentEncodedTypes, Arrays.asList(compressedFileTypeArr))));
        lVar.invoke(route);
        route.getAttributes().remove(PreCompressedKt.getCompressedKey());
    }

    public static /* synthetic */ void preCompressed$default(Route route, CompressedFileType[] compressedFileTypeArr, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            compressedFileTypeArr = CompressedFileType.values();
        }
        preCompressed(route, compressedFileTypeArr, lVar);
    }

    @x6.e
    public static final void resource(Route route, String str, String str2, String str3) {
        RoutingBuilderKt.get(route, str, new C18951(str2, combinePackage(getStaticBasePackage(route), str3), PreCompressedKt.getStaticContentEncodedTypes(route), null));
    }

    public static /* synthetic */ void resource$default(Route route, String str, String str2, String str3, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = str;
        }
        if ((i10 & 4) != 0) {
            str3 = null;
        }
        resource(route, str, str2, str3);
    }

    @x6.e
    public static final void resources(Route route, String str) {
        RoutingBuilderKt.get(route, "{static-content-path-parameter...}", new C18961(combinePackage(getStaticBasePackage(route), str), PreCompressedKt.getStaticContentEncodedTypes(route), null));
    }

    public static /* synthetic */ void resources$default(Route route, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        resources(route, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02c4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:76:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x0298 -> B:61:0x02a2). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object respondStaticFile(io.ktor.server.application.ApplicationCall r22, java.lang.String r23, java.io.File r24, java.util.List<? extends io.ktor.server.http.content.CompressedFileType> r25, r7.l<? super java.io.File, io.ktor.http.ContentType> r26, r7.l<? super java.io.File, ? extends java.util.List<? extends io.ktor.http.CacheControl>> r27, r7.q<? super java.io.File, ? super io.ktor.server.application.ApplicationCall, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r28, r7.l<? super java.io.File, java.lang.Boolean> r29, java.util.List<java.lang.String> r30, java.lang.String r31, d7.d<? super x6.t0> r32) {
        /*
            Method dump skipped, instruction units count: 780
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.http.content.StaticContentKt.respondStaticFile(io.ktor.server.application.ApplicationCall, java.lang.String, java.io.File, java.util.List, r7.l, r7.l, r7.q, r7.l, java.util.List, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object respondStaticFile$checkExclude(r7.l<? super java.io.File, java.lang.Boolean> r5, io.ktor.server.application.ApplicationCall r6, java.io.File r7, d7.d<? super java.lang.Boolean> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.server.http.content.StaticContentKt$respondStaticFile$checkExclude$1
            if (r0 == 0) goto L13
            r0 = r8
            io.ktor.server.http.content.StaticContentKt$respondStaticFile$checkExclude$1 r0 = (io.ktor.server.http.content.StaticContentKt$respondStaticFile$checkExclude$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.server.http.content.StaticContentKt$respondStaticFile$checkExclude$1 r0 = new io.ktor.server.http.content.StaticContentKt$respondStaticFile$checkExclude$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r8)
            goto L77
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2d:
            k2.c.G(r8)
            java.lang.Object r5 = r5.invoke(r7)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 != 0) goto L3f
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
            return r5
        L3f:
            io.ktor.http.HttpStatusCode$Companion r5 = io.ktor.http.HttpStatusCode.INSTANCE
            io.ktor.http.HttpStatusCode r5 = r5.getForbidden()
            boolean r7 = r5 instanceof byte[]
            if (r7 != 0) goto L64
            io.ktor.server.response.ApplicationResponse r7 = r6.getResponse()
            java.lang.Class<io.ktor.http.HttpStatusCode> r8 = io.ktor.http.HttpStatusCode.class
            kotlin.reflect.q r1 = kotlin.jvm.internal.l0.a(r8)
            java.lang.reflect.Type r3 = kotlin.reflect.b0.t(r1)
            kotlin.jvm.internal.m0 r4 = kotlin.jvm.internal.l0.f19747a
            kotlin.reflect.d r8 = r4.b(r8)
            io.ktor.util.reflect.TypeInfo r8 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r3, r8, r1)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r7, r8)
        L64:
            io.ktor.server.response.ApplicationResponse r7 = r6.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r7 = r7.getPipeline()
            r0.label = r2
            java.lang.Object r5 = r7.execute(r6, r5, r0)
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L77
            return r6
        L77:
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.http.content.StaticContentKt.respondStaticFile$checkExclude(r7.l, io.ktor.server.application.ApplicationCall, java.io.File, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x019c, code lost:
    
        if (io.ktor.server.application.ApplicationCallKt.isHandled(r3) == false) goto L35;
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0206 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x0195 -> B:41:0x0198). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object respondStaticResource(io.ktor.server.application.ApplicationCall r27, java.lang.String r28, java.lang.String r29, java.util.List<? extends io.ktor.server.http.content.CompressedFileType> r30, r7.l<? super java.net.URL, io.ktor.http.ContentType> r31, r7.l<? super java.net.URL, ? extends java.util.List<? extends io.ktor.http.CacheControl>> r32, r7.q<? super java.net.URL, ? super io.ktor.server.application.ApplicationCall, ? super d7.d<? super x6.t0>, ? extends java.lang.Object> r33, r7.l<? super java.net.URL, java.lang.Boolean> r34, java.util.List<java.lang.String> r35, java.lang.String r36, d7.d<? super x6.t0> r37) {
        /*
            Method dump skipped, instruction units count: 563
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.http.content.StaticContentKt.respondStaticResource(io.ktor.server.application.ApplicationCall, java.lang.String, java.lang.String, java.util.List, r7.l, r7.l, r7.q, r7.l, java.util.List, java.lang.String, d7.d):java.lang.Object");
    }

    public static final void setStaticBasePackage(Route route, String str) {
        if (str != null) {
            route.getAttributes().put(staticBasePackageName, str);
        } else {
            route.getAttributes().remove(staticBasePackageName);
        }
    }

    public static final void setStaticRootFolder(Route route, File file) {
        if (file != null) {
            route.getAttributes().put(staticRootFolderKey, file);
        } else {
            route.getAttributes().remove(staticRootFolderKey);
        }
    }

    @x6.e
    /* JADX INFO: renamed from: static, reason: not valid java name */
    public static final Route m6717static(Route route, l<? super Route, t0> lVar) {
        lVar.invoke(route);
        return route;
    }

    private static final Route staticContentRoute(Route route, String str, boolean z, p<? super ApplicationCall, ? super d<? super t0>, ? extends Object> pVar) {
        return RoutingBuilderKt.route(route, str, new C18991(z, pVar));
    }

    public static final Route staticFiles(Route route, String str, File file, String str2, l<? super StaticContentConfig<File>, t0> lVar) {
        StaticContentConfig staticContentConfig = new StaticContentConfig();
        lVar.invoke(staticContentConfig);
        return staticContentRoute(route, str, staticContentConfig.getAutoHeadResponse(), new AnonymousClass2(str2, file, staticContentConfig.getPreCompressedFileTypes$ktor_server_core(), staticContentConfig.getContentType$ktor_server_core(), staticContentConfig.getCacheControl$ktor_server_core(), staticContentConfig.getModifier$ktor_server_core(), staticContentConfig.getExclude$ktor_server_core(), staticContentConfig.getExtensions$ktor_server_core(), staticContentConfig.getDefaultPath(), null));
    }

    public static /* synthetic */ Route staticFiles$default(Route route, String str, File file, String str2, l lVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            str2 = "index.html";
        }
        if ((i10 & 8) != 0) {
            lVar = C19001.INSTANCE;
        }
        return staticFiles(route, str, file, str2, lVar);
    }

    public static final Route staticResources(Route route, String str, String str2, String str3, l<? super StaticContentConfig<URL>, t0> lVar) {
        StaticContentConfig staticContentConfig = new StaticContentConfig();
        lVar.invoke(staticContentConfig);
        return staticContentRoute(route, str, staticContentConfig.getAutoHeadResponse(), new C19022(str3, str2, staticContentConfig.getPreCompressedFileTypes$ktor_server_core(), staticContentConfig.getContentType$ktor_server_core(), staticContentConfig.getCacheControl$ktor_server_core(), staticContentConfig.getModifier$ktor_server_core(), staticContentConfig.getExclude$ktor_server_core(), staticContentConfig.getExtensions$ktor_server_core(), staticContentConfig.getDefaultPath(), null));
    }

    public static /* synthetic */ Route staticResources$default(Route route, String str, String str2, String str3, l lVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            str3 = "index.html";
        }
        if ((i10 & 8) != 0) {
            lVar = C19011.INSTANCE;
        }
        return staticResources(route, str, str2, str3, lVar);
    }

    @x6.e
    /* JADX INFO: renamed from: default, reason: not valid java name */
    public static final void m6714default(Route route, File file) {
        RoutingBuilderKt.get(route, new AnonymousClass1(combine(getStaticRootFolder(route), file), PreCompressedKt.getStaticContentEncodedTypes(route), null));
    }

    @x6.e
    public static final void file(Route route, String str, File file) {
        RoutingBuilderKt.get(route, str, new C18931(combine(getStaticRootFolder(route), file), PreCompressedKt.getStaticContentEncodedTypes(route), null));
    }

    @x6.e
    public static final void files(Route route, File file) {
        RoutingBuilderKt.get(route, "{static-content-path-parameter...}", new C18941(combine(getStaticRootFolder(route), file), PreCompressedKt.getStaticContentEncodedTypes(route), null));
    }

    @x6.e
    /* JADX INFO: renamed from: static, reason: not valid java name */
    public static final Route m6716static(Route route, String str, l<? super Route, t0> lVar) {
        return RoutingBuilderKt.route(route, str, lVar);
    }
}
