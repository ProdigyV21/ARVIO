# GPT & Open Source Models Adapter

> **Everything in this file is optional.**
> For canonical rules, see [PROJECT_RULES.md](../PROJECT_RULES.md).

This adapter provides guidance for GPT models and open-source alternatives.

---

## GPT Models

### Model Selection

| Model | Best For |
|-------|----------|
| **GPT-4o** | Balanced speed and quality, multimodal |
| **GPT-4 Turbo** | Complex reasoning, large context |
| **GPT-3.5** | Fast iterations, simple tasks |

---

### Function Calling

When function calling is available:

```json
{
  "name": "run_verification",
  "description": "Execute a verification command",
  "parameters": {
    "command": "npm test",
    "expected": "All tests pass"
  }
}
```

**Use for:**
- Verification commands
- File operations
- External service checks

---

### Context Optimization

GPT models may have smaller context than some alternatives:

1. **Be selective** — Only load necessary files
2. **Use search-first** — Critical for context efficiency
3. **Summarize large files** — Extract relevant sections only

---

## Open Source Models

### General Guidance

Open source models vary widely. General tips:

| Consideration | Guidance |
|---------------|----------|
| **Context length** | Verify model's limit; adjust file loading |
| **Instruction following** | Use explicit, structured prompts |
| **Code quality** | May need more verification steps |
| **Speed** | Varies by hardware; plan accordingly |

---

### Local Deployment

For locally-running models:

1. **Resource planning** — Ensure adequate GPU/RAM
2. **Latency expectations** — Adjust iteration speed assumptions
3. **Fallback strategy** — Document when to switch to cloud models

---

### Recommended Patterns

**Structured prompts work better:**

```markdown
## Task
{clear task description}

## Context
{relevant code snippets}

## Expected Output
{what you need back}

## Constraints
{any limitations or requirements}
```

---

## Shorter Context Strategies

When working with limited context:

1. **Aggressive search-first** — Never load full files blindly
2. **Incremental loading** — Add context as needed, not upfront
3. **State snapshots more frequently** — Prevent context overflow
4. **Split large tasks** — Smaller plans, more waves

---

## Anti-Patterns

❌ **Assuming GPT-4 context** — Verify actual model limits
❌ **Complex nested prompts** — Keep structure flat and clear
❌ **Ignoring model limits** — Quality crashes hard past context limit

---

## Model-Specific Files

Not required, but if organizing:

```
.openai/           # For OpenAI API configuration
.ollama/           # For Ollama local models
.llm/              # Generic local LLM config
```

---

*See PROJECT_RULES.md for canonical requirements.*
