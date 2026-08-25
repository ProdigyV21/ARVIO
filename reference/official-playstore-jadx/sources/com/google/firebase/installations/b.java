package com.google.firebase.installations;

import android.net.TrafficStats;
import android.text.TextUtils;
import android.util.Log;
import androidx.appcompat.widget.f0;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import com.google.android.gms.tasks.i;
import com.google.android.gms.tasks.t;
import com.google.firebase.components.o;
import io.sentry.util.l;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class b implements c {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Object f14371m = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z4.g f14372a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p5.c f14373b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final fi.iki.elonen.f f14374c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final h f14375d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final o f14376e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f f14377f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f14378g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ExecutorService f14379h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final com.google.firebase.concurrent.o f14380i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f14381j;
    public final HashSet k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f14382l;

    static {
        new AtomicInteger(1);
    }

    public b(z4.g gVar, m5.b bVar, ExecutorService executorService, com.google.firebase.concurrent.o oVar) {
        gVar.a();
        p5.c cVar = new p5.c(gVar.f23184a, bVar);
        fi.iki.elonen.f fVar = new fi.iki.elonen.f((Object) gVar, false);
        if (l.f17964l == null) {
            l.f17964l = new l(16);
        }
        l lVar = l.f17964l;
        if (h.f14389d == null) {
            h.f14389d = new h(lVar);
        }
        h hVar = h.f14389d;
        o oVar2 = new o(new com.google.firebase.components.d(gVar, 2));
        f fVar2 = new f();
        this.f14378g = new Object();
        this.k = new HashSet();
        this.f14382l = new ArrayList();
        this.f14372a = gVar;
        this.f14373b = cVar;
        this.f14374c = fVar;
        this.f14375d = hVar;
        this.f14376e = oVar2;
        this.f14377f = fVar2;
        this.f14379h = executorService;
        this.f14380i = oVar;
    }

    public final t a() {
        i iVar = new i();
        e eVar = new e(iVar);
        synchronized (this.f14378g) {
            this.f14382l.add(eVar);
        }
        return iVar.f13855a;
    }

    public final o5.b b(o5.b bVar) {
        int responseCode;
        p5.b bVarF;
        z4.g gVar = this.f14372a;
        gVar.a();
        String str = gVar.f23186c.f23199a;
        String str2 = bVar.f20782a;
        gVar.a();
        String str3 = gVar.f23186c.f23205g;
        String str4 = bVar.f20785d;
        p5.c cVar = this.f14373b;
        p5.d dVar = cVar.f21065c;
        if (!dVar.b()) {
            throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.");
        }
        URL urlA = p5.c.a("projects/" + str3 + "/installations/" + str2 + "/authTokens:generate");
        for (int i10 = 0; i10 <= 1; i10++) {
            TrafficStats.setThreadStatsTag(32771);
            HttpURLConnection httpURLConnectionC = cVar.c(urlA, str);
            try {
                try {
                    httpURLConnectionC.setRequestMethod(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST);
                    httpURLConnectionC.addRequestProperty("Authorization", "FIS_v2 " + str4);
                    httpURLConnectionC.setDoOutput(true);
                    p5.c.h(httpURLConnectionC);
                    responseCode = httpURLConnectionC.getResponseCode();
                    dVar.d(responseCode);
                } finally {
                    httpURLConnectionC.disconnect();
                    TrafficStats.clearThreadStatsTag();
                }
            } catch (IOException | AssertionError unused) {
            }
            if (responseCode >= 200 && responseCode < 300) {
                bVarF = p5.c.f(httpURLConnectionC);
            } else {
                p5.c.b(httpURLConnectionC, null, str, str3);
                if (responseCode == 401 || responseCode == 404) {
                    f0 f0VarA = p5.b.a();
                    f0VarA.f1566l = 3;
                    bVarF = f0VarA.b();
                } else {
                    if (responseCode == 429) {
                        throw new FirebaseInstallationsException("Firebase servers have received too many requests from this client in a short period of time. Please try again later.");
                    }
                    if (responseCode < 500 || responseCode >= 600) {
                        Log.e("Firebase-Installations", "Firebase Installations can not communicate with Firebase server APIs due to invalid configuration. Please update your Firebase initialization process and set valid Firebase options (API key, Project ID, Application ID) when initializing Firebase.");
                        f0 f0VarA2 = p5.b.a();
                        f0VarA2.f1566l = 2;
                        bVarF = f0VarA2.b();
                    }
                }
            }
            int iC = h.f0.c(bVarF.f21060c);
            if (iC == 0) {
                String str5 = bVarF.f21058a;
                long j10 = bVarF.f21059b;
                h hVar = this.f14375d;
                hVar.getClass();
                long seconds = TimeUnit.MILLISECONDS.toSeconds(hVar.f14390a.currentTimeMillis());
                o5.a aVarA = bVar.a();
                aVarA.f20777d = str5;
                aVarA.f20780g = Long.valueOf(j10);
                aVarA.f20781h = Long.valueOf(seconds);
                return aVarA.a();
            }
            if (iC == 1) {
                o5.a aVarA2 = bVar.a();
                aVarA2.f20779f = "BAD CONFIG";
                aVarA2.f20776c = 5;
                return aVarA2.a();
            }
            if (iC != 2) {
                throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.");
            }
            h(null);
            o5.a aVarA3 = bVar.a();
            aVarA3.f20776c = 2;
            return aVarA3.a();
        }
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.");
    }

    /* JADX WARN: Finally extract failed */
    public final void c(o5.b bVar) {
        synchronized (f14371m) {
            try {
                z4.g gVar = this.f14372a;
                gVar.a();
                io.sentry.internal.debugmeta.c cVarV = io.sentry.internal.debugmeta.c.v(gVar.f23184a);
                try {
                    this.f14374c.R(bVar);
                    if (cVarV != null) {
                        cVarV.D();
                    }
                } catch (Throwable th) {
                    if (cVarV != null) {
                        cVarV.D();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String d(o5.b r3) {
        /*
            r2 = this;
            z4.g r0 = r2.f14372a
            r0.a()
            java.lang.String r0 = r0.f23185b
            java.lang.String r1 = "CHIME_ANDROID_SDK"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L1e
            z4.g r0 = r2.f14372a
            java.lang.String r1 = "[DEFAULT]"
            r0.a()
            java.lang.String r0 = r0.f23185b
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L50
        L1e:
            int r3 = r3.f20783b
            r0 = 1
            if (r3 != r0) goto L50
            com.google.firebase.components.o r3 = r2.f14376e
            java.lang.Object r3 = r3.get()
            o5.c r3 = (o5.c) r3
            android.content.SharedPreferences r0 = r3.f20790a
            monitor-enter(r0)
            java.lang.String r1 = r3.a()     // Catch: java.lang.Throwable -> L36
            if (r1 == 0) goto L38
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L36
            goto L3d
        L36:
            r3 = move-exception
            goto L4e
        L38:
            java.lang.String r1 = r3.b()     // Catch: java.lang.Throwable -> L36
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L36
        L3d:
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 == 0) goto L4d
            com.google.firebase.installations.f r3 = r2.f14377f
            r3.getClass()
            java.lang.String r3 = com.google.firebase.installations.f.a()
            return r3
        L4d:
            return r1
        L4e:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L36
            throw r3
        L50:
            com.google.firebase.installations.f r3 = r2.f14377f
            r3.getClass()
            java.lang.String r3 = com.google.firebase.installations.f.a()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.installations.b.d(o5.b):java.lang.String");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [p5.c] */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [p5.a] */
    public final o5.b e(o5.b bVar) {
        int responseCode;
        String str = bVar.f20782a;
        String string = null;
        if (str != null && str.length() == 11) {
            o5.c cVar = (o5.c) this.f14376e.get();
            synchronized (cVar.f20790a) {
                try {
                    String[] strArr = o5.c.f20789c;
                    int i10 = 0;
                    while (true) {
                        if (i10 < 4) {
                            String str2 = strArr[i10];
                            String string2 = cVar.f20790a.getString("|T|" + cVar.f20791b + "|" + str2, null);
                            if (string2 == null || string2.isEmpty()) {
                                i10++;
                            } else if (string2.startsWith("{")) {
                                try {
                                    string = new JSONObject(string2).getString("token");
                                } catch (JSONException unused) {
                                }
                            } else {
                                string = string2;
                            }
                        }
                    }
                } finally {
                }
            }
        }
        p5.c cVar2 = this.f14373b;
        z4.g gVar = this.f14372a;
        gVar.a();
        String str3 = gVar.f23186c.f23199a;
        String str4 = bVar.f20782a;
        z4.g gVar2 = this.f14372a;
        gVar2.a();
        String str5 = gVar2.f23186c.f23205g;
        z4.g gVar3 = this.f14372a;
        gVar3.a();
        String str6 = gVar3.f23186c.f23200b;
        p5.d dVar = cVar2.f21065c;
        if (!dVar.b()) {
            throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.");
        }
        URL urlA = p5.c.a("projects/" + str5 + "/installations");
        int i11 = 0;
        p5.a aVar = cVar2;
        while (i11 <= 1) {
            TrafficStats.setThreadStatsTag(32769);
            HttpURLConnection httpURLConnectionC = aVar.c(urlA, str3);
            try {
                try {
                    httpURLConnectionC.setRequestMethod(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST);
                    httpURLConnectionC.setDoOutput(true);
                    if (string != null) {
                        httpURLConnectionC.addRequestProperty("x-goog-fis-android-iid-migration-auth", string);
                    }
                    p5.c.g(httpURLConnectionC, str4, str6);
                    responseCode = httpURLConnectionC.getResponseCode();
                    dVar.d(responseCode);
                } catch (IOException | AssertionError unused2) {
                }
                if (responseCode >= 200 && responseCode < 300) {
                    p5.a aVarE = p5.c.e(httpURLConnectionC);
                    httpURLConnectionC.disconnect();
                    TrafficStats.clearThreadStatsTag();
                    aVar = aVarE;
                } else {
                    try {
                        p5.c.b(httpURLConnectionC, str6, str3, str5);
                    } catch (IOException | AssertionError unused3) {
                        httpURLConnectionC.disconnect();
                        TrafficStats.clearThreadStatsTag();
                    }
                    if (responseCode == 429) {
                        throw new FirebaseInstallationsException("Firebase servers have received too many requests from this client in a short period of time. Please try again later.");
                    }
                    if (responseCode < 500 || responseCode >= 600) {
                        Log.e("Firebase-Installations", "Firebase Installations can not communicate with Firebase server APIs due to invalid configuration. Please update your Firebase initialization process and set valid Firebase options (API key, Project ID, Application ID) when initializing Firebase.");
                        p5.a aVar2 = new p5.a(null, null, null, null, 2);
                        httpURLConnectionC.disconnect();
                        TrafficStats.clearThreadStatsTag();
                        aVar = aVar2;
                    } else {
                        httpURLConnectionC.disconnect();
                        TrafficStats.clearThreadStatsTag();
                        i11++;
                        aVar = aVar;
                    }
                    httpURLConnectionC.disconnect();
                    TrafficStats.clearThreadStatsTag();
                    i11++;
                    aVar = aVar;
                }
                int iC = h.f0.c(aVar.f21057e);
                if (iC != 0) {
                    if (iC != 1) {
                        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.");
                    }
                    o5.a aVarA = bVar.a();
                    aVarA.f20779f = "BAD CONFIG";
                    aVarA.f20776c = 5;
                    return aVarA.a();
                }
                String str7 = aVar.f21054b;
                String str8 = aVar.f21055c;
                h hVar = this.f14375d;
                hVar.getClass();
                long seconds = TimeUnit.MILLISECONDS.toSeconds(hVar.f14390a.currentTimeMillis());
                p5.b bVar2 = aVar.f21056d;
                String str9 = bVar2.f21058a;
                long j10 = bVar2.f21059b;
                o5.a aVarA2 = bVar.a();
                aVarA2.f20775b = str7;
                aVarA2.f20776c = 4;
                aVarA2.f20777d = str9;
                aVarA2.f20778e = str8;
                aVarA2.f20780g = Long.valueOf(j10);
                aVarA2.f20781h = Long.valueOf(seconds);
                return aVarA2.a();
            } finally {
                httpURLConnectionC.disconnect();
                TrafficStats.clearThreadStatsTag();
            }
        }
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.");
    }

    public final void f(Exception exc) {
        synchronized (this.f14378g) {
            try {
                Iterator it = this.f14382l.iterator();
                while (it.hasNext()) {
                    ((g) it.next()).getClass();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void g(o5.b bVar) {
        synchronized (this.f14378g) {
            try {
                Iterator it = this.f14382l.iterator();
                while (it.hasNext()) {
                    if (((g) it.next()).a(bVar)) {
                        it.remove();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.firebase.installations.c
    public final t getId() {
        String str;
        z4.g gVar = this.f14372a;
        gVar.a();
        com.google.android.gms.common.internal.t.g(gVar.f23186c.f23200b, "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        z4.g gVar2 = this.f14372a;
        gVar2.a();
        com.google.android.gms.common.internal.t.g(gVar2.f23186c.f23205g, "Please set your Project ID. A valid Firebase Project ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        z4.g gVar3 = this.f14372a;
        gVar3.a();
        com.google.android.gms.common.internal.t.g(gVar3.f23186c.f23199a, "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.");
        z4.g gVar4 = this.f14372a;
        gVar4.a();
        String str2 = gVar4.f23186c.f23200b;
        Pattern pattern = h.f14388c;
        com.google.android.gms.common.internal.t.a("Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.", str2.contains(":"));
        z4.g gVar5 = this.f14372a;
        gVar5.a();
        com.google.android.gms.common.internal.t.a("Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.", h.f14388c.matcher(gVar5.f23186c.f23199a).matches());
        synchronized (this) {
            str = this.f14381j;
        }
        if (str != null) {
            return qb.l.n(str);
        }
        t tVarA = a();
        this.f14379h.execute(new a(this, 0));
        return tVarA;
    }

    public final synchronized void h(String str) {
        this.f14381j = str;
    }

    public final synchronized void i(o5.b bVar, o5.b bVar2) {
        if (this.k.size() != 0 && !TextUtils.equals(bVar.f20782a, bVar2.f20782a)) {
            Iterator it = this.k.iterator();
            while (it.hasNext()) {
                ((n5.a) it.next()).a();
            }
        }
    }
}
