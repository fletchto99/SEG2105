#!/bin/bash
for i
do
  filename="${i%.*}"
	java -jar umple_1.22.0.5146.jar -g GvClassDiagram $filename.ump
	extension="cd.gv"
	dot $filename$extension -Tpdf -o $filename.pdf
done
