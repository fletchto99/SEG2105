#!/bin/bash
for i
do
  filename="${i%.*}"
	java -jar umple_1.22.0.5146.jar -g GvStateDiagram $filename.ump
	dot $filename.gv -Tpdf -o $filename.pdf
done
