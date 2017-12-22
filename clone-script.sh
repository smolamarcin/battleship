#! /bin/bash
set -e
#
echo Hello Tomasz. It is is our demo. Please enter a directory name:
read foldername
git clone https://github.com/smolamarcin/battleship $foldername
cd "$foldername"
echo Invoking mvn clean install command
mvn clean install;
echo Invoking mvn test
mvn test;

#checkstyle
mvn checkstyle:checkstyle;

#find number of lines in java files
echo Number of lines in java files
find . -name '*.java' | xargs wc -l

#count of commits in master
echo Number of commits: 
git rev-list --count master

#count of remote branches
echo Number of branches: 
git remote show origin | grep tracked | wc -l
 
#Number of commits per author
echo Number of commits per author:
git shortlog | grep -E '^[^ ]' 

#count of lines per author
echo Number of lines per author 
git ls-files | while read f; do git blame --line-porcelain $f | grep '^author '; done | sort -f | uniq -ic | sort -n 

#Count of tests
echo Number of tests
mvn test | grep elapsed | grep -Eo 'run: [0-9]{1,4}' | grep -Eo '[0-9]{1,4}'| paste -sd+ | bc

#Count number of interfaces
echo Number of interfaces
grep -r --include=\*.java 'interface ' | wc -l

#Count number of packages
echo Number of packages
find . -type f -name "*.java" -printf '%h\n' | sort | uniq | wc -l

#Count number of public class
echo Number of public class
grep -r --include=\*.java 'public class' | wc -l
#grep -r --include=\*.java 'public class' | grep -v "test" | wc -l

#Count number of private class
echo Number of private class
grep -r --include=\*.java 'private class' | wc -l

#Count number of abstract class
echo Number of abstract class
grep -r --include=\*.java 'abstract class' | wc -l

#Count number of package private class
echo Number of package private class
grep -r --include=\*.java "class " | grep -v "public" | grep -v "private" | wc -l
