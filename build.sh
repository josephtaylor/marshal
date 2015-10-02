#!/bin/bash

echo ' -- building with maven'
mvn clean package
echo ' -- generating javadocs'
mvn javadoc:javadoc

echo ' -- moving javadocs'
cd target/reference
cp -rv apidocs/* .
rm -rf apidocs
cd ../..

echo ' -- copying jar to library folder'
mkdir target/library
cp -v target/marshal.jar target/library/

echo ' -- move everything to target/marshal'
mkdir target/marshal
cp -v library.properties target/marshal/
cp -rv src target/marshal/
cp -rv target/reference target/marshal/
cp -rv examples target/marshal/
cp -rv target/library target/marshal/

echo ' -- generate zip file '
cd target
zip -rv marshal.zip marshal 



