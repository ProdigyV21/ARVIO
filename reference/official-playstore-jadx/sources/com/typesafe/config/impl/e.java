package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;

/* JADX INFO: loaded from: classes4.dex */
public abstract class e implements d {
    @Override // com.typesafe.config.impl.d
    public final g L(g gVar, String str) {
        try {
            return a(gVar);
        } catch (RuntimeException e5) {
            throw e5;
        } catch (Exception e6) {
            throw new ConfigException.BugOrBroken(e6, "Unexpected exception");
        }
    }

    public abstract g a(g gVar);
}
