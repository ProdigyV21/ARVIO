import re

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'r') as f:
    content = f.read()

# find what we messed up
match = re.search(r'}\s*\?\:\s*emptyList\(\)\s*}(?!\s*})', content[30000:])
if match:
    pass

# We replaced }.awaitAll().flatten()\n    } with }.awaitAll().flatten()\n    } ?: emptyList()
# which makes the compiler think the block for withTimeoutOrNull hasn't been closed, because we need a closing brace for fetchXtreamEpgListingsAsync block if we used =

# Let's fix fetchXtreamEpgListingsAsync
content = content.replace("} ?: emptyList()", "} ?: emptyList()\n    }")

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'w') as f:
    f.write(content)
