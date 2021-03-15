call runcrud.bat

if "%ERRORLEVEL%" == "0" goto runchrome
echo Cannot open runcrud.bat script
goto fail

:runchrome
call start chrome http://localhost:8080/crud/v1/task/getTasks

:fail
echo.
echo There were errors.

:end
echo.
echo Work is finished.