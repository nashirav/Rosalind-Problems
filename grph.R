#!/usr/bin/env Rscript

# source("http://bioconductor.org/biocLite.R")
# biocLite("ShortRead")
library(ShortRead)

k <- 3
seqs <- readFasta("./datasets/rosalind_grph.txt");

seqs.l <- nchar(sapply(sread(seqs), toString))
prefixes <- sapply(sread(ShortRead(sread=subseq(sread(seqs),start=1,end=3), id=id(seqs))), toString)
sufixes <- sapply(sread(ShortRead(sread=subseq(sread(seqs),start=seqs.l-k+1,end=seqs.l), id=id(seqs))), toString)

seqs.names <- sapply(id(seqs), toString)
x <- data.frame(prefix = prefixes, id_p = seqs.names, stringsAsFactors = FALSE)
y <- data.frame(sufix = sufixes, id_s = seqs.names, stringsAsFactors = FALSE)
cross.j <- merge(x,y,all=TRUE) #cross join

cross.j <- subset(cross.j, id_p != id_s) #del duplicates
cross.j <- cbind(cross.j, ifequal=ifelse(cross.j$prefix==cross.j$sufix,1,ifelse(cross.j$prefix==cross.j$sufix,NA,0)))
cross.j.1 <- cross.j[cross.j$ifequal==1,]

print(paste(cross.j.1$id_s, cross.j.1$id_p))