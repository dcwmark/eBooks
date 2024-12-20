rem javac -depend Main.java
javac Main.java

if errorlevel 1 goto dontrun

java Main toy.xam

:dontrun
