package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;

/* JADX INFO: loaded from: classes4.dex */
public class c2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f14513a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f14514b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final l6.k f14515c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f14516d;

    public c2(int i10, l6.k kVar, String str, String str2) {
        this.f14513a = i10;
        this.f14515c = kVar;
        this.f14514b = str2;
        this.f14516d = str;
    }

    public static c2 c(int i10, String str, String str2) {
        return new c2(i10, null, str2, str);
    }

    public boolean a(c2 c2Var) {
        return true;
    }

    public final int b() {
        l6.k kVar = this.f14515c;
        if (kVar != null) {
            return kVar.a();
        }
        return -1;
    }

    public final l6.k d() {
        l6.k kVar = this.f14515c;
        if (kVar != null) {
            return kVar;
        }
        throw new ConfigException.BugOrBroken(null, "tried to get origin from token that doesn't have one: " + this);
    }

    public String e() {
        return this.f14516d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c2)) {
            return false;
        }
        c2 c2Var = (c2) obj;
        return a(c2Var) && this.f14513a == c2Var.f14513a;
    }

    public int hashCode() {
        return h.f0.c(this.f14513a);
    }

    public String toString() {
        String str = this.f14514b;
        if (str != null) {
            return str;
        }
        switch (this.f14513a) {
            case 1:
                return "START";
            case 2:
                return "END";
            case 3:
                return "COMMA";
            case 4:
                return "EQUALS";
            case 5:
                return "COLON";
            case 6:
                return "OPEN_CURLY";
            case 7:
                return "CLOSE_CURLY";
            case 8:
                return "OPEN_SQUARE";
            case 9:
                return "CLOSE_SQUARE";
            case 10:
                return "VALUE";
            case 11:
                return "NEWLINE";
            case 12:
                return "UNQUOTED_TEXT";
            case 13:
                return "IGNORED_WHITESPACE";
            case 14:
                return "SUBSTITUTION";
            case 15:
                return "PROBLEM";
            case 16:
                return "COMMENT";
            case 17:
                return "PLUS_EQUALS";
            default:
                throw null;
        }
    }
}
