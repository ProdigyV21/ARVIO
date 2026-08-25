package androidx.core.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class n1 implements Handler.Callback, ServiceConnection {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f2025i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Handler f2026l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final HashMap f2027m = new HashMap();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public HashSet f2028n = new HashSet();

    public n1(Context context) {
        this.f2025i = context;
        HandlerThread handlerThread = new HandlerThread("NotificationManagerCompat");
        handlerThread.start();
        this.f2026l = new Handler(handlerThread.getLooper(), this);
    }

    public final void a(m1 m1Var) {
        boolean z;
        ArrayDeque arrayDeque = m1Var.f2022d;
        ComponentName componentName = m1Var.f2019a;
        if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Processing component " + componentName + ", " + arrayDeque.size() + " queued tasks");
        }
        if (arrayDeque.isEmpty()) {
            return;
        }
        if (m1Var.f2020b) {
            z = true;
        } else {
            Intent component = new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(componentName);
            Context context = this.f2025i;
            boolean zBindService = context.bindService(component, this, 33);
            m1Var.f2020b = zBindService;
            if (zBindService) {
                m1Var.f2023e = 0;
            } else {
                Log.w("NotifManCompat", "Unable to bind to listener " + componentName);
                context.unbindService(this);
            }
            z = m1Var.f2020b;
        }
        if (!z || m1Var.f2021c == null) {
            b(m1Var);
            return;
        }
        while (true) {
            o1 o1Var = (o1) arrayDeque.peek();
            if (o1Var == null) {
                break;
            }
            try {
                if (Log.isLoggable("NotifManCompat", 3)) {
                    Log.d("NotifManCompat", "Sending task " + o1Var);
                }
                o1Var.a(m1Var.f2021c);
                arrayDeque.remove();
            } catch (DeadObjectException unused) {
                if (Log.isLoggable("NotifManCompat", 3)) {
                    Log.d("NotifManCompat", "Remote service has died: " + componentName);
                }
            } catch (RemoteException e5) {
                Log.w("NotifManCompat", "RemoteException communicating with " + componentName, e5);
            }
        }
        if (arrayDeque.isEmpty()) {
            return;
        }
        b(m1Var);
    }

    public final void b(m1 m1Var) {
        ComponentName componentName = m1Var.f2019a;
        ArrayDeque arrayDeque = m1Var.f2022d;
        Handler handler = this.f2026l;
        if (handler.hasMessages(3, componentName)) {
            return;
        }
        int i10 = m1Var.f2023e;
        int i11 = i10 + 1;
        m1Var.f2023e = i11;
        if (i11 <= 6) {
            int i12 = (1 << i10) * 1000;
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Scheduling retry for " + i12 + " ms");
            }
            handler.sendMessageDelayed(handler.obtainMessage(3, componentName), i12);
            return;
        }
        Log.w("NotifManCompat", "Giving up on delivering " + arrayDeque.size() + " tasks to " + componentName + " after " + m1Var.f2023e + " retries");
        arrayDeque.clear();
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        HashSet hashSet;
        int i10 = message.what;
        d.c cVar = null;
        if (i10 == 0) {
            o1 o1Var = (o1) message.obj;
            String string = Settings.Secure.getString(this.f2025i.getContentResolver(), "enabled_notification_listeners");
            synchronized (p1.f2035c) {
                if (string != null) {
                    try {
                        if (!string.equals(p1.f2036d)) {
                            String[] strArrSplit = string.split(":", -1);
                            HashSet hashSet2 = new HashSet(strArrSplit.length);
                            for (String str : strArrSplit) {
                                ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                                if (componentNameUnflattenFromString != null) {
                                    hashSet2.add(componentNameUnflattenFromString.getPackageName());
                                }
                            }
                            p1.f2037e = hashSet2;
                            p1.f2036d = string;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                hashSet = p1.f2037e;
            }
            if (!hashSet.equals(this.f2028n)) {
                this.f2028n = hashSet;
                List<ResolveInfo> listQueryIntentServices = this.f2025i.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
                HashSet<ComponentName> hashSet3 = new HashSet();
                for (ResolveInfo resolveInfo : listQueryIntentServices) {
                    if (hashSet.contains(resolveInfo.serviceInfo.packageName)) {
                        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                        ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                        if (resolveInfo.serviceInfo.permission != null) {
                            Log.w("NotifManCompat", "Permission present on component " + componentName + ", not adding listener record.");
                        } else {
                            hashSet3.add(componentName);
                        }
                    }
                }
                for (ComponentName componentName2 : hashSet3) {
                    if (!this.f2027m.containsKey(componentName2)) {
                        if (Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Adding listener record for " + componentName2);
                        }
                        this.f2027m.put(componentName2, new m1(componentName2));
                    }
                }
                Iterator it = this.f2027m.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    if (!hashSet3.contains(entry.getKey())) {
                        if (Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Removing listener record for " + entry.getKey());
                        }
                        m1 m1Var = (m1) entry.getValue();
                        if (m1Var.f2020b) {
                            this.f2025i.unbindService(this);
                            m1Var.f2020b = false;
                        }
                        m1Var.f2021c = null;
                        it.remove();
                    }
                }
            }
            for (m1 m1Var2 : this.f2027m.values()) {
                m1Var2.f2022d.add(o1Var);
                a(m1Var2);
            }
        } else if (i10 == 1) {
            l1 l1Var = (l1) message.obj;
            ComponentName componentName3 = l1Var.f2017a;
            IBinder iBinder = l1Var.f2018b;
            m1 m1Var3 = (m1) this.f2027m.get(componentName3);
            if (m1Var3 != null) {
                int i11 = d.b.f14654c;
                if (iBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(d.c.f14655b);
                    if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof d.c)) {
                        d.a aVar = new d.a();
                        aVar.f14653c = iBinder;
                        cVar = aVar;
                    } else {
                        cVar = (d.c) iInterfaceQueryLocalInterface;
                    }
                }
                m1Var3.f2021c = cVar;
                m1Var3.f2023e = 0;
                a(m1Var3);
                return true;
            }
        } else if (i10 == 2) {
            m1 m1Var4 = (m1) this.f2027m.get((ComponentName) message.obj);
            if (m1Var4 != null) {
                if (m1Var4.f2020b) {
                    this.f2025i.unbindService(this);
                    m1Var4.f2020b = false;
                }
                m1Var4.f2021c = null;
                return true;
            }
        } else {
            if (i10 != 3) {
                return false;
            }
            m1 m1Var5 = (m1) this.f2027m.get((ComponentName) message.obj);
            if (m1Var5 != null) {
                a(m1Var5);
                return true;
            }
        }
        return true;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Connected to service " + componentName);
        }
        this.f2026l.obtainMessage(1, new l1(componentName, iBinder)).sendToTarget();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("NotifManCompat", 3)) {
            Log.d("NotifManCompat", "Disconnected from service " + componentName);
        }
        this.f2026l.obtainMessage(2, componentName).sendToTarget();
    }
}
