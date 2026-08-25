package com.google.gson;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes4.dex */
public interface InstanceCreator<T> {
    T createInstance(Type type);
}
