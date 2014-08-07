echo "Copying resources"
cp -Rv resources build

echo "Compiling"
javac -cp "./lib/*:." main/SpaceInvader.java -d build
