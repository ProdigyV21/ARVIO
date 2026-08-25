package com.google.gson;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes4.dex */
public interface JsonSerializer<T> {
    JsonElement serialize(T t2, Type type, JsonSerializationContext jsonSerializationContext);
}
