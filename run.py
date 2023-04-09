import os
import subprocess

# nt = windows based
if os.name == 'nt': 
    process = subprocess.Popen('gradlew.bat run', shell=True)
else:
    process = subprocess.Popen('gradle run', shell=True)
output, error = process.communicate()