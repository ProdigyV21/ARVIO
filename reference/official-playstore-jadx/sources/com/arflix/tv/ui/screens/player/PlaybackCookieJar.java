package com.arflix.tv.ui.screens.player;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\f\u0010\rR&\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00100\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/ui/screens/player/PlaybackCookieJar;", "Lgb/r;", "<init>", "()V", "Lgb/b0;", "url", "", "Lgb/q;", "cookies", "Lx6/t0;", "saveFromResponse", "(Lgb/b0;Ljava/util/List;)V", "loadForRequest", "(Lgb/b0;)Ljava/util/List;", "j$/util/concurrent/ConcurrentHashMap", "", "", "cookiesByHost", "Lj$/util/concurrent/ConcurrentHashMap;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class PlaybackCookieJar implements gb.r {
    private final ConcurrentHashMap<String, List<gb.q>> cookiesByHost = new ConcurrentHashMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean saveFromResponse$lambda$0$0(gb.q qVar, gb.q qVar2) {
        return kotlin.jvm.internal.p.a(qVar2.f15796a, qVar.f15796a) && kotlin.jvm.internal.p.a(qVar2.f15799d, qVar.f15799d) && kotlin.jvm.internal.p.a(qVar2.f15800e, qVar.f15800e);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0081  */
    @Override // gb.r
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<gb.q> loadForRequest(gb.b0 r14) {
        /*
            r13 = this;
            java.lang.String r0 = r14.f15620d
            long r1 = java.lang.System.currentTimeMillis()
            j$.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.List<gb.q>> r3 = r13.cookiesByHost
            java.lang.Object r3 = r3.get(r0)
            java.util.List r3 = (java.util.List) r3
            if (r3 == 0) goto La9
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r5 = r4.iterator()
        L1e:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L88
            java.lang.Object r6 = r5.next()
            r7 = r6
            gb.q r7 = (gb.q) r7
            long r8 = r7.f15798c
            int r8 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            r9 = 0
            if (r8 <= 0) goto L82
            java.lang.String r8 = r7.f15799d
            boolean r10 = r7.f15804i
            if (r10 == 0) goto L3d
            boolean r8 = kotlin.jvm.internal.p.a(r0, r8)
            goto L43
        L3d:
            java.util.regex.Pattern r10 = gb.q.f15793j
            boolean r8 = qb.d.z(r0, r8)
        L43:
            r10 = 1
            if (r8 != 0) goto L48
        L46:
            r7 = r9
            goto L7f
        L48:
            java.lang.String r8 = r7.f15800e
            java.lang.String r11 = r14.b()
            boolean r12 = r11.equals(r8)
            if (r12 == 0) goto L55
            goto L70
        L55:
            boolean r12 = kotlin.text.u.P(r11, r8, r9)
            if (r12 == 0) goto L72
            java.lang.String r12 = "/"
            boolean r12 = kotlin.text.u.K(r8, r12, r9)
            if (r12 == 0) goto L64
            goto L70
        L64:
            int r8 = r8.length()
            char r8 = r11.charAt(r8)
            r11 = 47
            if (r8 != r11) goto L72
        L70:
            r8 = r10
            goto L73
        L72:
            r8 = r9
        L73:
            if (r8 != 0) goto L76
            goto L46
        L76:
            boolean r7 = r7.f15801f
            if (r7 == 0) goto L7e
            boolean r7 = r14.f15626j
            if (r7 == 0) goto L46
        L7e:
            r7 = r10
        L7f:
            if (r7 == 0) goto L82
            r9 = r10
        L82:
            if (r9 == 0) goto L1e
            r3.add(r6)
            goto L1e
        L88:
            int r14 = r3.size()
            int r1 = r4.size()
            if (r14 == r1) goto La8
            boolean r14 = r3.isEmpty()
            if (r14 == 0) goto L9e
            j$.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.List<gb.q>> r14 = r13.cookiesByHost
            r14.remove(r0)
            return r3
        L9e:
            j$.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.List<gb.q>> r14 = r13.cookiesByHost
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r3)
            r14.put(r0, r1)
        La8:
            return r3
        La9:
            kotlin.collections.z r14 = kotlin.collections.z.f19728i
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlaybackCookieJar.loadForRequest(gb.b0):java.util.List");
    }

    @Override // gb.r
    public void saveFromResponse(gb.b0 url, List<gb.q> cookies) {
        if (cookies.isEmpty()) {
            return;
        }
        String str = url.f15620d;
        List<gb.q> list = this.cookiesByHost.get(str);
        ArrayList arrayList = list != null ? new ArrayList(list) : new ArrayList();
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (gb.q qVar : cookies) {
            if (qVar.f15798c > jCurrentTimeMillis) {
                kotlin.collections.x.M0(arrayList, new b(qVar, 0));
                arrayList.add(qVar);
            }
        }
        if (arrayList.isEmpty()) {
            this.cookiesByHost.remove(str);
        } else {
            this.cookiesByHost.put(str, arrayList);
        }
    }
}
