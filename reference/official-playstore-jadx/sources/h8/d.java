package h8;

import kotlin.reflect.b0;

/* JADX INFO: loaded from: classes5.dex */
public enum d {
    FIELD(null),
    FILE(null),
    PROPERTY(null),
    PROPERTY_GETTER("get"),
    PROPERTY_SETTER("set"),
    RECEIVER(null),
    CONSTRUCTOR_PARAMETER("param"),
    SETTER_PARAMETER("setparam"),
    PROPERTY_DELEGATE_FIELD("delegate");


    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f15857i;

    d(String str) {
        this.f15857i = str == null ? b0.J(name()) : str;
    }
}
