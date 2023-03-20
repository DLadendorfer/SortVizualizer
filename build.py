import subprocess

process = subprocess.Popen('gradlew.bat build', shell=True, cwd='vizualizer/vizualizer/')
output, error = process.communicate()