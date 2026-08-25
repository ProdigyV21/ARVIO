package com.google.android.gms.internal.cast;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ba implements ib {
    protected int zza;

    public static void d(ArrayList arrayList, List list) {
        Charset charset = ya.f13698a;
        int size = arrayList.size();
        if (list instanceof ArrayList) {
            ((ArrayList) list).ensureCapacity(list.size() + size);
        } else if (list instanceof pb) {
            pb pbVar = (pb) list;
            int i10 = pbVar.f13549m + size;
            int length = pbVar.f13548l.length;
            if (i10 > length) {
                if (length != 0) {
                    while (length < i10) {
                        length = Math.max(((length * 3) / 2) + 1, 10);
                    }
                    pbVar.f13548l = Arrays.copyOf(pbVar.f13548l, length);
                } else {
                    pbVar.f13548l = new Object[Math.max(i10, 10)];
                }
            }
        }
        int size2 = list.size();
        int size3 = arrayList.size();
        for (int i11 = 0; i11 < size3; i11++) {
            Object obj = arrayList.get(i11);
            if (obj == null) {
                int size4 = list.size() - size2;
                StringBuilder sb2 = new StringBuilder(String.valueOf(size4).length() + 26);
                sb2.append("Element at index ");
                sb2.append(size4);
                sb2.append(" is null.");
                String string = sb2.toString();
                int size5 = list.size();
                while (true) {
                    size5--;
                    if (size5 < size2) {
                        throw new NullPointerException(string);
                    }
                    list.remove(size5);
                }
            } else {
                list.add(obj);
            }
        }
    }

    public abstract int c(rb rbVar);
}
