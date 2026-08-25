package com.typesafe.config;

import a0.c;
import androidx.compose.material3.d;
import com.typesafe.config.impl.f;
import java.io.Serializable;
import l6.k;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ConfigException extends RuntimeException implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final transient k f14502i;

    public static class BadBean extends BugOrBroken {
    }

    public static class BadValue extends ConfigException {
    }

    public static class BugOrBroken extends ConfigException {
    }

    public static class Generic extends ConfigException {
    }

    public static class IO extends ConfigException {
    }

    public static class Missing extends ConfigException {
    }

    public static class NotResolved extends BugOrBroken {
    }

    public static class Null extends Missing {
    }

    public static class Parse extends ConfigException {
    }

    public static class ValidationFailed extends ConfigException {
    }

    public static class WrongType extends ConfigException {
        public WrongType(k kVar, String str, String str2, String str3) {
            super(kVar, d.o(str, " has type ", str3, " rather than ", str2), null);
        }
    }

    public ConfigException(k kVar, String str, Throwable th) {
        super(kVar.description() + ": " + str, th);
        this.f14502i = kVar;
    }

    public static class UnresolvedSubstitution extends Parse {

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final String f14503l;

        public UnresolvedSubstitution(UnresolvedSubstitution unresolvedSubstitution, k kVar, String str) {
            super(kVar, str, unresolvedSubstitution);
            this.f14503l = unresolvedSubstitution.f14503l;
        }

        public UnresolvedSubstitution(k kVar, String str, f fVar) {
            super(kVar, d.C("Could not resolve substitution to a value: ", str), fVar);
            this.f14503l = str;
        }
    }

    public ConfigException(Exception exc, String str) {
        super(str, exc);
        this.f14502i = null;
    }

    public static class BadPath extends ConfigException {
        public BadPath(k kVar, String str, String str2) {
            super(kVar, str != null ? d.n("Invalid path '", str, "': ", str2) : str2, null);
        }

        public BadPath(String str) {
            super(null, c.l("Invalid path '", str, "': Environment variable contains an un-mapped number of underscores."));
        }
    }
}
