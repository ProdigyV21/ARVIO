package androidx.compose.ui.platform.actionmodecallback;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import androidx.compose.ui.geometry.Rect;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.a;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0000\u0018\u00002\u00020\u0001Bk\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002¢\u0006\u0004\b\u000b\u0010\fJ/\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J!\u0010\u0017\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0017\u0010\u0018J!\u0010\u0019\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0019\u0010\u0018J!\u0010\u001b\u001a\u00020\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0010\u001a\u0004\u0018\u00010\u001a¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010!\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\rH\u0001¢\u0006\u0004\b\u001f\u0010 J\u001f\u0010$\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0000¢\u0006\u0004\b\"\u0010#R\u001f\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010%\u001a\u0004\b&\u0010'R\"\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R*\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010%\u001a\u0004\b-\u0010'\"\u0004\b.\u0010/R*\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010%\u001a\u0004\b0\u0010'\"\u0004\b1\u0010/R*\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010%\u001a\u0004\b2\u0010'\"\u0004\b3\u0010/R*\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010%\u001a\u0004\b4\u0010'\"\u0004\b5\u0010/¨\u00066"}, d2 = {"Landroidx/compose/ui/platform/actionmodecallback/TextActionModeCallback;", "", "Lkotlin/Function0;", "Lx6/t0;", "onActionModeDestroy", "Landroidx/compose/ui/geometry/Rect;", "rect", "onCopyRequested", "onPasteRequested", "onCutRequested", "onSelectAllRequested", "<init>", "(Lr7/a;Landroidx/compose/ui/geometry/Rect;Lr7/a;Lr7/a;Lr7/a;Lr7/a;)V", "Landroid/view/Menu;", "menu", "Landroidx/compose/ui/platform/actionmodecallback/MenuItemOption;", "item", "callback", "addOrRemoveMenuItem", "(Landroid/view/Menu;Landroidx/compose/ui/platform/actionmodecallback/MenuItemOption;Lr7/a;)V", "Landroid/view/ActionMode;", "mode", "", "onCreateActionMode", "(Landroid/view/ActionMode;Landroid/view/Menu;)Z", "onPrepareActionMode", "Landroid/view/MenuItem;", "onActionItemClicked", "(Landroid/view/ActionMode;Landroid/view/MenuItem;)Z", "onDestroyActionMode", "()V", "updateMenuItems$ui_release", "(Landroid/view/Menu;)V", "updateMenuItems", "addMenuItem$ui_release", "(Landroid/view/Menu;Landroidx/compose/ui/platform/actionmodecallback/MenuItemOption;)V", "addMenuItem", "Lr7/a;", "getOnActionModeDestroy", "()Lr7/a;", "Landroidx/compose/ui/geometry/Rect;", "getRect", "()Landroidx/compose/ui/geometry/Rect;", "setRect", "(Landroidx/compose/ui/geometry/Rect;)V", "getOnCopyRequested", "setOnCopyRequested", "(Lr7/a;)V", "getOnPasteRequested", "setOnPasteRequested", "getOnCutRequested", "setOnCutRequested", "getOnSelectAllRequested", "setOnSelectAllRequested", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TextActionModeCallback {
    public static final int $stable = 8;
    private final a<t0> onActionModeDestroy;
    private a<t0> onCopyRequested;
    private a<t0> onCutRequested;
    private a<t0> onPasteRequested;
    private a<t0> onSelectAllRequested;
    private Rect rect;

    public TextActionModeCallback() {
        this(null, null, null, null, null, null, 63, null);
    }

    private final void addOrRemoveMenuItem(Menu menu, MenuItemOption item, a<t0> callback) {
        if (callback != null && menu.findItem(item.getId()) == null) {
            addMenuItem$ui_release(menu, item);
        } else {
            if (callback != null || menu.findItem(item.getId()) == null) {
                return;
            }
            menu.removeItem(item.getId());
        }
    }

    public final void addMenuItem$ui_release(Menu menu, MenuItemOption item) {
        menu.add(0, item.getId(), item.getOrder(), item.getTitleResource()).setShowAsAction(1);
    }

    public final a<t0> getOnActionModeDestroy() {
        return this.onActionModeDestroy;
    }

    public final a<t0> getOnCopyRequested() {
        return this.onCopyRequested;
    }

    public final a<t0> getOnCutRequested() {
        return this.onCutRequested;
    }

    public final a<t0> getOnPasteRequested() {
        return this.onPasteRequested;
    }

    public final a<t0> getOnSelectAllRequested() {
        return this.onSelectAllRequested;
    }

    public final Rect getRect() {
        return this.rect;
    }

    public final boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == MenuItemOption.Copy.getId()) {
            a<t0> aVar = this.onCopyRequested;
            if (aVar != null) {
                aVar.invoke();
            }
        } else if (itemId == MenuItemOption.Paste.getId()) {
            a<t0> aVar2 = this.onPasteRequested;
            if (aVar2 != null) {
                aVar2.invoke();
            }
        } else if (itemId == MenuItemOption.Cut.getId()) {
            a<t0> aVar3 = this.onCutRequested;
            if (aVar3 != null) {
                aVar3.invoke();
            }
        } else {
            if (itemId != MenuItemOption.SelectAll.getId()) {
                return false;
            }
            a<t0> aVar4 = this.onSelectAllRequested;
            if (aVar4 != null) {
                aVar4.invoke();
            }
        }
        if (mode == null) {
            return true;
        }
        mode.finish();
        return true;
    }

    public final boolean onCreateActionMode(ActionMode mode, Menu menu) {
        if (menu == null) {
            throw new IllegalArgumentException("onCreateActionMode requires a non-null menu");
        }
        if (mode == null) {
            throw new IllegalArgumentException("onCreateActionMode requires a non-null mode");
        }
        if (this.onCopyRequested != null) {
            addMenuItem$ui_release(menu, MenuItemOption.Copy);
        }
        if (this.onPasteRequested != null) {
            addMenuItem$ui_release(menu, MenuItemOption.Paste);
        }
        if (this.onCutRequested != null) {
            addMenuItem$ui_release(menu, MenuItemOption.Cut);
        }
        if (this.onSelectAllRequested == null) {
            return true;
        }
        addMenuItem$ui_release(menu, MenuItemOption.SelectAll);
        return true;
    }

    public final void onDestroyActionMode() {
        a<t0> aVar = this.onActionModeDestroy;
        if (aVar != null) {
            aVar.invoke();
        }
    }

    public final boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        if (mode == null || menu == null) {
            return false;
        }
        updateMenuItems$ui_release(menu);
        return true;
    }

    public final void setOnCopyRequested(a<t0> aVar) {
        this.onCopyRequested = aVar;
    }

    public final void setOnCutRequested(a<t0> aVar) {
        this.onCutRequested = aVar;
    }

    public final void setOnPasteRequested(a<t0> aVar) {
        this.onPasteRequested = aVar;
    }

    public final void setOnSelectAllRequested(a<t0> aVar) {
        this.onSelectAllRequested = aVar;
    }

    public final void setRect(Rect rect) {
        this.rect = rect;
    }

    public final void updateMenuItems$ui_release(Menu menu) {
        addOrRemoveMenuItem(menu, MenuItemOption.Copy, this.onCopyRequested);
        addOrRemoveMenuItem(menu, MenuItemOption.Paste, this.onPasteRequested);
        addOrRemoveMenuItem(menu, MenuItemOption.Cut, this.onCutRequested);
        addOrRemoveMenuItem(menu, MenuItemOption.SelectAll, this.onSelectAllRequested);
    }

    public TextActionModeCallback(a<t0> aVar, Rect rect, a<t0> aVar2, a<t0> aVar3, a<t0> aVar4, a<t0> aVar5) {
        this.onActionModeDestroy = aVar;
        this.rect = rect;
        this.onCopyRequested = aVar2;
        this.onPasteRequested = aVar3;
        this.onCutRequested = aVar4;
        this.onSelectAllRequested = aVar5;
    }

    public /* synthetic */ TextActionModeCallback(a aVar, Rect rect, a aVar2, a aVar3, a aVar4, a aVar5, int i10, h hVar) {
        this((i10 & 1) != 0 ? null : aVar, (i10 & 2) != 0 ? Rect.INSTANCE.getZero() : rect, (i10 & 4) != 0 ? null : aVar2, (i10 & 8) != 0 ? null : aVar3, (i10 & 16) != 0 ? null : aVar4, (i10 & 32) != 0 ? null : aVar5);
    }
}
