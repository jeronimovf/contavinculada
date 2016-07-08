@echo off
cls
setlocal
set path="C:\Program Files\PostgreSQL\9.5\bin"
For /f "tokens=1-4 delims=/ " %%a in ('date /t') do (set mydate=%%c-%%b-%%a)
For /f "tokens=1-2 delims=/:" %%a in ('time /t') do (set mytime=%%a-%%b)
pg_dump.exe --host localhost --port 5432 --username "contavinculada" --password --format plain --section data --encoding UTF8 --inserts --column-inserts --verbose --file "C:\Users\j129-9\Documents\NetBeansProjects\contavinculada\src\main\resources\META-INF\data-%mydate%-%mytime%.sql" "contavinculada"
endlocal