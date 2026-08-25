package com.google.gson;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes4.dex */
public interface JsonSerializationContext {
    JsonElement serialize(Object obj);

    JsonElement serialize(Object obj, Type type);
}
