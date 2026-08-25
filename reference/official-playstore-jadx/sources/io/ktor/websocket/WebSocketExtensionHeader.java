package io.ktor.websocket;

import ga.m;
import ga.p;
import ga.t;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import qb.d;
import r7.l;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\tR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\tR\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lio/ktor/websocket/WebSocketExtensionHeader;", "", "", ContentDisposition.Parameters.Name, "", "parameters", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "parametersToString", "()Ljava/lang/String;", "Lga/m;", "Lx6/x;", "parseParameters", "()Lga/m;", "toString", "Ljava/lang/String;", "getName", "Ljava/util/List;", "getParameters", "()Ljava/util/List;", "ktor-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class WebSocketExtensionHeader {
    private final String name;
    private final List<String> parameters;

    /* JADX INFO: renamed from: io.ktor.websocket.WebSocketExtensionHeader$parseParameters$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/x;", "invoke", "(Ljava/lang/String;)Lx6/x;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<String, x> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final x invoke(String str) {
            int iE0 = o.e0(str, '=', 0, false, 6);
            if (iE0 < 0) {
                return new x(str, "");
            }
            String strB0 = o.B0(str, d.X(0, iE0));
            int i10 = iE0 + 1;
            return new x(strB0, i10 < str.length() ? str.substring(i10) : "");
        }
    }

    public WebSocketExtensionHeader(String str, List<String> list) {
        this.name = str;
        this.parameters = list;
    }

    private final String parametersToString() {
        if (this.parameters.isEmpty()) {
            return "";
        }
        return ", " + kotlin.collections.x.u0(this.parameters, ",", null, null, null, 62);
    }

    public final String getName() {
        return this.name;
    }

    public final List<String> getParameters() {
        return this.parameters;
    }

    public final m<x> parseParameters() {
        return new t(new p(this.parameters, 3), AnonymousClass1.INSTANCE);
    }

    public String toString() {
        return this.name + ' ' + parametersToString();
    }
}
