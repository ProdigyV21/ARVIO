import re

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'r') as f:
    content = f.read()

bad_docstring = """    /**
     * Build IptvNowNext map from Xtream EPG listings.
     */

     * Groups listings by channel, sorts by start time, assigns now/next/later/upcoming.
     */"""
good_docstring = """    /**
     * Build IptvNowNext map from Xtream EPG listings.
     * Groups listings by channel, sorts by start time, assigns now/next/later/upcoming.
     */"""

content = content.replace(bad_docstring, good_docstring)

with open('app/src/main/kotlin/com/arflix/tv/data/repository/IptvRepository.kt', 'w') as f:
    f.write(content)
