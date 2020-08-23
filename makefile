JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        ImageAnnotator.java \
        XMLParser.java \
        Main.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
