package com.arflix.tv.data.repository;

import android.content.Context;
import androidx.media3.extractor.text.ttml.TtmlNode;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0086@¢\u0006\u0004\b\u0012\u0010\u0013J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\u00102\u0006\u0010\u0015\u001a\u00020\bH\u0086@¢\u0006\u0004\b\u0016\u0010\u0017J6\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001d0\u00102\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\bH\u0086@¢\u0006\u0004\b\u001e\u0010\u001fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010!R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%¨\u0006&"}, d2 = {"Lcom/arflix/tv/data/repository/TvDeviceAuthRepository;", "", "Landroid/content/Context;", "context", "Lgb/h0;", "okHttpClient", "<init>", "(Landroid/content/Context;Lgb/h0;)V", "", TtmlNode.TAG_BODY, "fallback", "parseError", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "Lcom/arflix/tv/data/repository/TvDeviceAuthStatus;", "parseStatus", "(Ljava/lang/String;)Lcom/arflix/tv/data/repository/TvDeviceAuthStatus;", "Lx6/d0;", "Lcom/arflix/tv/data/repository/TvDeviceAuthSession;", "startSession-IoAF18A", "(Ld7/d;)Ljava/lang/Object;", "startSession", "deviceCode", "pollStatus-gIAlu-s", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "pollStatus", "userCode", "email", "password", "intent", "Lcom/arflix/tv/data/repository/TvDeviceAuthCompleteResult;", "completeWithEmailPassword-yxL6bBk", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "completeWithEmailPassword", "Landroid/content/Context;", "Lgb/h0;", "Lgb/d0;", "jsonMediaType", "Lgb/d0;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TvDeviceAuthRepository {
    public static final int $stable = 8;
    private final Context context;
    private final gb.d0 jsonMediaType;
    private final gb.h0 okHttpClient;

    @Inject
    public TvDeviceAuthRepository(@ApplicationContext Context context, gb.h0 h0Var) {
        this.context = context;
        this.okHttpClient = h0Var;
        Pattern pattern = gb.d0.f15633d;
        this.jsonMediaType = xc.d.i0("application/json; charset=utf-8");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String parseError(String body, String fallback) {
        try {
            JSONObject jSONObject = new JSONObject(body);
            String strOptString = jSONObject.optString("error");
            if (!kotlin.text.o.h0(strOptString)) {
                return strOptString;
            }
            String strOptString2 = jSONObject.optString("message");
            if (!kotlin.text.o.h0(strOptString2)) {
                return strOptString2;
            }
            String strOptString3 = jSONObject.optString("error_description");
            if (!kotlin.text.o.h0(strOptString3)) {
                fallback = strOptString3;
            }
            return fallback;
        } catch (JSONException | Exception unused) {
            return fallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.arflix.tv.data.repository.TvDeviceAuthStatus parseStatus(java.lang.String r8) {
        /*
            r7 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>(r8)
            java.lang.String r8 = "status"
            java.lang.String r8 = r0.optString(r8)
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r8 = r8.toLowerCase(r1)
            int r1 = r8.hashCode()
            r2 = -1309235419(0xffffffffb1f6a725, float:-7.1785444E-9)
            if (r1 == r2) goto L3d
            r2 = -682587753(0xffffffffd7508997, float:-2.2928936E14)
            if (r1 == r2) goto L32
            r2 = 1185244855(0x46a566b7, float:21171.357)
            if (r1 == r2) goto L25
            goto L45
        L25:
            java.lang.String r1 = "approved"
            boolean r8 = r8.equals(r1)
            if (r8 != 0) goto L2e
            goto L45
        L2e:
            com.arflix.tv.data.repository.TvDeviceAuthStatusType r8 = com.arflix.tv.data.repository.TvDeviceAuthStatusType.APPROVED
        L30:
            r2 = r8
            goto L4b
        L32:
            java.lang.String r1 = "pending"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L45
            com.arflix.tv.data.repository.TvDeviceAuthStatusType r8 = com.arflix.tv.data.repository.TvDeviceAuthStatusType.PENDING
            goto L30
        L3d:
            java.lang.String r1 = "expired"
            boolean r8 = r8.equals(r1)
            if (r8 != 0) goto L48
        L45:
            com.arflix.tv.data.repository.TvDeviceAuthStatusType r8 = com.arflix.tv.data.repository.TvDeviceAuthStatusType.ERROR
            goto L30
        L48:
            com.arflix.tv.data.repository.TvDeviceAuthStatusType r8 = com.arflix.tv.data.repository.TvDeviceAuthStatusType.EXPIRED
            goto L30
        L4b:
            java.lang.String r8 = "access_token"
            java.lang.String r8 = r0.optString(r8)
            boolean r1 = kotlin.text.o.h0(r8)
            r3 = 0
            if (r1 == 0) goto L59
            r8 = r3
        L59:
            java.lang.String r1 = "refresh_token"
            java.lang.String r1 = r0.optString(r1)
            boolean r4 = kotlin.text.o.h0(r1)
            if (r4 == 0) goto L67
            r4 = r3
            goto L68
        L67:
            r4 = r1
        L68:
            java.lang.String r1 = "email"
            java.lang.String r1 = r0.optString(r1)
            boolean r5 = kotlin.text.o.h0(r1)
            if (r5 == 0) goto L76
            r5 = r3
            goto L77
        L76:
            r5 = r1
        L77:
            java.lang.String r1 = "message"
            java.lang.String r0 = r0.optString(r1)
            boolean r1 = kotlin.text.o.h0(r0)
            if (r1 == 0) goto L85
            r6 = r3
            goto L86
        L85:
            r6 = r0
        L86:
            com.arflix.tv.data.repository.TvDeviceAuthStatus r1 = new com.arflix.tv.data.repository.TvDeviceAuthStatus
            r3 = r8
            r1.<init>(r2, r3, r4, r5, r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.TvDeviceAuthRepository.parseStatus(java.lang.String):com.arflix.tv.data.repository.TvDeviceAuthStatus");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: completeWithEmailPassword-yxL6bBk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6146completeWithEmailPasswordyxL6bBk(java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, d7.d<? super x6.d0> r15) {
        /*
            r10 = this;
            boolean r0 = r15 instanceof com.arflix.tv.data.repository.TvDeviceAuthRepository$completeWithEmailPassword$1
            if (r0 == 0) goto L13
            r0 = r15
            com.arflix.tv.data.repository.TvDeviceAuthRepository$completeWithEmailPassword$1 r0 = (com.arflix.tv.data.repository.TvDeviceAuthRepository$completeWithEmailPassword$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.TvDeviceAuthRepository$completeWithEmailPassword$1 r0 = new com.arflix.tv.data.repository.TvDeviceAuthRepository$completeWithEmailPassword$1
            r0.<init>(r10, r15)
        L18:
            java.lang.Object r15 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L41
            if (r1 != r2) goto L39
            java.lang.Object r11 = r0.L$4
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r11 = r0.L$3
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r11 = r0.L$2
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r11 = r0.L$1
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r11 = r0.L$0
            java.lang.String r11 = (java.lang.String) r11
            k2.c.G(r15)
            goto L8f
        L39:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L41:
            k2.c.G(r15)
            com.arflix.tv.util.AuthEmailValidator r15 = com.arflix.tv.util.AuthEmailValidator.INSTANCE
            java.lang.String r5 = r15.normalize(r12)
            java.lang.String r12 = "signup"
            boolean r12 = kotlin.text.u.L(r14, r12, r2)
            java.lang.Integer r15 = r15.validate(r5, r12)
            if (r15 == 0) goto L6b
            int r11 = r15.intValue()
            android.content.Context r12 = r10.context
            java.lang.String r11 = r12.getString(r11)
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r12.<init>(r11)
            x6.c0 r11 = new x6.c0
            r11.<init>(r12)
            return r11
        L6b:
            ra.c r15 = ka.x0.f19655d
            com.arflix.tv.data.repository.TvDeviceAuthRepository$completeWithEmailPassword$3 r3 = new com.arflix.tv.data.repository.TvDeviceAuthRepository$completeWithEmailPassword$3
            r9 = 0
            r8 = r10
            r4 = r11
            r6 = r13
            r7 = r14
            r3.<init>(r4, r5, r6, r7, r8, r9)
            r11 = 0
            r0.L$0 = r11
            r0.L$1 = r11
            r0.L$2 = r11
            r0.L$3 = r11
            r0.L$4 = r11
            r0.Z$0 = r12
            r0.label = r2
            java.lang.Object r15 = ka.m0.y(r15, r3, r0)
            e7.a r11 = e7.a.f15033i
            if (r15 != r11) goto L8f
            return r11
        L8f:
            x6.d0 r15 = (x6.d0) r15
            java.lang.Object r11 = r15.f22580i
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.TvDeviceAuthRepository.m6146completeWithEmailPasswordyxL6bBk(java.lang.String, java.lang.String, java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: pollStatus-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6147pollStatusgIAlus(java.lang.String r5, d7.d<? super x6.d0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.TvDeviceAuthRepository$pollStatus$1
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.TvDeviceAuthRepository$pollStatus$1 r0 = (com.arflix.tv.data.repository.TvDeviceAuthRepository$pollStatus$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.TvDeviceAuthRepository$pollStatus$1 r0 = new com.arflix.tv.data.repository.TvDeviceAuthRepository$pollStatus$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            k2.c.G(r6)
            goto L49
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r6)
            ra.c r6 = ka.x0.f19655d
            com.arflix.tv.data.repository.TvDeviceAuthRepository$pollStatus$2 r1 = new com.arflix.tv.data.repository.TvDeviceAuthRepository$pollStatus$2
            r3 = 0
            r1.<init>(r5, r4, r3)
            r0.L$0 = r3
            r0.label = r2
            java.lang.Object r6 = ka.m0.y(r6, r1, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L49
            return r5
        L49:
            x6.d0 r6 = (x6.d0) r6
            java.lang.Object r5 = r6.f22580i
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.TvDeviceAuthRepository.m6147pollStatusgIAlus(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: startSession-IoAF18A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6148startSessionIoAF18A(d7.d<? super x6.d0> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.TvDeviceAuthRepository$startSession$1
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.TvDeviceAuthRepository$startSession$1 r0 = (com.arflix.tv.data.repository.TvDeviceAuthRepository$startSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.TvDeviceAuthRepository$startSession$1 r0 = new com.arflix.tv.data.repository.TvDeviceAuthRepository$startSession$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L43
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            ra.c r5 = ka.x0.f19655d
            com.arflix.tv.data.repository.TvDeviceAuthRepository$startSession$2 r1 = new com.arflix.tv.data.repository.TvDeviceAuthRepository$startSession$2
            r3 = 0
            r1.<init>(r4, r3)
            r0.label = r2
            java.lang.Object r5 = ka.m0.y(r5, r1, r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L43
            return r0
        L43:
            x6.d0 r5 = (x6.d0) r5
            java.lang.Object r5 = r5.f22580i
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.TvDeviceAuthRepository.m6148startSessionIoAF18A(d7.d):java.lang.Object");
    }
}
