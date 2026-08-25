package io.ktor.server.routing;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaError;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.HttpMethod;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.KtorDsl;
import io.ktor.util.pipeline.PipelineContext;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import kotlin.text.m;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0011\u001a4\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b\u0007\u0010\b\u001a<\u0010\u0007\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\t2\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b\u0007\u0010\u000b\u001aY\u0010\u0012\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000129\u0010\u0011\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001aY\u0010\u0014\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000129\u0010\u0011\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0013\u001ah\u0010\u0014\u001a\u00020\u0000\"\n\b\u0000\u0010\u0015\u0018\u0001*\u00020\u0010*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012;\b\u0004\u0010\u0011\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0013\u001aY\u0010\u0017\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000129\u0010\u0011\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0013\u001aY\u0010\u0018\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000129\u0010\u0011\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0013\u001ah\u0010\u0018\u001a\u00020\u0000\"\n\b\u0000\u0010\u0015\u0018\u0001*\u00020\u0010*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012;\b\u0004\u0010\u0011\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0013\u001aY\u0010\u001a\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000129\u0010\u0011\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0013\u001ah\u0010\u001a\u001a\u00020\u0000\"\n\b\u0000\u0010\u0015\u0018\u0001*\u00020\u0010*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012;\b\u0004\u0010\u0011\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0005H\u0087\bø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u0013\u001aY\u0010\u001c\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000129\u0010\u0011\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u0013\u001aY\u0010\u001d\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000129\u0010\u0011\u001a5\b\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f¢\u0006\u0002\b\u0005H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0013\u001a\u001b\u0010\u001f\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0001H\u0002¢\u0006\u0004\b\u001f\u0010 \u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lio/ktor/server/routing/Route;", "Lkotlin/text/m;", "path", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "build", "route", "(Lio/ktor/server/routing/Route;Lkotlin/text/m;Lr7/l;)Lio/ktor/server/routing/Route;", "Lio/ktor/http/HttpMethod;", "method", "(Lio/ktor/server/routing/Route;Lkotlin/text/m;Lio/ktor/http/HttpMethod;Lr7/l;)Lio/ktor/server/routing/Route;", "Lkotlin/Function3;", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "Ld7/d;", "", TtmlNode.TAG_BODY, "get", "(Lio/ktor/server/routing/Route;Lkotlin/text/m;Lr7/q;)Lio/ktor/server/routing/Route;", "post", "R", "postTypedPath", TtmlNode.TAG_HEAD, "put", "putTypedPath", "patch", "patchTypedPath", "delete", "options", "regex", "createRouteFromRegexPath", "(Lio/ktor/server/routing/Route;Lkotlin/text/m;)Lio/ktor/server/routing/Route;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RegexRoutingKt {

    /* JADX INFO: renamed from: io.ktor.server.routing.RegexRoutingKt$delete$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RegexRoutingKt$get$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19131 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19131(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RegexRoutingKt$head$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19141 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19141(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RegexRoutingKt$options$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19151 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19151(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RegexRoutingKt$patch$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19161 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19161(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RegexRoutingKt$patch$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "R", "Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.routing.RegexRoutingKt$patch$2", f = "RegexRouting.kt", l = {287, 197}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, R, d<? super t0>, Object> $body;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar, d<? super AnonymousClass2> dVar) {
            super(3, dVar);
            this.$body = qVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$body, dVar);
            anonymousClass2.L$0 = pipelineContext;
            return anonymousClass2.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                p.h();
                throw null;
            }
            if (i10 == 1) {
                PipelineContext pipelineContext = (PipelineContext) this.L$1;
                q qVar = (q) this.L$0;
                c.G(obj);
                if (obj == null) {
                    p.h();
                    throw null;
                }
                this.L$0 = null;
                this.L$1 = null;
                this.label = 2;
                Object objInvoke = qVar.invoke(pipelineContext, obj, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        public final Object invokeSuspend$$forInline(Object obj) {
            p.h();
            throw null;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RegexRoutingKt$post$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19171 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19171(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RegexRoutingKt$post$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "R", "Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.routing.RegexRoutingKt$post$2", f = "RegexRouting.kt", l = {287, MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED}, m = "invokeSuspend")
    public static final class C19182 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, R, d<? super t0>, Object> $body;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19182(q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar, d<? super C19182> dVar) {
            super(3, dVar);
            this.$body = qVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            C19182 c19182 = new C19182(this.$body, dVar);
            c19182.L$0 = pipelineContext;
            return c19182.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                p.h();
                throw null;
            }
            if (i10 == 1) {
                PipelineContext pipelineContext = (PipelineContext) this.L$1;
                q qVar = (q) this.L$0;
                c.G(obj);
                if (obj == null) {
                    p.h();
                    throw null;
                }
                this.L$0 = null;
                this.L$1 = null;
                this.label = 2;
                Object objInvoke = qVar.invoke(pipelineContext, obj, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        public final Object invokeSuspend$$forInline(Object obj) {
            p.h();
            throw null;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RegexRoutingKt$put$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/routing/Route;", "Lx6/t0;", "invoke", "(Lio/ktor/server/routing/Route;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C19191 extends r implements l<Route, t0> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> $body;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19191(q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
            super(1);
            this.$body = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Route) obj);
            return t0.f22605a;
        }

        public final void invoke(Route route) {
            route.handle(this.$body);
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.routing.RegexRoutingKt$put$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "R", "Lio/ktor/util/pipeline/PipelineContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "it", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;V)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.routing.RegexRoutingKt$put$2", f = "RegexRouting.kt", l = {287, 159}, m = "invokeSuspend")
    public static final class C19202 extends j implements q<PipelineContext<t0, ApplicationCall>, t0, d<? super t0>, Object> {
        final /* synthetic */ q<PipelineContext<t0, ApplicationCall>, R, d<? super t0>, Object> $body;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C19202(q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar, d<? super C19202> dVar) {
            super(3, dVar);
            this.$body = qVar;
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<t0, ApplicationCall> pipelineContext, t0 t0Var, d<? super t0> dVar) {
            C19202 c19202 = new C19202(this.$body, dVar);
            c19202.L$0 = pipelineContext;
            return c19202.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                p.h();
                throw null;
            }
            if (i10 == 1) {
                PipelineContext pipelineContext = (PipelineContext) this.L$1;
                q qVar = (q) this.L$0;
                c.G(obj);
                if (obj == null) {
                    p.h();
                    throw null;
                }
                this.L$0 = null;
                this.L$1 = null;
                this.label = 2;
                Object objInvoke = qVar.invoke(pipelineContext, obj, this);
                a aVar = a.f15033i;
                if (objInvoke == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }

        public final Object invokeSuspend$$forInline(Object obj) {
            p.h();
            throw null;
        }
    }

    private static final Route createRouteFromRegexPath(Route route, m mVar) {
        return route.createChild(new PathSegmentRegexRouteSelector(mVar));
    }

    @KtorDsl
    public static final Route delete(Route route, m mVar, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return route(route, mVar, HttpMethod.INSTANCE.getDelete(), new AnonymousClass1(qVar));
    }

    @KtorDsl
    public static final Route get(Route route, m mVar, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return route(route, mVar, HttpMethod.INSTANCE.getGet(), new C19131(qVar));
    }

    @KtorDsl
    public static final Route head(Route route, m mVar, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return route(route, mVar, HttpMethod.INSTANCE.getHead(), new C19141(qVar));
    }

    @KtorDsl
    public static final Route options(Route route, m mVar, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return route(route, mVar, HttpMethod.INSTANCE.getOptions(), new C19151(qVar));
    }

    @KtorDsl
    public static final Route patch(Route route, m mVar, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return route(route, mVar, HttpMethod.INSTANCE.getPatch(), new C19161(qVar));
    }

    @KtorDsl
    public static final <R> Route patchTypedPath(Route route, m mVar, q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar) {
        p.h();
        throw null;
    }

    @KtorDsl
    public static final Route post(Route route, m mVar, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return route(route, mVar, HttpMethod.INSTANCE.getPost(), new C19171(qVar));
    }

    @KtorDsl
    public static final <R> Route postTypedPath(Route route, m mVar, q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar) {
        p.h();
        throw null;
    }

    @KtorDsl
    public static final Route put(Route route, m mVar, q<? super PipelineContext<t0, ApplicationCall>, ? super t0, ? super d<? super t0>, ? extends Object> qVar) {
        return route(route, mVar, HttpMethod.INSTANCE.getPut(), new C19191(qVar));
    }

    @KtorDsl
    public static final <R> Route putTypedPath(Route route, m mVar, q<? super PipelineContext<t0, ApplicationCall>, ? super R, ? super d<? super t0>, ? extends Object> qVar) {
        p.h();
        throw null;
    }

    @KtorDsl
    public static final Route route(Route route, m mVar, l<? super Route, t0> lVar) {
        Route routeCreateRouteFromRegexPath = createRouteFromRegexPath(route, mVar);
        lVar.invoke(routeCreateRouteFromRegexPath);
        return routeCreateRouteFromRegexPath;
    }

    @KtorDsl
    public static final Route route(Route route, m mVar, HttpMethod httpMethod, l<? super Route, t0> lVar) {
        Route routeCreateChild = createRouteFromRegexPath(route, mVar).createChild(new HttpMethodRouteSelector(httpMethod));
        lVar.invoke(routeCreateChild);
        return routeCreateChild;
    }
}
