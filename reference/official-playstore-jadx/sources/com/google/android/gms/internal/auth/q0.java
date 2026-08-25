package com.google.android.gms.internal.auth;

import android.net.Uri;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public abstract class q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Uri f13093a = Uri.parse("content://com.google.android.gsf.gservices");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Pattern f13094b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Pattern f13095c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final AtomicBoolean f13096d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static HashMap f13097e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final HashMap f13098f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final HashMap f13099g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final HashMap f13100h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final HashMap f13101i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static Object f13102j;
    public static final String[] k;

    static {
        Uri.parse("content://com.google.android.gsf.gservices/prefix");
        f13094b = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
        f13095c = Pattern.compile("^(0|false|f|off|no|n)$", 2);
        f13096d = new AtomicBoolean();
        f13098f = new HashMap(16, 1.0f);
        f13099g = new HashMap(16, 1.0f);
        f13100h = new HashMap(16, 1.0f);
        f13101i = new HashMap(16, 1.0f);
        k = new String[0];
    }

    public static void a(String str, Object obj, String str2) {
        synchronized (q0.class) {
            try {
                if (obj == f13102j) {
                    f13097e.put(str, str2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
