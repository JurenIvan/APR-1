#! /bin/bash

java -cp bin hr.fer.apr.demo.NumericalIntegration $1 $2 $3 $4 $5 $6 && octave -q plotVars.m values.txt $4 $5 vars.png
