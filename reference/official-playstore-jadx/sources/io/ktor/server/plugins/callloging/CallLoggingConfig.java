package io.ktor.server.plugins.callloging;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpStatusCode;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.request.ApplicationRequest;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import io.ktor.util.KtorDsl;
import io.ktor.util.date.DateJvmKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import vc.b;
import zb.c;
import zb.e;
import zb.f;
import zb.i;
import zb.j;

/* JADX INFO: loaded from: classes4.dex */
@KtorDsl
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000b\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u000b\u0010\u0010J!\u0010\u0015\u001a\u00020\u00142\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0004\b\u0015\u0010\u0016J+\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00062\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0011¢\u0006\u0004\b\u0019\u0010\u001aJ!\u0010\u001c\u001a\u00020\u00142\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u0011¢\u0006\u0004\b\u001c\u0010\u0016J\u001b\u0010\u001f\u001a\u00020\u00142\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d¢\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\u0014¢\u0006\u0004\b!\u0010\u0003J\r\u0010\"\u001a\u00020\u0014¢\u0006\u0004\b\"\u0010\u0003J\u0013\u0010&\u001a\u00020\u0006*\u00020#H\u0000¢\u0006\u0004\b$\u0010%R(\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010 R,\u0010,\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u00110+8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R \u00101\u001a\b\u0012\u0004\u0012\u0002000+8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b1\u0010-\u001a\u0004\b2\u0010/R.\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u00118\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u0010\u0016R\"\u00108\u001a\u00020\u00128\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\"\u0010>\u001a\u00020\u00128\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b>\u00109\u001a\u0004\b?\u0010;\"\u0004\b@\u0010=R\"\u0010B\u001a\u00020A8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bB\u0010C\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR$\u0010I\u001a\u0004\u0018\u00010H8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bI\u0010J\u001a\u0004\bK\u0010L\"\u0004\bM\u0010N¨\u0006O"}, d2 = {"Lio/ktor/server/plugins/callloging/CallLoggingConfig;", "", "<init>", "()V", "Lio/ktor/server/application/ApplicationCall;", "call", "", "defaultFormat", "(Lio/ktor/server/application/ApplicationCall;)Ljava/lang/String;", "Lio/ktor/http/HttpStatusCode;", "status", "colored", "(Lio/ktor/http/HttpStatusCode;)Ljava/lang/String;", "value", "Lzb/c;", TtmlNode.ATTR_TTS_COLOR, "(Ljava/lang/Object;Lzb/c;)Ljava/lang/String;", "Lkotlin/Function1;", "", "predicate", "Lx6/t0;", "filter", "(Lr7/l;)V", ContentDisposition.Parameters.Name, "provider", "mdc", "(Ljava/lang/String;Lr7/l;)V", "formatter", "format", "Lkotlin/Function0;", "", "clock", "(Lr7/a;)V", "disableDefaultColors", "disableForStaticContent", "Lio/ktor/server/request/ApplicationRequest;", "toLogStringWithColors$ktor_server_call_logging", "(Lio/ktor/server/request/ApplicationRequest;)Ljava/lang/String;", "toLogStringWithColors", "Lr7/a;", "getClock$ktor_server_call_logging", "()Lr7/a;", "setClock$ktor_server_call_logging", "", "filters", "Ljava/util/List;", "getFilters$ktor_server_call_logging", "()Ljava/util/List;", "Lio/ktor/server/plugins/callloging/MDCEntry;", "mdcEntries", "getMdcEntries$ktor_server_call_logging", "formatCall", "Lr7/l;", "getFormatCall$ktor_server_call_logging", "()Lr7/l;", "setFormatCall$ktor_server_call_logging", "isColorsEnabled", "Z", "isColorsEnabled$ktor_server_call_logging", "()Z", "setColorsEnabled$ktor_server_call_logging", "(Z)V", "ignoreStaticContent", "getIgnoreStaticContent$ktor_server_call_logging", "setIgnoreStaticContent$ktor_server_call_logging", "Lwc/b;", "level", "Lwc/b;", "getLevel", "()Lwc/b;", "setLevel", "(Lwc/b;)V", "Lvc/b;", "logger", "Lvc/b;", "getLogger", "()Lvc/b;", "setLogger", "(Lvc/b;)V", "ktor-server-call-logging"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CallLoggingConfig {
    private boolean ignoreStaticContent;
    private b logger;
    private a<Long> clock = AnonymousClass1.INSTANCE;
    private final List<l<ApplicationCall, Boolean>> filters = new ArrayList();
    private final List<MDCEntry> mdcEntries = new ArrayList();
    private l<? super ApplicationCall, String> formatCall = new CallLoggingConfig$formatCall$1(this);
    private boolean isColorsEnabled = true;
    private wc.b level = wc.b.INFO;

    /* JADX INFO: renamed from: io.ktor.server.plugins.callloging.CallLoggingConfig$clock$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Long;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<Long> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public final Long invoke() {
            return Long.valueOf(DateJvmKt.getTimeMillis());
        }
    }

    private final String colored(HttpStatusCode status) {
        boolean z;
        try {
            if (this.isColorsEnabled) {
                j jVar = i.f23290a;
                synchronized (i.class) {
                    z = i.f23299j > 0;
                }
                if (!z) {
                    i.d();
                }
            }
        } catch (Throwable unused) {
            this.isColorsEnabled = false;
        }
        HttpStatusCode.Companion companion = HttpStatusCode.INSTANCE;
        if (p.a(status, companion.getFound()) ? true : p.a(status, companion.getOK()) ? true : p.a(status, companion.getAccepted()) ? true : p.a(status, companion.getCreated())) {
            return colored(status, c.GREEN);
        }
        return p.a(status, companion.getContinue()) ? true : p.a(status, companion.getProcessing()) ? true : p.a(status, companion.getPartialContent()) ? true : p.a(status, companion.getNotModified()) ? true : p.a(status, companion.getUseProxy()) ? true : p.a(status, companion.getUpgradeRequired()) ? true : p.a(status, companion.getNoContent()) ? colored(status, c.YELLOW) : colored(status, c.RED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String defaultFormat(ApplicationCall call) {
        HttpStatusCode httpStatusCodeStatus = call.getResponse().status();
        if (httpStatusCodeStatus == null) {
            httpStatusCodeStatus = "Unhandled";
        }
        if (httpStatusCodeStatus.equals(HttpStatusCode.INSTANCE.getFound())) {
            return colored(httpStatusCodeStatus) + ": " + toLogStringWithColors$ktor_server_call_logging(call.getRequest()) + " -> " + call.getResponse().getHeaders().get(HttpHeaders.INSTANCE.getLocation());
        }
        if (httpStatusCodeStatus.equals("Unhandled")) {
            return colored(httpStatusCodeStatus, c.RED) + ": " + toLogStringWithColors$ktor_server_call_logging(call.getRequest());
        }
        return colored(httpStatusCodeStatus) + ": " + toLogStringWithColors$ktor_server_call_logging(call.getRequest());
    }

    public final void clock(a<Long> clock) {
        this.clock = clock;
    }

    public final void disableDefaultColors() {
        this.isColorsEnabled = false;
    }

    public final void disableForStaticContent() {
        this.ignoreStaticContent = true;
    }

    public final void filter(l<? super ApplicationCall, Boolean> predicate) {
        this.filters.add(predicate);
    }

    public final void format(l<? super ApplicationCall, String> formatter) {
        this.formatCall = formatter;
    }

    public final a<Long> getClock$ktor_server_call_logging() {
        return this.clock;
    }

    public final List<l<ApplicationCall, Boolean>> getFilters$ktor_server_call_logging() {
        return this.filters;
    }

    public final l<ApplicationCall, String> getFormatCall$ktor_server_call_logging() {
        return this.formatCall;
    }

    /* JADX INFO: renamed from: getIgnoreStaticContent$ktor_server_call_logging, reason: from getter */
    public final boolean getIgnoreStaticContent() {
        return this.ignoreStaticContent;
    }

    public final wc.b getLevel() {
        return this.level;
    }

    public final b getLogger() {
        return this.logger;
    }

    public final List<MDCEntry> getMdcEntries$ktor_server_call_logging() {
        return this.mdcEntries;
    }

    /* JADX INFO: renamed from: isColorsEnabled$ktor_server_call_logging, reason: from getter */
    public final boolean getIsColorsEnabled() {
        return this.isColorsEnabled;
    }

    public final void mdc(String name, l<? super ApplicationCall, String> provider) {
        this.mdcEntries.add(new MDCEntry(name, provider));
    }

    public final void setClock$ktor_server_call_logging(a<Long> aVar) {
        this.clock = aVar;
    }

    public final void setColorsEnabled$ktor_server_call_logging(boolean z) {
        this.isColorsEnabled = z;
    }

    public final void setFormatCall$ktor_server_call_logging(l<? super ApplicationCall, String> lVar) {
        this.formatCall = lVar;
    }

    public final void setIgnoreStaticContent$ktor_server_call_logging(boolean z) {
        this.ignoreStaticContent = z;
    }

    public final void setLevel(wc.b bVar) {
        this.level = bVar;
    }

    public final void setLogger(b bVar) {
        this.logger = bVar;
    }

    public final String toLogStringWithColors$ktor_server_call_logging(ApplicationRequest applicationRequest) {
        return colored(ApplicationRequestPropertiesKt.getHttpMethod(applicationRequest).getValue(), c.CYAN) + " - " + ApplicationRequestPropertiesKt.path(applicationRequest) + " in " + CallLoggingKt.processingTimeMillis(applicationRequest.getCall(), this.clock) + "ms";
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final String colored(Object value, c color) {
        f eVar;
        if (this.isColorsEnabled) {
            if (((Boolean) f.f23284o.get()).booleanValue()) {
                eVar = new f();
            } else {
                eVar = new e();
            }
            f fVarB = eVar.b(color);
            fVarB.c();
            fVarB.f23285i.append(value);
            return fVarB.d().toString();
        }
        return value.toString();
    }
}
