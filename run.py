#-------------------------------------------------------------------------------
# Copyright (c) Ladendorfer Daniel.
# All Rights Reserved.  See LICENSE in the project root for license information.
#-------------------------------------------------------------------------------

import os
import subprocess

# nt = windows based
if os.name == 'nt': 
    process = subprocess.Popen('gradlew.bat run', shell=True)
else:
    process = subprocess.Popen('gradle run', shell=True)
output, error = process.communicate()