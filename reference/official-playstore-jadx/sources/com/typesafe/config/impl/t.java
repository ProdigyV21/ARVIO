package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public abstract class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile v1 f14619a;

    static {
        w1 w1Var = x.f14641a;
        HashMap map = new HashMap(System.getenv());
        HashMap map2 = new HashMap();
        for (String str : map.keySet()) {
            if (str.startsWith("CONFIG_FORCE_")) {
                StringBuilder sb2 = new StringBuilder();
                char[] charArray = str.substring(13, str.length()).toCharArray();
                int length = charArray.length;
                int i10 = 0;
                int i11 = 0;
                while (true) {
                    char c10 = '-';
                    if (i10 < length) {
                        char c11 = charArray[i10];
                        if (c11 == '_') {
                            i11++;
                        } else {
                            if (i11 > 0 && i11 < 4) {
                                if (i11 == 1) {
                                    c10 = '.';
                                } else if (i11 != 2) {
                                    c10 = i11 != 3 ? (char) 0 : '_';
                                }
                                sb2.append(c10);
                            } else if (i11 > 3) {
                                throw new ConfigException.BadPath(str);
                            }
                            sb2.append(c11);
                            i11 = 0;
                        }
                        i10++;
                    } else {
                        if (i11 > 0 && i11 < 4) {
                            sb2.append(i11 != 1 ? i11 != 2 ? i11 != 3 ? (char) 0 : '_' : '-' : '.');
                        } else if (i11 > 3) {
                            throw new ConfigException.BadPath(str);
                        }
                        map2.put(sb2.toString(), map.get(str));
                    }
                }
            }
        }
        f14619a = k1.a(w1.g("env variables overrides"), map2.entrySet());
    }
}
