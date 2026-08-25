package io.sentry;

import java.io.Closeable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes4.dex */
public interface k3 extends Closeable {
    Float B0();

    Double F();

    Object F0();

    Date I(ILogger iLogger);

    ArrayList I0(ILogger iLogger, u1 u1Var);

    Boolean L();

    Object P(ILogger iLogger, u1 u1Var);

    void beginArray();

    void beginObject();

    void endArray();

    void endObject();

    Integer f0();

    Long h0();

    boolean hasNext();

    String l0();

    HashMap m0(ILogger iLogger, u1 u1Var);

    double nextDouble();

    float nextFloat();

    int nextInt();

    long nextLong();

    String nextName();

    String nextString();

    io.sentry.vendor.gson.stream.b peek();

    void setLenient(boolean z);

    void skipValue();

    void t(ILogger iLogger, AbstractMap abstractMap, String str);

    TimeZone v(ILogger iLogger);
}
