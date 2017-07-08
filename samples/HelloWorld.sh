if [ -e HelloWorld.jar ]
then
    echo "Jar already present, skipping packaging."
else
    echo "Packaging Jar..."
    jar cfe HelloWorld.jar HelloWorld HelloWorld.class
fi

echo "Running jar"
java -jar HelloWorld.jar
