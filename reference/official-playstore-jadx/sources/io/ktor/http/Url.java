package io.ktor.http;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import x6.i0;
import x6.s;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b5\u0018\u0000 C2\u00020\u0001:\u0001CBc\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010\u0015R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010 \u001a\u0004\b!\u0010\u001aR\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010%\u001a\u0004\b&\u0010'R\u0017\u0010\f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\f\u0010\u001e\u001a\u0004\b(\u0010\u0015R\u0019\u0010\r\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u001e\u001a\u0004\b)\u0010\u0015R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u001e\u001a\u0004\b*\u0010\u0015R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010+\u001a\u0004\b,\u0010-R\u0014\u0010\u0011\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u001eR\u001b\u00101\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u0010\u0015R\u001b\u00104\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b2\u0010/\u001a\u0004\b3\u0010\u0015R\u001b\u00107\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b5\u0010/\u001a\u0004\b6\u0010\u0015R\u001d\u0010:\u001a\u0004\u0018\u00010\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b8\u0010/\u001a\u0004\b9\u0010\u0015R\u001d\u0010=\u001a\u0004\u0018\u00010\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b;\u0010/\u001a\u0004\b<\u0010\u0015R\u001b\u0010@\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b>\u0010/\u001a\u0004\b?\u0010\u0015R\u0011\u0010B\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\bA\u0010\u001a¨\u0006D"}, d2 = {"Lio/ktor/http/Url;", "", "Lio/ktor/http/URLProtocol;", "protocol", "", "host", "", "specifiedPort", "", "pathSegments", "Lio/ktor/http/Parameters;", "parameters", "fragment", "user", "password", "", "trailingQuery", "urlString", "<init>", "(Lio/ktor/http/URLProtocol;Ljava/lang/String;ILjava/util/List;Lio/ktor/http/Parameters;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "toString", "()Ljava/lang/String;", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "Lio/ktor/http/URLProtocol;", "getProtocol", "()Lio/ktor/http/URLProtocol;", "Ljava/lang/String;", "getHost", "I", "getSpecifiedPort", "Ljava/util/List;", "getPathSegments", "()Ljava/util/List;", "Lio/ktor/http/Parameters;", "getParameters", "()Lio/ktor/http/Parameters;", "getFragment", "getUser", "getPassword", "Z", "getTrailingQuery", "()Z", "encodedPath$delegate", "Lx6/s;", "getEncodedPath", "encodedPath", "encodedQuery$delegate", "getEncodedQuery", "encodedQuery", "encodedPathAndQuery$delegate", "getEncodedPathAndQuery", "encodedPathAndQuery", "encodedUser$delegate", "getEncodedUser", "encodedUser", "encodedPassword$delegate", "getEncodedPassword", "encodedPassword", "encodedFragment$delegate", "getEncodedFragment", "encodedFragment", "getPort", "port", "Companion", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Url {

    /* JADX INFO: renamed from: encodedFragment$delegate, reason: from kotlin metadata */
    private final s encodedFragment;

    /* JADX INFO: renamed from: encodedPassword$delegate, reason: from kotlin metadata */
    private final s encodedPassword;

    /* JADX INFO: renamed from: encodedPath$delegate, reason: from kotlin metadata */
    private final s encodedPath;

    /* JADX INFO: renamed from: encodedPathAndQuery$delegate, reason: from kotlin metadata */
    private final s encodedPathAndQuery;

    /* JADX INFO: renamed from: encodedQuery$delegate, reason: from kotlin metadata */
    private final s encodedQuery;

    /* JADX INFO: renamed from: encodedUser$delegate, reason: from kotlin metadata */
    private final s encodedUser;
    private final String fragment;
    private final String host;
    private final Parameters parameters;
    private final String password;
    private final List<String> pathSegments;
    private final URLProtocol protocol;
    private final int specifiedPort;
    private final boolean trailingQuery;
    private final String urlString;
    private final String user;

    public Url(URLProtocol uRLProtocol, String str, int i10, List<String> list, Parameters parameters, String str2, String str3, String str4, boolean z, String str5) {
        this.protocol = uRLProtocol;
        this.host = str;
        this.specifiedPort = i10;
        this.pathSegments = list;
        this.parameters = parameters;
        this.fragment = str2;
        this.user = str3;
        this.password = str4;
        this.trailingQuery = z;
        this.urlString = str5;
        if ((i10 < 0 || i10 >= 65536) && i10 != 0) {
            throw new IllegalArgumentException("port must be between 0 and 65535, or 0 if not set");
        }
        this.encodedPath = new i0(new Url$encodedPath$2(this));
        this.encodedQuery = new i0(new Url$encodedQuery$2(this));
        this.encodedPathAndQuery = new i0(new Url$encodedPathAndQuery$2(this));
        this.encodedUser = new i0(new Url$encodedUser$2(this));
        this.encodedPassword = new i0(new Url$encodedPassword$2(this));
        this.encodedFragment = new i0(new Url$encodedFragment$2(this));
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return other != null && Url.class == other.getClass() && p.a(this.urlString, ((Url) other).urlString);
    }

    public final String getEncodedFragment() {
        return (String) this.encodedFragment.getValue();
    }

    public final String getEncodedPassword() {
        return (String) this.encodedPassword.getValue();
    }

    public final String getEncodedPath() {
        return (String) this.encodedPath.getValue();
    }

    public final String getEncodedPathAndQuery() {
        return (String) this.encodedPathAndQuery.getValue();
    }

    public final String getEncodedQuery() {
        return (String) this.encodedQuery.getValue();
    }

    public final String getEncodedUser() {
        return (String) this.encodedUser.getValue();
    }

    public final String getFragment() {
        return this.fragment;
    }

    public final String getHost() {
        return this.host;
    }

    public final Parameters getParameters() {
        return this.parameters;
    }

    public final String getPassword() {
        return this.password;
    }

    public final List<String> getPathSegments() {
        return this.pathSegments;
    }

    public final int getPort() {
        Integer numValueOf = Integer.valueOf(this.specifiedPort);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        return numValueOf != null ? numValueOf.intValue() : this.protocol.getDefaultPort();
    }

    public final URLProtocol getProtocol() {
        return this.protocol;
    }

    public final int getSpecifiedPort() {
        return this.specifiedPort;
    }

    public final boolean getTrailingQuery() {
        return this.trailingQuery;
    }

    public final String getUser() {
        return this.user;
    }

    public int hashCode() {
        return this.urlString.hashCode();
    }

    /* JADX INFO: renamed from: toString, reason: from getter */
    public String getUrlString() {
        return this.urlString;
    }
}
