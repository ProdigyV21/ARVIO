package com.google.gson;

/* JADX INFO: loaded from: classes4.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: com.google.gson.LongSerializationPolicy.1
        @Override // com.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long l10) {
            return l10 == null ? JsonNull.INSTANCE : new JsonPrimitive(l10);
        }
    },
    STRING { // from class: com.google.gson.LongSerializationPolicy.2
        @Override // com.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long l10) {
            return l10 == null ? JsonNull.INSTANCE : new JsonPrimitive(l10.toString());
        }
    };

    public abstract JsonElement serialize(Long l10);
}
