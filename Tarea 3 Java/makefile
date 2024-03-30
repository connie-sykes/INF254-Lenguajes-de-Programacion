JFLAGS = -g

JC = javac

JVM = java

MAIN = Juego

JAVAS := $(wildcard *.java)

CLASSES := $(JAVAS:.java=.class)

.PHONY: clean run

%.class: %.java
	$(JC) $(JFLAGS) $^


run: $(CLASSES)
	$(JVM) $(MAIN)

clean:
	rm -rf $(CLASSES)