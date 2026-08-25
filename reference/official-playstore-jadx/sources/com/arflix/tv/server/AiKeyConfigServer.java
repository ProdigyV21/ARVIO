package com.arflix.tv.server;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.common.MimeTypes;
import fi.iki.elonen.m;
import fi.iki.elonen.n;
import fi.iki.elonen.t;
import io.github.jan.supabase.gotrue.SettingsSessionManager;
import java.io.ByteArrayInputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.text.o;
import org.json.JSONObject;
import r7.a;
import r7.l;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0007\u0018\u0000 %2\u00020\u0001:\u0001%BE\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0012\b\u0002\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J+\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00142\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0019\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u001b\u0010\u001cR \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001dR\u001e\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u001eR\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u001f\u001a\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010\u001fR\u0011\u0010$\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b#\u0010!¨\u0006&"}, d2 = {"Lcom/arflix/tv/server/AiKeyConfigServer;", "Lfi/iki/elonen/t;", "Lkotlin/Function1;", "", "Lx6/t0;", "onKeyReceived", "Lkotlin/Function0;", "", "logoProvider", "", "port", "pairingToken", "<init>", "(Lr7/l;Lr7/a;ILjava/lang/String;)V", "html", "Lfi/iki/elonen/n;", "serveHtml", "(Ljava/lang/String;)Lfi/iki/elonen/n;", "serveLogo", "()Lfi/iki/elonen/n;", "Lfi/iki/elonen/h;", SettingsSessionManager.SETTINGS_KEY, "handleKeySubmit", "(Lfi/iki/elonen/h;Lr7/l;)Lfi/iki/elonen/n;", "length", "generateToken", "(I)Ljava/lang/String;", "serve", "(Lfi/iki/elonen/h;)Lfi/iki/elonen/n;", "Lr7/l;", "Lr7/a;", "Ljava/lang/String;", "getPairingToken", "()Ljava/lang/String;", "token", "getCurrentPairingToken", "currentPairingToken", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AiKeyConfigServer extends t {
    private final a<byte[]> logoProvider;
    private final l<String, t0> onKeyReceived;
    private final String pairingToken;
    private final String token;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JW\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0012\b\u0002\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/arflix/tv/server/AiKeyConfigServer$Companion;", "", "<init>", "()V", "Lkotlin/Function1;", "", "Lx6/t0;", "onKeyReceived", "Lkotlin/Function0;", "", "logoProvider", "", "startPort", "maxAttempts", "pairingToken", "Lcom/arflix/tv/server/AiKeyConfigServer;", "startOnAvailablePort", "(Lr7/l;Lr7/a;IILjava/lang/String;)Lcom/arflix/tv/server/AiKeyConfigServer;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AiKeyConfigServer startOnAvailablePort$default(Companion companion, l lVar, a aVar, int i10, int i11, String str, int i12, Object obj) {
            if ((i12 & 2) != 0) {
                aVar = null;
            }
            if ((i12 & 4) != 0) {
                i10 = 8095;
            }
            if ((i12 & 8) != 0) {
                i11 = 10;
            }
            if ((i12 & 16) != 0) {
                str = null;
            }
            return companion.startOnAvailablePort(lVar, aVar, i10, i11, str);
        }

        public final AiKeyConfigServer startOnAvailablePort(l<? super String, t0> onKeyReceived, a<byte[]> logoProvider, int startPort, int maxAttempts, String pairingToken) {
            int i10 = maxAttempts + startPort;
            while (startPort < i10) {
                try {
                    AiKeyConfigServer aiKeyConfigServer = new AiKeyConfigServer(onKeyReceived, logoProvider, startPort, pairingToken);
                    aiKeyConfigServer.start(5000, false);
                    return aiKeyConfigServer;
                } catch (Exception unused) {
                    startPort++;
                }
            }
            return null;
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AiKeyConfigServer(l<? super String, t0> lVar, a<byte[]> aVar, int i10, String str) {
        super(i10);
        this.onKeyReceived = lVar;
        this.logoProvider = aVar;
        this.pairingToken = str;
        this.token = str == null ? generateToken$default(this, 0, 1, null) : str;
    }

    private final String generateToken(int length) {
        SecureRandom secureRandom = new SecureRandom();
        i iVar = new i(1, length, 1);
        ArrayList arrayList = new ArrayList(s.U(iVar, 10));
        x7.h hVarC = iVar.iterator();
        while (hVarC.f22624m) {
            hVarC.nextInt();
            arrayList.add(Character.valueOf("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(secureRandom.nextInt(62))));
        }
        return x.u0(arrayList, "", null, null, null, 62);
    }

    public static /* synthetic */ String generateToken$default(AiKeyConfigServer aiKeyConfigServer, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = 8;
        }
        return aiKeyConfigServer.generateToken(i10);
    }

    private final n handleKeySubmit(fi.iki.elonen.h session, l<? super String, t0> onKeyReceived) {
        JSONObject jSONObject;
        String string;
        String strOptString;
        String strOptString2;
        HashMap map = new HashMap();
        session.a(map);
        String str = (String) map.get("postData");
        String str2 = "";
        if (str == null) {
            str = "";
        }
        try {
            jSONObject = new JSONObject(str);
        } catch (Exception unused) {
            jSONObject = null;
        }
        if (jSONObject == null || (strOptString2 = jSONObject.optString("key", "")) == null || (string = o.L0(strOptString2).toString()) == null) {
            string = "";
        }
        if (jSONObject != null && (strOptString = jSONObject.optString("token", "")) != null) {
            str2 = strOptString;
        }
        if (this.token.length() > 0 && !str2.equals(this.token)) {
            return t.newFixedLengthResponse(m.FORBIDDEN, "application/json", new JSONObject().put("status", "forbidden").put("reason", "invalid_token").toString());
        }
        onKeyReceived.invoke(string);
        return t.newFixedLengthResponse(m.OK, "application/json", new JSONObject().put("status", "saved").toString());
    }

    private final n serveHtml(String html) {
        return t.newFixedLengthResponse(m.OK, t.MIME_HTML, html);
    }

    private final n serveLogo() {
        a<byte[]> aVar = this.logoProvider;
        byte[] bArr = aVar != null ? (byte[]) aVar.invoke() : null;
        if (bArr != null) {
            return t.newFixedLengthResponse(m.OK, MimeTypes.IMAGE_PNG, new ByteArrayInputStream(bArr), bArr.length);
        }
        return t.newFixedLengthResponse(m.NOT_FOUND, t.MIME_PLAINTEXT, "Not found");
    }

    /* JADX INFO: renamed from: getCurrentPairingToken, reason: from getter */
    public final String getToken() {
        return this.token;
    }

    public final String getPairingToken() {
        return this.pairingToken;
    }

    @Override // fi.iki.elonen.t
    public n serve(fi.iki.elonen.h session) {
        String uri = session.getUri();
        fi.iki.elonen.i method = session.getMethod();
        fi.iki.elonen.i iVar = fi.iki.elonen.i.f15430i;
        return (method == iVar && p.a(uri, DomExceptionUtils.SEPARATOR)) ? serveHtml(AiKeyWebPage.INSTANCE.getLandingHtml()) : (method == iVar && p.a(uri, "/groq")) ? serveHtml(AiKeyWebPage.INSTANCE.getGroqHtml()) : (method == iVar && p.a(uri, "/gemini")) ? serveHtml(AiKeyWebPage.INSTANCE.getGeminiHtml()) : (method == iVar && p.a(uri, "/logo.png")) ? serveLogo() : (method == fi.iki.elonen.i.f15432m && p.a(uri, "/api/key")) ? handleKeySubmit(session, this.onKeyReceived) : t.newFixedLengthResponse(m.NOT_FOUND, t.MIME_PLAINTEXT, "Not found");
    }

    public /* synthetic */ AiKeyConfigServer(l lVar, a aVar, int i10, String str, int i11, h hVar) {
        this(lVar, (i11 & 2) != 0 ? null : aVar, (i11 & 4) != 0 ? 8095 : i10, (i11 & 8) != 0 ? null : str);
    }
}
