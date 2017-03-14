# esignature-cl

Command line program to sign pdfs on the fly

## Libraries

### Source libraries

This program use the following libaries:

- itext5
- bc (bouncy castle)
- slf4j (not really)

In fact, you only need the following .jar as project's libraries:

- itextpdf-5.5.10.jar
- bcprov-ext-jdk15on-156.jar
- bcpkix-jdk15on-156.jar

### Test libraries

This program use the following libraries:

- junit

## Use

### Creating blank signatures (-create)

| Option | Opt/Obl | Description | +Info |
|----|----|----|----|----|
| -src | Obligatory | route of source pdf file |  |
| -dest | Obligatory | route of generated pdf file |  |
| -qos | Optional (default=1) | quantity of empty/blank signatures to add | If we use more than 3 (on top/bot margins) or 4 (on left/right margins) our signatures will not be visible |
| -margin | Optional (default=top) | margin where our empty signatures will be placed (we supose we work with DinA4 pdfs) | top left right or bot |
| -img | Optional | route of an image to add to signatures |  |

Example:

~~~
java -jar esignature-cl.jar -create -src src/main/resources/hello.pdf -dest src/main/resources/hello_test.pdf -qos 3 -margin right -img src/main/resources/icon.png
~~~

### Signing blank signatures (-sign)

| Option | Opt/Obl | Description | +Info |
|----|----|----|----|----|
| -src | Obligatory | route of source pdf file |  |
| -dest | Obligatory |   route of generated pdf file |  |
| -ks | Obligatory | keystore | It could be a .jks or a .p12 file |
| -pass | Obligatory | password | password of our keystore to use it |

Example:

~~~
java -jar esignature-cl.java -sign -src src/main/resources/hello_test.pdf -dest src/main/resources/hello_test_2.pdf -pass pass -ks src/main/resources/abc.p12
~~~

## Languaje

Java 1.8

## Link to download

<https://github.com/marcosruiz/esignature/blob/master/dist>

**Important!** You need to dowload *esignature-cl.jar* and the folder *lib*.

## Autor

Marcos Ruiz García

## Contact

sobrenombre@gmail.com

