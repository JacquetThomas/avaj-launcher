find . -name *.java > sources.txt
javac -sourcepath src @sources.txt #-target 1.7
