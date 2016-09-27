@echo off
pushd %~dp0
rmdir /q /s .gradle
rmdir /q /s node_modules
rmdir /q /s python_modules
rmdir /q /s out
rmdir /q /s build
popd