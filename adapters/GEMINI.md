# Gemini Adapter

> **Everything in this file is optional.**
> For canonical rules, see [PROJECT_RULES.md](../PROJECT_RULES.md).

This adapter provides optional enhancements for Gemini models in Antigravity.

---

## Model Selection

### Flash vs Pro

| Model Type | Best For |
|------------|----------|
| **Flash** | Quick iterations, simple edits, high-volume tasks |
| **Pro** | Complex planning, large refactors, deep analysis |

**Default recommendation:** Start with Pro for planning, switch to Flash for implementation.

---

## Context Window Optimization

Gemini models often have large context windows. Optimize usage:

1. **Load full files strategically** — Large context allows it, but still prefer search-first
2. **Batch related files** — Group related code in single context load
3. **Clear separation** — Use XML tags to separate file contents

### Context Loading Pattern

```xml
<file path="src/auth/login.ts">
{file contents}
</file>

<file path="src/auth/types.ts">
{file contents}
</file>

<task>
{your task here}
</task>
```

---

## Grounding

When available, use grounding for:

- Checking latest documentation
- Verifying API behaviors
- Validating external service states

**Not for:** Code implementation (use codebase content).

---

## Code Execution

If code execution sandbox is available:

- Use for verification commands
- Use for testing snippets
- Document outputs in SUMMARY.md

---

## Integration with .gemini/

The `.gemini/GEMINI.md` file can reference this adapter:

```markdown
# In .gemini/GEMINI.md

For canonical rules, see PROJECT_RULES.md.
For Gemini-specific tips, see adapters/GEMINI.md.
```

---

## Anti-Patterns

❌ **Loading entire codebase** — Even with large context, quality degrades
❌ **Ignoring context thresholds** — 50% is still the quality boundary
❌ **Skipping STATE.md** — Context window size doesn't replace persistent state

---

*See PROJECT_RULES.md for canonical requirements.*
