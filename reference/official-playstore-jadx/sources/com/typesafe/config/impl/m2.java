package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;

/* JADX INFO: loaded from: classes4.dex */
public abstract class m2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c2 f14581a = c2.c(1, "start of file", "");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c2 f14582b = c2.c(2, "end of file", "");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c2 f14583c = c2.c(3, "','", ",");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final c2 f14584d = c2.c(4, "'='", "=");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c2 f14585e = c2.c(5, "':'", ":");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c2 f14586f = c2.c(6, "'{'", "{");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final c2 f14587g = c2.c(7, "'}'", "}");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final c2 f14588h = c2.c(8, "'['", "[");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final c2 f14589i = c2.c(9, "']'", "]");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final c2 f14590j = c2.c(17, "'+='", "+=");

    public static String a(c2 c2Var) {
        if (c2Var instanceof k2) {
            return ((k2) c2Var).f14566e;
        }
        throw new ConfigException.BugOrBroken(null, "tried to get unquoted text from " + c2Var);
    }

    public static g b(c2 c2Var) {
        if (c2Var instanceof l2) {
            return ((l2) c2Var).f14569e;
        }
        throw new ConfigException.BugOrBroken(null, "tried to get value of non-value token " + c2Var);
    }

    public static boolean c(c2 c2Var) {
        return (c2Var instanceof l2) && b(c2Var).b() == 6;
    }
}
