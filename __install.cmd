@echo off
call npm install
call py -3 -m virtualenv python_modules
call px -m pip install -U pip
call px -m pip install -r pip-packages.txt
