package androidx.compose.foundation.text;

import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import kotlin.Metadata;
import kotlin.jvm.internal.b0;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a#\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\"\u001a\u0010\u0007\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lkotlin/Function1;", "Landroidx/compose/ui/input/key/KeyEvent;", "", "shortcutModifier", "Landroidx/compose/foundation/text/KeyMapping;", "commonKeyMapping", "(Lr7/l;)Landroidx/compose/foundation/text/KeyMapping;", "defaultKeyMapping", "Landroidx/compose/foundation/text/KeyMapping;", "getDefaultKeyMapping", "()Landroidx/compose/foundation/text/KeyMapping;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KeyMappingKt {
    private static final KeyMapping defaultKeyMapping;

    static {
        final KeyMapping keyMappingCommonKeyMapping = commonKeyMapping(new b0() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$1
            @Override // kotlin.jvm.internal.b0, kotlin.reflect.o
            public Object get(Object obj) {
                return Boolean.valueOf(KeyEvent_androidKt.m4405isCtrlPressedZmokQxo(((KeyEvent) obj).m4390unboximpl()));
            }
        });
        defaultKeyMapping = new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt$defaultKeyMapping$2$1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* JADX INFO: renamed from: map-ZmokQxo */
            public KeyCommand mo827mapZmokQxo(android.view.KeyEvent event) {
                KeyCommand keyCommand = null;
                if (KeyEvent_androidKt.m4407isShiftPressedZmokQxo(event) && KeyEvent_androidKt.m4405isCtrlPressedZmokQxo(event)) {
                    long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(event);
                    MappedKeys mappedKeys = MappedKeys.INSTANCE;
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, mappedKeys.m853getDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LEFT_WORD;
                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, mappedKeys.m854getDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_RIGHT_WORD;
                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, mappedKeys.m855getDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_PREV_PARAGRAPH;
                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, mappedKeys.m852getDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_NEXT_PARAGRAPH;
                    }
                } else if (KeyEvent_androidKt.m4405isCtrlPressedZmokQxo(event)) {
                    long jM4401getKeyZmokQxo2 = KeyEvent_androidKt.m4401getKeyZmokQxo(event);
                    MappedKeys mappedKeys2 = MappedKeys.INSTANCE;
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m853getDirectionLeftEK5gGoQ())) {
                        keyCommand = KeyCommand.LEFT_WORD;
                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m854getDirectionRightEK5gGoQ())) {
                        keyCommand = KeyCommand.RIGHT_WORD;
                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m855getDirectionUpEK5gGoQ())) {
                        keyCommand = KeyCommand.PREV_PARAGRAPH;
                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m852getDirectionDownEK5gGoQ())) {
                        keyCommand = KeyCommand.NEXT_PARAGRAPH;
                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m857getHEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_CHAR;
                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m851getDeleteEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_NEXT_WORD;
                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m847getBackspaceEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_PREV_WORD;
                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m846getBackslashEK5gGoQ())) {
                        keyCommand = KeyCommand.DESELECT;
                    }
                } else if (KeyEvent_androidKt.m4407isShiftPressedZmokQxo(event)) {
                    long jM4401getKeyZmokQxo3 = KeyEvent_androidKt.m4401getKeyZmokQxo(event);
                    MappedKeys mappedKeys3 = MappedKeys.INSTANCE;
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m860getMoveHomeEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_LEFT;
                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m859getMoveEndEK5gGoQ())) {
                        keyCommand = KeyCommand.SELECT_LINE_RIGHT;
                    }
                } else if (KeyEvent_androidKt.m4404isAltPressedZmokQxo(event)) {
                    long jM4401getKeyZmokQxo4 = KeyEvent_androidKt.m4401getKeyZmokQxo(event);
                    MappedKeys mappedKeys4 = MappedKeys.INSTANCE;
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo4, mappedKeys4.m847getBackspaceEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_FROM_LINE_START;
                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo4, mappedKeys4.m851getDeleteEK5gGoQ())) {
                        keyCommand = KeyCommand.DELETE_TO_LINE_END;
                    }
                }
                return keyCommand == null ? keyMappingCommonKeyMapping.mo827mapZmokQxo(event) : keyCommand;
            }
        };
    }

    public static final KeyMapping commonKeyMapping(final l<? super KeyEvent, Boolean> lVar) {
        return new KeyMapping() { // from class: androidx.compose.foundation.text.KeyMappingKt.commonKeyMapping.1
            @Override // androidx.compose.foundation.text.KeyMapping
            /* JADX INFO: renamed from: map-ZmokQxo */
            public KeyCommand mo827mapZmokQxo(android.view.KeyEvent event) {
                if (((Boolean) lVar.invoke(KeyEvent.m4384boximpl(event))).booleanValue() && KeyEvent_androidKt.m4407isShiftPressedZmokQxo(event)) {
                    if (Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(event), MappedKeys.INSTANCE.m868getZEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    return null;
                }
                if (((Boolean) lVar.invoke(KeyEvent.m4384boximpl(event))).booleanValue()) {
                    long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(event);
                    MappedKeys mappedKeys = MappedKeys.INSTANCE;
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, mappedKeys.m848getCEK5gGoQ()) ? true : Key.m4093equalsimpl0(jM4401getKeyZmokQxo, mappedKeys.m858getInsertEK5gGoQ())) {
                        return KeyCommand.COPY;
                    }
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, mappedKeys.m865getVEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, mappedKeys.m866getXEK5gGoQ())) {
                        return KeyCommand.CUT;
                    }
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, mappedKeys.m845getAEK5gGoQ())) {
                        return KeyCommand.SELECT_ALL;
                    }
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, mappedKeys.m867getYEK5gGoQ())) {
                        return KeyCommand.REDO;
                    }
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, mappedKeys.m868getZEK5gGoQ())) {
                        return KeyCommand.UNDO;
                    }
                    return null;
                }
                if (KeyEvent_androidKt.m4405isCtrlPressedZmokQxo(event)) {
                    return null;
                }
                if (KeyEvent_androidKt.m4407isShiftPressedZmokQxo(event)) {
                    long jM4401getKeyZmokQxo2 = KeyEvent_androidKt.m4401getKeyZmokQxo(event);
                    MappedKeys mappedKeys2 = MappedKeys.INSTANCE;
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m853getDirectionLeftEK5gGoQ())) {
                        return KeyCommand.SELECT_LEFT_CHAR;
                    }
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m854getDirectionRightEK5gGoQ())) {
                        return KeyCommand.SELECT_RIGHT_CHAR;
                    }
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m855getDirectionUpEK5gGoQ())) {
                        return KeyCommand.SELECT_UP;
                    }
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m852getDirectionDownEK5gGoQ())) {
                        return KeyCommand.SELECT_DOWN;
                    }
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m862getPageUpEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_UP;
                    }
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m861getPageDownEK5gGoQ())) {
                        return KeyCommand.SELECT_PAGE_DOWN;
                    }
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m860getMoveHomeEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_START;
                    }
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m859getMoveEndEK5gGoQ())) {
                        return KeyCommand.SELECT_LINE_END;
                    }
                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, mappedKeys2.m858getInsertEK5gGoQ())) {
                        return KeyCommand.PASTE;
                    }
                    return null;
                }
                long jM4401getKeyZmokQxo3 = KeyEvent_androidKt.m4401getKeyZmokQxo(event);
                MappedKeys mappedKeys3 = MappedKeys.INSTANCE;
                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m853getDirectionLeftEK5gGoQ())) {
                    return KeyCommand.LEFT_CHAR;
                }
                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m854getDirectionRightEK5gGoQ())) {
                    return KeyCommand.RIGHT_CHAR;
                }
                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m855getDirectionUpEK5gGoQ())) {
                    return KeyCommand.UP;
                }
                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m852getDirectionDownEK5gGoQ())) {
                    return KeyCommand.DOWN;
                }
                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m862getPageUpEK5gGoQ())) {
                    return KeyCommand.PAGE_UP;
                }
                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m861getPageDownEK5gGoQ())) {
                    return KeyCommand.PAGE_DOWN;
                }
                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m860getMoveHomeEK5gGoQ())) {
                    return KeyCommand.LINE_START;
                }
                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m859getMoveEndEK5gGoQ())) {
                    return KeyCommand.LINE_END;
                }
                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m856getEnterEK5gGoQ())) {
                    return KeyCommand.NEW_LINE;
                }
                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m847getBackspaceEK5gGoQ())) {
                    return KeyCommand.DELETE_PREV_CHAR;
                }
                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m851getDeleteEK5gGoQ())) {
                    return KeyCommand.DELETE_NEXT_CHAR;
                }
                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m863getPasteEK5gGoQ())) {
                    return KeyCommand.PASTE;
                }
                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m850getCutEK5gGoQ())) {
                    return KeyCommand.CUT;
                }
                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m849getCopyEK5gGoQ())) {
                    return KeyCommand.COPY;
                }
                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, mappedKeys3.m864getTabEK5gGoQ())) {
                    return KeyCommand.TAB;
                }
                return null;
            }
        };
    }

    public static final KeyMapping getDefaultKeyMapping() {
        return defaultKeyMapping;
    }
}
