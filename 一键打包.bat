@echo off

@echo start build exe-shell

call mvn clean install -Dskiptest=true

pause