#! /bin/bash
set -e
#

echo Invoking mvn clean install command
cd bom
mvn clean install;
cd ..
mvn clean install;
echo Invoking mvn test
mvn test;
#checkstyle
mvn checkstyle:checkstyle;
#find number of lines in java files
echo Number of lines in java files
find . -name '*.java' | xargs wc -l

echo
#count of commits in master
printf "Number of commits: "
git rev-list --count master

#count of remote branches
printf "Number of branches: "
git remote show origin | grep tracked | wc -l

echo
#Number of commits per author
echo Number of commits per author:
printf "Marcin: "
git shortlog | grep -E '^[^ ]' | grep c | grep -Eo '[0-9]{1,4}'| paste -sd+ | bc
printf "Marek: "
git shortlog | grep -E '^[^ ]' | grep e | grep -Eo '[0-9]{1,4}'| paste -sd+ | bc
printf "Dominik: "
git shortlog | grep -E '^[^ ]' | grep -i d | grep -Eo '[0-9]{1,4}'| paste -sd+ | bc
echo

#count of lines per author
echo Number of lines per author
printf "Marcin: "
git ls-files | while read f; do git blame --line-porcelain $f | grep '^author '; done | sort -f | uniq -ic | sort -n | grep c | grep -Eo '[0-9]{1,4}'| paste -sd+ | bc
printf "Marek: "
git ls-files | while read f; do git blame --line-porcelain $f | grep '^author '; done | sort -f | uniq -ic | sort -n | grep e | grep -Eo '[0-9]{1,4}'| paste -sd+ | bc
printf "Dominik: "
git ls-files | while read f; do git blame --line-porcelain $f | grep '^author '; done | sort -f | uniq -ic | sort -n | grep -i d | grep -Eo '[0-9]{1,4}'| paste -sd+ | bc
echo

#Count of tests
printf "Number of tests: "
mvn test | grep elapsed | grep -Eo 'run: [0-9]{1,4}' | grep -Eo '[0-9]{1,4}'| paste -sd+ | bc

#Count number of interfaces
printf "Number of interfaces: "
grep -r --include=\*.java 'interface ' | wc -l

#Count number of packages
printf "Number of packages: "
find . -type f -name "*.java" -printf '%h\n' | sort | uniq | wc -l

#Count number of public class
printf "Number of public class: "
grep -r --include=\*.java 'public class' | wc -l
#grep -r --include=\*.java 'public class' | grep -v "test" | wc -l

#Count number of private class
printf "Number of private class: "
grep -r --include=\*.java 'private class' | wc -l

#Count number of abstract class
printf "Number of abstract class: "
grep -r --include=\*.java 'abstract class' | wc -l

#Count number of package private class
printf "Number of package private class: "
grep -r --include=\*.java "class " | grep -v "public" | grep -v "private" | wc -l