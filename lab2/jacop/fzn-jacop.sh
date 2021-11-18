#!/bin/bash
exec java -XX:+UseSerialGC -server -cp "/Users/andreasolsson/Desktop/constraint/lab2/jacop/jacop-4.8.0.jar" -Xmx8G -Xss100M org.jacop.fz.Fz2jacop "$@"