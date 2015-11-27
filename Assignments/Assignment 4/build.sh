#!/bin/bash

java -jar umple_1.22.0.5146.jar -g GvStateDiagram E169_State_Machine.ump
dot E169_State_Machine.gv -Tpdf -o E169_State_Machine.pdf
