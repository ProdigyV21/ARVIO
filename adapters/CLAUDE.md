# Claude Adapter

> **Everything in this file is optional.**
> For canonical rules, see [PROJECT_RULES.md](../PROJECT_RULES.md).

This adapter provides optional enhancements for Claude models in Antigravity.

---

## Extended Thinking Mode

When available, activate extended thinking for:

| Task Type | Recommended |
|-----------|-------------|
| Architecture planning | ✅ High effort |
| Complex debugging | ✅ High effort |
| Security analysis | ✅ High effort |
| Simple edits | ❌ Not needed |
| Quick iterations | ❌ Overhead too high |

### Effort Levels

If the model supports effort/budget levels:

| Level | Use Case |
|-------|----------|
| `low` | Simple edits, formatting, comments |
| `medium` | Standard implementation (default) |
| `high` | Complex logic, refactoring, debugging |
| `max` | Architecture, security, critical decisions |

**Default:** `medium` if not specified.

---

## Artifacts Mode

When artifacts are supported:

- Use for code generation that needs preview
- Use for documentation with formatting
- Avoid for small inline edits

---

## Context Optimization

Claude-specific context tips:

1. **System prompt loading**: Core rules in system prompt, task details in user message
2. **XML structure**: Claude parses XML well — use task XML format from GSD-STYLE.md
3. **Conversation history**: Minimal history preferred; use STATE.md for continuity

---

## File Conventions

Not required, but if organizing Claude-specific files:

```
.claude/
├── CLAUDE.md      # This adapter (if using)
└── settings.json  # IDE-specific settings
```

---

## Anti-Patterns

❌ **Using max effort for everything** — Slow and expensive
❌ **Skipping verification** — Thinking mode doesn't guarantee correctness
❌ **Depending on artifacts** — Not all Claude interfaces support them

---

*See PROJECT_RULES.md for canonical requirements.*
