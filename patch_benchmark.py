import re

with open('app/src/test/kotlin/com/arflix/tv/data/repository/IptvBenchmarkTest.kt', 'r') as f:
    content = f.read()

# Update the coroutine test to use Thread.sleep instead of delay to properly benchmark the blocking IO behavior
# of the underlying requestJson call which still uses execute() (blocking).
# We want to show that Dispatchers.IO.limitedParallelism(...) properly handles blocking code.

content = content.replace("delay(10)", "Thread.sleep(10)")

with open('app/src/test/kotlin/com/arflix/tv/data/repository/IptvBenchmarkTest.kt', 'w') as f:
    f.write(content)
