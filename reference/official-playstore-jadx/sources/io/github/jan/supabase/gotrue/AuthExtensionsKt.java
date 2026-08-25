package io.github.jan.supabase.gotrue;

import f4.f;
import io.github.jan.supabase.gotrue.user.UserInfo;
import io.github.jan.supabase.gotrue.user.UserSession;
import io.ktor.http.LinkHeader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.i0;
import kotlin.collections.s;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import r7.a;
import wa.c;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003\u001a\u0012\u0010\b\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\t\u001a\u00020\u0003¨\u0006\n"}, d2 = {"noDeeplinkError", "", "arg", "", "parseSessionFromFragment", "Lio/github/jan/supabase/gotrue/user/UserSession;", "Lio/github/jan/supabase/gotrue/Auth;", "fragment", "parseSessionFromUrl", "url", "gotrue-kt_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class AuthExtensionsKt {

    /* JADX INFO: renamed from: io.github.jan.supabase.gotrue.AuthExtensionsKt$parseSessionFromFragment$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements a<String> {
        final /* synthetic */ Map<String, String> $sessionParts;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Map<String, String> map) {
            super(0);
            this.$sessionParts = map;
        }

        @Override // r7.a
        public final String invoke() {
            return "Fragment parts: " + this.$sessionParts;
        }
    }

    public static final Void noDeeplinkError(String str) {
        throw new IllegalStateException(f.y("\n        Trying to use a deeplink as a redirect url, but no deeplink " + str + " is set in the GoTrueConfig.\n        If you want to use deep linking, set the scheme and host in the GoTrueConfig:\n        install(GoTrue) {\n            scheme = \"YOUR_SCHEME\"\n            host = \"YOUR_HOST\"\n        }\n        You can also provide a custom redirect url.\n    ").toString());
    }

    public static final UserSession parseSessionFromFragment(Auth auth, String str) {
        List listY0 = o.y0(str, new String[]{"&"}, 0, 6);
        int iQ0 = i0.q0(s.U(listY0, 10));
        if (iQ0 < 16) {
            iQ0 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
        Iterator it = listY0.iterator();
        while (it.hasNext()) {
            List listY02 = o.y0((String) it.next(), new String[]{"="}, 0, 6);
            linkedHashMap.put(listY02.get(0), listY02.get(1));
        }
        q2.f.A0(q2.f.f21327l, "Auth", new AnonymousClass1(linkedHashMap));
        String str2 = (String) linkedHashMap.get("access_token");
        if (str2 == null) {
            UtilsKt.invalidArg("No access token found");
            throw new KotlinNothingValueException();
        }
        String str3 = (String) linkedHashMap.get("refresh_token");
        if (str3 == null) {
            UtilsKt.invalidArg("No refresh token found");
            throw new KotlinNothingValueException();
        }
        String str4 = (String) linkedHashMap.get("expires_in");
        if (str4 == null) {
            UtilsKt.invalidArg("No expires in found");
            throw new KotlinNothingValueException();
        }
        long j10 = Long.parseLong(str4);
        String str5 = (String) linkedHashMap.get("token_type");
        if (str5 == null) {
            UtilsKt.invalidArg("No token type found");
            throw new KotlinNothingValueException();
        }
        String str6 = (String) linkedHashMap.get(LinkHeader.Parameters.Type);
        if (str6 == null) {
            str6 = "";
        }
        return new UserSession(str2, str3, (String) linkedHashMap.get("provider_refresh_token"), (String) linkedHashMap.get("provider_token"), j10, str5, (UserInfo) null, str6, (c) null, 256, (h) null);
    }

    public static final UserSession parseSessionFromUrl(Auth auth, String str) {
        return parseSessionFromFragment(auth, o.D0(str, "#", str));
    }
}
