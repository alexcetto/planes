#!/usr/bin/env python
# -*- coding: utf-8 -*-

import re

fname = "ACFTREF.txt"
output = open("test.txt","w")
with open(fname) as f:
    content = f.readlines()

for c in content:
	c = c.replace('"', '')
	c = "\",\"".join(c.rsplit(",", 11))
	c = c[:-4]
	c = c.replace('  ','')
	c = "INSERT INTO `planes` (`code`, `mfr`, `model`, `type-acft`, `type-eng`, `ac-cat`, `build-cert-ind`, `no-eng`, `no-seats`, `ac-weight`, `speed`) VALUES (\"" + c +");"
	print>>output, c
	#print c