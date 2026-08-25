package io.ktor.network.sockets;

import a0.c;
import io.ktor.http.ContentDisposition;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.collections.i0;
import kotlin.collections.s;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\r¢\u0006\u0004\b\u000b\u0010\u000eJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u000f¢\u0006\u0004\b\u000b\u0010\u0010R \u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0017R\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u0017¨\u0006\u001a"}, d2 = {"Lio/ktor/network/sockets/SocketOptionsPlatformCapabilities;", "", "<init>", "()V", "", ContentDisposition.Parameters.Name, "socketOption", "(Ljava/lang/String;)Ljava/lang/Object;", "Ljava/nio/channels/SocketChannel;", "channel", "Lx6/t0;", "setReusePort", "(Ljava/nio/channels/SocketChannel;)V", "Ljava/nio/channels/ServerSocketChannel;", "(Ljava/nio/channels/ServerSocketChannel;)V", "Ljava/nio/channels/DatagramChannel;", "(Ljava/nio/channels/DatagramChannel;)V", "", "Ljava/lang/reflect/Field;", "standardSocketOptions", "Ljava/util/Map;", "Ljava/lang/reflect/Method;", "channelSetOption", "Ljava/lang/reflect/Method;", "serverChannelSetOption", "datagramSetOption", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SocketOptionsPlatformCapabilities {
    public static final SocketOptionsPlatformCapabilities INSTANCE;
    private static final Method channelSetOption;
    private static final Method datagramSetOption;
    private static final Method serverChannelSetOption;
    private static final Map<String, Field> standardSocketOptions;

    static {
        Method method;
        Method method2;
        Map map = a0.f19683i;
        INSTANCE = new SocketOptionsPlatformCapabilities();
        try {
            Field[] fields = Class.forName("java.net.StandardSocketOptions").getFields();
            if (fields != null) {
                ArrayList arrayList = new ArrayList();
                for (Field field : fields) {
                    int modifiers = field.getModifiers();
                    if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers) && Modifier.isPublic(modifiers)) {
                        arrayList.add(field);
                    }
                }
                int iQ0 = i0.q0(s.U(arrayList, 10));
                if (iQ0 < 16) {
                    iQ0 = 16;
                }
                Map linkedHashMap = new LinkedHashMap(iQ0);
                for (Object obj : arrayList) {
                    linkedHashMap.put(((Field) obj).getName(), obj);
                }
                map = linkedHashMap;
            }
        } catch (Throwable unused) {
        }
        standardSocketOptions = map;
        Method method3 = null;
        try {
            Class<?> cls = Class.forName("java.net.SocketOption");
            Class<?> cls2 = Class.forName("java.nio.channels.SocketChannel");
            Method[] methods = cls2.getMethods();
            int length = methods.length;
            for (int i10 = 0; i10 < length; i10++) {
                method = methods[i10];
                int modifiers2 = method.getModifiers();
                if (Modifier.isPublic(modifiers2) && !Modifier.isStatic(modifiers2) && p.a(method.getName(), "setOption") && method.getParameterTypes().length == 2 && p.a(method.getReturnType(), cls2) && p.a(method.getParameterTypes()[0], cls) && p.a(method.getParameterTypes()[1], Object.class)) {
                    break;
                }
            }
        } catch (Throwable unused2) {
        }
        method = null;
        channelSetOption = method;
        try {
            Class<?> cls3 = Class.forName("java.net.SocketOption");
            Class<?> cls4 = Class.forName("java.nio.channels.ServerSocketChannel");
            Method[] methods2 = cls4.getMethods();
            int length2 = methods2.length;
            for (int i11 = 0; i11 < length2; i11++) {
                method2 = methods2[i11];
                int modifiers3 = method2.getModifiers();
                if (Modifier.isPublic(modifiers3) && !Modifier.isStatic(modifiers3) && p.a(method2.getName(), "setOption") && method2.getParameterTypes().length == 2 && p.a(method2.getReturnType(), cls4) && p.a(method2.getParameterTypes()[0], cls3) && p.a(method2.getParameterTypes()[1], Object.class)) {
                    break;
                }
            }
        } catch (Throwable unused3) {
        }
        method2 = null;
        serverChannelSetOption = method2;
        try {
            Class<?> cls5 = Class.forName("java.net.SocketOption");
            Class<?> cls6 = Class.forName("java.nio.channels.DatagramChannel");
            Method[] methods3 = cls6.getMethods();
            int length3 = methods3.length;
            int i12 = 0;
            while (true) {
                if (i12 >= length3) {
                    break;
                }
                Method method4 = methods3[i12];
                int modifiers4 = method4.getModifiers();
                if (Modifier.isPublic(modifiers4) && !Modifier.isStatic(modifiers4) && p.a(method4.getName(), "setOption") && method4.getParameterTypes().length == 2 && p.a(method4.getReturnType(), cls6) && p.a(method4.getParameterTypes()[0], cls5) && p.a(method4.getParameterTypes()[1], Object.class)) {
                    method3 = method4;
                    break;
                }
                i12++;
            }
        } catch (Throwable unused4) {
        }
        datagramSetOption = method3;
    }

    private SocketOptionsPlatformCapabilities() {
    }

    private final Object socketOption(String name) throws IOException {
        Field field = standardSocketOptions.get(name);
        Object obj = field != null ? field.get(null) : null;
        if (obj != null) {
            return obj;
        }
        throw new IOException(c.l("Socket option ", name, " is not supported"));
    }

    public final void setReusePort(SocketChannel channel) throws IllegalAccessException, IOException, InvocationTargetException {
        channelSetOption.invoke(channel, socketOption("SO_REUSEPORT"), Boolean.TRUE);
    }

    public final void setReusePort(ServerSocketChannel channel) throws IllegalAccessException, IOException, InvocationTargetException {
        serverChannelSetOption.invoke(channel, socketOption("SO_REUSEPORT"), Boolean.TRUE);
    }

    public final void setReusePort(DatagramChannel channel) throws IllegalAccessException, IOException, InvocationTargetException {
        datagramSetOption.invoke(channel, socketOption("SO_REUSEPORT"), Boolean.TRUE);
    }
}
