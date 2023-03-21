import subprocess

process = subprocess.Popen('gradlew.bat build', shell=True)
output, error = process.communicate()