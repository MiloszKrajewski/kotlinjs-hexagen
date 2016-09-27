#!./python_modules/Scripts/python

import sys
import subprocess, threading, psutil
import time
import json
from watchdog.observers import Observer
from watchdog.events import PatternMatchingEventHandler

class Bunch:
    def __init__(self, **kwds):
        self.__dict__.update(kwds)

def execute(command, name):
    if name is not None:
        print("Starting '{}'".format(name))
    cmdl = "cmd /c {}".format(command)
    devnull = subprocess.DEVNULL 
    subprocess.Popen(cmdl, stdin=devnull).wait()
    if name is not None:
        print("Finished '{}'".format(name))

def spawn(command, name):
    if name is not None:
        print("Starting '{}'".format(name))
    cmdl = "cmd /c {}".format(command)
    devnull = subprocess.DEVNULL 
    return (subprocess.Popen(cmdl, stdin=devnull).pid, name)

def safe_find_process(pid):
    try:
        return psutil.Process(pid)
    except psutil.NoSuchProcess:
        pass

def safe_kill_process(process):
    try:
        process.kill()
    except psutil.NoSuchProcess:
        pass

def kill(pid, name=None):
    if pid is None:
        return
    if name is not None:
        print("Killing '{}'".format(name))
    parent = safe_find_process(pid)
    if parent is not None:
        for child in parent.children(recursive=True):
            safe_kill_process(child)
        safe_kill_process(parent)

def watch(folder, include, exclude, command, name):
    state = Bunch(last_event=time.clock(), last_action=None)
    stop = threading.Event()
    done = threading.Event()

    def _test():
        if state.last_action is None: 
            return True
        if state.last_action > state.last_event:
            return False
        return time.clock() - state.last_event > 1

    def _update(e):
        state.last_event = time.clock()

    def _loop():
        try:
            if name is not None:
                print("Watching '{}'".format(name))
            handler = PatternMatchingEventHandler(patterns=include, ignore_patterns=exclude, case_sensitive=True)
            handler.on_any_event = _update
            observer = Observer()
            observer.schedule(handler, folder, recursive=True)
            observer.start()
            while not stop.is_set():
                stop.wait(1)
                if _test():
                    state.last_action = time.clock()
                    execute(command, name)
            if name is not None:
                print("Unwatching '{}'".format(name))
            observer.stop()
            observer.join()
        finally:
            done.set()

    def _join():
        stop.set()
        done.wait()

    thread = threading.Thread(target=_loop)
    thread.start()
    return _join

def monitor(command, name):
    stop = threading.Event()
    done = threading.Event()
    pid = None

    def _spawn():
        nonlocal pid
        pid, _ = spawn(command, name)

    def _kill():
        nonlocal pid
        kill(pid, name)
        pid = None
    
    def _test():
        return safe_find_process(pid) is not None

    def _join():
        stop.set()
        done.wait() 

    def _loop():
        try:
            _spawn()
            while True:
                stop.wait(1)
                if stop.is_set():
                    _kill()
                    break
                elif not _test():
                    print("Restarting '{}'".format(name))
                    wait(10)
                    _spawn()
        finally:
            done.set()

    thread = threading.Thread(target=_loop)
    thread.start()
    return _join

def start_job(name, command):
    if isinstance(command, str):
        return monitor(command, name)
    if isinstance(command, dict):
        folder = command.get('folder', ".")
        include = command.get("include", [])
        exclude = command.get("exclude", [])
        command = command.get("command", "echo CHANGE DETECTED. NO ACTION.")
        return watch(folder, include, exclude, command, name)
    return lambda: None

def wait(secs):
    print("Waiting {}s".format(secs))
    time.sleep(5)

def main(argv):
    configName = "./watchconfig.json"
    if len(argv) > 1: 
        configName = argv[1] 

    finalizers = []

    def start_all():
        print("Loading config from '{}'".format(configName))
        nonlocal finalizers
        try:
            with open(configName) as configFile:
                processes = list(json.load(configFile).items())
        except FileNotFoundError:
            print("Config file '{}' could not be found".format(configName))
            processes = []
        finalizers = [start_job(*process) for process in processes]

    def stop_all():
        nonlocal finalizers
        for finalizer in finalizers:
            finalizer()
        finalizer = []

    start_all()

    while True:
        print("----------------------------------------\n")
        print("  (R)estart")
        print("  (Q)uit")
        print("\n----------------------------------------")

        command = input().strip().lower()

        if command == 'q':
            stop_all()
            break
        elif command == 'r':
            stop_all()
            start_all()

    print("Done.")
   

if __name__ == '__main__':
    main(sys.argv)
