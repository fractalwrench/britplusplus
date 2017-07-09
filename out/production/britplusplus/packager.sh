if [ -e CLASS_NAME.jar ]
then
    echo "Jar already present, skipping packaging."
else
    echo "Packaging Jar..."
    jar cfe CLASS_NAME.jar CLASS_NAME CLASS_NAME.class
fi

echo "Running jar"
java -jar CLASS_NAME.jar
